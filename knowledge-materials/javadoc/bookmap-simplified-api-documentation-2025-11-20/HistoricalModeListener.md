---
source_file: HistoricalModeListener.html
package: velox.api.layer1.simplified
classes: HistoricalModeListener
methods: onRealtimeStart
extends: HistoricalDataListener
---

# HistoricalModeListener

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Superinterfaces:** HistoricalDataListener

**All Known Subinterfaces:** AllDataModule, HistoricalModeAdapter

## Description

In addition to historical data, you will also be notified on transition between historical data and live data. This adds more computations compared to just getting historical data, since now indicator has to be recalculated on each rewind in replay.

## Methods

### onRealtimeStart

```java
void onRealtimeStart()
```