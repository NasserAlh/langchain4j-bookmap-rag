---
source_file: examples/OnTrade.md
type: example
topic: volume-profile, poc, value-area, gui, swing
package: velox.api.layer1.simplified
implements: CustomModule, TradeDataListener
annotations: Layer1SimpleAttachable, Layer1StrategyName, Layer1ApiVersion
---

# OnTrade - Volume Profile Panel

A volume profile add-on with POC (Point of Control) and Value Area calculations, displayed in a custom Swing panel.

## Features

- Real-time volume profile tracking
- Point of Control (POC) calculation
- Value Area calculation (70% of volume)
- Configurable POC and Value Area visibility
- Custom rendering panel

## Key Concepts

### Volume Profile Data Structure

Using `AtomicReference` for thread-safe updates:

```java
private final AtomicReference<ConcurrentSkipListMap<Double, Integer>> volumeProfileRef =
    new AtomicReference<>(new ConcurrentSkipListMap<>());
```

### Point of Control Calculation

POC is the price level with the highest volume:

```java
pointOfControl = newVolumeProfile.entrySet().stream()
    .max(Map.Entry.comparingByValue())
    .map(Map.Entry::getKey)
    .orElse(pointOfControl);
```

### Value Area Calculation

Value Area contains 70% of total volume:

```java
private ValueArea calculateValueArea(ConcurrentSkipListMap<Double, Integer> volumeProfile,
                                     double threshold) {
    double totalVolume = volumeProfile.values().stream()
        .mapToDouble(Integer::doubleValue).sum();
    double targetVolume = totalVolume * threshold;

    List<Map.Entry<Double, Integer>> sortedEntries = new ArrayList<>(volumeProfile.entrySet());
    sortedEntries.sort(Map.Entry.<Double, Integer>comparingByValue().reversed());

    double accumulatedVolume = 0;
    double lower = Double.MAX_VALUE;
    double upper = Double.MIN_VALUE;

    for (Map.Entry<Double, Integer> entry : sortedEntries) {
        double price = entry.getKey();
        accumulatedVolume += entry.getValue();
        lower = Math.min(lower, price);
        upper = Math.max(upper, price);
        if (accumulatedVolume >= targetVolume) {
            break;
        }
    }

    return new ValueArea(lower, upper);
}
```

## Complete Code

```java
package VP1;

import velox.api.layer1.annotations.*;
import velox.api.layer1.data.TradeInfo;
import velox.api.layer1.simplified.Api;
import velox.api.layer1.simplified.CustomModule;
import velox.api.layer1.simplified.InitialState;
import velox.api.layer1.data.InstrumentInfo;
import velox.api.layer1.common.Log;
import velox.api.layer1.simplified.TradeDataListener;

import javax.swing.*;
import java.awt.BorderLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicReference;

@Layer1SimpleAttachable
@Layer1StrategyName("VP Price Added")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
public class OnTrade implements TradeDataListener, CustomModule {

    private final AtomicReference<ConcurrentSkipListMap<Double, Integer>> volumeProfileRef =
        new AtomicReference<>(new ConcurrentSkipListMap<>());
    private double pointOfControl = 0.0;
    private double currentPrice = 0.0;
    private VolumeProfilePanel volumeProfilePanel;
    private JFrame frame;

    @Override
    public void initialize(String alias, InstrumentInfo info, Api api, InitialState initialState) {
        Runnable guiInitRunnable = () -> {
            frame = new JFrame("Volume Profile");
            volumeProfilePanel = new VolumeProfilePanel(volumeProfileRef.get());

            JCheckBox showPOC = new JCheckBox("Show POC", true);
            showPOC.addActionListener(e ->
                volumeProfilePanel.setShowPOC(showPOC.isSelected()));

            JCheckBox showValueArea = new JCheckBox("Show Value Area", true);
            showValueArea.addActionListener(e ->
                volumeProfilePanel.setShowValueArea(showValueArea.isSelected()));

            JPanel optionsPanel = new JPanel();
            optionsPanel.add(showPOC);
            optionsPanel.add(showValueArea);

            frame.add(optionsPanel, BorderLayout.NORTH);

            JScrollPane scrollPane = new JScrollPane(volumeProfilePanel);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            frame.add(scrollPane);
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        };

        if (SwingUtilities.isEventDispatchThread()) {
            guiInitRunnable.run();
        } else {
            try {
                SwingUtilities.invokeAndWait(guiInitRunnable);
            } catch (InterruptedException | InvocationTargetException e) {
                Log.error("Error initializing GUI", e);
            }
        }
    }

    @Override
    public void stop() {
        if (frame != null) {
            frame.dispose();
        }
    }

    @Override
    public synchronized void onTrade(double price, int size, TradeInfo tradeInfo) {
        currentPrice = price;

        // Create new map with updated volume
        ConcurrentSkipListMap<Double, Integer> newVolumeProfile =
            new ConcurrentSkipListMap<>(volumeProfileRef.get());
        newVolumeProfile.merge(price, size, Integer::sum);
        volumeProfileRef.set(newVolumeProfile);

        if (newVolumeProfile.isEmpty()) {
            Log.info("Volume Profile is empty.");
            return;
        }

        // Calculate Value Area (70% of volume)
        ValueArea va = calculateValueArea(newVolumeProfile, 0.7);

        // Calculate POC (price with highest volume)
        pointOfControl = newVolumeProfile.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(pointOfControl);

        volumeProfilePanel.setPointOfControl(pointOfControl);

        // Update UI on EDT
        SwingUtilities.invokeLater(() -> {
            volumeProfilePanel.updateVolumeProfile(
                new ConcurrentSkipListMap<>(newVolumeProfile));
            volumeProfilePanel.setValueAreaBounds(va.lowerBound, va.upperBound);
            volumeProfilePanel.setCurrentMarketPrice(currentPrice);
        });
    }

    private ValueArea calculateValueArea(ConcurrentSkipListMap<Double, Integer> volumeProfile,
                                         double threshold) {
        double totalVolume = volumeProfile.values().stream()
            .mapToDouble(Integer::doubleValue).sum();
        double targetVolume = totalVolume * threshold;

        List<Map.Entry<Double, Integer>> sortedEntries =
            new ArrayList<>(volumeProfile.entrySet());
        sortedEntries.sort(Map.Entry.<Double, Integer>comparingByValue().reversed());

        double accumulatedVolume = 0;
        double lower = Double.MAX_VALUE;
        double upper = Double.MIN_VALUE;

        for (Map.Entry<Double, Integer> entry : sortedEntries) {
            double price = entry.getKey();
            accumulatedVolume += entry.getValue();
            lower = Math.min(lower, price);
            upper = Math.max(upper, price);
            if (accumulatedVolume >= targetVolume) {
                break;
            }
        }

        return new ValueArea(lower, upper);
    }

    private static class ValueArea {
        public final double lowerBound;
        public final double upperBound;

        public ValueArea(double lowerBound, double upperBound) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }
    }
}
```

## VolumeProfilePanel (Supporting Class)

The `VolumeProfilePanel` class should implement custom painting for:
- Volume bars at each price level
- POC highlighting
- Value Area shading
- Current market price indicator

## Key Takeaways

1. **Use AtomicReference** for thread-safe reference updates
2. **Calculate POC** as max volume price level
3. **Calculate Value Area** by accumulating volume from highest to lowest until threshold reached
4. **Use invokeAndWait** for initialization to ensure UI is ready
5. **Use invokeLater** for subsequent updates

## See Also

- [Data Listeners](../guides/DataListeners.md) - TradeDataListener interface
- [NasserDom Example](NasserDom.md) - Another volume profile implementation
- [Getting Started](../guides/GettingStarted.md) - Basic add-on structure
