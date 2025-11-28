---
source_file: InvalidateInterface.html
package: velox.api.layer1.layers.strategies.interfaces
classes: InvalidateInterface
methods: invalidate, invalidateSynchronous
---

# InvalidateInterface

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Interface

## Description

Interface used to invalidate online indicator at any time.

## Methods

### invalidate

```java
void invalidate()
```

Invalidate indicator  
Indicator line will be painted on fly as data is calculated via next `OnlineCalculatable.calculateValuesInRange(String, String, long, long, int, CalculatedResultListener)`

### invalidateSynchronous

```java
@Deprecated
void invalidateSynchronous()
```

**Deprecated.**

Invalidate indicator  
Indicator line will be painted completely **only** after **all** data is calculated via next `OnlineCalculatable.calculateValuesInRange(String, String, long, long, int, CalculatedResultListener)`  
Usage is discouraged if calculation is heavy and may take long time to complete.