---
source_file: Layer1ApiUserMessageReadCatchupDataFinished.Reason.html
package: velox.api.layer1.messages
classes: Layer1ApiUserMessageReadCatchupDataFinished.Reason
methods: FINISHED, CANCELLED, SKIPPED, CLOSED, values, valueOf
---

# Layer1ApiUserMessageReadCatchupDataFinished.Reason

**Package:** velox.api.layer1.messages

**Type:** Enum

**Enclosing class:** `Layer1ApiUserMessageReadCatchupDataFinished`

## Inheritance

- `java.lang.Object`
- `java.lang.Enum<Layer1ApiUserMessageReadCatchupDataFinished.Reason>`
- `velox.api.layer1.messages.Layer1ApiUserMessageReadCatchupDataFinished.Reason`

**All Implemented Interfaces:** `Serializable`, `Comparable<Layer1ApiUserMessageReadCatchupDataFinished.Reason>`, `Constable`

## Enum Constants

### FINISHED

```java
public static final Layer1ApiUserMessageReadCatchupDataFinished.Reason FINISHED
```

Catching up finished

### CANCELLED

```java
public static final Layer1ApiUserMessageReadCatchupDataFinished.Reason CANCELLED
```

Catching up cancelled

### SKIPPED

```java
public static final Layer1ApiUserMessageReadCatchupDataFinished.Reason SKIPPED
```

Generator requested to skip remaining catchup

### CLOSED

```java
public static final Layer1ApiUserMessageReadCatchupDataFinished.Reason CLOSED
```

Recorder layer was closed during catching up

## Methods

### values

```java
public static Layer1ApiUserMessageReadCatchupDataFinished.Reason[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** An array containing the constants of this enum class, in the order they are declared

### valueOf

```java
public static Layer1ApiUserMessageReadCatchupDataFinished.Reason valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - The name of the enum constant to be returned

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null