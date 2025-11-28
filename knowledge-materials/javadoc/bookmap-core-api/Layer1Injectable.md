---
source_file: Layer1Injectable.html
package: velox.api.layer1.annotations
classes: Layer1Injectable
---

# Layer1Injectable

**Package:** velox.api.layer1.annotations

**Type:** Annotation Interface

```java
@Retention(RUNTIME)
public @interface Layer1Injectable
```

## Description

Marks classes that can be loaded from GUI as a strategy. Class will be injected in layers chain.

When strategy is loaded and is correctly injected in layers chain, it will receive `UserMessageLayersChainCreatedTargeted` message via `Layer1ApiAdminListener.onUserMessage(Object)` (you need to implement it). After this, you can interact with `Layer1ApiProvider`, received in your constructor.

When strategy is no longer in layers chain, `Layer1ApiFinishable.finish()` will be called (you need to implement it). You can interact with `Layer1ApiProvider` inside `Layer1ApiFinishable.finish()` method. After method is completed, **you can not** interact with `Layer1ApiProvider`. Please note, that interacting with `Layer1ApiProvider` after `Layer1ApiFinishable.finish()` was called will result in exception.