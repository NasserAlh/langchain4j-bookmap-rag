---
source_file: CanvasMouseListener.html
package: velox.api.layer1.layers.strategies.interfaces
classes: CanvasMouseListener
methods: getEventScore, onFocusGained, onFocusLost, mouseClicked, mousePressed, mouseReleased, mouseEntered, mouseExited, mouseDragged, mouseMoved, mouseWheelMoved
---

# CanvasMouseListener

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Interface

## Description

Use `ScreenSpaceCanvas.addMouseListener(CanvasMouseListener)` to add a mouse listener to a canvas.

If you have a web-development background, you might expect that events propagate (bubble-up), and notify event listeners of every component at a certain point. In such case, **note that mouse events in Bookmap do not propagate - only one module gets to handle an event,** which is the module that provided the highest score from `getEventScore(CanvasMouseEvent)`.

Internally a mouse event lifecycle looks like this:
1. A mouse event occurs
2. `getEventScore(CanvasMouseEvent)` is called for every module
3. Bookmap finds a module with the highest score
4. If this module is different from the previously focused one, `onFocusLost()` is called on the previous one
5. Bookmap calls `onFocusGained()` on the newly focused module
6. Bookmap calls the corresponding method (e.g. `mouseClicked(CanvasMouseEvent)`) *only on the focused module*

## Methods

### getEventScore

```java
int getEventScore(CanvasMouseEvent e)
```

Every `CanvasMouseListener` on every mouse event is asked to "vote" for this event. The module which provided the highest score - wins, its `CanvasContextMenuProvider.onFocusGained()` and the related event-handling method is called.

Use `MouseModuleScore` as a reference for your values, e.g. if you want your module to capture focus on trade circles/indicator lines, return the score higher than `MouseModuleScore.GRAPH_LAYERS_MODULES_MAX`.

Modules are advised to provide the score using as many parameters as possible, i.e. if you are only interested in mouse hover, check that there are no mouse clicks using `CanvasMouseEvent.sourceEvent` and `MouseEvent.getClickCount()`. In the same manner, if your module should only react to mouse clicks but not mouse hover, check if there was a button clicked. You can also test which button was clicked, e.g. `SwingUtilities.isLeftMouseButton(MouseEvent)`.

**Parameters:**
- `e` - Mouse event

**Returns:** Score in range [`MouseModuleScore.MIN`, `MouseModuleScore.MAX`] if you want to handle this event, `MouseModuleScore.NONE` if you are not interested in it.

**Throws:**
- `IllegalArgumentException` - If the score is out of range [`MouseModuleScore.MIN`, `MouseModuleScore.MAX`] and not equal to `MouseModuleScore.NONE`

**See Also:**
- `MouseModuleScore`

### onFocusGained

```java
default void onFocusGained()
```

Called if this module wins the voting for an event.

### onFocusLost

```java
default void onFocusLost()
```

Called after `CanvasContextMenuProvider.onFocusGained()`, when some other module wins the voting for another mouse event.

### mouseClicked

```java
default void mouseClicked(CanvasMouseEvent e)
```

### mousePressed

```java
default void mousePressed(CanvasMouseEvent e)
```

### mouseReleased

```java
default void mouseReleased(CanvasMouseEvent e)
```

### mouseEntered

```java
default void mouseEntered(CanvasMouseEvent e)
```

### mouseExited

```java
default void mouseExited(CanvasMouseEvent e)
```

### mouseDragged

```java
default void mouseDragged(CanvasMouseEvent e)
```

### mouseMoved

```java
default void mouseMoved(CanvasMouseEvent e)
```

### mouseWheelMoved

```java
default void mouseWheelMoved(CanvasMouseEvent e)
```