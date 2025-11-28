---
source_file: ScreenSpaceCanvas.CompositeVerticalCoordinate.html
package: velox.api.layer1.layers.strategies.interfaces
classes: ScreenSpaceCanvas.CompositeVerticalCoordinate
methods: base, pixelsY, dataY, CompositeVerticalCoordinate, compose, toString
---

# ScreenSpaceCanvas.CompositeVerticalCoordinate

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Class (static)

**Enclosing Interface:** ScreenSpaceCanvas

**Inheritance:** java.lang.Object → ScreenSpaceCanvas.CompositeVerticalCoordinate

**All Implemented Interfaces:** ScreenSpaceCanvas.VerticalCoordinate

## Description

Vertical coordinate described in universal way. You might want to use `ScreenSpaceCanvas.RelativeVerticalCoordinate` to improve readability.

## Fields

### base

```java
public final ScreenSpaceCanvas.CompositeCoordinateBase base
```

Coordinates origin (essentially this selects what the point will follow - screen or the data).

### pixelsY

```java
public final int pixelsY
```

Pixels offset relative to the origin (added up with `dataY`).

### dataY

```java
public final double dataY
```

Data offset relative to the origin (added up with `pixelsY`).

## Constructors

### CompositeVerticalCoordinate

```java
public CompositeVerticalCoordinate(ScreenSpaceCanvas.CompositeCoordinateBase base, int pixelsY, double dataY)
```

## Methods

### compose

```java
public ScreenSpaceCanvas.CompositeVerticalCoordinate compose()
```

Convert to `ScreenSpaceCanvas.CompositeVerticalCoordinate`

**Specified by:** `compose` in interface `ScreenSpaceCanvas.VerticalCoordinate`

**Returns:** Composite vertical coordinate

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`