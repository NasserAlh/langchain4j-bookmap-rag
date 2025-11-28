---
source_file: MboAggregationEvent.Order.html
package: velox.api.layer1.datastructure.events
classes: MboAggregationEvent.Order
methods: isBid, Order, toString
---

# MboAggregationEvent.Order

**Package:** velox.api.layer1.datastructure.events

**Type:** Class

**Inheritance:**
- `java.lang.Object`
- `velox.api.layer1.datastructure.events.MboAggregationEvent.PriceSizeUpdate`
- `velox.api.layer1.datastructure.events.MboAggregationEvent.Order`

**All Implemented Interfaces:** `Serializable`

**Enclosing class:** `MboAggregationEvent`

**See Also:**
- Serialized Form

## Fields

### isBid

```java
public final boolean isBid
```

**Inherited fields from MboAggregationEvent.PriceSizeUpdate:**
- `price`
- `size`
- `REMOVAL`

## Constructors

### Order

```java
public Order(boolean isBid, int price, int size)
```

## Methods

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `MboAggregationEvent.PriceSizeUpdate`

**Inherited methods from java.lang.Object:**
- `clone()`
- `equals(Object)`
- `finalize()`
- `getClass()`
- `hashCode()`
- `notify()`
- `notifyAll()`
- `wait()`
- `wait(long)`
- `wait(long, int)`