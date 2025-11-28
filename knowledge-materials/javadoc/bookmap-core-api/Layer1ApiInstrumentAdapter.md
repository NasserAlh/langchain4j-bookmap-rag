---
source_file: Layer1ApiInstrumentAdapter.html
package: velox.api.layer1
classes: Layer1ApiInstrumentAdapter
methods: onInstrumentAdded, onInstrumentRemoved, onInstrumentNotFound, onInstrumentAlreadySubscribed
extends: ** Layer1ApiInstrumentListener
---

# Layer1ApiInstrumentAdapter

**Package:** velox.api.layer1

**Type:** Interface

**All Superinterfaces:** Layer1ApiInstrumentListener

**All Known Subinterfaces:** Layer1ApiAdapter, velox.api.layer1.messages.indicators.StrategyUpdateGenerator

## Description

Provides default empty implementations.

## Methods

### onInstrumentAdded

```java
default void onInstrumentAdded(String alias, InstrumentInfo instrumentInfo)
```

Indicates successful subscription to the instrument

**Parameters:**
- `alias` - Instrument alias
- `instrumentInfo` - Instrument specifications

### onInstrumentRemoved

```java
default void onInstrumentRemoved(String alias)
```

Unsubscribed from instrument (user-initiated or provider initiated).

Please note that if you use this in **strategy that implements `OnlineCalculatable`**, you should not delete any data about instrument that is expected in `OnlineCalculatable.calculateValuesInRange(String, String, long, long, int, velox.api.layer1.layers.strategies.interfaces.CalculatedResultListener)` or `OnlineCalculatable.createOnlineValueCalculator(String, String, long, java.util.function.Consumer, velox.api.layer1.layers.strategies.interfaces.InvalidateInterface)`, or should handle absence of that data (in case you delete it) gracefully. The reason is that you can get `Layer1ApiInstrumentListener.onInstrumentRemoved(String)` in the middle of `OnlineCalculatable` request

**Parameters:**
- `alias` - 

### onInstrumentNotFound

```java
default void onInstrumentNotFound(String symbol, String exchange, String type)
```

Indicates that attempt to subscribe failed because instrument does not exist

**Parameters:**
- `symbol` - 
- `exchange` - 
- `type` - See `InstrumentCoreInfo.type`

### onInstrumentAlreadySubscribed

```java
default void onInstrumentAlreadySubscribed(String symbol, String exchange, String type)
```

Indicates that attempt to subscribe failed because subscription is already active

**Parameters:**
- `symbol` - 
- `exchange` - 
- `type` - See `InstrumentCoreInfo.type`