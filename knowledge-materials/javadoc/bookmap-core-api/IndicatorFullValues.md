---
source_file: IndicatorFullValues.html
package: velox.api.layer1.layers.strategies.interfaces
classes: IndicatorFullValues
methods: getValues
---

# IndicatorFullValues

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Interface

## Description

Clears all previous returned values, and provides new data, equal to response for new `OnlineCalculatable.calculateValuesInRange(String, String, long, long, int, CalculatedResultListener)` without actually calling `InvalidateInterface.invalidate()`.

Indicator line will be painted completely with new values.

## Methods

### getValues

```java
List<? extends Object> getValues()
```

**Returns:** Values list of result values. Size should not exceed to pixelWidth obtained from `OnlineValueCalculatorAdapter.onIntervalsNumber(int)` method.

**Note that neither returned list nor any of it's elements should never be modified from strategy**