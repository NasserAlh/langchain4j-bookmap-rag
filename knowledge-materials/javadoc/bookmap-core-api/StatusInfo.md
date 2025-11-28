---
source_file: StatusInfo.html
package: velox.api.layer1.data
classes: StatusInfo
methods: instrumentAlias, unrealizedPnl, realizedPnl, currency, position, averagePrice, volume, workingBuys, workingSells, isDuplicate, accountId, StatusInfo, StatusInfo, toBuilder, getTradingAccountId, toString
---

# StatusInfo

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.data.StatusInfo

**All Implemented Interfaces:** MultiAccountAware

## Description

Instrument status information (PnL, number of open orders, position, etc)

## Fields

### instrumentAlias

```java
public final String instrumentAlias
```

Alias of the instrument this information relates to

### unrealizedPnl

```java
public final double unrealizedPnl
```

unrealized (open) PnL

### realizedPnl

```java
public final double realizedPnl
```

realized (closed) PnL

### currency

```java
public final String currency
```

instrument currency or null

### position

```java
public final int position
```

position

### averagePrice

```java
public final double averagePrice
```

if position != 0, then it contains average price at which position was opened

### volume

```java
public final int volume
```

number of shares bought/sold during the day

### workingBuys

```java
public final int workingBuys
```

number of working buy orders, shouldn't be negative, but won't cause crash

### workingSells

```java
public final int workingSells
```

number of working sell orders, shouldn't be negative, but won't cause crash

### isDuplicate

```java
public final boolean isDuplicate
```

If true, this is a duplicate of an already existing order. Such order is expected to appear with false in this field somewhere else, so it won't be displayed in account info (to avoid seeing same order twice)

### accountId

```java
public final String accountId
```

Account id to identify which account this execution info belongs to. Null if there is no multi-account support (only single account is supported by the data provider). Should not mix nulls and specific account infos in the same data provider.

## Constructors

### StatusInfo

```java
@Deprecated
public StatusInfo(String instrumentAlias, double unrealizedPnl, double realizedPnl, String currency, int position, double averagePrice, int volume, int workingBuys, int workingSells)
```

**Deprecated:** Use `StatusInfoBuilder` instead

### StatusInfo

```java
@Deprecated
public StatusInfo(String instrumentAlias, double unrealizedPnl, double realizedPnl, String currency, int position, double averagePrice, int volume, int workingBuys, int workingSells, boolean isDuplicate)
```

**Deprecated:** Use `StatusInfoBuilder` instead

## Methods

### toBuilder

```java
public StatusInfoBuilder toBuilder()
```

### getTradingAccountId

```java
public String getTradingAccountId()
```

**Specified by:** `getTradingAccountId` in interface `MultiAccountAware`

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`