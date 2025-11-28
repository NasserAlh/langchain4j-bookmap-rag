---
source_file: Layer1ApiTradingProvider.html
package: velox.api.layer1
classes: Layer1ApiTradingProvider
methods: sendOrder, updateOrder
extends: ** Layer1ApiTradingListenable
---

# Layer1ApiTradingProvider

**Package:** velox.api.layer1

**Type:** Interface

**All Superinterfaces:** Layer1ApiTradingListenable

**All Known Subinterfaces:** Layer1ApiProvider

**All Known Implementing Classes:** 
- velox.api.layer0.live.ExternalLiveBaseProvider
- velox.api.layer0.replay.ExternalReaderBaseProvider  
- velox.api.layer1.layers.Layer1ApiDepthFreezer
- velox.api.layer1.layers.Layer1ApiInjectorRelay
- velox.api.layer1.layers.Layer1ApiRelay
- velox.api.layer1.layers.Layer1ApiStrategiesEchoMessagesLayer

## Description

Class implementing this should provide ability to work with orders

## Methods

### sendOrder

```java
void sendOrder(velox.api.layer1.data.OrderSendParameters orderSendParameters)
```

Submit order with specified parameters

**Parameters:**
- `orderSendParameters` - parameters

### updateOrder

```java
void updateOrder(velox.api.layer1.data.OrderUpdateParameters orderUpdateParameters)
```

Update order according to parameters

**Parameters:**
- `orderUpdateParameters` - parameters

## Inherited Methods from Layer1ApiTradingListenable

```java
void addListener(Layer1ApiTradingListener listener)
```

```java
void removeListener(Layer1ApiTradingListener listener)
```