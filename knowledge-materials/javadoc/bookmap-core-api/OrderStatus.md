---
source_file: OrderStatus.html
package: velox.api.layer1.data
classes: OrderStatus
methods: WORKING, CANCELLED, FILLED, INACTIVE, REJECTED, PENDINGCANCEL, PENDINGSUBMIT, PENDINGMODIFY, SUSPENDED, DISCONNECTED, code, values, valueOf, valueOf, valueOfLoose, isActive
---

# OrderStatus

**Package:** velox.api.layer1.data

**Type:** Enum

**Inheritance:**
- `java.lang.Object`
- `java.lang.Enum<OrderStatus>`
- `velox.api.layer1.data.OrderStatus`

**All Implemented Interfaces:** `Serializable`, `Comparable<OrderStatus>`, `Constable`

## Description

Order status

## Enum Constants

### WORKING
```java
public static final OrderStatus WORKING
```

### CANCELLED
```java
public static final OrderStatus CANCELLED
```

### FILLED
```java
public static final OrderStatus FILLED
```

### INACTIVE
```java
@Deprecated
public static final OrderStatus INACTIVE
```

**Deprecated**

### REJECTED
```java
public static final OrderStatus REJECTED
```

### PENDING_CANCEL
```java
public static final OrderStatus PENDING_CANCEL
```

### PENDING_SUBMIT
```java
public static final OrderStatus PENDING_SUBMIT
```

### PENDING_MODIFY
```java
public static final OrderStatus PENDING_MODIFY
```

### SUSPENDED
```java
public static final OrderStatus SUSPENDED
```

e.g. child orders in brackets

### DISCONNECTED
```java
public static final OrderStatus DISCONNECTED
```

## Fields

### code
```java
public final int code
```

Code used in recorded files

## Methods

### values
```java
public static OrderStatus[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** an array containing the constants of this enum class, in the order they are declared

### valueOf
```java
public static OrderStatus valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - the name of the enum constant to be returned

**Returns:** the enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - if this enum class has no constant with the specified name
- `NullPointerException` - if the argument is null

### valueOf
```java
public static OrderStatus valueOf(int code)
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
public static OrderStatus valueOfLoose(String name)
```

Parse order status from string that might not exactly match the enum.

**Parameters:**
- `name` - string to parse

**Returns:** parsed order type

### isActive
```java
public boolean isActive()
```