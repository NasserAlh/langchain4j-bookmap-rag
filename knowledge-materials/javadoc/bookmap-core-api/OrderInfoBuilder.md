---
source_file: OrderInfoBuilder.html
package: velox.api.layer1.data
classes: OrderInfoBuilder
methods: OrderInfoBuilder, getInstrumentAlias, setInstrumentAlias, getOrderId, setOrderId, isBuy, setBuy, getType, setType, getClientId, setClientId, getExchangeId, setExchangeId, isDoNotIncrease, setDoNotIncrease, getFilled, setFilled, getUnfilled, setUnfilled, getAverageFillPrice
total_methods: 42
---

# OrderInfoBuilder

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → OrderInfoBuilder

## Description

Modifiable version of OrderInfo/OrderInfoUpdate. This one can be edited and then transformed into OrderInfoUpdate.

Note, that while correctly marking changes (`markAllChanged()`, `markAllUnchanged()`) does not do anything in current version, it's still advised to do it, since it's pretty simple and might be used for something later.

## Constructors

### OrderInfoBuilder

```java
public OrderInfoBuilder(String instrumentAlias, String orderId, boolean isBuy, OrderType type, String clientId, boolean doNotIncrease)
```

## Methods

### getInstrumentAlias

```java
String getInstrumentAlias()
```

### setInstrumentAlias

```java
OrderInfoBuilder setInstrumentAlias(String instrumentAlias)
```

### getOrderId

```java
String getOrderId()
```

### setOrderId

```java
OrderInfoBuilder setOrderId(String orderId)
```

### isBuy

```java
boolean isBuy()
```

### setBuy

```java
OrderInfoBuilder setBuy(boolean isBuy)
```

### getType

```java
OrderType getType()
```

### setType

```java
OrderInfoBuilder setType(OrderType type)
```

### getClientId

```java
String getClientId()
```

### setClientId

```java
OrderInfoBuilder setClientId(String clientId)
```

### getExchangeId

```java
String getExchangeId()
```

### setExchangeId

```java
OrderInfoBuilder setExchangeId(String exchangeId)
```

### isDoNotIncrease

```java
boolean isDoNotIncrease()
```

### setDoNotIncrease

```java
OrderInfoBuilder setDoNotIncrease(boolean doNotIncrease)
```

### getFilled

```java
int getFilled()
```

### setFilled

```java
OrderInfoBuilder setFilled(int filled)
```

### getUnfilled

```java
int getUnfilled()
```

### setUnfilled

```java
OrderInfoBuilder setUnfilled(int unfilled)
```

### getAverageFillPrice

```java
double getAverageFillPrice()
```

### setAverageFillPrice

```java
OrderInfoBuilder setAverageFillPrice(double averageFillPrice)
```

### getDuration

```java
OrderDuration getDuration()
```

### setDuration

```java
OrderInfoBuilder setDuration(OrderDuration duration)
```

### getStatus

```java
OrderStatus getStatus()
```

### setStatus

```java
OrderInfoBuilder setStatus(OrderStatus status)
```

### getLimitPrice

```java
double getLimitPrice()
```

### setLimitPrice

```java
OrderInfoBuilder setLimitPrice(double limitPrice)
```

### getStopPrice

```java
double getStopPrice()
```

### setStopPrice

```java
OrderInfoBuilder setStopPrice(double stopPrice)
```

### isStopTriggered

```java
boolean isStopTriggered()
```

### setStopTriggered

```java
OrderInfoBuilder setStopTriggered(boolean isStopTriggered)
```

### getModificationUtcTime

```java
long getModificationUtcTime()
```

### setModificationUtcTime

```java
OrderInfoBuilder setModificationUtcTime(long modificationUtcTime)
```

### isSimulated

```java
boolean isSimulated()
```

### setSimulated

```java
OrderInfoBuilder setSimulated(boolean isSimulated)
```

### isDuplicate

```java
boolean isDuplicate()
```

### setDuplicate

```java
OrderInfoBuilder setDuplicate(boolean isDuplicate)
```

### getAccountId

```java
String getAccountId()
```

### setAccountId

```java
OrderInfoBuilder setAccountId(String accountId)
```

### markAllChanged

```java
OrderInfoBuilder markAllChanged()
```

### markAllUnchanged

```java
OrderInfoBuilder markAllUnchanged()
```

### build

```java
OrderInfoUpdate build()
```