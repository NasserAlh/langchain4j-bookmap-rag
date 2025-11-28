---
source_file: ScreenSpaceCanvas.CanvasIcon.html
package: velox.api.layer1.layers.strategies.interfaces
classes: ScreenSpaceCanvas.CanvasIcon
methods: CanvasIcon, getImage, getX1, getY1, getX2, getY2, copy, setImage, setX1, setX2, setY1, setY2
---

# ScreenSpaceCanvas.CanvasIcon

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Class

**Enclosing Interface:** ScreenSpaceCanvas

## Inheritance

**Inheritance Tree:**
- java.lang.Object
- velox.api.layer1.layers.strategies.interfaces.ScreenSpaceCanvas.BasicCanvasShape
- velox.api.layer1.layers.strategies.interfaces.ScreenSpaceCanvas.CanvasIcon

**All Implemented Interfaces:** ScreenSpaceCanvas.CanvasShape

## Fields

**Fields inherited from class velox.api.layer1.layers.strategies.interfaces.ScreenSpaceCanvas.BasicCanvasShape:**
- `uniqueId`

## Constructors

### CanvasIcon

```java
public CanvasIcon(ScreenSpaceCanvas.PreparedImage image, ScreenSpaceCanvas.HorizontalCoordinate x1, ScreenSpaceCanvas.VerticalCoordinate y1, ScreenSpaceCanvas.HorizontalCoordinate x2, ScreenSpaceCanvas.VerticalCoordinate y2)
```

Creates new icon with specified coordinates and texture (can be changed later)

**Parameters:**
- `image` - Image to use as texture for icon
- `x1` - Horizontal coordinate of bottom left corner
- `y1` - Vertical coordinate of bottom left corner
- `x2` - Horizontal coordinate of top right corner
- `y2` - Vertical coordinate of top right corner

## Methods

### getImage

```java
public ScreenSpaceCanvas.PreparedImage getImage()
```

### getX1

```java
public ScreenSpaceCanvas.HorizontalCoordinate getX1()
```

### getY1

```java
public ScreenSpaceCanvas.VerticalCoordinate getY1()
```

### getX2

```java
public ScreenSpaceCanvas.HorizontalCoordinate getX2()
```

### getY2

```java
public ScreenSpaceCanvas.VerticalCoordinate getY2()
```

### copy

```java
public ScreenSpaceCanvas.CanvasIcon copy()
```

Make a copy of a shape. Copy must be made in a way that guarantees that copy won't change on itself during normal use

### setImage

```java
public void setImage(ScreenSpaceCanvas.PreparedImage icon)
```

### setX1

```java
public void setX1(ScreenSpaceCanvas.HorizontalCoordinate x1)
```

### setX2

```java
public void setX2(ScreenSpaceCanvas.HorizontalCoordinate x2)
```

### setY1

```java
public void setY1(ScreenSpaceCanvas.VerticalCoordinate y1)
```

### setY2

```java
public void setY2(ScreenSpaceCanvas.VerticalCoordinate y2)
```

**Methods inherited from class velox.api.layer1.layers.strategies.interfaces.ScreenSpaceCanvas.BasicCanvasShape:**
- `getUniqueId()`
- `publishChange()`
- `setShapeListener(ScreenSpaceShapeListener)`

**Methods inherited from class java.lang.Object:**
- `clone()`
- `equals(Object)`
- `finalize()`
- `getClass()`
- `hashCode()`
- `notify()`
- `notifyAll()`
- `toString()`
- `wait()`
- `wait(long)`
- `wait(long, int)`