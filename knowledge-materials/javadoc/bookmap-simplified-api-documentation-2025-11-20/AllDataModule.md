---
source_file: AllDataModule.html
package: velox.api.layer1.simplified
classes: AllDataModule
methods: Inherited from BalanceListener, Inherited from BarDataListener, Inherited from BboListener, Inherited from CustomModule, Inherited from DepthDataListener, Inherited from HistoricalModeListener, Inherited from IntervalListener, Inherited from MarketByOrderDepthDataListener, Inherited from MultiInstrumentListener, Inherited from OrdersListener, Inherited from PositionListener, Inherited from TimeListener, Inherited from TradeDataListener
extends: BalanceListener, BarDataListener, BboListener, CustomModule, DepthDataListener, HistoricalDataListener, HistoricalModeListener, IntervalListener, MarketByOrderDepthDataListener, MultiInstrumentListener, OrdersListener, PositionListener, TimeListener, TradeDataListener
---

# AllDataModule

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Superinterfaces:** BalanceListener, BarDataListener, BboListener, CustomModule, DepthDataListener, HistoricalDataListener, HistoricalModeListener, IntervalListener, MarketByOrderDepthDataListener, MultiInstrumentListener, OrdersListener, PositionListener, TimeListener, TradeDataListener

**Extends:** CustomModule, BalanceListener, BarDataListener, BboListener, DepthDataListener, MarketByOrderDepthDataListener, MultiInstrumentListener, OrdersListener, PositionListener, TimeListener, TradeDataListener, HistoricalModeListener

## Description

Interface implementing all simplified module interfaces including historical data. For those who just want all types of data. Helpful to get started, not recommended for use in production, unless you really need ALL the data (because otherwise you are getting data you don't need). Especially think if you need `HistoricalDataListener` and `HistoricalModeListener` - those come with significant added cost.

## Methods

### Inherited from BalanceListener

#### onBalance

```java
void onBalance(BalanceInfo balanceInfo)
```

### Inherited from BarDataListener

#### onBar

```java
void onBar(OrderBook orderBook, Bar bar)
```

#### onInterval

```java
void onInterval()
```

### Inherited from BboListener

#### onBbo

```java
void onBbo(int bidPrice, int bidSize, int askPrice, int askSize)
```

### Inherited from CustomModule

#### initialize

```java
void initialize(String alias, InstrumentInfo instrumentInfo, Api api, InitialState initialState)
```

#### stop

```java
void stop()
```

### Inherited from DepthDataListener

#### onDepth

```java
void onDepth(boolean isBid, int price, int size)
```

### Inherited from HistoricalModeListener

#### onRealtimeStart

```java
void onRealtimeStart()
```

### Inherited from IntervalListener

#### getInterval

```java
Interval getInterval()
```

### Inherited from MarketByOrderDepthDataListener

#### cancel

```java
void cancel(String orderId)
```

#### replace

```java
void replace(String orderId, int price, int size)
```

#### send

```java
void send(String orderId, boolean isBid, int price, int size)
```

### Inherited from MultiInstrumentListener

#### onCurrentInstrument

```java
void onCurrentInstrument(String alias)
```

#### onInstrumentAdded

```java
void onInstrumentAdded(InstrumentInfo instrumentInfo)
```

### Inherited from OrdersListener

#### onOrderExecuted

```java
void onOrderExecuted(ExecutionInfo executionInfo)
```

#### onOrderUpdated

```java
void onOrderUpdated(OrderInfoUpdate orderInfoUpdate)
```

### Inherited from PositionListener

#### onPositionUpdate

```java
void onPositionUpdate(StatusInfo statusInfo)
```

### Inherited from TimeListener

#### onTimestamp

```java
void onTimestamp(long timestamp)
```

### Inherited from TradeDataListener

#### onTrade

```java
void onTrade(double price, int size, TradeInfo tradeInfo)
```