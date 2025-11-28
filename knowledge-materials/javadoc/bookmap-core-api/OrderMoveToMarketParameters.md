---
source_file: OrderMoveToMarketParameters.html
package: velox.api.layer1.data
classes: OrderMoveToMarketParameters
methods: offset, OrderMoveToMarketParameters, toString
---

# OrderMoveToMarketParameters

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** `java.lang.Object` → `velox.api.layer1.data.OrderUpdateParameters` → `OrderMoveToMarketParameters`

## Description

Used to change order price so it gets immediately executed.

## Fields

### offset

```java
public final int offset
```

How deep into market should order be moved.

**Inherited Fields from OrderUpdateParameters:**
- `orderId`

## Constructors

### OrderMoveToMarketParameters

```java
public OrderMoveToMarketParameters(String orderId, int offset)
```

## Methods

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `OrderUpdateParameters`

**Inherited Methods from Object:**
- `clone`, `equals`, `finalize`, `getClass`, `hashCode`, `notify`, `notifyAll`, `wait`, `wait(long)`, `wait(long, int)`