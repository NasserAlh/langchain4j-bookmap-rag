---
source_file: ScreenSpaceCanvas.RelativeHorizontalCoordinate.html
package: velox.api.layer1.layers.strategies.interfaces
classes: ScreenSpaceCanvas.RelativeHorizontalCoordinate
methods: HORIZONTALDATAZERO, HORIZONTALPIXELZERO, base, pixelsOffsetX, timeOffsetX, RelativeHorizontalCoordinate, compose, toString
---

# ScreenSpaceCanvas.RelativeHorizontalCoordinate

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Class (static)

**Enclosing Interface:** ScreenSpaceCanvas

**Inheritance:** java.lang.Object → ScreenSpaceCanvas.RelativeHorizontalCoordinate

**All Implemented Interfaces:** ScreenSpaceCanvas.HorizontalCoordinate

**Direct Known Subclasses:** ScreenSpaceCanvas.RelativeDataHorizontalCoordinate, ScreenSpaceCanvas.RelativePixelHorizontalCoordinate

## Description

Horizontal coordinate offset in a certain way relative to base point

## Fields

### HORIZONTAL_DATA_ZERO

```java
public static final ScreenSpaceCanvas.CompositeHorizontalCoordinate HORIZONTAL_DATA_ZERO
```

Corresponds to origin point of data

### HORIZONTAL_PIXEL_ZERO

```java
public static final ScreenSpaceCanvas.CompositeHorizontalCoordinate HORIZONTAL_PIXEL_ZERO
```

Corresponds to leftmost pixel of canvas

### base

```java
public final ScreenSpaceCanvas.HorizontalCoordinate base
```

### pixelsOffsetX

```java
public final int pixelsOffsetX
```

### timeOffsetX

```java
public final long timeOffsetX
```

## Constructors

### RelativeHorizontalCoordinate

```java
public RelativeHorizontalCoordinate(ScreenSpaceCanvas.HorizontalCoordinate base, int pixelsOffsetX, long timeOffsetX)
```

## Methods

### compose

```java
public ScreenSpaceCanvas.CompositeHorizontalCoordinate compose()
```

Convert to `ScreenSpaceCanvas.CompositeHorizontalCoordinate`

**Specified by:** `compose` in interface `ScreenSpaceCanvas.HorizontalCoordinate`

**Returns:** Composite horizontal coordinate

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`