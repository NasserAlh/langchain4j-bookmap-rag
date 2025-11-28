---
source_file: CalculatedResultListener.html
package: velox.api.layer1.layers.strategies.interfaces
classes: CalculatedResultListener
methods: isCancelled, provideResponse, setCompleted
---

# CalculatedResultListener

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Interface

## Description

Interface used to provide calculated values to bottom chart model.

## Methods

### isCancelled

```java
boolean isCancelled()
```

It's recommended to poll this once in a while to avoid unnecessary computations. If it's set to true then you can abort computation (and call `setCompleted()` to acknowledge).

**Returns:** `true` if this request is no longer valid, `false` otherwise

### provideResponse

```java
void provideResponse(Object value)
```

It is expected that number of provided responses is equal to number of requested intervals. Only after all responses are provided request is considered completed. Responses should be provided in chronological order.

**Parameters:**
- `value` - Representation of this pixel state (currently accepts `Double`, `OnlineCalculatable.Marker`, List<`OnlineCalculatable.Marker`> or `OnlineCalculatable.ValueBundle`)

### setCompleted

```java
void setCompleted()
```

You are required to make this call when you either completed and provided all calculations or aborted calculation. No requests will be made until setCompleted is called.