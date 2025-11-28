---
source_file: PositionAdapter.html
package: velox.api.layer1.simplified
classes: PositionAdapter
methods: onPositionUpdate
extends: PositionListener
---

# PositionAdapter

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Superinterfaces:** PositionListener

## Description

An adapter for `PositionListener` with empty default method implementations.

## Methods

### onPositionUpdate

```java
default void onPositionUpdate(velox.api.layer1.data.StatusInfo statusInfo)
```

Called when instrument status information changes (PnL, number of open orders, position, etc).

**Parameters:**
- `statusInfo` - Status information

**Specified by:** `onPositionUpdate` in interface `PositionListener`