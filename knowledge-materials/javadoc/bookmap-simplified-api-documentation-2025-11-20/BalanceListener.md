---
source_file: BalanceListener.html
package: velox.api.layer1.simplified
classes: BalanceListener
methods: onBalance
---

# BalanceListener

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Known Subinterfaces:** AllDataModule, BalanceAdapter

## Description

Provides balance info. Currently only works in realtime (HistoricalDataListener should **not** be implemented if you want this to work, consider to use HistoricalModeListener instead)

## Methods

### onBalance

```java
void onBalance(velox.api.layer1.data.BalanceInfo balanceInfo)
```

Called when account balance information changes

**Parameters:**
- `balanceInfo` - Account balance information