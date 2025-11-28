---
source_file: SwitchTabResponse.Visitor.html
package: velox.api.layer1.messages.tab
classes: SwitchTabResponse.Visitor
methods: onSuccess, onNoSuchTab
---

# SwitchTabResponse.Visitor

**Package:** velox.api.layer1.messages.tab

**Type:** Interface

**Enclosing Interface:** `SwitchTabResponse`

## Description

```java
public static interface SwitchTabResponse.Visitor<T, E extends Exception>
```

## Methods

### onSuccess

```java
T onSuccess(SwitchTabResponse.Success success) throws E
```

**Throws:**
- `E` - Generic exception type

### onNoSuchTab

```java
T onNoSuchTab(SwitchTabResponse.NoSuchTab noSuchTab) throws E
```

**Throws:**
- `E` - Generic exception type