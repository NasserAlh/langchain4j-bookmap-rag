---
source_file: guides/DataListeners.md
type: guide
topic: data-listeners, market-data, timestamps, depth, trades, bars, mbo
package: velox.api.layer1.simplified
related_interfaces: TimeListener, DepthDataListener, TradeDataListener, BarDataListener, MarketByOrderDepthDataListener
related_classes: TradeInfo, OrderBook, Bar
---

# Data Listeners

This guide covers the various listener interfaces available for receiving market data in Bookmap add-ons.

## TimeListener

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

## DepthDataListener (MBP Data)

Implement `DepthDataListener` to receive Market By Price (MBP) depth updates.

### Method Signature

```java
void onDepth(boolean isBid, int price, int size)
```

### Parameters

| Parameter | Description |
|-----------|-------------|
| `isBid` | `true` for bid side, `false` for ask side |
| `price` | Price level (as level number) |
| `size` | New size (0 if level removed) |

### Example: Building an Order Book

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

## TradeDataListener

Implement `TradeDataListener` to receive trade events.

### Method Signature

```java
void onTrade(double price, int size, TradeInfo tradeInfo)
```

### Parameters

| Parameter | Description |
|-----------|-------------|
| `price` | Trade price |
| `size` | Trade size |
| `tradeInfo` | Additional trade information (includes `isBidAggressor`) |

### Example: VWAP Calculation

Helper class for VWAP:

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

Complete implementation:

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

## BarDataListener

Implement `BarDataListener` to receive OHLC bar data at fixed intervals.

### Methods

```java
void onBar(OrderBook orderBook, Bar bar)
long getInterval()  // From IntervalListener - defines bar interval
```

### Parameters

| Parameter | Description |
|-----------|-------------|
| `orderBook` | Order book snapshot when bar completes |
| `bar` | Bar data (OHLC values) |

### Common Intervals

Use constants from `Intervals` class:
- `Intervals.INTERVAL_1_MINUTE`
- `Intervals.INTERVAL_2_MINUTES`
- `Intervals.INTERVAL_5_MINUTES`
- etc.

## MarketByOrderDepthDataListener (MBO Data)

MBO (Market By Order) data provides order-by-order information from CME (available via Rithmic since 2017).

### Methods

```java
void send(String orderId, boolean isBid, int price, int size)
void replace(String orderId, int price, int size)
void cancel(String orderId)
```

### Example

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

## See Also

- [Order Book Management](OrderBookManagement.md) - Building and managing order books
- [Historical Data](HistoricalData.md) - Handling historical vs real-time data
- [Javadoc: DepthDataListener](../javadoc/bookmap-simplified-api-documentation-2025-11-20/DepthDataListener.md)
- [Javadoc: TradeDataListener](../javadoc/bookmap-simplified-api-documentation-2025-11-20/TradeDataListener.md)
