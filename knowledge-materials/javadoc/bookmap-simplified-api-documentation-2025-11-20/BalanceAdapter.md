---
source_file: BalanceAdapter.html
package: velox.api.layer1.simplified
classes: BalanceAdapter
methods: onBalance
extends: BalanceListener
---

# BalanceAdapter

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Superinterfaces:** BalanceListener

## Description

An adapter for `BalanceListener` with empty default method implementations.

## Methods

### onBalance

```java
default void onBalance(velox.api.layer1.data.BalanceInfo balanceInfo)
```

Called when account balance information changes.

**Parameters:**
- `balanceInfo` - Account balance information

**Specified by:** `onBalance` in interface `BalanceListener`