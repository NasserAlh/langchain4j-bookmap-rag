---
source_file: InstrumentInfo.BuilderBase.html
package: velox.api.layer1.data
classes: InstrumentInfo.BuilderBase
methods: pips, multiplier, fullName, isFullDepth, sizeMultiplier, isCrypto, recordingTag, isApiProtected, isNbboSupported, dataDelay, requestedSymbol, BuilderBase, build, setFieldsFrom, getPips, setPips, getMultiplier, setMultiplier, getFullName, setFullName
total_methods: 35
---

# InstrumentInfo.BuilderBase

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** `java.lang.Object` → `velox.api.layer1.data.InstrumentCoreInfo.BuilderBase<T>` → `velox.api.layer1.data.InstrumentInfo.BuilderBase<T>`

**Direct Known Subclasses:** `InstrumentInfo.Builder`

**Enclosing class:** `InstrumentInfo`

## Description

```java
public abstract static class InstrumentInfo.BuilderBase<T extends InstrumentInfo.BuilderBase<T>> extends InstrumentCoreInfo.BuilderBase<T>
```

## Fields

### pips
```java
protected double pips
```

### multiplier
```java
protected double multiplier
```

### fullName
```java
protected String fullName
```

### isFullDepth
```java
protected boolean isFullDepth
```

### sizeMultiplier
```java
protected double sizeMultiplier
```

### isCrypto
```java
protected boolean isCrypto
```

### recordingTag
```java
protected String recordingTag
```

### isApiProtected
```java
protected boolean isApiProtected
```

### isNbboSupported
```java
protected boolean isNbboSupported
```

### dataDelay
```java
protected long dataDelay
```

### requestedSymbol
```java
protected String requestedSymbol
```

**Fields inherited from class `velox.api.layer1.data.InstrumentCoreInfo.BuilderBase`:**
- `exchange`
- `symbol`
- `type`

## Constructors

### BuilderBase
```java
public BuilderBase()
```

## Methods

### build
```java
public abstract InstrumentInfo build()
```

**Specified by:** `build` in class `InstrumentCoreInfo.BuilderBase<T extends InstrumentInfo.BuilderBase<T>>`

### setFieldsFrom
```java
public T setFieldsFrom(InstrumentInfo info)
```

### getPips
```java
public double getPips()
```

### setPips
```java
public T setPips(double pips)
```

Also known as min tick, tick size or minimal price increment. This allows bookmap to translate level numbers to price that is shown to the user and vice versa.

### getMultiplier
```java
public double getMultiplier()
```

### setMultiplier
```java
public T setMultiplier(double multiplier)
```

Similar to "tick value" in its meaning, with the only difference of being per price unit instead of per tick (can be computed as tick value divided by `pips`).

Specifically, this field tells bookmap the following: "how many dollars (or other currency units) will my P&L change if I have position of 1 and price moves 1 dollar (or other price unit) up".

### getFullName
```java
public String getFullName()
```

### setFullName
```java
public T setFullName(String fullName)
```

Full instrument name on the platforms where it's available, null otherwise.

### isFullDepth
```java
public boolean isFullDepth()
```

### setFullDepth
```java
public T setFullDepth(boolean fullDepth)
```

True if data provided for instrument is full depth data.

### getSizeMultiplier
```java
public double getSizeMultiplier()
```

### setSizeMultiplier
```java
public T setSizeMultiplier(double sizeMultiplier)
```

Used when size granularity is less than 1 (for most providers it's always 1, so you can ignore this field if only those are targeted). This is only a visual thing, as all updates should still be performed in integer units. It was introduced mostly because of cryptocurrencies - since sizes are no longer integer values, you can premultiply all sizes by certain value and specify it in this field. E.g. 1000 will make bookmap display size of 1 as 0.001.

### isCrypto
```java
public boolean isCrypto()
```

### setCrypto
```java
public T setCrypto(boolean crypto)
```

Allows bookmap to distinguish crypto instruments from other types. If true - this instrument is a cryptocurrency or is based on cryptocurrencies in some way.

### getRecordingTag
```java
public String getRecordingTag()
```

### setRecordingTag
```java
public T setRecordingTag(String recordingTag)
```

Used to distinguish local history with special modes on the per instrument basis (i.e. local history with delayed data vs live data). Provider should set this field to specify special mode of this instrument,"DELAYED" for example.

**Note:** it is recommended to not set this field for *LIVE* instruments for backward compatibility of historical servers, i.e. so the historical server returned "live" data to the user with the old client.

### isApiProtected
```java
public boolean isApiProtected()
```

### setApiProtected
```java
public T setApiProtected(boolean apiProtected)
```

If this flag is set to true, then all add-ons, except those marked with `UnrestrictedData` annotation, will be blocked from receiving data from this instrument.

### setDataDelay
```java
public T setDataDelay(long dataDelay)
```

Sets the data delay for the instrument.

**Parameters:**
- `dataDelay` - The data delay in nanoseconds.

**Returns:** A reference to this object (for method chaining).

### getRequestedSymbol
```java
public String getRequestedSymbol()
```

### setRequestedSymbol
```java
public T setRequestedSymbol(String requestedSymbol)
```

Used to correctly handle "generic" instrument substitution. If your provider wants to subscribe to the instrument with a different symbol than you received in subscription request (for example you received "ES" and want to subscribe to "ESH4"), then you should set this field to the symbol which you received in request.

**Parameters:**
- `requestedSymbol` - symbol that you received in subscription method.

### isNbboSupported
```java
public boolean isNbboSupported()
```

### setNbboSupported
```java
public T setNbboSupported(boolean nbboSupported)
```

If this flag is set to true, then this instrument supports NBBO data, and it is possible to create NBBO indicator for the instrument.

**Methods inherited from class `velox.api.layer1.data.InstrumentCoreInfo.BuilderBase`:**
- `getExchange()`
- `getSymbol()`
- `getType()`
- `self()`
- `setExchange(String)`
- `setFieldsFrom(InstrumentCoreInfo)`
- `setSymbol(String)`
- `setType(String)`

**Methods inherited from class `java.lang.Object`:**
- `clone()`
- `equals(Object)`
- `finalize()`
- `getClass()`
- `hashCode()`
- `notify()`
- `notifyAll()`
- `toString()`
- `wait()`
- `wait(long)`
- `wait(long, int)`