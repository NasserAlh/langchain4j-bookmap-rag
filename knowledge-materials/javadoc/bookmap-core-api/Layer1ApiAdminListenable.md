---
source_file: Layer1ApiAdminListenable.html
package: velox.api.layer1
classes: Layer1ApiAdminListenable
methods: addListener, removeListener
---

# Layer1ApiAdminListenable

**Package:** velox.api.layer1

**Type:** Interface

**All Known Subinterfaces:** Layer1ApiAdminProvider, Layer1ApiProvider, LayerApiListenable

**All Known Implementing Classes:** ExternalLiveBaseProvider, ExternalReaderBaseProvider, Layer1ApiBasicListenable, Layer1ApiDepthFreezer, Layer1ApiInjectorRelay, Layer1ApiRelay, Layer1ApiStrategiesEchoMessagesLayer, Layer1ApiUpstreamRelay

## Methods

### addListener

```java
void addListener(Layer1ApiAdminListener listener)
```

Add listener for general events

**Parameters:**
- `listener` - Listener to add

### removeListener

```java
void removeListener(Layer1ApiAdminListener listener)
```

Remove listener for general events

**Parameters:**
- `listener` - Listener to remove