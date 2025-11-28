---
source_file: IdHelper.html
package: velox.api.layer1.utils
classes: IdHelper
methods: IdHelper, generateShortUuid, generateLongUniqueId
---

# IdHelper

**Package:** velox.api.layer1.utils

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.utils.IdHelper

## Description

Generates ID's that are trying to be globally unique.

## Constructors

### IdHelper

```java
public IdHelper()
```

## Methods

### generateShortUuid

```java
public static String generateShortUuid()
```

Generate UUID that's repackaged as BASE64 string.

### generateLongUniqueId

```java
public static long generateLongUniqueId()
```

Generate a 64bit ID that is guaranteed to be unique within a session and is very likely to be unique between sessions too (ID counter starts at random point determined on startup and then incremented by 1 on every call)