---
source_file: OrderUpdatesExecutionsAggregationEvent.html
package: velox.api.layer1.datastructure.events
classes: OrderUpdatesExecutionsAggregationEvent
methods: orderUpdates, OrderUpdatesExecutionsAggregationEvent, clone
---

# OrderUpdatesExecutionsAggregationEvent

**Package:** velox.api.layer1.datastructure.events

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.datastructure.events.Event → OrderUpdatesExecutionsAggregationEvent

**All Implemented Interfaces:** Serializable, Cloneable, CloneableSerializable, CustomGeneratedEvent

## Description

Keeps info about all updates and executions that happened to orders

**See Also:**
- Serialized Form

## Fields

### orderUpdates

```java
public final List<Event> orderUpdates
```

List of updates. Will only contain `OrderUpdatedEvent` and `OrderExecutedEvent`

**Inherited Fields from Event:**
- `eventType`
- `time`

## Constructors

### OrderUpdatesExecutionsAggregationEvent

```java
public OrderUpdatesExecutionsAggregationEvent(long time)
```

## Methods

### clone

```java
public Object clone()
```

**Specified by:**
- `clone` in interface `CloneableSerializable`
- `clone` in class `Event`

**Inherited Methods from Event:**
- `getTime()`

**Inherited Methods from Object:**
- `equals(Object)`
- `finalize()`
- `getClass()`
- `hashCode()`
- `notify()`
- `notifyAll()`
- `toString()`
- `wait()`
- `wait(long)`
- `wait(long, int)`