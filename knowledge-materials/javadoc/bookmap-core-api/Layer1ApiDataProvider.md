---
source_file: Layer1ApiDataProvider.html
package: velox.api.layer1
classes: Layer1ApiDataProvider
methods: formatPrice
extends: ** Layer1ApiDataListenable
---

# Layer1ApiDataProvider

**Package:** velox.api.layer1

**Type:** Interface

**All Superinterfaces:** Layer1ApiDataListenable

**All Known Subinterfaces:** Layer1ApiProvider

**All Known Implementing Classes:** 
- velox.api.layer0.live.ExternalLiveBaseProvider
- velox.api.layer0.replay.ExternalReaderBaseProvider
- velox.api.layer1.layers.Layer1ApiDepthFreezer
- velox.api.layer1.layers.Layer1ApiInjectorRelay
- velox.api.layer1.layers.Layer1ApiRelay
- velox.api.layer1.layers.Layer1ApiStrategiesEchoMessagesLayer

## Description

Class implementing this should provide trade/depth data to subscribers

## Methods

### formatPrice

```java
@Nullable String formatPrice(String alias, double price)
```

Format price using platform capabilities if possible

**Parameters:**
- `alias` - 
- `price` - 

**Returns:** 

**Throws:**
- `InstrumentNotDefinedException` - If instrument was not subscribed to, so pips can not be found

## Inherited Methods from Layer1ApiDataListenable

```java
void addListener(Layer1ApiDataListener listener)
```

```java
void removeListener(Layer1ApiDataListener listener)
```