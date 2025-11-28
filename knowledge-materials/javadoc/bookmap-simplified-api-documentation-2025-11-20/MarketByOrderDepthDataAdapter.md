---
source_file: MarketByOrderDepthDataAdapter.html
package: velox.api.layer1.simplified
classes: MarketByOrderDepthDataAdapter
methods: MarketByOrderDepthDataAdapter, send, replace, cancel
---

# MarketByOrderDepthDataAdapter

**Package:** velox.api.layer1.simplified

**Type:** Class

**Inheritance:** java.lang.Object → MarketByOrderDepthDataAdapter

**All Implemented Interfaces:** MarketByOrderDepthDataListener

## Description

An adapter for `MarketByOrderDepthDataListener` with empty default methods implementations.

## Constructors

### MarketByOrderDepthDataAdapter

```java
public MarketByOrderDepthDataAdapter()
```

## Methods

### send

```java
public void send(String orderId, boolean isBid, int price, int size)
```

Called when a new order was accepted by the exchange.

**Parameters:**
- `orderId` - Unique order id
- `isBid` - True if update describes a buy order
- `price` - Limit price of the order in units of price levels
- `size` - The size of the order

### replace

```java
public void replace(String orderId, int price, int size)
```

Called when an existing order was modified.

**Parameters:**
- `orderId` - Unique order id
- `price` - New limit price (can be the same as previous)
- `size` - New order size (can be the same as previous)

### cancel

```java
public void cancel(String orderId)
```

Called when an existing order was deleted.

**Parameters:**
- `orderId` - Unique order id