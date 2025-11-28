---
source_file: SimpleOrderSendParameters.html
package: velox.api.layer1.data
classes: SimpleOrderSendParameters
methods: SimpleOrderSendParameters.PriceFormatter, limitPrice, stopPrice, takeProfitOffset, additionalTakeProfitTiers, stopLossOffset, stopLossTrailingStep, additionalStopLossTiers, takeProfitClientId, stopLossClientId, trailingStep, doNotIncrease, reversingPositionHint, closingPositionHint, sizeMultiplier, SimpleOrderSendParameters, SimpleOrderSendParameters, SimpleOrderSendParameters, SimpleOrderSendParameters, SimpleOrderSendParameters
total_methods: 27
---

# SimpleOrderSendParameters

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.data.SingleOrderSendParameters → SimpleOrderSendParameters

**All Implemented Interfaces:** MultiAccountAware, OrderSendParameters

## Description

Simple order. (market, stop, limit or stop-limit)

## Nested Classes

### SimpleOrderSendParameters.PriceFormatter

**Deprecated.**

## Fields

### limitPrice

```java
public final double limitPrice
```

Limit price for limit and stop-limit orders, NaN for market orders

### stopPrice

```java
public final double stopPrice
```

Stop price for stop and stop-limit orders, NaN for market and limit orders

### takeProfitOffset

```java
public final int takeProfitOffset
```

Offset for take profit order (for brackets). 0 if not needed. If `additionalTakeProfitTiers` contains any tiers, this defines the first tier (size will be whatever remains after the `additionalTakeProfitTiers`).

### additionalTakeProfitTiers

```java
public final List<BracketTier> additionalTakeProfitTiers
```

If not empty - describes take profit tiers of the bracket after the first (which is described by `takeProfitOffset`).

### stopLossOffset

```java
public final int stopLossOffset
```

Offset for stop loss order (for brackets). 0 if not needed. If `additionalStopLossTiers` contains any tiers, this defines the first tier (size will be whatever remains after the `additionalStopLossTiers`).

### stopLossTrailingStep

```java
public final int stopLossTrailingStep
```

Stop loss trailing step. 0 for non-trailing stop loss.

### additionalStopLossTiers

```java
public final List<BracketTier> additionalStopLossTiers
```

If not empty - describes stop loss tiers of the bracket after the first (which is described by `stopLossOffset`).

### takeProfitClientId

```java
public final String takeProfitClientId
```

Client id for take profit order, similar too `SingleOrderSendParameters.clientId`

### stopLossClientId

```java
public final String stopLossClientId
```

Client id for stop loss order, similar too `SingleOrderSendParameters.clientId`

### trailingStep

```java
public final int trailingStep
```

Trailing step for this order, if it's a stop order. 0 for non-trailing orders

### doNotIncrease

```java
public final boolean doNotIncrease
```

Do non increase flag - if set it should not be possible to increase order size. Handled inside Bookmap L1 stack, no need to worry about it when writing L0 provider.

### reversingPositionHint

```java
public final boolean reversingPositionHint
```

Indicates that intention is to reverse position. Some platforms might provide you a better way to do it, in which case you can use that instead. Provider is not required to support this.

### closingPositionHint

```java
public final boolean closingPositionHint
```

Indicates that intention is to close position. Some platforms might provide you a better way to do it, in which case you can use that instead. Provider is not required to support this.

### sizeMultiplier

```java
@Deprecated
public double sizeMultiplier
```

**Deprecated.**

Was added by mistake. Please don't use it. Kept for compatibility to avoid introducing new API version.

## Constructors

### SimpleOrderSendParameters

```java
@Deprecated
public SimpleOrderSendParameters(String alias, boolean isBuy, int size, OrderDuration duration, double limitPrice, double stopPrice)
```

**Deprecated.**

Don't use this directly - use `SimpleOrderSendParametersBuilder` instead.

### SimpleOrderSendParameters

```java
@Deprecated
public SimpleOrderSendParameters(String alias, boolean isBuy, int size, OrderDuration duration, int trailingStep, double limitPrice, double stopPrice)
```

**Deprecated.**

Don't use this directly - use `SimpleOrderSendParametersBuilder` instead.

### SimpleOrderSendParameters

```java
@Deprecated
public SimpleOrderSendParameters(String alias, boolean isBuy, int size, OrderDuration duration, double limitPrice, double stopPrice, int takeProfitOffset, int stopLossOffset, int stopLossTrailingStep, int trailingStep, boolean doNotIncrease)
```

**Deprecated.**

Don't use this directly - use `SimpleOrderSendParametersBuilder` instead.

### SimpleOrderSendParameters

```java
@Deprecated
public SimpleOrderSendParameters(String alias, boolean isBuy, int size, OrderDuration duration, String clientId, double limitPrice, double stopPrice, int takeProfitOffset, int stopLossOffset, int stopLossTrailingStep, int trailingStep, boolean doNotIncrease, boolean reversingPositionHint, boolean closingPositionHint)
```

**Deprecated.**

Don't use this directly - use `SimpleOrderSendParametersBuilder` instead.

### SimpleOrderSendParameters

```java
@Deprecated
public SimpleOrderSendParameters(String alias, boolean isBuy, int size, OrderDuration duration, String clientId, double limitPrice, double stopPrice, int takeProfitOffset, int stopLossOffset, int stopLossTrailingStep, int trailingStep, boolean doNotIncrease)
```

**Deprecated.**

Don't use this directly - use `SimpleOrderSendParametersBuilder` instead.

### SimpleOrderSendParameters

```java
@Deprecated
public SimpleOrderSendParameters(String alias, boolean isBuy, int size, OrderDuration duration, double limitPrice, double stopPrice, double sizeMultiplier)
```

**Deprecated.**

sizeMultiplier parameter was added to API due to a mistake, please use a version without it

### SimpleOrderSendParameters

```java
@Deprecated
public SimpleOrderSendParameters(String alias, boolean isBuy, int size, OrderDuration duration, int trailingStep, double limitPrice, double stopPrice, double sizeMultiplier)
```

**Deprecated.**

sizeMultiplier parameter was added to API due to a mistake, please use a version without it

### SimpleOrderSendParameters

```java
@Deprecated
public SimpleOrderSendParameters(String alias, boolean isBuy, int size, OrderDuration duration, double limitPrice, double stopPrice, int takeProfitOffset, int stopLossOffset, int stopLossTrailingStep, int trailingStep, boolean doNotIncrease, double sizeMultiplier)
```

**Deprecated.**

sizeMultiplier parameter was added to API due to a mistake, please use a version without it

### SimpleOrderSendParameters

```java
@Deprecated
public SimpleOrderSendParameters(String alias, boolean isBuy, int size, OrderDuration duration, String clientId, double limitPrice, double stopPrice, int takeProfitOffset, int stopLossOffset, int stopLossTrailingStep, int trailingStep, boolean doNotIncrease, double sizeMultiplier)
```

**Deprecated.**

sizeMultiplier parameter was added to API due to a mistake, please use a version without it

## Methods

### toBuilder

```java
public AbstractSimpleOrderSendParametersBuilder<SimpleOrderSendParametersBuilder> toBuilder()
```

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `SingleOrderSendParameters`

### representation

```java
@Deprecated
public String representation(SimpleOrderSendParameters.PriceFormatter formatter, double sizeMultiplier)
```

**Deprecated.** Use `OrderRepresentationHelper.textRepresentation(velox.api.layer1.data.SimpleOrderSendParameters, velox.api.layer1.common.helper.OrderRepresentationHelper.PriceFormatter, double)` instead.

**Parameters:**
- `formatter` - function converting price to string
- `sizeMultiplier` - size multiplier for order size to be displayed

**Returns:** string representation of the order