---
source_file: Event.html
package: velox.api.layer1.datastructure.events
classes: Event
methods: time, eventType, Event, getTime, clone
---

# Event

**Package:** velox.api.layer1.datastructure.events

**Type:** Class

**Inheritance:** java.lang.Object → Event

**All Implemented Interfaces:** Serializable, Cloneable, CloneableSerializable, CustomGeneratedEvent

**Direct Known Subclasses:** DepthAggregationEvent, MboAggregationEvent, OrderExecutedEvent, OrderUpdatedEvent, OrderUpdatesExecutionsAggregationEvent, TradeAggregationEvent

## Description

Base class for events that can be retrieved from the tree.

**See Also:**
- Serialized Form

---

## Fields

### time

```java
public final long time
```

### eventType

```java
@SerializedName(value="eventType", alternate="event_type")
public final velox.api.layer1.datastructure.events.EventType eventType
```

---

## Constructors

### Event

```java
public Event(long time, velox.api.layer1.datastructure.events.EventType eventType)
```

---

## Methods

### getTime

```java
public long getTime()
```

**Specified by:** `getTime` in interface `CustomGeneratedEvent`

**Returns:** time of this event

### clone

```java
public abstract Object clone()
```

**Specified by:** `clone` in interface `CloneableSerializable`

**Overrides:** `clone` in class `Object`