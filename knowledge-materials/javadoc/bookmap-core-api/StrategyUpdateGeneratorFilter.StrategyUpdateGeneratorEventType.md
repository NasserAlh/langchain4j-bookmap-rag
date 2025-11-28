---
source_file: StrategyUpdateGeneratorFilter.StrategyUpdateGeneratorEventType.html
package: velox.api.layer1.messages.indicators
classes: StrategyUpdateGeneratorFilter.StrategyUpdateGeneratorEventType
methods: INSTRUMENTS, DEPTHMBP, DEPTHMBO, TRADES, ORDERS, USERDATA, OTHER, values, valueOf
---

# StrategyUpdateGeneratorFilter.StrategyUpdateGeneratorEventType

**Package:** velox.api.layer1.messages.indicators

**Type:** Enum

**Enclosing Interface:** `StrategyUpdateGeneratorFilter`

**Inheritance:**
- `java.lang.Object`
- `java.lang.Enum<StrategyUpdateGeneratorFilter.StrategyUpdateGeneratorEventType>`
- `StrategyUpdateGeneratorFilter.StrategyUpdateGeneratorEventType`

**All Implemented Interfaces:**
- `java.io.Serializable`
- `java.lang.Comparable<StrategyUpdateGeneratorFilter.StrategyUpdateGeneratorEventType>`
- `java.lang.constant.Constable`

## Enum Constants

### INSTRUMENTS

```java
public static final StrategyUpdateGeneratorFilter.StrategyUpdateGeneratorEventType INSTRUMENTS
```

### DEPTH_MBP

```java
public static final StrategyUpdateGeneratorFilter.StrategyUpdateGeneratorEventType DEPTH_MBP
```

### DEPTH_MBO

```java
public static final StrategyUpdateGeneratorFilter.StrategyUpdateGeneratorEventType DEPTH_MBO
```

### TRADES

```java
public static final StrategyUpdateGeneratorFilter.StrategyUpdateGeneratorEventType TRADES
```

### ORDERS

```java
public static final StrategyUpdateGeneratorFilter.StrategyUpdateGeneratorEventType ORDERS
```

### USER_DATA

```java
public static final StrategyUpdateGeneratorFilter.StrategyUpdateGeneratorEventType USER_DATA
```

### OTHER

```java
public static final StrategyUpdateGeneratorFilter.StrategyUpdateGeneratorEventType OTHER
```

Everything not listed above. Typically it is relatively small portion of data

**Beware: if other type of data is introduced in next versions, all events of that type will no longer go under OTHER category. Use with caution**

## Methods

### values

```java
public static StrategyUpdateGeneratorFilter.StrategyUpdateGeneratorEventType[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** An array containing the constants of this enum class, in the order they are declared

### valueOf

```java
public static StrategyUpdateGeneratorFilter.StrategyUpdateGeneratorEventType valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - The name of the enum constant to be returned

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null