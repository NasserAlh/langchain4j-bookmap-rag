---
source_file: Layer1ApiProviderSupportedFeatures.html
package: velox.api.layer1.data
classes: Layer1ApiProviderSupportedFeatures
methods: Layer1ApiProviderSupportedFeatures.ClientSideFeature, trading, multiAccountTrading, tradingVia, tradingFrom, oco, oso, depth, mbo, trailingStopsAsIndependentOrders, trailingStopsAsBracketChildren, brackets, bracketTiers, convertOrderToMkt, supportedLimitDurations, supportedStopDurations, supportedStopOrders, negativeStopLimitOffset, marketMode, isBalanceSupported
total_methods: 38
---

# Layer1ApiProviderSupportedFeatures

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.data.Layer1ApiProviderSupportedFeatures

## Description

Key differences between providers should be described by this class.

## Nested Classes

### Layer1ApiProviderSupportedFeatures.ClientSideFeature

**Type:** enum

Features that are implemented on the client side.

## Fields

### trading

```java
public final boolean trading
```

Basic trading capabilities

### multiAccountTrading

```java
public final boolean multiAccountTrading
```

Whether multi-account trading is supported by the provider.

**See Also:**
- `Layer1ApiTradingListener`
- `TradingAccountsInfoMessage`
- `Layer1MultiAccountTradingSupported`

### tradingVia

```java
@Deprecated
public List<String> tradingVia
```

**Deprecated.** Use `symbolsMappingFunction` instead.

### tradingFrom

```java
@Deprecated
public List<String> tradingFrom
```

**Deprecated.** Use `symbolsMappingFunction` instead.

### oco

```java
public final boolean oco
```

### oso

```java
public final boolean oso
```

### depth

```java
public final boolean depth
```

Provides depth data. This is what most providers should do. Enabled in builder by default. Either `depth` or `mbo` must be enabled for any provider. Provider that generates MBO data (`mbo`) can still generate depth data if there is a reason to believe provider can do it better than deriving from MBO directly.

### mbo

```java
public final boolean mbo
```

Provides MBO data.

### trailingStopsAsIndependentOrders

```java
public final boolean trailingStopsAsIndependentOrders
```

### trailingStopsAsBracketChildren

```java
public final boolean trailingStopsAsBracketChildren
```

### brackets

```java
public final boolean brackets
```

### bracketTiers

```java
public final boolean bracketTiers
```

### convertOrderToMkt

```java
public final boolean convertOrderToMkt
```

### supportedLimitDurations

```java
public final List<OrderDuration> supportedLimitDurations
```

### supportedStopDurations

```java
public final List<OrderDuration> supportedStopDurations
```

### supportedStopOrders

```java
public final List<OrderType> supportedStopOrders
```

### negativeStopLimitOffset

```java
public final boolean negativeStopLimitOffset
```

If this is set to false then stop-limit orders can only be sent with positive or zero offset

### marketMode

```java
public final boolean marketMode
```

### isBalanceSupported

```java
public final boolean isBalanceSupported
```

### tradingStartKnown

```java
public final boolean tradingStartKnown
```

If set it means that provider has a way to know when trading day starts for the instrument

### knownInstruments

```java
public final List<SubscribeInfo> knownInstruments
```

List of instruments to be automatically added to subscription dialog

### exchangeUsedForSubscription

```java
public final boolean exchangeUsedForSubscription
```

Determines if user can enter exchange when subscribing

### typeUsedForSubscription

```java
public final boolean typeUsedForSubscription
```

Determines if user can set type when subscribing

### pipsFunction

```java
public final Function<SubscribeInfo, DefaultAndList<Double>> pipsFunction
```

If set, it will be called to determine what are the possible pips values for selected instrument

### sizeMultiplierFunction

```java
public final Function<SubscribeInfo, DefaultAndList<Double>> sizeMultiplierFunction
```

If set, it will be called to determine what are the possible size multiplier values for selected instrument

### subscriptionInfoFunction

```java
public final Function<SubscribeInfo, String> subscriptionInfoFunction
```

Provides a short text block to be displayed in subscription dialog. Might be something informational (like what this instrument is) or a warning (i.e. that selected pips is too small to be meaningful for most users). Note that you might get any subclass of `SubscribeInfo`, so make sure to ignore unknown types and return something reasonable like `null`. Also keep in mind, that even if your adapter does require pips and size multiplier, you still might get `SubscribeInfo` instead of `SubscribeInfoCrypto` whenever those two are not defined yet (e.g. while waiting for `pipsFunction`)

### historicalDataInfo

```java
public final HistoricalDataInfo historicalDataInfo
```

If not null, describes how historical data can be retrieved for particular instrument

### lookupInfo

```java
public final LookupInfo lookupInfo
```

### isDelayed

```java
public final boolean isDelayed
```

If data is delayed

### clientSideFeatures

```java
public final Set<Layer1ApiProviderSupportedFeatures.ClientSideFeature> clientSideFeatures
```

List of features that are emulated on client side. Adapter will typically leave that empty. For example if adapter tells that brackets are not supported Bookmap will activate client-side replacement, but it will be less reliable (e.g. won't work if Bookmap is closed, will be slower due to network latency, etc)

### isTradingSubscriptionSupported

```java
public final boolean isTradingSubscriptionSupported
```

Indicates if the trading subscription can be supported, usually it is needed on cross trading.

### symbolsMappingFunction

```java
public final Function<Set<InstrumentCoreInfo>, SymbolMappingInfo> symbolsMappingFunction
```

Bookmap will provide you a list of the known representations of this instrument (for different platforms). If you don't recognize the instrument - return null. Bookmap might ask you again once it has more data (i.e. from other providers)

### receiveCrossTradingStatusMessage

```java
public final boolean receiveCrossTradingStatusMessage
```

Indicates if the provider wants to receive a message when cross-trading starts/stops on one of its instruments.

### isHistoricalAggregationDisabled

```java
public final boolean isHistoricalAggregationDisabled
```

By default, Bookmap will aggregate historical data, by setting this flag to true you can disable this functionality.

## Methods

### getCopyWithAppliedTradingParams

```java
public Layer1ApiProviderSupportedFeatures getCopyWithAppliedTradingParams(Layer1ApiProviderSupportedFeatures other)
```

**Parameters:**
- `other` - 

**Returns:** copy of this supported features with all trading params from other

### toBuilder

```java
public Layer1ApiProviderSupportedFeaturesBuilder toBuilder()
```

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`