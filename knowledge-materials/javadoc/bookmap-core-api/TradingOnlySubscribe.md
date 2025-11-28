---
source_file: TradingOnlySubscribe.html
package: velox.api.layer1.messages.tradingsubscription
classes: TradingOnlySubscribe
methods: subscribeInfo, TradingOnlySubscribe
---

# TradingOnlySubscribe

**Package:** velox.api.layer1.messages.tradingsubscription

**Type:** Class

**Inheritance:** java.lang.Object → TradingOnlySubscribe

## Description

Provider will receive this message if Bookmap need Trading Subscription (without data). Provider should set `Layer1ApiProviderSupportedFeatures.isTradingSubscriptionSupported` flag to receive it. This type of subscription is needed in case of cross trading.

Providers are expected to start sending trading related information (positions, orders, etc.) after receiving this message.

In case of cryptocurrencies SubscribeInfoCrypto instead of subscribeInfo will be sent.

## Fields

### subscribeInfo

```java
public final SubscribeInfo subscribeInfo
```

## Constructors

### TradingOnlySubscribe

```java
public TradingOnlySubscribe(SubscribeInfo subscribeInfo)
```