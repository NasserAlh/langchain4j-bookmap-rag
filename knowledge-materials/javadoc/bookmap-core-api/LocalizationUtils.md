---
source_file: LocalizationUtils.html
package: velox.gui.utils.localization
classes: LocalizationUtils
methods: FULLDATESKELETON, LocalizationUtils, getCollator, sortAlphabetically, getMessageFormat, getCurrency, getCurrency, getDefaultNumberFormat, getNumberFormat, getDateFormatForSceleton, getNumberFormat, getNumberFormatter, getDefaultNumberFormatter, format, format, format, format, parseNumber, parseDouble, parseInteger
total_methods: 25
---

# LocalizationUtils

**Package:** velox.gui.utils.localization

**Type:** Class

**Inheritance:** java.lang.Object → velox.gui.utils.localization.LocalizationUtils

## Description

Some util method for trivial localization tasks

## Fields

### FULL_DATE_SKELETON

```java
public static final String FULL_DATE_SKELETON
```

Represents date in full format, e.g. "Sun, Feb 4, 2024, 20:39:15 GMT+2" (for English locale)

## Constructors

### LocalizationUtils

```java
public LocalizationUtils()
```

## Methods

### getCollator

```java
public static com.ibm.icu.text.Collator getCollator()
```

**Returns:** Collator with current Bookmap locale that used for locale sensitive string comparison

### sortAlphabetically

```java
public static List<String> sortAlphabetically(List<String> strings)
```

Sort alphabetically list of string

**Parameters:**
- `strings` - list to sort (will not be changed)

**Returns:** sorted list

### getMessageFormat

```java
public static com.ibm.icu.text.MessageFormat getMessageFormat(String pattern)
```

**Parameters:**
- `pattern` - 

**Returns:** MessageFormat with current Bookmap locale

### getCurrency

```java
public static com.ibm.icu.util.Currency getCurrency()
```

**Returns:** default currency object for current Bookmap locale

### getCurrency

```java
public static com.ibm.icu.util.Currency getCurrency(String theIsoCode)
```

Returns a currency object given an ISO 4217 3-letter code.

**Parameters:**
- `theIsoCode` - 

**Returns:** 

### getDefaultNumberFormat

```java
public static com.ibm.icu.text.NumberFormat getDefaultNumberFormat()
```

Return NumberFormat that was used by default before Bookmap localization

### getNumberFormat

```java
public static com.ibm.icu.text.NumberFormat getNumberFormat()
```

**Returns:** NumberFormat for current Bookmap locale

### getDateFormatForSceleton

```java
public static com.ibm.icu.text.DateFormat getDateFormatForSceleton(String skeleton)
```

**Parameters:**
- `skeleton` - - a skeleton string that represents a custom date-time pattern, please see `DateFormat`

**Returns:** DateFormat with current Bookmap locale for given skeleton

### getNumberFormat

```java
public static com.ibm.icu.text.NumberFormat getNumberFormat(NumberFormatStyle numberFormat)
```

NumberFormat with current Bookmap localization. If you don't need to parse numbers but need a customized number format than better use `LocalizedNumberFormatter` from `getNumberFormatter()` method, it has more user-friendly setup process

**Parameters:**
- `numberFormat` - 

**Returns:** most likely will return `DecimalFormat`

**See Also:**
- `NumberFormatStyle`

### getNumberFormatter

```java
public static com.ibm.icu.number.LocalizedNumberFormatter getNumberFormatter()
```

**Returns:** NumberFormatter for current Bookmap locale

### getDefaultNumberFormatter

```java
public static com.ibm.icu.number.LocalizedNumberFormatter getDefaultNumberFormatter()
```

**Returns:** NumberFormatter by default locale

### format

```java
public static String format(long number)
```

### format

```java
public static String format(double number)
```

### format

```java
public static String format(double number, int precision)
```

### format

```java
public static String format(double number, com.ibm.icu.util.Currency currency)
```

### parseNumber

```java
public static Number parseNumber(String text)
```

Locale specific parse of text that represent number

**Parameters:**
- `text` - to parse

**Returns:** parsed number

**Throws:**
- `NumberFormatException` - if the specified string cannot be parsed fully.

### parseDouble

```java
public static double parseDouble(String text)
```

Locale specific parse of text that represent double number

**Parameters:**
- `text` - to parse

**Returns:** parsed double

**Throws:**
- `NumberFormatException` - if the specified string cannot be parsed fully.

### parseInteger

```java
public static int parseInteger(String text)
```

Locale specific parse of text that represent integer number

**Parameters:**
- `text` - to parse

**Returns:** parsed int

**Throws:**
- `NumberFormatException` - if the beginning of the specified string cannot be parsed.

### parseLong

```java
public static double parseLong(String text)
```

Locale specific parse of text that represent long number

**Parameters:**
- `text` - to parse

**Returns:** parsed long

**Throws:**
- `NumberFormatException` - if the beginning of the specified string cannot be parsed.

### mapOf

```java
public static Map<String, Object> mapOf(Object... input)
```

A replacement for `Map.of()` which allows null as values, null keys are still prohibited.  
Use this method if you unsure if passed values 100% not null.

**Parameters:**
- `input` - key values pairs, pass order is similiar to `Map.of(Object, Object)`, i.e.: *`mapOf(key1, value1, key2, value2, ...)`*

**Returns:** immutable map with respective key and values

### mapOf

```java
public static Map<String, Object> mapOf(Map<String, Object> map)
```

Simmilar to `mapOf(Object...)`, but creates an immutable map of the specified map. Allows null as values, null keys are still prohibited.

**Parameters:**
- `map` - the map for which an immutable map is to be returned.

**Returns:** immutable map with respective key and values

### localizeListCellRenderer

```java
public static <E> ListCellRenderer<E> localizeListCellRenderer(ListCellRenderer<? super E> renderer, Function<E, String> localizedStringSupplier)
```

Wraps supplied renderer. The wrapper replaces the object to be rendered with its localized interpretation before sending it to the wrapped renderer.

**Type Parameters:**
- `E` - 

**Parameters:**
- `renderer` - renderer to wrap
- `localizedStringSupplier` - function that transform object to its localized string representation

**Returns:** wrapped renderer

### localizeListCellRenderer

```java
public static <E extends TranslatableComponent> ListCellRenderer<E> localizeListCellRenderer(ListCellRenderer<? super E> renderer)
```

Wraps supplied renderer. The wrapper replaces the object to be rendered with its localized interpretation before sending it to the wrapped renderer.  
The same as `localizeListCellRenderer(ListCellRenderer, Function)` but for the list of items implementing `TranslatableComponent` instead of specifying localizedStringSupplier function.

**Type Parameters:**
- `E` - 

**Parameters:**
- `renderer` - renderer to wrap

**Returns:** wrapped renderer