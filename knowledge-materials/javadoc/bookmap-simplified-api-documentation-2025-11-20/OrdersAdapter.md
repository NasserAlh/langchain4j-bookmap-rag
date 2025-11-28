---
source_file: OrdersAdapter.html
package: velox.api.layer1.simplified
classes: OrdersAdapter
methods: onOrderUpdated, onOrderExecuted
extends: OrdersListener
---

# OrdersAdapter

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Superinterfaces:** OrdersListener

## Description

An adapter for `OrdersListener` with empty default method implementations.

## Methods

### onOrderUpdated

```java
default void onOrderUpdated(velox.api.layer1.data.OrderInfoUpdate orderInfoUpdate)
```

Called each time order is changed (placed/cancelled/updated/filled/partially filled). Please also see `OrdersListener` description.

**Specified by:** `onOrderUpdated` in interface `OrdersListener`

### onOrderExecuted

```java
default void onOrderExecuted(velox.api.layer1.data.ExecutionInfo executionInfo)
```

Called when execution (fill/partial fill) happens. Please also see `OrdersListener` description. Order with `ExecutionInfo.executionId` is supposed to exist (you should get at least one `OrdersListener.onOrderUpdated(OrderInfoUpdate)` for that order first). Note, that there will be a separate `OrdersListener.onOrderUpdated(OrderInfoUpdate)` call reflecting filled/unfilled size change.

**Specified by:** `onOrderExecuted` in interface `OrdersListener`