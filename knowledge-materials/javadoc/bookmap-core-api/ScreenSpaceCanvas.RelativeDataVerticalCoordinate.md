---
source_file: ScreenSpaceCanvas.RelativeDataVerticalCoordinate.html
package: velox.api.layer1.layers.strategies.interfaces
classes: ScreenSpaceCanvas.RelativeDataVerticalCoordinate
methods: RelativeDataVerticalCoordinate
extends: ** ScreenSpaceCanvas.RelativeVerticalCoordinate
---

# ScreenSpaceCanvas.RelativeDataVerticalCoordinate

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Class (static)

**Enclosing Interface:** ScreenSpaceCanvas

## Inheritance

**Extends:** ScreenSpaceCanvas.RelativeVerticalCoordinate

**All Implemented Interfaces:** ScreenSpaceCanvas.VerticalCoordinate

**Inheritance Tree:**
- java.lang.Object
- velox.api.layer1.layers.strategies.interfaces.ScreenSpaceCanvas.RelativeVerticalCoordinate
- velox.api.layer1.layers.strategies.interfaces.ScreenSpaceCanvas.RelativeDataVerticalCoordinate

## Description

Vertical coordinate offset certain number of data levels from base point.

## Fields

**Inherited from ScreenSpaceCanvas.RelativeVerticalCoordinate:**
- `base`
- `dataOffsetY`
- `pixelsOffsetY`
- `VERTICAL_DATA_ZERO`
- `VERTICAL_PIXEL_ZERO`

## Constructors

### RelativeDataVerticalCoordinate

```java
public RelativeDataVerticalCoordinate(ScreenSpaceCanvas.VerticalCoordinate base, double offset)
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