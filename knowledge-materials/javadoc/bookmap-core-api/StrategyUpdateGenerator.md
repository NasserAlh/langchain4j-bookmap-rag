---
source_file: StrategyUpdateGenerator.html
package: velox.api.layer1.messages.indicators
classes: StrategyUpdateGenerator
methods: setTime, onUserMessage, From GeneratedUpdateConsumer, From Layer1ApiDataAdapter, From Layer1ApiInstrumentAdapter, From Layer1ApiMboDataAdapter, From Layer1ApiTradingAdapter
extends: ** GeneratedUpdateConsumer, Layer1ApiDataAdapter, Layer1ApiDataListener, Layer1ApiInstrumentAdapter, Layer1ApiInstrumentListener, Layer1ApiMboDataAdapter, Layer1ApiMboDataListener, Layer1ApiTradingAdapter, Layer1ApiTradingListener, ** Layer1ApiDataAdapter, Layer1ApiMboDataAdapter, Layer1ApiTradingAdapter, Layer1ApiInstrumentAdapter, GeneratedUpdateConsumer
---

# StrategyUpdateGenerator

**Package:** velox.api.layer1.messages.indicators

**Type:** Interface

**All Superinterfaces:** GeneratedUpdateConsumer, Layer1ApiDataAdapter, Layer1ApiDataListener, Layer1ApiInstrumentAdapter, Layer1ApiInstrumentListener, Layer1ApiMboDataAdapter, Layer1ApiMboDataListener, Layer1ApiTradingAdapter, Layer1ApiTradingListener

**Extends:** Layer1ApiDataAdapter, Layer1ApiMboDataAdapter, Layer1ApiTradingAdapter, Layer1ApiInstrumentAdapter, GeneratedUpdateConsumer

## Description

Can generate any number of custom events in response to depth/trade updates.

## Methods

### setTime

```java
void setTime(long time)
```

Following updates will have have this time.

**Parameters:**
- `time` - 

### onUserMessage

```java
void onUserMessage(Object data)
```

Similar to `Layer1ApiAdminListener.onUserMessage(Object)`
Currently only iceberg messages are passed here.

**Parameters:**
- `data` - 

## Inherited Methods

### From GeneratedUpdateConsumer

```java
Consumer<Object> getGeneratedEventsConsumer()
```

```java
void setGeneratedEventsConsumer(Consumer<Object> consumer)
```

### From Layer1ApiDataAdapter

```java
void onDepth(String alias, boolean isBid, int price, int size)
```

```java
void onMarketMode(String alias, MarketMode mode)
```

```java
void onTrade(String alias, double price, int size, TradeInfo tradeInfo)
```

### From Layer1ApiInstrumentAdapter

```java
void onInstrumentAdded(String alias, InstrumentInfo instrumentInfo)
```

```java
void onInstrumentAlreadySubscribed(String alias, String symbol, String exchange)
```

```java
void onInstrumentNotFound(String alias, String symbol, String exchange)
```

```java
void onInstrumentRemoved(String alias)
```

### From Layer1ApiMboDataAdapter

```java
void onMboCancel(String alias, String orderId)
```

```java
void onMboReplace(String alias, String orderId, int newPrice, int newSize)
```

```java
void onMboSend(String alias, String orderId, boolean isBid, int price, int size)
```

### From Layer1ApiTradingAdapter

```java
void onBalance(BalanceInfo balanceInfo)
```

```java
void onOrderExecuted(ExecutionInfo executionInfo)
```

```java
void onOrderUpdated(OrderInfoUpdate orderInfoUpdate)
```

```java
void onStatus(StatusInfo statusInfo)
```