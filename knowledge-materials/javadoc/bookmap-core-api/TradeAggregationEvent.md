---
source_file: TradeAggregationEvent.html
package: velox.api.layer1.datastructure.events
classes: TradeAggregationEvent
methods: bidAggressorMap, askAggressorMap, lastPrice, lastSize, lastAggressorBid, TradeAggregationEvent, clone, getBidTradeSize, getAskTradeSize, getTotalTradeSize, toString
---

# TradeAggregationEvent

**Package:** velox.api.layer1.datastructure.events

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.datastructure.events.Event → TradeAggregationEvent

**All Implemented Interfaces:** Serializable, Cloneable, CloneableSerializable, CustomGeneratedEvent

## Description

Aggregation of trade events in some time interval

**See Also:**
- Serialized Form

## Fields

### bidAggressorMap

```java
public Map<Double, Map<Integer, Integer>> bidAggressorMap
```

Map of trades, where bid is aggressor, where key is price level (if multiplied by instrument pips, will give price), and value is map, describing trades at this level, in following format:  
Key: size of trade, value: number of trades of this size

### askAggressorMap

```java
public Map<Double, Map<Integer, Integer>> askAggressorMap
```

Map of trades, where ask is aggressor, where key is price level (if multiplied by instrument pips, will give price), and value is map, describing trades at this level, in following format:  
Key: size of trade, value: number of trades of this size

### lastPrice

```java
public double lastPrice
```

Last trade price (in requested interval). `Double.NaN` if none.

### lastSize

```java
public Integer lastSize
```

Last trade size (in requested interval). null if none.

### lastAggressorBid

```java
public Boolean lastAggressorBid
```

Last trade aggressor (in requested interval). null if none.

**Fields inherited from class velox.api.layer1.datastructure.events.Event:**
- `eventType`
- `time`

## Constructors

### TradeAggregationEvent

```java
public TradeAggregationEvent(long time)
```

## Methods

### clone

```java
public Object clone()
```

**Specified by:**
- `clone` in interface `CloneableSerializable`
- `clone` in class `Event`

### getBidTradeSize

```java
public int getBidTradeSize()
```

### getAskTradeSize

```java
public int getAskTradeSize()
```

### getTotalTradeSize

```java
public int getTotalTradeSize()
```

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`

**Methods inherited from class velox.api.layer1.datastructure.events.Event:**
- `getTime`

**Methods inherited from class java.lang.Object:**
- `equals`, `finalize`, `getClass`, `hashCode`, `notify`, `notifyAll`, `wait`, `wait`, `wait`