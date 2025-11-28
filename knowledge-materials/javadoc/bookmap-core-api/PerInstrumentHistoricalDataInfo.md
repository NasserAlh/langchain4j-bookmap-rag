---
source_file: PerInstrumentHistoricalDataInfo.html
package: velox.api.layer1.data
classes: PerInstrumentHistoricalDataInfo
methods: instrumentToHistoricalDataInfo, PerInstrumentHistoricalDataInfo
---

# PerInstrumentHistoricalDataInfo

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → PerInstrumentHistoricalDataInfo

**All Implemented Interfaces:** HistoricalDataInfo

## Description

Allows historical data info to be different for each instrument

## Fields

### instrumentToHistoricalDataInfo

```java
public final Function<SubscribeInfo, HistoricalDataInfo> instrumentToHistoricalDataInfo
```

A mapping from `SubscribeInfo` to the `HistoricalDataInfo`. Currently multiple levels of `PerInstrumentHistoricalDataInfo` are not supported.

## Constructors

### PerInstrumentHistoricalDataInfo

```java
public PerInstrumentHistoricalDataInfo(Function<SubscribeInfo, HistoricalDataInfo> instrumentToHistoricalDataInfo)
```