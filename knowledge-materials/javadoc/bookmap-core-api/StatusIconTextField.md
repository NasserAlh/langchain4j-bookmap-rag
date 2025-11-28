---
source_file: StatusIconTextField.html
package: velox.api.layer1.gui
classes: StatusIconTextField, StatusIconTextField
methods: textField, StatusIconTextField, addFocusListener, addKeyListener, changeTextFieldState, getText, isOptimizedDrawingEnabled, removeFocusListener, removeKeyListener, requestFocus, setChangeListener, setEnabled, setIcon, setIconToolTip, setText, setToolTipText, setVisible, textField, StatusIconTextField, isOptimizedDrawingEnabled
total_methods: 34
---

# StatusIconTextField

**Package:** velox.api.layer1.gui

**Type:** Class

**Inheritance:** java.lang.Object → java.awt.Component → java.awt.Container → javax.swing.JComponent → javax.swing.JPanel → StatusIconTextField

**All Implemented Interfaces:** ImageObserver, MenuContainer, Serializable, Accessible

## Description

A wrapper for a `JTextField` capable of showing a warning icon.

**See Also:**
- Serialized Form

## Fields

### textField

```java
final JTextField textField
```

## Constructors

### StatusIconTextField

```java
StatusIconTextField(JTextField field)
```

## Methods

### addFocusListener

```java
void addFocusListener(FocusListener l)
```

### addKeyListener

```java
void addKeyListener(KeyListener l)
```

### changeTextFieldState

```java
void changeTextFieldState(TextFieldState value)
```

### getText

```java
String getText()
```

### isOptimizedDrawingEnabled

```java
boolean isOptimizedDrawingEnabled()
```

### removeFocusListener

```java
void removeFocusListener(FocusListener l)
```

### removeKeyListener

```java
void removeKeyListener(KeyListener l)
```

### requestFocus

```java
void requestFocus()
```

### setChangeListener

```java
void setChangeListener(Runnable callback)
```

### setEnabled

```java
void setEnabled(boolean enabled)
```

### setIcon

```java
void setIcon(Icon icon)
```

### setIconToolTip

```java
void setIconToolTip(String text)
```

### setText

```java
void setText(String text)
```

### setToolTipText

```java
void setToolTipText(String text)
```

### setVisible

```java
void setVisible(boolean aFlag)
```

---

# StatusIconTextField

## Inheritance

**Methods inherited from class:** `java.awt.Component`
- `action`
- `add`
- `addComponentListener`
- `addHierarchyBoundsListener`
- `addHierarchyListener`
- `addInputMethodListener`
- `addMouseListener`
- `addMouseMotionListener`
- `addMouseWheelListener`
- `bounds`
- `checkImage`
- `checkImage`
- `coalesceEvents`
- `contains`
- `createImage`
- `createImage`
- `createVolatileImage`
- `createVolatileImage`
- `disableEvents`
- `dispatchEvent`
- `enable`
- `enableEvents`
- `enableInputMethods`
- `firePropertyChange`
- `firePropertyChange`
- `firePropertyChange`
- `firePropertyChange`
- `firePropertyChange`
- `firePropertyChange`
- `getBackground`
- `getBounds`
- `getColorModel`
- `getComponentListeners`
- `getComponentOrientation`
- `getCursor`
- `getDropTarget`
- `getFocusCycleRootAncestor`
- `getFocusListeners`
- `getFocusTraversalKeysEnabled`
- `getFont`
- `getForeground`
- `getGraphicsConfiguration`
- `getHierarchyBoundsListeners`
- `getHierarchyListeners`
- `getIgnoreRepaint`
- `getInputContext`
- `getInputMethodListeners`
- `getInputMethodRequests`
- `getKeyListeners`
- `getLocale`
- `getLocation`
- `getLocationOnScreen`
- `getMouseListeners`
- `getMouseMotionListeners`
- `getMousePosition`
- `getMouseWheelListeners`
- `getName`
- `getParent`
- `getPropertyChangeListeners`
- `getPropertyChangeListeners`
- `getSize`
- `getToolkit`
- `getTreeLock`
- `gotFocus`
- `handleEvent`
- `hasFocus`
- `imageUpdate`
- `inside`
- `isBackgroundSet`
- `isCursorSet`
- `isDisplayable`
- `isEnabled`
- `isFocusable`
- `isFocusOwner`
- `isFocusTraversable`
- `isFontSet`
- `isForegroundSet`
- `isLightweight`
- `isMaximumSizeSet`
- `isMinimumSizeSet`
- `isPreferredSizeSet`
- `isShowing`
- `isValid`
- `isVisible`
- `keyDown`
- `keyUp`
- `list`
- `list`
- `list`
- `location`
- `lostFocus`
- `mouseDown`
- `mouseDrag`
- `mouseEnter`
- `mouseExit`
- `mouseMove`
- `mouseUp`
- `move`
- `nextFocus`
- `paintAll`
- `postEvent`
- `prepareImage`
- `prepareImage`
- `processComponentEvent`
- `processFocusEvent`
- `processHierarchyBoundsEvent`
- `processHierarchyEvent`
- `processInputMethodEvent`
- `processMouseWheelEvent`
- `remove`
- `removeComponentListener`
- `removeHierarchyBoundsListener`
- `removeHierarchyListener`
- `removeInputMethodListener`
- `removeMouseListener`
- `removeMouseMotionListener`
- `removeMouseWheelListener`
- `removePropertyChangeListener`
- `removePropertyChangeListener`
- `repaint`
- `repaint`
- `repaint`
- `requestFocus`
- `requestFocus`
- `requestFocusInWindow`
- `resize`
- `resize`
- `setBounds`
- `setBounds`
- `setComponentOrientation`
- `setCursor`
- `setDropTarget`
- `setFocusable`
- `setFocusTraversalKeysEnabled`
- `setIgnoreRepaint`
- `setLocale`
- `setLocation`
- `setLocation`
- `setMixingCutoutShape`
- `setName`
- `setSize`
- `setSize`
- `show`
- `show`
- `size`
- `toString`
- `transferFocus`
- `transferFocusBackward`
- `transferFocusUpCycle`

**Methods inherited from class:** `java.lang.Object`
- `clone`
- `equals`
- `finalize`
- `getClass`
- `hashCode`
- `notify`
- `notifyAll`
- `wait`
- `wait`
- `wait`

## Fields

### textField

```java
public final JTextField textField
```

## Constructors

### StatusIconTextField

```java
public StatusIconTextField(JTextField field)
```

## Methods

### isOptimizedDrawingEnabled

```java
public boolean isOptimizedDrawingEnabled()
```

**Overrides:** `isOptimizedDrawingEnabled` in class `JComponent`

### setText

```java
public void setText(String text)
```

### changeTextFieldState

```java
public void changeTextFieldState(velox.api.layer1.gui.TextFieldState value)
```

### getText

```java
public String getText()
```

### setIconToolTip

```java
public void setIconToolTip(String text)
```

### setIcon

```java
public void setIcon(Icon icon)
```

### requestFocus

```java
public void requestFocus()
```

**Overrides:** `requestFocus` in class `JComponent`

### setChangeListener

```java
public void setChangeListener(Runnable callback)
```

### addKeyListener

```java
public void addKeyListener(KeyListener l)
```

**Overrides:** `addKeyListener` in class `Component`

### removeKeyListener

```java
public void removeKeyListener(KeyListener l)
```

**Overrides:** `removeKeyListener` in class `Component`

### addFocusListener

```java
public void addFocusListener(FocusListener l)
```

**Overrides:** `addFocusListener` in class `Component`

### removeFocusListener

```java
public void removeFocusListener(FocusListener l)
```

**Overrides:** `removeFocusListener` in class `Component`

### setEnabled

```java
public void setEnabled(boolean enabled)
```

**Overrides:** `setEnabled` in class `JComponent`

### setVisible

```java
public void setVisible(boolean aFlag)
```

**Overrides:** `setVisible` in class `JComponent`

### setToolTipText

```java
public void setToolTipText(String text)
```

**Overrides:** `setToolTipText` in class `JComponent`