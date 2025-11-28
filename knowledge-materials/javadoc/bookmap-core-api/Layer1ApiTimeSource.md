---
source_file: Layer1ApiTimeSource.html
package: velox.api.layer1.layers
classes: Layer1ApiTimeSource
methods: Layer1ApiTimeSource.Layer1TimeSourceShortcutAllowed, getTimeSource, getTimeSource
---

# Layer1ApiTimeSource

**Package:** velox.api.layer1.layers

**Type:** Interface

**All Known Implementing Classes:** Layer1ApiDepthFreezer, Layer1ApiInjectorRelay, Layer1ApiRelay, Layer1ApiStrategiesEchoMessagesLayer

## Description

Allows taking a shortcut when responding to `Layer1ApiAdminProvider.getCurrentTime()`. See `Layer1ApiRelay.getTimeSource()` to understand the mechanism. In short: even if layer implemented `Layer1ApiAdminProvider.getCurrentTime()` it can still allow a shortcut by implementing this interface.

## Nested Classes

### Layer1ApiTimeSource.Layer1TimeSourceShortcutAllowed

```java
@interface Layer1ApiTimeSource.Layer1TimeSourceShortcutAllowed
```

Mark `Layer1ApiAdminProvider.getCurrentTime()` implementation with this to confirm you are supporting the shortcut.

## Methods

### getTimeSource

```java
Layer1ApiProvider getTimeSource()
```

### getTimeSource

```java
static Layer1ApiProvider getTimeSource(Layer1ApiProvider provider)
```