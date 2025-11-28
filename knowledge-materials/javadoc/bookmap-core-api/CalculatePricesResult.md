---
source_file: CalculatePricesResult.html
package: velox.api.layer1.utils
classes: CalculatePricesResult
methods: labels, firstValue, lastValue, multiplier, emptyTop, emptyBottom, priceStep, CalculatePricesResult, getScore, isBetterThan, toString
---

# CalculatePricesResult

**Package:** velox.api.layer1.utils

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.utils.CalculatePricesResult

## Fields

### labels

```java
public String[] labels
```

### firstValue

```java
public double firstValue
```

Price value corresponding to bottom pixel of chart

### lastValue

```java
public double lastValue
```

Price value corresponding to top pixel of chart

### multiplier

```java
public double multiplier
```

### emptyTop

```java
public double emptyTop
```

Represents the height of the empty space between the chart top and max value of the indicator

### emptyBottom

```java
public double emptyBottom
```

Represents the height of the empty space between the chart bottom and min value of the indicator

### priceStep

```java
public double priceStep
```

## Constructors

### CalculatePricesResult

```java
public CalculatePricesResult()
```

## Methods

### getScore

```java
public double getScore()
```

Calculates the score based on empty space size. More empty space the bigger the score (worse)

**Returns:** score

### isBetterThan

```java
public boolean isBetterThan(CalculatePricesResult other)
```

Compares which indicator range is better based on score (unused empty space size). Less empty space is better.

**Parameters:**
- `other` - CalculatePricesResult to compare with

**Returns:** true if this result is better than other

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`