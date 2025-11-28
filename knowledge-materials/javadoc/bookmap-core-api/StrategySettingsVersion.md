---
source_file: StrategySettingsVersion.html
package: velox.api.layer1.settings
classes: StrategySettingsVersion
methods: currentVersion, compatibleVersions
---

# StrategySettingsVersion

**Package:** velox.api.layer1.settings

**Type:** Annotation Interface

```java
@Retention(RUNTIME)
public @interface StrategySettingsVersion
```

## Description

Defines current version of settings and compatibility with previous versions. Any settings that wish to be saved and loaded from config should have this annotation.

## Elements

### currentVersion

```java
int currentVersion()
```

**Returns:** Current settings version

### compatibleVersions

```java
int[] compatibleVersions()
```

**Returns:** Settings versions that are compatible with current settings version. If stored settings are not compatible with current version, default settings will be used.