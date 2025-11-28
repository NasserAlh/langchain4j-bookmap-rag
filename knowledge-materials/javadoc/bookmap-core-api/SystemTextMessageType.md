---
source_file: SystemTextMessageType.html
package: velox.api.layer1.data
classes: SystemTextMessageType
methods: UNCLASSIFIED, ORDERFAILURE, SUBSCRIPTIONLIMIT, values, valueOf
---

# SystemTextMessageType

**Package:** velox.api.layer1.data

**Type:** Enum

## Inheritance

- java.lang.Object
- java.lang.Enum<SystemTextMessageType>
- velox.api.layer1.data.SystemTextMessageType

**All Implemented Interfaces:** Serializable, Comparable<SystemTextMessageType>, Constable

## Enum Constants

### UNCLASSIFIED

```java
public static final SystemTextMessageType UNCLASSIFIED
```

Message not falling into other categories. Better show it to user.

### ORDER_FAILURE

```java
public static final SystemTextMessageType ORDER_FAILURE
```

Rejection/modification error/etc.

### SUBSCRIPTION_LIMIT

```java
public static final SystemTextMessageType SUBSCRIPTION_LIMIT
```

Subscription limit reached

## Methods

### values

```java
public static SystemTextMessageType[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** An array containing the constants of this enum class, in the order they are declared

### valueOf

```java
public static SystemTextMessageType valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - The name of the enum constant to be returned

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null