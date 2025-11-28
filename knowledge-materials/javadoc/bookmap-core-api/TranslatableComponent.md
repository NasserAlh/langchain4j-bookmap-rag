---
source_file: TranslatableComponent.html
package: velox.gui.utils.localization.translatable
classes: TranslatableComponent
methods: toLocalizedString, toLocalizedString, toDefaultString
---

# TranslatableComponent

**Package:** velox.gui.utils.localization.translatable

**Type:** Interface

**All Known Implementing Classes:** NotTranslatableText, TranslatableException, TranslatableFormattedText, TranslatableList, TranslatableRuntimeException, TranslatableText

## Description

Interface for components which can be used on different locales after creation. One of the cases, when you want to use this interface, is if you need to display some text to the user in his specific locale and also include it in the logs on the English.

Also if you want to implement this interface and your class can include other `TranslatableComponent` as arguments it is recommended to call their `toLocalizedString(ULocale)` method to enable their chaining.

Here is an example of how this is done in `TranslatableText`:

```java
private Map<String, Object> getNormalizedArgs(ULocale locale) {
    Map<String, Object> normalizedArgs = new HashMap<>();

    for (Entry<String, Object> argument : args.entrySet()) {
        if (argument.getValue() instanceof TranslatableComponent) {
            TranslatableComponent component = (TranslatableComponent) argument.getValue();
            normalizedArgs.put(argument.getKey(), component.toLocalizedString(locale));
        } else {
            normalizedArgs.put(argument.getKey(), argument.getValue());
        }
    }
    return normalizedArgs;
}
```

All classes that implement this interface support chaining.

## Methods

### toLocalizedString

```java
String toLocalizedString(com.ibm.icu.util.ULocale locale)
```

**Parameters:**
- `locale` - 

**Returns:** Localized string in specific locale

### toLocalizedString

```java
default String toLocalizedString()
```

**Returns:** Localized string in the Bookmap current locale

### toDefaultString

```java
default String toDefaultString()
```

**Returns:** Localized string in the Bookmap default locale (i.e. `Locale.ENGLISH`)