---
source_file: IndicatorDisplayLogic.html
package: velox.api.layer1.messages.indicators
classes: IndicatorDisplayLogic
methods: limitsCalculator, valuesFormatter, IndicatorDisplayLogic, setLimitsCalculator, setValuesFormatter
---

# IndicatorDisplayLogic

**Package:** velox.api.layer1.messages.indicators

**Type:** Class

**Inheritance:** java.lang.Object → IndicatorDisplayLogic

## Description

Bundle for display logic components. If some field is null, default logic will be used for that component.

## Fields

### limitsCalculator

```java
public LimitsCalculator limitsCalculator
```

### valuesFormatter

```java
public ValuesFormatter valuesFormatter
```

## Constructors

### IndicatorDisplayLogic

```java
public IndicatorDisplayLogic()
```

## Methods

### setLimitsCalculator

```java
public IndicatorDisplayLogic setLimitsCalculator(LimitsCalculator limitsCalculator)
```

### setValuesFormatter

```java
public IndicatorDisplayLogic setValuesFormatter(ValuesFormatter valuesFormatter)
```

**Methods inherited from class java.lang.Object:**
- `clone()`
- `equals(Object)`
- `finalize()`
- `getClass()`
- `hashCode()`
- `notify()`
- `notifyAll()`
- `toString()`
- `wait()`
- `wait(long)`
- `wait(long, int)`