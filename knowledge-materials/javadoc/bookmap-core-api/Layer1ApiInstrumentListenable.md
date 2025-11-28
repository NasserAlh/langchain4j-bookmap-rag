---
source_file: Layer1ApiInstrumentListenable.html
package: velox.api.layer1
classes: Layer1ApiInstrumentListenable
methods: addListener, removeListener
---

# Layer1ApiInstrumentListenable

**Package:** velox.api.layer1

**Type:** Interface

**All Known Subinterfaces:** Layer1ApiInstrumentProvider, Layer1ApiProvider, LayerApiListenable

**All Known Implementing Classes:** ExternalLiveBaseProvider, ExternalReaderBaseProvider, Layer1ApiBasicListenable, Layer1ApiDepthFreezer, Layer1ApiInjectorRelay, Layer1ApiRelay, Layer1ApiStrategiesEchoMessagesLayer, Layer1ApiUpstreamRelay

## Methods

### addListener

```java
void addListener(Layer1ApiInstrumentListener listener)
```

Add instrument events listener

**Parameters:**
- `listener` - Listener to add

### removeListener

```java
void removeListener(Layer1ApiInstrumentListener listener)
```

Remove instrument events listener

**Parameters:**
- `listener` - Listener to remove