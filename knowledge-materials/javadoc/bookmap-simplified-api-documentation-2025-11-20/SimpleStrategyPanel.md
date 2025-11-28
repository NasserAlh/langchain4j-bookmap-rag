---
source_file: SimpleStrategyPanel.html
package: velox.api.layer1.simplified
classes: SimpleStrategyPanel, SimpleStrategyPanel
methods: SimpleStrategyPanel, addItem, addItems, addItems, addItems, SimpleStrategyPanel, addItem, addItems, addItems, addItems
---

# SimpleStrategyPanel

**Package:** velox.api.layer1.simplified

**Type:** Class

**Inheritance:** java.lang.Object → java.awt.Component → java.awt.Container → javax.swing.JComponent → javax.swing.JPanel → velox.gui.StrategyPanel → SimpleStrategyPanel

**All Implemented Interfaces:** ImageObserver, MenuContainer, Serializable, Accessible

## Description

The class creates a Strategy panel and provides simple methods for placing components into it.

**See Also:**
- Serialized Form

---

## Constructors

### SimpleStrategyPanel

```java
SimpleStrategyPanel(String title)
```

---

## Methods

### addItem

```java
void addItem(String label, Component c)
```

Places two components.

### addItems

```java
void addItems(Component... components)
```

Places an arbitrary number of parameters of the Component type.

### addItems

```java
void addItems(Component[] components, int[] gridWidths)
```

Places an arbitrary number of parameters of the Component type.

### addItems

```java
void addItems(String label, Component... components)
```

Places two or more components.

---

## Inherited Methods

**From velox.gui.StrategyPanel:**
- `addReloadListener`
- `getTitle`
- `removeReloadListenersIf`
- `requestReload`
- `setEnabled`
- `setLocalizedTitle`

**From javax.swing.JPanel:**
- `getAccessibleContext`
- `getUI`
- `getUIClassID`
- `paramString`
- `setUI`
- `updateUI`

**From javax.swing.JComponent:**
- `addAncestorListener`
- `addNotify`
- `addVetoableChangeListener`
- `computeVisibleRect`
- `contains`
- `createToolTip`
- `disable`
- `enable`
- `firePropertyChange`
- `fireVetoableChange`
- `getActionForKeyStroke`
- `getActionMap`
- `getAlignmentX`
- `getAlignmentY`
- `getAncestorListeners`
- `getAutoscrolls`
- `getBaseline`
- `getBaselineResizeBehavior`
- `getBorder`
- `getBounds`
- `getClientProperty`
- `getComponentGraphics`
- `getComponentPopupMenu`
- `getConditionForKeyStroke`
- `getDebugGraphicsOptions`
- `getDefaultLocale`
- `getFontMetrics`
- `getGraphics`
- `getHeight`
- `getInheritsPopupMenu`
- `getInputMap`
- `getInputVerifier`
- `getInsets`
- `getListeners`
- `getLocation`
- `getMaximumSize`
- `getMinimumSize`
- `getNextFocusableComponent`
- `getPopupLocation`
- `getPreferredSize`
- `getRegisteredKeyStrokes`
- `getRootPane`
- `getSize`
- `getToolTipLocation`
- `getToolTipText`
- `getTopLevelAncestor`
- `getTransferHandler`
- `getVerifyInputWhenFocusTarget`
- `getVetoableChangeListeners`
- `getVisibleRect`
- `getWidth`
- `getX`
- `getY`
- `grabFocus`
- `hide`
- `isDoubleBuffered`
- `isLightweightComponent`
- `isManagingFocus`
- `isOpaque`
- `isOptimizedDrawingEnabled`
- `isPaintingForPrint`
- `isPaintingOrigin`
- `isPaintingTile`
- `isRequestFocusEnabled`
- `isValidateRoot`
- `paint`
- `paintBorder`
- `paintChildren`
- `paintComponent`
- `paintImmediately`
- `print`
- `printAll`
- `printBorder`
- `printChildren`
- `printComponent`
- `processComponentKeyEvent`
- `processKeyBinding`
- `processKeyEvent`
- `processMouseEvent`
- `processMouseMotionEvent`
- `putClientProperty`
- `registerKeyboardAction`
- `removeAncestorListener`
- `removeNotify`
- `removeVetoableChangeListener`
- `repaint`
- `requestDefaultFocus`
- `requestFocus`
- `requestFocusInWindow`
- `resetKeyboardActions`
- `reshape`
- `revalidate`
- `scrollRectToVisible`
- `setActionMap`
- `setAlignmentX`
- `setAlignmentY`
- `setAutoscrolls`
- `setBackground`
- `setBorder`
- `setComponentPopupMenu`
- `setDebugGraphicsOptions`
- `setDefaultLocale`
- `setDoubleBuffered`
- `setFocusTraversalKeys`
- `setFont`
- `setForeground`
- `setInheritsPopupMenu`
- `setInputMap`
- `setInputVerifier`
- `setMaximumSize`
- `setMinimumSize`
- `setNextFocusableComponent`
- `setOpaque`
- `setPreferredSize`
- `setRequestFocusEnabled`
- `setToolTipText`
- `setTransferHandler`
- `setUI`
- `setVerifyInputWhenFocusTarget`
- `setVisible`
- `unregisterKeyboardAction`
- `update`

**From java.awt.Container:**
- `add`
- `addContainerListener`
- `addImpl`
- `addPropertyChangeListener`
- `applyComponentOrientation`
- `areFocusTraversalKeysSet`
- `countComponents`
- `deliverEvent`
- `doLayout`
- `findComponentAt`
- `getComponent`
- `getComponentAt`
- `getComponentCount`
- `getComponents`
- `getComponentZOrder`
- `getContainerListeners`
- `getFocusTraversalKeys`
- `getFocusTraversalPolicy`
- `getLayout`
- `getMousePosition`
- `insets`
- `invalidate`
- `isAncestorOf`
- `isFocusCycleRoot`
- `isFocusTraversalPolicyProvider`
- `isFocusTraversalPolicySet`
- `layout`
- `list`
- `locate`
- `minimumSize`
- `paintComponents`
- `preferredSize`
- `printComponents`
- `processContainerEvent`
- `processEvent`
- `remove`
- `removeAll`
- `removeContainerListener`
- `setComponentZOrder`
- `setFocusCycleRoot`
- `setFocusTraversalPolicy`
- `setFocusTraversalPolicyProvider`
- `setLayout`
- `transferFocusDownCycle`
- `validate`
- `validateTree`

---

# SimpleStrategyPanel

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

### SimpleStrategyPanel

```java
public SimpleStrategyPanel(String title)
```

## Methods

### addItem

```java
public void addItem(String label, Component c)
```

Places two components. The first is a JLabel instance with a specified text (label). Each component uses one cell in a row.

**Parameters:**
- `label` - The text for the JLabel
- `c` - The component to place alongside the label

---

### addItems

```java
public void addItems(String label, Component... components)
```

Places two or more components. The first is a JLabel instance with a specified text (label). The rest is an arbitrary number of parameters of the Component type. Each component uses one cell in a row.

**Parameters:**
- `label` - The text for the JLabel
- `components` - Variable number of components to place

---

### addItems

```java
public void addItems(Component... components)
```

Places an arbitrary number of parameters of the Component type. Each component uses one cell in a row.

**Parameters:**
- `components` - Variable number of components to place

---

### addItems

```java
public void addItems(Component[] components, int[] gridWidths)
```

Places an arbitrary number of parameters of the Component type. Number of cells in a row that the component uses must be specified. The second parameter (gridWidths) is an array of number of cells that components use. For example, an array of {1, 2, 4} specifies 1 cell for components[0], 2 cells for components[1], and 4 cells for component[2]. The components length and the gridWidths length must be equal, otherwise an exception will be thrown.

**Parameters:**
- `components` - Array of components to place
- `gridWidths` - Array specifying the number of grid cells each component should occupy