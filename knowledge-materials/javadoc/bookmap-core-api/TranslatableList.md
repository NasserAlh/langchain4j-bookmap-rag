---
source_file: TranslatableList.html
package: velox.gui.utils.localization.translatable
classes: TranslatableList
methods: TranslatableList, TranslatableList, TranslatableList, addLineObject, addNewLine, toLocalizedString
---

# TranslatableList

**Package:** velox.gui.utils.localization.translatable

**Type:** Class

**Inheritance:** java.lang.Object → TranslatableList

**All Implemented Interfaces:** TranslatableComponent

## Description

Creates localized listing from its components with line breaks. Make sense to use it only in conjunction with other TranslatableComponents.

## Constructors

### TranslatableList

```java
public TranslatableList(boolean isHtml)
```

**Parameters:**
- `isHtml` - True if should use `'<br>'` instead of `'\n'` for line break

### TranslatableList

```java
public TranslatableList(boolean isHtml, List<?> lines)
```

**Parameters:**
- `isHtml` - True if should use `'<br>'` instead of `'\n'` for line break
- `lines` - From which should create listing

### TranslatableList

```java
public TranslatableList(boolean isHtml, Object... lines)
```

**Parameters:**
- `isHtml` - True if should use `'<br>'` instead of `'\n'` for line break
- `lines` - From which should create listing

## Methods

### addLineObject

```java
public void addLineObject(Object line)
```

Adds new line to the listing

**Parameters:**
- `line` - 

### addNewLine

```java
public void addNewLine()
```

Adds line break to the listing

### toLocalizedString

```java
public String toLocalizedString(com.ibm.icu.util.ULocale locale)
```

**Specified by:** `toLocalizedString` in interface `TranslatableComponent`

**Parameters:**
- `locale` - 

**Returns:** Localized string in specific locale

---

**Methods inherited from class java.lang.Object:** clone, equals, finalize, getClass, hashCode, notify, notifyAll, toString, wait, wait, wait

**Methods inherited from interface velox.gui.utils.localization.translatable.TranslatableComponent:** toDefaultString, toLocalizedString