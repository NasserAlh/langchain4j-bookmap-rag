---
source_file: ScreenSpaceCanvas.CanvasShape.html
package: velox.api.layer1.layers.strategies.interfaces
classes: ScreenSpaceCanvas.CanvasShape
methods: getUniqueId, setShapeListener, copy
---

# ScreenSpaceCanvas.CanvasShape

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Interface

**Enclosing Interface:** ScreenSpaceCanvas

**All Known Implementing Classes:** ScreenSpaceCanvas.BasicCanvasShape, ScreenSpaceCanvas.CanvasIcon

## Methods

### getUniqueId

```java
long getUniqueId()
```

### setShapeListener

```java
void setShapeListener(velox.api.layer1.layers.strategies.interfaces.ScreenSpaceShapeListener listener)
```

### copy

```java
ScreenSpaceCanvas.CanvasShape copy()
```

Make a copy of a shape. Copy must be made in a way that guarantees that copy won't change on itself during normal use.