---
source_file: BarDataListener.html
package: velox.api.layer1.simplified
classes: BarDataListener
methods: onBar, onInterval, From Interface velox.api.layer1.simplified.IntervalListener
extends: IntervalListener
---

# BarDataListener

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Superinterfaces:** IntervalListener

**All Known Subinterfaces:** AllDataModule, BarDataAdapter

## Description

Get bars and order book snapshot with fixed interval

## Methods

### onBar

```java
void onBar(velox.api.layer1.layers.utils.OrderBook orderBook, velox.api.layer1.simplified.Bar bar)
```

Called when bar is ready. Bar width is specified via `IntervalListener.getInterval()`

### onInterval

```java
default void onInterval()
```

`BarDataListener` provides default empty implementation for `IntervalListener.onInterval()` since you get `onBar(OrderBook, Bar)` called at the same time. But you still can override and use this method.

**Specified by:** `onInterval` in interface `IntervalListener`

## Inherited Methods

### From Interface velox.api.layer1.simplified.IntervalListener

```java
getInterval()
```