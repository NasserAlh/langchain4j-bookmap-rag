---
source_file: Layer1ApiSoundAlertCancelMessage.html
package: velox.api.layer1.messages
classes: Layer1ApiSoundAlertCancelMessage
methods: Layer1ApiSoundAlertCancelMessage.Layer1ApiSoundMessagesFilter, source, soundMessagesFilter, Layer1ApiSoundAlertCancelMessage, toString
---

# Layer1ApiSoundAlertCancelMessage

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → Layer1ApiSoundAlertCancelMessage

**All Implemented Interfaces:** Layer1ApiStrategiesEchoMessagesLayer.StrategyEchoMessageFromLayer

## Description

Cancels all messages that are currently pending, that match filter.  
**Note:** if this message is sent from the `Layer1ApiFinishable.finish()`, the filter method might be ignored resulting in all messages from the source being cancelled.

**See Also:**
- `Layer1ApiSoundAlertMessage`

## Nested Classes

### Layer1ApiSoundAlertCancelMessage.Layer1ApiSoundMessagesFilter

```java
static interface Layer1ApiSoundMessagesFilter
```

## Fields

### source

```java
public final Class<?> source
```

Class that created this message

### soundMessagesFilter

```java
public final Layer1ApiSoundAlertCancelMessage.Layer1ApiSoundMessagesFilter soundMessagesFilter
```

## Constructors

### Layer1ApiSoundAlertCancelMessage

```java
public Layer1ApiSoundAlertCancelMessage(Class<?> source, Layer1ApiSoundAlertCancelMessage.Layer1ApiSoundMessagesFilter soundMessagesFilter)
```

## Methods

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`

---

**Methods inherited from class java.lang.Object:**
- `clone`
- `equals`
- `finalize`
- `getClass`
- `hashCode`
- `notify`
- `notifyAll`
- `wait`
- `wait(long)`
- `wait(long, int)`