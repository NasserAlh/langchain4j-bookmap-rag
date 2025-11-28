---
source_file: SymbolMappingInfo.html
package: velox.api.layer1.data
classes: SymbolMappingInfo
methods: alternatives, crossTradingTo, multiplier, pipsSupplier, SymbolMappingInfo
---

# SymbolMappingInfo

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → SymbolMappingInfo

## Description

Information to be injected into the symbols mapping system

## Fields

### alternatives

```java
public final Set<InstrumentCoreInfo> alternatives
```

Alternative representations of the same symbol, i.e. same symbol on a different platform

### crossTradingTo

```java
public final Set<InstrumentCoreInfo> crossTradingTo
```

Cross trading targets to add.

### multiplier

```java
public final double multiplier
```

Mutiplier, see `InstrumentInfo.multiplier`

### pipsSupplier

```java
public final @Nullable Function<Double, Double> pipsSupplier
```

Function that supplies pips value (see `InstrumentInfo.pips`) based on the provided price.

## Constructors

### SymbolMappingInfo

```java
public SymbolMappingInfo(Set<InstrumentCoreInfo> alternatives, Set<InstrumentCoreInfo> crossTradingTo, double multiplier, @Nullable Function<Double, Double> pipsSupplier)
```