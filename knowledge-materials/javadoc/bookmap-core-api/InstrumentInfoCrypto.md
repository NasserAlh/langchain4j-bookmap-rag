---
source_file: InstrumentInfoCrypto.html
package: velox.api.layer1.data
classes: InstrumentInfoCrypto
methods: Inherited from InstrumentInfo, Inherited from InstrumentCoreInfo, InstrumentInfoCrypto, InstrumentInfoCrypto, Inherited from InstrumentInfo, Inherited from InstrumentCoreInfo, Inherited from java.lang.Object
---

# InstrumentInfoCrypto

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** `java.lang.Object` → `velox.api.layer1.data.InstrumentCoreInfo` → `velox.api.layer1.data.InstrumentInfo` → `InstrumentInfoCrypto`

**All Implemented Interfaces:** `Serializable`

**Deprecated:** This class is deprecated

## Description

Instrument info with some extra data usually found in cryptocurrencies.

You no longer need this class, since `InstrumentInfo.sizeMultiplier` was moved to parent class.

**See Also:**
- Serialized Form

## Fields

### Inherited from InstrumentInfo

- `dataDelay`
- `fullName`
- `isApiProtected`
- `isCrypto`
- `isFullDepth`
- `isNbboSupported`
- `multiplier`
- `pips`
- `recordingTag`
- `requestedSymbol`
- `sizeMultiplier`
- `UNKNOWN_DELAY`

### Inherited from InstrumentCoreInfo

- `exchange`
- `symbol`
- `type`

## Constructors

### InstrumentInfoCrypto

```java
public InstrumentInfoCrypto(String symbol, String exchange, String type, double pips, double multiplier, String fullName, double sizeMultiplier)
```

**Deprecated:** This constructor is deprecated

### InstrumentInfoCrypto

```java
public InstrumentInfoCrypto(String symbol, String exchange, String type, double pips, double multiplier, String fullName, boolean isFullDepth, double sizeMultiplier)
```

**Deprecated:** This constructor is deprecated

Assumes `InstrumentInfo.isFullDepth` = true

## Methods

### Inherited from InstrumentInfo

- `toBuilder()`
- `toString()`

### Inherited from InstrumentCoreInfo

- `equals(Object)`
- `hashCode()`
- `normalize()`

### Inherited from java.lang.Object

- `clone()`
- `finalize()`
- `getClass()`
- `notify()`
- `notifyAll()`
- `wait()`
- `wait(long)`
- `wait(long, int)`