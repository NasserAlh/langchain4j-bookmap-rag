---
source_file: SingleOrderSendParameters.html
package: velox.api.layer1.data
classes: SingleOrderSendParameters
methods: alias, isBuy, size, duration, clientId, accountId, SingleOrderSendParameters, SingleOrderSendParameters, getTradingAccountId, toString
---

# SingleOrderSendParameters

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.data.SingleOrderSendParameters

**All Implemented Interfaces:** MultiAccountAware, OrderSendParameters

**Direct Known Subclasses:** SimpleOrderSendParameters

## Description

Base class for sending a single order

## Fields

### alias

```java
public final String alias
```

Alias of the instrument

### isBuy

```java
public final boolean isBuy
```

true for buy order, false for sell order

### size

```java
public final int size
```

Order size

### duration

```java
public final OrderDuration duration
```

### clientId

```java
public final String clientId
```

Allows to match order submission request to a response.

Randomly generated id 24 characters long - this allows to fit it into most tag fields

### accountId

```java
public final String accountId
```

Account id to identify which account this order parameters belongs to. Should be null if there is no multi-account support from the provider side. Account id should be specified if there is multi-account support and the order should be sent to a specific account. If account id is set to null, but provider supports multi-account, the order will be sent to the primary account.

## Constructors

### SingleOrderSendParameters

```java
@Deprecated
protected SingleOrderSendParameters(String alias, boolean isBuy, int size, OrderDuration duration, String clientId)
```

**Deprecated:** Use `AbstractSingleOrderSendParametersBuilder` instead

### SingleOrderSendParameters

```java
@Deprecated
protected SingleOrderSendParameters(String alias, boolean isBuy, int size, OrderDuration duration)
```

**Deprecated:** Use `AbstractSingleOrderSendParametersBuilder` instead

## Methods

### getTradingAccountId

```java
public String getTradingAccountId()
```

**Specified by:** `getTradingAccountId` in interface `MultiAccountAware`

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`

---

**Methods inherited from class java.lang.Object:** `clone`, `equals`, `finalize`, `getClass`, `hashCode`, `notify`, `notifyAll`, `wait`, `wait`, `wait`