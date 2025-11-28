---
source_file: OrderCancelParameters.html
package: velox.api.layer1.data
classes: OrderCancelParameters
methods: batchId, batchEnd, OrderCancelParameters, OrderCancelParameters, OrderCancelParameters, toString
---

# OrderCancelParameters

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.data.OrderUpdateParameters → OrderCancelParameters

## Description

Used to cancel the order.

## Fields

### batchId

```java
public final long batchId
```

Orders with the same value will be attempted to be cancelled at once. This is a hint to provider that these requests are sent at once and optimizations can be applied. Some providers might skip redundant cancellations within batch.

### batchEnd

```java
public final boolean batchEnd
```

Marks end of batch. Failing to send this might indefinitely delay execution of batch.

**Inherited Fields:**
- `orderId` from `velox.api.layer1.data.OrderUpdateParameters`

## Constructors

### OrderCancelParameters

```java
public OrderCancelParameters(String orderId)
```

Request to cancel single order.

### OrderCancelParameters

```java
public OrderCancelParameters(String orderId, boolean batchEnd)
```

Request to cancel single order.

**Parameters:**
- `orderId` - ID of the order to cancel.
- `batchEnd` - If true it's a single order cancellation request. If false it's a start of new cancellation batch. Unique batch ID will be generated automatically.

### OrderCancelParameters

```java
public OrderCancelParameters(String orderId, long batchId, boolean batchEnd)
```

## Methods

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `velox.api.layer1.data.OrderUpdateParameters`

**Inherited Methods from java.lang.Object:**
- `clone`, `equals`, `finalize`, `getClass`, `hashCode`, `notify`, `notifyAll`, `wait`, `wait`, `wait`