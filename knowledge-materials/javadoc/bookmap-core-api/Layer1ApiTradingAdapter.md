---
source_file: Layer1ApiTradingAdapter.html
package: velox.api.layer1
classes: Layer1ApiTradingAdapter
methods: onOrderUpdated, onOrderExecuted, onStatus, onBalance
extends: ** Layer1ApiTradingListener
---

# Layer1ApiTradingAdapter

**Package:** velox.api.layer1

**Type:** Interface

**All Superinterfaces:** Layer1ApiTradingListener

**All Known Subinterfaces:** Layer1ApiAdapter, OnlineValueCalculatorAdapter, StrategyUpdateGenerator

## Description

Provides default empty implementations.

## Methods

### onOrderUpdated

```java
default void onOrderUpdated(OrderInfoUpdate orderInfoUpdate)
```

Called when order is updated (order creation is also considered an update).

**Parameters:**
- `orderInfoUpdate` - Information about update

### onOrderExecuted

```java
default void onOrderExecuted(ExecutionInfo executionInfo)
```

Called when one of our orders gets executed.

**Parameters:**
- `executionInfo` - Information about execution

### onStatus

```java
default void onStatus(StatusInfo statusInfo)
```

Called when instrument status information changes (PnL, number of open orders, position, etc).

**Parameters:**
- `statusInfo` - Status information

### onBalance

```java
default void onBalance(BalanceInfo balanceInfo)
```

Called when account balance information changes.

**Parameters:**
- `balanceInfo` - Account balance information