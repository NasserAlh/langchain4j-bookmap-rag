---
source_file: Layer1ApiProviderSupportedFeaturesBuilder.html
package: velox.api.layer1.data
classes: Layer1ApiProviderSupportedFeaturesBuilder, Layer1ApiProviderSupportedFeaturesBuilder
methods: exchangeUsedForSubscription, historicalDataInfo, knownInstruments, lookupInfo, pipsFunction, sizeMultiplierFunction, subscriptionInfoFunction, symbolsMappingFunction, tradingFrom, tradingStartKnown, tradingVia, typeUsedForSubscription, Layer1ApiProviderSupportedFeaturesBuilder, build, getClientSideFeatures, getHistoricalDataInfo, getIsTradingSubscriptionSupported, getKnownInstruments, getLookupInfo, getPipsFunction
total_methods: 166
---

# Layer1ApiProviderSupportedFeaturesBuilder

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → Layer1ApiProviderSupportedFeaturesBuilder

## Description

See fields meaning in `Layer1ApiProviderSupportedFeatures`

## Fields

### exchangeUsedForSubscription
```java
boolean exchangeUsedForSubscription
```

### historicalDataInfo
```java
HistoricalDataInfo historicalDataInfo
```

### knownInstruments
```java
List<SubscribeInfo> knownInstruments
```

### lookupInfo
```java
LookupInfo lookupInfo
```

### pipsFunction
```java
Function<SubscribeInfo, DefaultAndList<Double>> pipsFunction
```

### sizeMultiplierFunction
```java
Function<SubscribeInfo, DefaultAndList<Double>> sizeMultiplierFunction
```

### subscriptionInfoFunction
```java
Function<SubscribeInfo, String> subscriptionInfoFunction
```

### symbolsMappingFunction
```java
Function<Set<InstrumentCoreInfo>, SymbolMappingInfo> symbolsMappingFunction
```

### tradingFrom
```java
List<String> tradingFrom
```

**Deprecated**

### tradingStartKnown
```java
boolean tradingStartKnown
```

### tradingVia
```java
List<String> tradingVia
```

**Deprecated**

### typeUsedForSubscription
```java
boolean typeUsedForSubscription
```

## Constructors

### Layer1ApiProviderSupportedFeaturesBuilder
```java
Layer1ApiProviderSupportedFeaturesBuilder()
```

Creates a new instance with all features marked as unsupported

## Methods

### build
```java
Layer1ApiProviderSupportedFeatures build()
```

### getClientSideFeatures
```java
Set<Layer1ApiProviderSupportedFeatures.ClientSideFeature> getClientSideFeatures()
```

### getHistoricalDataInfo
```java
HistoricalDataInfo getHistoricalDataInfo()
```

### getIsTradingSubscriptionSupported
```java
boolean getIsTradingSubscriptionSupported()
```

### getKnownInstruments
```java
List<SubscribeInfo> getKnownInstruments()
```

### getLookupInfo
```java
LookupInfo getLookupInfo()
```

### getPipsFunction
```java
Function<SubscribeInfo, DefaultAndList<Double>> getPipsFunction()
```

### getSizeMultiplierFunction
```java
Function<SubscribeInfo, DefaultAndList<Double>> getSizeMultiplierFunction()
```

### getSubscriptionInfoFunction
```java
Function<SubscribeInfo, String> getSubscriptionInfoFunction()
```

### getSupportedLimitDurations
```java
List<OrderDuration> getSupportedLimitDurations()
```

### getSupportedStopDurations
```java
List<OrderDuration> getSupportedStopDurations()
```

### getSupportedStopOrders
```java
List<OrderType> getSupportedStopOrders()
```

### getSymbolsMappingFunction
```java
Function<Set<InstrumentCoreInfo>, SymbolMappingInfo> getSymbolsMappingFunction()
```

### getTradingFrom
```java
List<String> getTradingFrom()
```

**Deprecated**

### getTradingVia
```java
List<String> getTradingVia()
```

**Deprecated**

### isBalanceSupported
```java
boolean isBalanceSupported()
```

### isBrackets
```java
boolean isBrackets()
```

### isBracketTiers
```java
boolean isBracketTiers()
```

### isConvertOrderToMkt
```java
boolean isConvertOrderToMkt()
```

### isDelayed
```java
boolean isDelayed()
```

### isDepth
```java
boolean isDepth()
```

### isExchangeUsedForSubscription
```java
boolean isExchangeUsedForSubscription()
```

### isHistoricalAggregationDisabled
```java
boolean isHistoricalAggregationDisabled()
```

### isMarketMode
```java
boolean isMarketMode()
```

### isMbo
```java
boolean isMbo()
```

### isMultiAccountTrading
```java
boolean isMultiAccountTrading()
```

### isNegativeStopLimitOffset
```java
boolean isNegativeStopLimitOffset()
```

### isOco
```java
boolean isOco()
```

### isOso
```java
boolean isOso()
```

### isReceiveCrossTradingStatusMessage
```java
boolean isReceiveCrossTradingStatusMessage()
```

### isTrading
```java
boolean isTrading()
```

### isTradingStartKnown
```java
boolean isTradingStartKnown()
```

### isTrailingStopsAsBracketChildren
```java
boolean isTrailingStopsAsBracketChildren()
```

### isTrailingStopsAsIndependentOrders
```java
boolean isTrailingStopsAsIndependentOrders()
```

### isTypeUsedForSubscription
```java
boolean isTypeUsedForSubscription()
```

### setBalanceSupported
```java
Layer1ApiProviderSupportedFeaturesBuilder setBalanceSupported(boolean isBalanceSupported)
```

### setBrackets
```java
Layer1ApiProviderSupportedFeaturesBuilder setBrackets(boolean brackets)
```

### setBracketTiers
```java
Layer1ApiProviderSupportedFeaturesBuilder setBracketTiers(boolean bracketTiers)
```

### setClientSideFeatures
```java
Layer1ApiProviderSupportedFeaturesBuilder setClientSideFeatures(Set<Layer1ApiProviderSupportedFeatures.ClientSideFeature> clientSideFeatures)
```

### setConvertOrderToMkt
```java
Layer1ApiProviderSupportedFeaturesBuilder setConvertOrderToMkt(boolean convertOrderToMkt)
```

### setDelayed
```java
Layer1ApiProviderSupportedFeaturesBuilder setDelayed(boolean isDelayed)
```

### setDepth
```java
Layer1ApiProviderSupportedFeaturesBuilder setDepth(boolean depth)
```

### setExchangeUsedForSubscription
```java
Layer1ApiProviderSupportedFeaturesBuilder setExchangeUsedForSubscription(boolean exchangeUsedForSubscription)
```

### setHistoricalAggregationDisabled
```java
Layer1ApiProviderSupportedFeaturesBuilder setHistoricalAggregationDisabled(boolean isHistoricalAggregationDisabled)
```

### setHistoricalDataInfo
```java
Layer1ApiProviderSupportedFeaturesBuilder setHistoricalDataInfo(HistoricalDataInfo historicalDataInfo)
```

### setKnownInstruments
```java
Layer1ApiProviderSupportedFeaturesBuilder setKnownInstruments(List<SubscribeInfo> knownInstruments)
```

### setLookupInfo
```java
Layer1ApiProviderSupportedFeaturesBuilder setLookupInfo(LookupInfo lookupInfo)
```

### setMarketMode
```java
Layer1ApiProviderSupportedFeaturesBuilder setMarketMode(boolean marketMode)
```

### setMbo
```java
Layer1ApiProviderSupportedFeaturesBuilder setMbo(boolean mbo)
```

### setMultiAccountTrading
```java
Layer1ApiProviderSupportedFeaturesBuilder setMultiAccountTrading(boolean multiAccountTrading)
```

### setNegativeStopLimitOffset
```java
Layer1ApiProviderSupportedFeaturesBuilder setNegativeStopLimitOffset(boolean negativeStopLimitOffset)
```

### setOco
```java
Layer1ApiProviderSupportedFeaturesBuilder setOco(boolean oco)
```

### setOso
```java
Layer1ApiProviderSupportedFeaturesBuilder setOso(boolean oso)
```

### setPipsFunction
```java
Layer1ApiProviderSupportedFeaturesBuilder setPipsFunction(Function<SubscribeInfo, DefaultAndList<Double>> pipsFunction)
```

### setReceiveCrossTradingStatusMessage
```java
Layer1ApiProviderSupportedFeaturesBuilder setReceiveCrossTradingStatusMessage(boolean receiveCrossTradingStatusMessage)
```

### setSizeMultiplierFunction
```java
Layer1ApiProviderSupportedFeaturesBuilder setSizeMultiplierFunction(Function<SubscribeInfo, DefaultAndList<Double>> sizeMultiplierFunction)
```

### setSubscriptionInfoFunction
```java
Layer1ApiProviderSupportedFeaturesBuilder setSubscriptionInfoFunction(Function<SubscribeInfo, String> subscriptionInfoFunction)
```

### setSupportedLimitDurations
```java
Layer1ApiProviderSupportedFeaturesBuilder setSupportedLimitDurations(List<OrderDuration> supportedLimitDurations)
```

### setSupportedOrderDurations
```java
Layer1ApiProviderSupportedFeaturesBuilder setSupportedOrderDurations(List<OrderDuration> supportedOrderDurations)
```

### setSupportedStopDurations
```java
Layer1ApiProviderSupportedFeaturesBuilder setSupportedStopDurations(List<OrderDuration> supportedStopDurations)
```

### setSupportedStopOrders
```java
Layer1ApiProviderSupportedFeaturesBuilder setSupportedStopOrders(List<OrderType> supportedStopOrders)
```

### setSymbolsMappingFunction
```java
Layer1ApiProviderSupportedFeaturesBuilder setSymbolsMappingFunction(Function<Set<InstrumentCoreInfo>, SymbolMappingInfo> symbolsMappingFunction)
```

### setTrading
```java
Layer1ApiProviderSupportedFeaturesBuilder setTrading(boolean trading)
```

### setTradingFrom
```java
Layer1ApiProviderSupportedFeaturesBuilder setTradingFrom(List<String> tradingFrom)
```

**Deprecated**

### setTradingStartKnown
```java
Layer1ApiProviderSupportedFeaturesBuilder setTradingStartKnown(boolean tradingStartKnown)
```

### setTradingSubscriptionSupported
```java
Layer1ApiProviderSupportedFeaturesBuilder setTradingSubscriptionSupported(boolean isTradingSubscriptionSupported)
```

### setTradingVia
```java
Layer1ApiProviderSupportedFeaturesBuilder setTradingVia(List<String> tradingVia)
```

**Deprecated**

### setTrailingStopsAsBracketChildren
```java
Layer1ApiProviderSupportedFeaturesBuilder setTrailingStopsAsBracketChildren(boolean trailingStopsAsBracketChildren)
```

### setTrailingStopsAsIndependentOrders
```java
Layer1ApiProviderSupportedFeaturesBuilder setTrailingStopsAsIndependentOrders(boolean trailingStopsAsIndependentOrders)
```

### setTypeUsedForSubscription
```java
Layer1ApiProviderSupportedFeaturesBuilder setTypeUsedForSubscription(boolean typeUsedForSubscription)
```

---

# Layer1ApiProviderSupportedFeaturesBuilder

## Fields

### tradingVia

```java
@Deprecated
public List<String> tradingVia
```

**Deprecated.**

Providers that this provider would like to trade via if those are available.

### tradingFrom

```java
@Deprecated
public List<String> tradingFrom
```

**Deprecated.**

Providers that this provider would like to handle trading for

### tradingStartKnown

```java
public boolean tradingStartKnown
```

### knownInstruments

```java
public List<SubscribeInfo> knownInstruments
```

### exchangeUsedForSubscription

```java
public boolean exchangeUsedForSubscription
```

### typeUsedForSubscription

```java
public boolean typeUsedForSubscription
```

### pipsFunction

```java
public Function<SubscribeInfo, DefaultAndList<Double>> pipsFunction
```

### sizeMultiplierFunction

```java
public Function<SubscribeInfo, DefaultAndList<Double>> sizeMultiplierFunction
```

### subscriptionInfoFunction

```java
public Function<SubscribeInfo, String> subscriptionInfoFunction
```

### historicalDataInfo

```java
public HistoricalDataInfo historicalDataInfo
```

### lookupInfo

```java
public LookupInfo lookupInfo
```

### symbolsMappingFunction

```java
public Function<Set<InstrumentCoreInfo>, SymbolMappingInfo> symbolsMappingFunction
```

## Constructors

### Layer1ApiProviderSupportedFeaturesBuilder

```java
public Layer1ApiProviderSupportedFeaturesBuilder()
```

Creates a new instance with all features marked as unsupported

## Methods

### build

```java
public Layer1ApiProviderSupportedFeatures build()
```

### isDepth

```java
public boolean isDepth()
```

### setDepth

```java
public Layer1ApiProviderSupportedFeaturesBuilder setDepth(boolean depth)
```

### isMbo

```java
public boolean isMbo()
```

### setMbo

```java
public Layer1ApiProviderSupportedFeaturesBuilder setMbo(boolean mbo)
```

### getTradingVia

```java
@Deprecated
public List<String> getTradingVia()
```

**Deprecated.**

### setTradingVia

```java
@Deprecated
public Layer1ApiProviderSupportedFeaturesBuilder setTradingVia(List<String> tradingVia)
```

**Deprecated.**

### getTradingFrom

```java
@Deprecated
public List<String> getTradingFrom()
```

**Deprecated.**

### setTradingFrom

```java
@Deprecated
public Layer1ApiProviderSupportedFeaturesBuilder setTradingFrom(List<String> tradingFrom)
```

**Deprecated.**

### isTrading

```java
public boolean isTrading()
```

### setTrading

```java
public Layer1ApiProviderSupportedFeaturesBuilder setTrading(boolean trading)
```

### isMultiAccountTrading

```java
public boolean isMultiAccountTrading()
```

### setMultiAccountTrading

```java
public Layer1ApiProviderSupportedFeaturesBuilder setMultiAccountTrading(boolean multiAccountTrading)
```

### isOco

```java
public boolean isOco()
```

### setOco

```java
public Layer1ApiProviderSupportedFeaturesBuilder setOco(boolean oco)
```

### isOso

```java
public boolean isOso()
```

### setOso

```java
public Layer1ApiProviderSupportedFeaturesBuilder setOso(boolean oso)
```

### isTrailingStopsAsIndependentOrders

```java
public boolean isTrailingStopsAsIndependentOrders()
```

### setTrailingStopsAsIndependentOrders

```java
public Layer1ApiProviderSupportedFeaturesBuilder setTrailingStopsAsIndependentOrders(boolean trailingStopsAsIndependentOrders)
```

### isTrailingStopsAsBracketChildren

```java
public boolean isTrailingStopsAsBracketChildren()
```

### setTrailingStopsAsBracketChildren

```java
public Layer1ApiProviderSupportedFeaturesBuilder setTrailingStopsAsBracketChildren(boolean trailingStopsAsBracketChildren)
```

### isBrackets

```java
public boolean isBrackets()
```

### setBrackets

```java
public Layer1ApiProviderSupportedFeaturesBuilder setBrackets(boolean brackets)
```

### isBracketTiers

```java
public boolean isBracketTiers()
```

### setBracketTiers

```java
public Layer1ApiProviderSupportedFeaturesBuilder setBracketTiers(boolean bracketTiers)
```

### isConvertOrderToMkt

```java
public boolean isConvertOrderToMkt()
```

### setConvertOrderToMkt

```java
public Layer1ApiProviderSupportedFeaturesBuilder setConvertOrderToMkt(boolean convertOrderToMkt)
```

### setMarketMode

```java
public Layer1ApiProviderSupportedFeaturesBuilder setMarketMode(boolean marketMode)
```

### isMarketMode

```java
public boolean isMarketMode()
```

### getSupportedLimitDurations

```java
public List<OrderDuration> getSupportedLimitDurations()
```

### getSupportedStopDurations

```java
public List<OrderDuration> getSupportedStopDurations()
```

### setSupportedOrderDurations

```java
public Layer1ApiProviderSupportedFeaturesBuilder setSupportedOrderDurations(List<OrderDuration> supportedOrderDurations)
```

### getSupportedStopOrders

```java
public List<OrderType> getSupportedStopOrders()
```

### isNegativeStopLimitOffset

```java
public boolean isNegativeStopLimitOffset()
```

### setNegativeStopLimitOffset

```java
public Layer1ApiProviderSupportedFeaturesBuilder setNegativeStopLimitOffset(boolean negativeStopLimitOffset)
```

### setSupportedStopOrders

```java
public Layer1ApiProviderSupportedFeaturesBuilder setSupportedStopOrders(List<OrderType> supportedStopOrders)
```

### setSupportedLimitDurations

```java
public Layer1ApiProviderSupportedFeaturesBuilder setSupportedLimitDurations(List<OrderDuration> supportedLimitDurations)
```

### setSupportedStopDurations

```java
public Layer1ApiProviderSupportedFeaturesBuilder setSupportedStopDurations(List<OrderDuration> supportedStopDurations)
```

### setBalanceSupported

```java
public Layer1ApiProviderSupportedFeaturesBuilder setBalanceSupported(boolean isBalanceSupported)
```

### isBalanceSupported

```java
public boolean isBalanceSupported()
```

### isTradingStartKnown

```java
public boolean isTradingStartKnown()
```

### setTradingStartKnown

```java
public Layer1ApiProviderSupportedFeaturesBuilder setTradingStartKnown(boolean tradingStartKnown)
```

### getKnownInstruments

```java
public List<SubscribeInfo> getKnownInstruments()
```

### setKnownInstruments

```java
public Layer1ApiProviderSupportedFeaturesBuilder setKnownInstruments(List<SubscribeInfo> knownInstruments)
```

### isExchangeUsedForSubscription

```java
public boolean isExchangeUsedForSubscription()
```

### setExchangeUsedForSubscription

```java
public Layer1ApiProviderSupportedFeaturesBuilder setExchangeUsedForSubscription(boolean exchangeUsedForSubscription)
```

### isTypeUsedForSubscription

```java
public boolean isTypeUsedForSubscription()
```

### setTypeUsedForSubscription

```java
public Layer1ApiProviderSupportedFeaturesBuilder setTypeUsedForSubscription(boolean typeUsedForSubscription)
```

### getPipsFunction

```java
public Function<SubscribeInfo, DefaultAndList<Double>> getPipsFunction()
```

### setPipsFunction

```java
public Layer1ApiProviderSupportedFeaturesBuilder setPipsFunction(Function<SubscribeInfo, DefaultAndList<Double>> pipsFunction)
```

### getSizeMultiplierFunction

```java
public Function<SubscribeInfo, DefaultAndList<Double>> getSizeMultiplierFunction()
```

### setSizeMultiplierFunction

```java
public Layer1ApiProviderSupportedFeaturesBuilder setSizeMultiplierFunction(Function<SubscribeInfo, DefaultAndList<Double>> sizeMultiplierFunction)
```

### getSubscriptionInfoFunction

```java
public Function<SubscribeInfo, String> getSubscriptionInfoFunction()
```

### setSubscriptionInfoFunction

```java
public Layer1ApiProviderSupportedFeaturesBuilder setSubscriptionInfoFunction(Function<SubscribeInfo, String> subscriptionInfoFunction)
```

### getHistoricalDataInfo

```java
public HistoricalDataInfo getHistoricalDataInfo()
```

### setHistoricalDataInfo

```java
public Layer1ApiProviderSupportedFeaturesBuilder setHistoricalDataInfo(HistoricalDataInfo historicalDataInfo)
```

### setLookupInfo

```java
public Layer1ApiProviderSupportedFeaturesBuilder setLookupInfo(LookupInfo lookupInfo)
```

### getLookupInfo

```java
public LookupInfo getLookupInfo()
```

### isDelayed

```java
public boolean isDelayed()
```

### setDelayed

```java
public Layer1ApiProviderSupportedFeaturesBuilder setDelayed(boolean isDelayed)
```

### setClientSideFeatures

```java
public Layer1ApiProviderSupportedFeaturesBuilder setClientSideFeatures(Set<Layer1ApiProviderSupportedFeatures.ClientSideFeature> clientSideFeatures)
```

### getClientSideFeatures

```java
public Set<Layer1ApiProviderSupportedFeatures.ClientSideFeature> getClientSideFeatures()
```

### setTradingSubscriptionSupported

```java
public Layer1ApiProviderSupportedFeaturesBuilder setTradingSubscriptionSupported(boolean isTradingSubscriptionSupported)
```

### getIsTradingSubscriptionSupported

```java
public boolean getIsTradingSubscriptionSupported()
```

### setSymbolsMappingFunction

```java
public Layer1ApiProviderSupportedFeaturesBuilder setSymbolsMappingFunction(Function<Set<InstrumentCoreInfo>, SymbolMappingInfo> symbolsMappingFunction)
```

### getSymbolsMappingFunction

```java
public Function<Set<InstrumentCoreInfo>, SymbolMappingInfo> getSymbolsMappingFunction()
```

### setReceiveCrossTradingStatusMessage

```java
public Layer1ApiProviderSupportedFeaturesBuilder setReceiveCrossTradingStatusMessage(boolean receiveCrossTradingStatusMessage)
```

### isReceiveCrossTradingStatusMessage

```java
public boolean isReceiveCrossTradingStatusMessage()
```

### isHistoricalAggregationDisabled

```java
public boolean isHistoricalAggregationDisabled()
```

### setHistoricalAggregationDisabled

```java
public Layer1ApiProviderSupportedFeaturesBuilder setHistoricalAggregationDisabled(boolean isHistoricalAggregationDisabled)
```