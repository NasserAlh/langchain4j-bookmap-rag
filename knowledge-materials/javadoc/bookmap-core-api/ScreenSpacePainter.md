---
source_file: ScreenSpacePainter.html
package: velox.api.layer1.layers.strategies.interfaces
classes: ScreenSpacePainter
methods: onMoveStart, onMoveEnd, onHeatmapTimeLeft, onHeatmapActiveTimeWidth, onHeatmapFullTimeWidth, onHeatmapPriceBottom, onHeatmapPriceHeight, onHeatmapPixelsLeft, onHeatmapActivePixelsWidth, onHeatmapFullPixelsWidth, onHeatmapPixelsBottom, onHeatmapPixelsHeight, onRightOfTimelineLeft, onRightOfTimelineWidth, dispose
---

# ScreenSpacePainter

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Interface

**All Known Subinterfaces:** ScreenSpacePainterAdapter

## Description

Provides information regarding where user is looking at. Expected reaction is to create/update shapes. Note: you are not limited to creating/updating/deleting shapes in response to the methods of this interface, shapes can be updated at any time.

## Methods

### onMoveStart

```java
void onMoveStart()
```

Called after view was moved before transmitting actual changes, allows batch processing of changes.

---

### onMoveEnd

```java
void onMoveEnd()
```

Called after transmitting actual changes, allows batch processing of changes.

---

### onHeatmapTimeLeft

```java
void onHeatmapTimeLeft(long heatmapTimeLeft)
```

Time corresponding to left edge of heatmap changed.

**Parameters:**
- `heatmapTimeLeft` - New time in nanoseconds

---

### onHeatmapActiveTimeWidth

```java
void onHeatmapActiveTimeWidth(long heatmapActiveTimeWidth)
```

Width of the heatmap area corresponding to data (in nanoseconds) changed.

**Parameters:**
- `heatmapActiveTimeWidth` - New width in nanoseconds

---

### onHeatmapFullTimeWidth

```java
void onHeatmapFullTimeWidth(long heatmapFullTimeWidth)
```

Width of the heatmap area (including the part containing no data, in nanoseconds) changed.

**Parameters:**
- `heatmapFullTimeWidth` - New width in nanoseconds

---

### onHeatmapPriceBottom

```java
void onHeatmapPriceBottom(long heatmapPriceBottom)
```

Lowest price displayed on heatmap changed.

**Parameters:**
- `heatmapPriceBottom` - New lowest price as a number of level (multiply by `InstrumentInfo.pips` to get human-readable price).

---

### onHeatmapPriceHeight

```java
void onHeatmapPriceHeight(long heatmapPriceHeight)
```

Number of levels displayed on heatmap changed.

**Parameters:**
- `heatmapPriceHeight` - New number of levels

---

### onHeatmapPixelsLeft

```java
void onHeatmapPixelsLeft(int heatmapPixelsLeft)
```

Position of left edge of heatmap on inside the window changed.

*In the current version of Screen Space Painter API this method is useless for 3-rd party addons.*

**Parameters:**
- `heatmapPixelsLeft` - New x coordinate in pixels

---

### onHeatmapActivePixelsWidth

```java
void onHeatmapActivePixelsWidth(int heatmapActivePixelsWidth)
```

Width of the heatmap area corresponding to data (in pixels) changed.

**Parameters:**
- `heatmapActivePixelsWidth` - 

---

### onHeatmapFullPixelsWidth

```java
void onHeatmapFullPixelsWidth(int heatmapFullPixelsWidth)
```

Width of the heatmap area (including the part containing no data, in pixels) changed.

**Parameters:**
- `heatmapFullPixelsWidth` - New width in pixels

---

### onHeatmapPixelsBottom

```java
void onHeatmapPixelsBottom(int heatmapPixelsBottom)
```

Position of bottom edge of heatmap on inside the window changed. Y coordinate increases when going up (same direction as price increase).

*In the current version of Screen Space Painter API this method is useless for 3-d party addons.*

**Parameters:**
- `heatmapPixelsBottom` - New y coordinate in pixels.

---

### onHeatmapPixelsHeight

```java
void onHeatmapPixelsHeight(int heatmapPixelsHeight)
```

Height of the area representing the heatmap (in pixels) changed.

**Parameters:**
- `heatmapPixelsHeight` - New heatmap height in pixels

---

### onRightOfTimelineLeft

```java
void onRightOfTimelineLeft(int rightOfTimelineLeft)
```

X coordinate of the timeline has changed (in pixels). Note, that in drag mode when timeline is not visible it's considered to be in the leftmost pixel of heatmap.

**Parameters:**
- `rightOfTimelineLeft` - New x coordinate of timeline in pixels

---

### onRightOfTimelineWidth

```java
void onRightOfTimelineWidth(int rightOfTimelineWidth)
```

Width of the area to the right of timeline (in pixels) has changed.

**Parameters:**
- `rightOfTimelineWidth` - New width of the area to the right of timeline

---

### dispose

```java
void dispose()
```

Painter is no longer needed. When this method is called all resources should be disposed (including created `ScreenSpaceCanvas`).