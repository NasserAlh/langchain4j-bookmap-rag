---
source_file: CustomSettingsPanelProvider.html
package: velox.api.layer1.simplified
classes: CustomSettingsPanelProvider
methods: getCustomSettingsPanels
---

# CustomSettingsPanelProvider

**Package:** velox.api.layer1.simplified

**Type:** Interface

## Description

Allows specifying custom UI directly. Alternatively you can use `Parameter` for automatic UI generation.

This UI is only shown when module is enabled. If you want to show some placeholder (usually a disabled version of UI with default values) when module is disabled - implement the following method:
```java
public static StrategyPanel[] getCustomDisabledSettingsPanels()
```

This functionality is, strictly speaking, independent of this interface, but you will typically want to use both together. Important: If you use an obfuscator make sure to exclude this method.

## Methods

### getCustomSettingsPanels

```java
velox.gui.StrategyPanel[] getCustomSettingsPanels()
```