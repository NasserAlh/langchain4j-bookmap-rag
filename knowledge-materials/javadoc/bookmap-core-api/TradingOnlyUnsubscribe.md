---
source_file: TradingOnlyUnsubscribe.html
package: velox.api.layer1.messages.tradingsubscription
classes: TradingOnlyUnsubscribe
methods: alias, TradingOnlyUnsubscribe
---

# TradingOnlyUnsubscribe

**Package:** velox.api.layer1.messages.tradingsubscription

**Type:** Class

**Inheritance:** java.lang.Object → TradingOnlyUnsubscribe

## Description

Provider will receive this message if Bookmap need Trading Subscription (without data). Provider should set `Layer1ApiProviderSupportedFeatures.isTradingSubscriptionSupported` flag to receive it. This type of subscription is needed in case of cross trading.

## Fields

### alias

```java
public final String alias
```

## Constructors

### TradingOnlyUnsubscribe

```java
public TradingOnlyUnsubscribe(String alias)
```