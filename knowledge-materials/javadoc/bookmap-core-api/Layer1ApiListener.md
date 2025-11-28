---
source_file: Layer1ApiListener.html
package: velox.api.layer1
classes: Layer1ApiListener
methods: Inherited from Layer1ApiAdminListener, Inherited from Layer1ApiDataListener, Inherited from Layer1ApiInstrumentListener, Inherited from Layer1ApiMboDataListener, Inherited from Layer1ApiTradingListener
extends: ** Layer1ApiAdminListener, Layer1ApiDataListener, Layer1ApiInstrumentListener, Layer1ApiMboDataListener, Layer1ApiTradingListener
---

# Layer1ApiListener

**Package:** velox.api.layer1

**Type:** Interface

**All Superinterfaces:** Layer1ApiAdminListener, Layer1ApiDataListener, Layer1ApiInstrumentListener, Layer1ApiMboDataListener, Layer1ApiTradingListener

**All Known Subinterfaces:** Layer1ApiAdapter

**All Known Implementing Classes:** Layer1ApiDepthFreezer, Layer1ApiInjectorRelay, Layer1ApiRelay, Layer1ApiStrategiesEchoMessagesLayer, Layer1ApiUpstreamRelay

## Description

Extends all possible Layer1 listeners. Capable of listening to any Layer1 API events.

## Methods

### Inherited from Layer1ApiAdminListener

```java
void onConnectionLost(DisconnectionReason reason, String text)
```

```java
void onConnectionRestored()
```

```java
void onLoginFailed(LoginFailedReason reason, String text)
```

```java
void onLoginSuccessful()
```

```java
void onSystemTextMessage(String message, SystemTextMessageType type)
```

```java
void onUserMessage(Object message)
```

### Inherited from Layer1ApiDataListener

```java
void onDepth(String alias, boolean isBid, int price, int size)
```

```java
void onMarketMode(String alias, MarketMode mode)
```

```java
void onTrade(String alias, double price, int size, TradeInfo tradeInfo)
```

### Inherited from Layer1ApiInstrumentListener

```java
void onInstrumentAdded(String alias, InstrumentInfo instrumentInfo)
```

```java
void onInstrumentAlreadySubscribed(String alias, String symbol, String exchange)
```

```java
void onInstrumentNotFound(String alias, String symbol, String exchange)
```

```java
void onInstrumentRemoved(String alias)
```

### Inherited from Layer1ApiMboDataListener

```java
void onMboCancel(String alias, String orderId)
```

```java
void onMboReplace(String alias, String orderId, int price, int size)
```

```java
void onMboSend(String alias, String orderId, boolean isBid, int price, int size)
```

### Inherited from Layer1ApiTradingListener

```java
void onBalance(BalanceInfo balanceInfo)
```

```java
void onOrderExecuted(ExecutionInfo executionInfo)
```

```java
void onOrderUpdated(OrderInfoUpdate orderInfoUpdate)
```

```java
void onStatus(StatusInfo statusInfo)
```