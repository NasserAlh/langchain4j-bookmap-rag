---
source_file: DisconnectionReason.html
package: velox.api.layer1.data
classes: DisconnectionReason
methods: SIMULTANEOUSLOGIN, NOINTERNET, UNKNOWN, FATAL, values, valueOf
---

# DisconnectionReason

**Package:** velox.api.layer1.data

**Type:** Enum

**Inheritance:**
- java.lang.Object
- java.lang.Enum<DisconnectionReason>
- velox.api.layer1.data.DisconnectionReason

**All Implemented Interfaces:** Serializable, Comparable<DisconnectionReason>, Constable

## Description

Reason why disconnection happened

## Enum Constants

### SIMULTANEOUS_LOGIN

```java
public static final DisconnectionReason SIMULTANEOUS_LOGIN
```

This connection was kicked due to some other new connection above limit  
No reconnect should be attempted

### NO_INTERNET

```java
public static final DisconnectionReason NO_INTERNET
```

Connection lost due to internet connection issues,  
Reconnect should be attempted

### UNKNOWN

```java
public static final DisconnectionReason UNKNOWN
```

Any other reason, reconnect might or might not be attempted, in most cases it shouldn't be attempted

### FATAL

```java
public static final DisconnectionReason FATAL
```

Some reason leading to disconnect  
No reconnect should be attempted

## Methods

### values

```java
public static DisconnectionReason[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** An array containing the constants of this enum class, in the order they are declared

### valueOf

```java
public static DisconnectionReason valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - The name of the enum constant to be returned

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null