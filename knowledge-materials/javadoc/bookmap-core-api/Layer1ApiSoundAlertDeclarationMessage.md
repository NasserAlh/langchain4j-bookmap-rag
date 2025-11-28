---
source_file: Layer1ApiSoundAlertDeclarationMessage.html
package: velox.api.layer1.messages
classes: Layer1ApiSoundAlertDeclarationMessage
methods: Layer1ApiSoundAlertDeclarationMessage.Builder, id, triggerDescription, source, strategyName, aliasMatcher, isPopupAllowed, isSoundAllowed, isRepeated, isAdd, toString, builder
---

# Layer1ApiSoundAlertDeclarationMessage

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → Layer1ApiSoundAlertDeclarationMessage

**All Implemented Interfaces:** Layer1ApiStrategiesEchoMessagesLayer.StrategyEchoMessageFromLayer

## Description

Use this message to declare your addon intents of sending alerts to a user. The class specifies a "blueprint" of future alerts group. Send created message via `Layer1ApiAdminProvider.sendUserMessage(Object)`, and link the declaration message to an alert via `Layer1ApiSoundAlertMessage.alertDeclarationId`.

The Bookmap caches these messages internally, and if your `Layer1ApiSoundAlertMessage` with a linked `Layer1ApiSoundAlertDeclarationMessage` does not conform the fields specified in the declaration message - an exception will be thrown, and your addon will be unloaded.

When a user removes a declaration from GUI - your addon will receive this message with flag **`isAdd` = false**. That is, your addon needs to listen for these messages using `Layer1ApiAdminListener.onUserMessage(Object)`

**See Also:**
- `Layer1ApiSoundAlertMessage`

## Nested Classes

### Layer1ApiSoundAlertDeclarationMessage.Builder

## Fields

### id

```java
public final String id
```

Id can be used to reference this declaration message

### triggerDescription

```java
public final String triggerDescription
```

Short description of the trigger event for this declaration. Will be shown on the UI

### source

```java
public final Class<?> source
```

Class that created this message. The class must have `Layer1StrategyName` annotation present

### strategyName

```java
public final String strategyName
```

Name of the strategy that created this message, extracted from `Layer1StrategyName.value()`, displayed on the UI

### aliasMatcher

```java
public final Predicate<String> aliasMatcher
```

The predicate obtains an instrument alias. If it returns true, the declaration is believed to be linked to the specified alias. Used internally for declarations filtration on the UI

### isPopupAllowed

```java
public final boolean isPopupAllowed
```

Define whether an alert can have a popup notification  
The actual state of the popup notification (on/off) is defined with `Layer1ApiAlertSettingsMessage.popup`

### isSoundAllowed

```java
public final boolean isSoundAllowed
```

Define whether an alert can have a sound notification  
The actual state of the sound notification (on/off) is defined with `Layer1ApiAlertSettingsMessage.sound`

### isRepeated

```java
public final boolean isRepeated
```

True if alerts described by this declaration are repeated - `Layer1ApiSoundAlertMessage.repeatCount` > 1

### isAdd

```java
public final boolean isAdd
```

True if this message adds a declaration. Otherwise, the declaration with the same `id` will be removed

## Methods

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`

### builder

```java
public static Layer1ApiSoundAlertDeclarationMessage.Builder builder()
```

Creates builder to build `Layer1ApiSoundAlertDeclarationMessage`.

**Returns:** created builder