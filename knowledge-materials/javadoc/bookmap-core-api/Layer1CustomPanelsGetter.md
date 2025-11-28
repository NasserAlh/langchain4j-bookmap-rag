---
source_file: Layer1CustomPanelsGetter.html
package: velox.api.layer1
classes: Layer1CustomPanelsGetter
methods: getCustomGuiFor
---

# Layer1CustomPanelsGetter

**Package:** velox.api.layer1

**Type:** Interface

## Description

Implement this interface if you want strategy to show custom panels when selected in strategies dialog.

## Methods

### getCustomGuiFor

```java
StrategyPanel[] getCustomGuiFor(String alias, String indicatorName)
```

This request is used when this strategy was selected in strategies dialog.

**Parameters:**
- `alias` - Alias of instrument that is currently opened by user
- `indicatorName` - If strategy was selected via settings button of strategies configuration popup this will be the name of indicator (useful if you have more then 1 indicator created by strategy), null if strategy was selected in strategies dialog with corresponding button click

**Returns:** Panels with specified titles that will be displayed in dialog when strategy is selected (first panel is topmost)