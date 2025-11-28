---
source_file: NotTranslatableText.html
package: velox.gui.utils.localization.translatable
classes: NotTranslatableText
methods: NotTranslatableText, toLocalizedString
---

# NotTranslatableText

**Package:** velox.gui.utils.localization.translatable

**Type:** Class

**Inheritance:** java.lang.Object → NotTranslatableText

**All Implemented Interfaces:** TranslatableComponent

## Description

A simple class that accepts any String and implements the `TranslatableComponent` interface. Always returns the same passed text regardless of locale. Use it if you need to pass TranslatableComponent but the text contained in TranslatableComponent does not require localization.

## Constructors

### NotTranslatableText

```java
public NotTranslatableText(String text)
```

## Methods

### toLocalizedString

```java
public String toLocalizedString(com.ibm.icu.util.ULocale locale)
```

**Specified by:** `toLocalizedString` in interface `TranslatableComponent`

**Parameters:**
- `locale` - 

**Returns:** localized string in specific locale

---

**Methods inherited from class java.lang.Object:**
- `clone`, `equals`, `finalize`, `getClass`, `hashCode`, `notify`, `notifyAll`, `toString`, `wait`, `wait(long)`, `wait(long, int)`

**Methods inherited from interface velox.gui.utils.localization.translatable.TranslatableComponent:**
- `toDefaultString`, `toLocalizedString`