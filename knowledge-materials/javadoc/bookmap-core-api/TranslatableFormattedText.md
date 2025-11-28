---
source_file: TranslatableFormattedText.html
package: velox.gui.utils.localization.translatable
classes: TranslatableFormattedText
methods: TranslatableFormattedText, toLocalizedString
---

# TranslatableFormattedText

**Package:** velox.gui.utils.localization.translatable

**Type:** Class

**Inheritance:** java.lang.Object → TranslatableFormattedText

**All Implemented Interfaces:** TranslatableComponent

## Description

Formats to localized text. Use this class only with skeletons from `MessageFormat`.
A format that passed to this class shouldn't contain any punctuation or plain text.

Correct usage: **`new TranslatableFormattedText("{0, number, :: unit/hour unit-width-full-name}", 1)`**

Incorrect usage: **`new TranslatableFormattedText("{0, number, :: unit/hour unit-width-full-name} to complete task", 1)`**

In incorrect case, you should extract this text to the `.property` file and use **[TranslatableText](TranslatableText.html)**

## Constructors

### TranslatableFormattedText

```java
public TranslatableFormattedText(String format, Object... args)
```

**Parameters:**
- `format` - Skeleton from `MessageFormat`, accept only positional argument
- `args` - Positional arguments

## Methods

### toLocalizedString

```java
public String toLocalizedString(com.ibm.icu.util.ULocale locale)
```

**Specified by:** `toLocalizedString` in interface `TranslatableComponent`

**Parameters:**
- `locale` - 

**Returns:** Localized string in specific locale