---
source_file: Layer1ApiDepthFreezer.html
package: velox.api.layer1.layers
classes: Layer1ApiDepthFreezer
methods: Layer1ApiDepthFreezer.DepthFreezeRequest, Layer1ApiDepthFreezer, onDepth, onUserMessage
extends: **
---

# Layer1ApiDepthFreezer

**Package:** velox.api.layer1.layers

**Type:** Class

## Inheritance

**Extends:** 
- `java.lang.Object`
- `velox.api.layer1.Layer1ApiBasicListenable`
- `velox.api.layer1.layers.Layer1ApiUpstreamRelay`
- `velox.api.layer1.layers.Layer1ApiRelay`

**All Implemented Interfaces:**
- `java.lang.AutoCloseable`
- `velox.api.layer1.Layer1ApiAdminListenable`
- `velox.api.layer1.Layer1ApiAdminListener`
- `velox.api.layer1.Layer1ApiAdminProvider`
- `velox.api.layer1.Layer1ApiDataListenable`
- `velox.api.layer1.Layer1ApiDataListener`
- `velox.api.layer1.Layer1ApiDataProvider`
- `velox.api.layer1.Layer1ApiInstrumentListenable`
- `velox.api.layer1.Layer1ApiInstrumentListener`
- `velox.api.layer1.Layer1ApiInstrumentProvider`
- `velox.api.layer1.Layer1ApiListener`
- `velox.api.layer1.Layer1ApiMboDataListenable`
- `velox.api.layer1.Layer1ApiMboDataListener`
- `velox.api.layer1.Layer1ApiProvider`
- `velox.api.layer1.Layer1ApiTradingListenable`
- `velox.api.layer1.Layer1ApiTradingListener`
- `velox.api.layer1.Layer1ApiTradingProvider`
- `velox.api.layer1.LayerApiListenable`
- `velox.api.layer1.layers.Layer1ApiTimeSource`

## Nested Classes

### Layer1ApiDepthFreezer.DepthFreezeRequest
Static nested class

**Inherited from interface `velox.api.layer1.layers.Layer1ApiTimeSource`:**
- `Layer1ApiTimeSource.Layer1TimeSourceShortcutAllowed`

## Fields

**Inherited from class `velox.api.layer1.layers.Layer1ApiRelay`:**
- `provider`

**Inherited from class `velox.api.layer1.layers.Layer1ApiUpstreamRelay`:**
- `closed`

**Inherited from class `velox.api.layer1.Layer1ApiBasicListenable`:**
- `adminListeners`
- `dataListeners`
- `instrumentListeners`
- `mboDataListeners`
- `theOnlyAdminListener`
- `theOnlyDataListener`
- `theOnlyInstrumentListener`
- `theOnlyMboDataListener`
- `theOnlyTradingListener`
- `tradingListeners`

## Constructors

### Layer1ApiDepthFreezer

```java
public Layer1ApiDepthFreezer(Layer1ApiProvider provider)
```

## Methods

### onDepth

```java
public void onDepth(String alias, boolean isBid, int price, int size)
```

Depth data update.

**Specified by:** `onDepth` in interface `Layer1ApiDataListener`

**Overrides:** `onDepth` in class `Layer1ApiUpstreamRelay`

**Parameters:**
- `alias` - Instrument alias
- `isBid` - True if it is for bids, false for asks
- `price` - Price where size changed
- `size` - New size

### onUserMessage

```java
public void onUserMessage(Object data)
```

Send an upstream event. Allows incorporating arbitrary functionality into protocol.

**Specified by:** `onUserMessage` in interface `Layer1ApiAdminListener`

**Overrides:** `onUserMessage` in class `Layer1ApiUpstreamRelay`

**Parameters:**
- `data` - Message content

## Inherited Methods

**From class `velox.api.layer1.layers.Layer1ApiRelay`:**
- `close()`
- `formatPrice(String, double)`
- `getCurrentTime()`
- `getSource()`
- `getSupportedFeatures()`
- `getTimeSource()`
- `login(velox.api.layer1.data.LoginData)`
- `sendOrder(velox.api.layer1.data.OrderSendParameters)`
- `sendUserMessage(Object)`
- `setProvider(velox.api.layer1.Layer1ApiProvider)`
- `subscribe(velox.api.layer1.data.SubscribeInfo)`
- `toString()`
- `unsubscribe(String)`
- `updateOrder(velox.api.layer1.data.OrderUpdateParameters)`

**From class `velox.api.layer1.layers.Layer1ApiUpstreamRelay`:**
- `onBalance(velox.api.layer1.data.BalanceInfo)`
- `onConnectionLost(velox.api.layer1.data.DisconnectionReason, String)`
- `onConnectionRestored()`
- `onInstrumentAdded(String, velox.api.layer1.data.InstrumentInfo)`
- `onInstrumentAlreadySubscribed(String, String, String)`
- `onInstrumentNotFound(String, String, String)`
- `onInstrumentRemoved(String)`
- `onLoginFailed(velox.api.layer1.data.LoginFailedReason, String)`
- `onLoginSuccessful()`
- `onMarketMode(String, velox.api.layer1.data.MarketMode)`
- `onMboCancel(String, String)`
- `onMboReplace(String, String, int, int)`
- `onMboSend(String, String, boolean, int, int)`
- `onOrderExecuted(velox.api.layer1.data.ExecutionInfo)`
- `onOrderUpdated(velox.api.layer1.data.OrderInfoUpdate)`
- `onStatus(velox.api.layer1.data.StatusInfo)`
- `onSystemTextMessage(String, velox.api.layer1.data.SystemTextMessageType)`
- `onTrade(String, double, int, velox.api.layer1.data.TradeInfo)`