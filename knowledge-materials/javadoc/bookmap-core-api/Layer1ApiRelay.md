---
source_file: Layer1ApiRelay.html
package: velox.api.layer1.layers
classes: Layer1ApiRelay
methods: provider, Layer1ApiRelay, Layer1ApiRelay, setProvider, formatPrice, subscribe, unsubscribe, sendOrder, updateOrder, login, getCurrentTime, close, getSource, sendUserMessage, getSupportedFeatures, getTimeSource, toString
---

# Layer1ApiRelay

**Package:** velox.api.layer1.layers

**Type:** Class

**Inheritance:** `java.lang.Object` → `velox.api.layer1.Layer1ApiBasicListenable` → `velox.api.layer1.layers.Layer1ApiUpstreamRelay` → `Layer1ApiRelay`

**All Implemented Interfaces:** `AutoCloseable`, `Layer1ApiAdminListenable`, `Layer1ApiAdminListener`, `Layer1ApiAdminProvider`, `Layer1ApiDataListenable`, `Layer1ApiDataListener`, `Layer1ApiDataProvider`, `Layer1ApiInstrumentListenable`, `Layer1ApiInstrumentListener`, `Layer1ApiInstrumentProvider`, `Layer1ApiListener`, `Layer1ApiMboDataListenable`, `Layer1ApiMboDataListener`, `Layer1ApiProvider`, `Layer1ApiTradingListenable`, `Layer1ApiTradingListener`, `Layer1ApiTradingProvider`, `LayerApiListenable`, `Layer1ApiTimeSource`

**Direct Known Subclasses:** `Layer1ApiDepthFreezer`, `Layer1ApiInjectorRelay`

## Description

Good starting point for all injectable strategies. Just passes all events through.

## Fields

### provider

```java
protected Layer1ApiProvider provider
```

Do not change it directly, use `setProvider(Layer1ApiProvider)`. And don't do that unless you really need to.

## Constructors

### Layer1ApiRelay

```java
public Layer1ApiRelay(Layer1ApiProvider provider)
```

### Layer1ApiRelay

```java
protected Layer1ApiRelay(Layer1ApiProvider provider, boolean subscribeToProvider)
```

## Methods

### setProvider

```java
protected void setProvider(Layer1ApiProvider provider)
```

If you use this method you must implement `getCurrentTime()` so time shortcut is not applied

### formatPrice

```java
public String formatPrice(String alias, double price)
```

Format price using platform capabilities if possible

**Specified by:** `formatPrice` in interface `Layer1ApiDataProvider`

**Parameters:**
- `alias` - 
- `price` - 

**Returns:** 

### subscribe

```java
public void subscribe(SubscribeInfo subscribeInfo)
```

Subscribe to specified instrument. Some parameters can be null depending on the platform.

**Specified by:** `subscribe` in interface `Layer1ApiInstrumentProvider`

**Parameters:**
- `subscribeInfo` - has fields similar to `InstrumentCoreInfo`

### unsubscribe

```java
public void unsubscribe(String alias)
```

Unsubscribe from the instrument

**Specified by:** `unsubscribe` in interface `Layer1ApiInstrumentProvider`

**Parameters:**
- `alias` - instrument alias

### sendOrder

```java
public void sendOrder(OrderSendParameters orderSendParameters)
```

Submit order with specified parameters

**Specified by:** `sendOrder` in interface `Layer1ApiTradingProvider`

**Parameters:**
- `orderSendParameters` - parameters

### updateOrder

```java
public void updateOrder(OrderUpdateParameters orderUpdateParameters)
```

Update order according to parameters

**Specified by:** `updateOrder` in interface `Layer1ApiTradingProvider`

**Parameters:**
- `orderUpdateParameters` - parameters

### login

```java
public void login(LoginData loginData)
```

Initiate login to the platform.

**Specified by:** `login` in interface `Layer1ApiAdminProvider`

**Parameters:**
- `loginData` - credentials, different platforms use different subclasses

### getCurrentTime

```java
public long getCurrentTime()
```

Returns current Unix epoch time. This method allows time distortions if consumer is too slow - in this case time of the next event may be returned.

**Specified by:** `getCurrentTime` in interface `Layer1ApiAdminProvider`

**Returns:** current Unix epoch time in nanoseconds

### close

```java
public void close()
```

Stop relaying data. Will not let send new events upwards, but is not guaranteed to stop ones already in progress.

**Specified by:** `close` in interface `AutoCloseable`
**Specified by:** `close` in interface `Layer1ApiAdminProvider`
**Overrides:** `close` in class `Layer1ApiUpstreamRelay`

### getSource

```java
public String getSource()
```

Returns string representing data source.

**Specified by:** `getSource` in interface `Layer1ApiAdminProvider`

**Returns:** string representing data source

### sendUserMessage

```java
public Object sendUserMessage(Object data)
```

Send a downstream event. Allows incorporating arbitrary functionality into protocol

**Specified by:** `sendUserMessage` in interface `Layer1ApiAdminProvider`

**Parameters:**
- `data` - Message content

**Returns:** 

### getSupportedFeatures

```java
public Layer1ApiProviderSupportedFeatures getSupportedFeatures()
```

Get information about features that are supported by a provider. Note that some fields might be set differently depending on when request is made. E.g. adapter might not know if trading will be there until login actually happens.

If you are writing a provider - take a look at `MaximumSupportedFeatures` as a way to declare full list of capabilities before being instantiated

**Specified by:** `getSupportedFeatures` in interface `Layer1ApiAdminProvider`

**Returns:** object describing supported features

**See Also:**
- `MaximumSupportedFeatures`

### getTimeSource

```java
public Layer1ApiProvider getTimeSource()
```

**Specified by:** `getTimeSource` in interface `Layer1ApiTimeSource`

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`