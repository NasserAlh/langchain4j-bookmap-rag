---
source_file: BboAdapter.html
package: velox.api.layer1.simplified
classes: BboAdapter
methods: onBbo
extends: BboListener
---

# BboAdapter

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Superinterfaces:** BboListener

## Description

An adapter for `BboListener` with empty default method implementations.

## Methods

### onBbo

```java
default void onBbo(int bidPrice, int bidSize, int askPrice, int askSize)
```

**Specified by:** `onBbo` in interface `BboListener`

Called on each BBO change providing new price/size value