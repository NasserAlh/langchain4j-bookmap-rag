---
source_file: Layer1ApiChangeTradingModeMessage.Mode.html
package: velox.api.layer1.messages
classes: Layer1ApiChangeTradingModeMessage.Mode
methods: CHART, DOM, BOTH, values, valueOf, toLocalizedString
---

# Layer1ApiChangeTradingModeMessage.Mode

**Package:** velox.api.layer1.messages

**Type:** Enum

**Enclosing class:** [`Layer1ApiChangeTradingModeMessage`](Layer1ApiChangeTradingModeMessage.html)

**Inheritance:**
- `java.lang.Object`
- `java.lang.Enum<Layer1ApiChangeTradingModeMessage.Mode>`
- `velox.api.layer1.messages.Layer1ApiChangeTradingModeMessage.Mode`

**All Implemented Interfaces:** `Serializable`, `Comparable<Layer1ApiChangeTradingModeMessage.Mode>`, `Constable`

## Description

The mode to be enabled or disabled in the Trade Control Panel

## Enum Constants

### CHART

```java
public static final Layer1ApiChangeTradingModeMessage.Mode CHART
```

### DOM

```java
public static final Layer1ApiChangeTradingModeMessage.Mode DOM
```

### BOTH

```java
public static final Layer1ApiChangeTradingModeMessage.Mode BOTH
```

## Methods

### values

```java
public static Layer1ApiChangeTradingModeMessage.Mode[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** An array containing the constants of this enum class, in the order they are declared

### valueOf

```java
public static Layer1ApiChangeTradingModeMessage.Mode valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - The name of the enum constant to be returned

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null

### toLocalizedString

```java
public String toLocalizedString()
```