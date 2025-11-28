---
source_file: Layer1ApiDataAdapter.html
package: velox.api.layer1
classes: Layer1ApiDataAdapter
methods: onTrade, onDepth, onMarketMode
extends: ** Layer1ApiDataListener
---

# Layer1ApiDataAdapter

**Package:** velox.api.layer1

**Type:** Interface

**All Superinterfaces:** Layer1ApiDataListener

**All Known Subinterfaces:** Layer1ApiAdapter, OnlineValueCalculatorAdapter, StrategyUpdateGenerator

## Description

Provides default empty implementations.

## Methods

### onTrade

```java
default void onTrade(String alias, double price, int size, TradeInfo tradeInfo)
```

Trade

**Parameters:**
- `alias` - Instrument alias
- `price` - Price in the same units as in depth update (e.g. if pips=25 then price=1000 means 25000)
- `size` - Trade size
- `tradeInfo` - Additional information about the trade

### onDepth

```java
default void onDepth(String alias, boolean isBid, int price, int size)
```

Depth data update.

**Parameters:**
- `alias` - Instrument alias
- `isBid` - True if it is for bids, false for asks
- `price` - Price where size changed
- `size` - New size

### onMarketMode

```java
default void onMarketMode(String alias, MarketMode marketMode)
```

Market mode update.

**Parameters:**
- `alias` - 
- `marketMode` -