---
source_file: IndicatorContextMenuInformation.html
package: velox.api.layer1.messages.indicators
classes: IndicatorContextMenuInformation
methods: getGraphContextMenuItems, getWidgetContextMenuItems
---

# IndicatorContextMenuInformation

**Package:** velox.api.layer1.messages.indicators

**Type:** Interface

## Description

Provide information about all context menu entries for:
- graph context menu (RMB click on graph line)
- widget context menu (RMB click on widget)

## Methods

### getGraphContextMenuItems

```java
JMenuItem[] getGraphContextMenuItems(long time)
```

**Parameters:**
- `time` - Graph time at x position where click was done

**Returns:** Array of menu items, that should be put in **graph** context menu. Order of items will follow order of this array. null or empty array if no strategy specific entries are desired

### getWidgetContextMenuItems

```java
JMenuItem[] getWidgetContextMenuItems()
```

**Returns:** Array of menu items, that should be put in **widget** context menu. Order of items will follow order of this array. null or empty array if no strategy specific entries are desired