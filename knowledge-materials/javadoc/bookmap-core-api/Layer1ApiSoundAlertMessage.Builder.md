---
source_file: Layer1ApiSoundAlertMessage.Builder.html
package: velox.api.layer1.messages
classes: Layer1ApiSoundAlertMessage.Builder
methods: alertDeclarationId, Builder, setSound, setTextInfo, setShowPopup, setRepeatCount, setRepeatDelay, setStatusListener, setSource, setMetadata, setAlias, setPriority, setAdditionalInfo, setSeverityIcon, setAlertDeclarationId, build
---

# Layer1ApiSoundAlertMessage.Builder

**Package:** velox.api.layer1.messages

**Type:** Class (static final)

**Enclosing class:** [`Layer1ApiSoundAlertMessage`](Layer1ApiSoundAlertMessage.html)

**Inheritance:** `java.lang.Object` → `velox.api.layer1.messages.Layer1ApiSoundAlertMessage.Builder`

## Description

Builder to build [`Layer1ApiSoundAlertMessage`](Layer1ApiSoundAlertMessage.html).

## Fields

### alertDeclarationId

```java
public String alertDeclarationId
```

## Constructors

### Builder

```java
public Builder(Layer1ApiSoundAlertMessage message)
```

Create a builder with fields prepopulated from the specified [`Layer1ApiSoundAlertMessage`](Layer1ApiSoundAlertMessage.html). Useful if you want to create a message with the same ID and/or slightly changed fields.

## Methods

### setSound

```java
public Layer1ApiSoundAlertMessage.Builder setSound(byte[] sound)
```

### setTextInfo

```java
public Layer1ApiSoundAlertMessage.Builder setTextInfo(String textInfo)
```

### setShowPopup

```java
public Layer1ApiSoundAlertMessage.Builder setShowPopup(boolean showPopup)
```

### setRepeatCount

```java
public Layer1ApiSoundAlertMessage.Builder setRepeatCount(long repeatCount)
```

### setRepeatDelay

```java
public Layer1ApiSoundAlertMessage.Builder setRepeatDelay(Duration repeatDelay)
```

### setStatusListener

```java
public Layer1ApiSoundAlertMessage.Builder setStatusListener(Layer1ApiSoundAlertMessage.SoundAlertStatusListener statusListener)
```

### setSource

```java
public Layer1ApiSoundAlertMessage.Builder setSource(Class<?> source)
```

### setMetadata

```java
public Layer1ApiSoundAlertMessage.Builder setMetadata(Object metadata)
```

### setAlias

```java
public Layer1ApiSoundAlertMessage.Builder setAlias(String alias)
```

### setPriority

```java
public Layer1ApiSoundAlertMessage.Builder setPriority(int priority)
```

### setAdditionalInfo

```java
public Layer1ApiSoundAlertMessage.Builder setAdditionalInfo(String additionalInfo)
```

### setSeverityIcon

```java
public Layer1ApiSoundAlertMessage.Builder setSeverityIcon(Image icon)
```

### setAlertDeclarationId

```java
public Layer1ApiSoundAlertMessage.Builder setAlertDeclarationId(String alertDeclarationId)
```

### build

```java
public Layer1ApiSoundAlertMessage build()
```