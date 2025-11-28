---
source_file: ScreenSpaceCanvas.RelativePixelHorizontalCoordinate.html
package: velox.api.layer1.layers.strategies.interfaces
classes: ScreenSpaceCanvas.RelativePixelHorizontalCoordinate
methods: RelativePixelHorizontalCoordinate
extends: ** [`ScreenSpaceCanvas.RelativeHorizontalCoordinate`](ScreenSpaceCanvas.RelativeHorizontalCoordinate.html)
---

# ScreenSpaceCanvas.RelativePixelHorizontalCoordinate

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Class (static)

**Enclosing Interface:** [`ScreenSpaceCanvas`](ScreenSpaceCanvas.html)

## Inheritance

**Extends:** [`ScreenSpaceCanvas.RelativeHorizontalCoordinate`](ScreenSpaceCanvas.RelativeHorizontalCoordinate.html)

**All Implemented Interfaces:** [`ScreenSpaceCanvas.HorizontalCoordinate`](ScreenSpaceCanvas.HorizontalCoordinate.html)

**Inheritance Path:** `java.lang.Object` → `velox.api.layer1.layers.strategies.interfaces.ScreenSpaceCanvas.RelativeHorizontalCoordinate` → `velox.api.layer1.layers.strategies.interfaces.ScreenSpaceCanvas.RelativePixelHorizontalCoordinate`

## Description

Horizontal coordinate offset certain number of pixels from base point

## Fields

**Inherited from [`ScreenSpaceCanvas.RelativeHorizontalCoordinate`](ScreenSpaceCanvas.RelativeHorizontalCoordinate.html):**
- `base`
- `HORIZONTAL_DATA_ZERO`
- `HORIZONTAL_PIXEL_ZERO`
- `pixelsOffsetX`
- `timeOffsetX`

## Constructors

### RelativePixelHorizontalCoordinate

```java
public RelativePixelHorizontalCoordinate(ScreenSpaceCanvas.HorizontalCoordinate base, int offset)
```

## Methods

**Inherited from [`ScreenSpaceCanvas.RelativeHorizontalCoordinate`](ScreenSpaceCanvas.RelativeHorizontalCoordinate.html):**
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