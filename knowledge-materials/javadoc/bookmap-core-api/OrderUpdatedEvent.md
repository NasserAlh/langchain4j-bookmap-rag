---
source_file: OrderUpdatedEvent.html
package: velox.api.layer1.datastructure.events
classes: OrderUpdatedEvent
methods: alias, orderInfoUpdate, OrderUpdatedEvent, OrderUpdatedEvent, clone, toString
---

# OrderUpdatedEvent

**Package:** velox.api.layer1.datastructure.events

**Type:** Class

**Inheritance:** `java.lang.Object` → `velox.api.layer1.datastructure.events.Event` → `OrderUpdatedEvent`

**All Implemented Interfaces:** `Serializable`, `Cloneable`, `CloneableSerializable`, `CustomGeneratedEvent`

## Description

Order update event. Currently just a wrapper around `OrderInfoUpdate`, probably will contain more data in the future.

**See Also:**
- Serialized Form

## Fields

### alias

```java
public final String alias
```

### orderInfoUpdate

```java
public final OrderInfoUpdate orderInfoUpdate
```

**Fields inherited from class `velox.api.layer1.datastructure.events.Event`:**
- `eventType`
- `time`

## Constructors

### OrderUpdatedEvent

```java
public OrderUpdatedEvent(long t, OrderInfoUpdate orderInfoUpdate, String alias)
```

### OrderUpdatedEvent

```java
public OrderUpdatedEvent(OrderUpdatedEvent event)
```

## Methods

### clone

```java
public Object clone()
```

**Specified by:**
- `clone` in interface `CloneableSerializable`
- `clone` in class `Event`

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`

**Methods inherited from class `velox.api.layer1.datastructure.events.Event`:**
- `getTime`

**Methods inherited from class `java.lang.Object`:**
- `equals`
- `finalize`
- `getClass`
- `hashCode`
- `notify`
- `notifyAll`
- `wait`
- `wait(long)`
- `wait(long, int)`