---
source_file: Layer1ApiUpstreamRelay.html
package: velox.api.layer1.layers
classes: Layer1ApiUpstreamRelay
methods: closed, Layer1ApiUpstreamRelay, onInstrumentAdded, onInstrumentRemoved, onInstrumentNotFound, onInstrumentAlreadySubscribed, onTrade, onDepth, onMboSend, onMboReplace, onMboCancel, onMarketMode, onOrderUpdated, onOrderExecuted, onStatus, onBalance, onLoginFailed, onLoginSuccessful, onConnectionLost, onConnectionRestored
total_methods: 23
extends: ** velox.api.layer1.Layer1ApiBasicListenable, **
---

# Layer1ApiUpstreamRelay

**Package:** velox.api.layer1.layers

**Type:** Class

## Inheritance

**Extends:** velox.api.layer1.Layer1ApiBasicListenable

**Implements:** 
- velox.api.layer1.Layer1ApiAdminListenable
- velox.api.layer1.Layer1ApiAdminListener
- velox.api.layer1.Layer1ApiDataListenable
- velox.api.layer1.Layer1ApiDataListener
- velox.api.layer1.Layer1ApiInstrumentListenable
- velox.api.layer1.Layer1ApiInstrumentListener
- velox.api.layer1.Layer1ApiListener
- velox.api.layer1.Layer1ApiMboDataListenable
- velox.api.layer1.Layer1ApiMboDataListener
- velox.api.layer1.Layer1ApiTradingListenable
- velox.api.layer1.Layer1ApiTradingListener
- velox.api.layer1.LayerApiListenable

**Direct Known Subclasses:** velox.api.layer1.layers.Layer1ApiRelay

## Fields

### closed

```java
protected AtomicBoolean closed
```

## Constructors

### Layer1ApiUpstreamRelay

```java
public Layer1ApiUpstreamRelay()
```

## Methods

### onInstrumentAdded

```java
void onInstrumentAdded(String alias, InstrumentInfo instrumentInfo)
```

Indicates successful subscription to the instrument

**Parameters:**
- `alias` - Instrument alias
- `instrumentInfo` - Instrument specifications

---

### onInstrumentRemoved

```java
void onInstrumentRemoved(String alias)
```

Unsubscribed from instrument (user-initiated or provider initiated).

Please note that if you use this in **strategy that implements `OnlineCalculatable`**, you should not delete any data about instrument that is expected in `OnlineCalculatable.calculateValuesInRange(String, String, long, long, int, velox.api.layer1.layers.strategies.interfaces.CalculatedResultListener)` or `OnlineCalculatable.createOnlineValueCalculator(String, String, long, java.util.function.Consumer, velox.api.layer1.layers.strategies.interfaces.InvalidateInterface)`, or should handle absence of that data (in case you delete it) gracefully. The reason is that you can get `Layer1ApiInstrumentListener.onInstrumentRemoved(String)` in the middle of `OnlineCalculatable` request

**Parameters:**
- `alias` - 

---

### onInstrumentNotFound

```java
void onInstrumentNotFound(String symbol, String exchange, String type)
```

Indicates that attempt to subscribe failed because instrument does not exist

**Parameters:**
- `symbol` - 
- `exchange` - 
- `type` - See `InstrumentCoreInfo.type`

---

### onInstrumentAlreadySubscribed

```java
void onInstrumentAlreadySubscribed(String symbol, String exchange, String type)
```

Indicates that attempt to subscribe failed because subscription is already active

**Parameters:**
- `symbol` - 
- `exchange` - 
- `type` - See `InstrumentCoreInfo.type`

---

### onTrade

```java
void onTrade(String alias, double price, int size, TradeInfo tradeInfo)
```

Trade

**Parameters:**
- `alias` - Instrument alias
- `price` - Price in the same units as in depth update (e.g. if pips=25 then price=1000 means 25000)
- `size` - Trade size
- `tradeInfo` - Additional information about the trade

---

### onDepth

```java
void onDepth(String alias, boolean isBid, int price, int size)
```

Depth data update.

**Parameters:**
- `alias` - Instrument alias
- `isBid` - True if it is for bids, false for asks
- `price` - Price where size changed
- `size` - New size

---

### onMboSend

```java
void onMboSend(String alias, String orderId, boolean isBid, int price, int size)
```

---

### onMboReplace

```java
void onMboReplace(String alias, String orderId, int price, int size)
```

---

### onMboCancel

```java
void onMboCancel(String alias, String orderId)
```

---

### onMarketMode

```java
void onMarketMode(String alias, MarketMode marketMode)
```

Market mode update.

**Parameters:**
- `alias` - 
- `marketMode` - 

---

### onOrderUpdated

```java
void onOrderUpdated(OrderInfoUpdate orderInfoUpdate)
```

Called when order is updated (order creation is also considered an update).

**Parameters:**
- `orderInfoUpdate` - Information about update

---

### onOrderExecuted

```java
void onOrderExecuted(ExecutionInfo executionInfo)
```

Called when one of our orders gets executed

**Parameters:**
- `executionInfo` - Information about execution

---

### onStatus

```java
void onStatus(StatusInfo statusInfo)
```

Called when instrument status information changes (PnL, number of open orders, position, etc).

**Parameters:**
- `statusInfo` - Status information

---

### onBalance

```java
void onBalance(BalanceInfo balanceInfo)
```

Called when account balance information changes

**Parameters:**
- `balanceInfo` - Account balance information

---

### onLoginFailed

```java
void onLoginFailed(LoginFailedReason reason, String message)
```

Failed to login with specified credentials

**Parameters:**
- `reason` - Reason code
- `message` - Test message associated with login fail (may be null)

---

### onLoginSuccessful

```java
void onLoginSuccessful()
```

Successful login.

---

### onConnectionLost

```java
void onConnectionLost(DisconnectionReason reason, String message)
```

Connection to server lost. Provider can call this method multiple times in sequence if the reason was changed (i.e. from `DisconnectionReason.NO_INTERNET` to `DisconnectionReason.FATAL`)

**Parameters:**
- `reason` - Reason code
- `message` - Text message associated with login disconnection (may be null)

---

### onConnectionRestored

```java
void onConnectionRestored()
```

Connection to server restored. A provider should call it only if it previously invoked `Layer1ApiAdminListener.onConnectionLost(DisconnectionReason, String)`

---

### onSystemTextMessage

```java
void onSystemTextMessage(String message, SystemTextMessageType messageType)
```

Passes the message into Bookmap and shows it as a popup. It is suitable for 1-time notifications, but for the general case take a look at the notifications API - `Layer1ApiSoundAlertMessage`

**Parameters:**
- `message` - Message itself
- `messageType` - Message type

**See Also:**
- `Layer1ApiSoundAlertMessage`

---

### onUserMessage

```java
void onUserMessage(Object data)
```

Send an upstream event. Allows incorporating arbitrary functionality into protocol

**Parameters:**
- `data` - Message content

---

### close

```java
void close()
```

Stop relaying data. Will not let send new events upwards, but is not guaranteed to stop ones already in progress.