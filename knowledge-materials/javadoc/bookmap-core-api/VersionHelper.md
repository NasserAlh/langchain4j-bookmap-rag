---
source_file: VersionHelper.html
package: velox.api.layer1.common
classes: VersionHelper
methods: VersionHelper, setBookmapVersionOnce, getBookmapVersion
---

# VersionHelper

**Package:** velox.api.layer1.common

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.common.VersionHelper

## Description

This class can be used to retrieve Bookmap version.

## Constructors

### VersionHelper

```java
public VersionHelper()
```

## Methods

### setBookmapVersionOnce

```java
public static void setBookmapVersionOnce(String version)
```

Called by Bookmap code once to set version which will later become available to Layer1 API.

**Parameters:**
- `version` - Current Bookmap version

### getBookmapVersion

```java
public static String getBookmapVersion()
```

Get string representation of bookmap version.

**Returns:** Bookmap version, e.g. "7.1.0.42"