---
source_file: Layer1ApiUserMessageModifyIndicator.GraphType.html
package: velox.api.layer1.messages.indicators
classes: Layer1ApiUserMessageModifyIndicator.GraphType
methods: BOTTOM, PRIMARY, NONE, values, valueOf
---

# Layer1ApiUserMessageModifyIndicator.GraphType

**Package:** velox.api.layer1.messages.indicators

**Type:** Enum

**Enclosing class:** `Layer1ApiUserMessageModifyIndicator`

**Inheritance:**
- `java.lang.Object`
- `java.lang.Enum<Layer1ApiUserMessageModifyIndicator.GraphType>`
- `Layer1ApiUserMessageModifyIndicator.GraphType`

**All Implemented Interfaces:** `Serializable`, `Comparable<Layer1ApiUserMessageModifyIndicator.GraphType>`, `Constable`

## Enum Constants

### BOTTOM

```java
public static final Layer1ApiUserMessageModifyIndicator.GraphType BOTTOM
```

Display graph in the bottom chart. Good option for your first indicator.

### PRIMARY

```java
public static final Layer1ApiUserMessageModifyIndicator.GraphType PRIMARY
```

Display over main chart. Allows overlaying different types of data.

### NONE

```java
public static final Layer1ApiUserMessageModifyIndicator.GraphType NONE
```

No indicator graph.

## Methods

### values

```java
public static Layer1ApiUserMessageModifyIndicator.GraphType[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** An array containing the constants of this enum class, in the order they are declared

### valueOf

```java
public static Layer1ApiUserMessageModifyIndicator.GraphType valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - The name of the enum constant to be returned

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null