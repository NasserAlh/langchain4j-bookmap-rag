---
source_file: OrdersListener.html
package: velox.api.layer1.simplified
classes: OrdersListener
methods: onOrderUpdated, onOrderExecuted
---

# OrdersListener

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Known Subinterfaces:** AllDataModule, OrdersAdapter

## Description

Provides order updates.

**Warning 1: Order prices are provided as raw price value (without dividing by min tick (pips)), so you will have to do multiplication yourself if you want to show those on main chart.**

`HistoricalDataListener` should **not** be implemented if you want this to work, consider to use `HistoricalModeListener` instead.

## Methods

### onOrderUpdated

```java
void onOrderUpdated(velox.api.layer1.data.OrderInfoUpdate orderInfoUpdate)
```

Called each time order is changed (placed/cancelled/updated/filled/partially filled). Please also see `OrdersListener` description.

### onOrderExecuted

```java
void onOrderExecuted(velox.api.layer1.data.ExecutionInfo executionInfo)
```

Called when execution (fill/partial fill) happens. Please also see `OrdersListener` description. Order with `ExecutionInfo.executionId` is supposed to exist (you should get at least one `onOrderUpdated(OrderInfoUpdate)` for that order first).

Note, that there will be a separate `onOrderUpdated(OrderInfoUpdate)` call reflecting filled/unfilled size change.