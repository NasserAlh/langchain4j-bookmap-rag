---
source_file: CanvasMouseEvent.CoordinateRequestType.html
package: velox.api.layer1.layers.strategies.interfaces
classes: CanvasMouseEvent.CoordinateRequestType
methods: DATA, PIXELS, values, valueOf
---

# CanvasMouseEvent.CoordinateRequestType

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Enum

**Enclosing class:** `CanvasMouseEvent`

**Inheritance:**
- `java.lang.Object`
- `java.lang.Enum<CanvasMouseEvent.CoordinateRequestType>`
- `CanvasMouseEvent.CoordinateRequestType`

**All Implemented Interfaces:** `Serializable`, `Comparable<CanvasMouseEvent.CoordinateRequestType>`, `Constable`

## Description

Use it in `CanvasMouseEvent.getX(velox.api.layer1.layers.strategies.interfaces.ScreenSpaceCanvas.HorizontalCoordinate, velox.api.layer1.layers.strategies.interfaces.CanvasMouseEvent.CoordinateRequestType)` and `CanvasMouseEvent.getY(velox.api.layer1.layers.strategies.interfaces.ScreenSpaceCanvas.VerticalCoordinate, velox.api.layer1.layers.strategies.interfaces.CanvasMouseEvent.CoordinateRequestType)` to get the result in pixels or data (*time in nanos* for X, *price in pips* for Y coordinates).

## Enum Constants

### DATA
```java
public static final CanvasMouseEvent.CoordinateRequestType DATA
```

### PIXELS
```java
public static final CanvasMouseEvent.CoordinateRequestType PIXELS
```

## Methods

### values

```java
public static CanvasMouseEvent.CoordinateRequestType[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** An array containing the constants of this enum class, in the order they are declared

### valueOf

```java
public static CanvasMouseEvent.CoordinateRequestType valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - The name of the enum constant to be returned

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null