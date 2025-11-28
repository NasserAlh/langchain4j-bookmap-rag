---
source_file: Layer1PriceAxisRangeCalculatable.html
package: velox.api.layer1.layers.strategies.interfaces
classes: Layer1PriceAxisRangeCalculatable
methods: Layer1PriceAxisRangeCalculatable.InputPriceAxisInfo, Layer1PriceAxisRangeCalculatable.ResultPriceAxisInfo, getPriceRanges
---

# Layer1PriceAxisRangeCalculatable

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Interface

## Description

Implement this interface if you want to manually calculate price axis ranges for your indicators.

## Nested Classes

### Layer1PriceAxisRangeCalculatable.InputPriceAxisInfo

Range of values for this indicator [minValue, maxValue]

### Layer1PriceAxisRangeCalculatable.ResultPriceAxisInfo

## Methods

### getPriceRanges

```java
Map<String, Layer1PriceAxisRangeCalculatable.ResultPriceAxisInfo> getPriceRanges(String alias, double linesCount, Map<String, Layer1PriceAxisRangeCalculatable.InputPriceAxisInfo> inputInfo)
```

You can use PriceRangeCalculationHelper for existing implementations.

**Parameters:**
- `alias` - Instrument alias
- `linesCount` - Number of visible horizontal lines, that can display labels. Note that this value is double. Value 2.3 will means 2 visible lines, and 3/10 of distance between lines as empty space **to the top**
- `inputInfo` - Map, containing all indicators for this strategy and this alias (having determined limits. If there was no data for this indicator, it won't be passed here), and their value range

**Returns:** Map, where key set should be equal to inputInfo key set, and value represents desired range and labels for this indicator