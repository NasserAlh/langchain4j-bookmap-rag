---
source_file: PriceRangeCalculationHelper.html
package: velox.api.layer1.utils
classes: PriceRangeCalculationHelper
methods: PRICESSTEPSMULTIPLIERS, MAXPOSITIVEPRICESSTEPSMULTIPLIERPOWER, MAXNEGATIVEPRICESSTEPSMULTIPLIERPOWER, PriceRangeCalculationHelper, getGoodNumbersCalculation, getGoodNumbersCalculation, getGoodNumbersPriceResult
---

# PriceRangeCalculationHelper

**Package:** velox.api.layer1.utils

**Type:** Class

**Inheritance:** java.lang.Object → PriceRangeCalculationHelper

## Description

Provides implementations of price range calculation

## Fields

### PRICES_STEPS_MULTIPLIERS

```java
public static final int[] PRICES_STEPS_MULTIPLIERS
```

### MAX_POSITIVE_PRICES_STEPS_MULTIPLIER_POWER

```java
public static final int MAX_POSITIVE_PRICES_STEPS_MULTIPLIER_POWER
```

### MAX_NEGATIVE_PRICES_STEPS_MULTIPLIER_POWER

```java
public static final int MAX_NEGATIVE_PRICES_STEPS_MULTIPLIER_POWER
```

## Constructors

### PriceRangeCalculationHelper

```java
public PriceRangeCalculationHelper()
```

## Methods

### getGoodNumbersCalculation

```java
public static Layer1PriceAxisRangeCalculatable.ResultPriceAxisInfo getGoodNumbersCalculation(double minValue, double maxValue, double linesCount)
```

Provides an implementation of price range calculation that displays only "good" numbers on axis (1, 2, 5, 10, 25, 50 * 10^2n and their multipliers)

**Parameters:**
- `minValue` - Minimal value of indicator on chart range
- `maxValue` - Maximum value of indicator on chart range
- `linesCount` - Number of visible horizontal lines, that can display labels. Note that this value is double. Value 2.3 will means 2 visible lines, and 3/10 of distance between lines as empty space **to the top**

### getGoodNumbersCalculation

```java
public static Map<String, Layer1PriceAxisRangeCalculatable.ResultPriceAxisInfo> getGoodNumbersCalculation(double linesCount, Map<String, Layer1PriceAxisRangeCalculatable.InputPriceAxisInfo> inputInfo)
```

Invokes `getGoodNumbersCalculation(double, double, double)` for every map entry

### getGoodNumbersPriceResult

```java
public static CalculatePricesResult getGoodNumbersPriceResult(double minValue, double maxValue, double linesCount)
```

Same as `getGoodNumbersCalculation(double, double, double)`, different return type  
You don't need to use this method unless you want to see advanced parameters of best solution

**Parameters:**
- `minValue` - Minimal value of indicator on chart range
- `maxValue` - Maximum value of indicator on chart range
- `linesCount` - Number of visible horizontal lines, that can display labels. Note that this value is double. Value 2.3 will means 2 visible lines, and 3/10 of distance between lines as empty space **to the top**

**Returns:** Best calculated result or null if non was calculated