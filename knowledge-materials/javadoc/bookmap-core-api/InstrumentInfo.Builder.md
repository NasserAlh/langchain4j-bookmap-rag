---
source_file: InstrumentInfo.Builder.html
package: velox.api.layer1.data
classes: InstrumentInfo.Builder
methods: Builder, self, build
---

# InstrumentInfo.Builder

**Package:** velox.api.layer1.data

**Type:** Class

**Enclosing class:** [`InstrumentInfo`](InstrumentInfo.html)

**Inheritance:**
- `java.lang.Object`
- `velox.api.layer1.data.InstrumentCoreInfo.BuilderBase<T>`
- `velox.api.layer1.data.InstrumentInfo.BuilderBase<InstrumentInfo.Builder>`
- `velox.api.layer1.data.InstrumentInfo.Builder`

## Fields

**Fields inherited from class `velox.api.layer1.data.InstrumentInfo.BuilderBase`:**
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

**Fields inherited from class `velox.api.layer1.data.InstrumentCoreInfo.BuilderBase`:**
- `exchange`
- `symbol`
- `type`

## Constructors

### Builder

```java
public Builder()
```

## Methods

### self

```java
protected InstrumentInfo.Builder self()
```

**Specified by:** `self` in class `InstrumentCoreInfo.BuilderBase<InstrumentInfo.Builder>`

### build

```java
public InstrumentInfo build()
```

**Specified by:** `build` in class `InstrumentInfo.BuilderBase<InstrumentInfo.Builder>`

## Inherited Methods

**Methods inherited from class `velox.api.layer1.data.InstrumentInfo.BuilderBase`:**
- `getFullName()`
- `getMultiplier()`
- `getPips()`
- `getRecordingTag()`
- `getRequestedSymbol()`
- `getSizeMultiplier()`
- `isApiProtected()`
- `isCrypto()`
- `isFullDepth()`
- `isNbboSupported()`
- `setApiProtected(boolean)`
- `setCrypto(boolean)`
- `setDataDelay(long)`
- `setFieldsFrom(InstrumentInfo)`
- `setFullDepth(boolean)`
- `setFullName(String)`
- `setMultiplier(double)`
- `setNbboSupported(boolean)`
- `setPips(double)`
- `setRecordingTag(String)`
- `setRequestedSymbol(String)`
- `setSizeMultiplier(double)`

**Methods inherited from class `velox.api.layer1.data.InstrumentCoreInfo.BuilderBase`:**
- `getExchange()`
- `getSymbol()`
- `getType()`
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