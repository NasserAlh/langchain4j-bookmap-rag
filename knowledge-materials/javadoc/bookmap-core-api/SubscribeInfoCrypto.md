---
source_file: SubscribeInfoCrypto.html
package: velox.api.layer1.data
classes: SubscribeInfoCrypto
methods: pips, priceMultiplier, sizeMultiplier, SubscribeInfoCrypto
---

# SubscribeInfoCrypto

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.data.SubscribeInfo → SubscribeInfoCrypto

## Description

Describes subscription request with extra parameters typical for cryptocurrencies.

## Fields

### pips

```java
public final double pips
```

Some cryptocurrencies might have pips (min tick) too low to be useful in bookmap. This allows to request resampling of the data.

### priceMultiplier

```java
@Deprecated
public final double priceMultiplier
```

**Deprecated.** Use `sizeMultiplier` instead. This field is a result of a typo, kept for compatibility reasons.

### sizeMultiplier

```java
public final double sizeMultiplier
```

Some cryptocurrencies have non-integer sizes. See `InstrumentInfo.sizeMultiplier`.

**Inherited Fields from SubscribeInfo:**
- `exchange`
- `symbol` 
- `type`

## Constructors

### SubscribeInfoCrypto

```java
public SubscribeInfoCrypto(String symbol, String exchange, String type, double pips, double sizeMultiplier)
```

## Methods

**Methods inherited from SubscribeInfo:**
- `equals(Object)`
- `hashCode()`
- `toString()`

**Methods inherited from Object:**
- `clone()`
- `finalize()`
- `getClass()`
- `notify()`
- `notifyAll()`
- `wait()`
- `wait(long)`
- `wait(long, int)`