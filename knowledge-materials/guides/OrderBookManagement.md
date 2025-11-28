---
source_file: guides/OrderBookManagement.md
type: guide
topic: order-book, mbp, mbo, depth, price-levels
package: velox.api.layer1.simplified
related_interfaces: DepthDataListener, MarketByOrderDepthDataListener
annotations: NoAutosubscription
---

# Order Book Management

This guide covers building and managing order books for both MBP (Market By Price) and MBO (Market By Order) data.

## MBP Order Book

The Order Book consists of `bids` (descending order) and `asks` (ascending order) collections.

### Data Structure

```java
// Bids sorted in descending order (highest price first)
private final TreeMap<Integer, Integer> bids = new TreeMap<>(Comparator.reverseOrder());

// Asks sorted in ascending order (lowest price first)
private final TreeMap<Integer, Integer> asks = new TreeMap<>();
```

### Getting Best Price and Size

```java
private void demoBestPriceSize() {
    int bestBidPrice = bids.firstKey();
    int bestAskPrice = asks.firstKey();
    int bestBidSize = bids.firstEntry().getValue();
    int bestAskSize = asks.firstEntry().getValue();
}
```

### Summing Price Levels

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

## MBO Order Book

Custom MBO Order Book implementation with order-level granularity:

### Order Class

```java
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
```

### Complete MBO Order Book Implementation

```java
public class OrderBook implements MarketByOrderDepthDataListener {

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
            // Size reduction at same price - maintain queue position
            order.size = size;
        } else {
            // Price change or size increase - loses queue position
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

### Getting Total Size at Price Level

```java
public int getTotalSizeAtPrice(boolean isBid, int price) {
    return (isBid ? bids : asks).get(price).values().stream()
        .mapToInt(order -> order.size)
        .sum();
}
```

## Direct Subscription with @NoAutosubscription

When you need manual control over data subscription:

```java
@NoAutosubscription
@Layer1SimpleAttachable
@Layer1StrategyName("Market Data Listener")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
public class MarketDataListener implements CustomModuleAdapter {

    private final OrderBook orderBook = new OrderBook();

    @Override
    public void initialize(String alias, InstrumentInfo info, Api api, InitialState initialState) {
        // Manually subscribe to MBO data
        api.addMarketByOrderDepthDataListeners(orderBook);
    }
}
```

## Key Differences: MBP vs MBO

| Feature | MBP (Market By Price) | MBO (Market By Order) |
|---------|----------------------|----------------------|
| Granularity | Aggregated size per price | Individual orders |
| Data Source | All exchanges | CME via Rithmic |
| Use Case | General depth analysis | Queue position, order flow |
| Complexity | Simpler | More complex |

## See Also

- [Data Listeners](DataListeners.md) - Receiving market data
- [NasserDom Example](../examples/NasserDom.md) - Complete DOM implementation
- [Javadoc: MarketByOrderDepthDataListener](../javadoc/bookmap-simplified-api-documentation-2025-11-20/MarketByOrderDepthDataListener.md)
