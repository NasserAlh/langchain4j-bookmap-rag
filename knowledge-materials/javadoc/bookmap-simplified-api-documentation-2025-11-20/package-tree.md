---
source_file: package-tree.html
package: velox.api.layer1.simplified
classes: velox.api.layer1.simplified Package Hierarchy
---

# velox.api.layer1.simplified Package Hierarchy

**Package:** velox.api.layer1.simplified

## Class Hierarchy

- `java.lang.Object`
  - `AxisGroup`
  - `AxisRules`
  - `Bar`
  - `java.awt.Component` (implements `java.awt.image.ImageObserver`, `java.awt.MenuContainer`, `java.io.Serializable`)
    - `java.awt.Container`
      - `javax.swing.JComponent` (implements `java.io.Serializable`)
        - `javax.swing.JPanel` (implements `javax.accessibility.Accessible`)
          - `velox.gui.StrategyPanel`
            - `SimpleStrategyPanel`
  - `DeactivatableStrategyUpdateGeneratorWithFilter` (implements `velox.api.layer1.messages.indicators.StrategyUpdateGenerator`, `velox.api.layer1.messages.indicators.StrategyUpdateGeneratorFilter`, `velox.api.layer1.messages.indicators.StrategyUpdateGeneratorSkipper`)
  - `InitialState`
  - `InstanceUtils`
  - `Intervals`
  - `velox.api.layer1.Layer1ApiBasicListenable` (implements `velox.api.layer1.LayerApiListenable`)
    - `velox.api.layer1.layers.Layer1ApiUpstreamRelay` (implements `velox.api.layer1.Layer1ApiListener`)
      - `velox.api.layer1.layers.Layer1ApiRelay` (implements `velox.api.layer1.Layer1ApiListener`, `velox.api.layer1.Layer1ApiProvider`, `velox.api.layer1.layers.Layer1ApiTimeSource`)
        - `velox.api.layer1.layers.Layer1ApiInjectorRelay`
          - `SimplifiedL1ApiLoader<T>` (implements `velox.api.layer1.Layer1ApiFinishable`, `velox.api.layer1.Layer1ApiInstrumentSpecificEnabledStateProvider`, `velox.api.layer1.settings.Layer1ConfigSettingsInterface`, `velox.api.layer1.Layer1CustomPanelsGetter`, `velox.api.layer1.layers.strategies.interfaces.Layer1IndicatorColorInterface`, `velox.api.layer1.localization.Layer1LocalizationInterface`, `velox.api.layer1.layers.strategies.interfaces.Layer1PriceAxisRangeCalculatable`, `velox.api.layer1.layers.strategies.interfaces.Layer1StrategyAddInterface`, `velox.api.layer1.layers.strategies.interfaces.Layer1WidgetRangeInterface`)
  - `LoadSessionIdWrapper`
  - `MarketByOrderDepthDataAdapter` (implements `MarketByOrderDepthDataListener`)
  - `NumberConverter`
  - `ParameterFieldDeserializer` (implements `com.google.gson.JsonDeserializer<T>`)
  - `UserSettings`
  - `UserSettings.ParameterField`
  - `UserSettingsWrapper`
  - `WidgetGroup`
  - `WidgetRules`
  - `WidgetRulesCalculator`

## Interface Hierarchy

- `Api`
- `BalanceListener`
  - `AllDataModule` (also extends `BarDataListener`, `BboListener`, `CustomModule`, `DepthDataListener`, `HistoricalModeListener`, `MarketByOrderDepthDataListener`, `MultiInstrumentListener`, `OrdersListener`, `PositionListener`, `TimeListener`, `TradeDataListener`)
  - `BalanceAdapter`
- `BboListener`
  - `AllDataModule` (also extends `BalanceListener`, `BarDataListener`, `CustomModule`, `DepthDataListener`, `HistoricalModeListener`, `MarketByOrderDepthDataListener`, `MultiInstrumentListener`, `OrdersListener`, `PositionListener`, `TimeListener`, `TradeDataListener`)
  - `BboAdapter`
- `CustomModule`
  - `AllDataModule` (also extends `BalanceListener`, `BarDataListener`, `BboListener`, `DepthDataListener`, `HistoricalModeListener`, `MarketByOrderDepthDataListener`, `MultiInstrumentListener`, `OrdersListener`, `PositionListener`, `TimeListener`, `TradeDataListener`)
  - `CustomModuleAdapter`
- `CustomSettingsPanelProvider`
- `DepthDataListener`
  - `AllDataModule` (also extends `BalanceListener`, `BarDataListener`, `BboListener`, `CustomModule`, `HistoricalModeListener`, `MarketByOrderDepthDataListener`, `MultiInstrumentListener`, `OrdersListener`, `PositionListener`, `TimeListener`, `TradeDataListener`)
  - `DepthDataAdapter`
- `HistoricalDataListener`
  - `BackfilledDataListener`
  - `HistoricalModeListener`
    - `AllDataModule` (also extends `BalanceListener`, `BarDataListener`, `BboListener`, `CustomModule`, `DepthDataListener`, `MarketByOrderDepthDataListener`, `MultiInstrumentListener`, `OrdersListener`, `PositionListener`, `TimeListener`, `TradeDataListener`)
    - `HistoricalModeAdapter`
- `Indicator`
  - `IndicatorModifiable`
- `IntervalListener`
  - `BarDataListener`
    - `AllDataModule` (also extends `BalanceListener`, `BboListener`, `CustomModule`, `DepthDataListener`, `HistoricalModeListener`, `MarketByOrderDepthDataListener`, `MultiInstrumentListener`, `OrdersListener`, `PositionListener`, `TimeListener`, `TradeDataListener`)
    - `BarDataAdapter`
  - `IntervalAdapter`
- `MarketByOrderDepthDataListener`
  - `AllDataModule` (also extends `BalanceListener`, `BarDataListener`, `BboListener`, `CustomModule`, `DepthDataListener`, `HistoricalModeListener`, `MultiInstrumentListener`, `OrdersListener`, `PositionListener`, `TimeListener`, `TradeDataListener`)
- `MessageListener`
- `MultiInstrumentListener`
  - `AllDataModule` (also extends `BalanceListener`, `BarDataListener`, `BboListener`, `CustomModule`, `DepthDataListener`, `HistoricalModeListener`, `MarketByOrderDepthDataListener`, `OrdersListener`, `PositionListener`, `TimeListener`, `TradeDataListener`)
  - `MultiInstrumentAdapter`
- `OrdersListener`
  - `AllDataModule` (also extends `BalanceListener`, `BarDataListener`, `BboListener`, `CustomModule`, `DepthDataListener`, `HistoricalModeListener`, `MarketByOrderDepthDataListener`, `MultiInstrumentListener`, `PositionListener`, `TimeListener`, `TradeDataListener`)
  - `OrdersAdapter`
- `ParameterChangeListener`
- `PositionListener`
  - `AllDataModule` (also extends `BalanceListener`, `BarDataListener`, `BboListener`, `CustomModule`, `DepthDataListener`, `HistoricalModeListener`, `MarketByOrderDepthDataListener`, `MultiInstrumentListener`, `OrdersListener`, `TimeListener`, `TradeDataListener`)
  - `PositionAdapter`
- `SnapshotEndListener`
  - `SnapshotEndAdapter`
- `TimeListener`
  - `AllDataModule` (also extends `BalanceListener`, `BarDataListener`, `BboListener`, `CustomModule`, `DepthDataListener`, `HistoricalModeListener`, `MarketByOrderDepthDataListener`, `MultiInstrumentListener`, `OrdersListener`, `PositionListener`, `TradeDataListener`)
  - `TimeAdapter`
- `TradeDataListener`
  - `AllDataModule` (also extends `BalanceListener`, `BarDataListener`, `BboListener`, `CustomModule`, `DepthDataListener`, `HistoricalModeListener`, `MarketByOrderDepthDataListener`, `MultiInstrumentListener`, `OrdersListener`, `PositionListener`, `TimeListener`)
  - `TradeDataAdapter`

## Annotation Interface Hierarchy

- `NoAutosubscription` (implements `java.lang.annotation.Annotation`)
- `Parameter` (implements `java.lang.annotation.Annotation`)

## Enum Class Hierarchy

- `java.lang.Object`
  - `java.lang.Enum<E>` (implements `java.lang.Comparable<T>`, `java.lang.constant.Constable`, `java.io.Serializable`)
    - `LineStyle`
    - `SimplifiedL1ApiLoader.Mode`