---
source_file: LineStyle.html
package: velox.api.layer1.simplified
classes: LineStyle
methods: SOLID, SHORTDASH, LONGDASH, DOT, DASHDOT, values, valueOf, toIndicatorStyle, toString, toLocalizedString
---

# LineStyle

**Package:** velox.api.layer1.simplified

**Type:** Enum

**Inheritance:**
- `java.lang.Object`
- `java.lang.Enum<LineStyle>`
- `velox.api.layer1.simplified.LineStyle`

**All Implemented Interfaces:** `Serializable`, `Comparable<LineStyle>`, `Constable`

## Enum Constants

### SOLID
```java
public static final LineStyle SOLID
```

### SHORT_DASH
```java
public static final LineStyle SHORT_DASH
```

### LONG_DASH
```java
public static final LineStyle LONG_DASH
```

### DOT
```java
public static final LineStyle DOT
```

### DASH_DOT
```java
public static final LineStyle DASH_DOT
```

## Methods

### values
```java
public static LineStyle[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** An array containing the constants of this enum class, in the order they are declared

### valueOf
```java
public static LineStyle valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - The name of the enum constant to be returned

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null

### toIndicatorStyle
```java
public velox.api.layer1.messages.indicators.IndicatorLineStyle toIndicatorStyle(int width)
```

### toString
```java
public String toString()
```

**Overrides:** `toString` in class `Enum<LineStyle>`

### toLocalizedString
```java
public String toLocalizedString()
```

Use this method to get text that should be displayed in the GUI