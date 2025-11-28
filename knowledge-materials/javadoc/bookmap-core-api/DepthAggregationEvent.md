---
source_file: DepthAggregationEvent.html
package: velox.api.layer1.datastructure.events
classes: DepthAggregationEvent
methods: bidsMap, asksMap, DepthAggregationEvent, DepthAggregationEvent, getMap, toString, bestBid, clone
---

# DepthAggregationEvent

**Package:** velox.api.layer1.datastructure.events

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.datastructure.events.Event → DepthAggregationEvent

**All Implemented Interfaces:** Serializable, Cloneable, CloneableSerializable, CustomGeneratedEvent

## Description

Aggregation of depth events in some time interval

**See Also:**
- Serialized Form

## Fields

### bidsMap

```java
public Map<Integer, Integer> bidsMap
```

Map of bids, where key is price level (if multiplied by instrument pips, will give price), and value is size at this level

### asksMap

```java
public Map<Integer, Integer> asksMap
```

Map of asks, where key is price level (if multiplied by instrument pips, will give price), and value is size at this level

**Fields inherited from class velox.api.layer1.datastructure.events.Event:**
- `eventType`
- `time`

## Constructors

### DepthAggregationEvent

```java
public DepthAggregationEvent(long time)
```

### DepthAggregationEvent

```java
public DepthAggregationEvent(long time, int bidsCapacity, int asksCapacity)
```

## Methods

### getMap

```java
public Map<Integer, Integer> getMap(boolean isBid)
```

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`

### bestBid

```java
public int bestBid()
```

### clone

```java
public Object clone()
```

**Specified by:**
- `clone` in interface `CloneableSerializable`
- `clone` in class `Event`

**Methods inherited from class velox.api.layer1.datastructure.events.Event:**
- `getTime()`

**Methods inherited from class java.lang.Object:**
- `equals(Object)`
- `finalize()`
- `getClass()`
- `hashCode()`
- `notify()`
- `notifyAll()`
- `wait()`
- `wait(long)`
- `wait(long, int)`