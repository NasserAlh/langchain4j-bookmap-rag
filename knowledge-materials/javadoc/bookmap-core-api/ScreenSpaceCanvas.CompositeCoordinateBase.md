---
source_file: ScreenSpaceCanvas.CompositeCoordinateBase.html
package: velox.api.layer1.layers.strategies.interfaces
classes: ScreenSpaceCanvas.CompositeCoordinateBase
methods: DATAZERO, PIXELZERO, RELATIVE, values, valueOf
---

# ScreenSpaceCanvas.CompositeCoordinateBase

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Enum

**Enclosing interface:** `ScreenSpaceCanvas`

**Inheritance:**
- `java.lang.Object`
- `java.lang.Enum<ScreenSpaceCanvas.CompositeCoordinateBase>`
- `ScreenSpaceCanvas.CompositeCoordinateBase`

**All Implemented Interfaces:** `Serializable`, `Comparable<ScreenSpaceCanvas.CompositeCoordinateBase>`, `Constable`

## Enum Constants

### DATA_ZERO

```java
public static final ScreenSpaceCanvas.CompositeCoordinateBase DATA_ZERO
```

Handle price and pixels as offset relative to data axis zero

### PIXEL_ZERO

```java
public static final ScreenSpaceCanvas.CompositeCoordinateBase PIXEL_ZERO
```

Handle price and pixels as offset relative to pixels zero (left/bottom)

### RELATIVE

```java
public static final ScreenSpaceCanvas.CompositeCoordinateBase RELATIVE
```

This coordinate has no absolute meaning and only makes sense in combination with some other data

## Methods

### values

```java
public static ScreenSpaceCanvas.CompositeCoordinateBase[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** An array containing the constants of this enum class, in the order they are declared

### valueOf

```java
public static ScreenSpaceCanvas.CompositeCoordinateBase valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - The name of the enum constant to be returned

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null