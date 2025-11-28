---
source_file: InstrumentInfo.html
package: velox.api.layer1.data
classes: InstrumentInfo
methods: InstrumentInfo.Builder, InstrumentInfo.BuilderBase<T extends InstrumentInfo.BuilderBase<T>>, UNKNOWNDELAY, pips, multiplier, fullName, isFullDepth, sizeMultiplier, isCrypto, recordingTag, isApiProtected, isNbboSupported, dataDelay, requestedSymbol, InstrumentInfo, InstrumentInfo, InstrumentInfo, InstrumentInfo, toBuilder, toString
---

# InstrumentInfo

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.data.InstrumentCoreInfo → InstrumentInfo

**All Implemented Interfaces:** Serializable

**Direct Known Subclasses:** InstrumentInfoCrypto

## Description

Information about the instrument

**See Also:**
- Serialized Form

## Nested Classes

### InstrumentInfo.Builder
Static nested class

### InstrumentInfo.BuilderBase<T extends InstrumentInfo.BuilderBase<T>>
Static nested class with generic type parameter

## Fields

### UNKNOWN_DELAY

```java
public static final long UNKNOWN_DELAY
```

Delay value for the `dataDelay` field, if the delay is currently unknown, but it is known that the data is delayed. Used to inform the Bookmap about some data delay.

### pips

```java
public final double pips
```

Also known as min tick, tick size or minimal price increment. This allows bookmap to translate level numbers to price that is shown to the user and vice versa

### multiplier

```java
public final double multiplier
```

Similar to "tick value" in its meaning, with the only difference of being per price unit instead of per tick (can be computed as tick value divided by `pips`)

Specifically, this field tells bookmap the following: "how many dollars (or other currency units) will my P&L change if I have position of 1 and price moves 1 dollar (or other price unit) up"

### fullName

```java
public final String fullName
```

Full instrument name on the platforms where it's available, null otherwise

### isFullDepth

```java
public final boolean isFullDepth
```

True if data provided for instrument is full depth data

### sizeMultiplier

```java
public final double sizeMultiplier
```

Used when size granularity is less than 1 (for most providers it's always 1, so you can ignore this field if only those are targeted). This is only a visual thing, as all updates should still be performed in integer units. It was introduced mostly because of cryptocurrencies - since sizes are no longer integer values, you can premultiply all sizes by certain value and specify it in this field. E.g. 1000 will make bookmap display size of 1 as 0.001.

### isCrypto

```java
public final boolean isCrypto
```

Allows bookmap to distinguish crypto instruments from other types. If true - this instrument is a cryptocurrency or is based on cryptocurrencies in some way

### recordingTag

```java
public final String recordingTag
```

Used to distinguish local history with special modes on the per instrument basis (i.e. local history with delayed data vs live data)
Provider should set this field to specify special mode of this instrument,"DELAYED" for example

**Note:** it is recommended to not set this field for *LIVE* instruments for backward compatibility of historical servers, i.e. so the historical server returned "live" data to the user with the old client

### isApiProtected

```java
public final boolean isApiProtected
```

If this flag is set to true, then all add-ons, except those marked with `UnrestrictedData` annotation, will be blocked from receiving data from this instrument

### isNbboSupported

```java
public final boolean isNbboSupported
```

If this flag is set to true, then this instrument supports NBBO data, and it is possible to create NBBO indicator for the instrument

### dataDelay

```java
public final long dataDelay
```

Delay in nanoseconds for the data of this instrument. If delay is currently unknown, but it is known that the data is delayed, then you should set this field to `UNKNOWN_DELAY`, to inform the Bookmap that the data is delayed (so the Bookmap can apply some restrictions for delayed data instruments).

### requestedSymbol

```java
public final String requestedSymbol
```

Used to correctly handle "generic" instrument substitution. If your provider wants to subscribe to the instrument with a different symbol than you received in subscription request (for example you received "ES" and want to subscribe to "ESH4"), then you should set this field to the symbol which you received in request.

**Fields inherited from class velox.api.layer1.data.InstrumentCoreInfo:**
- `exchange`
- `symbol` 
- `type`

## Constructors

### InstrumentInfo

```java
@Deprecated
public InstrumentInfo(String symbol, String exchange, String type, double pips, double multiplier, String fullName, boolean isFullDepth, double sizeMultiplier, boolean isCrypto)
```

**Deprecated:** use `InstrumentInfo.Builder` instead

### InstrumentInfo

```java
@Deprecated
public InstrumentInfo(String symbol, String exchange, String type, double pips, double multiplier, String fullName, boolean isFullDepth, double sizeMultiplier)
```

**Deprecated:** use `InstrumentInfo.Builder` instead

Similar to `InstrumentInfo(String, String, String, double, double, String, boolean, double, boolean)`, assumes `isCrypto` false

### InstrumentInfo

```java
@Deprecated
public InstrumentInfo(String symbol, String exchange, String type, double pips, double multiplier, String fullName, boolean isFullDepth)
```

**Deprecated:** use `InstrumentInfo.Builder` instead

### InstrumentInfo

```java
protected InstrumentInfo(InstrumentInfo.BuilderBase<?> builder)
```

## Methods

### toBuilder

```java
public InstrumentInfo.BuilderBase<?> toBuilder()
```

**Overrides:** `toBuilder` in class `InstrumentCoreInfo`

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `InstrumentCoreInfo`

**Methods inherited from class velox.api.layer1.data.InstrumentCoreInfo:**
- `equals`
- `hashCode`
- `normalize`

**Methods inherited from class java.lang.Object:**
- `clone`
- `finalize`
- `getClass`
- `notify`
- `notifyAll`
- `wait`
- `wait(long)`
- `wait(long, int)`