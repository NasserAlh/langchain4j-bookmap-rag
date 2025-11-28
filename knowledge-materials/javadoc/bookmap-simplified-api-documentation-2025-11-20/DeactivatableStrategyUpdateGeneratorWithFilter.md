---
source_file: DeactivatableStrategyUpdateGeneratorWithFilter.html
package: velox.api.layer1.simplified
classes: DeactivatableStrategyUpdateGeneratorWithFilter
methods: DeactivatableStrategyUpdateGeneratorWithFilter, setGeneratedEventsConsumer, getGeneratedEventsConsumer, onStatus, onOrderUpdated, onOrderExecuted, onBalance, onTrade, onMarketMode, onDepth, onMboSend, onMboReplace, onMboCancel, onInstrumentAdded, onInstrumentRemoved, onInstrumentNotFound, onInstrumentAlreadySubscribed, onUserMessage, setTime, getGeneratorUpdateTypes
total_methods: 24
---

# DeactivatableStrategyUpdateGeneratorWithFilter

**Package:** velox.api.layer1.simplified

**Type:** Class

**Inheritance:** java.lang.Object → DeactivatableStrategyUpdateGeneratorWithFilter

**All Implemented Interfaces:** velox.api.layer1.Layer1ApiDataAdapter, velox.api.layer1.Layer1ApiDataListener, velox.api.layer1.Layer1ApiInstrumentAdapter, velox.api.layer1.Layer1ApiInstrumentListener, velox.api.layer1.Layer1ApiMboDataAdapter, velox.api.layer1.Layer1ApiMboDataListener, velox.api.layer1.Layer1ApiTradingAdapter, velox.api.layer1.Layer1ApiTradingListener, velox.api.layer1.messages.indicators.GeneratedUpdateConsumer, velox.api.layer1.messages.indicators.StrategyUpdateGenerator, velox.api.layer1.messages.indicators.StrategyUpdateGeneratorFilter, velox.api.layer1.messages.indicators.StrategyUpdateGeneratorSkipper

## Nested Classes

**Inherited from velox.api.layer1.messages.indicators.StrategyUpdateGeneratorFilter:**
- `velox.api.layer1.messages.indicators.StrategyUpdateGeneratorFilter.StrategyUpdateGeneratorEventType`

## Constructors

### DeactivatableStrategyUpdateGeneratorWithFilter

```java
public DeactivatableStrategyUpdateGeneratorWithFilter(SimplifiedL1ApiLoader simplifiedL1ApiLoader, String targetAlias, InstanceWrapper listener)
```

## Methods

### setGeneratedEventsConsumer

```java
public void setGeneratedEventsConsumer(Consumer<velox.api.layer1.layers.strategies.interfaces.CustomGeneratedEventAliased> consumer)
```

**Specified by:** `setGeneratedEventsConsumer` in interface `velox.api.layer1.messages.indicators.GeneratedUpdateConsumer`

### getGeneratedEventsConsumer

```java
public Consumer<velox.api.layer1.layers.strategies.interfaces.CustomGeneratedEventAliased> getGeneratedEventsConsumer()
```

**Specified by:** `getGeneratedEventsConsumer` in interface `velox.api.layer1.messages.indicators.GeneratedUpdateConsumer`

### onStatus

```java
public void onStatus(velox.api.layer1.data.StatusInfo statusInfo)
```

**Specified by:** `onStatus` in interface `velox.api.layer1.Layer1ApiTradingAdapter`
**Specified by:** `onStatus` in interface `velox.api.layer1.Layer1ApiTradingListener`

### onOrderUpdated

```java
public void onOrderUpdated(velox.api.layer1.data.OrderInfoUpdate orderInfoUpdate)
```

**Specified by:** `onOrderUpdated` in interface `velox.api.layer1.Layer1ApiTradingAdapter`
**Specified by:** `onOrderUpdated` in interface `velox.api.layer1.Layer1ApiTradingListener`

### onOrderExecuted

```java
public void onOrderExecuted(velox.api.layer1.data.ExecutionInfo executionInfo)
```

**Specified by:** `onOrderExecuted` in interface `velox.api.layer1.Layer1ApiTradingAdapter`
**Specified by:** `onOrderExecuted` in interface `velox.api.layer1.Layer1ApiTradingListener`

### onBalance

```java
public void onBalance(velox.api.layer1.data.BalanceInfo balanceInfo)
```

**Specified by:** `onBalance` in interface `velox.api.layer1.Layer1ApiTradingAdapter`
**Specified by:** `onBalance` in interface `velox.api.layer1.Layer1ApiTradingListener`

### onTrade

```java
public void onTrade(String alias, double price, int size, velox.api.layer1.data.TradeInfo tradeInfo)
```

**Specified by:** `onTrade` in interface `velox.api.layer1.Layer1ApiDataAdapter`
**Specified by:** `onTrade` in interface `velox.api.layer1.Layer1ApiDataListener`

### onMarketMode

```java
public void onMarketMode(String alias, velox.api.layer1.data.MarketMode marketMode)
```

**Specified by:** `onMarketMode` in interface `velox.api.layer1.Layer1ApiDataAdapter`
**Specified by:** `onMarketMode` in interface `velox.api.layer1.Layer1ApiDataListener`

### onDepth

```java
public void onDepth(String alias, boolean isBid, int price, int size)
```

**Specified by:** `onDepth` in interface `velox.api.layer1.Layer1ApiDataAdapter`
**Specified by:** `onDepth` in interface `velox.api.layer1.Layer1ApiDataListener`

### onMboSend

```java
public void onMboSend(String alias, String orderId, boolean isBid, int price, int size)
```

**Specified by:** `onMboSend` in interface `velox.api.layer1.Layer1ApiMboDataAdapter`
**Specified by:** `onMboSend` in interface `velox.api.layer1.Layer1ApiMboDataListener`

### onMboReplace

```java
public void onMboReplace(String alias, String orderId, int price, int size)
```

**Specified by:** `onMboReplace` in interface `velox.api.layer1.Layer1ApiMboDataAdapter`
**Specified by:** `onMboReplace` in interface `velox.api.layer1.Layer1ApiMboDataListener`

### onMboCancel

```java
public void onMboCancel(String alias, String orderId)
```

**Specified by:** `onMboCancel` in interface `velox.api.layer1.Layer1ApiMboDataAdapter`
**Specified by:** `onMboCancel` in interface `velox.api.layer1.Layer1ApiMboDataListener`

### onInstrumentAdded

```java
public void onInstrumentAdded(String alias, velox.api.layer1.data.InstrumentInfo instrumentInfo)
```

**Specified by:** `onInstrumentAdded` in interface `velox.api.layer1.Layer1ApiInstrumentAdapter`
**Specified by:** `onInstrumentAdded` in interface `velox.api.layer1.Layer1ApiInstrumentListener`

### onInstrumentRemoved

```java
public void onInstrumentRemoved(String alias)
```

**Specified by:** `onInstrumentRemoved` in interface `velox.api.layer1.Layer1ApiInstrumentAdapter`
**Specified by:** `onInstrumentRemoved` in interface `velox.api.layer1.Layer1ApiInstrumentListener`

### onInstrumentNotFound

```java
public void onInstrumentNotFound(String symbol, String exchange, String type)
```

**Specified by:** `onInstrumentNotFound` in interface `velox.api.layer1.Layer1ApiInstrumentAdapter`
**Specified by:** `onInstrumentNotFound` in interface `velox.api.layer1.Layer1ApiInstrumentListener`

### onInstrumentAlreadySubscribed

```java
public void onInstrumentAlreadySubscribed(String symbol, String exchange, String type)
```

**Specified by:** `onInstrumentAlreadySubscribed` in interface `velox.api.layer1.Layer1ApiInstrumentAdapter`
**Specified by:** `onInstrumentAlreadySubscribed` in interface `velox.api.layer1.Layer1ApiInstrumentListener`

### onUserMessage

```java
public void onUserMessage(Object data)
```

**Specified by:** `onUserMessage` in interface `velox.api.layer1.messages.indicators.StrategyUpdateGenerator`

### setTime

```java
public void setTime(long time)
```

**Specified by:** `setTime` in interface `velox.api.layer1.messages.indicators.StrategyUpdateGenerator`

### getGeneratorUpdateTypes

```java
public Set<velox.api.layer1.messages.indicators.StrategyUpdateGeneratorFilter.StrategyUpdateGeneratorEventType> getGeneratorUpdateTypes()
```

**Specified by:** `getGeneratorUpdateTypes` in interface `velox.api.layer1.messages.indicators.StrategyUpdateGeneratorFilter`

### getGeneratorAliases

```java
public Set<String> getGeneratorAliases()
```

**Specified by:** `getGeneratorAliases` in interface `velox.api.layer1.messages.indicators.StrategyUpdateGeneratorFilter`

### deactivate

```java
public void deactivate()
```

### isActive

```java
public boolean isActive()
```

### skipToRealtimeRequested

```java
public boolean skipToRealtimeRequested()
```

**Specified by:** `skipToRealtimeRequested` in interface `velox.api.layer1.messages.indicators.StrategyUpdateGeneratorSkipper`

## Inherited Methods

**Methods inherited from class java.lang.Object:**
- `clone()`
- `equals(Object)`
- `finalize()`
- `getClass()`
- `hashCode()`
- `notify()`
- `notifyAll()`
- `toString()`
- `wait()`
- `wait(long)`
- `wait(long, int)`