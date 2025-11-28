---
source_file: StrategyUpdateGeneratorSkipper.html
package: velox.api.layer1.messages.indicators
classes: StrategyUpdateGeneratorSkipper
methods: skipToRealtimeRequested
---

# StrategyUpdateGeneratorSkipper

**Package:** velox.api.layer1.messages.indicators

**Type:** Interface

## Description

Allows generator to skip part of initial generation phase.

## Methods

### skipToRealtimeRequested

```java
boolean skipToRealtimeRequested()
```

If this method starts returning true generator calculation might skip to the end. It's especially relevant in replay for simplified indicators in mixed mode.

**Returns:** `boolean` - Whether to skip to realtime calculation