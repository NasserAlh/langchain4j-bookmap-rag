---
source_file: Layer1ExternalAction.html
package: velox.api.layer1.actions
classes: Layer1ExternalAction
methods: EXTERNALWINDOWALIASPROPERTY, ALLOWEXECUTINGBUILTINACTIONS, Inherited from Action
extends: ** Action
---

# Layer1ExternalAction

**Package:** velox.api.layer1.actions

**Type:** Interface

**All Superinterfaces:** Action

## Description

Represents an action that will be register in Bookmap action system.

This class should be annotated with `Layer1ActionMetadata` annotation(s), otherwise it will be ignored.

External actions will be located in the **Keyboard Shortcuts** window in **Addons/strategy_name** group.

If `Layer1ExternalAction` instance throws an exception when it's called, strategy will be unloaded.

## Fields

### EXTERNAL_WINDOW_ALIAS_PROPERTY

```java
static final String EXTERNAL_WINDOW_ALIAS_PROPERTY
```

Strategy window (JComponent or JFrame) must be tagged with this property, if the strategy wants to execute custom actions in the external strategy window.

Property value is an alias to which window belongs.

```java
frame.getRootPane().putClientProperty(Layer1ExternalAction.EXTERNAL_WINDOW_ALIAS_PROPERTY, alias);
```

If your strategy doesn't have any external windows, ignore this field.

**See Also:** Constant Field Values

### ALLOW_EXECUTING_BUILTIN_ACTIONS

```java
static final String ALLOW_EXECUTING_BUILTIN_ACTIONS
```

Strategy window (JComponent or JFrame) must be tagged with this property, if the strategy wants to execute built-in actions in the external strategy window.

This property works only in conjunction with `EXTERNAL_WINDOW_ALIAS_PROPERTY` and should be set to `Boolean.TRUE`.

Trading actions will work even if an instrument is hidden.

```java
frame.getRootPane().putClientProperty(Layer1ExternalAction.EXTERNAL_WINDOW_ALIAS_PROPERTY, alias);
frame.getRootPane().putClientProperty(Layer1ExternalAction.ALLOW_EXECUTING_BUILTIN_ACTIONS, true);
```

If your strategy doesn't have any external windows, ignore this field.

**See Also:** Constant Field Values

## Methods

### Inherited from Action

```java
void onShortcutChanged(String actionId, Set<KeyStroke> keyStrokes)
```

```java
void performAction(String actionId, KeyEvent event)
```