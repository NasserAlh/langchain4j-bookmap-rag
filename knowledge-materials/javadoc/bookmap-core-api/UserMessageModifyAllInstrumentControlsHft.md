---
source_file: UserMessageModifyAllInstrumentControlsHft.html
package: velox.api.layer1.messages
classes: UserMessageModifyAllInstrumentControlsHft
methods: UserMessageModifyAllInstrumentControlsHft, getActionPerformedMessage
---

# UserMessageModifyAllInstrumentControlsHft

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** `java.lang.Object` → `velox.api.layer1.messages.Layer1ApiUserMessageModifyActionToAllInstruments` → `UserMessageModifyAllInstrumentControlsHft`

## Fields

**Inherited from velox.api.layer1.messages.Layer1ApiUserMessageModifyActionToAllInstruments:**
- `isAdd`
- `name`

## Constructors

### UserMessageModifyAllInstrumentControlsHft

```java
public UserMessageModifyAllInstrumentControlsHft(String name, boolean isAdd)
```

## Methods

### getActionPerformedMessage

```java
public Layer1ApiStrategyMessageSymboled getActionPerformedMessage(String symbol)
```

Creates message that will be sent when this action is selected, containing information about invoking instrument symbol.

**Parameters:**
- `symbol` - Instrument symbol

**Returns:** Message containing symbol information

**Specified by:** `getActionPerformedMessage` in class `Layer1ApiUserMessageModifyActionToAllInstruments`

---

**Methods inherited from java.lang.Object:**
- `clone()`
- `equals(Object)`
- `finalize()`
- `getClass()`
- `hashCode()`
- `notify()`
- `notifyAll()`
- `toString()`
- `wait()`
- `wait(long)`
- `wait(long, int)`