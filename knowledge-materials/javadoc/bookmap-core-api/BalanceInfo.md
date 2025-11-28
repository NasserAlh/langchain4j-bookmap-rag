---
source_file: BalanceInfo.html
package: velox.api.layer1.data
classes: BalanceInfo
methods: BalanceInfo.BalanceInCurrency, accountName, accountId, balancesInCurrency, BalanceInfo, BalanceInfo, getTradingAccountId, toString
---

# BalanceInfo

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → BalanceInfo

**All Implemented Interfaces:** MultiAccountAware

## Description

Information about account balance in each currency

## Nested Classes

### BalanceInfo.BalanceInCurrency

Information about account balance in specific currency

## Fields

### accountName

```java
@Deprecated
public final String accountName
```

**Deprecated:** because displayed name and account id should be set with `AccountInfo`. Use `accountId` instead.

Short name of an account, if known (displayed name)

### accountId

```java
public final String accountId
```

Account id to identify which account this balance info belongs to.
Null if there is no multi-account support (only single account is supported by the data provider).
Should not mix nulls and specific account infos in the same data provider.

### balancesInCurrency

```java
public final List<BalanceInfo.BalanceInCurrency> balancesInCurrency
```

List of balances (in each currency)

## Constructors

### BalanceInfo

```java
@Deprecated
public BalanceInfo(String accountName, List<BalanceInfo.BalanceInCurrency> balancesInCurrency)
```

**Deprecated:** because `AccountInfo` should be used instead of `accountName`. Use `BalanceInfo(List, String)` instead.

Avoid using it directly, use `BalanceInfoBuilder` instead

### BalanceInfo

```java
public BalanceInfo(List<BalanceInfo.BalanceInCurrency> balancesInCurrency)
```

Avoid using it directly, use `BalanceInfoBuilder` instead

## Methods

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

---

**Methods inherited from class java.lang.Object:** `clone`, `equals`, `finalize`, `getClass`, `hashCode`, `notify`, `notifyAll`, `wait`, `wait`, `wait`