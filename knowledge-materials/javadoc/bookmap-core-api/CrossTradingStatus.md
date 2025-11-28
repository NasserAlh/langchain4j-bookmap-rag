---
source_file: CrossTradingStatus.html
package: velox.api.layer1.data
classes: CrossTradingStatus
methods: STARTED, STOPPED, values, valueOf
---

# CrossTradingStatus

**Package:** velox.api.layer1.data

**Type:** Enum

## Inheritance

- java.lang.Object
- java.lang.Enum<CrossTradingStatus>
- velox.api.layer1.data.CrossTradingStatus

**All Implemented Interfaces:** Serializable, Comparable<CrossTradingStatus>, Constable

## Enum Constants

### STARTED

```java
public static final CrossTradingStatus STARTED
```

### STOPPED

```java
public static final CrossTradingStatus STOPPED
```

## Methods

### values

```java
public static CrossTradingStatus[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** An array containing the constants of this enum class, in the order they are declared

### valueOf

```java
public static CrossTradingStatus valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - The name of the enum constant to be returned

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null