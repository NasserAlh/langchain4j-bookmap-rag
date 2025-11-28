---
source_file: LocalizedBundle.html
package: velox.gui.utils.localization
classes: LocalizedBundle
methods: getString, getString, getLocale, getULocale
---

# LocalizedBundle

**Package:** velox.gui.utils.localization

**Type:** Interface

## Description

This class use ICU message format for handling of localized text, you can get info about it here:
`MessageFormat`, [icu-user guide](https://unicode-org.github.io/icu/userguide/format_parse)

## Methods

### getString

```java
String getString(String key)
```

In localization file you should write keys for variables like this: `<key>=<text>` 

**Parameters:**
- `key` - 

**Returns:** Localized text for current locale

**Throws:**
- `NullPointerException` - If key is null
- `MissingResourceException` - If no object for the given key can be found
- `ClassCastException` - If the object found for the given key is not a string

### getString

```java
String getString(String key, Map<String, Object> arguments)
```

In localization file you should write keys for variables like this: `<key>=<text>` 

**Parameters:**
- `key` - 
- `arguments` - That should be inserted in text.

**Returns:** Localized text for current locale

**Throws:**
- `NullPointerException` - If key is null
- `MissingResourceException` - If no object for the given key can be found
- `ClassCastException` - If the object found for the given key is not a string

### getLocale

```java
Locale getLocale()
```

**Returns:** Locale that this bundle use

### getULocale

```java
com.ibm.icu.util.ULocale getULocale()
```

**Returns:** Locale that this bundle use