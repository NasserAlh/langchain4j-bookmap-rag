---
source_file: SimpleOrderSendParametersBuilder.html
package: velox.api.layer1.data
classes: SimpleOrderSendParametersBuilder
methods: SimpleOrderSendParametersBuilder, SimpleOrderSendParametersBuilder, SimpleOrderSendParametersBuilder, SimpleOrderSendParametersBuilder
---

# SimpleOrderSendParametersBuilder

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** `java.lang.Object` → `velox.api.layer1.data.AbstractSingleOrderSendParametersBuilder<AbstractSimpleOrderSendParametersBuilder<T>>` → `velox.api.layer1.data.AbstractSimpleOrderSendParametersBuilder<SimpleOrderSendParametersBuilder>` → `SimpleOrderSendParametersBuilder`

## Description

Builder for [`SimpleOrderSendParameters`](SimpleOrderSendParameters.html)

## Fields

**Inherited from `velox.api.layer1.data.AbstractSimpleOrderSendParametersBuilder`:**
- `additionalStopLossTiers`
- `additionalTakeProfitTiers`
- `closingPositionHint`
- `doNotIncrease`
- `limitPrice`
- `reversingPositionHint`
- `stopLossClientId`
- `stopLossOffset`
- `stopLossTrailingStep`
- `stopPrice`
- `takeProfitClientId`
- `takeProfitOffset`
- `trailingStep`

**Inherited from `velox.api.layer1.data.AbstractSingleOrderSendParametersBuilder`:**
- `accountId`
- `alias`
- `clientId`
- `duration`
- `isBuy`
- `size`

## Constructors

### SimpleOrderSendParametersBuilder

```java
public SimpleOrderSendParametersBuilder(String alias, boolean isBuy, int size)
```

### SimpleOrderSendParametersBuilder

```java
@Deprecated
public SimpleOrderSendParametersBuilder(String alias, boolean isBuy, int size, OrderDuration duration, String clientId, double limitPrice, double stopPrice, int takeProfitOffset, int stopLossOffset, int stopLossTrailingStep, int trailingStep, boolean doNotIncrease, boolean reversingPositionHint, boolean closingPositionHint)
```

**Deprecated**

### SimpleOrderSendParametersBuilder

```java
@Deprecated
public SimpleOrderSendParametersBuilder(String alias, boolean isBuy, int size, OrderDuration duration, String clientId, double limitPrice, double stopPrice, int takeProfitOffset, int stopLossOffset, int stopLossTrailingStep, int trailingStep, boolean doNotIncrease)
```

**Deprecated**

### SimpleOrderSendParametersBuilder

```java
@Deprecated
public SimpleOrderSendParametersBuilder(String alias, boolean isBuy, int size, OrderDuration duration, String clientId, double limitPrice, double stopPrice, int takeProfitOffset, int stopLossOffset, int stopLossTrailingStep, int trailingStep, boolean doNotIncrease, double sizeMultiplier)
```

**Deprecated**

sizeMultiplier parameter was added to API due to a mistake, please use a version without it

## Methods

**Inherited from `velox.api.layer1.data.AbstractSimpleOrderSendParametersBuilder`:**
- `build()`
- `getAdditionalStopLossTiers()`
- `getAdditionalTakeProfitTiers()`
- `getLimitPrice()`
- `getSizeMultiplier()`
- `getStopLossClientId()`
- `getStopLossOffset()`
- `getStopLossTrailingStep()`
- `getStopPrice()`
- `getTakeProfitClientId()`
- `getTakeProfitOffset()`
- `getTrailingStep()`
- `isClosingPositionHint()`
- `isDoNotIncrease()`
- `isReversingPositionHint()`
- `setAdditionalStopLossTiers(List)`
- `setAdditionalTakeProfitTiers(List)`
- `setClosingPositionHint(boolean)`
- `setDoNotIncrease(boolean)`
- `setLimitPrice(double)`
- `setReversingPositionHint(boolean)`
- `setSizeMultiplier(double)`
- `setStopLossClientId(String)`
- `setStopLossOffset(int)`
- `setStopLossTrailingStep(int)`
- `setStopPrice(double)`
- `setTakeProfitClientId(String)`
- `setTakeProfitOffset(int)`
- `setTrailingStep(int)`
- `toString()`

**Inherited from `velox.api.layer1.data.AbstractSingleOrderSendParametersBuilder`:**
- `getAccountId()`
- `getAlias()`
- `getClientId()`
- `getDuration()`
- `getSize()`
- `isBuy()`
- `setAccountId(String)`
- `setAlias(String)`
- `setBuy(boolean)`
- `setClientId(String)`
- `setDuration(OrderDuration)`
- `setSize(int)`

**Inherited from `java.lang.Object`:**
- `clone()`
- `equals(Object)`
- `finalize()`
- `getClass()`
- `hashCode()`
- `notify()`
- `notifyAll()`
- `wait()`
- `wait(long)`
- `wait(long, int)`