---
source_file: GuiUtils.html
package: velox.gui.utils
classes: GuiUtils
methods: GuiUtils.ResetControlPanelCallbacks, GuiUtils, setPanelEnabled, doForEachComponentInPanel, getPanelResetPoints, getResetControlPanel, getCommonGbc
---

# GuiUtils

**Package:** velox.gui.utils

**Type:** Class

**Inheritance:** java.lang.Object → velox.gui.utils.GuiUtils

## Nested Classes

### GuiUtils.ResetControlPanelCallbacks

**Type:** Interface

## Constructors

### GuiUtils

```java
public GuiUtils()
```

## Methods

### setPanelEnabled

```java
public static void setPanelEnabled(JPanel panel, Boolean isEnabled)
```

Enables or disables all elements within the panel.

**Parameters:**
- `panel` - 
- `isEnabled` - 

### doForEachComponentInPanel

```java
public static void doForEachComponentInPanel(JPanel panel, Consumer<Component> action)
```

Calls action with each component in the panel including the panel itself.

**Parameters:**
- `panel` - 
- `action` - 

### getPanelResetPoints

```java
public static velox.api.layer1.common.Utils.Pair<JPanel, JScrollPane> getPanelResetPoints()
```

### getResetControlPanel

```java
public static JPanel getResetControlPanel(GuiUtils.ResetControlPanelCallbacks callbacks, JPanel panelResetPoints, boolean isSessionStartTimeEnabled)
```

### getCommonGbc

```java
public static GridBagConstraints getCommonGbc(int x, int y, int width)
```