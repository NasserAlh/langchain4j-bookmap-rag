---
source_file: Layer1ApiDataInterfaceRequestMessage.html
package: velox.api.layer1.messages.indicators
classes: Layer1ApiDataInterfaceRequestMessage
methods: consumer, Layer1ApiDataInterfaceRequestMessage
---

# Layer1ApiDataInterfaceRequestMessage

**Package:** velox.api.layer1.messages.indicators

**Type:** Class

**Inheritance:** java.lang.Object → Layer1ApiDataInterfaceRequestMessage

**All Implemented Interfaces:** Layer1ApiStrategiesEchoMessagesLayer.StrategyEchoMessageFromLayer

## Description

Message to request interface to interact with data structure

You might want to invalidate current indicators once you receive your `Layer1ApiDataInterfaceRequestMessage`

## Fields

### consumer

```java
public final Consumer<DataStructureInterface> consumer
```

## Constructors

### Layer1ApiDataInterfaceRequestMessage

```java
public Layer1ApiDataInterfaceRequestMessage(Consumer<DataStructureInterface> consumer)
```

**Parameters:**
- `consumer` - Interface will be returned via this consumer