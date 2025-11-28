---
source_file: Log.LogLevel.html
package: velox.api.layer1.common
classes: Log.LogLevel
methods: TRACE, DEBUG, INFO, WARN, ERROR, TRADING, NOLOG, levelAsInt, values, valueOf, fromInt, allows
---

# Log.LogLevel

**Package:** velox.api.layer1.common

**Type:** Enum

**Enclosing class:** `Log`

**Inheritance:** `java.lang.Object` → `java.lang.Enum<Log.LogLevel>` → `Log.LogLevel`

**All Implemented Interfaces:** `Serializable`, `Comparable<Log.LogLevel>`, `Constable`

## Enum Constants

### TRACE
```java
public static final Log.LogLevel TRACE
```
Trace messages. A lot of information is logged, so this level is usually only needed when debugging a problem.

### DEBUG
```java
public static final Log.LogLevel DEBUG
```
Debug messages. This level is useful during development.

### INFO
```java
public static final Log.LogLevel INFO
```
Informative messages. Typically used for deployment.

### WARN
```java
public static final Log.LogLevel WARN
```
Important warnings. The application will continue to work correctly.

### ERROR
```java
public static final Log.LogLevel ERROR
```
Critical errors. The application may no longer work correctly.

### TRADING
```java
public static final Log.LogLevel TRADING
```
Information related to trading activity. Used for debugging trading issues.

### NO_LOG
```java
public static final Log.LogLevel NO_LOG
```
Unrealistically high level used to disable logs

## Fields

### levelAsInt
```java
public final int levelAsInt
```
Integer representing log level. Used for comparing log levels (more/less critical)

## Methods

### values
```java
public static Log.LogLevel[] values()
```
Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** An array containing the constants of this enum class, in the order they are declared

### valueOf
```java
public static Log.LogLevel valueOf(String name)
```
Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - The name of the enum constant to be returned.

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null

### fromInt
```java
public static Log.LogLevel fromInt(int levelAsInt)
```

### allows
```java
public boolean allows(Log.LogLevel level)
```