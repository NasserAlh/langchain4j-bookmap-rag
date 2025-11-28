---
source_file: OrderByOrderBook.OrderUpdateResult.html
package: velox.api.layer1.layers.utils
classes: OrderByOrderBook.OrderUpdateResult
methods: fromPrice, fromSize, toSize, OrderUpdateResult
---

# OrderByOrderBook.OrderUpdateResult

**Package:** velox.api.layer1.layers.utils

**Type:** Class

**Enclosing Class:** [`OrderByOrderBook`](OrderByOrderBook.html)

**Inheritance:** `java.lang.Object` → `OrderByOrderBook.OrderUpdateResult`

## Description

Used to describe [`OrderByOrderBook.updateOrder(long, int, long)`](OrderByOrderBook.html#updateOrder(long,int,long)) result.

## Fields

### fromPrice

```java
public final int fromPrice
```

Price where the modified order was before

### fromSize

```java
public final long fromSize
```

Size on [`fromPrice`](#fromPrice) after modification

### toSize

```java
public final long toSize
```

Size on new order price after modification

## Constructors

### OrderUpdateResult

```java
public OrderUpdateResult(int fromPrice, long fromSize, long toSize)
```