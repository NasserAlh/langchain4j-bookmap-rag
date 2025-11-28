---
source_file: TimeListener.html
package: velox.api.layer1.simplified
classes: TimeListener
methods: onTimestamp
---

# TimeListener

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Known Subinterfaces:** AllDataModule, TimeAdapter

## Description

Get event timestamps

## Methods

### onTimestamp

```java
void onTimestamp(long t)
```

Time of the next event(s)

**Parameters:**
- `t` - Time in nanoseconds