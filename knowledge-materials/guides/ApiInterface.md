---
source_file: guides/ApiInterface.md
type: guide
topic: api, listeners, indicators, settings, module-management
package: velox.api.layer1.simplified
related_classes: Api, Indicator, IndicatorModifiable
related_interfaces: BalanceListener, BarDataListener, BboListener, DepthDataListener, HistoricalModeListener, IntervalListener, MarketByOrderDepthDataListener, MultiInstrumentListener, OrdersListener, PositionListener, SnapshotEndListener, TimeListener, TradeDataListener
annotations: NoAutosubscription, StrategySettingsVersion
---

# Api Interface

The `Api` interface allows communication back to Bookmap. It is provided in the `initialize()` method and should be stored for later use.

## Adding Listeners

Methods for manually adding listeners when using `@NoAutosubscription`:

| Method | Listener Type |
|--------|---------------|
| `addBalanceListeners(BalanceListener)` | Balance updates |
| `addBarDataListeners(BarDataListener)` | Bar/candle data |
| `addBboDataListeners(BboListener)` | Best bid/offer |
| `addDepthDataListeners(DepthDataListener)` | MBP depth |
| `addHistoricalModeListeners(HistoricalModeListener)` | Historical mode changes |
| `addIntervalListeners(IntervalListener)` | Interval callbacks |
| `addMarketByOrderDepthDataListeners(MarketByOrderDepthDataListener)` | MBO depth |
| `addMultiInstrumentListeners(MultiInstrumentListener)` | Multi-instrument |
| `addOrdersListeners(OrdersListener)` | Order updates |
| `addSnapshotEndListeners(SnapshotEndListener)` | Snapshot completion |
| `addStatusListeners(PositionListener)` | Position updates |
| `addTimeListeners(TimeListener)` | Timestamp updates |
| `addTradeDataListeners(TradeDataListener)` | Trade events |

### Example: Manual Subscription

```java
@NoAutosubscription
@Layer1SimpleAttachable
@Layer1StrategyName("Manual Subscription Example")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
public class ManualSubscriptionExample implements CustomModuleAdapter, DepthDataListener {

    @Override
    public void initialize(String alias, InstrumentInfo info, Api api, InitialState initialState) {
        // Manually subscribe to depth data
        api.addDepthDataListeners(this);
    }

    @Override
    public void onDepth(boolean isBid, int price, int size) {
        // Handle depth updates
    }
}
```

## Registering Indicators

### Basic Registration

```java
// Basic - no initial value (line not visible until first update)
Indicator registerIndicator(String name, GraphType graphType)

// With initial value
Indicator registerIndicator(String name, GraphType graphType, double initialValue)

// Full control over visibility
Indicator registerIndicator(String name, GraphType graphType, double initialValue,
                           boolean showLineByDefault, boolean showWidgetByDefault)
```

### Modifiable Indicators

```java
// Modifiable variants allow changing indicator properties after creation
IndicatorModifiable registerIndicatorModifiable(String name, GraphType graphType)
IndicatorModifiable registerIndicatorModifiable(String name, GraphType graphType, double initialValue)
IndicatorModifiable registerIndicatorModifiable(String name, GraphType graphType, double initialValue,
                                                boolean showLineByDefault, boolean showWidgetByDefault)
```

### GraphType Options

| GraphType | Description |
|-----------|-------------|
| `GraphType.PRIMARY` | Main chart |
| `GraphType.BOTTOM` | Bottom panel |

### Example: Creating an Indicator

```java
@Override
public void initialize(String alias, InstrumentInfo info, Api api, InitialState initialState) {
    // Create indicator on main chart
    Indicator indicator = api.registerIndicator("My Indicator", GraphType.PRIMARY);

    // Update indicator value
    indicator.addPoint(someValue);

    // Add icon to indicator
    indicator.addIcon(priceLevel, iconImage, centerX, centerY);
}
```

## Order Management

```java
// Submit order
void sendOrder(OrderSendParameters orderSendParameters)

// Update existing order
void updateOrder(OrderUpdateParameters orderUpdateParameters)
```

See [Order Placement](OrderPlacement.md) for detailed examples.

## Settings Management

Settings allow persisting configuration between sessions.

### Requirements

- Settings class must be annotated with `@StrategySettingsVersion`
- Settings object must be serializable

### Methods

```java
// Store settings
<T> void setSettings(T settingsObject)

// Retrieve settings (creates new with defaults if none saved)
<T> T getSettings(Class<? extends T> settingsClass)
```

### Example: Settings Class

```java
@StrategySettingsVersion(currentVersion = 1, compatibleVersions = {})
public static class Settings {
    int requiredSequentialCandles = 5;
    double minPercentMove = 0.00;
}
```

### Example: Using Settings

```java
private Settings settings;

@Override
public void initialize(String alias, InstrumentInfo info, Api api, InitialState initialState) {
    // Load saved settings (or create new with defaults)
    settings = api.getSettings(Settings.class);

    // Use settings
    int candles = settings.requiredSequentialCandles;
}

// When settings change (e.g., from UI)
private void onSettingsChanged() {
    settings.requiredSequentialCandles = newValue;
    api.setSettings(settings);  // Persist changes
}
```

## Module Management

```java
// Request unload (use when error detected or task complete)
void unload()

// Request reload (typically for applying settings changes)
void reload()
```

### Example: Unload After Task

```java
@Override
public void onRealtimeStart() {
    // Historical processing complete, unload the module
    Log.info("Task complete. Unloading...");
    api.unload();
}
```

## Utility Methods

```java
// Communicate with other modules
void sendUserMessage(Object data)

// Advanced L1 stack access (most add-ons don't need this)
Layer1ApiProvider getProvider()
```

## See Also

- [Getting Started](GettingStarted.md) - Basic add-on structure
- [Order Placement](OrderPlacement.md) - Placing orders
- [OrderBlockStrategy Example](../examples/OrderBlockStrategy.md) - Settings and indicators in action
- [Javadoc: Api](../javadoc/bookmap-simplified-api-documentation-2025-11-20/Api.md)
