---
source_file: ScreenSpaceCanvas.RelativePixelVerticalCoordinate.html
package: velox.api.layer1.layers.strategies.interfaces
classes: ScreenSpaceCanvas.RelativePixelVerticalCoordinate
methods: RelativePixelVerticalCoordinate
---

# ScreenSpaceCanvas.RelativePixelVerticalCoordinate

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Class (static)

**Enclosing Interface:** ScreenSpaceCanvas

**Inheritance:**
- java.lang.Object
- velox.api.layer1.layers.strategies.interfaces.ScreenSpaceCanvas.RelativeVerticalCoordinate
- velox.api.layer1.layers.strategies.interfaces.ScreenSpaceCanvas.RelativePixelVerticalCoordinate

**All Implemented Interfaces:** ScreenSpaceCanvas.VerticalCoordinate

## Description

Vertical coordinate offset certain number of pixels from base point.

## Fields

**Inherited from ScreenSpaceCanvas.RelativeVerticalCoordinate:**
- `base`
- `dataOffsetY`
- `pixelsOffsetY`
- `VERTICAL_DATA_ZERO`
- `VERTICAL_PIXEL_ZERO`

## Constructors

### RelativePixelVerticalCoordinate

```java
public RelativePixelVerticalCoordinate(ScreenSpaceCanvas.VerticalCoordinate base, int offset)
```

## Methods

**Inherited from ScreenSpaceCanvas.RelativeVerticalCoordinate:**
- `compose()`
- `toString()`

**Inherited from java.lang.Object:**
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