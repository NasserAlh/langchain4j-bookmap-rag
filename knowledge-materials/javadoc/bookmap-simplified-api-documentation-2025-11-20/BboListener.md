---
source_file: BboListener.html
package: velox.api.layer1.simplified
classes: BboListener
methods: onBbo
---

# BboListener

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Known Subinterfaces:** AllDataModule, BboAdapter

## Description

Get best bid/offer updates each time one of those changes.

## Methods

### onBbo

```java
void onBbo(int bidPrice, int bidSize, int askPrice, int askSize)
```

Called on each BBO change providing new price/size value