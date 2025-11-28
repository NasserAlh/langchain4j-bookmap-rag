---
source_file: MarketByOrderDepthDataListener.html
package: velox.api.layer1.simplified
classes: MarketByOrderDepthDataListener
methods: send, replace, cancel
---

# MarketByOrderDepthDataListener

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Known Subinterfaces:**
- `AllDataModule`

**All Known Implementing Classes:**
- `MarketByOrderDepthDataAdapter`

## Description

Get market depth data order by order (if supported by the data provider)

## Methods

### send

```java
void send(String orderId, boolean isBid, int price, int size)
```

Called when a new order was accepted by the exchange

**Parameters:**
- `orderId` - Unique order id
- `isBid` - True if update describes a buy order
- `price` - Limit price of the order in units of price levels
- `size` - The size of the order

### replace

```java
void replace(String orderId, int price, int size)
```

Called when an existing order was modified

**Parameters:**
- `orderId` - Unique order id
- `price` - New limit price (can be the same as previous)
- `size` - New order size (can be the same as previous)

### cancel

```java
void cancel(String orderId)
```

Called when an existing order was deleted

**Parameters:**
- `orderId` - Unique order id