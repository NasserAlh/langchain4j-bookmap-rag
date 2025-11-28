---
source_file: OrderMoveParameters.html
package: velox.api.layer1.data
classes: OrderMoveParameters
methods: stopPrice, limitPrice, OrderMoveParameters, toString
---

# OrderMoveParameters

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** `java.lang.Object` → `velox.api.layer1.data.OrderUpdateParameters` → `OrderMoveParameters`

## Description

Used to change order price(s)

## Fields

### stopPrice

```java
public final double stopPrice
```

New stop price, NaN if order does not have a stop price

### limitPrice

```java
public final double limitPrice
```

New limit price, NaN if order does not have a limit price

**Inherited from OrderUpdateParameters:**
- `orderId` - Order identifier

## Constructors

### OrderMoveParameters

```java
public OrderMoveParameters(String orderId, double stopPrice, double limitPrice)
```

## Methods

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `OrderUpdateParameters`

**Inherited from Object:**
- `clone`, `equals`, `finalize`, `getClass`, `hashCode`, `notify`, `notifyAll`, `wait`, `wait(long)`, `wait(long, int)`