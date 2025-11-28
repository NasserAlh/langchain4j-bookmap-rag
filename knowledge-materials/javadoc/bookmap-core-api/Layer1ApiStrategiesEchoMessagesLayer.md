---
source_file: Layer1ApiStrategiesEchoMessagesLayer.html
package: velox.api.layer1.layers
classes: Layer1ApiStrategiesEchoMessagesLayer
methods: Layer1ApiStrategiesEchoMessagesLayer.StrategyEchoMessageFromGui, Layer1ApiStrategiesEchoMessagesLayer.StrategyEchoMessageFromLayer, Layer1ApiStrategiesEchoMessagesLayer, sendUserMessage
---

# Layer1ApiStrategiesEchoMessagesLayer

**Package:** velox.api.layer1.layers

**Type:** Class

**Inheritance:** `java.lang.Object` → `velox.api.layer1.Layer1ApiBasicListenable` → `velox.api.layer1.layers.Layer1ApiUpstreamRelay` → `velox.api.layer1.layers.Layer1ApiRelay` → `velox.api.layer1.layers.Layer1ApiInjectorRelay` → `Layer1ApiStrategiesEchoMessagesLayer`

**All Implemented Interfaces:** `java.lang.AutoCloseable`, `velox.api.layer1.Layer1ApiAdminListenable`, `velox.api.layer1.Layer1ApiAdminListener`, `velox.api.layer1.Layer1ApiAdminProvider`, `velox.api.layer1.Layer1ApiDataListenable`, `velox.api.layer1.Layer1ApiDataListener`, `velox.api.layer1.Layer1ApiDataProvider`, `velox.api.layer1.Layer1ApiInstrumentListenable`, `velox.api.layer1.Layer1ApiInstrumentListener`, `velox.api.layer1.Layer1ApiInstrumentProvider`, `velox.api.layer1.Layer1ApiListener`, `velox.api.layer1.Layer1ApiMboDataListenable`, `velox.api.layer1.Layer1ApiMboDataListener`, `velox.api.layer1.Layer1ApiProvider`, `velox.api.layer1.Layer1ApiTradingListenable`, `velox.api.layer1.Layer1ApiTradingListener`, `velox.api.layer1.Layer1ApiTradingProvider`, `velox.api.layer1.LayerApiListenable`, `velox.api.layer1.layers.Layer1ApiTimeSource`

## Description

If message of type `Layer1ApiStrategiesEchoMessagesLayer.StrategyEchoMessage` is sent using `Layer1ApiAdminProvider.sendUserMessage(Object)`, `Layer1ApiStrategiesEchoMessagesLayer` will send it back with `Layer1ApiAdminListener.onUserMessage(Object)`. It will be done in ASYNCHRONOUS manner. It will still be in sync with data coming from provider, but sendUserMessage will likely return before the message reaches the target.

## Nested Classes

### Layer1ApiStrategiesEchoMessagesLayer.StrategyEchoMessageFromGui

```java
static interface StrategyEchoMessageFromGui
```

### Layer1ApiStrategiesEchoMessagesLayer.StrategyEchoMessageFromLayer

```java
static interface StrategyEchoMessageFromLayer
```

## Constructors

### Layer1ApiStrategiesEchoMessagesLayer

```java
public Layer1ApiStrategiesEchoMessagesLayer(Layer1ApiProvider provider)
```

## Methods

### sendUserMessage

```java
Object sendUserMessage(Object data)
```

Send a downstream event. Allows incorporating arbitrary functionality into protocol.

**Specified by:** `sendUserMessage` in interface `Layer1ApiAdminProvider`

**Overrides:** `sendUserMessage` in class `Layer1ApiRelay`

**Parameters:**
- `data` - Message content

**Returns:**