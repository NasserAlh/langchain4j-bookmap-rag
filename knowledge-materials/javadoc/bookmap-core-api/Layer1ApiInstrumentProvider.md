---
source_file: Layer1ApiInstrumentProvider.html
package: velox.api.layer1
classes: Layer1ApiInstrumentProvider
methods: subscribe, subscribe, unsubscribe
extends: ** Layer1ApiInstrumentListenable
---

# Layer1ApiInstrumentProvider

**Package:** velox.api.layer1

**Type:** Interface

**All Superinterfaces:** Layer1ApiInstrumentListenable

**All Known Subinterfaces:** Layer1ApiProvider

**All Known Implementing Classes:** 
- velox.api.layer0.live.ExternalLiveBaseProvider
- velox.api.layer0.replay.ExternalReaderBaseProvider
- velox.api.layer1.layers.Layer1ApiDepthFreezer
- velox.api.layer1.layers.Layer1ApiInjectorRelay
- velox.api.layer1.layers.Layer1ApiRelay
- velox.api.layer1.layers.Layer1ApiStrategiesEchoMessagesLayer

## Description

Class implementing this should provide ability to work with instruments.

## Methods

### subscribe

```java
void subscribe(SubscribeInfo subscribeInfo)
```

Subscribe to specified instrument. Some parameters can be null depending on the platform.

**Parameters:**
- `subscribeInfo` - Has fields similar to `InstrumentCoreInfo`

### subscribe

```java
@Deprecated
default void subscribe(String symbol, @Nullable String exchange, String type)
```

**Deprecated.** Default implementation forwards events to new method. Use `subscribe(SubscribeInfo)` instead

Subscribe to specified instrument. Some parameters can be null depending on the platform.

**Parameters:**
- `symbol` - 
- `exchange` - 
- `type` - See `InstrumentCoreInfo.type`

### unsubscribe

```java
void unsubscribe(String alias)
```

Unsubscribe from the instrument.

**Parameters:**
- `alias` - Instrument alias

## Inherited Methods

**From Layer1ApiInstrumentListenable:**
- `addListener(Layer1ApiInstrumentListener)`
- `removeListener(Layer1ApiInstrumentListener)`