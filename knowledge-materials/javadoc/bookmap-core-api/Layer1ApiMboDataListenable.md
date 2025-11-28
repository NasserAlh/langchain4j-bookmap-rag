---
source_file: Layer1ApiMboDataListenable.html
package: velox.api.layer1
classes: Layer1ApiMboDataListenable
methods: addListener, removeListener
---

# Layer1ApiMboDataListenable

**Package:** velox.api.layer1

**Type:** Interface

**All Known Subinterfaces:** Layer1ApiMboDataProvider, Layer1ApiProvider, LayerApiListenable

**All Known Implementing Classes:** ExternalLiveBaseProvider, ExternalReaderBaseProvider, Layer1ApiBasicListenable, Layer1ApiDepthFreezer, Layer1ApiInjectorRelay, Layer1ApiRelay, Layer1ApiStrategiesEchoMessagesLayer, Layer1ApiUpstreamRelay

## Methods

### addListener

```java
void addListener(Layer1ApiMboDataListener listener)
```

Add mbo data listener

**Parameters:**
- `listener` - Listener to add

### removeListener

```java
void removeListener(Layer1ApiMboDataListener listener)
```

Remove mbo data listener

**Parameters:**
- `listener` - Listener to remove