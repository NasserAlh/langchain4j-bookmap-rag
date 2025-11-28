---
source_file: Layer1ApiDepthFreezer.DepthFreezeRequest.html
package: velox.api.layer1.layers
classes: Layer1ApiDepthFreezer.DepthFreezeRequest
methods: alias, freeze, freezeEndTime, DepthFreezeRequest, DepthFreezeRequest
---

# Layer1ApiDepthFreezer.DepthFreezeRequest

**Package:** velox.api.layer1.layers

**Type:** Class

**Enclosing class:** [`Layer1ApiDepthFreezer`](Layer1ApiDepthFreezer.html)

**Inheritance:** java.lang.Object → Layer1ApiDepthFreezer.DepthFreezeRequest

## Fields

### alias

```java
public final String alias
```

### freeze

```java
public final boolean freeze
```

### freezeEndTime

```java
public final long freezeEndTime
```

If freeze was not removed by this time - remove automatically on next event

## Constructors

### DepthFreezeRequest

```java
public DepthFreezeRequest(String alias, boolean freeze, long freezeEndTime)
```

### DepthFreezeRequest

```java
public DepthFreezeRequest(String alias, boolean freeze)
```