---
source_file: Layer1ApiUserMessageModifyIndicator.LayerRenderPriority.html
package: velox.api.layer1.messages.indicators
classes: Layer1ApiUserMessageModifyIndicator.LayerRenderPriority
methods: ABSOLUTETOP, TOP, ABOVEBBO, ABSOLUTEBOTTOM, priority, values, valueOf
---

# Layer1ApiUserMessageModifyIndicator.LayerRenderPriority

**Package:** velox.api.layer1.messages.indicators

**Type:** Enum

**Enclosing class:** `Layer1ApiUserMessageModifyIndicator`

**Inheritance:**
- `java.lang.Object`
- `java.lang.Enum<Layer1ApiUserMessageModifyIndicator.LayerRenderPriority>`
- `Layer1ApiUserMessageModifyIndicator.LayerRenderPriority`

**All Implemented Interfaces:** `Serializable`, `Comparable<Layer1ApiUserMessageModifyIndicator.LayerRenderPriority>`, `Constable`

## Enum Constants

### ABSOLUTE_TOP

```java
public static final Layer1ApiUserMessageModifyIndicator.LayerRenderPriority ABSOLUTE_TOP
```

Highest possible layer priority for indicator. No value more than this is allowed

### TOP

```java
public static final Layer1ApiUserMessageModifyIndicator.LayerRenderPriority TOP
```

Reasonable priority to be on top of all core bookmap layers  
Numerical value 1000

### ABOVE_BBO

```java
public static final Layer1ApiUserMessageModifyIndicator.LayerRenderPriority ABOVE_BBO
```

This priority corresponds to position just above BBO lines  
Numerical value 500

### ABSOLUTE_BOTTOM

```java
public static final Layer1ApiUserMessageModifyIndicator.LayerRenderPriority ABSOLUTE_BOTTOM
```

No value less then this is allowed. This corresponds to background layer value in bookmap  
Numerical value 0

## Fields

### priority

```java
public int priority
```

## Methods

### values

```java
public static Layer1ApiUserMessageModifyIndicator.LayerRenderPriority[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** An array containing the constants of this enum class, in the order they are declared

### valueOf

```java
public static Layer1ApiUserMessageModifyIndicator.LayerRenderPriority valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - The name of the enum constant to be returned

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null