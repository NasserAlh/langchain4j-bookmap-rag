---
source_file: Layer1StrategyName.html
package: velox.api.layer1.annotations
classes: Layer1StrategyName
methods: value, localizationKey
---

# Layer1StrategyName

**Package:** velox.api.layer1.annotations

**Type:** Annotation Interface

```java
@Retention(RUNTIME)
public @interface Layer1StrategyName
```

## Description

Specifies a name for strategy that will be displayed in strategies dialog.

## Elements

### value

```java
String value()
```

Name of strategy that will be used in the settings. If `localizationKey()` is empty or `Layer1LocalizationBundle` annotation is not present, then this name will be displayed as a strategy name in the related dialogs. See also `localizationKey()` for more details.

### localizationKey

```java
String localizationKey()
```

Key for localization of strategy name. If this key is not empty and `Layer1LocalizationBundle` annotation is present, then the name of the strategy will be taken from the specified localization bundle using this key. This localized name will be used in all displayable dialogs, and `value()` will be used in the settings. If the specified bundle doesn't contain the key, an exception will be thrown when the localized name is retrieved.

**Default:** `""`