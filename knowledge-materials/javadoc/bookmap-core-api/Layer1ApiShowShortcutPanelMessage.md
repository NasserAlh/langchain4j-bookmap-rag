---
source_file: Layer1ApiShowShortcutPanelMessage.html
package: velox.api.layer1.messages
classes: Layer1ApiShowShortcutPanelMessage
methods: groups, Layer1ApiShowShortcutPanelMessage, Layer1ApiShowShortcutPanelMessage
---

# Layer1ApiShowShortcutPanelMessage

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → Layer1ApiShowShortcutPanelMessage

**All Implemented Interfaces:** Layer1ApiStrategiesEchoMessagesLayer.StrategyEchoMessageFromLayer

## Description

Show shortcut panel to user if it's currently not shown.

## Fields

### groups

```java
public final String[] groups
```

## Constructors

### Layer1ApiShowShortcutPanelMessage

```java
public Layer1ApiShowShortcutPanelMessage(String[] groups)
```

**Parameters:**
- `groups` - Describes group hierarchy that will be expanded when the shortcut panel is shown. To open your addon menu use following path: first level is `ActionInfo#ADDONS_TRANSLATION_KEY` (addons group), second level is your addon name (localized name if your addon have it). For further path use the same names for your addon internal actions as you use when create an action in `Layer1ActionMetadata.groups()` (if you use localization keys, use them here as well). Example: `new String[]{ActionConstants.ADDONS_TRANSLATION_KEY, localizedBundle.getString("LocalizedStrategy.Name"), "LocalizedStrategy.ActionGroup"}`

### Layer1ApiShowShortcutPanelMessage

```java
public Layer1ApiShowShortcutPanelMessage()
```