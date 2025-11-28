---
source_file: OrderResizeParameters.html
package: velox.api.layer1.data
classes: OrderResizeParameters
methods: size, OrderResizeParameters, toString
---

# OrderResizeParameters

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.data.OrderUpdateParameters → OrderResizeParameters

## Description

Used to change order size.

## Fields

### size

```java
public final int size
```

## Constructors

### OrderResizeParameters

```java
public OrderResizeParameters(String orderId, int size)
```

## Methods

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `OrderUpdateParameters`

## Inherited Members

**From class velox.api.layer1.data.OrderUpdateParameters:**
- `orderId` field

**From class java.lang.Object:**
- `clone`, `equals`, `finalize`, `getClass`, `hashCode`, `notify`, `notifyAll`, `wait`