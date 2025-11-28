---
source_file: MultiInstrumentAdapter.html
package: velox.api.layer1.simplified
classes: MultiInstrumentAdapter
methods: onCurrentInstrument, onInstrumentAdded
extends: MultiInstrumentListener
---

# MultiInstrumentAdapter

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Superinterfaces:** MultiInstrumentListener

## Description

An adapter for `MultiInstrumentListener` with empty default method implementations.

## Methods

### onCurrentInstrument

```java
default void onCurrentInstrument(String alias)
```

**Specified by:** `onCurrentInstrument` in interface `MultiInstrumentListener`

### onInstrumentAdded

```java
default void onInstrumentAdded(velox.api.layer1.data.InstrumentInfo info)
```

**Specified by:** `onInstrumentAdded` in interface `MultiInstrumentListener`