---
source_file: Comprehensive_Guide_Bookmap_Addons.md
type: guide
topic: bookmap-addon-development, trading-strategy, market-data
package: velox.api.layer1.simplified
related_classes: CustomModule, CustomModuleAdapter, Api, InstrumentInfo, InitialState
related_interfaces: DepthDataListener, TradeDataListener, BarDataListener, TimeListener, HistoricalDataListener, MarketByOrderDepthDataListener
annotations: Layer1SimpleAttachable, Layer1ApiVersion, Layer1StrategyName, Layer1TradingStrategy, NoAutosubscription
examples: HelloBookmapApi, MarketDataListener, NasserDom, OrderPlacerAddon, OrderBlockStrategy2, OnTrade
sections: minimum-requirements, data-listeners, order-book-management, historical-data, api-interface, order-placement, complete-examples
---

# Comprehensive Guide to Bookmap Add-On Development

This guide provides comprehensive documentation for developing Bookmap add-ons using the Simplified API.

---

## Table of Contents

- [1. Important Note](#1-important-note)
- [2. Minimum Requirements](#2-minimum-requirements)
  - [2.1 Required Annotations](#21-required-annotations)
  - [2.2 Required Implementation](#22-required-implementation)
  - [2.3 Example: Basic Add-On](#23-example-basic-add-on)
  - [2.4 Example: Add-On with Logging](#24-example-add-on-with-logging)
- [3. Data Listeners](#3-data-listeners)
  - [3.1 TimeListener](#31-timelistener)
  - [3.2 DepthDataListener (MBP Data)](#32-depthdatalistener-mbp-data)
  - [3.3 TradeDataListener](#33-tradedatalistener)
  - [3.4 BarDataListener](#34-bardatalistener)
  - [3.5 MarketByOrderDepthDataListener (MBO Data)](#35-marketbyorderdepthdatalistener-mbo-data)
- [4. Order Book Management](#4-order-book-management)
  - [4.1 MBP Order Book](#41-mbp-order-book)
  - [4.2 MBO Order Book](#42-mbo-order-book)
- [5. Historical Data](#5-historical-data)
  - [5.1 HistoricalDataListener](#51-historicaldatalistener)
  - [5.2 HistoricalModeListener](#52-historicalmodelistener)
  - [5.3 Types of Historical Data](#53-types-of-historical-data)
- [6. Api Interface](#6-api-interface)
  - [6.1 Adding Listeners](#61-adding-listeners)
  - [6.2 Registering Indicators](#62-registering-indicators)
  - [6.3 Order Management](#63-order-management)
  - [6.4 Settings Management](#64-settings-management)
  - [6.5 Module Management](#65-module-management)
  - [6.6 Utility Methods](#66-utility-methods)
- [7. Order Placement](#7-order-placement)
  - [7.1 SimpleOrderSendParametersBuilder](#71-simpleordersendparametersbuilder)
  - [7.2 Example: OrderPlacer Class](#72-example-orderplacer-class)
- [8. Complete Examples](#8-complete-examples)
  - [8.1 NasserDom - Depth of Market with Volume Profile](#81-nasserdom---depth-of-market-with-volume-profile)
  - [8.2 OnTrade - Volume Profile Panel](#82-ontrade---volume-profile-panel)
  - [8.3 OrderBlockStrategy2 - Trading Strategy with Indicators](#83-orderblockstrategy2---trading-strategy-with-indicators)

---

## 1. Important Note

When creating a Bookmap add-on, it's important to understand that it operates as part of the larger Bookmap platform. This means that typical standalone Java entry points, such as `public static void main(String[] args)`, are not used or allowed. Instead, the add-on lifecycle is managed by Bookmap itself.

---

## 2. Minimum Requirements

### 2.1 Required Annotations

To create a Bookmap add-on, your class must include:

| Annotation | Purpose |
|------------|---------|
| `@Layer1SimpleAttachable` | Makes the add-on attachable to instruments |
| `@Layer1ApiVersion` | Specifies API version compatibility |
| `@Layer1StrategyName` | Display name shown in Bookmap UI |
| `@Layer1TradingStrategy` | Required for add-ons that place orders |

### 2.2 Required Implementation

Implement one of:
- `CustomModule` interface
- `CustomModuleAdapter` adapter (provides empty default implementations)

### 2.3 Example: Basic Add-On

```java
@Layer1SimpleAttachable
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
public class HelloBookmapApiMinimum implements CustomModuleAdapter {
}
```

### 2.4 Example: Add-On with Logging

```java
@Layer1SimpleAttachable
@Layer1StrategyName("Hello Bookmap API with logs")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
public class HelloBookmapApiWithLogs implements CustomModule {

    @Override
    public void initialize(String alias, InstrumentInfo info, Api api, InitialState initialState) {
        Log.info("Hello");
    }

    @Override
    public void stop() {
        Log.info("Bye");
    }
}
```

---

## 3. Data Listeners

### 3.1 TimeListener

Add-ons implementing `TimeListener` receive `onTimestamp` callback before any other update, providing the true timestamp for subsequent updates.

```java
@Layer1SimpleAttachable
@Layer1StrategyName("Market Data Listener")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
public class MarketDataListener implements CustomModuleAdapter, TimeListener {

    private long timestamp;

    @Override
    public void onTimestamp(long nanoseconds) {
        timestamp = nanoseconds;
    }
}
```

### 3.2 DepthDataListener (MBP Data)

Implement `DepthDataListener` to receive Market By Price (MBP) depth updates.

**Method:**
```java
void onDepth(boolean isBid, int price, int size)
```

**Parameters:**
- `isBid` - `true` for bid side, `false` for ask side
- `price` - Price level (as level number)
- `size` - New size (0 if level removed)

**Example:**
```java
@Layer1SimpleAttachable
@Layer1StrategyName("Market Data Listener")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
public class MarketDataListener implements CustomModuleAdapter, DepthDataListener {

    private final TreeMap<Integer, Integer> bids = new TreeMap<>(Comparator.reverseOrder());
    private final TreeMap<Integer, Integer> asks = new TreeMap<>();

    @Override
    public void onDepth(boolean isBid, int price, int size) {
        TreeMap<Integer, Integer> book = isBid ? bids : asks;
        if (size == 0) {
            book.remove(price);
        } else {
            book.put(price, size);
        }
    }
}
```

### 3.3 TradeDataListener

Implement `TradeDataListener` to receive trade events.

**Method:**
```java
void onTrade(double price, int size, TradeInfo tradeInfo)
```

**Parameters:**
- `price` - Trade price
- `size` - Trade size
- `tradeInfo` - Additional trade information (includes `isBidAggressor`)

**Example: VWAP Calculation**
```java
public class Vwap {
    public double priceSize = 0;
    public long volume = 0;

    public void onTrade(double price, int size) {
        priceSize += price * size;
        volume += size;
    }
}
```

```java
@Layer1SimpleAttachable
@Layer1StrategyName("Market Data Listener")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
public class MarketDataListener implements CustomModuleAdapter, TradeDataListener {

    private final Vwap buyers = new Vwap();
    private final Vwap sellers = new Vwap();
    private double minPriceIncrement;

    @Override
    public void initialize(String alias, InstrumentInfo info, Api api, InitialState initialState) {
        minPriceIncrement = info.pips;
    }

    @Override
    public void onTrade(double price, int size, TradeInfo tradeInfo) {
        (tradeInfo.isBidAggressor ? buyers : sellers).onTrade(price, size);
    }

    @Override
    public void stop() {
        double vwapBuy = minPriceIncrement * buyers.priceSize / buyers.volume;
        double vwapSell = minPriceIncrement * sellers.priceSize / sellers.volume;
        double vwap = minPriceIncrement * (buyers.priceSize + sellers.priceSize) / (buyers.volume + sellers.volume);
        Log.info(String.format("VWAP Buy: %.2f, Sell: %.2f, Total: %.2f", vwapBuy, vwapSell, vwap));
    }
}
```

### 3.4 BarDataListener

Implement `BarDataListener` to receive OHLC bar data at fixed intervals.

**Methods:**
```java
void onBar(OrderBook orderBook, Bar bar)
long getInterval()  // From IntervalListener
```

**Parameters:**
- `orderBook` - Order book snapshot when bar completes
- `bar` - Bar data (OHLC values)

### 3.5 MarketByOrderDepthDataListener (MBO Data)

MBO (Market By Order) data provides order-by-order information from CME (available via Rithmic since 2017).

**Methods:**
```java
void send(String orderId, boolean isBid, int price, int size)
void replace(String orderId, int price, int size)
void cancel(String orderId)
```

**Example:**
```java
@Layer1SimpleAttachable
@Layer1StrategyName("Market Data Listener")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
public class MarketDataListener implements CustomModuleAdapter, MarketByOrderDepthDataListener {

    private final OrderBookMbo orderBook = new OrderBookMbo();

    @Override
    public void send(String orderId, boolean isBid, int price, int size) {
        orderBook.send(orderId, isBid, price, size);
    }

    @Override
    public void replace(String orderId, int price, int size) {
        orderBook.replace(orderId, price, size);
    }

    @Override
    public void cancel(String orderId) {
        orderBook.cancel(orderId);
    }
}
```

---

## 4. Order Book Management

### 4.1 MBP Order Book

The Order Book consists of `bids` (descending order) and `asks` (ascending order) collections.

**Getting Best Price and Size:**
```java
private void demoBestPriceSize() {
    int bestBidPrice = bids.firstKey();
    int bestAskPrice = asks.firstKey();
    int bestBidSize = bids.firstEntry().getValue();
    int bestAskSize = asks.firstEntry().getValue();
}
```

**Summing Price Levels:**
```java
private int demoSumOfPriceLevels(boolean isBid, int numLevelsToSum) {
    int sizeOfTopLevels = 0;
    for (Integer size : (isBid ? bids : asks).values()) {
        if (--numLevelsToSum < 0) {
            break;
        }
        sizeOfTopLevels += size;
    }
    return sizeOfTopLevels;
}
```

### 4.2 MBO Order Book

Custom MBO Order Book implementation with order-level granularity:

```java
public class OrderBook implements MarketByOrderDepthDataListener {

    public static class Order {
        public String orderId;
        public boolean isBid;
        public int price;
        public int size;

        public Order(String orderId, boolean isBid, int price, int size) {
            this.orderId = orderId;
            this.isBid = isBid;
            this.price = price;
            this.size = size;
        }
    }

    public final TreeMap<Integer, Map<String, Order>> asks = new TreeMap<>((a, b) -> a - b);
    public final TreeMap<Integer, Map<String, Order>> bids = new TreeMap<>((a, b) -> b - a);
    public final Map<String, Order> orders = new HashMap<>();

    @Override
    public void send(String orderId, boolean isBid, int price, int size) {
        Order order = new Order(orderId, isBid, price, size);
        orders.put(orderId, order);
        (isBid ? bids : asks).computeIfAbsent(price, k -> new LinkedHashMap<>()).put(orderId, order);
    }

    @Override
    public void replace(String orderId, int price, int size) {
        Order order = orders.get(orderId);
        if (price == order.price && size < order.size) {
            order.size = size;
        } else {
            cancel(orderId);
            send(orderId, order.isBid, price, size);
        }
    }

    @Override
    public void cancel(String orderId) {
        Order order = orders.remove(orderId);
        Map<String, Order> priceLevel = (order.isBid ? bids : asks).get(order.price);
        priceLevel.remove(orderId);
        if (priceLevel.isEmpty()) {
            (order.isBid ? bids : asks).remove(order.price);
        }
    }
}
```

**Getting Total Size at Price Level:**
```java
public int getTotalSizeAtPrice(boolean isBid, int price) {
    return (isBid ? bids : asks).get(price).values().stream().mapToInt(order -> order.size).sum();
}
```

**Direct Subscription with @NoAutosubscription:**
```java
@NoAutosubscription
@Layer1SimpleAttachable
@Layer1StrategyName("Market Data Listener")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
public class MarketDataListener implements CustomModuleAdapter {

    private final OrderBook orderBook = new OrderBook();

    @Override
    public void initialize(String alias, InstrumentInfo info, Api api, InitialState initialState) {
        api.addMarketByOrderDepthDataListeners(orderBook);
    }
}
```

---

## 5. Historical Data

### 5.1 HistoricalDataListener

Implement `HistoricalDataListener` to handle pre-subscription market data.

### 5.2 HistoricalModeListener

Use `HistoricalModeListener` to get notified when pre-subscription data processing completes.

```java
@Layer1SimpleAttachable
@Layer1StrategyName("Market Data Listener")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
public class MarketDataListener implements CustomModuleAdapter, TradeDataListener, HistoricalModeListener {

    private final Vwap buyers = new Vwap();
    private final Vwap sellers = new Vwap();
    private double minPriceIncrement;
    private Api api;

    @Override
    public void initialize(String alias, InstrumentInfo info, Api api, InitialState initialState) {
        this.api = api;
        minPriceIncrement = info.pips;
    }

    @Override
    public void onTrade(double price, int size, TradeInfo tradeInfo) {
        (tradeInfo.isBidAggressor ? buyers : sellers).onTrade(price, size);
    }

    @Override
    public void stop() {
        double vwapBuy = minPriceIncrement * buyers.priceSize / buyers.volume;
        double vwapSell = minPriceIncrement * sellers.priceSize / sellers.volume;
        double vwap = minPriceIncrement * (buyers.priceSize + sellers.priceSize) / (buyers.volume + sellers.volume);
        Log.info(String.format("VWAP Buy: %.2f, Sell: %.2f, Total: %.2f", vwapBuy, vwapSell, vwap));
    }

    @Override
    public void onRealtimeStart() {
        Log.info("Real-time data started. Unloading...");
        api.unload();
    }
}
```

### 5.3 Types of Historical Data

| Type | Description |
|------|-------------|
| Pre-subscription data | Collected by Bookmap before the add-on was enabled |
| Backfill data | Up to 48 hours of data from Bookmap servers |
| Historical data | Replay data stored in Bookmap's `.bmf` files |

---

## 6. Api Interface

The `Api` interface allows communication back to Bookmap.

### 6.1 Adding Listeners

Methods for manually adding listeners when using `@NoAutosubscription`:

| Method | Listener Type |
|--------|---------------|
| `addBalanceListeners(BalanceListener)` | Balance updates |
| `addBarDataListeners(BarDataListener)` | Bar/candle data |
| `addBboDataListeners(BboListener)` | Best bid/offer |
| `addDepthDataListeners(DepthDataListener)` | MBP depth |
| `addHistoricalModeListeners(HistoricalModeListener)` | Historical mode changes |
| `addIntervalListeners(IntervalListener)` | Interval callbacks |
| `addMarketByOrderDepthDataListeners(MarketByOrderDepthDataListener)` | MBO depth |
| `addMultiInstrumentListeners(MultiInstrumentListener)` | Multi-instrument |
| `addOrdersListeners(OrdersListener)` | Order updates |
| `addSnapshotEndListeners(SnapshotEndListener)` | Snapshot completion |
| `addStatusListeners(PositionListener)` | Position updates |
| `addTimeListeners(TimeListener)` | Timestamp updates |
| `addTradeDataListeners(TradeDataListener)` | Trade events |

### 6.2 Registering Indicators

```java
// Basic registration
Indicator registerIndicator(String name, GraphType graphType)

// With initial value
Indicator registerIndicator(String name, GraphType graphType, double initialValue)

// Full control
Indicator registerIndicator(String name, GraphType graphType, double initialValue,
                           boolean showLineByDefault, boolean showWidgetByDefault)

// Modifiable variants
IndicatorModifiable registerIndicatorModifiable(String name, GraphType graphType)
IndicatorModifiable registerIndicatorModifiable(String name, GraphType graphType, double initialValue)
IndicatorModifiable registerIndicatorModifiable(String name, GraphType graphType, double initialValue,
                                                boolean showLineByDefault, boolean showWidgetByDefault)
```

**GraphType Options:**
- `GraphType.PRIMARY` - Main chart
- `GraphType.BOTTOM` - Bottom panel

### 6.3 Order Management

```java
// Submit order
void sendOrder(OrderSendParameters orderSendParameters)

// Update existing order
void updateOrder(OrderUpdateParameters orderUpdateParameters)
```

### 6.4 Settings Management

```java
// Store settings (class must have @StrategySettingsVersion)
<T> void setSettings(T settingsObject)

// Retrieve settings
<T> T getSettings(Class<? extends T> settingsClass)
```

### 6.5 Module Management

```java
// Request unload (use when error detected)
void unload()

// Request reload (typically for settings changes)
void reload()
```

### 6.6 Utility Methods

```java
// Communicate with other modules
void sendUserMessage(Object data)

// Advanced L1 stack access
Layer1ApiProvider getProvider()
```

---

## 7. Order Placement

### 7.1 SimpleOrderSendParametersBuilder

**Package:** `velox.api.layer1.data`

**Inherited Fields from AbstractSimpleOrderSendParametersBuilder:**
- `additionalStopLossTiers`, `additionalTakeProfitTiers`
- `closingPositionHint`, `reversingPositionHint`
- `doNotIncrease`, `limitPrice`, `stopPrice`
- `stopLossClientId`, `stopLossOffset`, `stopLossTrailingStep`
- `takeProfitClientId`, `takeProfitOffset`, `trailingStep`

**Inherited Fields from AbstractSingleOrderSendParametersBuilder:**
- `alias`, `clientId`, `duration`, `isBuy`, `size`

**Constructor:**
```java
public SimpleOrderSendParametersBuilder(String alias, boolean isBuy, int size)
```

### 7.2 Example: OrderPlacer Class

**Integration Steps:**

1. Define the field:
```java
private OrderPlacer orderPlacer;
```

2. Initialize in `initialize()`:
```java
@Override
public void initialize(String alias, InstrumentInfo info, Api api, InitialState initialState) {
    orderPlacer = new OrderPlacer(alias, api);
}
```

3. Use in callbacks:
```java
@Override
public void onTrade(double price, int size, TradeInfo tradeInfo) {
    orderPlacer.placeOrder(true, price, 1, 0, 20, 10);
}
```

**Complete OrderPlacer Implementation:**
```java
class OrderPlacer {
    private final String alias;
    private final Api api;

    public OrderPlacer(String alias, Api api) {
        if (alias == null || api == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        this.alias = alias;
        this.api = api;
    }

    public boolean placeOrder(boolean isBuy, double price, int quantity, int trailingStopStep,
                              int takeProfitOffset, int stopLossOffset) {
        SimpleOrderSendParametersBuilder builder = new SimpleOrderSendParametersBuilder(alias, isBuy, quantity);
        builder.setDuration(OrderDuration.IOC);
        builder.setTakeProfitOffset(takeProfitOffset);
        builder.setStopLossOffset(stopLossOffset);

        if (trailingStopStep > 0) {
            builder.setStopLossTrailingStep(trailingStopStep);
        }

        SimpleOrderSendParameters order = builder.build();
        api.sendOrder(order);
        return true;
    }
}
```

**Complete Add-On Example:**
```java
import velox.api.layer1.data.OrderDuration;
import velox.api.layer1.data.SimpleOrderSendParameters;
import velox.api.layer1.data.SimpleOrderSendParametersBuilder;
import velox.api.layer1.simplified.Api;
import velox.api.layer1.simplified.CustomModuleAdapter;
import velox.api.layer1.simplified.InitialState;
import velox.api.layer1.simplified.InstrumentInfo;
import velox.api.layer1.simplified.Layer1ApiVersionValue;
import velox.api.layer1.simplified.TradeInfo;
import velox.api.layer1.simplified.Log;
import velox.api.layer1.simplified.annotations.Layer1SimpleAttachable;
import velox.api.layer1.simplified.annotations.Layer1StrategyName;
import velox.api.layer1.simplified.annotations.Layer1ApiVersion;

@Layer1SimpleAttachable
@Layer1StrategyName("Order Placer Example")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
@Layer1TradingStrategy
public class OrderPlacerAddon extends CustomModuleAdapter {

    private OrderPlacer orderPlacer;

    @Override
    public void initialize(String alias, InstrumentInfo info, Api api, InitialState initialState) {
        orderPlacer = new OrderPlacer(alias, api);
    }

    @Override
    public void onTrade(double price, int size, TradeInfo tradeInfo) {
        orderPlacer.placeOrder(true, price, 1, 0, 20, 10);
    }
}
```

---

## 8. Complete Examples

### 8.1 NasserDom - Depth of Market with Volume Profile

A complete add-on implementing `CustomModule`, `DepthDataListener`, and `TradeDataListener` with a Swing GUI.

**Features:**
- Real-time order book display
- Volume profile tracking
- Batch processing for performance
- Thread-safe updates

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
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
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
        if (!uiInitialized) {
            return;
        }
        try {
            boolean offered = depthDataQueue.offer(new DepthData(isBid, price, size));
            if (!offered) {
                // Queue full - handle as needed
            }
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

### 8.2 OnTrade - Volume Profile Panel

A volume profile add-on with POC (Point of Control) and Value Area calculations.

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
            showPOC.addActionListener(e -> volumeProfilePanel.setShowPOC(showPOC.isSelected()));
            JCheckBox showValueArea = new JCheckBox("Show Value Area", true);
            showValueArea.addActionListener(e -> volumeProfilePanel.setShowValueArea(showValueArea.isSelected()));

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
        ConcurrentSkipListMap<Double, Integer> newVolumeProfile = new ConcurrentSkipListMap<>(volumeProfileRef.get());
        newVolumeProfile.merge(price, size, Integer::sum);
        volumeProfileRef.set(newVolumeProfile);

        if (newVolumeProfile.isEmpty()) {
            Log.info("Volume Profile is empty.");
            return;
        }

        ValueArea va = calculateValueArea(newVolumeProfile, 0.7);

        pointOfControl = newVolumeProfile.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(pointOfControl);

        volumeProfilePanel.setPointOfControl(pointOfControl);

        SwingUtilities.invokeLater(() -> {
            volumeProfilePanel.updateVolumeProfile(new ConcurrentSkipListMap<>(newVolumeProfile));
            volumeProfilePanel.setValueAreaBounds(va.lowerBound, va.upperBound);
            volumeProfilePanel.setCurrentMarketPrice(currentPrice);
        });
    }

    private ValueArea calculateValueArea(ConcurrentSkipListMap<Double, Integer> volumeProfile, double threshold) {
        double totalVolume = volumeProfile.values().stream().mapToDouble(Integer::doubleValue).sum();
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

### 8.3 OrderBlockStrategy2 - Trading Strategy with Indicators

A complete trading strategy with custom settings panels and order placement.

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
public class OrderBlockStrategy2 implements BarDataListener, CustomModule, TimeListener, CustomSettingsPanelProvider {
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

        iconClass bearishIconHandler = new iconClass("/icons8-arrow-down-30.png", "/default-arrow-down.png");
        bearishIcon = bearishIconHandler.getIcon();
        iconClass bullishIconHandler = new iconClass("/icons8-arrow-up-30.png", "/default-arrow-up.png");
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
                if (previousBars.get(0).getClose() > previousBars.get(0).getOpen() && bar.getClose() < bar.getOpen()) {
                    Log.info(BEARISH_OB);
                    indicator.addIcon(previousBars.get(0).getHigh(), bearishIcon, iconCenterX, iconCenterY);
                } else if (previousBars.get(0).getClose() < previousBars.get(0).getOpen() && bar.getClose() > bar.getOpen()) {
                    Log.info(BULLISH_OB);
                    indicator.addIcon(previousBars.get(0).getLow(), bullishIcon, iconCenterX, iconCenterY);
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
        JSpinner candlesSpinner = new JSpinner(new SpinnerNumberModel(settings.requiredSequentialCandles, 1, 100, 1));
        candlesSpinner.addChangeListener(e -> {
            settings.requiredSequentialCandles = (Integer) candlesSpinner.getValue();
            api.setSettings(settings);
        });
        p1.add(candlesSpinner);

        StrategyPanel p2 = new StrategyPanel("Minimum Percentage Move");
        JSpinner percentMoveSpinner = new JSpinner(new SpinnerNumberModel(settings.minPercentMove, 0.0, 100.0, 0.1));
        percentMoveSpinner.addChangeListener(e -> {
            settings.minPercentMove = (Double) percentMoveSpinner.getValue();
            api.setSettings(settings);
        });
        p2.add(percentMoveSpinner);

        return new StrategyPanel[] { p1, p2 };
    }

    private void PlaceOrder() {
        Random random = new Random();
        boolean isBuy = random.nextBoolean();

        SimpleOrderSendParametersBuilder builder = new SimpleOrderSendParametersBuilder(alias, true, 1);
        builder.setStopLossOffset(10);
        builder.setTakeProfitOffset(10);
        builder.setDuration(OrderDuration.IOC);

        SimpleOrderSendParameters order = builder.build();
        api.sendOrder(order);
    }
}
```

---

## Related Documentation

- [Javadoc: Simplified API](javadoc/bookmap-simplified-api-documentation-2025-11-20/)
- [Javadoc: Core API](javadoc/bookmap-core-api/)
