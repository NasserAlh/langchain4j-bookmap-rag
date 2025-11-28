---
source_file: OrderInfoUpdate.html
package: velox.api.layer1.data
classes: OrderInfoUpdate
methods: filledChanged, unfilledChanged, averageFillPriceChanged, durationChanged, statusChanged, limitPriceChanged, stopPriceChanged, stopTriggeredChanged, modificationTimeChanged, OrderInfoUpdate, OrderInfoUpdate, OrderInfoUpdate, OrderInfoUpdate, toString, toBuilder
---

# OrderInfoUpdate

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.data.OrderInfo → OrderInfoUpdate

**All Implemented Interfaces:** Serializable, MultiAccountAware

## Description

Contains cumulative information about the order + boolean fields, that mark what has changed since the last time it was reported.

**See Also:**
- Serialized Form

---

## Fields

### filledChanged
```java
public final boolean filledChanged
```

### unfilledChanged
```java
public final boolean unfilledChanged
```

### averageFillPriceChanged
```java
public final boolean averageFillPriceChanged
```

### durationChanged
```java
public final boolean durationChanged
```

### statusChanged
```java
public final boolean statusChanged
```

### limitPriceChanged
```java
public final boolean limitPriceChanged
```

### stopPriceChanged
```java
public final boolean stopPriceChanged
```

### stopTriggeredChanged
```java
public final boolean stopTriggeredChanged
```

### modificationTimeChanged
```java
public final boolean modificationTimeChanged
```

**Fields inherited from class velox.api.layer1.data.OrderInfo:**
- `accountId`, `averageFillPrice`, `clientId`, `doNotIncrease`, `duration`, `exchangeId`, `filled`, `instrumentAlias`, `isBuy`, `isDuplicate`, `isSimulated`, `limitPrice`, `modificationUtcTime`, `orderId`, `status`, `stopPrice`, `stopTriggered`, `type`, `unfilled`

---

## Constructors

### OrderInfoUpdate
```java
@Deprecated
public OrderInfoUpdate(String instrumentAlias, String orderId, boolean isBuy, OrderType type, String clientId, boolean doNotIncrease, int filled, boolean filledChanged, int unfilled, boolean unfilledChanged, double averageFillPrice, boolean averageFillPriceChanged, OrderDuration duration, boolean durationChanged, OrderStatus status, boolean statusChanged, double limitPrice, boolean limitPriceChanged, double stopPrice, boolean stopPriceChanged, boolean stopTriggered, boolean stopTriggeredChanged, long modificationUtcTime, boolean modificationTimeChanged, boolean isSimulated, boolean isDuplicate)
```

**Deprecated:** Use builder instead

### OrderInfoUpdate
```java
@Deprecated
public OrderInfoUpdate(String instrumentAlias, String orderId, boolean isBuy, OrderType type, String clientId, boolean doNotIncrease, int filled, boolean filledChanged, int unfilled, boolean unfilledChanged, double averageFillPrice, boolean averageFillPriceChanged, OrderDuration duration, boolean durationChanged, OrderStatus status, boolean statusChanged, double limitPrice, boolean limitPriceChanged, double stopPrice, boolean stopPriceChanged, boolean stopTriggered, boolean stopTriggeredChanged, long modificationUtcTime, boolean modificationTimeChanged, boolean isSimulated)
```

**Deprecated:** Use builder instead

### OrderInfoUpdate
```java
@Deprecated
public OrderInfoUpdate(String instrumentAlias, String orderId, boolean isBuy, OrderType type, String clientId, boolean doNotIncrease, int filled, boolean filledChanged, int unfilled, boolean unfilledChanged, double averageFillPrice, boolean averageFillPriceChanged, OrderDuration duration, boolean durationChanged, OrderStatus status, boolean statusChanged, double limitPrice, boolean limitPriceChanged, double stopPrice, boolean stopPriceChanged, boolean stopTriggered, boolean stopTriggeredChanged, long modificationTime, boolean modificationTimeChanged)
```

**Deprecated:** Use builder instead

### OrderInfoUpdate
```java
public OrderInfoUpdate(OrderInfo orderInfo)
```

---

## Methods

### toString
```java
public String toString()
```

**Overrides:** `toString` in class `Object`

### toBuilder
```java
public OrderInfoBuilder toBuilder()
```

**Methods inherited from class velox.api.layer1.data.OrderInfo:**
- `getTradingAccountId()`

**Methods inherited from class java.lang.Object:**
- `clone()`, `equals(Object)`, `finalize()`, `getClass()`, `hashCode()`, `notify()`, `notifyAll()`, `wait()`, `wait(long)`, `wait(long, int)`