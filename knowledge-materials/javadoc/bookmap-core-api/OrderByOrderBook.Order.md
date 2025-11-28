---
source_file: OrderByOrderBook.Order.html
package: velox.api.layer1.layers.utils
classes: OrderByOrderBook.Order
methods: isBid, price, size, Order
---

# OrderByOrderBook.Order

**Package:** velox.api.layer1.layers.utils

**Type:** Class (static nested)

**Enclosing Class:** `OrderByOrderBook`

**Inheritance:** `java.lang.Object` → `OrderByOrderBook.Order`

## Description

Describes single order.

## Fields

### isBid

```java
public boolean isBid
```

**Description:** true if bid, false if ask

### price

```java
public int price
```

**Description:** order price

### size

```java
public long size
```

**Description:** order size

## Constructors

### Order

```java
public Order(boolean isBid, int price, long size)
```

## Methods Inherited

**From `java.lang.Object`:**
- `clone()`
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