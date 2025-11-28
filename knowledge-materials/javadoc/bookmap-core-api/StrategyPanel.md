---
source_file: StrategyPanel.html
package: velox.gui
classes: StrategyPanel, StrategyPanel
methods: StrategyPanel, StrategyPanel, StrategyPanel, StrategyPanel, addReloadListener, getTitle, removeReloadListenersIf, requestReload, setEnabled, setLocalizedTitle, StrategyPanel, StrategyPanel, StrategyPanel, StrategyPanel, setLocalizedTitle, getTitle, setEnabled, requestReload, addReloadListener, removeReloadListenersIf
---

# StrategyPanel

**Package:** velox.gui

**Type:** Class

**Inheritance:** java.lang.Object → java.awt.Component → java.awt.Container → javax.swing.JComponent → javax.swing.JPanel → StrategyPanel

**All Implemented Interfaces:** ImageObserver, MenuContainer, Serializable, Accessible

## Description

JPanel that can be display in strategies dialog with custom title specified  
Will follow general style with border and title  
If you want displayed panel to be without general styled border and title, set title to null

Also, sets all components `Component.setEnabled(boolean)`

**See Also:**
- Serialized Form

## Constructors

### StrategyPanel

```java
StrategyPanel(String title)
```

### StrategyPanel

```java
StrategyPanel(String title, boolean isDoubleBuffered)
```

### StrategyPanel

```java
StrategyPanel(String title, LayoutManager layout)
```

### StrategyPanel

```java
StrategyPanel(String title, LayoutManager layout, boolean isDoubleBuffered)
```

## Methods

### addReloadListener

```java
void addReloadListener(Runnable reloadListener)
```

Called on `requestReload()`

### getTitle

```java
String getTitle()
```

### removeReloadListenersIf

```java
void removeReloadListenersIf(Predicate<Runnable> listener)
```

Remove reloadListeners (see `requestReload()`) which match the rule.

### requestReload

```java
void requestReload()
```

Reload strategy panels,  
`Layer1CustomPanelsGetter.getCustomGuiFor(String, String)` will be  
called again if panel was still visible to retrieve updated version

### setEnabled

```java
void setEnabled(boolean enabled)
```

### setLocalizedTitle

```java
void setLocalizedTitle(TranslatableComponent localizedTitle)
```

Set localized title.

---

# StrategyPanel

## Inheritance

**Methods inherited from class java.awt.Component:**
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
- `setComponentOrientation(ComponentOrientation)`
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

**Methods inherited from class java.lang.Object:**
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

## Constructors

### StrategyPanel

```java
public StrategyPanel(String title)
```

**Parameters:**
- `title` - Title that will be displayed in strategies dialog

### StrategyPanel

```java
public StrategyPanel(String title, boolean isDoubleBuffered)
```

**Parameters:**
- `title` - Title that will be displayed in strategies dialog
- `isDoubleBuffered` - 

### StrategyPanel

```java
public StrategyPanel(String title, LayoutManager layout, boolean isDoubleBuffered)
```

**Parameters:**
- `title` - Title that will be displayed in strategies dialog
- `layout` - 
- `isDoubleBuffered` - 

### StrategyPanel

```java
public StrategyPanel(String title, LayoutManager layout)
```

**Parameters:**
- `title` - Title that will be displayed in strategies dialog
- `layout` - 

## Methods

### setLocalizedTitle

```java
public void setLocalizedTitle(TranslatableComponent localizedTitle)
```

Set localized title. After set, this version will be displayed instead of usual title.

**Parameters:**
- `localizedTitle` - 

### getTitle

```java
public String getTitle()
```

### setEnabled

```java
public void setEnabled(boolean enabled)
```

**Overrides:** `setEnabled(boolean)` in class `JComponent`

### requestReload

```java
public void requestReload()
```

Reload strategy panels, `Layer1CustomPanelsGetter.getCustomGuiFor(String, String)` will be called again if panel was still visible to retrieve updated version.

### addReloadListener

```java
public void addReloadListener(Runnable reloadListener)
```

Called on `requestReload()`

**Parameters:**
- `reloadListener` - 

### removeReloadListenersIf

```java
public void removeReloadListenersIf(Predicate<Runnable> listener)
```

Remove reloadListeners (see `requestReload()`) which match the rule. Helpful when panel gets reused multiple times.