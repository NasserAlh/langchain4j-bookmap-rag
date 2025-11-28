---
source_file: BracketTier.html
package: velox.api.layer1.data
classes: BracketTier
methods: offset, size, clientId, BracketTier
---

# BracketTier

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.data.BracketTier

## Description

Describes a single tier of the bracket.

## Fields

### offset

```java
public final int offset
```

Similar to `SimpleOrderSendParameters.takeProfitOffset`, but for a single tier

### size

```java
public final int size
```

Size of the tier

### clientId

```java
public final String clientId
```

Client id for the corresponding order, similar too `SingleOrderSendParameters.clientId`

## Constructors

### BracketTier

```java
public BracketTier(int offset, int size)
```