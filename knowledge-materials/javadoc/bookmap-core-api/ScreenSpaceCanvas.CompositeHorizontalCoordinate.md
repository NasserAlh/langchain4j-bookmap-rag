---
source_file: ScreenSpaceCanvas.CompositeHorizontalCoordinate.html
package: velox.api.layer1.layers.strategies.interfaces
classes: ScreenSpaceCanvas.CompositeHorizontalCoordinate
methods: base, pixelsX, timeX, CompositeHorizontalCoordinate, compose, toString
---

# ScreenSpaceCanvas.CompositeHorizontalCoordinate

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Class

**Enclosing Interface:** `ScreenSpaceCanvas`

**Inheritance:** `java.lang.Object` → `ScreenSpaceCanvas.CompositeHorizontalCoordinate`

**All Implemented Interfaces:** `ScreenSpaceCanvas.HorizontalCoordinate`

## Description

Horizontal coordinate described in universal way. You might want to use `ScreenSpaceCanvas.RelativeHorizontalCoordinate` to improve readability.

## Fields

### base

```java
public final ScreenSpaceCanvas.CompositeCoordinateBase base
```

Coordinates origin (essentially this selects what the point will follow - screen or the data).

### pixelsX

```java
public final int pixelsX
```

Pixels offset relative to the origin (added up with `timeX`).

### timeX

```java
public final long timeX
```

Data offset relative to the origin (added up with `pixelsX`).

## Constructors

### CompositeHorizontalCoordinate

```java
public CompositeHorizontalCoordinate(ScreenSpaceCanvas.CompositeCoordinateBase base, int pixelsX, long timeX)
```

## Methods

### compose

```java
public ScreenSpaceCanvas.CompositeHorizontalCoordinate compose()
```

Convert to `ScreenSpaceCanvas.CompositeHorizontalCoordinate`

**Specified by:** `compose` in interface `ScreenSpaceCanvas.HorizontalCoordinate`

**Returns:** `ScreenSpaceCanvas.CompositeHorizontalCoordinate`

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`