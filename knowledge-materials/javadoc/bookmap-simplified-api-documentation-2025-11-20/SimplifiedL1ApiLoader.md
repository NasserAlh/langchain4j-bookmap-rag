---
source_file: SimplifiedL1ApiLoader.html
package: velox.api.layer1.simplified
classes: SimplifiedL1ApiLoader
methods: SimplifiedL1ApiLoader.Mode, CUSTOMTRADEEVENTSAGGREGATOR, initialUserSettings, SimplifiedL1ApiLoader, acceptLocalizedBundleProvider, onStrategyAdd, finish, close, setColor, getColor, addColorChangeListener, acceptSettingsInterface, getCustomGuiFor, settingsChanged, onStrategyCheckboxEnabled, isStrategyEnabled, setStrategyEnabledRecheckCallback, onUserMessage, onInstrumentAdded, onInstrumentRemoved
total_methods: 37
---

# SimplifiedL1ApiLoader

**Package:** velox.api.layer1.simplified

**Type:** Class

**Type Parameters:** `<T extends CustomModule>`

## Inheritance

**Extends:** 
- `java.lang.Object`
- `velox.api.layer1.Layer1ApiBasicListenable`
- `velox.api.layer1.layers.Layer1ApiUpstreamRelay`
- `velox.api.layer1.layers.Layer1ApiRelay`
- `velox.api.layer1.layers.Layer1ApiInjectorRelay`

**All Implemented Interfaces:**
- `java.lang.AutoCloseable`
- `velox.api.layer1.Layer1ApiAdminListenable`
- `velox.api.layer1.Layer1ApiAdminListener`
- `velox.api.layer1.Layer1ApiAdminProvider`
- `velox.api.layer1.Layer1ApiDataListenable`
- `velox.api.layer1.Layer1ApiDataListener`
- `velox.api.layer1.Layer1ApiDataProvider`
- `velox.api.layer1.Layer1ApiFinishable`
- `velox.api.layer1.Layer1ApiInstrumentListenable`
- `velox.api.layer1.Layer1ApiInstrumentListener`
- `velox.api.layer1.Layer1ApiInstrumentProvider`
- `velox.api.layer1.Layer1ApiInstrumentSpecificEnabledStateProvider`
- `velox.api.layer1.Layer1ApiListener`
- `velox.api.layer1.Layer1ApiMboDataListenable`
- `velox.api.layer1.Layer1ApiMboDataListener`
- `velox.api.layer1.Layer1ApiProvider`
- `velox.api.layer1.Layer1ApiTradingListenable`
- `velox.api.layer1.Layer1ApiTradingListener`
- `velox.api.layer1.Layer1ApiTradingProvider`
- `velox.api.layer1.Layer1CustomPanelsGetter`
- `velox.api.layer1.LayerApiListenable`
- `velox.api.layer1.layers.Layer1ApiTimeSource`
- `velox.api.layer1.layers.strategies.interfaces.Layer1IndicatorColorInterface`
- `velox.api.layer1.layers.strategies.interfaces.Layer1PriceAxisRangeCalculatable`
- `velox.api.layer1.layers.strategies.interfaces.Layer1StrategyAddInterface`
- `velox.api.layer1.layers.strategies.interfaces.Layer1WidgetRangeInterface`
- `velox.api.layer1.localization.Layer1LocalizationInterface`
- `velox.api.layer1.settings.Layer1ConfigSettingsInterface`

## Nested Classes

### SimplifiedL1ApiLoader.Mode
```java
static enum SimplifiedL1ApiLoader.Mode
```

## Fields

### CUSTOM_TRADE_EVENTS_AGGREGATOR

```java
public static final velox.api.layer1.layers.strategies.interfaces.CustomEventAggregatble CUSTOM_TRADE_EVENTS_AGGREGATOR
```

### initialUserSettings

```java
public Map<String, Map<String, UserSettings.ParameterField>> initialUserSettings
```

## Constructors

### SimplifiedL1ApiLoader

```java
public SimplifiedL1ApiLoader(velox.api.layer1.Layer1ApiProvider provider, Class<T> clazz)
```

## Methods

### acceptLocalizedBundleProvider

```java
void acceptLocalizedBundleProvider(velox.api.layer1.localization.LocalizedBundleProvider localizedBundleProvider)
```

**Specified by:** `acceptLocalizedBundleProvider` in interface `velox.api.layer1.localization.Layer1LocalizationInterface`

### onStrategyAdd

```java
void onStrategyAdd()
```

**Specified by:** `onStrategyAdd` in interface `velox.api.layer1.layers.strategies.interfaces.Layer1StrategyAddInterface`

### finish

```java
void finish()
```

**Specified by:** `finish` in interface `velox.api.layer1.Layer1ApiFinishable`

### close

```java
void close()
```

**Specified by:**
- `close` in interface `java.lang.AutoCloseable`
- `close` in interface `velox.api.layer1.Layer1ApiAdminProvider`

**Overrides:** `close` in class `velox.api.layer1.layers.Layer1ApiInjectorRelay`

### setColor

```java
void setColor(String alias, String name, Color color)
```

**Specified by:** `setColor` in interface `velox.api.layer1.layers.strategies.interfaces.Layer1IndicatorColorInterface`

### getColor

```java
Color getColor(String alias, String name)
```

**Specified by:** `getColor` in interface `velox.api.layer1.layers.strategies.interfaces.Layer1IndicatorColorInterface`

### addColorChangeListener

```java
void addColorChangeListener(velox.colors.ColorsChangedListener listener)
```

**Specified by:** `addColorChangeListener` in interface `velox.api.layer1.layers.strategies.interfaces.Layer1IndicatorColorInterface`

### acceptSettingsInterface

```java
void acceptSettingsInterface(velox.api.layer1.messages.indicators.SettingsAccess settingsAccess)
```

**Specified by:** `acceptSettingsInterface` in interface `velox.api.layer1.settings.Layer1ConfigSettingsInterface`

### getCustomGuiFor

```java
velox.gui.StrategyPanel[] getCustomGuiFor(String alias, String indicatorName)
```

**Specified by:** `getCustomGuiFor` in interface `velox.api.layer1.Layer1CustomPanelsGetter`

### settingsChanged

```java
void settingsChanged(String alias, UserSettings settings)
```

### onStrategyCheckboxEnabled

```java
void onStrategyCheckboxEnabled(String alias, boolean isEnabled)
```

**Specified by:** `onStrategyCheckboxEnabled` in interface `velox.api.layer1.Layer1ApiInstrumentSpecificEnabledStateProvider`

### isStrategyEnabled

```java
boolean isStrategyEnabled(String alias)
```

**Specified by:** `isStrategyEnabled` in interface `velox.api.layer1.Layer1ApiInstrumentSpecificEnabledStateProvider`

### setStrategyEnabledRecheckCallback

```java
void setStrategyEnabledRecheckCallback(String alias, Runnable recheckCallback)
```

**Specified by:** `setStrategyEnabledRecheckCallback` in interface `velox.api.layer1.Layer1ApiInstrumentSpecificEnabledStateProvider`

### onUserMessage

```java
void onUserMessage(Object data)
```

**Specified by:** `onUserMessage` in interface `velox.api.layer1.Layer1ApiAdminListener`

**Overrides:** `onUserMessage` in class `velox.api.layer1.layers.Layer1ApiInjectorRelay`

### onInstrumentAdded

```java
void onInstrumentAdded(String alias, velox.api.layer1.data.InstrumentInfo instrumentInfo)
```

**Specified by:** `onInstrumentAdded` in interface `velox.api.layer1.Layer1ApiInstrumentListener`

**Overrides:** `onInstrumentAdded` in class `velox.api.layer1.layers.Layer1ApiInjectorRelay`

### onInstrumentRemoved

```java
void onInstrumentRemoved(String alias)
```

**Specified by:** `onInstrumentRemoved` in interface `velox.api.layer1.Layer1ApiInstrumentListener`

**Overrides:** `onInstrumentRemoved` in class `velox.api.layer1.layers.Layer1ApiInjectorRelay`

### onDepth

```java
void onDepth(String alias, boolean isBid, int price, int size)
```

**Specified by:** `onDepth` in interface `velox.api.layer1.Layer1ApiDataListener`

**Overrides:** `onDepth` in class `velox.api.layer1.layers.Layer1ApiInjectorRelay`

### onMboSend

```java
void onMboSend(String alias, String orderId, boolean isBid, int price, int size)
```

**Specified by:** `onMboSend` in interface `velox.api.layer1.Layer1ApiMboDataListener`

**Overrides:** `onMboSend` in class `velox.api.layer1.layers.Layer1ApiInjectorRelay`

### onMboReplace

```java
void onMboReplace(String alias, String orderId, int price, int size)
```

**Specified by:** `onMboReplace` in interface `velox.api.layer1.Layer1ApiMboDataListener`

**Overrides:** `onMboReplace` in class `velox.api.layer1.layers.Layer1ApiInjectorRelay`

### onMboCancel

```java
void onMboCancel(String alias, String orderId)
```

**Specified by:** `onMboCancel` in interface `velox.api.layer1.Layer1ApiMboDataListener`

**Overrides:** `onMboCancel` in class `velox.api.layer1.layers.Layer1ApiInjectorRelay`

### onTrade

```java
void onTrade(String alias, double price, int size, velox.api.layer1.data.TradeInfo tradeInfo)
```

**Specified by:** `onTrade` in interface `velox.api.layer1.Layer1ApiDataListener`

**Overrides:** `onTrade` in class `velox.api.layer1.layers.Layer1ApiInjectorRelay`

### onOrderUpdated

```java
void onOrderUpdated(velox.api.layer1.data.OrderInfoUpdate orderInfoUpdate)
```

**Specified by:** `onOrderUpdated` in interface `velox.api.layer1.Layer1ApiTradingListener`

**Overrides:** `onOrderUpdated` in class `velox.api.layer1.layers.Layer1ApiInjectorRelay`

### onOrderExecuted

```java
void onOrderExecuted(velox.api.layer1.data.ExecutionInfo executionInfo)
```

**Specified by:** `onOrderExecuted` in interface `velox.api.layer1.Layer1ApiTradingListener`

**Overrides:** `onOrderExecuted` in class `velox.api.layer1.layers.Layer1ApiInjectorRelay`

### onStatus

```java
void onStatus(velox.api.layer1.data.StatusInfo statusInfo)
```

**Specified by:** `onStatus` in interface `velox.api.layer1.Layer1ApiTradingListener`

**Overrides:** `onStatus` in class `velox.api.layer1.layers.Layer1ApiInjectorRelay`

### onBalance

```java
void onBalance(velox.api.layer1.data.BalanceInfo balanceInfo)
```

**Specified by:** `onBalance` in interface `velox.api.layer1.Layer1ApiTradingListener`

**Overrides:** `onBalance` in class `velox.api.layer1.layers.Layer1ApiInjectorRelay`

### getPriceRanges

```java
Map<String, velox.api.layer1.layers.strategies.interfaces.Layer1PriceAxisRangeCalculatable.ResultPriceAxisInfo> getPriceRanges(String alias, double linesCount, Map<String, velox.api.layer1.layers.strategies.interfaces.Layer1PriceAxisRangeCalculatable.InputPriceAxisInfo> inputInfo)
```

**Specified by:** `getPriceRanges` in interface `velox.api.layer1.layers.strategies.interfaces.Layer1PriceAxisRangeCalculatable`

### createUserSettingsFromAnnotations

```java
UserSettings createUserSettingsFromAnnotations(Object instance, Class<? extends Annotation> clazz, String alias, boolean settingsExist)
```

### restartInstrument

```java
void restartInstrument(String alias)
```

### getUserSettings

```java
UserSettings getUserSettings(String alias)
```

### setWidgetConsumer

```java
void setWidgetConsumer(String alias, String fullName, velox.api.layer1.layers.strategies.interfaces.WidgetRangeConsumer consumer)
```

**Specified by:** `setWidgetConsumer` in interface `velox.api.layer1.layers.strategies.interfaces.Layer1WidgetRangeInterface`

### sendUserMessageAsync

```java
void sendUserMessageAsync(Object data)
```

### sendUserMessage

```java
Object sendUserMessage(Object data)
```

**Specified by:** `sendUserMessage` in interface `velox.api.layer1.Layer1ApiAdminProvider`

**Overrides:** `sendUserMessage` in class `velox.api.layer1.layers.Layer1ApiRelay`

### getCurrentTime

```java
long getCurrentTime()
```

**Specified by:** `getCurrentTime` in interface `velox.api.layer1.Layer1ApiAdminProvider`

**Overrides:** `getCurrentTime` in class `velox.api.layer1.layers.Layer1ApiRelay`