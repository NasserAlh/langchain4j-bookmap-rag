---
source_file: Layer1LocalizationInterface.html
package: velox.api.layer1.localization
classes: Layer1LocalizationInterface
methods: acceptLocalizedBundleProvider
---

# Layer1LocalizationInterface

**Package:** velox.api.layer1.localization

**Type:** Interface

## Description

Use this interface if you want to localize your addon and be in sync with Bookmap locale.

## Methods

### acceptLocalizedBundleProvider

```java
void acceptLocalizedBundleProvider(LocalizedBundleProvider localizedBundleProvider)
```

You will receive localizationBundleProvider before UserMessageLayersChainCreatedTargeted.

**Parameters:**
- `localizedBundleProvider` - 