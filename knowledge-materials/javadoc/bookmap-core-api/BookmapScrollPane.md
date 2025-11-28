---
source_file: BookmapScrollPane.html
package: velox.gui
classes: BookmapScrollPane, BookmapScrollPane
methods: BookmapScrollPane, BookmapScrollPane, BookmapScrollPane, doLayout, preferredSize, setCompressFromOutside, setPreventSpaceForScrollbarIfShorterThanMaximumHeight, BookmapScrollPane, BookmapScrollPane, BookmapScrollPane, setCompressFromOutside, setPreventSpaceForScrollbarIfShorterThanMaximumHeight, preferredSize, doLayout
---

# BookmapScrollPane

**Package:** velox.gui

**Type:** Class

**Inheritance:** java.lang.Object → java.awt.Component → java.awt.Container → javax.swing.JComponent → javax.swing.JScrollPane → BookmapScrollPane

**All Implemented Interfaces:** ImageObserver, MenuContainer, Serializable, Accessible, ScrollPaneConstants

## Description

This is an extension of standard JScrollPane which has the following enhancements:

1. This version of JScrollPane respects setMaximumSize regardless of layout
2. It handles vertical scrollbar appearance/disappearance correctly - by default, it will change horizontal size automatically
3. If `compressFromOutside` is set it will compress preferred size of inner component from outside automatically (thus making it downsize instead of just staying partially invisible under the scroll panel)
4. Scrolling speed logic is different and stays the same regardless of content

**See Also:**
- Serialized Form

## Constructors

### BookmapScrollPane

```java
BookmapScrollPane()
```

### BookmapScrollPane

```java
BookmapScrollPane(JComponent view)
```

### BookmapScrollPane

```java
BookmapScrollPane(JComponent view, boolean shouldAddSpaceForScrollbar)
```

## Methods

### doLayout

```java
void doLayout()
```

### preferredSize

```java
Dimension preferredSize()
```

### setCompressFromOutside

```java
void setCompressFromOutside(boolean compressFromOutside)
```

This method sets the value of compressFromOutside flag (see class documentation for the explanation of this flag).

**Parameters:**
- `compressFromOutside` - The compressFromOutside flag value

### setPreventSpaceForScrollbarIfShorterThanMaximumHeight

```java
void setPreventSpaceForScrollbarIfShorterThanMaximumHeight(boolean preventSpaceForScrollbarIfShorterThanMaximumHeight)
```

When the `BookmapScrollPane` is used in a component that resizes itself in response to its children preferred size, we end up in a case when the scroll pane adds some space for the scrollbar, but later receives enough space to show all the contents without the scrollbar.

**Parameters:**
- `preventSpaceForScrollbarIfShorterThanMaximumHeight` - Whether to prevent space for scrollbar when shorter than maximum height

---

# BookmapScrollPane

**Package:** velox.gui

**Type:** Class

**Inheritance:** java.awt.Container → java.awt.Component → java.lang.Object

## Constructors

### BookmapScrollPane

```java
public BookmapScrollPane()
```

### BookmapScrollPane

```java
public BookmapScrollPane(JComponent view)
```

### BookmapScrollPane

```java
public BookmapScrollPane(JComponent view, boolean shouldAddSpaceForScrollbar)
```

## Methods

### setCompressFromOutside

```java
public void setCompressFromOutside(boolean compressFromOutside)
```

This method sets the value of compressFromOutside flag (see class documentation for the explanation of this flag).

**Parameters:**
- `compressFromOutside` - Flag value

### setPreventSpaceForScrollbarIfShorterThanMaximumHeight

```java
public void setPreventSpaceForScrollbarIfShorterThanMaximumHeight(boolean preventSpaceForScrollbarIfShorterThanMaximumHeight)
```

When the `BookmapScrollPane` is used in a component that resizes itself in response to its children preferred size, we end up in a case when the scroll pane adds some space for the scrollbar, but later receives enough space to show all the contents without the scrollbar. But, there is an additional horizontal space added, which messes up the layout.

This flag forbids adding the space for the scrollbar if the scrollpane `getPreferredSize().height` is less than or equal to its `#getMaximumSize().height`. By default, maximum size is the size which will show all the scroll pane in screen.

**Parameters:**
- `preventSpaceForScrollbarIfShorterThanMaximumHeight` - Flag value

### preferredSize

```java
public Dimension preferredSize()
```

**Overrides:** `preferredSize` in class `Container`

### doLayout

```java
public void doLayout()
```

**Overrides:** `doLayout` in class `Container`

## Methods Inherited from java.awt.Container

- `add(Component)`
- `add(Component, int)`
- `add(Component, Object)`
- `add(Component, Object, int)`
- `add(String, Component)`
- `addContainerListener(ContainerListener)`
- `addImpl(Component, Object, int)`
- `addPropertyChangeListener(PropertyChangeListener)`
- `addPropertyChangeListener(String, PropertyChangeListener)`
- `applyComponentOrientation(ComponentOrientation)`
- `areFocusTraversalKeysSet(int)`
- `countComponents()`
- `deliverEvent(Event)`
- `findComponentAt(int, int)`
- `findComponentAt(Point)`
- `getComponent(int)`
- `getComponentAt(int, int)`
- `getComponentAt(Point)`
- `getComponentCount()`
- `getComponents()`
- `getComponentZOrder(Component)`
- `getContainerListeners()`
- `getFocusTraversalKeys(int)`
- `getFocusTraversalPolicy()`
- `getLayout()`
- `getMousePosition(boolean)`
- `insets()`
- `invalidate()`
- `isAncestorOf(Component)`
- `isFocusCycleRoot()`
- `isFocusCycleRoot(Container)`
- `isFocusTraversalPolicyProvider()`
- `isFocusTraversalPolicySet()`
- `layout()`
- `list(PrintStream, int)`
- `list(PrintWriter, int)`
- `locate(int, int)`
- `minimumSize()`
- `paintComponents(Graphics)`
- `printComponents(Graphics)`
- `processContainerEvent(ContainerEvent)`
- `processEvent(AWTEvent)`
- `remove(int)`
- `remove(Component)`
- `removeAll()`
- `removeContainerListener(ContainerListener)`
- `setComponentZOrder(Component, int)`
- `setFocusCycleRoot(boolean)`
- `setFocusTraversalPolicy(FocusTraversalPolicy)`
- `setFocusTraversalPolicyProvider(boolean)`
- `transferFocusDownCycle()`
- `validate()`
- `validateTree()`

## Methods Inherited from java.awt.Component

- `action(Event, Object)`
- `add(PopupMenu)`
- `addComponentListener(ComponentListener)`
- `addFocusListener(FocusListener)`
- `addHierarchyBoundsListener(HierarchyBoundsListener)`
- `addHierarchyListener(HierarchyListener)`
- `addInputMethodListener(InputMethodListener)`
- `addKeyListener(KeyListener)`
- `addMouseListener(MouseListener)`
- `addMouseMotionListener(MouseMotionListener)`
- `addMouseWheelListener(MouseWheelListener)`
- `bounds()`
- `checkImage(Image, int, int, ImageObserver)`
- `checkImage(Image, ImageObserver)`
- `coalesceEvents(AWTEvent, AWTEvent)`
- `contains(Point)`
- `createImage(int, int)`
- `createImage(ImageProducer)`
- `createVolatileImage(int, int)`
- `createVolatileImage(int, int, ImageCapabilities)`
- `disableEvents(long)`
- `dispatchEvent(AWTEvent)`
- `enable(boolean)`
- `enableEvents(long)`
- `enableInputMethods(boolean)`
- `firePropertyChange(String, byte, byte)`
- `firePropertyChange(String, double, double)`
- `firePropertyChange(String, float, float)`
- `firePropertyChange(String, long, long)`
- `firePropertyChange(String, short, short)`
- `firePropertyChange(String, Object, Object)`
- `getBackground()`
- `getBounds()`
- `getColorModel()`
- `getComponentListeners()`
- `getComponentOrientation()`
- `getCursor()`
- `getDropTarget()`
- `getFocusCycleRootAncestor()`
- `getFocusListeners()`
- `getFocusTraversalKeysEnabled()`
- `getFont()`
- `getForeground()`
- `getGraphicsConfiguration()`
- `getHierarchyBoundsListeners()`
- `getHierarchyListeners()`
- `getIgnoreRepaint()`
- `getInputContext()`
- `getInputMethodListeners()`
- `getInputMethodRequests()`
- `getKeyListeners()`
- `getLocale()`
- `getLocation()`
- `getLocationOnScreen()`
- `getMouseListeners()`
- `getMouseMotionListeners()`
- `getMousePosition()`
- `getMouseWheelListeners()`
- `getName()`
- `getParent()`
- `getPropertyChangeListeners()`
- `getPropertyChangeListeners(String)`
- `getSize()`
- `getToolkit()`
- `getTreeLock()`
- `gotFocus(Event, Object)`
- `handleEvent(Event)`
- `hasFocus()`
- `imageUpdate(Image, int, int, int, int, int)`
- `inside(int, int)`
- `isBackgroundSet()`
- `isCursorSet()`
- `isDisplayable()`
- `isEnabled()`
- `isFocusable()`
- `isFocusOwner()`
- `isFocusTraversable()`
- `isFontSet()`
- `isForegroundSet()`
- `isLightweight()`
- `isMaximumSizeSet()`
- `isMinimumSizeSet()`
- `isPreferredSizeSet()`
- `isShowing()`
- `isValid()`
- `isVisible()`
- `keyDown(Event, int)`
- `keyUp(Event, int)`
- `list()`
- `list(PrintStream)`
- `list(PrintWriter)`
- `location()`
- `lostFocus(Event, Object)`
- `mouseDown(Event, int, int)`
- `mouseDrag(Event, int, int)`
- `mouseEnter(Event, int, int)`
- `mouseExit(Event, int, int)`
- `mouseMove(Event, int, int)`
- `mouseUp(Event, int, int)`
- `move(int, int)`
- `nextFocus()`
- `paintAll(Graphics)`
- `postEvent(Event)`
- `prepareImage(Image, int, int, ImageObserver)`
- `prepareImage(Image, ImageObserver)`
- `processComponentEvent(ComponentEvent)`
- `processFocusEvent(FocusEvent)`
- `processHierarchyBoundsEvent(HierarchyEvent)`
- `processHierarchyEvent(HierarchyEvent)`
- `processInputMethodEvent(InputMethodEvent)`
- `processMouseWheelEvent(MouseWheelEvent)`
- `remove(MenuComponent)`
- `removeComponentListener(ComponentListener)`
- `removeFocusListener(FocusListener)`
- `removeHierarchyBoundsListener(HierarchyBoundsListener)`
- `removeHierarchyListener(HierarchyListener)`
- `removeInputMethodListener(InputMethodListener)`
- `removeKeyListener(KeyListener)`
- `removeMouseListener(MouseListener)`
- `removeMouseMotionListener(MouseMotionListener)`
- `removeMouseWheelListener(MouseWheelListener)`
- `removePropertyChangeListener(PropertyChangeListener)`
- `removePropertyChangeListener(String, PropertyChangeListener)`
- `repaint()`
- `repaint(int, int, int, int)`
- `repaint(long)`
- `requestFocus(boolean, FocusEvent.Cause)`
- `requestFocus(FocusEvent.Cause)`
- `requestFocusInWindow(FocusEvent.Cause)`
- `resize(int, int)`
- `resize(Dimension)`
- `setBounds(int, int, int, int)`
- `setBounds(Rectangle)`
- `setCursor(Cursor)`
- `setDropTarget(DropTarget)`
- `setFocusable(boolean)`
- `setFocusTraversalKeysEnabled(boolean)`
- `setIgnoreRepaint(boolean)`
- `setLocale(Locale)`
- `setLocation(int, int)`
- `setLocation(Point)`
- `setMixingCutoutShape(Shape)`
- `setName(String)`
- `setSize(int, int)`
- `setSize(Dimension)`
- `show()`
- `show(boolean)`
- `size()`
- `toString()`
- `transferFocus()`
- `transferFocusBackward()`
- `transferFocusUpCycle()`

## Methods Inherited from java.lang.Object

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