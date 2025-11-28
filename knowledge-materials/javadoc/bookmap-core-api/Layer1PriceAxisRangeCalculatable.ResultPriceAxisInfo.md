---
source_file: Layer1PriceAxisRangeCalculatable.ResultPriceAxisInfo.html
package: velox.api.layer1.layers.strategies.interfaces
classes: Layer1PriceAxisRangeCalculatable.ResultPriceAxisInfo
methods: minValue, maxValue, labels, ResultPriceAxisInfo, toString
---

# Layer1PriceAxisRangeCalculatable.ResultPriceAxisInfo

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Class

**Enclosing Interface:** `Layer1PriceAxisRangeCalculatable`

**Inheritance:** `java.lang.Object` → `Layer1PriceAxisRangeCalculatable.ResultPriceAxisInfo`

## Fields

### minValue

```java
public final double minValue
```

### maxValue

```java
public final double maxValue
```

### labels

```java
public final String[] labels
```

## Constructors

### ResultPriceAxisInfo

```java
public ResultPriceAxisInfo(double minValue, double maxValue, String[] labels)
```

**Parameters:**
- `minValue` - Value corresponding to bottom pixel of chart
- `maxValue` - Value corresponding to top pixel of chart
- `labels` - Of horizontal lines, from bottom to top

Example: for 2.3 lines count minValue = 0, maxValue = 23 and labels 10 and 20 is correct

## Methods

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`

**Methods inherited from class `java.lang.Object`:**
- `clone`
- `equals`
- `finalize`
- `getClass`
- `hashCode`
- `notify`
- `notifyAll`
- `wait`
- `wait(long)`
- `wait(long, int)`