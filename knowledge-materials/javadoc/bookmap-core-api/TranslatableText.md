---
source_file: TranslatableText.html
package: velox.gui.utils.localization.translatable
classes: TranslatableText
methods: TranslatableText, TranslatableText, getKey, toLocalizedString, hashCode, equals, toString
---

# TranslatableText

**Package:** velox.gui.utils.localization.translatable

**Type:** Class

**Inheritance:** java.lang.Object → TranslatableText

**All Implemented Interfaces:** TranslatableComponent

## Description

You can use this class instead of `LocalizedBundle.getString(String key, Map args)` if the given text can be used on several locales at the same time.

This class support chaining with other TranslatableComponent classes when they passed as arguments.

For a convenient use, extend this class with you own add-on specific class with a constructor that will call super constructor with a specific bundleName and classLoader (to not pass the bundleName and classLoader explicitly every time you create a new instance of TranslatableText).

**See Also:**
- [DemoStrategies](https://github.com/BookmapAPI/DemoStrategies) see velox.api.layer1.simpledemo.localization.LocalizedAlertDemoTranslatableText class for an example

## Constructors

### TranslatableText

```java
public TranslatableText(String key, Map<String, Object> args, String bundleName, ClassLoader classLoader, boolean throwException)
```

Get locale specific text by given key, and insert given arguments.

**Parameters:**
- `key` - Translation key
- `args` - Named arguments that should inserted in text
- `bundleName` - In which key is located
- `classLoader` - With which your classes were loaded from the jar
- `throwException` - If true we throw an exception if the key is not found in the given bundleName, otherwise return the key as is

### TranslatableText

```java
public TranslatableText(String key, String bundleName, ClassLoader classLoader, boolean throwException)
```

Get locale specific text by given key, without arguments.

**Parameters:**
- `key` - Translation key
- `bundleName` - In which key is located
- `classLoader` - With which your classes were loaded from the jar
- `throwException` - If true we throw an exception if the key is not found in the given bundleName, otherwise return the key as is

## Methods

### getKey

```java
public String getKey()
```

**Returns:** Translation key

### toLocalizedString

```java
public String toLocalizedString(com.ibm.icu.util.ULocale locale)
```

**Specified by:** `toLocalizedString` in interface `TranslatableComponent`

**Parameters:**
- `locale` - 

**Returns:** Localized string in specific locale

### hashCode

```java
public int hashCode()
```

**Overrides:** `hashCode` in class `Object`

### equals

```java
public boolean equals(Object obj)
```

**Overrides:** `equals` in class `Object`

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`

## Inherited Methods

**From class java.lang.Object:**
- `clone`, `finalize`, `getClass`, `notify`, `notifyAll`, `wait`, `wait(long)`, `wait(long, int)`

**From interface velox.gui.utils.localization.translatable.TranslatableComponent:**
- `toDefaultString`, `toLocalizedString`