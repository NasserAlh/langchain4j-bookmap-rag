---
source_file: CustomModule.html
package: velox.api.layer1.simplified
classes: CustomModule
methods: initialize, stop
---

# CustomModule

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Known Subinterfaces:** AllDataModule, CustomModuleAdapter

## Description

Must be implemented by modules annotated with `Layer1SimpleAttachable`. Provides way for Bookmap to interact with module.

## Methods

### initialize

```java
void initialize(String alias, InstrumentInfo info, Api api, InitialState initialState)
```

Called before any other method.

**Parameters:**
- `alias` - Host instrument (the one where checkbox was checked)
- `info` - Instrument info. Might be an instance of a subclass, providing some extra data
- `api` - Object for interacting with Bookmap
- `initialState` - Additional information partially replacing historical data, such as last trade price

### stop

```java
void stop()
```

Called before unloading the module. If you have started any thread or allocated any resources - that's a good place to release those.