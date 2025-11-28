---
source_file: DepthDataAdapter.html
package: velox.api.layer1.simplified
classes: DepthDataAdapter
methods: onDepth
extends: DepthDataListener
---

# DepthDataAdapter

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Superinterfaces:** DepthDataListener

## Description

An adapter for `DepthDataListener` with empty default method implementations.

## Methods

### onDepth

```java
default void onDepth(boolean isBid, int price, int size)
```

Called on each incremental depth update

**Parameters:**
- `isBid` - true if update describes changes to bid side of the order book
- `price` - price where the update happens (as level number)
- `size` - new size on the level (0 if level is removed)