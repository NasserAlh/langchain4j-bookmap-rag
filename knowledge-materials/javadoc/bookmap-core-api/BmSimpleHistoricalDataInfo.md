---
source_file: BmSimpleHistoricalDataInfo.html
package: velox.api.layer1.data
classes: BmSimpleHistoricalDataInfo
methods: serverUrl, instrumentToImportDuration, BmSimpleHistoricalDataInfo, BmSimpleHistoricalDataInfo
---

# BmSimpleHistoricalDataInfo

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → BmSimpleHistoricalDataInfo

**All Implemented Interfaces:** HistoricalDataInfo

**Annotations:** @DefaultQualifier(org.checkerframework.checker.nullness.qual.NonNull.class)

## Description

Describes parameters for data retrieval from Bookmap simple historical server.

## Fields

### serverUrl

```java
public final String serverUrl
```

URL of historical server

### instrumentToImportDuration

```java
public final @Nullable Function<SubscribeInfo, Long> instrumentToImportDuration
```

A mapping from `SubscribeInfo` to the `Long`.

Used for selecting default or the closest available historical import duration value in subscription dialog.

Returns default historical import duration in nanoseconds.

## Constructors

### BmSimpleHistoricalDataInfo

```java
public BmSimpleHistoricalDataInfo(String serverUrl)
```

### BmSimpleHistoricalDataInfo

```java
public BmSimpleHistoricalDataInfo(String serverUrl, @Nullable Function<SubscribeInfo, Long> instrumentToImportDuration)
```