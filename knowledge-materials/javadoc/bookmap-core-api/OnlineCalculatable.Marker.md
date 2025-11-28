---
source_file: OnlineCalculatable.Marker.html
package: velox.api.layer1.layers.strategies.interfaces
classes: OnlineCalculatable.Marker
methods: markerY, iconOffsetX, iconOffsetY, icon, Marker, toString
---

# OnlineCalculatable.Marker

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Class

**Enclosing Interface:** `OnlineCalculatable`

**Inheritance:** `java.lang.Object` → `OnlineCalculatable.Marker`

## Description

If passed as value it will cause marker to be drawn at that place.

## Fields

### markerY

```java
public final double markerY
```

Vertical position of marker (price / pips)

### iconOffsetX

```java
public final int iconOffsetX
```

X component of icon offset. This allows you to position marker accordingly to it's content (e.g. right arrow will probably have this field equal to icon width)

### iconOffsetY

```java
public final int iconOffsetY
```

Y component of icon offset. This allows you to position marker accordingly to it's content (e.g. down arrow will probably have this field equal to icon height)

### icon

```java
public final BufferedImage icon
```

Image used as marker

## Constructors

### Marker

```java
public Marker(double markerY, int iconOffsetX, int iconOffsetY, BufferedImage icon)
```

Creates new marker

**Parameters:**
- `markerY` - Value for `markerY`
- `iconOffsetX` - Value for `iconOffsetX`
- `iconOffsetY` - Value for `iconOffsetY`
- `icon` - Value for `icon`. Icon will be cloned.

## Methods

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`