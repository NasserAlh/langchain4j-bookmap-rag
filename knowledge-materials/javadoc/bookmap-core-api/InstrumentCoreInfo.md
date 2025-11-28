---
source_file: InstrumentCoreInfo.html
package: velox.api.layer1.data
classes: InstrumentCoreInfo
methods: InstrumentCoreInfo.Builder, InstrumentCoreInfo.BuilderBase<T extends InstrumentCoreInfo.BuilderBase<T>>, symbol, exchange, type, InstrumentCoreInfo, InstrumentCoreInfo, toBuilder, toString, hashCode, equals, normalize
---

# InstrumentCoreInfo

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → InstrumentCoreInfo

**All Implemented Interfaces:** Serializable

**Direct Known Subclasses:** InstrumentInfo

## Description

Minimal sufficient amount of data to identify the instrument within any platform.

**See Also:**
- Serialized Form

## Nested Classes

### InstrumentCoreInfo.Builder

### InstrumentCoreInfo.BuilderBase<T extends InstrumentCoreInfo.BuilderBase<T>>

Builder pattern for inheritance structures.

## Fields

### symbol

```java
public final String symbol
```

### exchange

```java
public final String exchange
```

### type

```java
public final String type
```

For multiplatform mode might end with "@Platform", e.g. "@Rithmic"

## Constructors

### InstrumentCoreInfo

```java
public InstrumentCoreInfo(String symbol, String exchange, String type)
```

### InstrumentCoreInfo

```java
protected InstrumentCoreInfo(InstrumentCoreInfo.BuilderBase<?> builder)
```

## Methods

### toBuilder

```java
public InstrumentCoreInfo.BuilderBase<?> toBuilder()
```

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`

### hashCode

```java
public int hashCode()
```

**Overrides:** `hashCode` in class `Object`

### equals

```java
public boolean equals(Object obj)
```

**Overrides:** `equals` in class `Object`

### normalize

```java
public InstrumentCoreInfo normalize()
```

Create a copy of this InstrumentInfo with all null fields transformed into empty strings.

---

**Methods inherited from class java.lang.Object:**
- `clone`
- `finalize`
- `getClass`
- `notify`
- `notifyAll`
- `wait`
- `wait(long)`
- `wait(long, int)`