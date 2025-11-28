---
source_file: OrderType.html
package: velox.api.layer1.data
classes: OrderType
methods: STPLMT, LMT, MKT, STP, code, ordinalNumber, values, valueOf, toLocalizedString, valueOf, valueOfLoose, getTypeFromPrices
---

# OrderType

**Package:** velox.api.layer1.data

**Type:** Enum

**Inheritance:**
- `java.lang.Object`
- `java.lang.Enum<OrderType>`
- `velox.api.layer1.data.OrderType`

**All Implemented Interfaces:** `Serializable`, `Comparable<OrderType>`, `Constable`

## Description

Order type

## Enum Constants

### STP_LMT
```java
public static final OrderType STP_LMT
```

### LMT
```java
public static final OrderType LMT
```

### MKT
```java
public static final OrderType MKT
```

### STP
```java
public static final OrderType STP
```

## Fields

### code
```java
public final int code
```

Code used in recorded files

### ordinalNumber
```java
public final int ordinalNumber
```

Specifies the order of OrderType values, for example for sorting.

## Methods

### values
```java
public static OrderType[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** an array containing the constants of this enum class, in the order they are declared

### valueOf
```java
public static OrderType valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - the name of the enum constant to be returned

**Returns:** the enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - if this enum class has no constant with the specified name
- `NullPointerException` - if the argument is null

### toLocalizedString
```java
public String toLocalizedString()
```

### valueOf
```java
public static OrderType valueOf(int code)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `code` - the name of the enum constant to be returned

**Returns:** the enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - if this enum class has no constant with the specified name
- `NullPointerException` - if the argument is null

### valueOfLoose
```java
public static OrderType valueOfLoose(String name)
```

Parse order type from string that might not exactly match the enum.

**Parameters:**
- `name` - string to parse

**Returns:** order type

### getTypeFromPrices
```java
public static OrderType getTypeFromPrices(double stopPrice, double limitPrice)
```