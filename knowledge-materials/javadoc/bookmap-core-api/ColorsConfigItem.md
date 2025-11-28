---
source_file: ColorsConfigItem.html
package: velox.gui.colors
classes: ColorsConfigItem
methods: colorCurrent, text, ColorsConfigItem, ColorsConfigItem, ColorsConfigItem, ColorsConfigItem, ColorsConfigItem, ColorsConfigItem, ColorsConfigItem, getColor, getColorConfigName, getLabel, onAdjust, onCurrentColorChanged, onRestore, setColor, setEnabled, updateColor, colorCurrent, text
total_methods: 37
---

# ColorsConfigItem

**Package:** velox.gui.colors

**Type:** Class

**Inheritance:** java.lang.Object → java.awt.Component → java.awt.Container → javax.swing.JComponent → javax.swing.JPanel → ColorsConfigItem

**All Implemented Interfaces:** ImageObserver, MenuContainer, Serializable, Accessible

## Description

GUI element that allows easy color selection

**See Also:**
- Serialized Form

## Fields

### colorCurrent
```java
protected Color colorCurrent
```

### text
```java
protected final String text
```

## Constructors

### ColorsConfigItem
```java
ColorsConfigItem(Color currentColor, Color defaultColor, String label, Consumer<Color> colorChangedListener)
```

Creates color configuration UI component, similar to the ones Bookmap uses

### ColorsConfigItem
```java
ColorsConfigItem(Color currentColor, Color defaultColor, Consumer<Color> colorChangedListener)
```

Creates color configuration UI component, similar to the ones Bookmap uses.

### ColorsConfigItem
```java
ColorsConfigItem(String text, Color defaultColor, IndicatorColorInterface indicatorColorInterface, ColorsChangedListener listener)
```

### ColorsConfigItem
```java
ColorsConfigItem(String fullName, String text, boolean showLabel, Color defaultColor, IndicatorColorInterface indicatorColorInterface, ColorsChangedListener listener)
```

### ColorsConfigItem
```java
ColorsConfigItem(String fullName, String text, boolean showLabel, Color defaultColor, IndicatorColorInterface indicatorColorInterface, ColorsChangedListener listener, ColorsConfigItemCallbacks callbacks, boolean styleAsMenuItem)
```

### ColorsConfigItem
```java
ColorsConfigItem(String fullName, String text, boolean showLabel, Color defaultColor, IndicatorColorInterface indicatorColorInterface, ColorsChangedListener listener, ColorsConfigItemCallbacks callbacks, boolean styleAsMenuItem, Insets insets)
```

### ColorsConfigItem
```java
ColorsConfigItem(String fullName, String text, Color defaultColor, IndicatorColorInterface indicatorColorInterface, ColorsChangedListener listener)
```

## Methods

### getColor
```java
Color getColor()
```

### getColorConfigName
```java
String getColorConfigName()
```

### getLabel
```java
JLabel getLabel()
```

### onAdjust
```java
protected void onAdjust()
```

### onCurrentColorChanged
```java
void onCurrentColorChanged()
```

### onRestore
```java
void onRestore(boolean call)
```

### setColor
```java
void setColor(Color color, boolean call)
```

### setEnabled
```java
void setEnabled(boolean enabled)
```

### updateColor
```java
void updateColor()
```

Sets current color to match color provided by color interface (to be used in case color was changed externally)

---

## Methods inherited from class java.awt.Component

**Inherited Methods:**
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

## Methods inherited from class java.lang.Object

**Inherited Methods:**
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

---

## Field Details

### colorCurrent

```java
protected Color colorCurrent
```

### text

```java
protected final String text
```

---

## Constructor Details

### ColorsConfigItem

```java
public ColorsConfigItem(Color currentColor, Color defaultColor, Consumer<Color> colorChangedListener)
```

Creates color configuration UI component, similar to the ones Bookmap uses. No label is used (equivalent of `ColorsConfigItem(Color, Color, String, Consumer)` with label set to null)

**Parameters:**
- `currentColor` - Currently selected color (component will be set to it)
- `defaultColor` - Default color (when reset button is pressed it will be applied)
- `colorChangedListener` - Called when color is changed (regardless of the way it happens - reset also counts as color change)

### ColorsConfigItem

```java
public ColorsConfigItem(Color currentColor, Color defaultColor, String label, Consumer<Color> colorChangedListener)
```

Creates color configuration UI component, similar to the ones Bookmap uses

**Parameters:**
- `currentColor` - Currently selected color (component will be set to it)
- `defaultColor` - Default color (when reset button is pressed it will be applied)
- `label` - Text on the component, null to remove label
- `colorChangedListener` - Called when color is changed (regardless of the way it happens - reset also counts as color change)

### ColorsConfigItem

```java
public ColorsConfigItem(String fullName, String text, boolean showLabel, Color defaultColor, IndicatorColorInterface indicatorColorInterface, ColorsChangedListener listener)
```

**Parameters:**
- `fullName` - Full color name
- `text` - Text that may be displayed to user
- `showLabel` - False if text should not be displayed, true otherwise
- `defaultColor` - If no color is already in settings for this name, this color will be used. Restoring default will use this color.
- `listener` - 

### ColorsConfigItem

```java
public ColorsConfigItem(String fullName, String text, boolean showLabel, Color defaultColor, IndicatorColorInterface indicatorColorInterface, ColorsChangedListener listener, ColorsConfigItemCallbacks callbacks, boolean styleAsMenuItem)
```

**Parameters:**
- `fullName` - Full color name
- `text` - Text that may be displayed to user
- `showLabel` - False if text should not be displayed, true otherwise
- `defaultColor` - If no color is already in settings for this name, this color will be used. Restoring default will use this color.
- `listener` - 
- `callbacks` - May be null
- `styleAsMenuItem` - If true, item will be styled to put in menu

### ColorsConfigItem

```java
public ColorsConfigItem(String fullName, String text, Color defaultColor, IndicatorColorInterface indicatorColorInterface, ColorsChangedListener listener)
```

**Parameters:**
- `fullName` - 
- `text` - Can be null if no label
- `defaultColor` - If no color is already in settings for this name, this color will be used. Restoring default will use this color.
- `indicatorColorInterface` - 
- `listener` - 

### ColorsConfigItem

```java
public ColorsConfigItem(String text, Color defaultColor, IndicatorColorInterface indicatorColorInterface, ColorsChangedListener listener)
```

### ColorsConfigItem

```java
public ColorsConfigItem(String fullName, String text, boolean showLabel, Color defaultColor, IndicatorColorInterface indicatorColorInterface, ColorsChangedListener listener, ColorsConfigItemCallbacks callbacks, boolean styleAsMenuItem, Insets insets)
```

**Parameters:**
- `fullName` - Full color name
- `text` - Text that may be displayed to user
- `showLabel` - False if text should not be displayed, true otherwise
- `defaultColor` - If no color is already in settings for this name, this color will be used. Restoring default will use this color.
- `listener` - 
- `callbacks` - May be null
- `styleAsMenuItem` - If true, item will be styled to put in menu

---

## Method Details

### getColorConfigName

```java
public String getColorConfigName()
```

### getColor

```java
public Color getColor()
```

### setColor

```java
public void setColor(Color color, boolean call)
```

### onCurrentColorChanged

```java
public void onCurrentColorChanged()
```

### onAdjust

```java
protected void onAdjust()
```

### onRestore

```java
protected void onRestore(boolean call)
```

---

### onRestore

```java
public void onRestore(boolean call)
```

### getLabel

```java
public JLabel getLabel()
```

### setEnabled

```java
public void setEnabled(boolean enabled)
```

**Overrides:** `setEnabled` in class `JComponent`

### updateColor

```java
public void updateColor()
```

Sets current color to match color provided by color interface (to be used in case color was changed externally)