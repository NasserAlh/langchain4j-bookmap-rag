---
source_file: MultiInstrumentListener.html
package: velox.api.layer1.simplified
classes: MultiInstrumentListener
methods: onCurrentInstrument, onInstrumentAdded
---

# MultiInstrumentListener

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Known Subinterfaces:** AllDataModule, MultiInstrumentAdapter

## Description

Listen to data from multiple instruments at once. Without this interface you will only get data about the alias passed inside `CustomModule.initialize(String, InstrumentInfo, Api, InitialState)`, when this interface is implemented data for all instruments will be received. You can determine which instrument data belongs to by the alias set with previous `onCurrentInstrument(String)` call. Note, that if alias did not change between two updates `onCurrentInstrument(String)` will not be called.

## Methods

### onCurrentInstrument

```java
void onCurrentInstrument(String alias)
```

### onInstrumentAdded

```java
void onInstrumentAdded(velox.api.layer1.data.InstrumentInfo info)
```