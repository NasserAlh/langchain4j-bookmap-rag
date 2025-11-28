---
source_file: Layer1ApiAdapter.html
package: velox.api.layer1
classes: Layer1ApiAdapter
methods: Methods inherited from Layer1ApiAdminAdapter, Methods inherited from Layer1ApiDataAdapter, Methods inherited from Layer1ApiInstrumentAdapter, Methods inherited from Layer1ApiMboDataAdapter, Methods inherited from Layer1ApiTradingAdapter
extends: ** Layer1ApiAdminAdapter, Layer1ApiAdminListener, Layer1ApiDataAdapter, Layer1ApiDataListener, Layer1ApiInstrumentAdapter, Layer1ApiInstrumentListener, Layer1ApiListener, Layer1ApiMboDataAdapter, Layer1ApiMboDataListener, Layer1ApiTradingAdapter, Layer1ApiTradingListener, ** Layer1ApiInstrumentAdapter, Layer1ApiDataAdapter, Layer1ApiMboDataAdapter, Layer1ApiTradingAdapter, Layer1ApiAdminAdapter, Layer1ApiListener
---

# Layer1ApiAdapter

**Package:** velox.api.layer1

**Type:** Interface

**All Superinterfaces:** Layer1ApiAdminAdapter, Layer1ApiAdminListener, Layer1ApiDataAdapter, Layer1ApiDataListener, Layer1ApiInstrumentAdapter, Layer1ApiInstrumentListener, Layer1ApiListener, Layer1ApiMboDataAdapter, Layer1ApiMboDataListener, Layer1ApiTradingAdapter, Layer1ApiTradingListener

**Extends:** Layer1ApiInstrumentAdapter, Layer1ApiDataAdapter, Layer1ApiMboDataAdapter, Layer1ApiTradingAdapter, Layer1ApiAdminAdapter, Layer1ApiListener

## Description

Extends all possible Layer1 listeners. Capable of listening to any Layer1 API events. Provides default empty implementations.

## Methods

### Methods inherited from Layer1ApiAdminAdapter

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
void onSystemTextMessage(String message, SystemTextMessageType messageType)
```

```java
void onUserMessage(Object message)
```

### Methods inherited from Layer1ApiDataAdapter

```java
void onDepth(String alias, boolean isBid, int price, int size)
```

```java
void onMarketMode(String alias, MarketMode mode)
```

```java
void onTrade(String alias, double price, int size, TradeInfo tradeInfo)
```

### Methods inherited from Layer1ApiInstrumentAdapter

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

### Methods inherited from Layer1ApiMboDataAdapter

```java
void onMboCancel(String alias, String orderId)
```

```java
void onMboReplace(String alias, String orderId, int newPrice, int newSize)
```

```java
void onMboSend(String alias, String orderId, boolean isBid, int price, int size)
```

### Methods inherited from Layer1ApiTradingAdapter

```java
void onBalance(BalanceInfo balanceInfo)
```

```java
void onOrderExecuted(ExecutionInfo executionInfo)
```

```java
void onOrderUpdated(OrderInfoUpdate orderUpdate)
```

```java
void onStatus(StatusInfo statusInfo)
```