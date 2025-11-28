---
source_file: Layer1ApiDataListenable.html
package: velox.api.layer1
classes: Layer1ApiDataListenable
methods: addListener, removeListener
---

# Layer1ApiDataListenable

**Package:** velox.api.layer1

**Type:** Interface

**All Known Subinterfaces:** Layer1ApiDataProvider, Layer1ApiProvider, LayerApiListenable

**All Known Implementing Classes:** ExternalLiveBaseProvider, ExternalReaderBaseProvider, Layer1ApiBasicListenable, Layer1ApiDepthFreezer, Layer1ApiInjectorRelay, Layer1ApiRelay, Layer1ApiStrategiesEchoMessagesLayer, Layer1ApiUpstreamRelay

## Methods

### addListener

```java
void addListener(Layer1ApiDataListener listener)
```

Add data listener

**Parameters:**
- `listener` - Listener to add

### removeListener

```java
void removeListener(Layer1ApiDataListener listener)
```

Remove data listener

**Parameters:**
- `listener` - Listener to remove