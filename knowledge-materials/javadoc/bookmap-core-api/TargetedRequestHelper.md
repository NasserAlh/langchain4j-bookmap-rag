---
source_file: TargetedRequestHelper.html
package: velox.api.layer1.providers.helper
classes: TargetedRequestHelper
methods: TargetedRequestHelper, getSupportedFeaturesForAlias
---

# TargetedRequestHelper

**Package:** velox.api.layer1.providers.helper

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.providers.helper.TargetedRequestHelper

## Description

Helper class for extracting alias-specific information

## Constructors

### TargetedRequestHelper

```java
public TargetedRequestHelper()
```

## Methods

### getSupportedFeaturesForAlias

```java
public static Layer1ApiProviderSupportedFeatures getSupportedFeaturesForAlias(Layer1ApiProvider provider, String alias)
```

Check which features are supported by a provider corresponding to certain alias

**Parameters:**
- `provider` - Layer implementing `Layer1ApiProvider` used to route the request
- `alias` - Defines which instrument the request belongs to

**Returns:** Which features corresponding alias supports

---

**Methods inherited from class java.lang.Object:**
`clone`, `equals`, `finalize`, `getClass`, `hashCode`, `notify`, `notifyAll`, `toString`, `wait`, `wait`, `wait`