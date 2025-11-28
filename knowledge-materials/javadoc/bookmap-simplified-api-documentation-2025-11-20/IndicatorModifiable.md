---
source_file: IndicatorModifiable.html
package: velox.api.layer1.simplified
classes: IndicatorModifiable
methods: addPoint, clear
extends: Indicator
---

# IndicatorModifiable

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Superinterfaces:** Indicator

## Inheritance

Extends `Indicator` interface

## Methods

### addPoint

```java
void addPoint(long timestamp, double value)
```

### clear

```java
void clear(long fromTimestamp, long toTimestamp)
```

## Methods Inherited from Indicator

- `addIcon(double, java.awt.image.BufferedImage, int, int)`
- `addPoint(double)`
- `setAxisRules(velox.api.layer1.simplified.AxisRules)`
- `setColor(java.awt.Color)`
- `setLineStyle(velox.api.layer1.simplified.LineStyle)`
- `setRenderPriority(int)`
- `setWidgetRules(velox.api.layer1.simplified.WidgetRules)`
- `setWidth(int)`