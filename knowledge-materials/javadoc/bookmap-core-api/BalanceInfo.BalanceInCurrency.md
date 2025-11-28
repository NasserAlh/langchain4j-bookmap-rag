---
source_file: BalanceInfo.BalanceInCurrency.html
package: velox.api.layer1.data
classes: BalanceInfo.BalanceInCurrency
methods: balance, realizedPnl, unrealizedPnl, previousDayBalance, netLiquidityValue, currency, rateToBase, BalanceInCurrency, toString
---

# BalanceInfo.BalanceInCurrency

**Package:** velox.api.layer1.data

**Type:** Class (static nested)

**Enclosing Class:** [`BalanceInfo`](BalanceInfo.html)

**Inheritance:** `java.lang.Object` → `velox.api.layer1.data.BalanceInfo.BalanceInCurrency`

## Description

Information about account balance in specific currency

## Fields

### balance

```java
public final double balance
```

### realizedPnl

```java
public final double realizedPnl
```

### unrealizedPnl

```java
public final double unrealizedPnl
```

### previousDayBalance

```java
public final double previousDayBalance
```

### netLiquidityValue

```java
public final double netLiquidityValue
```

### currency

```java
public final String currency
```

Currency name

### rateToBase

```java
public final Double rateToBase
```

Conversion rate to account base currency. Null if this is a base currency.

## Constructors

### BalanceInCurrency

```java
public BalanceInCurrency(double balance, double realizedPnl, double unrealizedPnl, double previousDayBalance, double netLiquidityValue, String currency, Double rateToBase)
```

## Methods

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`

**Methods inherited from class `java.lang.Object`:**
- `clone`
- `equals`
- `finalize`
- `getClass`
- `hashCode`
- `notify`
- `notifyAll`
- `wait`
- `wait(long)`
- `wait(long, int)`