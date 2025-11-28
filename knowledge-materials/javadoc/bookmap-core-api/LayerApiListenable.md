---
source_file: LayerApiListenable.html
package: velox.api.layer1
classes: LayerApiListenable
methods: Methods inherited from Layer1ApiAdminListenable, Methods inherited from Layer1ApiDataListenable, Methods inherited from Layer1ApiInstrumentListenable, Methods inherited from Layer1ApiMboDataListenable, Methods inherited from Layer1ApiTradingListenable
extends: ** Layer1ApiAdminListenable, Layer1ApiDataListenable, Layer1ApiInstrumentListenable, Layer1ApiMboDataListenable, Layer1ApiTradingListenable
---

# LayerApiListenable

**Package:** velox.api.layer1

**Type:** Interface

**All Superinterfaces:** Layer1ApiAdminListenable, Layer1ApiDataListenable, Layer1ApiInstrumentListenable, Layer1ApiMboDataListenable, Layer1ApiTradingListenable

**All Known Subinterfaces:** Layer1ApiProvider

**All Known Implementing Classes:** ExternalLiveBaseProvider, ExternalReaderBaseProvider, Layer1ApiBasicListenable, Layer1ApiDepthFreezer, Layer1ApiInjectorRelay, Layer1ApiRelay, Layer1ApiStrategiesEchoMessagesLayer, Layer1ApiUpstreamRelay

## Inheritance

```java
public interface LayerApiListenable
extends Layer1ApiAdminListenable, Layer1ApiDataListenable, Layer1ApiMboDataListenable, Layer1ApiInstrumentListenable, Layer1ApiTradingListenable
```

## Methods

### Methods inherited from Layer1ApiAdminListenable

```java
void addListener(Layer1ApiAdminListener listener)
void removeListener(Layer1ApiAdminListener listener)
```

### Methods inherited from Layer1ApiDataListenable

```java
void addListener(Layer1ApiDataListener listener)
void removeListener(Layer1ApiDataListener listener)
```

### Methods inherited from Layer1ApiInstrumentListenable

```java
void addListener(Layer1ApiInstrumentListener listener)
void removeListener(Layer1ApiInstrumentListener listener)
```

### Methods inherited from Layer1ApiMboDataListenable

```java
void addListener(Layer1ApiMboDataListener listener)
void removeListener(Layer1ApiMboDataListener listener)
```

### Methods inherited from Layer1ApiTradingListenable

```java
void addListener(Layer1ApiTradingListener listener)
void removeListener(Layer1ApiTradingListener listener)
```