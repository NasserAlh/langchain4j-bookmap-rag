---
source_file: Layer1ApiInstrumentListener.html
package: velox.api.layer1
classes: Layer1ApiInstrumentListener
methods: onInstrumentAdded, onInstrumentRemoved, onInstrumentNotFound, onInstrumentAlreadySubscribed
---

# Layer1ApiInstrumentListener

**Package:** velox.api.layer1

**Type:** Interface

**All Known Subinterfaces:** Layer1ApiAdapter, Layer1ApiInstrumentAdapter, Layer1ApiListener, velox.api.layer1.messages.indicators.StrategyUpdateGenerator

**All Known Implementing Classes:** velox.api.layer1.layers.Layer1ApiDepthFreezer, velox.api.layer1.layers.Layer1ApiInjectorRelay, velox.api.layer1.layers.Layer1ApiRelay, velox.api.layer1.layers.Layer1ApiStrategiesEchoMessagesLayer, velox.api.layer1.layers.Layer1ApiUpstreamRelay

## Description

Listener for instrument events

## Methods

### onInstrumentAdded

```java
void onInstrumentAdded(String alias, InstrumentInfo instrumentInfo)
```

Indicates successful subscription to the instrument

**Parameters:**
- `alias` - Instrument alias
- `instrumentInfo` - Instrument specifications

### onInstrumentRemoved

```java
void onInstrumentRemoved(String alias)
```

Unsubscribed from instrument (user-initiated or provider initiated).

Please note that if you use this in **strategy that implements `OnlineCalculatable`**, you should not delete any data about instrument that is expected in `OnlineCalculatable.calculateValuesInRange(String, String, long, long, int, velox.api.layer1.layers.strategies.interfaces.CalculatedResultListener)` or `OnlineCalculatable.createOnlineValueCalculator(String, String, long, java.util.function.Consumer, velox.api.layer1.layers.strategies.interfaces.InvalidateInterface)`, or should handle absence of that data (in case you delete it) gracefully. The reason is that you can get `onInstrumentRemoved(String)` in the middle of `OnlineCalculatable` request

**Parameters:**
- `alias` - 

### onInstrumentNotFound

```java
void onInstrumentNotFound(String symbol, @Nullable String exchange, String type)
```

Indicates that attempt to subscribe failed because instrument does not exist

**Parameters:**
- `symbol` - 
- `exchange` - 
- `type` - See `InstrumentCoreInfo.type`

### onInstrumentAlreadySubscribed

```java
void onInstrumentAlreadySubscribed(String symbol, @Nullable String exchange, String type)
```

Indicates that attempt to subscribe failed because subscription is already active

**Parameters:**
- `symbol` - 
- `exchange` - 
- `type` - See `InstrumentCoreInfo.type`