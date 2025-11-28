---
source_file: Action.html
package: velox.api.layer1.actions
classes: Action
methods: performAction, onShortcutChanged
---

# Action

**Package:** velox.api.layer1.actions

**Type:** Interface

**All Known Subinterfaces:** Layer1ExternalAction

## Description

Represents an action on which users can set shortcuts.

This is the most basic entity for internal usage.

Strategies should use `Layer1ExternalAction` instead to register their actions.

## Methods

### performAction

```java
boolean performAction(String actionId, KeyEvent e)
```

Will be triggered when a user presses shortcut associated with this action.

If `Layer1ExternalAction` instance throws an exception when it's called, strategy will be unloaded.

**Parameters:**
- `actionId` - Associated with the shortcut
- `e` - Is standard awt key event

**Returns:** 
- `true` means that action is processing this event and no further event transmission is required (to the next listener in the queue) because the event is handled by this action
- `false` if the action will not handle the event (e.g. due to the fact that the focus is not on the target window, etc.), in this case the event will be passed to the next listener in the queue

### onShortcutChanged

```java
default void onShortcutChanged(String actionId, Set<String> shortcuts)
```

Will be triggered when a user changes shortcuts (on add, edit or remove) in the Keyboard Shortcuts window.

Also, known shortcuts related to this action will be sent to this method after the action is registered.

The shortcut format is a string representation obtained from the `KeyStroke`, using `KeyStroke.getKeyStrokeForEvent(java.awt.event.KeyEvent)` method.

If `Layer1ExternalAction` instance throws an exception when it's called, strategy will be unloaded.

**Parameters:**
- `actionId` - For which shortcut is changed
- `shortcuts` - Set of shortcuts is bound to this action