---
source_file: package-summary.html
package: velox.api.layer1.simplified
classes: velox.api.layer1.simplified
methods: AllDataModule, Api, AxisGroup, AxisRules, BackfilledDataListener, BalanceAdapter, BalanceListener, Bar, BarDataAdapter, BarDataListener, BboAdapter, BboListener, CustomModule, CustomModuleAdapter, CustomSettingsPanelProvider, DeactivatableStrategyUpdateGeneratorWithFilter, DepthDataAdapter, DepthDataListener, HistoricalDataListener, HistoricalModeAdapter
total_methods: 59
---

# velox.api.layer1.simplified

**Package:** velox.api.layer1.simplified

## Classes and Interfaces

### AllDataModule
**Type:** Interface

Interface implementing all simplified module interfaces including historical data.

### Api
**Type:** Interface

Allows communicating back to Bookmap.

### AxisGroup
**Type:** Class

Group of indicators that have same value ranges.

### AxisRules
**Type:** Class

This class describes axis range selection rules.

### BackfilledDataListener
**Type:** Interface

Marks class that wants to receive backfilled (cloud historical) data.

### BalanceAdapter
**Type:** Interface

An adapter for `BalanceListener` with empty default method implementations.

### BalanceListener
**Type:** Interface

Provides balance info.

### Bar
**Type:** Class

Single OHLC bar.

### BarDataAdapter
**Type:** Interface

An adapter for `BarDataListener` with empty default method implementations.

### BarDataListener
**Type:** Interface

Get bars and order book snapshot with fixed interval.

### BboAdapter
**Type:** Interface

An adapter for `BboListener` with empty default method implementations.

### BboListener
**Type:** Interface

Get best bid/offer updates each time one of those changes.

### CustomModule
**Type:** Interface

Must be implemented by modules annotated with `Layer1SimpleAttachable`.

### CustomModuleAdapter
**Type:** Interface

An adapter for `CustomModule` with empty default method implementations.

### CustomSettingsPanelProvider
**Type:** Interface

Allows specifying custom UI directly.

### DeactivatableStrategyUpdateGeneratorWithFilter
**Type:** Class

### DepthDataAdapter
**Type:** Interface

An adapter for `DepthDataListener` with empty default method implementations.

### DepthDataListener
**Type:** Interface

Get incremental depth updates.

### HistoricalDataListener
**Type:** Interface

Marks class that wants to receive data before the point it was loaded.

### HistoricalModeAdapter
**Type:** Interface

An adapter for `HistoricalModeListener` with empty default method implementations.

### HistoricalModeListener
**Type:** Interface

In addition to historical data, you will also be notified on transition between historical data and live data.

### Indicator
**Type:** Interface

Indicator representing a line.

### IndicatorModifiable
**Type:** Interface

### InitialState
**Type:** Class

Some initialization data about events that happened shortly before the initialization moment (note, that for historical-enabled indicators many fields will be empty).

### InstanceUtils
**Type:** Class

### IntervalAdapter
**Type:** Interface

An adapter for `IntervalListener` with empty default method implementations.

### IntervalListener
**Type:** Interface

### Intervals
**Type:** Class

Just a bunch of common intervals.

### LineStyle
**Type:** Enum Class

### LoadSessionIdWrapper
**Type:** Class

### MarketByOrderDepthDataAdapter
**Type:** Class

An adapter for `MarketByOrderDepthDataListener` with empty default methods implementations.

### MarketByOrderDepthDataListener
**Type:** Interface

Get market depth data order by order (if supported by the data provider).

### MessageListener
**Type:** Interface

### MultiInstrumentAdapter
**Type:** Interface

An adapter for `MultiInstrumentListener` with empty default method implementations.

### MultiInstrumentListener
**Type:** Interface

Listen to data from multiple instruments at once.

### NoAutosubscription
**Type:** Annotation Interface

Cancels auto-subscription to all available listeners for the user's strategy. When applied, a user should add listeners manually `Api`.

### NumberConverter
**Type:** Class

### OrdersAdapter
**Type:** Interface

An adapter for `OrdersListener` with empty default method implementations.

### OrdersListener
**Type:** Interface

Provides order updates.

**Warning 1:** Order prices are provided as raw price value (without dividing by min tick (pips)), so you will have to do multiplication yourself if you want to show those on main chart.

`HistoricalDataListener` should **not** be implemented if you want this to work, consider to use `HistoricalModeListener` instead.

### Parameter
**Type:** Annotation Interface

A field annotated with the Parameter annotation can be configured from Bookmap's GUI ("Configure api plugins" dialog).

### ParameterChangeListener
**Type:** Interface

### ParameterFieldDeserializer
**Type:** Class

### PositionAdapter
**Type:** Interface

An adapter for `PositionListener` with empty default method implementations.

### PositionListener
**Type:** Interface

Provides position info.

### SimpleStrategyPanel
**Type:** Class

The class creates a Strategy panel and provides simple methods for placing components into it.

### SimplifiedL1ApiLoader<T extends CustomModule>
**Type:** Class

### SimplifiedL1ApiLoader.Mode
**Type:** Enum Class

### SnapshotEndAdapter
**Type:** Interface

An adapter for `SnapshotEndListener` with empty default method implementations.

### SnapshotEndListener
**Type:** Interface

### TimeAdapter
**Type:** Interface

An adapter for `TimeListener` with empty default method implementations.

### TimeListener
**Type:** Interface

Get event timestamps.

### TradeDataAdapter
**Type:** Interface

An adapter for `TradeDataListener` with empty default method implementations.

### TradeDataListener
**Type:** Interface

### UserSettings
**Type:** Class

### UserSettings.ParameterField
**Type:** Class

### UserSettingsWrapper
**Type:** Class

### WidgetGroup
**Type:** Class

### WidgetRules
**Type:** Class

### WidgetRulesCalculator
**Type:** Class