---
source_file: Layer1UserLicenseFieldsInterface.html
package: velox.api.layer1.settings
classes: Layer1UserLicenseFieldsInterface
methods: acceptUserLicenseFields
---

# Layer1UserLicenseFieldsInterface

**Package:** velox.api.layer1.settings

**Type:** Interface

## Description

Implement this interface if you want your strategy, layer0 provider or `CredentialsFieldManager` to receive user license fields related to it.

## Methods

### acceptUserLicenseFields

```java
void acceptUserLicenseFields(Map<String, String> licenseFields)
```

User license fields related to this module (e.g. expiration date) will be passed to this method as soon as the object is created.

**Parameters:**
- `licenseFields` - User license fields