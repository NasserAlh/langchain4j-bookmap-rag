---
source_file: Layer1ApiSoundAlertDeclarationMessage.Builder.html
package: velox.api.layer1.messages
classes: Layer1ApiSoundAlertDeclarationMessage.Builder
methods: Builder, setTriggerDescription, setSource, setAliasMatcher, setPopupAllowed, setSoundAllowed, setRepeated, setIsAdd, build
---

# Layer1ApiSoundAlertDeclarationMessage.Builder

**Package:** velox.api.layer1.messages

**Type:** Class

**Enclosing class:** `Layer1ApiSoundAlertDeclarationMessage`

**Inheritance:** java.lang.Object → velox.api.layer1.messages.Layer1ApiSoundAlertDeclarationMessage.Builder

## Description

Public static final builder class for constructing `Layer1ApiSoundAlertDeclarationMessage` instances.

## Constructors

### Builder

```java
public Builder(Layer1ApiSoundAlertDeclarationMessage message)
```

Create a builder with fields prepopulated from the specified `Layer1ApiSoundAlertDeclarationMessage`. Useful if you want to modify an existing declaration, or remove a declaration from Bookmap by creating a message with the same **`Layer1ApiSoundAlertDeclarationMessage.id`** but **`Layer1ApiSoundAlertDeclarationMessage.isAdd` = false**

## Methods

### setTriggerDescription

```java
public Layer1ApiSoundAlertDeclarationMessage.Builder setTriggerDescription(String triggerDescription)
```

### setSource

```java
public Layer1ApiSoundAlertDeclarationMessage.Builder setSource(Class<?> source)
```

### setAliasMatcher

```java
public Layer1ApiSoundAlertDeclarationMessage.Builder setAliasMatcher(Predicate<String> aliasMatcher)
```

### setPopupAllowed

```java
public Layer1ApiSoundAlertDeclarationMessage.Builder setPopupAllowed(boolean popupAllowed)
```

### setSoundAllowed

```java
public Layer1ApiSoundAlertDeclarationMessage.Builder setSoundAllowed(boolean soundAllowed)
```

### setRepeated

```java
public Layer1ApiSoundAlertDeclarationMessage.Builder setRepeated(boolean repeated)
```

### setIsAdd

```java
public Layer1ApiSoundAlertDeclarationMessage.Builder setIsAdd(boolean isAdd)
```

### build

```java
public Layer1ApiSoundAlertDeclarationMessage build()
```