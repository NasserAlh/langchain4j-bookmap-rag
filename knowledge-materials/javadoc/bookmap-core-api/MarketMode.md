---
source_file: MarketMode.html
package: velox.api.layer1.data
classes: MarketMode
methods: OPEN, HALTED, ENDOFDAY, code, values, valueOf, valueOf, valueOfLoose
---

# MarketMode

**Package:** velox.api.layer1.data

**Type:** Enum

**Inheritance:**
- java.lang.Object
- java.lang.Enum<MarketMode>
- velox.api.layer1.data.MarketMode

**All Implemented Interfaces:** Serializable, Comparable<MarketMode>, Constable

## Enum Constants

### OPEN
```java
public static final MarketMode OPEN
```

### HALTED
```java
public static final MarketMode HALTED
```

### END_OF_DAY
```java
public static final MarketMode END_OF_DAY
```

## Fields

### code
```java
public final short code
```

Code used in recorded files

## Methods

### values
```java
public static MarketMode[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** An array containing the constants of this enum class, in the order they are declared

### valueOf
```java
public static MarketMode valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - The name of the enum constant to be returned

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null

### valueOf
```java
public static MarketMode valueOf(int code)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `code` - The name of the enum constant to be returned

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null

### valueOfLoose
```java
public static MarketMode valueOfLoose(String name)
```