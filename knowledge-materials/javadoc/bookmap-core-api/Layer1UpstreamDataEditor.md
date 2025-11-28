---
source_file: Layer1UpstreamDataEditor.html
package: velox.api.layer1.annotations
classes: Layer1UpstreamDataEditor
---

# Layer1UpstreamDataEditor

**Package:** velox.api.layer1.annotations

**Type:** Annotation Interface

```java
@Retention(RUNTIME)
public @interface Layer1UpstreamDataEditor
```

## Description

Tells bookmap that strategy will be modifying upstream data (e.g. creating synthetic instruments(see also [`Layer1SyntheticInstrumentProvider`](../utils/Layer1SyntheticInstrumentProvider.html)). Such strategy will only work in live mode. Significant part of API functionality won't work in this mode.