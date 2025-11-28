---
source_file: IntervalAdapter.html
package: velox.api.layer1.simplified
classes: IntervalAdapter
methods: getInterval, onInterval
extends: IntervalListener
---

# IntervalAdapter

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Superinterfaces:** IntervalListener

## Description

An adapter for `IntervalListener` with empty default method implementations.

## Methods

### getInterval

```java
default long getInterval()
```

Return desired interval width in nanoseconds. Should always be larger than `Intervals.MIN_INTERVAL`. You can use other constants in `Intervals` class for common intervals, but you are not required to.

**Specified by:** `getInterval` in interface `IntervalListener`

### onInterval

```java
default void onInterval()
```

Called with frequency set by `IntervalListener.getInterval()`. Useful as replacement for `BarDataListener.onBar(velox.api.layer1.layers.utils.OrderBook, Bar)` when you don't really need the bar itself and just want a timer functionality (keep in mind, that indicator time is not bound to computer clock, so using general-purpose timers won't work in many cases)

**Specified by:** `onInterval` in interface `IntervalListener`