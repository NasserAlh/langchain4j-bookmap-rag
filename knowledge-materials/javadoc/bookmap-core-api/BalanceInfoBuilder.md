---
source_file: BalanceInfoBuilder.html
package: velox.api.layer1.data
classes: BalanceInfoBuilder
methods: BalanceInfoBuilder, build, updateBalanceInCurrency, getAccountName, setAccountName, getAccountId, setAccountId, getBalancesInCurrency, setBalancesInCurrency
---

# BalanceInfoBuilder

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → BalanceInfoBuilder

## Description

Builder for `BalanceInfo` - see corresponding javadoc for information about the fields.

## Constructors

### BalanceInfoBuilder

```java
public BalanceInfoBuilder()
```

## Methods

### build

```java
public BalanceInfo build()
```

### updateBalanceInCurrency

```java
public BalanceInfoBuilder updateBalanceInCurrency(BalanceInfo.BalanceInCurrency balanceInCurrency)
```

### getAccountName

```java
@Deprecated
public String getAccountName()
```

**Deprecated:** Use `getAccountId()` instead

**Returns:** Account name (displayed account name)

### setAccountName

```java
@Deprecated
public BalanceInfoBuilder setAccountName(String accountName)
```

**Deprecated:** Use `setAccountId(String)` instead

### getAccountId

```java
public String getAccountId()
```

**Returns:** Account id of the account the balance is for. Null if there is no multi-account support (only single account is supported by the data provider).

### setAccountId

```java
public BalanceInfoBuilder setAccountId(String accountId)
```

Indicate which account the balance is for. If not set, there is no multi-account support (only single account is supported by the data provider).

**Important:** Don't set to null if there is multi-account support and data is coming from multiple accounts!

### getBalancesInCurrency

```java
public ArrayList<BalanceInfo.BalanceInCurrency> getBalancesInCurrency()
```

### setBalancesInCurrency

```java
public BalanceInfoBuilder setBalancesInCurrency(ArrayList<BalanceInfo.BalanceInCurrency> balancesInCurrency)
```