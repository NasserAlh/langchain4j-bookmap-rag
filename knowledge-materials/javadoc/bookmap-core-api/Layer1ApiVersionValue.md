---
source_file: Layer1ApiVersionValue.html
package: velox.api.layer1.annotations
classes: Layer1ApiVersionValue
methods: VERSION0, VERSION1, VERSION2, VERSION3, values, valueOf, getNumericValue, getHighestVersion, valueOf
---

# Layer1ApiVersionValue

**Package:** velox.api.layer1.annotations

**Type:** Enum

**Inheritance:** java.lang.Object → java.lang.Enum<Layer1ApiVersionValue> → Layer1ApiVersionValue

**All Implemented Interfaces:** Serializable, Comparable<Layer1ApiVersionValue>, Constable

## Description

List of API versions. If you are writing a new module, you typically want the latest one available in the lowest Bookmap that you are targeting. Easiest way to check would be to install that version and see what is the last value in this enum. If no such enum exists this means you have picked pre-compatibility-system version, which is not recommended. Compatibility system was added in version 7.0 during beta stage.

Only breaking changes are listed in comments. New features can be added at any point without incrementing API version (especially while in alpha/beta), so there is no guarantee that older build will run with indicator intended for newer one.

## Enum Constants

### VERSION0

```java
public static final Layer1ApiVersionValue VERSION0
```

API right before compatibility system was introduced. There were few small changes in API before that, but presumably not many people used API at that stage.

### VERSION1

```java
public static final Layer1ApiVersionValue VERSION1
```

Development started around 2018-06-12 in version 7.0.

- Removed Layer1ApiAdminListener.selectAccount. Use `SelectAccountMessage` instead
- Fixed return values in `Layer1ApiProviderSupportedFeaturesBuilder`

### VERSION2

```java
public static final Layer1ApiVersionValue VERSION2
```

Development started around 2018-11-20 in version 7.0.

- Changed AliasFilter from being a class to being an interface
- Changed IndicatorColorScheme from being a class to being an interface

### VERSION3

```java
public static final Layer1ApiVersionValue VERSION3
```

Development started around 2024-09-15 in version 7.6.

- Introduced child-first class loader.

## Methods

### values

```java
public static Layer1ApiVersionValue[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** An array containing the constants of this enum class, in the order they are declared

### valueOf

```java
public static Layer1ApiVersionValue valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - The name of the enum constant to be returned

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null

### getNumericValue

```java
public int getNumericValue()
```

### getHighestVersion

```java
public static Layer1ApiVersionValue getHighestVersion()
```

### valueOf

```java
public static Layer1ApiVersionValue valueOf(int numericValue)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `numericValue` - The name of the enum constant to be returned

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null