---
source_file: IndicatorColorScheme.ColorIntervalResponse.html
package: velox.api.layer1.messages.indicators
classes: IndicatorColorScheme.ColorIntervalResponse
methods: colors, dots, ColorIntervalResponse
---

# IndicatorColorScheme.ColorIntervalResponse

**Package:** velox.api.layer1.messages.indicators

**Type:** Class

**Enclosing Interface:** `IndicatorColorScheme`

**Inheritance:** `java.lang.Object` → `IndicatorColorScheme.ColorIntervalResponse`

## Description

Describes all color changes in given interval.

## Fields

### colors

```java
public final String[] colors
```

### dots

```java
public final double[] dots
```

## Constructors

### ColorIntervalResponse

```java
public ColorIntervalResponse(String[] colors, double[] dots)
```

0th color is used for all values <= 0th dot  
1th color is used for all values > 0th dot and <= 1th dot  
...  
last color is used for all values > last dot  
size of dots list = size of colors list - 1

**Parameters:**
- `colors` - 
- `dots` -