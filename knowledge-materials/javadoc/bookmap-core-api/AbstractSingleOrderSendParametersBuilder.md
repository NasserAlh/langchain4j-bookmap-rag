---
source_file: AbstractSingleOrderSendParametersBuilder.html
package: velox.api.layer1.data
classes: AbstractSingleOrderSendParametersBuilder<T extends AbstractSingleOrderSendParametersBuilder<T>>
methods: alias, isBuy, size, duration, clientId, accountId, AbstractSingleOrderSendParametersBuilder, AbstractSingleOrderSendParametersBuilder, AbstractSingleOrderSendParametersBuilder, build, getAlias, setAlias, isBuy, setBuy, getSize, setSize, getDuration, setDuration, getClientId, setClientId
total_methods: 23
---

# AbstractSingleOrderSendParametersBuilder<T extends AbstractSingleOrderSendParametersBuilder<T>>

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → AbstractSingleOrderSendParametersBuilder<T>

**Direct Known Subclasses:** AbstractSimpleOrderSendParametersBuilder

## Fields

### alias

```java
protected String alias
```

### isBuy

```java
protected boolean isBuy
```

### size

```java
protected int size
```

### duration

```java
protected OrderDuration duration
```

### clientId

```java
protected String clientId
```

### accountId

```java
protected String accountId
```

## Constructors

### AbstractSingleOrderSendParametersBuilder

```java
protected AbstractSingleOrderSendParametersBuilder()
```

### AbstractSingleOrderSendParametersBuilder

```java
protected AbstractSingleOrderSendParametersBuilder(String alias, boolean isBuy, int size, OrderDuration duration, String clientId, String accountId)
```

### AbstractSingleOrderSendParametersBuilder

```java
protected AbstractSingleOrderSendParametersBuilder(String alias, boolean isBuy, int size, OrderDuration duration, String clientId)
```

## Methods

### build

```java
public SingleOrderSendParameters build()
```

### getAlias

```java
public String getAlias()
```

### setAlias

```java
public T setAlias(String alias)
```

### isBuy

```java
public boolean isBuy()
```

### setBuy

```java
public T setBuy(boolean isBuy)
```

### getSize

```java
public int getSize()
```

### setSize

```java
public T setSize(int size)
```

### getDuration

```java
public OrderDuration getDuration()
```

### setDuration

```java
public T setDuration(OrderDuration duration)
```

### getClientId

```java
public String getClientId()
```

### setClientId

```java
public T setClientId(String clientId)
```

### getAccountId

```java
public String getAccountId()
```

### setAccountId

```java
public T setAccountId(String accountId)
```

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`