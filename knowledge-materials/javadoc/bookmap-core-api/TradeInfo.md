---
source_file: TradeInfo.html
package: velox.api.layer1.data
classes: TradeInfo
methods: isOtc, isBidAggressor, isExecutionStart, isExecutionEnd, aggressorOrderId, passiveOrderId, TradeInfo, TradeInfo, TradeInfo, compare, toString
---

# TradeInfo

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.data.TradeInfo

**All Implemented Interfaces:** java.io.Serializable

## Description

Information about the trade

**See Also:**
- Serialized Form

## Fields

### isOtc

```java
public final boolean isOtc
```

True if the trade is OTC

### isBidAggressor

```java
public final boolean isBidAggressor
```

True if bid was the aggressor, false if ask was the aggressor

### isExecutionStart

```java
public final boolean isExecutionStart
```

True if this trade starts new order execution chain

### isExecutionEnd

```java
public final boolean isExecutionEnd
```

True if this trade ends order execution chain

### aggressorOrderId

```java
public final @Nullable String aggressorOrderId
```

Aggressor for this trade, can be null if not known/not applicable

### passiveOrderId

```java
public final @Nullable String passiveOrderId
```

Order (partially) consumed by aggressor, can be null if not known/not applicable

## Constructors

### TradeInfo

```java
public TradeInfo(boolean isOtc, boolean isBidAggressor)
```

### TradeInfo

```java
public TradeInfo(boolean isOtc, boolean isBidAggressor, boolean isExecutionStart, boolean isExecutionEnd)
```

### TradeInfo

```java
public TradeInfo(boolean isOtc, boolean isBidAggressor, boolean isExecutionStart, boolean isExecutionEnd, @Nullable String aggressorOrderId, @Nullable String passiveOrderId)
```

## Methods

### compare

```java
public boolean compare(TradeInfo other)
```

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`