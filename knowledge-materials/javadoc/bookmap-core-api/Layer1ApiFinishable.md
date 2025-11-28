---
source_file: Layer1ApiFinishable.html
package: velox.api.layer1
classes: Layer1ApiFinishable
methods: finish
---

# Layer1ApiFinishable

**Package:** velox.api.layer1

**Type:** Interface

## Description

Every strategy should implement this interface.  
Defines action when strategy is being unloaded (removing added GUI, disposing dialogs, etc).

**See Also:**
- `Layer1Attachable` for full strategy life cycle description

## Methods

### finish

```java
void finish()
```

Will be called when strategy is being unloaded.  
All previously known values (e.g. settings obtained and cached via `SettingsAccess` interface) should be considered outdated.

**See Also:**
- `Layer1Attachable` for full strategy life cycle description