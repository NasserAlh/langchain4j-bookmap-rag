---
source_file: BoundsInfo.html
package: velox.api.layer1.messages.indicators
classes: BoundsInfo
methods: initialValue, minValue, maxValue, BoundsInfo, toString
---

# BoundsInfo

**Package:** velox.api.layer1.messages.indicators

**Type:** Class

**Inheritance:** java.lang.Object → BoundsInfo

## Fields

### initialValue

```java
public Double initialValue
```

Value if no data is present. If null, 0 is used.

### minValue

```java
public Double minValue
```

Can be null to indicate no predetermined minimum value.

### maxValue

```java
public Double maxValue
```

Can be null to indicate no predetermined maximum value.

## Constructors

### BoundsInfo

```java
public BoundsInfo(Double initialValue, Double minValue, Double maxValue)
```

## Methods

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`

**Methods inherited from class java.lang.Object:**
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