---
source_file: OrderInfo.html
package: velox.api.layer1.data
classes: OrderInfo
methods: instrumentAlias, orderId, isBuy, type, clientId, exchangeId, doNotIncrease, filled, unfilled, averageFillPrice, duration, status, limitPrice, stopPrice, stopTriggered, modificationUtcTime, isSimulated, isDuplicate, accountId, OrderInfo
total_methods: 23
---

# OrderInfo

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.data.OrderInfo

**All Implemented Interfaces:** Serializable, MultiAccountAware

**Direct Known Subclasses:** OrderInfoUpdate

## Description

Cumulative information about the order

**See Also:**
- Serialized Form

## Fields

### instrumentAlias
```java
public final String instrumentAlias
```

### orderId
```java
public final String orderId
```

### isBuy
```java
public final boolean isBuy
```

### type
```java
public final OrderType type
```

### clientId
```java
public final String clientId
```

### exchangeId
```java
public final String exchangeId
```

### doNotIncrease
```java
public final boolean doNotIncrease
```

### filled
```java
public final int filled
```

### unfilled
```java
public final int unfilled
```

### averageFillPrice
```java
public final double averageFillPrice
```

### duration
```java
public final OrderDuration duration
```

### status
```java
public final OrderStatus status
```

### limitPrice
```java
public final double limitPrice
```

### stopPrice
```java
public final double stopPrice
```

### stopTriggered
```java
public final boolean stopTriggered
```

### modificationUtcTime
```java
public final long modificationUtcTime
```

### isSimulated
```java
public final boolean isSimulated
```

### isDuplicate
```java
public final boolean isDuplicate
```

Order can be duplicated in case of crosstrading

### accountId
```java
public final String accountId
```

Account id to identify which account this order info belongs to.
Null if there is no multi-account support (only single account is supported by the data provider).
Should not mix nulls and specific account in the same data provider.

## Constructors

### OrderInfo
```java
@Deprecated
public OrderInfo(String instrumentAlias, String orderId, boolean isBuy, OrderType type, String clientId, boolean doNotIncrease, int filled, int unfilled, double averageFillPrice, OrderDuration duration, OrderStatus status, double limitPrice, double stopPrice, boolean stopTriggered, long modificationUtcTime)
```

**Deprecated:** Use builder instead

### OrderInfo
```java
@Deprecated
public OrderInfo(String instrumentAlias, String orderId, boolean isBuy, OrderType type, String clientId, boolean doNotIncrease, int filled, int unfilled, double averageFillPrice, OrderDuration duration, OrderStatus status, double limitPrice, double stopPrice, boolean stopTriggered, long modificationUtcTime, boolean isSimulated)
```

**Deprecated:** Use builder instead

### OrderInfo
```java
@Deprecated
public OrderInfo(String instrumentAlias, String orderId, boolean isBuy, OrderType type, String clientId, boolean doNotIncrease, int filled, int unfilled, double averageFillPrice, OrderDuration duration, OrderStatus status, double limitPrice, double stopPrice, boolean stopTriggered, long modificationUtcTime, boolean isSimulated, boolean isDuplicate)
```

**Deprecated:** Use builder instead

## Methods

### getTradingAccountId
```java
public String getTradingAccountId()
```

**Specified by:** `getTradingAccountId` in interface `MultiAccountAware`

---

**Methods inherited from class java.lang.Object:**
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