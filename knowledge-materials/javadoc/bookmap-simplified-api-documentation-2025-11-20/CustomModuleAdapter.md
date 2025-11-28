---
source_file: CustomModuleAdapter.html
package: velox.api.layer1.simplified
classes: CustomModuleAdapter
methods: initialize, stop
extends: CustomModule
---

# CustomModuleAdapter

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Superinterfaces:** CustomModule

## Description

An adapter for `CustomModule` with empty default method implementations.

## Methods

### initialize

```java
default void initialize(String alias, InstrumentInfo info, Api api, InitialState initialState)
```

Called before any other method.

**Parameters:**
- `alias` - Host instrument (the one where checkbox was checked)
- `info` - Instrument info. Might be an instance of a subclass, providing some extra data
- `api` - Object for interacting with Bookmap
- `initialState` - Additional information partially replacing historical data, such as last trade price

### stop

```java
default void stop()
```

Called before unloading the module. If you have started any thread or allocated any resources - that's a good place to release those.