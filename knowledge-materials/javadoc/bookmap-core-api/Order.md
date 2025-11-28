---
source_file: Order.html
package: velox.api.layer1.layers.utils.mbo
classes: Order
methods: Order, Order, getOrderId, isBid, getPrice, getSize, setPrice, setSize, hashCode, equals
---

# Order

**Package:** velox.api.layer1.layers.utils.mbo

**Type:** Class

**Inheritance:** java.lang.Object → Order

## Constructors

### Order

```java
public Order(String orderId, boolean isBid, int price, int size)
```

### Order

```java
public Order(Order other)
```

## Methods

### getOrderId

```java
public String getOrderId()
```

### isBid

```java
public boolean isBid()
```

### getPrice

```java
public int getPrice()
```

### getSize

```java
public int getSize()
```

### setPrice

```java
public void setPrice(int price)
```

### setSize

```java
public void setSize(int size)
```

### hashCode

```java
public int hashCode()
```

**Overrides:** `hashCode` in class `Object`

### equals

```java
public boolean equals(Object obj)
```

**Overrides:** `equals` in class `Object`

## Inherited Methods

**From java.lang.Object:**
- `clone()`
- `finalize()`
- `getClass()`
- `notify()`
- `notifyAll()`
- `toString()`
- `wait()`
- `wait(long)`
- `wait(long, int)`