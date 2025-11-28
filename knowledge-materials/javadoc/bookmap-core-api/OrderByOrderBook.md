---
source_file: OrderByOrderBook.html
package: velox.api.layer1.layers.utils
classes: OrderByOrderBook
methods: OrderByOrderBook.Order, OrderByOrderBook.OrderUpdateResult, OrderByOrderBook, getOrderBook, addOrder, updateOrder, removeOrder, getLastPriceOfOrder, getSide, hasOrder, getOrder, getAllIds, selfTest
---

# OrderByOrderBook

**Package:** velox.api.layer1.layers.utils

**Type:** Class

**Inheritance:** java.lang.Object → OrderByOrderBook

## Description

Class designed to help with conversion from MBO to MBP data.

## Nested Classes

### OrderByOrderBook.Order

Describes single order

### OrderByOrderBook.OrderUpdateResult

Used to describe `updateOrder(long, int, long)` result.

## Constructors

### OrderByOrderBook

```java
public OrderByOrderBook()
```

## Methods

### getOrderBook

```java
public OrderBook getOrderBook()
```

Returns a backing order book. Do not modify it.

**Returns:** Backing order book

---

### addOrder

```java
public long addOrder(long id, boolean isBid, int price, long size) throws IllegalArgumentException
```

Add new order

**Parameters:**
- `id` - Unique order identifier
- `isBid` - True for bid side, false for ask side
- `price` - Price of the order
- `size` - Size of the order

**Returns:** New size at the level where order is added

**Throws:**
- `IllegalArgumentException` - Update leads to invalid book state (crosses are considered valid state)

---

### updateOrder

```java
public OrderByOrderBook.OrderUpdateResult updateOrder(long id, int price, long size) throws IllegalArgumentException
```

Update existing order

**Parameters:**
- `id` - Unique order identifier
- `price` - New price of the order
- `size` - New size of the order

**Returns:** `OrderByOrderBook.OrderUpdateResult` describing effect of this update

**Throws:**
- `IllegalArgumentException` - Update leads to invalid book state (crosses are considered valid state)

---

### removeOrder

```java
public long removeOrder(long id) throws IllegalArgumentException
```

Remove existing order from the book

**Parameters:**
- `id` - Unique order identifier

**Returns:** New size on the level where order is removed

**Throws:**
- `IllegalArgumentException` - If order does not exist

---

### getLastPriceOfOrder

```java
public int getLastPriceOfOrder(long id) throws NullPointerException
```

Get current price corresponding to the order

**Parameters:**
- `id` - Unique order identifier

**Returns:** Price of the order

**Throws:**
- `NullPointerException` - If order does not exist

---

### getSide

```java
public boolean getSide(long id) throws NullPointerException
```

Get side of an order

**Parameters:**
- `id` - Unique order identifier

**Returns:** True if bid, false if ask

**Throws:**
- `NullPointerException` - If order does not exist

---

### hasOrder

```java
public boolean hasOrder(long id)
```

Check if order is in the book

**Parameters:**
- `id` - Unique order identifier

**Returns:** True if order is in the book, false otherwise

---

### getOrder

```java
public OrderByOrderBook.Order getOrder(long id)
```

Get order by id

**Parameters:**
- `id` - Unique order identifier

**Returns:** Order information or null if it does not exist

---

### getAllIds

```java
public Collection<Long> getAllIds()
```

Get all identifiers of orders in the book

**Returns:** All identifiers of orders in the book

---

### selfTest

```java
public void selfTest()
```

Run self-check verifying order book validity. Detects some common issues (but not all).