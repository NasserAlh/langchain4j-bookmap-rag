---
source_file: OnlineCalculatable.html
package: velox.api.layer1.layers.strategies.interfaces
classes: OnlineCalculatable
methods: DataCoordinateMarker, Marker, ValueBundle, calculateValuesInRange, createOnlineValueCalculator, shouldRepeatCalculateValuesInRange, allowPartialUpdates
---

# OnlineCalculatable

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Interface

## Description

Strategy implementing this interfaces should support 2 types of requests:
1. Calculate value for given time points
2. Calculate value on fly when receiving new updates

Calculated value can be any object

Bottom panel will currently work with Double objects representing current value

## Nested Classes

### DataCoordinateMarker

```java
static interface DataCoordinateMarker
```

Marker bound to data coordinates.

### Marker

```java
static class Marker
```

If passed as value it will cause marker to be drawn at that place.

### ValueBundle

```java
static class ValueBundle
```

## Methods

### calculateValuesInRange

```java
void calculateValuesInRange(String indicatorName, String indicatorAlias, long t0, long intervalWidth, int intervalsNumber, CalculatedResultListener listener)
```

1st request type:
Should calculate left to right values in given time points
t0; t0 + intervalWidth; t0 + 2 * intervalWidth ... t0 + (intervalsNumber - 1) * intervalWidh)

**Parameters:**
- `indicatorName` - Name of indicator
- `indicatorAlias` - Alias of this indicator
- `t0` - Time of 1st value (in ns)
- `intervalWidth` - Width of one calculated interval (in ns)
- `intervalsNumber` - Number of values to calculate, >= 0
- `listener` - 

### createOnlineValueCalculator

```java
OnlineValueCalculatorAdapter createOnlineValueCalculator(String indicatorName, String indicatorAlias, long time, Consumer<Object> listener, InvalidateInterface invalidateInterface)
```

2nd request type:

**Parameters:**
- `indicatorName` - Name of indicator
- `indicatorAlias` - Alias of this indicator
- `time` - Starting with this time values are computed online
- `listener` - Can be used in following ways:
  - a) you can return generated events via listener (see `CalculatedResultListener.provideResponse(Object)` for supported object types
  - b) you can use `IndicatorFullValues` to fully update historical values of your indicator
- `invalidateInterface` - Can be used to invalidate indicator

**Returns:** Value calculator for this strategy

### shouldRepeatCalculateValuesInRange

```java
default boolean shouldRepeatCalculateValuesInRange(String indicatorName, String indicatorAlias, long intervalWidth, long timePassed)
```

**Experimental! Note that if you use this functionality, you should always use `OnlineValueCalculatorAdapter.onTimeSourceProvided(LongSupplier)` as your time source!**

By default Bookmap will repeat `calculateValuesInRange(String, String, long, long, int, CalculatedResultListener)` request if by the time response is handled more than one intervalWidth passed. However sometimes (if calculateValuesInRange is relatively slow and/or user is zoomed in closely, so chart moves fast) it can be repeated too many times (or even continiously, as long as chart moves). While it can be acceptable in some cases, you can improve the performance by telling bookmap to switch to `OnlineValueCalculatorAdapter` earlier.

You can return false when you believe there are not too many intervals and not too much time passed for your strategy to effectively process all data in that time interval. This will be called by Bookmap only if all data in requested range is ready (Bookmap will drop data for for which too many seconds and too many intervals passed)

**It's important that this method doesn't block for too long.**

**Parameters:**
- `indicatorName` - Name of indicator
- `indicatorAlias` - Alias of this indicator
- `intervalWidth` - Width of one calculated interval (in ns)
- `timePassed` - Time passed since the end of `calculateValuesInRange(String, String, long, long, int, CalculatedResultListener)` range

**Returns:** False to switch to `OnlineValueCalculatorAdapter`, true to use `calculateValuesInRange(String, String, long, long, int, CalculatedResultListener)` again

### allowPartialUpdates

```java
default boolean allowPartialUpdates(String indicatorName, String alias)
```

**Parameters:**
- `indicatorName` - Name of indicator
- `alias` - Alias of this indicator. Override you want your indicator to receive only full chart range requests (i.e. you don't want to receive 0-1000 pixels request, and then 1000-1020 request to catch up with chart)

**Returns:** **true** if you allow partial requests (default implementation), **false** otherwise