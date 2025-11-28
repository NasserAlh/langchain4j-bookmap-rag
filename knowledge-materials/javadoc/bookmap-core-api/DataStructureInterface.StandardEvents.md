---
source_file: DataStructureInterface.StandardEvents.html
package: velox.api.layer1.messages.indicators
classes: DataStructureInterface.StandardEvents
methods: TRADE, DEPTH, MBO, ORDER, values, valueOf
---

# DataStructureInterface.StandardEvents

**Package:** velox.api.layer1.messages.indicators

**Type:** Enum

**Enclosing Interface:** `DataStructureInterface`

**Inheritance:**
- `java.lang.Object`
- `java.lang.Enum<DataStructureInterface.StandardEvents>`
- `DataStructureInterface.StandardEvents`

**All Implemented Interfaces:** `Serializable`, `Comparable<DataStructureInterface.StandardEvents>`, `Constable`

## Enum Constants

### TRADE

```java
public static final DataStructureInterface.StandardEvents TRADE
```

Type corresponding to `TradeAggregationEvent`

### DEPTH

```java
public static final DataStructureInterface.StandardEvents DEPTH
```

Type corresponding to `DepthAggregationEvent`

### MBO

```java
public static final DataStructureInterface.StandardEvents MBO
```

Type corresponding to `MboAggregationEvent`

### ORDER

```java
public static final DataStructureInterface.StandardEvents ORDER
```

Type corresponding to `OrderUpdatesExecutionsAggregationEvent`

## Methods

### values

```java
public static DataStructureInterface.StandardEvents[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** An array containing the constants of this enum class, in the order they are declared

### valueOf

```java
public static DataStructureInterface.StandardEvents valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - The name of the enum constant to be returned

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null