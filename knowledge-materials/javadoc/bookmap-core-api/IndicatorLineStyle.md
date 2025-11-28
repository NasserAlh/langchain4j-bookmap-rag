---
source_file: IndicatorLineStyle.html
package: velox.api.layer1.messages.indicators
classes: IndicatorLineStyle
methods: DEFAULT, NONE, SHORTDASHES, SHORTDASHESWIDELEFTNARROWRIGHT, mainLineStyleMask, mainLineStyleMultiplier, mainLineWidth, rightLineStyleMask, rightLineStyleMultiplier, rightLineWidth, IndicatorLineStyle, IndicatorLineStyle
---

# IndicatorLineStyle

**Package:** velox.api.layer1.messages.indicators

**Type:** Class

**Inheritance:** java.lang.Object → IndicatorLineStyle

## Description

This class allows you to specify how the indicator line will look.

There are two parts: mainLine (3 fields) and rightLine (3 fields). Main line is the part to the left of timeline and rightLine is part to the right.

Each part is described by 3 parts:

- **mask** - single short int (2 bytes) where each bit can be 1 (line is present) or 0 (line is not present). e.g. 0xF0F0 (binary 1111000011110000) will make line look like repeated "----    ----    " segments
- **multiplier** - scales the mask describing how many pixels each bit represents
- **lineWidth** - just a width of the line in pixels

## Fields

### DEFAULT

```java
public static final IndicatorLineStyle DEFAULT
```

Similar to most lines in Bookmap

### NONE

```java
public static final IndicatorLineStyle NONE
```

No line. Use if you want to display icons only

### SHORT_DASHES

```java
public static final IndicatorLineStyle SHORT_DASHES
```

Short dashes of the same style both to the left and to the right of timeline

### SHORT_DASHES_WIDE_LEFT_NARROW_RIGHT

```java
public static final IndicatorLineStyle SHORT_DASHES_WIDE_LEFT_NARROW_RIGHT
```

Mostly a demo to show how to configure more complex style.

### mainLineStyleMask

```java
public final short mainLineStyleMask
```

Mask for the main line style. See this class for more information

### mainLineStyleMultiplier

```java
public final short mainLineStyleMultiplier
```

Multiplier for the main line style. See this class for more information

### mainLineWidth

```java
public final int mainLineWidth
```

Main line width. See this class for more information

### rightLineStyleMask

```java
public final short rightLineStyleMask
```

Mask for the right line style. See this class for more information

### rightLineStyleMultiplier

```java
public final short rightLineStyleMultiplier
```

Multiplier for the right line style. See this class for more information

### rightLineWidth

```java
public final int rightLineWidth
```

Right line width. See this class for more information

## Constructors

### IndicatorLineStyle

```java
public IndicatorLineStyle()
```

Creates default line style (similar to most lines in Bookmap)

### IndicatorLineStyle

```java
public IndicatorLineStyle(short mainLineStyleMask, short mainLineStyleMultiplier, int mainLineWidth, short rightLineStyleMask, short rightLineStyleMultiplier, int rightLineWidth)
```

Creates a custom line style. See descriptions for individual fields and this class documentation for parameter descriptions.

**Parameters:**
- `mainLineStyleMask` - Mask for the main line style
- `mainLineStyleMultiplier` - Multiplier for the main line style
- `mainLineWidth` - Main line width
- `rightLineStyleMask` - Mask for the right line style
- `rightLineStyleMultiplier` - Multiplier for the right line style
- `rightLineWidth` - Right line width