---
source_file: ScreenSpaceCanvasFactory.ScreenSpaceCanvasType.html
package: velox.api.layer1.layers.strategies.interfaces
classes: ScreenSpaceCanvasFactory.ScreenSpaceCanvasType
methods: FULLWINDOW, HEATMAP, RIGHTOFTIMELINE, values, valueOf
---

# ScreenSpaceCanvasFactory.ScreenSpaceCanvasType

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Enum

**Enclosing interface:** `ScreenSpaceCanvasFactory`

## Inheritance

- `java.lang.Object`
- `java.lang.Enum<ScreenSpaceCanvasFactory.ScreenSpaceCanvasType>`
- `velox.api.layer1.layers.strategies.interfaces.ScreenSpaceCanvasFactory.ScreenSpaceCanvasType`

**All Implemented Interfaces:** `Serializable`, `Comparable<ScreenSpaceCanvasFactory.ScreenSpaceCanvasType>`, `Constable`

## Description

Area that canvas corresponds to

## Enum Constants

### FULL_WINDOW

```java
public static final ScreenSpaceCanvasFactory.ScreenSpaceCanvasType FULL_WINDOW
```

Entire window - does not work yet

### HEATMAP

```java
public static final ScreenSpaceCanvasFactory.ScreenSpaceCanvasType HEATMAP
```

### RIGHT_OF_TIMELINE

```java
public static final ScreenSpaceCanvasFactory.ScreenSpaceCanvasType RIGHT_OF_TIMELINE
```

## Methods

### values

```java
public static ScreenSpaceCanvasFactory.ScreenSpaceCanvasType[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** An array containing the constants of this enum class, in the order they are declared

### valueOf

```java
public static ScreenSpaceCanvasFactory.ScreenSpaceCanvasType valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - The name of the enum constant to be returned

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null