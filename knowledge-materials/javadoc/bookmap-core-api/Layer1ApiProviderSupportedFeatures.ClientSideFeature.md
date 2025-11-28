---
source_file: Layer1ApiProviderSupportedFeatures.ClientSideFeature.html
package: velox.api.layer1.data
classes: Layer1ApiProviderSupportedFeatures.ClientSideFeature
methods: BRACKETS, OCO, OSO, TRAILINGSTOPSINDEPENDENT, TRAILINGSTOPSASBRACKETCHILDREN, values, valueOf
---

# Layer1ApiProviderSupportedFeatures.ClientSideFeature

**Package:** velox.api.layer1.data

**Type:** Enum

**Enclosing class:** [`Layer1ApiProviderSupportedFeatures`](Layer1ApiProviderSupportedFeatures.html)

**Inheritance:** `java.lang.Object` → `java.lang.Enum<Layer1ApiProviderSupportedFeatures.ClientSideFeature>` → `Layer1ApiProviderSupportedFeatures.ClientSideFeature`

**All Implemented Interfaces:** `Serializable`, `Comparable<Layer1ApiProviderSupportedFeatures.ClientSideFeature>`, `Constable`

## Description

Features that are implemented on the client side. Each enum value corresponds to one of [`Layer1ApiProviderSupportedFeatures`](Layer1ApiProviderSupportedFeatures.html) fields.

It is only trading features here for now, so we copy all them in case of crosstrading. If you add non-trading features at this list, please filter it out on [`Layer1ApiProviderSupportedFeatures.getCopyWithAppliedTradingParams(Layer1ApiProviderSupportedFeatures other)`](Layer1ApiProviderSupportedFeatures.html#getCopyWithAppliedTradingParams(velox.api.layer1.data.Layer1ApiProviderSupportedFeatures))

## Enum Constants

### BRACKETS

```java
public static final Layer1ApiProviderSupportedFeatures.ClientSideFeature BRACKETS
```

[`Layer1ApiProviderSupportedFeatures.brackets`](Layer1ApiProviderSupportedFeatures.html#brackets) is client-side

### OCO

```java
public static final Layer1ApiProviderSupportedFeatures.ClientSideFeature OCO
```

[`Layer1ApiProviderSupportedFeatures.oco`](Layer1ApiProviderSupportedFeatures.html#oco) is client-side

### OSO

```java
public static final Layer1ApiProviderSupportedFeatures.ClientSideFeature OSO
```

[`Layer1ApiProviderSupportedFeatures.oso`](Layer1ApiProviderSupportedFeatures.html#oso) is client-side

### TRAILING_STOPS_INDEPENDENT

```java
public static final Layer1ApiProviderSupportedFeatures.ClientSideFeature TRAILING_STOPS_INDEPENDENT
```

[`Layer1ApiProviderSupportedFeatures.trailingStopsAsIndependentOrders`](Layer1ApiProviderSupportedFeatures.html#trailingStopsAsIndependentOrders) is client-side

### TRAILING_STOPS_AS_BRACKET_CHILDREN

```java
public static final Layer1ApiProviderSupportedFeatures.ClientSideFeature TRAILING_STOPS_AS_BRACKET_CHILDREN
```

[`Layer1ApiProviderSupportedFeatures.trailingStopsAsBracketChildren`](Layer1ApiProviderSupportedFeatures.html#trailingStopsAsBracketChildren) is client-side

## Methods

### values

```java
public static Layer1ApiProviderSupportedFeatures.ClientSideFeature[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** An array containing the constants of this enum class, in the order they are declared

### valueOf

```java
public static Layer1ApiProviderSupportedFeatures.ClientSideFeature valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - The name of the enum constant to be returned

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null