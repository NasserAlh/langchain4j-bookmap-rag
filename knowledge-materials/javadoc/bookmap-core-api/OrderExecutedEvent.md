---
source_file: OrderExecutedEvent.html
package: velox.api.layer1.datastructure.events
classes: OrderExecutedEvent
methods: alias, executionInfo, OrderExecutedEvent, OrderExecutedEvent, clone, toString
---

# OrderExecutedEvent

**Package:** velox.api.layer1.datastructure.events

**Type:** Class

**Inheritance:** `java.lang.Object` → `velox.api.layer1.datastructure.events.Event` → `OrderExecutedEvent`

**All Implemented Interfaces:** `Serializable`, `Cloneable`, `CloneableSerializable`, `CustomGeneratedEvent`

## Description

Information about order execution.

**See Also:**
- Serialized Form

## Fields

### alias

```java
public String alias
```

Alias of an instrument execution belongs to

### executionInfo

```java
public ExecutionInfo executionInfo
```

Information about the order execution

**Fields inherited from class `velox.api.layer1.datastructure.events.Event`:**
- `eventType`
- `time`

## Constructors

### OrderExecutedEvent

```java
public OrderExecutedEvent(long t, ExecutionInfo executionInfo, String alias)
```

### OrderExecutedEvent

```java
public OrderExecutedEvent(OrderExecutedEvent event)
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

**Overrides:**
- `toString` in class `Object`

**Methods inherited from class `velox.api.layer1.datastructure.events.Event`:**
- `getTime`

**Methods inherited from class `java.lang.Object`:**
- `equals`, `finalize`, `getClass`, `hashCode`, `notify`, `notifyAll`, `wait`, `wait`, `wait`