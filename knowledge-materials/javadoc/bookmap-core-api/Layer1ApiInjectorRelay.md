---
source_file: Layer1ApiInjectorRelay.html
package: velox.api.layer1.layers
classes: Layer1ApiInjectorRelay
methods: upstreamInjectorLock, Layer1ApiInjectorRelay, inject, injectSynchronously, safeInject, close, onInstrumentAdded, onInstrumentRemoved, onInstrumentNotFound, onInstrumentAlreadySubscribed, onTrade, onDepth, onMboSend, onMboReplace, onMboCancel, onMarketMode, onOrderUpdated, onOrderExecuted, onStatus, onBalance
total_methods: 26
---

# Layer1ApiInjectorRelay

**Package:** velox.api.layer1.layers

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.Layer1ApiBasicListenable → velox.api.layer1.layers.Layer1ApiUpstreamRelay → velox.api.layer1.layers.Layer1ApiRelay → Layer1ApiInjectorRelay

**All Implemented Interfaces:** AutoCloseable, Layer1ApiAdminListenable, Layer1ApiAdminListener, Layer1ApiAdminProvider, Layer1ApiDataListenable, Layer1ApiDataListener, Layer1ApiDataProvider, Layer1ApiInstrumentListenable, Layer1ApiInstrumentListener, Layer1ApiInstrumentProvider, Layer1ApiListener, Layer1ApiMboDataListenable, Layer1ApiMboDataListener, Layer1ApiProvider, Layer1ApiTradingListenable, Layer1ApiTradingListener, Layer1ApiTradingProvider, LayerApiListenable, Layer1ApiTimeSource

**Direct Known Subclasses:** Layer1ApiStrategiesEchoMessagesLayer

## Description

This relay is also capable of injecting messages into provider → gui stream in asynchronous manner. Use it when you need to do on* call in response to some event.

## Fields

### upstreamInjectorLock

```java
protected final Object upstreamInjectorLock
```

## Constructors

### Layer1ApiInjectorRelay

```java
public Layer1ApiInjectorRelay(Layer1ApiProvider provider)
```

## Methods

### inject

```java
protected void inject(Runnable r)
```

Inject runnable into messages stream provider → gui. Event is handled asynchronously. If you need to inject event synchronously and/or from the same thread - take a look at `injectSynchronously(Runnable)`

### injectSynchronously

```java
protected void injectSynchronously(Runnable r)
```

Inject runnable into messages stream provider → gui from the same thread. Blocks until event is injected. Be careful when using this method, it's easy to cause a deadlock. If in doubt - use `inject(Runnable)` instead.

### safeInject

```java
@Deprecated
protected void safeInject(Runnable r)
```

**Deprecated.**

Inject runnable into messages stream provider → gui. If exception happens this method will log it, but won't crash the application (this was a behavior that appeared by accident). Use `inject(Runnable)` instead.

### close

```java
public void close()
```

Stop relaying data. Will not let send new events upwards, but is not guaranteed to stop ones already in progress.

**Specified by:** `close` in interface `AutoCloseable`

**Specified by:** `close` in interface `Layer1ApiAdminProvider`

**Overrides:** `close` in class `Layer1ApiRelay`

### onInstrumentAdded

```java
public void onInstrumentAdded(String alias, InstrumentInfo instrumentInfo)
```

Indicates successful subscription to the instrument

**Specified by:** `onInstrumentAdded` in interface `Layer1ApiInstrumentListener`

**Overrides:** `onInstrumentAdded` in class `Layer1ApiUpstreamRelay`

**Parameters:**
- `alias` - instrument alias
- `instrumentInfo` - instrument specifications

### onInstrumentRemoved

```java
public void onInstrumentRemoved(String alias)
```

Unsubscribed from instrument (user-initiated or provider initiated).  
Please note that if you use this in **strategy that implements `OnlineCalculatable`**, you should not delete any data about instrument that is expected in `OnlineCalculatable.calculateValuesInRange(String, String, long, long, int, velox.api.layer1.layers.strategies.interfaces.CalculatedResultListener)` or `OnlineCalculatable.createOnlineValueCalculator(String, String, long, java.util.function.Consumer, velox.api.layer1.layers.strategies.interfaces.InvalidateInterface)`, or should handle absence of that data (in case you delete it) gracefully. The reason is that you can get `Layer1ApiInstrumentListener.onInstrumentRemoved(String)` in the middle of `OnlineCalculatable` request

**Specified by:** `onInstrumentRemoved` in interface `Layer1ApiInstrumentListener`

**Overrides:** `onInstrumentRemoved` in class `Layer1ApiUpstreamRelay`

**Parameters:**
- `alias` - 

### onInstrumentNotFound

```java
public void onInstrumentNotFound(String symbol, String exchange, String type)
```

Indicates that attempt to subscribe failed because instrument does not exist

**Specified by:** `onInstrumentNotFound` in interface `Layer1ApiInstrumentListener`

**Overrides:** `onInstrumentNotFound` in class `Layer1ApiUpstreamRelay`

**Parameters:**
- `symbol` - 
- `exchange` - 
- `type` - see `InstrumentCoreInfo.type`

### onInstrumentAlreadySubscribed

```java
public void onInstrumentAlreadySubscribed(String symbol, String exchange, String type)
```

Indicates that attempt to subscribe failed because subscription is already active

**Specified by:** `onInstrumentAlreadySubscribed` in interface `Layer1ApiInstrumentListener`

**Overrides:** `onInstrumentAlreadySubscribed` in class `Layer1ApiUpstreamRelay`

**Parameters:**
- `symbol` - 
- `exchange` - 
- `type` - see `InstrumentCoreInfo.type`

### onTrade

```java
public void onTrade(String alias, double price, int size, TradeInfo tradeInfo)
```

Trade

**Specified by:** `onTrade` in interface `Layer1ApiDataListener`

**Overrides:** `onTrade` in class `Layer1ApiUpstreamRelay`

**Parameters:**
- `alias` - instrument alias
- `price` - price in the same units as in depth update (e.g. if pips=25 then price=1000 means 25000)
- `size` - trade size
- `tradeInfo` - additional information about the trade

### onDepth

```java
public void onDepth(String alias, boolean isBid, int price, int size)
```

Depth data update.

**Specified by:** `onDepth` in interface `Layer1ApiDataListener`

**Overrides:** `onDepth` in class `Layer1ApiUpstreamRelay`

**Parameters:**
- `alias` - instrument alias
- `isBid` - true if it is for bids, false for asks
- `price` - price where size changed
- `size` - new size

### onMboSend

```java
public void onMboSend(String alias, String orderId, boolean isBid, int price, int size)
```

**Specified by:** `onMboSend` in interface `Layer1ApiMboDataListener`

**Overrides:** `onMboSend` in class `Layer1ApiUpstreamRelay`

### onMboReplace

```java
public void onMboReplace(String alias, String orderId, int price, int size)
```

**Specified by:** `onMboReplace` in interface `Layer1ApiMboDataListener`

**Overrides:** `onMboReplace` in class `Layer1ApiUpstreamRelay`

### onMboCancel

```java
public void onMboCancel(String alias, String orderId)
```

**Specified by:** `onMboCancel` in interface `Layer1ApiMboDataListener`

**Overrides:** `onMboCancel` in class `Layer1ApiUpstreamRelay`

### onMarketMode

```java
public void onMarketMode(String alias, MarketMode marketMode)
```

Market mode update.

**Specified by:** `onMarketMode` in interface `Layer1ApiDataListener`

**Overrides:** `onMarketMode` in class `Layer1ApiUpstreamRelay`

**Parameters:**
- `alias` - 
- `marketMode` - 

### onOrderUpdated

```java
public void onOrderUpdated(OrderInfoUpdate orderInfoUpdate)
```

Called when order is updated (order creation is also considered an update).

**Specified by:** `onOrderUpdated` in interface `Layer1ApiTradingListener`

**Overrides:** `onOrderUpdated` in class `Layer1ApiUpstreamRelay`

**Parameters:**
- `orderInfoUpdate` - information about update

### onOrderExecuted

```java
public void onOrderExecuted(ExecutionInfo executionInfo)
```

Called when one of our orders gets executed

**Specified by:** `onOrderExecuted` in interface `Layer1ApiTradingListener`

**Overrides:** `onOrderExecuted` in class `Layer1ApiUpstreamRelay`

**Parameters:**
- `executionInfo` - information about execution

### onStatus

```java
public void onStatus(StatusInfo statusInfo)
```

Called when instrument status information changes (PnL, number of open orders, position, etc).

**Specified by:** `onStatus` in interface `Layer1ApiTradingListener`

**Overrides:** `onStatus` in class `Layer1ApiUpstreamRelay`

**Parameters:**
- `statusInfo` - status information

### onBalance

```java
public void onBalance(BalanceInfo balanceInfo)
```

Called when account balance information changes

**Specified by:** `onBalance` in interface `Layer1ApiTradingListener`

**Overrides:** `onBalance` in class `Layer1ApiUpstreamRelay`

**Parameters:**
- `balanceInfo` - account balance information

### onLoginFailed

```java
public void onLoginFailed(LoginFailedReason reason, String message)
```

Failed to login with specified credentials

**Specified by:** `onLoginFailed` in interface `Layer1ApiAdminListener`

**Overrides:** `onLoginFailed` in class `Layer1ApiUpstreamRelay`

**Parameters:**
- `reason` - reason code
- `message` - test message associated with login fail (may be null)

### onLoginSuccessful

```java
public void onLoginSuccessful()
```

Successful login.

**Specified by:** `onLoginSuccessful` in interface `Layer1ApiAdminListener`

**Overrides:** `onLoginSuccessful` in class `Layer1ApiUpstreamRelay`

### onConnectionLost

```java
public void onConnectionLost(DisconnectionReason reason, String message)
```

Connection to server lost. Provider can call this method multiple times in sequence if the reason was changed (i.e. from `DisconnectionReason.NO_INTERNET` to `DisconnectionReason.FATAL`)

**Specified by:** `onConnectionLost` in interface `Layer1ApiAdminListener`

**Overrides:** `onConnectionLost` in class `Layer1ApiUpstreamRelay`

**Parameters:**
- `reason` - reason code
- `message` - text message associated with login disconnection (may be null)

### onConnectionRestored

```java
public void onConnectionRestored()
```

Connection to server restored. A provider should call it only if it previously invoked `Layer1ApiAdminListener.onConnectionLost(DisconnectionReason, String)`

**Specified by:** `onConnectionRestored` in interface `Layer1ApiAdminListener`

**Overrides:** `onConnectionRestored` in class `Layer1ApiUpstreamRelay`

### onSystemTextMessage

```java
public void onSystemTextMessage(String message, SystemTextMessageType messageType)
```

Passes the message into Bookmap and shows it as a popup. It is suitable for 1-time notifications, but for the general case take a look at the notifications API - `Layer1ApiSoundAlertMessage`

**Specified by:** `onSystemTextMessage` in interface `Layer1ApiAdminListener`

**Overrides:** `onSystemTextMessage` in class `Layer1ApiUpstreamRelay`

**Parameters:**
- `message` - message itself
- `messageType` - message type

**See Also:**
- `Layer1ApiSoundAlertMessage`

### onUserMessage

```java
public void onUserMessage(Object data)
```

Send an upstream event. Allows incorporating arbitrary functionality into protocol

**Specified by:** `onUserMessage` in interface `Layer1ApiAdminListener`

**Overrides:** `onUserMessage` in class `Layer1ApiUpstreamRelay`

**Parameters:**
- `data` - Message content