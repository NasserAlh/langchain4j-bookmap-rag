---
source_file: guides/HistoricalData.md
type: guide
topic: historical-data, pre-subscription, backfill, replay, real-time
package: velox.api.layer1.simplified
related_interfaces: HistoricalDataListener, HistoricalModeListener
related_classes: Api
---

# Historical Data

This guide covers handling historical data in Bookmap add-ons, including pre-subscription data, backfill, and the transition to real-time data.

## Types of Historical Data

| Type | Description |
|------|-------------|
| Pre-subscription data | Collected by Bookmap before the add-on was enabled |
| Backfill data | Up to 48 hours of data from Bookmap servers |
| Historical data | Replay data stored in Bookmap's `.bmf` files |

## HistoricalDataListener

Implement `HistoricalDataListener` to handle pre-subscription market data. This allows your add-on to process data that was collected before it was enabled.

## HistoricalModeListener

Use `HistoricalModeListener` to get notified when pre-subscription data processing completes and real-time data begins.

### Key Method

```java
void onRealtimeStart()
```

Called when the add-on transitions from processing historical data to receiving real-time data.

### Example: VWAP with Historical Mode Detection

```java
@Layer1SimpleAttachable
@Layer1StrategyName("Market Data Listener")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
public class MarketDataListener implements CustomModuleAdapter, TradeDataListener, HistoricalModeListener {

    private final Vwap buyers = new Vwap();
    private final Vwap sellers = new Vwap();
    private double minPriceIncrement;
    private Api api;

    @Override
    public void initialize(String alias, InstrumentInfo info, Api api, InitialState initialState) {
        this.api = api;
        minPriceIncrement = info.pips;
    }

    @Override
    public void onTrade(double price, int size, TradeInfo tradeInfo) {
        (tradeInfo.isBidAggressor ? buyers : sellers).onTrade(price, size);
    }

    @Override
    public void stop() {
        double vwapBuy = minPriceIncrement * buyers.priceSize / buyers.volume;
        double vwapSell = minPriceIncrement * sellers.priceSize / sellers.volume;
        double vwap = minPriceIncrement * (buyers.priceSize + sellers.priceSize) / (buyers.volume + sellers.volume);
        Log.info(String.format("VWAP Buy: %.2f, Sell: %.2f, Total: %.2f", vwapBuy, vwapSell, vwap));
    }

    @Override
    public void onRealtimeStart() {
        // Historical data processing complete, real-time data starting
        Log.info("Real-time data started. Unloading...");
        api.unload();
    }
}
```

## Use Cases

### Calculate Metrics from Historical Data

Use `HistoricalModeListener` to:
1. Process all historical data
2. Calculate metrics (VWAP, volume profile, etc.)
3. Either continue with real-time or unload

### Reset State at Real-time Start

```java
@Override
public void onRealtimeStart() {
    // Reset accumulators for real-time only tracking
    buyers.reset();
    sellers.reset();
    Log.info("Switched to real-time mode - accumulators reset");
}
```

### Different Behavior for Historical vs Real-time

```java
private boolean isRealtime = false;

@Override
public void onRealtimeStart() {
    isRealtime = true;
}

@Override
public void onTrade(double price, int size, TradeInfo tradeInfo) {
    if (isRealtime) {
        // Only process real-time trades
        processRealtimeTrade(price, size, tradeInfo);
    } else {
        // Accumulate historical data differently
        accumulateHistoricalTrade(price, size, tradeInfo);
    }
}
```

## See Also

- [Data Listeners](DataListeners.md) - Receiving market data
- [Api Interface](ApiInterface.md) - Using `api.unload()` and other methods
- [Javadoc: HistoricalModeListener](../javadoc/bookmap-simplified-api-documentation-2025-11-20/HistoricalModeListener.md)
