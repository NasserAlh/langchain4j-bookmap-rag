---
source_file: Layer1ActionMapper.html
package: velox.api.layer1.actions
classes: Layer1ActionMapper
methods: getActionsContainer
---

# Layer1ActionMapper

**Package:** velox.api.layer1.actions

**Type:** Interface

## Description

Implement this interface if a strategy wants to register external actions `Layer1ExternalAction` in Bookmap action system.

Bookmap looks for this interface when loading strategies. Once it is found, `getActionsContainer()` method will be called.

## Methods

### getActionsContainer

```java
Layer1ActionsContainer getActionsContainer()
```

It's the entry point for action registration.

**Returns:** `Layer1ActionsContainer` instance that contains action list