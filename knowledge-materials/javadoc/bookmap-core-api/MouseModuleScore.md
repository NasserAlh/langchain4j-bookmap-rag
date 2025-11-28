---
source_file: MouseModuleScore.html
package: velox.api.layer1.layers.strategies.interfaces
classes: MouseModuleScore
methods: MAX, TRADINGCLICK, GRAPHLAYERSMODULESMAX, GRAPHLAYERSMODULESMIN, CHARTDRAG, MIN, NONE, score, values, valueOf
---

# MouseModuleScore

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Enum

**Inheritance:**
- `java.lang.Object`
- `java.lang.Enum<MouseModuleScore>`
- `MouseModuleScore`

**All Implemented Interfaces:** `Serializable`, `Comparable<MouseModuleScore>`, `Constable`

## Description

Helper scores for values returned from `CanvasMouseListener.getEventScore(velox.api.layer1.layers.strategies.interfaces.CanvasMouseEvent)` and `CanvasContextMenuProvider.getRightClickEventScore(velox.api.layer1.layers.strategies.interfaces.CanvasMouseEvent)`. These scores allow your module to have higher priority than a certain part of Bookmap.

**See Also:**
- `CanvasMouseListener.getEventScore(velox.api.layer1.layers.strategies.interfaces.CanvasMouseEvent)`
- `CanvasContextMenuProvider.getRightClickEventScore(velox.api.layer1.layers.strategies.interfaces.CanvasMouseEvent)`

## Enum Constants

### MAX

```java
public static final MouseModuleScore MAX
```

Max possible score for 3-rd party modules

### TRADING_CLICK

```java
public static final MouseModuleScore TRADING_CLICK
```

Max score for click in trading area (right of timeline)

### GRAPH_LAYERS_MODULES_MAX

```java
public static final MouseModuleScore GRAPH_LAYERS_MODULES_MAX
```

Max score for Bookmap-rendered layers on chart, such as trade circles, candlesticks, indicator lines, icons, BBO, vwap

### GRAPH_LAYERS_MODULES_MIN

```java
public static final MouseModuleScore GRAPH_LAYERS_MODULES_MIN
```

### CHART_DRAG

```java
public static final MouseModuleScore CHART_DRAG
```

Max score for entering the drag mode

### MIN

```java
public static final MouseModuleScore MIN
```

### NONE

```java
public static final MouseModuleScore NONE
```

Respond with this score if you are not interested in this event

## Fields

### score

```java
public final int score
```

## Methods

### values

```java
public static MouseModuleScore[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** an array containing the constants of this enum class, in the order they are declared

### valueOf

```java
public static MouseModuleScore valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - the name of the enum constant to be returned

**Returns:** the enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - if this enum class has no constant with the specified name
- `NullPointerException` - if the argument is null