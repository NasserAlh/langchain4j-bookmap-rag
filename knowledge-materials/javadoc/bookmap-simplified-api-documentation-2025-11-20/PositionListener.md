---
source_file: PositionListener.html
package: velox.api.layer1.simplified
classes: PositionListener
methods: onPositionUpdate
---

# PositionListener

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Known Subinterfaces:** AllDataModule, PositionAdapter

## Description

Provides position info. Currently only works in realtime (HistoricalDataListener should **not** be implemented if you want this to work, consider to use HistoricalModeListener instead)

## Methods

### onPositionUpdate

```java
void onPositionUpdate(velox.api.layer1.data.StatusInfo statusInfo)
```

Called when instrument status information changes (PnL, number of open orders, position, etc).

**Parameters:**
- `statusInfo` - Status information