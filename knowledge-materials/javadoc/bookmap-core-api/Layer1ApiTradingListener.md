---
source_file: Layer1ApiTradingListener.html
package: velox.api.layer1
classes: Layer1ApiTradingListener
methods: onOrderUpdated, onOrderExecuted, onStatus, onBalance
---

# Layer1ApiTradingListener

**Package:** velox.api.layer1

**Type:** Interface

**All Known Subinterfaces:**
- `Layer1ApiAdapter`
- `Layer1ApiListener`
- `Layer1ApiTradingAdapter`
- `OnlineValueCalculatorAdapter`
- `StrategyUpdateGenerator`

**All Known Implementing Classes:**
- `Layer1ApiDepthFreezer`
- `Layer1ApiInjectorRelay`
- `Layer1ApiRelay`
- `Layer1ApiStrategiesEchoMessagesLayer`
- `Layer1ApiUpstreamRelay`

## Description

Listener for order routing.

Multi-account support:
If there is a multi-account support from data provider, then account id should be specified for each order update, status and balance update sent to this listener.

- If both data provider, and strategy has multi-account support (i.e. strategy is annotated with `Layer1MultiAccountTradingSupported`), then data for each account will be sent to the strategy, where each account is identified by accountId field.
- If data provider has multi-account support, but strategy doesn't, only data for primary account will be sent to the strategy (see `AccountInfo.isPrimary`). But accountId field still should be always non-null, to not mix nulls and primary account details from the same data provider and instrument.
- If data provider doesn't have multi-account support, then accountId field in the data sent by the data provider must be always null, and such data should relate to the single account.

**See Also:**
- `TradingAccountsInfoMessage`
- `Layer1MultiAccountTradingSupported`

## Methods

### onOrderUpdated

```java
void onOrderUpdated(OrderInfoUpdate orderInfoUpdate)
```

Called when order is updated (order creation is also considered an update).

**Parameters:**
- `orderInfoUpdate` - Information about update

### onOrderExecuted

```java
void onOrderExecuted(ExecutionInfo executionInfo)
```

Called when one of our orders gets executed.

**Parameters:**
- `executionInfo` - Information about execution

### onStatus

```java
void onStatus(StatusInfo statusInfo)
```

Called when instrument status information changes (PnL, number of open orders, position, etc).

**Parameters:**
- `statusInfo` - Status information

### onBalance

```java
void onBalance(BalanceInfo balanceInfo)
```

Called when account balance information changes.

**Parameters:**
- `balanceInfo` - Account balance information