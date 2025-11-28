---
source_file: OrderBookMbo.html
package: velox.api.layer1.layers.utils.mbo
classes: OrderBookMbo
methods: OrderBookMbo, OrderBookMbo, sendDataToListener, isEmpty, getBestBidPrice, getBestAskPrice, send, replace, cancel, getOrder
---

# OrderBookMbo

**Package:** velox.api.layer1.layers.utils.mbo

**Type:** Class

**Inheritance:** java.lang.Object → OrderBookMbo

## Constructors

### OrderBookMbo

```java
public OrderBookMbo()
```

### OrderBookMbo

```java
public OrderBookMbo(OrderBookMbo other)
```

## Methods

### sendDataToListener

```java
void sendDataToListener(String alias, Layer1ApiMboDataListener listener)
```

### isEmpty

```java
boolean isEmpty()
```

### getBestBidPrice

```java
int getBestBidPrice()
```

### getBestAskPrice

```java
int getBestAskPrice()
```

### send

```java
void send(String orderId, boolean isBid, int price, int size)
```

### replace

```java
void replace(String orderId, int price, int size)
```

### cancel

```java
void cancel(String orderId)
```

### getOrder

```java
Order getOrder(String orderId)
```