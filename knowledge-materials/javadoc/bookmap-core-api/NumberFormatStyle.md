---
source_file: NumberFormatStyle.html
package: velox.gui.utils.localization
classes: NumberFormatStyle
methods: ISOCURRENCYSTYLE, STANDARDCURRENCYSTYLE, ACCOUNTINGCURRENCYSTYLE, CASHCURRENCYSTYLE, PLURALCURRENCYSTYLE, CURRENCYSTYLE, INTEGERSTYLE, NUMBERSTYLE, PERCENTSTYLE, SCIENTIFICSTYLE, index, values, valueOf
---

# NumberFormatStyle

**Package:** velox.gui.utils.localization

**Type:** Enum

**Inheritance:**
- `java.lang.Object`
- `java.lang.Enum<NumberFormatStyle>`
- `velox.gui.utils.localization.NumberFormatStyle`

**All Implemented Interfaces:** `Serializable`, `Comparable<NumberFormatStyle>`, `Constable`

## Description

Simple translation of constant fields of the `NumberFormat` class to enum.

**See Also:** `NumberFormat`

---

## Enum Constants

### ISO_CURRENCY_STYLE

```java
public static final NumberFormatStyle ISO_CURRENCY_STYLE
```

Constant to specify currency style of format which uses currency ISO code to represent currency, for example: "USD3.00".

### STANDARD_CURRENCY_STYLE

```java
public static final NumberFormatStyle STANDARD_CURRENCY_STYLE
```

Constant to specify currency style of format which uses currency symbol to represent currency, for example "$3.00", using non-accounting style for negative values (e.g. minus sign).

### ACCOUNTING_CURRENCY_STYLE

```java
public static final NumberFormatStyle ACCOUNTING_CURRENCY_STYLE
```

Constant to specify currency style of format which uses currency symbol to represent currency for accounting, for example: "($3.00), instead of "-$3.00" (`CURRENCY_STYLE`).

### CASH_CURRENCY_STYLE

```java
public static final NumberFormatStyle CASH_CURRENCY_STYLE
```

Constant to specify currency style of format which uses currency symbol to represent currency for accounting, for example: "($3.00), instead of "-$3.00" (CURRENCYSTYLE).

### PLURAL_CURRENCY_STYLE

```java
public static final NumberFormatStyle PLURAL_CURRENCY_STYLE
```

Constant to specify currency style of format which uses currency long name with plural format to represent currency, for example, "3.00 US Dollars".

### CURRENCY_STYLE

```java
public static final NumberFormatStyle CURRENCY_STYLE
```

Constant to specify general currency style of format.

### INTEGER_STYLE

```java
public static final NumberFormatStyle INTEGER_STYLE
```

Constant to specify a integer number style format.

### NUMBER_STYLE

```java
public static final NumberFormatStyle NUMBER_STYLE
```

Constant to specify normal number style of format.

### PERCENT_STYLE

```java
public static final NumberFormatStyle PERCENT_STYLE
```

Constant to specify a style of format to display percent.

### SCIENTIFIC_STYLE

```java
public static final NumberFormatStyle SCIENTIFIC_STYLE
```

Constant to specify a style of format to display scientific number.

---

## Fields

### index

```java
public final int index
```

---

## Methods

### values

```java
public static NumberFormatStyle[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** an array containing the constants of this enum class, in the order they are declared

### valueOf

```java
public static NumberFormatStyle valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - the name of the enum constant to be returned

**Returns:** the enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - if this enum class has no constant with the specified name
- `NullPointerException` - if the argument is null