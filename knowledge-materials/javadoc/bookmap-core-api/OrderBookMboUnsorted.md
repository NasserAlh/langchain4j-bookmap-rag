---
source_file: OrderBookMboUnsorted.html
package: velox.api.layer1.layers.utils.mbo
classes: OrderBookMboUnsorted
methods: OrderBookMboUnsorted, OrderBookMboUnsorted, OrderBookMboUnsorted, sendDataToListener, send, replace, cancel, getOrder
---

# OrderBookMboUnsorted

**Package:** velox.api.layer1.layers.utils.mbo

**Type:** Class

**Inheritance:** java.lang.Object → OrderBookMboUnsorted

## Description

Faster but less functional alternative to [`OrderBookMbo`](OrderBookMbo.html)

## Constructors

### OrderBookMboUnsorted

```java
public OrderBookMboUnsorted()
```

### OrderBookMboUnsorted

```java
public OrderBookMboUnsorted(OrderBookMboUnsorted other)
```

### OrderBookMboUnsorted

```java
public OrderBookMboUnsorted(OrderBookMbo other)
```

## Methods

### sendDataToListener

```java
public void sendDataToListener(String alias, Layer1ApiMboDataListener listener)
```

Note that unlike [`OrderBookMbo.sendDataToListener(String, Layer1ApiMboDataListener)`](OrderBookMbo.html#sendDataToListener(java.lang.String,velox.api.layer1.Layer1ApiMboDataListener)) this one is somewhat slow - it has to sort orders by levels first, which is done by constructing [`OrderBookMbo`](OrderBookMbo.html)

### send

```java
public void send(String orderId, boolean isBid, int price, int size)
```

### replace

```java
public void replace(String orderId, int price, int size)
```

### cancel

```java
public void cancel(String orderId)
```

### getOrder

```java
public Order getOrder(String orderId)
```