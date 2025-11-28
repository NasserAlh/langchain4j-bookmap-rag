---
source_file: IndicatorColorScheme.html
package: velox.api.layer1.messages.indicators
classes: IndicatorColorScheme
methods: getColorFullName, getColors, getColorFor, getColorIntervalsList, getMainColorName, getDefaultColor
---

# IndicatorColorScheme

**Package:** velox.api.layer1.messages.indicators

**Type:** Interface

## Description

Describes colors given to certain indicator values when indicator is drawn using default lines on bottom panel.

## Nested Classes

- `IndicatorColorScheme.ColorDescription`
- `IndicatorColorScheme.ColorIntervalResponse` - Describes all color changes in given interval

## Methods

### getColorFullName

```java
static String getColorFullName(String userName, Class<?> strategyClass)
```

### getColors

```java
IndicatorColorScheme.ColorDescription[] getColors()
```

Return all used colors description.

### getColorFor

```java
String getColorFor(Double value)
```

Return color that should be used for this value.

### getColorIntervalsList

```java
IndicatorColorScheme.ColorIntervalResponse getColorIntervalsList(double valueFrom, double valueTo)
```

**Parameters:**
- `valueFrom` - 
- `valueTo` - 

**Returns:** intervals for all colors that should be used for values in interval [valueFrom, valueTo]

### getMainColorName

```java
default String getMainColorName()
```

### getDefaultColor

```java
default Color getDefaultColor()
```