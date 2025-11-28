---
source_file: CanvasMouseEvent.html
package: velox.api.layer1.layers.strategies.interfaces
classes: CanvasMouseEvent
methods: CanvasMouseEvent.CoordinateRequestType, sourceEvent, CanvasMouseEvent, getX, getY, toString
---

# CanvasMouseEvent

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Class

**Inheritance:** java.lang.Object → CanvasMouseEvent

## Description

Describes a `ScreenSpaceCanvas` related mouse event. Use `getX(velox.api.layer1.layers.strategies.interfaces.ScreenSpaceCanvas.HorizontalCoordinate, velox.api.layer1.layers.strategies.interfaces.CanvasMouseEvent.CoordinateRequestType)` and `getY(velox.api.layer1.layers.strategies.interfaces.ScreenSpaceCanvas.VerticalCoordinate, velox.api.layer1.layers.strategies.interfaces.CanvasMouseEvent.CoordinateRequestType)` coordinates to analyze where exactly on a chart the event occurred.

## Nested Classes

### CanvasMouseEvent.CoordinateRequestType

Use it in `getX(velox.api.layer1.layers.strategies.interfaces.ScreenSpaceCanvas.HorizontalCoordinate, velox.api.layer1.layers.strategies.interfaces.CanvasMouseEvent.CoordinateRequestType)` and `getY(velox.api.layer1.layers.strategies.interfaces.ScreenSpaceCanvas.VerticalCoordinate, velox.api.layer1.layers.strategies.interfaces.CanvasMouseEvent.CoordinateRequestType)` to get the result in pixels or data (*time in nanos* for X, *price in pips* for Y coordinates).

## Fields

### sourceEvent

```java
public final MouseEvent sourceEvent
```

A mouse event that produced this `CanvasMouseEvent`. Use it to analyze additional event-related data e.g. key modifiers or `MouseWheelEvent.getScrollAmount()` for mouse wheel event.

## Constructors

### CanvasMouseEvent

```java
public CanvasMouseEvent(CanvasPixelToDataBiMapper mapper, MouseEvent sourceEvent)
```

## Methods

### getX

```java
public ScreenSpaceCanvas.CompositeHorizontalCoordinate getX(ScreenSpaceCanvas.HorizontalCoordinate relativeTo, CanvasMouseEvent.CoordinateRequestType requestType)
```

Get `x` coordinate in canvas dimensions.  
*Note that if you use `requestType` with type `CanvasMouseEvent.CoordinateRequestType.PIXELS`, and the `relativeTo` has the base of type `ScreenSpaceCanvas.CompositeCoordinateBase.DATA_ZERO` it is possible to get an integer overflow during calculation, in this case you will get `Integer.MAX_VALUE` in the resulting `ScreenSpaceCanvas.CompositeHorizontalCoordinate.pixelsX`.*

**Parameters:**
- `relativeTo` - A base for the resulting coordinate. Usually you want to use `ScreenSpaceCanvas.RelativeHorizontalCoordinate.HORIZONTAL_DATA_ZERO` or `ScreenSpaceCanvas.RelativeHorizontalCoordinate.HORIZONTAL_PIXEL_ZERO` to get the result based on `ScreenSpaceCanvas.CompositeCoordinateBase.DATA_ZERO` or `ScreenSpaceCanvas.CompositeCoordinateBase.PIXEL_ZERO` respectively. Note that the `relativeTo` coordinate cannot have a base of `ScreenSpaceCanvas.CompositeCoordinateBase.RELATIVE`
- `requestType` - A horizontal coordinate can be represented in pixels or time. For that the resulting coordinate has 2 fields - `ScreenSpaceCanvas.CompositeHorizontalCoordinate.pixelsX` and `ScreenSpaceCanvas.CompositeHorizontalCoordinate.timeX`. Depending on the provided `requestType` one of the fields will have a value, another will be 0.

**Returns:** x coordinate, calculated relative to `relativeTo` coordinate, with representation specified by `requestType`

**Throws:**
- `IllegalArgumentException` - If the provided `relativeTo` has a base of type `ScreenSpaceCanvas.CompositeCoordinateBase.RELATIVE`

### getY

```java
public ScreenSpaceCanvas.CompositeVerticalCoordinate getY(ScreenSpaceCanvas.VerticalCoordinate relativeTo, CanvasMouseEvent.CoordinateRequestType requestType)
```

Get `y` coordinate in canvas dimensions.  
*Note that if you use `requestType` with type `CanvasMouseEvent.CoordinateRequestType.PIXELS`, and the `relativeTo` has the base of type `ScreenSpaceCanvas.CompositeCoordinateBase.DATA_ZERO` it is possible to get an integer overflow during calculation, in this case you will get `Integer.MAX_VALUE` in the resulting `ScreenSpaceCanvas.CompositeVerticalCoordinate.pixelsY`.*

**Parameters:**
- `relativeTo` - A base for the resulting coordinate. Usually you want to use `ScreenSpaceCanvas.RelativeVerticalCoordinate.VERTICAL_DATA_ZERO` or `ScreenSpaceCanvas.RelativeVerticalCoordinate.VERTICAL_PIXEL_ZERO` to get the result based on `ScreenSpaceCanvas.CompositeCoordinateBase.DATA_ZERO` or `ScreenSpaceCanvas.CompositeCoordinateBase.PIXEL_ZERO` respectively. Note that the `relativeTo` coordinate cannot have a base of `ScreenSpaceCanvas.CompositeCoordinateBase.RELATIVE`
- `requestType` - A vertical coordinate can be represented in pixels or number of price pips. For that the resulting coordinate has 2 fields - `ScreenSpaceCanvas.CompositeVerticalCoordinate.pixelsY` and `ScreenSpaceCanvas.CompositeVerticalCoordinate.dataY`. Depending on the provided `requestType` one of the fields will have a value, another will be 0.

**Returns:** y coordinate, calculated relative to `relativeTo` coordinate, with representation specified by `requestType`

**Throws:**
- `IllegalArgumentException` - If the provided `relativeTo` has a base of type `ScreenSpaceCanvas.CompositeCoordinateBase.RELATIVE`

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`