---
source_file: DepthDataListener.html
package: velox.api.layer1.simplified
classes: DepthDataListener
methods: onDepth
---

# DepthDataListener

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Known Subinterfaces:** AllDataModule, DepthDataAdapter

## Description

Get incremental depth updates.

## Methods

### onDepth

```java
void onDepth(boolean isBid, int price, int size)
```

Called on each incremental depth update

**Parameters:**
- `isBid` - True if update describes changes to bid side of the order book
- `price` - Price where the update happens (as level number)
- `size` - New size on the level (0 if level is removed)