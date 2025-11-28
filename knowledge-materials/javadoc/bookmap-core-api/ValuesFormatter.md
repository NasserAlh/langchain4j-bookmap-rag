---
source_file: ValuesFormatter.html
package: velox.api.layer1.messages.indicators
classes: ValuesFormatter
methods: formatTooltip, formatTooltip, formatWidget, formatWidget
---

# ValuesFormatter

**Package:** velox.api.layer1.messages.indicators

**Type:** Interface

## Description

Use this interface to use custom formatting for your indicator's tooltip and/or widgets values.

## Methods

### formatTooltip

```java
String formatTooltip(double value, double minValue, double maxValue, int pixelsCount)
```

This method will be called only if you don't implement `formatTooltip(String, double, double, double, int)`

**Parameters:**
- `value` - Value displayed in tooltip
- `minValue` - Bottom panel bottom pixel value
- `maxValue` - Bottom panel top pixel value
- `pixelsCount` - Height of bottom panel

**Returns:** Formatted value that will be displayed in tooltip, null if default formatter is to be used

### formatTooltip

```java
default String formatTooltip(String alias, double value, double minValue, double maxValue, int pixelsCount)
```

This is the main alias-specific method to format tooltip. If you implement it, `formatTooltip(double, double, double, int)` will not be called. Otherwise, it will be called to format.

**Parameters:**
- `alias` - Of instrument
- `value` - Value displayed in tooltip
- `minValue` - Bottom panel bottom pixel value
- `maxValue` - Bottom panel top pixel value
- `pixelsCount` - Height of bottom panel

**Returns:** Formatted value that will be displayed in tooltip, null if default formatter is to be used

### formatWidget

```java
String formatWidget(double value)
```

This method will be called only if you don't implement `formatWidget(String, double)`

**Parameters:**
- `value` - Value displayed in widget

**Returns:** Formatted value that will be displayed in widget

### formatWidget

```java
default String formatWidget(String alias, double value)
```

This is the main alias-specific method to format widget. If you implement it, `formatTooltip(double, double, double, int)` will not be called. Otherwise, it will be called to format.

**Parameters:**
- `alias` - Of instrument
- `value` - Value displayed in widget

**Returns:** Formatted value that will be displayed in widget