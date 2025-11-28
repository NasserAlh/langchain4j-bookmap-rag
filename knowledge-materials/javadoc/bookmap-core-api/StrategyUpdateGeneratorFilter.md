---
source_file: StrategyUpdateGeneratorFilter.html
package: velox.api.layer1.messages.indicators
classes: StrategyUpdateGeneratorFilter
methods: getGeneratorUpdateTypes, getGeneratorAliases
---

# StrategyUpdateGeneratorFilter

**Package:** velox.api.layer1.messages.indicators

**Type:** Interface

## Description

Limits events and instrument aliases that [`StrategyUpdateGenerator`](StrategyUpdateGenerator.html) listens to. At this stage you are not guaranteed not to receive other events (so you still should filter those out; this behavior might change later) but this allows faster processing.

## Nested Classes

- `StrategyUpdateGeneratorFilter.StrategyUpdateGeneratorEventType` (enum)

## Methods

### getGeneratorUpdateTypes

```java
Set<StrategyUpdateGeneratorFilter.StrategyUpdateGeneratorEventType> getGeneratorUpdateTypes()
```

**Returns:** Set containing all update types you wish to receive or null to receive all update types

### getGeneratorAliases

```java
Set<String> getGeneratorAliases()
```

**Returns:** Set containing all aliases for which you wish to receive updates or null to receive updates for all aliases