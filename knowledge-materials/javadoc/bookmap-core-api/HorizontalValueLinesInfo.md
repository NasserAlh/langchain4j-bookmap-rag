---
source_file: HorizontalValueLinesInfo.html
package: velox.api.layer1.messages.indicators
classes: HorizontalValueLinesInfo
methods: getHorizontalLines
---

# HorizontalValueLinesInfo

**Package:** velox.api.layer1.messages.indicators

**Type:** Interface

## Description

Allows to draw any number of horizontal lines at given values.

## Methods

### getHorizontalLines

```java
Map<Double, String> getHorizontalLines(String alias)
```

**Parameters:**
- `alias` - For which lines are drawn

**Returns:** Mapping from value of line to color of line. Color must be one of defined by `IndicatorColorScheme`