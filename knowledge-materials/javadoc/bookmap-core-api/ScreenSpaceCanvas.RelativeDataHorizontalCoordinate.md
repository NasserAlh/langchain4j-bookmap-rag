---
source_file: ScreenSpaceCanvas.RelativeDataHorizontalCoordinate.html
package: velox.api.layer1.layers.strategies.interfaces
classes: ScreenSpaceCanvas.RelativeDataHorizontalCoordinate
methods: RelativeDataHorizontalCoordinate
---

# ScreenSpaceCanvas.RelativeDataHorizontalCoordinate

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Class (static)

**Enclosing Interface:** `ScreenSpaceCanvas`

**Inheritance:**
- `java.lang.Object`
- `velox.api.layer1.layers.strategies.interfaces.ScreenSpaceCanvas.RelativeHorizontalCoordinate`
- `velox.api.layer1.layers.strategies.interfaces.ScreenSpaceCanvas.RelativeDataHorizontalCoordinate`

**All Implemented Interfaces:** `ScreenSpaceCanvas.HorizontalCoordinate`

## Description

Horizontal coordinate offset certain number of data levels point.

## Fields

**Inherited from `ScreenSpaceCanvas.RelativeHorizontalCoordinate`:**
- `base`
- `HORIZONTAL_DATA_ZERO`
- `HORIZONTAL_PIXEL_ZERO`
- `pixelsOffsetX`
- `timeOffsetX`

## Constructors

### RelativeDataHorizontalCoordinate

```java
public RelativeDataHorizontalCoordinate(ScreenSpaceCanvas.HorizontalCoordinate base, long offset)
```

## Methods

**Inherited from `ScreenSpaceCanvas.RelativeHorizontalCoordinate`:**
- `compose()`
- `toString()`

**Inherited from `java.lang.Object`:**
- `clone()`
- `equals(Object)`
- `finalize()`
- `getClass()`
- `hashCode()`
- `notify()`
- `notifyAll()`
- `wait()`
- `wait(long)`
- `wait(long, int)`