---
source_file: TradeAggregator.html
package: velox.api.layer1.providers.data
classes: TradeAggregator
methods: TradeAggregator.TradeAggregatorConsumer, TradeAggregator, onTrade, onTrade, run
---

# TradeAggregator

**Package:** velox.api.layer1.providers.data

**Type:** Class

**Inheritance:** java.lang.Object → TradeAggregator

**All Implemented Interfaces:** Runnable

## Description

Provides logic for trade aggregation boolean flags calculation - one flag for each new trade with new ticket ID (provided by target system - time stamp, aggressor order ID or real trade ID) and second flag for completing previous 'execution chain' on new trade or by timer.

## Nested Classes

### TradeAggregator.TradeAggregatorConsumer

```java
static interface TradeAggregator.TradeAggregatorConsumer
```

## Constructors

### TradeAggregator

```java
public TradeAggregator(TradeAggregator.TradeAggregatorConsumer callback)
```

Creates new instance of `TradeAggregator` object.

**Parameters:**
- `callback` - Callback method for reporting trades upstream.

## Methods

### onTrade

```java
void onTrade(double price, String aTicketId, boolean isBidAggressor, boolean isOtc, int size)
```

Handles trades aggregation logic and forwards the trade, do not call `Layer1ApiDataListener.onTrade(String, double, int, TradeInfo)` after you call this method.

### onTrade

```java
void onTrade(double price, String aTicketId, boolean isBidAggressor, boolean isOtc, int size, String aggressorOrderId, String passiveOrderId)
```

Handles trades aggregation logic and forwards the trade, do not call `Layer1ApiDataListener.onTrade(String, double, int, TradeInfo)` after you call this method.

### run

```java
void run()
```

**Specified by:** `run` in interface `Runnable`