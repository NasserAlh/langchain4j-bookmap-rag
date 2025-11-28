---
source_file: Api.html
package: velox.api.layer1.simplified
classes: Api
methods: registerIndicator, registerIndicatorModifiable, registerIndicator, registerIndicator, registerIndicatorModifiable, registerIndicatorModifiable, sendOrder, updateOrder, setSettings, getSettings, unload, reload, addTimeListeners, addDepthDataListeners, addMarketByOrderDepthDataListeners, addSnapshotEndListeners, addTradeDataListeners, addIntervalListeners, addBarDataListeners, addBboDataListeners
total_methods: 27
---

# Api

**Package:** velox.api.layer1.simplified

**Type:** Interface

## Description

Allows communicating back to Bookmap.

## Methods

### registerIndicator

```java
default Indicator registerIndicator(String name, velox.api.layer1.messages.indicators.Layer1ApiUserMessageModifyIndicator.GraphType graphType)
```

Similar to `registerIndicator(String, GraphType, double)`, assumes initialValue = NaN (no line until first update)

**Parameters:**
- `name` - User-friendly name for an indicator. **Must be unique within alias.**
- `graphType` - Where to draw the indicator (bottom panel or main chart)

**Returns:** Indicator object that can be used to manipulate the line

### registerIndicatorModifiable

```java
default IndicatorModifiable registerIndicatorModifiable(String name, velox.api.layer1.messages.indicators.Layer1ApiUserMessageModifyIndicator.GraphType graphType)
```

### registerIndicator

```java
default Indicator registerIndicator(String name, velox.api.layer1.messages.indicators.Layer1ApiUserMessageModifyIndicator.GraphType graphType, double initialValue)
```

Register an indicator (line).

**Parameters:**
- `name` - User-friendly name for an indicator. **Must be unique within alias.**
- `graphType` - Where to draw the indicator (bottom panel or main chart)
- `initialValue` - Initial value of the indicator. NaN means "no visible line".

**Returns:** Indicator object that can be used to manipulate the line

### registerIndicator

```java
Indicator registerIndicator(String name, velox.api.layer1.messages.indicators.Layer1ApiUserMessageModifyIndicator.GraphType graphType, double initialValue, boolean showLineByDefault, boolean showWidgetByDefault)
```

Register an indicator (line).

**Parameters:**
- `name` - User-friendly name for an indicator. **Must be unique within alias.**
- `graphType` - Where to draw the indicator (bottom panel or main chart)
- `initialValue` - Initial value of the indicator. NaN means "no visible line".
- `showLineByDefault` - Show indicator line by default. If false user has to manually enable the line before it's visible
- `showWidgetByDefault` - Show indicator widget by default. If false user has to manually enable the widget before it's visible

**Returns:** Indicator object that can be used to manipulate the line

### registerIndicatorModifiable

```java
default IndicatorModifiable registerIndicatorModifiable(String name, velox.api.layer1.messages.indicators.Layer1ApiUserMessageModifyIndicator.GraphType graphType, double initialValue)
```

### registerIndicatorModifiable

```java
IndicatorModifiable registerIndicatorModifiable(String name, velox.api.layer1.messages.indicators.Layer1ApiUserMessageModifyIndicator.GraphType graphType, double initialValue, boolean showLineByDefault, boolean showWidgetByDefault)
```

### sendOrder

```java
void sendOrder(velox.api.layer1.data.OrderSendParameters orderSendParameters)
```

Submit order with specified parameters

**Parameters:**
- `orderSendParameters` - Parameters

### updateOrder

```java
void updateOrder(velox.api.layer1.data.OrderUpdateParameters orderUpdateParameters)
```

Update order according to parameters

**Parameters:**
- `orderUpdateParameters` - Parameters

### setSettings

```java
<T> void setSettings(T settingsObject)
```

Store settings.

**Parameters:**
- `settingsObject` - Your settings object. Class must be annotated with `StrategySettingsVersion`

### getSettings

```java
<T> T getSettings(Class<? extends T> settingsClass)
```

Retrieve earlier stored settings

**Parameters:**
- `settingsClass` - Class of your settings object. Must be annotated with `StrategySettingsVersion`

**Returns:** Settings object. If there is no compatible saved object for this request, new object will be created with default constructor  
**Changing returned object will not change settings. You need to call `setSettings(Object)` for this**

### unload

```java
void unload()
```

Request unloading the module. This will lead to your module being unloaded for current alias. You could use it when error is detected and running module further makes no sense. You might still get few events before your requests gets processed. Note, that module will keep running for other aliases.

### reload

```java
void reload()
```

Request reloading the module. This will lead to your module being unloaded for current alias, then loaded back. Typical use case would be to apply settings changes.

### addTimeListeners

```java
void addTimeListeners(TimeListener timeListener)
```

Manually add time listener when using `NoAutosubscription`

### addDepthDataListeners

```java
void addDepthDataListeners(DepthDataListener depthDataListener)
```

Manually add depth data listener when using `NoAutosubscription`

### addMarketByOrderDepthDataListeners

```java
void addMarketByOrderDepthDataListeners(MarketByOrderDepthDataListener marketByOrderDepthDataListener)
```

Manually add depth data listener when using `NoAutosubscription`

### addSnapshotEndListeners

```java
void addSnapshotEndListeners(SnapshotEndListener snapshotEndListener)
```

Manually add snapshot end listener when using `NoAutosubscription`

### addTradeDataListeners

```java
void addTradeDataListeners(TradeDataListener tradeDataListener)
```

Manually add trade data listener when using `NoAutosubscription`

### addIntervalListeners

```java
void addIntervalListeners(IntervalListener intervalListener)
```

Manually add interval listener when using `NoAutosubscription`

### addBarDataListeners

```java
void addBarDataListeners(BarDataListener barDataListener)
```

Manually add bar data listener when using `NoAutosubscription`

### addBboDataListeners

```java
void addBboDataListeners(BboListener bboListener)
```

Manually add bbo listener when using `NoAutosubscription`

### addOrdersListeners

```java
void addOrdersListeners(OrdersListener ordersListener)
```

Manually add order listener when using `NoAutosubscription`

### addStatusListeners

```java
void addStatusListeners(PositionListener positionListener)
```

Manually add position listener when using `NoAutosubscription`

### addBalanceListeners

```java
void addBalanceListeners(BalanceListener balanceListener)
```

Manually add balance data listener when using `NoAutosubscription`

### addHistoricalModeListeners

```java
void addHistoricalModeListeners(HistoricalModeListener historicalModeListener)
```

Manually add historical mode listener when using `NoAutosubscription`

### addMultiInstrumentListeners

```java
void addMultiInstrumentListeners(MultiInstrumentListener multiInstrumentListener)
```

Manually add multi-instrument data listener when using `NoAutosubscription`

### sendUserMessage

```java
void sendUserMessage(Object data)
```

Allows communicating with other modules

### getProvider

```java
velox.api.layer1.Layer1ApiProvider getProvider()
```

Allows advanced actions that require direct access to L1 stack. Most indicators do not need it.