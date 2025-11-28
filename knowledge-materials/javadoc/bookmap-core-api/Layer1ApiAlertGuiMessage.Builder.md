---
source_file: Layer1ApiAlertGuiMessage.Builder.html
package: velox.api.layer1.messages
classes: Layer1ApiAlertGuiMessage.Builder
methods: Builder, setSource, setGuiPanelsProvider, setIsAdd, build
---

# Layer1ApiAlertGuiMessage.Builder

**Package:** velox.api.layer1.messages

**Type:** Class

**Enclosing class:** [`Layer1ApiAlertGuiMessage`](Layer1ApiAlertGuiMessage.html)

**Inheritance:** `java.lang.Object` → `velox.api.layer1.messages.Layer1ApiAlertGuiMessage.Builder`

## Constructors

### Builder

```java
public Builder(Layer1ApiAlertGuiMessage message)
```

Create a builder with fields prepopulated from the specified [`Layer1ApiAlertGuiMessage`](Layer1ApiAlertGuiMessage.html).

## Methods

### setSource

```java
public Layer1ApiAlertGuiMessage.Builder setSource(Class<?> source)
```

### setGuiPanelsProvider

```java
public Layer1ApiAlertGuiMessage.Builder setGuiPanelsProvider(Function<Layer1ApiSoundAlertDeclarationMessage, StrategyPanel[]> guiPanelsProvider)
```

### setIsAdd

```java
public Layer1ApiAlertGuiMessage.Builder setIsAdd(boolean isAdd)
```

### build

```java
public Layer1ApiAlertGuiMessage build()
```