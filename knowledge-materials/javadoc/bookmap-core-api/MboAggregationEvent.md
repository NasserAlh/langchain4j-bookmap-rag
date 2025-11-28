---
source_file: MboAggregationEvent.html
package: velox.api.layer1.datastructure.events
classes: MboAggregationEvent
methods: newOrders, updates, MboAggregationEvent, toString, clone
---

# MboAggregationEvent

**Package:** velox.api.layer1.datastructure.events

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.datastructure.events.Event → MboAggregationEvent

**All Implemented Interfaces:** Serializable, Cloneable, CloneableSerializable, CustomGeneratedEvent

## Description

Aggregation of MBO data in some time interval

**See Also:**
- Serialized Form

## Nested Classes

- `MboAggregationEvent.Order`
- `MboAggregationEvent.PriceSizeUpdate`

## Fields

### newOrders

```java
public Map<String, MboAggregationEvent.Order> newOrders
```

New orders submitted in this period. Key is order id.

### updates

```java
public Map<String, MboAggregationEvent.PriceSizeUpdate> updates
```

Order updates. Key is order id.

**Inherited from Event:**
- `eventType`
- `time`

## Constructors

### MboAggregationEvent

```java
public MboAggregationEvent(long time)
```

## Methods

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`

### clone

```java
public Object clone()
```

**Specified by:**
- `clone` in interface `CloneableSerializable`
- `clone` in class `Event`

**Inherited from Event:**
- `getTime()`

**Inherited from Object:**
- `equals(Object)`
- `finalize()`
- `getClass()`
- `hashCode()`
- `notify()`
- `notifyAll()`
- `wait()`
- `wait(long)`
- `wait(long, int)`