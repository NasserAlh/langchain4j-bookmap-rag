---
source_file: IntervalListener.html
package: velox.api.layer1.simplified
classes: IntervalListener
methods: getInterval, onInterval
---

# IntervalListener

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Known Subinterfaces:** AllDataModule, BarDataAdapter, BarDataListener, IntervalAdapter

## Methods

### getInterval

```java
long getInterval()
```

Return desired interval width in nanoseconds. Should always be larger than `Intervals.MIN_INTERVAL`. You can use other constants in `Intervals` class for common intervals, but you are not required to.

**Returns:** Desired interval width in nanoseconds

### onInterval

```java
void onInterval()
```

Called with frequency set by `getInterval()`. Useful as replacement for `BarDataListener.onBar(velox.api.layer1.layers.utils.OrderBook, Bar)` when you don't really need the bar itself and just want a timer functionality (keep in mind, that indicator time is not bound to computer clock, so using general-purpose timers won't work in many cases)