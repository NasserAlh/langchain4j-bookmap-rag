---
source_file: Indicator.html
package: velox.api.layer1.simplified
classes: Indicator
methods: addPoint, addIcon, setColor, setWidth, setLineStyle, setRenderPriority, setAxisRules, setWidgetRules
---

# Indicator

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Known Subinterfaces:** IndicatorModifiable

## Description

Indicator representing a line.

## Methods

### addPoint

```java
void addPoint(double value)
```

Set new line Y coordinate in indicator coordinates space. `Double.NaN` to stop drawing (can be imagined as switching to invisible color)

**Parameters:**
- `value` - New value

### addIcon

```java
void addIcon(double value, BufferedImage icon, int iconCenterX, int iconCenterY)
```

Add icon rendered in a point corresponding to specified value

**Parameters:**
- `value` - Value defining vertical position of an icon
- `icon` - The icon itself
- `iconCenterX` - X coordinate on the image that will be matched to event time
- `iconCenterY` - Y coordinate on the image that will be matched to event price

### setColor

```java
void setColor(Color color)
```

Set new line color. Applied immediately.

**Parameters:**
- `color` - New line color

### setWidth

```java
void setWidth(int width)
```

Set new line width. Applied immediately.

**Parameters:**
- `width` - Width in pixels

### setLineStyle

```java
void setLineStyle(LineStyle lineStyle)
```

Set new line style. Applied immediately.

**Parameters:**
- `lineStyle` - New line style

### setRenderPriority

```java
void setRenderPriority(int renderPriority)
```

Essentially controls Z (depth) coordinate of the line. Lines with higher values will be "closer" to the screen (on top of the ones with lower values). See `Layer1ApiUserMessageModifyIndicator.LayerRenderPriority` for reference values. You should normally specify the position relative to one of the values specified in it.

**Parameters:**
- `renderPriority` - Requested render priority of the layer

### setAxisRules

```java
void setAxisRules(AxisRules axisRules)
```

Set rules for selecting indicator range. Please keep in mind, that when part of `AxisGroup` this method should not be called directly, instead call corresponding `AxisGroup.setAxisRules(AxisRules)`

**Parameters:**
- `axisRules` - Object describing the rules

### setWidgetRules

```java
void setWidgetRules(WidgetRules widgetRules)
```