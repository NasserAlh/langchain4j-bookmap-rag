---
source_file: CustomGeneratedEvent.html
package: velox.api.layer1.layers.strategies.interfaces
classes: CustomGeneratedEvent
methods: getTime, From CloneableSerializable
extends: ** Cloneable, CloneableSerializable, Serializable
---

# CustomGeneratedEvent

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Interface

**All Superinterfaces:** Cloneable, CloneableSerializable, Serializable

**All Known Implementing Classes:** DepthAggregationEvent, Event, MboAggregationEvent, OrderExecutedEvent, OrderUpdatedEvent, OrderUpdatesExecutionsAggregationEvent, TradeAggregationEvent

## Description

All events created by custom event generators should implement this interface.

## Methods

### getTime

```java
long getTime()
```

**Returns:** Time of this event

## Inherited Methods

### From CloneableSerializable

```java
Object clone()
```