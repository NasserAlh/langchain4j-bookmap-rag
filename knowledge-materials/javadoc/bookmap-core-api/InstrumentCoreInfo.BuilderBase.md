---
source_file: InstrumentCoreInfo.BuilderBase.html
package: velox.api.layer1.data
classes: InstrumentCoreInfo.BuilderBase
methods: symbol, exchange, type, BuilderBase, self, build, setFieldsFrom, getSymbol, setSymbol, getExchange, setExchange, getType, setType
---

# InstrumentCoreInfo.BuilderBase

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → InstrumentCoreInfo.BuilderBase<T>

**Type Parameters:**
- `T` - concrete builder class

**Direct Known Subclasses:** InstrumentCoreInfo.Builder, InstrumentInfo.BuilderBase

**Enclosing class:** InstrumentCoreInfo

## Description

Builder pattern for inheritance structures. It works like this:

```
InstrumentCoreInfo.BuilderBase <----- InstrumentCoreInfo.Builder
             ^
             |
InstrumentInfo.BuilderBase <--------- InstrumentInfo.Builder
             ^
             |
InstrumentInfoDxfeed.Builder
```

If you want to add another builder to inheritance after last child (for example after InstrumentInfoDxfeed.Builder) you should split the exiting builder into concrete and abstract classes (like InstrumentInfoDxfeed.BuilderBase and InstrumentInfoDxfeed.Builder), and extend your new concrete builder from created abstract class, i.e. something like this:

```
...
InstrumentInfo.BuilderBase <--------- InstrumentInfo.Builder
             ^
             |
InstrumentInfoDxfeed.BuilderBase <-- InstrumentInfoDxfeed.Builder
             ^
             |
InstrumentInfoNew.Builder
```

Also, there are several methods that you should overwrite for everything to work correctly:
- `self()`, `build()` - in the concrete builder class
- `setFieldsFrom(InstrumentCoreInfo)` - in the child builder base class with correct InstrumentInfo type
- `InstrumentCoreInfo.toBuilder()` - in the InstrumentInfo subclasses to ensure the correct builder class is created, otherwise you will lose some fields, and receive wrong InstrumentInfo subtype

## Fields

### symbol
```java
protected String symbol
```

### exchange
```java
protected String exchange
```

### type
```java
protected String type
```

## Constructors

### BuilderBase
```java
public BuilderBase()
```

## Methods

### self
```java
protected abstract T self()
```

### build
```java
public abstract InstrumentCoreInfo build()
```

### setFieldsFrom
```java
public T setFieldsFrom(InstrumentCoreInfo info)
```

### getSymbol
```java
public String getSymbol()
```

### setSymbol
```java
public T setSymbol(String symbol)
```

### getExchange
```java
public String getExchange()
```

### setExchange
```java
public T setExchange(String exchange)
```

### getType
```java
public String getType()
```

### setType
```java
public T setType(String type)
```