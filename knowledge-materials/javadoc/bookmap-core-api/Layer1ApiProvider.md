---
source_file: Layer1ApiProvider.html
package: velox.api.layer1
classes: Layer1ApiProvider
methods: Inherited from Layer1ApiAdminListenable, Inherited from Layer1ApiAdminProvider, Inherited from Layer1ApiDataListenable, Inherited from Layer1ApiDataProvider, Inherited from Layer1ApiInstrumentListenable, Inherited from Layer1ApiInstrumentProvider, Inherited from Layer1ApiMboDataListenable, Inherited from Layer1ApiTradingListenable, Inherited from Layer1ApiTradingProvider
extends: ** java.lang.AutoCloseable, Layer1ApiAdminListenable, Layer1ApiAdminProvider, Layer1ApiDataListenable, Layer1ApiDataProvider, Layer1ApiInstrumentListenable, Layer1ApiInstrumentProvider, Layer1ApiMboDataListenable, Layer1ApiTradingListenable, Layer1ApiTradingProvider, LayerApiListenable, ** Layer1ApiInstrumentProvider, Layer1ApiDataProvider, Layer1ApiTradingProvider, Layer1ApiAdminProvider, LayerApiListenable
---

# Layer1ApiProvider

**Package:** velox.api.layer1

**Type:** Interface

**All Superinterfaces:** java.lang.AutoCloseable, Layer1ApiAdminListenable, Layer1ApiAdminProvider, Layer1ApiDataListenable, Layer1ApiDataProvider, Layer1ApiInstrumentListenable, Layer1ApiInstrumentProvider, Layer1ApiMboDataListenable, Layer1ApiTradingListenable, Layer1ApiTradingProvider, LayerApiListenable

**All Known Implementing Classes:** velox.api.layer0.live.ExternalLiveBaseProvider, velox.api.layer0.replay.ExternalReaderBaseProvider, velox.api.layer1.layers.Layer1ApiDepthFreezer, velox.api.layer1.layers.Layer1ApiInjectorRelay, velox.api.layer1.layers.Layer1ApiRelay, velox.api.layer1.layers.Layer1ApiStrategiesEchoMessagesLayer

**Extends:** Layer1ApiInstrumentProvider, Layer1ApiDataProvider, Layer1ApiTradingProvider, Layer1ApiAdminProvider, LayerApiListenable

## Description

Extends all possible Layer1 provider aspect interfaces. This is what layer1 provider should implement.

## Methods

### Inherited from Layer1ApiAdminListenable

```java
void addListener(Layer1ApiAdminListener listener)
```

```java
void removeListener(Layer1ApiAdminListener listener)
```

### Inherited from Layer1ApiAdminProvider

```java
void close()
```

```java
long getCurrentTime()
```

```java
String getSource()
```

```java
Set<String> getSupportedFeatures()
```

```java
void login(LoginData loginData)
```

```java
void sendUserMessage(Object message)
```

### Inherited from Layer1ApiDataListenable

```java
void addListener(Layer1ApiDataListener listener)
```

```java
void removeListener(Layer1ApiDataListener listener)
```

### Inherited from Layer1ApiDataProvider

```java
String formatPrice(String alias, double price)
```

### Inherited from Layer1ApiInstrumentListenable

```java
void addListener(Layer1ApiInstrumentListener listener)
```

```java
void removeListener(Layer1ApiInstrumentListener listener)
```

### Inherited from Layer1ApiInstrumentProvider

```java
void subscribe(String alias, String symbol, String exchange)
```

```java
void subscribe(SubscribeInfo subscribeInfo)
```

```java
void unsubscribe(String alias)
```

### Inherited from Layer1ApiMboDataListenable

```java
void addListener(Layer1ApiMboDataListener listener)
```

```java
void removeListener(Layer1ApiMboDataListener listener)
```

### Inherited from Layer1ApiTradingListenable

```java
void addListener(Layer1ApiTradingListener listener)
```

```java
void removeListener(Layer1ApiTradingListener listener)
```

### Inherited from Layer1ApiTradingProvider

```java
void sendOrder(OrderSendParameters orderSendParameters)
```

```java
void updateOrder(OrderUpdateParameters orderUpdateParameters)
```