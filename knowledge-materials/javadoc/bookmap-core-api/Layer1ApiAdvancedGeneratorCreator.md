---
source_file: Layer1ApiAdvancedGeneratorCreator.html
package: velox.api.layer1
classes: Layer1ApiAdvancedGeneratorCreator
methods: onAddGenerators, onRemoveIndicators, onSettingsChanged
---

# Layer1ApiAdvancedGeneratorCreator

**Package:** velox.api.layer1

**Type:** Interface

**Deprecated:** Do not use this class, use `Layer1ApiNotReloadedOnSettingsChange` instead. Although the generator will still work after restarting the addon, if you implement this interface, classloader for this generator will be closed, which could raise a `ClassNotFoundException`

## Description

Normally when settings are changed (e.g. via chart settings inherit option), strategy will be finished via `Layer1ApiFinishable.finish()` and started via `UserMessageLayersChainCreatedTargeted`. If strategy generates custom events, normally it would create and add generator every on `UserMessageLayersChainCreatedTargeted` message. Generating events can take considerable time.

This interface allows you to handle event generation in such cases gracefully.

It will call `onAddGenerators()` every time data has changes (e.g. as new feed is being loaded)
`onRemoveIndicators()` when added indicators should be removed due to data change
`onSettingsChanged()` every time settings has been changed.

Note: don't use this interface for managing event generators that rely on settings that can be changed

## Methods

### onAddGenerators

```java
void onAddGenerators()
```

**Deprecated**

Called when data has been changed (e.g. as new feed is being loaded) or strategy is manually added. Strategy is expected to add all required generators here.

### onRemoveIndicators

```java
void onRemoveIndicators()
```

**Deprecated**

Called when strategy is being unloaded due to data change (e.g. as new feed is being loaded) or if strategy is manually removed.

### onSettingsChanged

```java
void onSettingsChanged()
```

**Deprecated**

Called when settings has been changed. All previously added generators.