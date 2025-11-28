---
source_file: ScreenSpaceCanvas.RelativeVerticalCoordinate.html
package: velox.api.layer1.layers.strategies.interfaces
classes: ScreenSpaceCanvas.RelativeVerticalCoordinate
methods: VERTICALDATAZERO, VERTICALPIXELZERO, base, dataOffsetY, pixelsOffsetY, RelativeVerticalCoordinate, compose, toString
---

# ScreenSpaceCanvas.RelativeVerticalCoordinate

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Class

**Enclosing Interface:** `ScreenSpaceCanvas`

**Inheritance:**
- Extends: `java.lang.Object`
- Implements: `ScreenSpaceCanvas.VerticalCoordinate`

**Direct Known Subclasses:** `ScreenSpaceCanvas.RelativeDataVerticalCoordinate`, `ScreenSpaceCanvas.RelativePixelVerticalCoordinate`

## Description

Vertical coordinate offset in a certain way relative to base point

## Fields

### VERTICAL_DATA_ZERO

```java
public static final ScreenSpaceCanvas.CompositeVerticalCoordinate VERTICAL_DATA_ZERO
```

Corresponds to origin point of data

### VERTICAL_PIXEL_ZERO

```java
public static final ScreenSpaceCanvas.CompositeVerticalCoordinate VERTICAL_PIXEL_ZERO
```

Corresponds to bottom-most pixel of canvas

### base

```java
public final ScreenSpaceCanvas.VerticalCoordinate base
```

### dataOffsetY

```java
public final double dataOffsetY
```

### pixelsOffsetY

```java
public final int pixelsOffsetY
```

## Constructors

### RelativeVerticalCoordinate

```java
protected RelativeVerticalCoordinate(ScreenSpaceCanvas.VerticalCoordinate base, double dataOffsetY, int pixelsOffsetY)
```

## Methods

### compose

```java
public ScreenSpaceCanvas.CompositeVerticalCoordinate compose()
```

Convert to `ScreenSpaceCanvas.CompositeVerticalCoordinate`

**Specified by:** `compose` in interface `ScreenSpaceCanvas.VerticalCoordinate`

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`