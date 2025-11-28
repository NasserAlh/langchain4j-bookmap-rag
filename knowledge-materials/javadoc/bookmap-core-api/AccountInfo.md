---
source_file: AccountInfo.html
package: velox.api.layer1.data
classes: AccountInfo
methods: id, summary, providerProgrammaticName, isPrimary, AccountInfo, toBuilder, toString, equals, hashCode
---

# AccountInfo

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → AccountInfo

## Description

Information about account

## Fields

### id

```java
public final String id
```

Unique identifier for the account. Must uniquely identify the account among all the providers (so provider should form the id to contain provider-specific prefix or suffix).

### summary

```java
public final String summary
```

Summary string, containing account name. Is used as a display name for the account.

### providerProgrammaticName

```java
public final String providerProgrammaticName
```

Programmatic name (unique internal provider identifier) of the provider this account belongs to. Unique among all the providers. Use `ProviderTypeHelper` to get to short name or user name by provider programmatic name (e.g. EXT:velox.api.layer0.adapters.BinanceProvider → "Binance"/"BN")

Note for the provider developer: this field is set by the Bookmap itself, and should not be set by the provider. (if it is set by the provider, it will be overwritten by the Bookmap anyway)

### isPrimary

```java
public final boolean isPrimary
```

If true used as a default account, when the strategy is not aware of multi-account data providers. At least one account should be marked as primary, to be able to retrieve any data from the data provider. Data for non-primary accounts is send to the strategy only if it has `Layer1MultiAccountTradingSupported` annotation, or if such data has no account specified (i.e. AccountInfo is null). Usually primary account is the account selected with `SelectAccountMessage` - decided by data provider for strategies that not aware of multi-accounts.

## Constructors

### AccountInfo

```java
@Deprecated
public AccountInfo(String id)
```

**Deprecated:** use `AccountInfo(String, String, String, boolean)` instead. Deprecated because id is supposed to be unique and should not be used as a summary (displayed name).

**Parameters:**
- `id` - Account id (unique identifier for the account). Used both for id and summary.

## Methods

### toBuilder

```java
public AccountInfoBuilder toBuilder()
```

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`

### equals

```java
public boolean equals(Object o)
```

**Overrides:** `equals` in class `Object`

### hashCode

```java
public int hashCode()
```

**Overrides:** `hashCode` in class `Object`