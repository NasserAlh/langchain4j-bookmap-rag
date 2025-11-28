---
source_file: examples/OrderBlockStrategy.md
type: example
topic: trading-strategy, indicators, settings, bar-data, order-placement
package: velox.api.layer1.simplified
implements: CustomModule, BarDataListener, TimeListener, CustomSettingsPanelProvider
annotations: Layer1SimpleAttachable, Layer1StrategyName, Layer1ApiVersion, Layer1TradingStrategy
---

# OrderBlockStrategy - Trading Strategy with Indicators

A complete trading strategy demonstrating custom settings panels, indicator registration, bar data processing, and order placement.

## Features

- Bar data analysis for order block detection
- Custom settings panel with persistent configuration
- Indicator with icon markers on chart
- Order placement with stop loss and take profit
- Bullish/Bearish order block identification

## Key Concepts

### Settings with Persistence

Using `@StrategySettingsVersion` for versioned, persistent settings:

```java
@StrategySettingsVersion(currentVersion = 1, compatibleVersions = {})
public static class Settings {
    int requiredSequentialCandles = 5;
    double minPercentMove = 0.00;
}

// In initialize()
settings = api.getSettings(Settings.class);
```

### Indicator Registration

Register indicators on the primary chart:

```java
indicator = api.registerIndicator("OrderBlockIndicator", GraphType.PRIMARY);
```

### Adding Icons to Indicators

Display visual markers at specific price levels:

```java
indicator.addIcon(priceLevel, iconImage, iconCenterX, iconCenterY);
```

### Bar Interval Configuration

Implementing `BarDataListener` requires specifying the bar interval:

```java
@Override
public long getInterval() {
    return Intervals.INTERVAL_2_MINUTES;
}
```

Available intervals include:
- `Intervals.INTERVAL_1_MINUTE`
- `Intervals.INTERVAL_2_MINUTES`
- `Intervals.INTERVAL_5_MINUTES`
- `Intervals.INTERVAL_15_MINUTES`
- `Intervals.INTERVAL_1_HOUR`

### Custom Settings Panel

Implement `CustomSettingsPanelProvider` for UI settings:

```java
@Override
public StrategyPanel[] getCustomSettingsPanels() {
    StrategyPanel p1 = new StrategyPanel("Required Sequential Candles");
    JSpinner spinner = new JSpinner(new SpinnerNumberModel(
        settings.requiredSequentialCandles, 1, 100, 1));
    spinner.addChangeListener(e -> {
        settings.requiredSequentialCandles = (Integer) spinner.getValue();
        api.setSettings(settings);  // Persist changes
    });
    p1.add(spinner);
    return new StrategyPanel[] { p1 };
}
```

## Complete Code

```java
package main;

import util.iconClass;
import velox.api.layer1.annotations.*;
import velox.api.layer1.common.Log;
import velox.api.layer1.data.InstrumentInfo;
import velox.api.layer1.data.OrderDuration;
import velox.api.layer1.data.SimpleOrderSendParameters;
import velox.api.layer1.data.SimpleOrderSendParametersBuilder;
import velox.api.layer1.layers.utils.OrderBook;
import velox.api.layer1.settings.StrategySettingsVersion;
import velox.api.layer1.simplified.*;
import velox.api.layer1.messages.indicators.Layer1ApiUserMessageModifyIndicator.GraphType;
import velox.gui.StrategyPanel;

import javax.swing.*;
import javax.swing.SpinnerNumberModel;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Layer1SimpleAttachable
@Layer1StrategyName("Order Blocks Strategy WW")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
@Layer1TradingStrategy
public class OrderBlockStrategy2 implements BarDataListener, CustomModule,
                                            TimeListener, CustomSettingsPanelProvider {
    private static final String BEARISH_OB = "Bearish OB";
    private static final String BULLISH_OB = "Bullish OB";
    private Indicator indicator;
    private int requiredSequentialCandles;
    private double minPercentMove;
    private final List<Bar> previousBars = new ArrayList<>();
    private Api api;
    private BufferedImage bearishIcon;
    private BufferedImage bullishIcon;
    private String alias;

    @StrategySettingsVersion(currentVersion = 1, compatibleVersions = {})
    public static class Settings {
        int requiredSequentialCandles = 5;
        double minPercentMove = 0.00;
    }

    private Settings settings;

    @Override
    public void initialize(String alias, InstrumentInfo info, Api api, InitialState initialState) {
        this.api = api;
        this.alias = alias;
        settings = api.getSettings(Settings.class);
        requiredSequentialCandles = settings.requiredSequentialCandles;
        minPercentMove = settings.minPercentMove;

        indicator = api.registerIndicator("OrderBlockIndicator", GraphType.PRIMARY);

        iconClass bearishIconHandler = new iconClass(
            "/icons8-arrow-down-30.png", "/default-arrow-down.png");
        bearishIcon = bearishIconHandler.getIcon();
        iconClass bullishIconHandler = new iconClass(
            "/icons8-arrow-up-30.png", "/default-arrow-up.png");
        bullishIcon = bullishIconHandler.getIcon();
    }

    private double calculatePercentChange(List<Bar> bars) {
        if (bars == null || bars.size() < 2) {
            Log.info("Not enough bars to calculate percent change. Returning 0.");
            return 0.0;
        }

        double firstClose = bars.get(0).getClose();
        double lastClose = bars.get(bars.size() - 1).getClose();

        return ((lastClose - firstClose) / firstClose) * 100;
    }

    @Override
    public void onTimestamp(long nanoseconds) {
        // Handle timestamp logic if needed
    }

    @Override
    public void onBar(OrderBook orderBook, Bar bar) {
        int iconCenterX = (bearishIcon != null) ? bearishIcon.getWidth() / 2 : 0;
        int iconCenterY = (bearishIcon != null) ? bearishIcon.getHeight() / 2 : 0;

        if (previousBars.size() >= requiredSequentialCandles && indicator != null) {
            double percentChange = calculatePercentChange(previousBars);
            if (percentChange >= minPercentMove) {
                // Detect bearish order block
                if (previousBars.get(0).getClose() > previousBars.get(0).getOpen()
                    && bar.getClose() < bar.getOpen()) {
                    Log.info(BEARISH_OB);
                    indicator.addIcon(previousBars.get(0).getHigh(),
                                     bearishIcon, iconCenterX, iconCenterY);
                }
                // Detect bullish order block
                else if (previousBars.get(0).getClose() < previousBars.get(0).getOpen()
                         && bar.getClose() > bar.getOpen()) {
                    Log.info(BULLISH_OB);
                    indicator.addIcon(previousBars.get(0).getLow(),
                                     bullishIcon, iconCenterX, iconCenterY);
                }
            }
        }
        previousBars.add(bar);
        if (previousBars.size() > requiredSequentialCandles) {
            previousBars.remove(0);
        }
    }

    @Override
    public long getInterval() {
        return Intervals.INTERVAL_2_MINUTES;
    }

    @Override
    public void stop() {
        Log.info("OrderBlockAddOn stopped");
    }

    @Override
    public StrategyPanel[] getCustomSettingsPanels() {
        StrategyPanel p1 = new StrategyPanel("Required Sequential Candles");
        JSpinner candlesSpinner = new JSpinner(
            new SpinnerNumberModel(settings.requiredSequentialCandles, 1, 100, 1));
        candlesSpinner.addChangeListener(e -> {
            settings.requiredSequentialCandles = (Integer) candlesSpinner.getValue();
            api.setSettings(settings);
        });
        p1.add(candlesSpinner);

        StrategyPanel p2 = new StrategyPanel("Minimum Percentage Move");
        JSpinner percentMoveSpinner = new JSpinner(
            new SpinnerNumberModel(settings.minPercentMove, 0.0, 100.0, 0.1));
        percentMoveSpinner.addChangeListener(e -> {
            settings.minPercentMove = (Double) percentMoveSpinner.getValue();
            api.setSettings(settings);
        });
        p2.add(percentMoveSpinner);

        return new StrategyPanel[] { p1, p2 };
    }

    private void placeOrder() {
        SimpleOrderSendParametersBuilder builder =
            new SimpleOrderSendParametersBuilder(alias, true, 1);
        builder.setStopLossOffset(10);
        builder.setTakeProfitOffset(10);
        builder.setDuration(OrderDuration.IOC);

        SimpleOrderSendParameters order = builder.build();
        api.sendOrder(order);
    }
}
```

## Order Block Detection Logic

The strategy identifies order blocks based on candle patterns:

1. **Bearish Order Block**: When a bullish candle (close > open) is followed by a bearish candle (close < open) after a sequence of candles with sufficient percent move
2. **Bullish Order Block**: When a bearish candle (close < open) is followed by a bullish candle (close > open) after a sequence of candles with sufficient percent move

## Key Takeaways

1. **Use `@StrategySettingsVersion`** for persistent, versioned settings
2. **Implement `CustomSettingsPanelProvider`** for user-configurable parameters
3. **Register indicators** with `api.registerIndicator()` for chart overlays
4. **Use `addIcon()`** to place visual markers at price levels
5. **Implement `getInterval()`** to specify bar aggregation period
6. **Use `@Layer1TradingStrategy`** annotation for order placement capability

## See Also

- [Api Interface](../guides/ApiInterface.md) - Indicator registration and settings
- [Order Placement](../guides/OrderPlacement.md) - Order submission details
- [Data Listeners](../guides/DataListeners.md) - BarDataListener interface
- [Getting Started](../guides/GettingStarted.md) - Basic add-on structure
