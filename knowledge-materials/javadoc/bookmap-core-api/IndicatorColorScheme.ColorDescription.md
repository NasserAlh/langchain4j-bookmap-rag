---
source_file: IndicatorColorScheme.ColorDescription.html
package: velox.api.layer1.messages.indicators
classes: IndicatorColorScheme.ColorDescription
methods: defaultColor, name, isDisplayedInColorDialog, isDisplayedInIndicatorPopup, ColorDescription, setDisplayedInIndicatorPopup, setDisplayedInColorDialog
---

# IndicatorColorScheme.ColorDescription

**Package:** velox.api.layer1.messages.indicators

**Type:** Class

**Enclosing Interface:** `IndicatorColorScheme`

**Inheritance:** `java.lang.Object` → `IndicatorColorScheme.ColorDescription`

## Fields

### defaultColor

```java
public final Color defaultColor
```

### name

```java
public final String name
```

Color name that user will see

### isDisplayedInColorDialog

```java
public boolean isDisplayedInColorDialog
```

### isDisplayedInIndicatorPopup

```java
public boolean isDisplayedInIndicatorPopup
```

## Constructors

### ColorDescription

```java
public ColorDescription(Class<?> strategyClass, String name, Color defaultColor, boolean isDisplayedInColorDialog)
```

**Parameters:**
- `strategyClass` - 
- `name` - Should consist only of a-zA-Z0-9 and spaces, and should not be empty
- `defaultColor` - 
- `isDisplayedInColorDialog` - If true, this color will be configurable via default color dialog (in indicators section), otherwise color will not be configurable there. **Note that color dialog is global (non alias specific)**

## Methods

### setDisplayedInIndicatorPopup

```java
public IndicatorColorScheme.ColorDescription setDisplayedInIndicatorPopup(boolean displayedInIndicatorPopup)
```

**Parameters:**
- `displayedInIndicatorPopup` - If true, this color will be configurable via right click menu for indicator, otherwise color will not be configurable there. Default: false

**Returns:** 

### setDisplayedInColorDialog

```java
public IndicatorColorScheme.ColorDescription setDisplayedInColorDialog(boolean displayedInColorDialog)
```

**Parameters:**
- `displayedInColorDialog` - If true, this color will be configurable via default color dialog (in indicators section), otherwise color will not be configurable there. Note that color dialog is global (non alias specific)

**Returns:**