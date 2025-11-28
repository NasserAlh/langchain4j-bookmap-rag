---
source_file: examples/NasserDom.md
type: example
topic: depth-of-market, volume-profile, gui, swing, order-book
package: velox.api.layer1.simplified
implements: CustomModule, DepthDataListener, TradeDataListener
annotations: Layer1SimpleAttachable, Layer1StrategyName, Layer1ApiVersion, Layer1TradingStrategy
---

# NasserDom - Depth of Market with Volume Profile

A complete add-on implementing a Depth of Market (DOM) display with volume profile tracking using Swing GUI.

## Features

- Real-time order book display
- Volume profile tracking per price level
- Batch processing for performance
- Thread-safe updates
- Color-coded bid/ask columns

## Key Implementation Concepts

### Thread-Safe Data Structures

```java
private final ConcurrentSkipListMap<Integer, Integer> bids = new ConcurrentSkipListMap<>(Comparator.reverseOrder());
private final ConcurrentSkipListMap<Integer, Integer> volumeProfile = new ConcurrentSkipListMap<>();
private final ConcurrentSkipListMap<Integer, Integer> asks = new ConcurrentSkipListMap<>();
private final BlockingQueue<DepthData> depthDataQueue = new LinkedBlockingQueue<>();
```

### Batch Processing for Performance

Using a queue and executor service to batch depth updates:

```java
private void startBatchProcessing() {
    executorService.submit(() -> {
        try {
            while (true) {
                processBatchData();
                throttleBatchProcessing();
            }
        } catch (Exception e) {
            Log.info("Exception in startBatchProcessing: " + e.getMessage());
        }
    });
}

private void processBatchData() {
    List<DepthData> batch = new ArrayList<>();
    depthDataQueue.drainTo(batch);
    for (DepthData data : batch) {
        updateBook(data);
    }
    if (!batch.isEmpty()) {
        dataChanged = true;
        updateDOM();
    }
}
```

### Throttled UI Updates

```java
private static final long UPDATE_INTERVAL = 100;  // milliseconds

private void updateDOM() {
    if (!uiInitialized) return;
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastUpdateTime > UPDATE_INTERVAL && dataChanged) {
        SwingUtilities.invokeLater(this::refreshTableData);
        lastUpdateTime = currentTime;
        dataChanged = false;
    }
}
```

## Complete Code

```java
import velox.api.layer1.annotations.*;
import velox.api.layer1.common.Log;
import velox.api.layer1.data.InstrumentInfo;
import velox.api.layer1.data.TradeInfo;
import velox.api.layer1.simplified.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

@Layer1SimpleAttachable
@Layer1StrategyName("Nasser Dom")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
@Layer1TradingStrategy
public class NasserDom implements CustomModule, DepthDataListener, TradeDataListener {
    private final ConcurrentSkipListMap<Integer, Integer> bids = new ConcurrentSkipListMap<>(Comparator.reverseOrder());
    private final ConcurrentSkipListMap<Integer, Integer> volumeProfile = new ConcurrentSkipListMap<>();
    private final ConcurrentSkipListMap<Integer, Integer> asks = new ConcurrentSkipListMap<>();
    private final BlockingQueue<DepthData> depthDataQueue = new LinkedBlockingQueue<>();
    private final ReentrantLock bidsLock = new ReentrantLock();
    private final ReentrantLock asksLock = new ReentrantLock();
    private static final long UPDATE_INTERVAL = 100;
    private volatile boolean uiInitialized = false;
    private volatile boolean dataChanged = false;
    private ExecutorService executorService;
    private DefaultTableModel tableModel;
    private long lastUpdateTime = 0;
    private JTable table;

    @Override
    public void initialize(String alias, InstrumentInfo info, Api api, InitialState initialState) {
        Log.info("Initialize called.");
        initUI();
        executorService = Executors.newSingleThreadExecutor();
        startBatchProcessing();
    }

    @Override
    public void onTrade(double price, int size, TradeInfo tradeInfo) {
        int priceInTicks = (int) (price / 0.25);
        volumeProfile.merge(priceInTicks, size, Integer::sum);
        Log.info("Updated Volume Profile: " + volumeProfile.toString());
        dataChanged = true;
        updateDOM();
    }

    public static class CustomTableCellRenderer extends DefaultTableCellRenderer {
        public CustomTableCellRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);
            if (column == 0) {
                cellComponent.setBackground(Color.decode("#5072A7"));
                cellComponent.setForeground(Color.WHITE);
            } else if (column == 2) {
                cellComponent.setBackground(Color.decode("#58111A"));
                cellComponent.setForeground(Color.WHITE);
            } else {
                cellComponent.setBackground(table.getBackground());
                cellComponent.setForeground(table.getForeground());
            }
            return cellComponent;
        }
    }

    private void startBatchProcessing() {
        executorService.submit(() -> {
            try {
                while (true) {
                    processBatchData();
                    throttleBatchProcessing();
                }
            } catch (Exception e) {
                Log.info("Exception in startBatchProcessing: " + e.getMessage());
            }
        });
    }

    private void processBatchData() {
        List<DepthData> batch = new ArrayList<>();
        depthDataQueue.drainTo(batch);
        for (DepthData data : batch) {
            updateBook(data);
        }
        if (!batch.isEmpty()) {
            dataChanged = true;
            updateDOM();
        }
    }

    private void updateBook(DepthData data) {
        ReentrantLock lock = data.isBid ? bidsLock : asksLock;
        lock.lock();
        try {
            ConcurrentSkipListMap<Integer, Integer> book = data.isBid ? bids : asks;
            if (data.size == 0) {
                book.remove(data.price);
            } else {
                book.put(data.price, data.size);
            }
        } finally {
            lock.unlock();
        }
    }

    private void throttleBatchProcessing() {
        try {
            TimeUnit.MILLISECONDS.sleep(UPDATE_INTERVAL);
        } catch (InterruptedException e) {
            Log.info("Batch processing thread interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private void initUI() {
        try {
            SwingUtilities.invokeLater(this::createUI);
        } catch (Exception e) {
            Log.info("Exception in initUI: " + e.getMessage());
        }
    }

    private void createUI() {
        JFrame frame = new JFrame("Depth of Market");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 400);
        String[] columnNames = {"Bid", "Price", "Ask", "VP"};
        tableModel = new DefaultTableModel(null, columnNames);
        table = new JTable(tableModel);
        table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.setVisible(true);
        uiInitialized = true;
        Log.info("UI successfully initialized.");
    }

    @Override
    public void onDepth(boolean isBid, int price, int size) {
        if (!uiInitialized) return;
        try {
            depthDataQueue.offer(new DepthData(isBid, price, size));
        } catch (Exception e) {
            Log.info("Exception in onDepth: " + e.getMessage());
        }
    }

    private void updateDOM() {
        if (!uiInitialized) {
            Log.info("UI not initialized yet, skipping updateDOM.");
            return;
        }
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdateTime > UPDATE_INTERVAL && dataChanged) {
            SwingUtilities.invokeLater(this::refreshTableData);
            lastUpdateTime = currentTime;
            dataChanged = false;
        }
    }

    private void refreshTableData() {
        int maxRows = Math.max(bids.size(), asks.size());
        Object[][] data = new Object[maxRows][4];
        int midRow = maxRows / 2;
        populateBidData(data, midRow);
        populateAskData(data, midRow);
        populateVolumeProfileData(data, midRow);
        tableModel.setDataVector(data, new Object[]{"Bid", "Price", "Ask", "VP"});
    }

    private void populateVolumeProfileData(Object[][] data, int midRow) {
        for (Map.Entry<Integer, Integer> entry : volumeProfile.entrySet()) {
            int priceInTicks = entry.getKey();
            int volume = entry.getValue();
            double price = priceInTicks * 0.25;
            int targetRow = findRowByPrice((int) price, midRow, data);
            if (targetRow != -1) {
                data[targetRow][3] = volume;
            }
        }
    }

    private void populateBidData(Object[][] data, int midRow) {
        int i = 0;
        for (Integer price : bids.keySet()) {
            int targetRow = midRow + i;
            if (targetRow >= data.length) break;
            data[targetRow][0] = bids.get(price);
            data[targetRow][1] = price * 0.25;
            i++;
        }
    }

    private void populateAskData(Object[][] data, int midRow) {
        int i = 0;
        for (Integer price : asks.keySet()) {
            int targetRow = midRow - i - 1;
            if (targetRow < 0) break;
            data[targetRow][2] = asks.get(price);
            data[targetRow][1] = price * 0.25;
            i++;
        }
    }

    private int findRowByPrice(int price, int midRow, Object[][] data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i][1] != null && (Double) data[i][1] == price * 0.25) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void stop() {
        try {
            if (executorService != null) {
                executorService.shutdown();
                executorService.awaitTermination(5, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            Log.info("Exception in stop: " + e.getMessage());
        }
    }

    private static class DepthData {
        boolean isBid;
        int price;
        int size;

        DepthData(boolean isBid, int price, int size) {
            this.isBid = isBid;
            this.price = price;
            this.size = size;
        }
    }
}
```

## Key Takeaways

1. **Use concurrent collections** for thread-safe data sharing between callbacks and UI
2. **Batch process updates** to avoid overwhelming the UI thread
3. **Throttle UI refreshes** to maintain responsiveness
4. **Clean up resources** in `stop()` method
5. **Use SwingUtilities.invokeLater** for all UI updates

## See Also

- [Data Listeners](../guides/DataListeners.md) - DepthDataListener and TradeDataListener
- [Order Book Management](../guides/OrderBookManagement.md) - Building order books
- [OnTrade Example](OnTrade.md) - Another volume profile implementation
