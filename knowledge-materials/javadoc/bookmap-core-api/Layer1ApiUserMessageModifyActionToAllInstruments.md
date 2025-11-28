---
source_file: Layer1ApiUserMessageModifyActionToAllInstruments.html
package: velox.api.layer1.messages
classes: Layer1ApiUserMessageModifyActionToAllInstruments
methods: name, isAdd, Layer1ApiUserMessageModifyActionToAllInstruments, getActionPerformedMessage
---

# Layer1ApiUserMessageModifyActionToAllInstruments

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → Layer1ApiUserMessageModifyActionToAllInstruments

**Direct Known Subclasses:** UserMessageModifyAllInstrumentControlsHft

## Description

Adds new action to dropdown menu for every instrument in ControlGui

## Fields

### name

```java
public final String name
```

### isAdd

```java
public final boolean isAdd
```

## Constructors

### Layer1ApiUserMessageModifyActionToAllInstruments

```java
public Layer1ApiUserMessageModifyActionToAllInstruments(String name, boolean isAdd)
```

**Parameters:**
- `name` - Name of action that user will see
- `isAdd` - True if action is added, false if removed

## Methods

### getActionPerformedMessage

```java
public abstract Layer1ApiStrategyMessageSymboled getActionPerformedMessage(String symbol)
```

Creates message that will be sent when this action is selected, containing information about invoking instrument symbol

**Parameters:**
- `symbol` - Instrument symbol for which action was invoked

**Returns:** Message to be sent when action is performed