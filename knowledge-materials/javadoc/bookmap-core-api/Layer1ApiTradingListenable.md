---
source_file: Layer1ApiTradingListenable.html
package: velox.api.layer1
classes: Layer1ApiTradingListenable
methods: addListener, removeListener
---

# Layer1ApiTradingListenable

**Package:** velox.api.layer1

**Type:** Interface

**All Known Subinterfaces:** Layer1ApiProvider, Layer1ApiTradingProvider, LayerApiListenable

**All Known Implementing Classes:** ExternalLiveBaseProvider, ExternalReaderBaseProvider, Layer1ApiBasicListenable, Layer1ApiDepthFreezer, Layer1ApiInjectorRelay, Layer1ApiRelay, Layer1ApiStrategiesEchoMessagesLayer, Layer1ApiUpstreamRelay

## Methods

### addListener

```java
void addListener(Layer1ApiTradingListener listener)
```

Add trading listener

**Parameters:**
- `listener` - Listener to add

### removeListener

```java
void removeListener(Layer1ApiTradingListener listener)
```

Remove trading listener

**Parameters:**
- `listener` - Listener to remove