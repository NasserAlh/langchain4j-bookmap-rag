---
source_file: AxisGroup.html
package: velox.api.layer1.simplified
classes: AxisGroup
methods: AxisGroup, setAxisRules, add, remove, getIndicators, getAxisRules
---

# AxisGroup

**Package:** velox.api.layer1.simplified

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.simplified.AxisGroup

## Description

Group of indicators that have same value ranges.

## Constructors

### AxisGroup

```java
public AxisGroup()
```

## Methods

### setAxisRules

```java
public void setAxisRules(AxisRules axisRules)
```

Set rules for selecting indicators range. This will be applied to all indicators in a group.

**Parameters:**
- `axisRules` - Object describing the rules

### add

```java
public void add(Indicator indicator)
```

Add indicator to a group

**Parameters:**
- `indicator` - Indicator to add

### remove

```java
public void remove(Indicator indicator)
```

Remove indicator from axis group

**Parameters:**
- `indicator` - Indicator to remove

### getIndicators

```java
public List<Indicator> getIndicators()
```

### getAxisRules

```java
public AxisRules getAxisRules()
```