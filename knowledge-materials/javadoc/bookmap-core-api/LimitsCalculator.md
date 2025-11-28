---
source_file: LimitsCalculator.html
package: velox.api.layer1.messages.indicators
classes: LimitsCalculator
methods: getLimits
---

# LimitsCalculator

**Package:** velox.api.layer1.messages.indicators

**Type:** Interface

## Description

Custom logic for calculating display limits for indicator that has given minimum and maximum values in displayed range.

**Note:** this logic will be only partially work in conjunctions with `Layer1ApiUserMessageModifyIndicator.indicatorMinMarginPriceOut` and `Layer1ApiUserMessageModifyIndicator.indicatorMaxMarginPriceOut`. Returned limits will be readjusted to meet requirement from this values.

## Methods

### getLimits

```java
LimitsResponse getLimits(double minValue, double maxValue)
```

Calculated desired display limits for indicator that has given minimum and maximum values in displayed range.

minValue and maxValue could be Double.NaN if no data is present for this indicator.

**Note:** indicator can continuously readjust between two adjacent ranges, so it's not recommended for this function to return a significantly different result for adjacent ranges (i.e for (0, 10) will return (-5, 15) and at the same time for (0, 11) will return (0, 11)) as it can lead to the indicator picture "jumping" (a few significant changes of indicator ranges per second, hard to watch data normally because of it)

**Parameters:**
- `minValue` - 
- `maxValue` - 

**Returns:** limits calculated limits for such indicator's min/max values