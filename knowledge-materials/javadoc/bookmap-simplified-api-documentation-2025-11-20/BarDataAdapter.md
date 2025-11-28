---
source_file: BarDataAdapter.html
package: velox.api.layer1.simplified
classes: BarDataAdapter
methods: onBar, From BarDataListener, From IntervalListener
extends: BarDataListener, IntervalListener
---

# BarDataAdapter

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Superinterfaces:** BarDataListener, IntervalListener

## Description

An adapter for `BarDataListener` with empty default method implementations.

## Methods

### onBar

```java
default void onBar(velox.api.layer1.layers.utils.OrderBook orderBook, Bar bar)
```

Called when bar is ready. Bar width is specified via `IntervalListener.getInterval()`

**Specified by:** `onBar` in interface `BarDataListener`

## Inherited Methods

### From BarDataListener
- `onInterval()`

### From IntervalListener  
- `getInterval()`