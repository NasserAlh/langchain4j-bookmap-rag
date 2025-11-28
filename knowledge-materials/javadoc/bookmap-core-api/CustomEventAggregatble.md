---
source_file: CustomEventAggregatble.html
package: velox.api.layer1.layers.strategies.interfaces
classes: CustomEventAggregatble
methods: getInitialValue, aggregateAggregationWithAggregation, aggregateAggregationWithValue
---

# CustomEventAggregatble

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Interface

## Description

Describes rules of aggregation of custom events

## Methods

### getInitialValue

```java
CustomGeneratedEvent getInitialValue(long t)
```

**Returns:** Event representing aggregation of 0 objects with time t, of type described in `GeneratedEventInfo.aggregationClass`

### aggregateAggregationWithAggregation

```java
void aggregateAggregationWithAggregation(CustomGeneratedEvent aggregation1, CustomGeneratedEvent aggregation2)
```

Aggregates aggregation1 with aggregation 2, modifying aggregation1

**Parameters:**
- `aggregation1` - Of type described in `GeneratedEventInfo.aggregationClass`
- `aggregation2` - Of type described in `GeneratedEventInfo.aggregationClass`

### aggregateAggregationWithValue

```java
void aggregateAggregationWithValue(CustomGeneratedEvent aggregation, CustomGeneratedEvent value)
```

Aggregates aggregation with value, modifying aggregation

**Parameters:**
- `aggregation` - Of type described in `GeneratedEventInfo.aggregationClass`
- `value` - Of type described in `GeneratedEventInfo.valueClass`