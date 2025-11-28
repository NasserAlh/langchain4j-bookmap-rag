---
source_file: Layer1ApiDataListener.html
package: velox.api.layer1
classes: Layer1ApiDataListener
methods: onTrade, onDepth, onMarketMode
---

# Layer1ApiDataListener

**Package:** velox.api.layer1

**Type:** Interface

**All Known Subinterfaces:**
- Layer1ApiAdapter
- Layer1ApiDataAdapter
- Layer1ApiListener
- OnlineValueCalculatorAdapter
- StrategyUpdateGenerator

**All Known Implementing Classes:**
- Layer1ApiDepthFreezer
- Layer1ApiInjectorRelay
- Layer1ApiRelay
- Layer1ApiStrategiesEchoMessagesLayer
- Layer1ApiUpstreamRelay

## Description

Listener for trading events

## Methods

### onTrade

```java
void onTrade(String alias, double price, int size, TradeInfo tradeInfo)
```

Trade

**Parameters:**
- `alias` - Instrument alias
- `price` - Price in the same units as in depth update (e.g. if pips=25 then price=1000 means 25000)
- `size` - Trade size
- `tradeInfo` - Additional information about the trade

### onDepth

```java
void onDepth(String alias, boolean isBid, int price, int size)
```

Depth data update.

**Parameters:**
- `alias` - Instrument alias
- `isBid` - True if it is for bids, false for asks
- `price` - Price where size changed
- `size` - New size

### onMarketMode

```java
void onMarketMode(String alias, MarketMode marketMode)
```

Market mode update.

**Parameters:**
- `alias` - 
- `marketMode` -