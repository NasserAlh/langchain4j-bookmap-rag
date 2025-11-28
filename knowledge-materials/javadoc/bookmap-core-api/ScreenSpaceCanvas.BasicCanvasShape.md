---
source_file: ScreenSpaceCanvas.BasicCanvasShape.html
package: velox.api.layer1.layers.strategies.interfaces
classes: ScreenSpaceCanvas.BasicCanvasShape
methods: uniqueId, BasicCanvasShape, setShapeListener, getUniqueId, publishChange
---

# ScreenSpaceCanvas.BasicCanvasShape

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Class

**Enclosing Interface:** ScreenSpaceCanvas

**Inheritance:** java.lang.Object → ScreenSpaceCanvas.BasicCanvasShape

**All Implemented Interfaces:** ScreenSpaceCanvas.CanvasShape

**Direct Known Subclasses:** ScreenSpaceCanvas.CanvasIcon

## Fields

### uniqueId

```java
protected long uniqueId
```

## Constructors

### BasicCanvasShape

```java
public BasicCanvasShape()
```

## Methods

### setShapeListener

```java
void setShapeListener(velox.api.layer1.layers.strategies.interfaces.ScreenSpaceShapeListener listener)
```

**Specified by:** `setShapeListener` in interface `ScreenSpaceCanvas.CanvasShape`

### getUniqueId

```java
long getUniqueId()
```

**Specified by:** `getUniqueId` in interface `ScreenSpaceCanvas.CanvasShape`

### publishChange

```java
protected void publishChange()
```

## Inherited Methods

**From class java.lang.Object:**
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

**From interface ScreenSpaceCanvas.CanvasShape:**
- `copy()`