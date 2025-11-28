---
source_file: Layer1ApiAlertSettingsMessage.html
package: velox.api.layer1.messages
classes: Layer1ApiAlertSettingsMessage
methods: Layer1ApiAlertSettingsMessage.Builder, id, declarationId, popup, sound, source, defaultSettings, builder, toString
---

# Layer1ApiAlertSettingsMessage

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → Layer1ApiAlertSettingsMessage

**All Implemented Interfaces:** Layer1ApiStrategiesEchoMessagesLayer.StrategyEchoMessageFromLayer

## Description

Bookmap sends this message to the addon, if a user changed alert settings via GUI. Your addon should listen for these messages via `Layer1ApiAdminListener.onUserMessage(Object)` and change its inner state accordingly, so the following `Layer1ApiSoundAlertMessage`'s sent by the addon have correct settings.

Internally Bookmap stores these settings, and checks the sound alert messages for conformity with the expected settings. If they diverge, that is - a sound alert message arrived with **`Layer1ApiSoundAlertMessage.sound` = true**, while there is a settings message registered with **`sound` = false** - an exception will be thrown, and the addon will be unloaded.

Your addon can also send this message to notify Bookmap about sound alert settings changes.

**See Also:**
- `Layer1ApiSoundAlertMessage`
- `Layer1ApiSoundAlertDeclarationMessage`

## Nested Classes

### Layer1ApiAlertSettingsMessage.Builder

## Fields

### id

```java
public final String id
```

Id can be used to reference this message

### declarationId

```java
public final String declarationId
```

The `Layer1ApiSoundAlertDeclarationMessage.id` of a linked alert declaration

Note that if you specify this field, in the Bookmap there should already exist a registered declaration with the given id.

### popup

```java
public final boolean popup
```

True if the sound alerts described with a linked alert declaration have a popup notification

### sound

```java
public final boolean sound
```

True if the sound alerts described with a linked alert declaration have a sound notification

### source

```java
public final Class<?> source
```

Class that created this message. Must be annotated by `Layer1StrategyName`

## Methods

### defaultSettings

```java
public static Layer1ApiAlertSettingsMessage defaultSettings(String declarationId, Class<?> source)
```

Create a default settings message with **`popup` = false** and **`sound` = false**

**Returns:** Default settings

### builder

```java
public static Layer1ApiAlertSettingsMessage.Builder builder()
```

Creates builder to build `Layer1ApiSoundAlertMessage`.

**Returns:** Created builder

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`