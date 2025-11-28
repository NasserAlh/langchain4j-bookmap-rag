---
source_file: ScreenSpaceCanvas.html
package: velox.api.layer1.layers.strategies.interfaces
classes: ScreenSpaceCanvas
methods: getUniqueId, dispose, addShape, removeShape, addMouseListener, addContextMenuProvider
---

# ScreenSpaceCanvas

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Interface

## Nested Classes

- `ScreenSpaceCanvas.BasicCanvasShape`
- `ScreenSpaceCanvas.CanvasIcon`
- `ScreenSpaceCanvas.CanvasShape` (interface)
- `ScreenSpaceCanvas.CompositeCoordinateBase` (enum)
- `ScreenSpaceCanvas.CompositeHorizontalCoordinate` - Horizontal coordinate described in universal way
- `ScreenSpaceCanvas.CompositeVerticalCoordinate` - Vertical coordinate described in universal way
- `ScreenSpaceCanvas.HorizontalCoordinate` (interface)
- `ScreenSpaceCanvas.PreparedImage` - Wrapper for an image
- `ScreenSpaceCanvas.RelativeDataHorizontalCoordinate` - Horizontal coordinate offset certain number of data levels point
- `ScreenSpaceCanvas.RelativeDataVerticalCoordinate` - Vertical coordinate offset certain number of data levels from base point
- `ScreenSpaceCanvas.RelativeHorizontalCoordinate` - Horizontal coordinate offset in a certain way relative to base point
- `ScreenSpaceCanvas.RelativePixelHorizontalCoordinate` - Horizontal coordinate offset certain number of pixels from base point
- `ScreenSpaceCanvas.RelativePixelVerticalCoordinate` - Vertical coordinate offset certain number of pixels from base point
- `ScreenSpaceCanvas.RelativeVerticalCoordinate` - Vertical coordinate offset in a certain way relative to base point
- `ScreenSpaceCanvas.VerticalCoordinate` (interface)

## Methods

### getUniqueId

```java
long getUniqueId()
```

### dispose

```java
void dispose()
```

### addShape

```java
void addShape(ScreenSpaceCanvas.CanvasShape canvasShape)
```

### removeShape

```java
void removeShape(ScreenSpaceCanvas.CanvasShape canvasShape)
```

### addMouseListener

```java
void addMouseListener(CanvasMouseListener listener)
```

Add a mouse listener to this canvas. You can modify shapes based on mouse hover/click, e.g. highlight your icons, scale them up or provide more info at the point of interest.

**Parameters:**
- `listener` - 

### addContextMenuProvider

```java
void addContextMenuProvider(CanvasContextMenuProvider contextMenuProvider)
```

Allows to add custom menu items into the right-click menu

**Parameters:**
- `contextMenuProvider` -