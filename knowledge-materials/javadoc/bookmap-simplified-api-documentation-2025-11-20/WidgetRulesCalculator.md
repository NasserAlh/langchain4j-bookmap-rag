---
source_file: WidgetRulesCalculator.html
package: velox.api.layer1.simplified
classes: WidgetRulesCalculator
methods: WidgetRulesCalculator, longToTransactTime, getIndex, packExtreme, getTemporalExtreme, processPoint, initializeOrUpdateLower, initializeOrUpdateUpper, onBasicImplementationWidgetLifeSpanChanges, getActualWidgetRange, getTemporalMinMax, updateRangeToBeReported, reportRange, adjustGroupRange, isCalculationOrReportingAllowed
---

# WidgetRulesCalculator

**Package:** velox.api.layer1.simplified

**Type:** Class

**Inheritance:** java.lang.Object → WidgetRulesCalculator

## Constructors

### WidgetRulesCalculator

```java
public WidgetRulesCalculator()
```

## Methods

### longToTransactTime

```java
public static String longToTransactTime(long moment)
```

### getIndex

```java
public static int getIndex(List<org.apache.commons.lang3.tuple.ImmutablePair<Long, Double>> list, long timestamp)
```

### packExtreme

```java
public static void packExtreme(double extreme, List<org.apache.commons.lang3.tuple.ImmutablePair<Long, Double>> result, boolean isMaximum, long timestamp)
```

### getTemporalExtreme

```java
public static double getTemporalExtreme(List<org.apache.commons.lang3.tuple.ImmutablePair<Long, Double>> extremes, boolean isMax, long timestamp, long widgetRangeLifeSpan)
```

### processPoint

```java
public static org.apache.commons.lang3.tuple.ImmutableTriple<Double, Double, Long> processPoint(double value, long time, long nextTime, double lower, double upper, WidgetRules widgetRules, List<org.apache.commons.lang3.tuple.ImmutablePair<Long, org.apache.commons.lang3.tuple.ImmutablePair<Double, Double>>> sampledWidgetRanges, List<org.apache.commons.lang3.tuple.ImmutablePair<Long, org.apache.commons.lang3.tuple.ImmutablePair<Double, Double>>> spannedWidgetRanges)
```

### initializeOrUpdateLower

```java
public static double initializeOrUpdateLower(double lower, double value)
```

### initializeOrUpdateUpper

```java
public static double initializeOrUpdateUpper(double upper, double value)
```

### onBasicImplementationWidgetLifeSpanChanges

```java
public static List<Object> onBasicImplementationWidgetLifeSpanChanges(WidgetRules widgetRules, List<org.apache.commons.lang3.tuple.Pair<Long, velox.api.layer1.simplified.Point>> points)
```

### getActualWidgetRange

```java
public void getActualWidgetRange()
```

### getTemporalMinMax

```java
public static org.apache.commons.lang3.tuple.ImmutablePair<Double, Double> getTemporalMinMax(long leftTime, double lower, double upper, List<org.apache.commons.lang3.tuple.ImmutablePair<Long, org.apache.commons.lang3.tuple.ImmutablePair<Double, Double>>> spannedWidgetRanges)
```

### updateRangeToBeReported

```java
public static org.apache.commons.lang3.tuple.ImmutablePair<Double, Double> updateRangeToBeReported(org.apache.commons.lang3.tuple.ImmutablePair<Double, Double> actualRange, double latestReportedLower, double latestReportedUpper)
```

### reportRange

```java
public static org.apache.commons.lang3.tuple.ImmutablePair<Double, Double> reportRange(velox.api.layer1.layers.strategies.interfaces.WidgetRangeConsumer consumer, double latestReportedLower, double latestReportedUpper)
```

### adjustGroupRange

```java
public static org.apache.commons.lang3.tuple.ImmutablePair<Double, Double> adjustGroupRange(WidgetGroup widgetGroup)
```

### isCalculationOrReportingAllowed

```java
public static boolean isCalculationOrReportingAllowed(velox.api.layer1.layers.strategies.interfaces.WidgetRangeConsumer consumer, WidgetRules widgetRules, WidgetGroup widgetGroup)
```