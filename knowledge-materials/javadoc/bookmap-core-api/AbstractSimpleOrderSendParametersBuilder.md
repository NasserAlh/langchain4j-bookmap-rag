---
source_file: AbstractSimpleOrderSendParametersBuilder.html
package: velox.api.layer1.data
classes: AbstractSimpleOrderSendParametersBuilder
methods: limitPrice, stopPrice, takeProfitOffset, additionalTakeProfitTiers, stopLossOffset, stopLossTrailingStep, additionalStopLossTiers, takeProfitClientId, stopLossClientId, trailingStep, doNotIncrease, reversingPositionHint, closingPositionHint, build, toString, getLimitPrice, setLimitPrice, getStopPrice, setStopPrice, getTakeProfitOffset
total_methods: 43
---

# AbstractSimpleOrderSendParametersBuilder

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.data.AbstractSingleOrderSendParametersBuilder<AbstractSimpleOrderSendParametersBuilder<T>> → AbstractSimpleOrderSendParametersBuilder<T>

**Direct Known Subclasses:** SimpleOrderSendParametersBuilder

```java
public abstract class AbstractSimpleOrderSendParametersBuilder<T extends AbstractSimpleOrderSendParametersBuilder<T>>
extends AbstractSingleOrderSendParametersBuilder<AbstractSimpleOrderSendParametersBuilder<T>>
```

## Fields

### limitPrice
```java
protected double limitPrice
```

### stopPrice
```java
protected double stopPrice
```

### takeProfitOffset
```java
protected int takeProfitOffset
```

### additionalTakeProfitTiers
```java
protected List<BracketTier> additionalTakeProfitTiers
```

### stopLossOffset
```java
protected int stopLossOffset
```

### stopLossTrailingStep
```java
protected int stopLossTrailingStep
```

### additionalStopLossTiers
```java
protected List<BracketTier> additionalStopLossTiers
```

### takeProfitClientId
```java
protected String takeProfitClientId
```

### stopLossClientId
```java
protected String stopLossClientId
```

### trailingStep
```java
protected int trailingStep
```

### doNotIncrease
```java
protected boolean doNotIncrease
```

### reversingPositionHint
```java
protected boolean reversingPositionHint
```

### closingPositionHint
```java
protected boolean closingPositionHint
```

## Methods

### build
```java
public SimpleOrderSendParameters build()
```

**Overrides:** `build()` in class `AbstractSingleOrderSendParametersBuilder<AbstractSimpleOrderSendParametersBuilder<T extends AbstractSimpleOrderSendParametersBuilder<T>>>`

### toString
```java
public String toString()
```

**Overrides:** `toString()` in class `AbstractSingleOrderSendParametersBuilder<AbstractSimpleOrderSendParametersBuilder<T extends AbstractSimpleOrderSendParametersBuilder<T>>>`

### getLimitPrice
```java
public double getLimitPrice()
```

### setLimitPrice
```java
public T setLimitPrice(double limitPrice)
```

### getStopPrice
```java
public double getStopPrice()
```

### setStopPrice
```java
public T setStopPrice(double stopPrice)
```

### getTakeProfitOffset
```java
public int getTakeProfitOffset()
```

### setTakeProfitOffset
```java
public T setTakeProfitOffset(int takeProfitOffset)
```

### getAdditionalTakeProfitTiers
```java
public List<BracketTier> getAdditionalTakeProfitTiers()
```

### setAdditionalTakeProfitTiers
```java
public T setAdditionalTakeProfitTiers(List<BracketTier> additionalTakeProfitTiers)
```

### getStopLossOffset
```java
public int getStopLossOffset()
```

### setStopLossOffset
```java
public T setStopLossOffset(int stopLossOffset)
```

### getStopLossTrailingStep
```java
public int getStopLossTrailingStep()
```

### setStopLossTrailingStep
```java
public T setStopLossTrailingStep(int stopLossTrailingStep)
```

### getAdditionalStopLossTiers
```java
public List<BracketTier> getAdditionalStopLossTiers()
```

### setAdditionalStopLossTiers
```java
public T setAdditionalStopLossTiers(List<BracketTier> additionalStopLossTiers)
```

### getTakeProfitClientId
```java
public String getTakeProfitClientId()
```

### setTakeProfitClientId
```java
public T setTakeProfitClientId(String takeProfitClientId)
```

### getStopLossClientId
```java
public String getStopLossClientId()
```

### setStopLossClientId
```java
public T setStopLossClientId(String stopLossClientId)
```

### getTrailingStep
```java
public int getTrailingStep()
```

### setTrailingStep
```java
public T setTrailingStep(int trailingStep)
```

### isDoNotIncrease
```java
public boolean isDoNotIncrease()
```

### setDoNotIncrease
```java
public T setDoNotIncrease(boolean doNotIncrease)
```

### isReversingPositionHint
```java
public boolean isReversingPositionHint()
```

### setReversingPositionHint
```java
public T setReversingPositionHint(boolean reversingPositionHint)
```

### isClosingPositionHint
```java
public boolean isClosingPositionHint()
```

### setClosingPositionHint
```java
public T setClosingPositionHint(boolean closingPositionHint)
```

### getSizeMultiplier
```java
@Deprecated
public double getSizeMultiplier()
```

**Deprecated**

### setSizeMultiplier
```java
@Deprecated
public T setSizeMultiplier(double sizeMultiplier)
```

**Deprecated**

## Inherited Methods

**From class `velox.api.layer1.data.AbstractSingleOrderSendParametersBuilder`:**
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

**From class `java.lang.Object`:**
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