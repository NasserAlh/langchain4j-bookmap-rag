---
source_file: SubscribeInfo.html
package: velox.api.layer1.data
classes: SubscribeInfo
methods: symbol, exchange, type, SubscribeInfo, hashCode, equals, toString
---

# SubscribeInfo

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → SubscribeInfo

**Direct Known Subclasses:** LookupSubscribeInfo, SubscribeInfoCrypto

## Description

Describes subscription request

## Fields

### symbol

```java
public final String symbol
```

### exchange

```java
public final String exchange
```

### type

```java
public final String type
```

see `InstrumentCoreInfo.type`

## Constructors

### SubscribeInfo

```java
public SubscribeInfo(String symbol, String exchange, String type)
```

## Methods

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

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`

---

**Methods inherited from class java.lang.Object:** `clone`, `finalize`, `getClass`, `notify`, `notifyAll`, `wait`, `wait`, `wait`