---
source_file: TradeDataAdapter.html
package: velox.api.layer1.simplified
classes: TradeDataAdapter
methods: onTrade
extends: TradeDataListener
---

# TradeDataAdapter

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Superinterfaces:** TradeDataListener

## Description

An adapter for `TradeDataListener` with empty default method implementations.

## Methods

### onTrade

```java
default void onTrade(double price, int size, velox.api.layer1.data.TradeInfo tradeInfo)
```

**Specified by:** `onTrade` in interface `TradeDataListener`