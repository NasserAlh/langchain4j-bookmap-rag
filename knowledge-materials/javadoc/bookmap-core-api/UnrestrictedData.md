---
source_file: UnrestrictedData.html
package: velox.api.layer1.annotations
classes: UnrestrictedData
---

# UnrestrictedData

**Package:** velox.api.layer1.annotations

**Type:** Annotation Interface

```java
@Retention(RUNTIME)
public @interface UnrestrictedData
```

## Description

Bookmap will only trust this annotation if module is signed. Tells Bookmap that this module is either purely an indicator or falls under other exceptions which lift data restrictions (e.g. it can run on DxFeed data).