---
source_file: Layer1ApiNotReloadedOnSettingsChange.html
package: velox.api.layer1
classes: Layer1ApiNotReloadedOnSettingsChange
methods: onSettingsChanged
---

# Layer1ApiNotReloadedOnSettingsChange

**Package:** velox.api.layer1

**Type:** Interface

## Description

If add-on implements this interface then it will not be unloaded and then loaded again when user has reset chart settings. Add-on should manage its own behavior in this case (recreate generators if they depend on settings, invalidate indicators, etc.).

Note: add-ons that create synthetic instruments should implement this interface, as otherwise they will cause crash if user will try to use some chart setting manipulation (reset/save/load/inherit) on synthetic instruments.

## Methods

### onSettingsChanged

```java
void onSettingsChanged(String instrumentAlias)
```

Called when the user resets chart settings.

**Note:** This method is called from another thread, not the one from which you by default receive events (such as onUserMessage, onTrade, and e.c.). Therefore, you may need to consider synchronization when using this method in conjunction with those other methods.

**Parameters:**
- `instrumentAlias` - null if the user has reset all charts settings, otherwise the alias of the instrument on which the user has reset settings