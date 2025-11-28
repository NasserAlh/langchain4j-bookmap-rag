---
source_file: guides/OrderPlacement.md
type: guide
topic: order-placement, trading, orders, stop-loss, take-profit
package: velox.api.layer1.data
related_classes: SimpleOrderSendParametersBuilder, SimpleOrderSendParameters, OrderDuration, Api
annotations: Layer1TradingStrategy
---

# Order Placement

This guide covers placing orders in Bookmap add-ons using `SimpleOrderSendParametersBuilder`.

## Prerequisites

Add-ons that place orders must include the `@Layer1TradingStrategy` annotation:

```java
@Layer1SimpleAttachable
@Layer1StrategyName("My Trading Strategy")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
@Layer1TradingStrategy  // Required for order placement
public class MyTradingStrategy implements CustomModuleAdapter {
    // ...
}
```

## SimpleOrderSendParametersBuilder

**Package:** `velox.api.layer1.data`

### Constructor

```java
public SimpleOrderSendParametersBuilder(String alias, boolean isBuy, int size)
```

| Parameter | Description |
|-----------|-------------|
| `alias` | Instrument alias from `initialize()` |
| `isBuy` | `true` for buy order, `false` for sell |
| `size` | Order quantity |

### Available Fields

**From AbstractSimpleOrderSendParametersBuilder:**
- `additionalStopLossTiers`, `additionalTakeProfitTiers`
- `closingPositionHint`, `reversingPositionHint`
- `doNotIncrease`, `limitPrice`, `stopPrice`
- `stopLossClientId`, `stopLossOffset`, `stopLossTrailingStep`
- `takeProfitClientId`, `takeProfitOffset`, `trailingStep`

**From AbstractSingleOrderSendParametersBuilder:**
- `alias`, `clientId`, `duration`, `isBuy`, `size`

## OrderPlacer Helper Class

A reusable helper class for placing orders:

```java
class OrderPlacer {
    private final String alias;
    private final Api api;

    public OrderPlacer(String alias, Api api) {
        if (alias == null || api == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        this.alias = alias;
        this.api = api;
    }

    public boolean placeOrder(boolean isBuy, double price, int quantity,
                              int trailingStopStep, int takeProfitOffset, int stopLossOffset) {
        SimpleOrderSendParametersBuilder builder =
            new SimpleOrderSendParametersBuilder(alias, isBuy, quantity);

        builder.setDuration(OrderDuration.IOC);
        builder.setTakeProfitOffset(takeProfitOffset);
        builder.setStopLossOffset(stopLossOffset);

        if (trailingStopStep > 0) {
            builder.setStopLossTrailingStep(trailingStopStep);
        }

        SimpleOrderSendParameters order = builder.build();
        api.sendOrder(order);
        return true;
    }
}
```

## Integration Steps

### 1. Define the Field

```java
private OrderPlacer orderPlacer;
```

### 2. Initialize in initialize()

```java
@Override
public void initialize(String alias, InstrumentInfo info, Api api, InitialState initialState) {
    orderPlacer = new OrderPlacer(alias, api);
}
```

### 3. Use in Callbacks

```java
@Override
public void onTrade(double price, int size, TradeInfo tradeInfo) {
    // Place order with 20 tick take profit, 10 tick stop loss
    orderPlacer.placeOrder(true, price, 1, 0, 20, 10);
}
```

## Complete Add-On Example

```java
import velox.api.layer1.data.OrderDuration;
import velox.api.layer1.data.SimpleOrderSendParameters;
import velox.api.layer1.data.SimpleOrderSendParametersBuilder;
import velox.api.layer1.simplified.Api;
import velox.api.layer1.simplified.CustomModuleAdapter;
import velox.api.layer1.simplified.InitialState;
import velox.api.layer1.simplified.InstrumentInfo;
import velox.api.layer1.simplified.Layer1ApiVersionValue;
import velox.api.layer1.simplified.TradeInfo;
import velox.api.layer1.simplified.Log;
import velox.api.layer1.simplified.annotations.Layer1SimpleAttachable;
import velox.api.layer1.simplified.annotations.Layer1StrategyName;
import velox.api.layer1.simplified.annotations.Layer1ApiVersion;

@Layer1SimpleAttachable
@Layer1StrategyName("Order Placer Example")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
@Layer1TradingStrategy
public class OrderPlacerAddon extends CustomModuleAdapter {

    private OrderPlacer orderPlacer;

    @Override
    public void initialize(String alias, InstrumentInfo info, Api api, InitialState initialState) {
        orderPlacer = new OrderPlacer(alias, api);
    }

    @Override
    public void onTrade(double price, int size, TradeInfo tradeInfo) {
        orderPlacer.placeOrder(true, price, 1, 0, 20, 10);
    }
}
```

## Order Duration Options

| Duration | Description |
|----------|-------------|
| `OrderDuration.IOC` | Immediate or Cancel |
| `OrderDuration.GTC` | Good Till Cancel |
| `OrderDuration.DAY` | Day order |

## Common Patterns

### Market Order with Brackets

```java
SimpleOrderSendParametersBuilder builder =
    new SimpleOrderSendParametersBuilder(alias, true, 1);
builder.setDuration(OrderDuration.IOC);
builder.setTakeProfitOffset(20);  // 20 ticks profit target
builder.setStopLossOffset(10);    // 10 ticks stop loss
api.sendOrder(builder.build());
```

### Trailing Stop

```java
SimpleOrderSendParametersBuilder builder =
    new SimpleOrderSendParametersBuilder(alias, true, 1);
builder.setStopLossOffset(10);
builder.setStopLossTrailingStep(5);  // Trail by 5 ticks
api.sendOrder(builder.build());
```

### Limit Order

```java
SimpleOrderSendParametersBuilder builder =
    new SimpleOrderSendParametersBuilder(alias, true, 1);
builder.setLimitPrice(desiredPrice);
builder.setDuration(OrderDuration.GTC);
api.sendOrder(builder.build());
```

## See Also

- [Api Interface](ApiInterface.md) - Using `api.sendOrder()`
- [OrderBlockStrategy Example](../examples/OrderBlockStrategy.md) - Trading strategy with orders
- [Javadoc: SimpleOrderSendParametersBuilder](../javadoc/bookmap-core-api/SimpleOrderSendParametersBuilder.md)
