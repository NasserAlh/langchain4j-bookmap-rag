---
source_file: OnlineCalculatable.DataCoordinateMarker.html
package: velox.api.layer1.layers.strategies.interfaces
classes: OnlineCalculatable.DataCoordinateMarker
methods: getMinY, getMaxY, getValueY, makeMarker
---

# OnlineCalculatable.DataCoordinateMarker

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Interface

**Enclosing Interface:** `OnlineCalculatable`

## Description

Marker bound to data coordinates. Useful for, say, bars.

## Methods

### getMinY

```java
double getMinY()
```

Y coordinate of the lower edge. Used to adjust the range of bottom panel. Doesn't have to be strictly equal to the actual icons edge coordinate, since it's only used for range adjustments. If you are OK with part of the icon being out of range, you can specify only the critical part boundaries.

### getMaxY

```java
double getMaxY()
```

Similar to `getMinY()`, but for the upper edge.

### getValueY

```java
double getValueY()
```

Value used for the widget/line

### makeMarker

```java
OnlineCalculatable.Marker makeMarker(Function<Double, Integer> yDataCoordinateToPixelFunction)
```

Creates a visual representation of the marker.

**Parameters:**
- `yDataCoordinateToPixelFunction` - Function that converts y data coordinate to pixel coordinate mapped to the chart pixels range. Pixel coordinates should be used to calculate `OnlineCalculatable.Marker` height and pixels offset. If data coordinate is out of chart limits, it is mapped to the pixels outside the chart pixels. The resulting pixel coordinate could end up being far off the actual chart boundaries, potentially yielding values that are extremely large or negative. Implementations should handle such cases, for instance, by ignoring or clamping those pixel values if they exceed practical bounds (e.g., 10,000 pixels max). You should handle these out-of-range values to avoid issues like excessive memory usage, crashes, or unpredictable drawing behavior.

**Returns:** Marker that is drawn on the chart or null if no marker should be drawn. Note that returning `null` indicates that no marker should be drawn for the given state or input, or if yDataCoordinateToPixelFunction returns a value that could not be displayed correctly on the chart.