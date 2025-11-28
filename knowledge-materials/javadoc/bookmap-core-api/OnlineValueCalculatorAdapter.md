---
source_file: OnlineValueCalculatorAdapter.html
package: velox.api.layer1.layers.strategies.interfaces
classes: OnlineValueCalculatorAdapter
methods: onUserMessage, onTimeSourceProvided, onIntervalWidth, onLeftTimeChanged, onIntervalsNumber, onRealTimeDataStart, From Layer1ApiDataAdapter, From Layer1ApiMboDataAdapter, From Layer1ApiTradingAdapter
extends: ** Layer1ApiDataAdapter, Layer1ApiDataListener, Layer1ApiMboDataAdapter, Layer1ApiMboDataListener, Layer1ApiTradingAdapter, Layer1ApiTradingListener, ** Layer1ApiMboDataAdapter, Layer1ApiDataAdapter, Layer1ApiTradingAdapter
---

# OnlineValueCalculatorAdapter

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Interface

**All Superinterfaces:** Layer1ApiDataAdapter, Layer1ApiDataListener, Layer1ApiMboDataAdapter, Layer1ApiMboDataListener, Layer1ApiTradingAdapter, Layer1ApiTradingListener

**Extends:** Layer1ApiMboDataAdapter, Layer1ApiDataAdapter, Layer1ApiTradingAdapter

## Description

Receives updates, can generate new value for given strategy in response.

## Methods

### onUserMessage

```java
default void onUserMessage(Object data)
```

### onTimeSourceProvided

```java
default void onTimeSourceProvided(LongSupplier timeSource)
```

**Guaranteed** to be called once after adapter is created and **before any other methods are called**  
You should use this method instead of the old way of `Layer1ApiAdminProvider.getCurrentTime()` to get current time from adapter for new indicators, as returned time may differ in some cases.

**Parameters:**
- `timeSource` - Provider of current time for adapter

### onIntervalWidth

```java
default void onIntervalWidth(long intervalWidth)
```

**Guaranteed** to be called once after adapter is created and `onTimeSourceProvided(LongSupplier)` and before any other methods are called

**Parameters:**
- `intervalWidth` - Nanoseconds in 1 pixel of chart

### onLeftTimeChanged

```java
default void onLeftTimeChanged(long leftTime)
```

**Guaranteed** to be called once after adapter is created and and `onTimeSourceProvided(LongSupplier)` and `onIntervalWidth(long)` and `onIntervalsNumber(int)` methods are called, **before any other methods are called**  
Subsequent calls indicate that chart's left pixel was changed.

**Parameters:**
- `leftTime` - Time of beginning of left-most chart pixel

### onIntervalsNumber

```java
default void onIntervalsNumber(int intervalsNumber)
```

**Guaranteed** to be called once after adapter is created and and `onTimeSourceProvided(LongSupplier)` and `onIntervalWidth(long)` method is called, **before any other methods are called**

**Parameters:**
- `intervalsNumber` - Number of pixel on screen for lifetime of this adapter (if that number changes, adapter will be recreated)

### onRealTimeDataStart

```java
default void onRealTimeDataStart()
```

Any data that came before this method call was historical data from last pixel  
There is no need to treat this data differently, but if this data triggers any invalidates, in some case this could result in continuous invalidations  
(if time is paused in replay and chart range is not changing, data received will always be the same

If you return `IndicatorFullValues` in response to this call, you should make sure it incorporates pixel with current time (obtained via provided timeSource)

## Inherited Methods

### From Layer1ApiDataAdapter

```java
void onDepth(String alias, boolean isBid, int price, int size)
void onMarketMode(String alias, MarketMode marketMode)
void onTrade(String alias, double price, int size, TradeInfo info)
```

### From Layer1ApiMboDataAdapter

```java
void onMboCancel(String alias, String orderId)
void onMboReplace(String alias, String orderId, int newPrice, int newSize)
void onMboSend(String alias, String orderId, boolean isBid, int price, int size)
```

### From Layer1ApiTradingAdapter

```java
void onBalance(BalanceInfo balanceInfo)
void onOrderExecuted(ExecutionInfo executionInfo)
void onOrderUpdated(OrderInfoUpdate orderInfoUpdate)
void onStatus(StatusInfo statusInfo)
```