---
source_file: Layer1ApiInstrumentSpecificEnabledStateProvider.html
package: velox.api.layer1
classes: Layer1ApiInstrumentSpecificEnabledStateProvider
methods: onStrategyCheckboxEnabled, isStrategyEnabled, setStrategyEnabledRecheckCallback
---

# Layer1ApiInstrumentSpecificEnabledStateProvider

**Package:** velox.api.layer1

**Type:** Interface

## Description

If this interface is implemented, when strategy checkbox is selected/deselected in Studies config / Strategy config dialog, strategy will not be loaded/unloaded, instead it will be notified via this interface.

## Methods

### onStrategyCheckboxEnabled

```java
void onStrategyCheckboxEnabled(String alias, boolean isEnabled)
```

Checkbox for this strategy for this alias was set enabled/disabled.

**Parameters:**
- `alias` - Alias of instrument
- `isEnabled` - True if checkbox was selected, false otherwise

### isStrategyEnabled

```java
boolean isStrategyEnabled(String alias)
```

**Parameters:**
- `alias` - Alias of instrument

**Returns:** True if strategy is currently enabled for this alias, false otherwise

### setStrategyEnabledRecheckCallback

```java
default void setStrategyEnabledRecheckCallback(String alias, Runnable recheckCallback)
```

Set callback that allows strategy to notify that value returned by `isStrategyEnabled(String)` has changed. You don't need to implement it unless your strategy can change enabled state without being asked from outside.

**Parameters:**
- `alias` - Alias of the instrument which callback relates to
- `recheckCallback` - Runnable to call if enabled state has changed