---
source_file: InitialState.html
package: velox.api.layer1.simplified
classes: InitialState
methods: InitialState, getCurrentTime, getLastTradeSize, getLastTradePrice, getTradeInfo
---

# InitialState

**Package:** velox.api.layer1.simplified

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.simplified.InitialState

## Description

Some initialization data about events that happened shortly before the initialization moment (note, that for historical-enabled indicators many fields will be empty)

## Constructors

### InitialState

```java
public InitialState()
```

## Methods

### getCurrentTime

```java
public long getCurrentTime()
```

### getLastTradeSize

```java
public int getLastTradeSize()
```

### getLastTradePrice

```java
public double getLastTradePrice()
```

### getTradeInfo

```java
public velox.api.layer1.data.TradeInfo getTradeInfo()
```