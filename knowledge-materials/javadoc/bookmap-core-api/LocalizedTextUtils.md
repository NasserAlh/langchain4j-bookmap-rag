---
source_file: LocalizedTextUtils.html
package: velox.gui.utils.localization
classes: LocalizedTextUtils
methods: LocalizedTextUtils, getCharCount, getCharCount, getCharAt, getCharAt, toUpperCase, toUpperCase, toLowerCase, toLowerCase
---

# LocalizedTextUtils

**Package:** velox.gui.utils.localization

**Type:** Class

**Inheritance:** java.lang.Object → LocalizedTextUtils

## Description

Utility class for work with Unicode strings.  
Contains methods for work with the user-perceived characters instead of Unicode code points.  
For more info see [UAX #29: Unicode Text Segmentation#Grapheme Cluster Boundaries](http://www.unicode.org/reports/tr29/#Grapheme_Cluster_Boundaries)

## Constructors

### LocalizedTextUtils

```java
public LocalizedTextUtils()
```

## Methods

### getCharCount

```java
public static int getCharCount(String text, com.ibm.icu.util.ULocale locale)
```

Returns the length of the given string.  
The length is equal to the number of user-perceived characters in the string for the given locale.

**Note:** this method is computation-heavy, so it is not recommended to use in frequently called code.  
Consider creation of own `BreakIterator` through `BreakIterator.getCharacterInstance(ULocale locale)` method in this case

**Parameters:**
- `text` - which length need to know
- `locale` - use the character boundary rules for this locale

**Returns:** the length of the sequence of characters represented by *text* object.

### getCharCount

```java
public static int getCharCount(String text)
```

Returns the length of the given string on the current Bookmap locale.  
The length is equal to the number of user-perceived characters in the string for the current Bookmap locale.

**Note:** this method is computation-heavy, so it is not recommended to use in frequently called code.  
Consider creation of own `BreakIterator` through `BreakIterator.getCharacterInstance(ULocale locale)` method in this case

**Parameters:**
- `text` - which length need to know

**Returns:** the length of the sequence of characters represented by *text* object.

### getCharAt

```java
public static String getCharAt(String text, int position, com.ibm.icu.util.ULocale locale)
```

Returns the user-perceived character at the given position of the text.

**Note:** this method is computation-heavy, so it is not recommended to use in frequently called code.  
Consider creation of own `BreakIterator` through `BreakIterator.getCharacterInstance(ULocale locale)` method in this case

**Parameters:**
- `text` - from which need to get character
- `position` - the index to the user-perceived character in the text
- `locale` - use the character boundary rules for this locale

**Returns:** the user-perceived character character at the given position

### getCharAt

```java
public static String getCharAt(String text, int position)
```

Returns the user-perceived character at the given position of the text.  
Uses the current Bookmap locale character boundary rules.

**Note:** this method is computation-heavy, so it is not recommended to use in frequently called code.  
Consider creation of own `BreakIterator` through `BreakIterator.getCharacterInstance(ULocale locale)` method in this case

**Parameters:**
- `text` - from which need to get character
- `position` - the index to the user-perceived character in the text

**Returns:** the user-perceived character character at the given position

### toUpperCase

```java
public static String toUpperCase(String text, com.ibm.icu.util.ULocale locale)
```

Same as `String.toUpperCase(Locale)`, but support wider number of languages

**Parameters:**
- `text` - that needs to cast to the upper case
- `locale` - use the case transformation rules for this locale

**Returns:** the String, converted to uppercase

### toUpperCase

```java
public static String toUpperCase(String text)
```

Same as `String.toUpperCase(Locale)`, but support wider number of languages.  
Uses the current Bookmap locale case transformation rules

**Parameters:**
- `text` - that needs to cast to the upper case

**Returns:** the String, converted to uppercase

### toLowerCase

```java
public static String toLowerCase(String text, com.ibm.icu.util.ULocale locale)
```

Same as `String.toLowerCase(Locale)`, but support wider number of languages

**Parameters:**
- `text` - that needs to cast to the lower case
- `locale` - use the case transformation rules for this locale

**Returns:** the String, converted to lowercase

### toLowerCase

```java
public static String toLowerCase(String text)
```

Same as `String.toLowerCase(Locale)`, but support wider number of languages  
Uses the current Bookmap locale case transformation rules

**Parameters:**
- `text` - that needs to cast to the lower case

**Returns:** the String, converted to lowercase