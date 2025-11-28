---
source_file: AxisRules.html
package: velox.api.layer1.simplified
classes: AxisRules
methods: AxisRules, getForcedMin, setForcedMin, getForcedMax, setForcedMax, getIncludedMin, setIncludedMin, getIncludedMax, setIncludedMax, getMargin, setMargin, isSymmetrical, setSymmetrical, apply
---

# AxisRules

**Package:** velox.api.layer1.simplified

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.simplified.AxisRules

## Description

This class describes axis range selection rules.

## Constructors

### AxisRules

```java
public AxisRules()
```

## Methods

### getForcedMin

```java
double getForcedMin()
```

### setForcedMin

```java
void setForcedMin(double forcedMin)
```

### getForcedMax

```java
double getForcedMax()
```

### setForcedMax

```java
void setForcedMax(double forcedMax)
```

### getIncludedMin

```java
double getIncludedMin()
```

### setIncludedMin

```java
void setIncludedMin(double includedMin)
```

### getIncludedMax

```java
double getIncludedMax()
```

### setIncludedMax

```java
void setIncludedMax(double includedMax)
```

### getMargin

```java
double getMargin()
```

### setMargin

```java
void setMargin(double margin)
```

### isSymmetrical

```java
boolean isSymmetrical()
```

### setSymmetrical

```java
void setSymmetrical(boolean symmetrical)
```

### apply

```java
org.apache.commons.lang3.tuple.Pair<Double, Double> apply(double min, double max)
```

## Inherited Methods

**From java.lang.Object:**
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