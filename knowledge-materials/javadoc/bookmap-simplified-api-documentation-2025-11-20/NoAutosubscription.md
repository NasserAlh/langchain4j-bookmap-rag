---
source_file: NoAutosubscription.html
package: velox.api.layer1.simplified
classes: NoAutosubscription
---

# NoAutosubscription

**Package:** velox.api.layer1.simplified

**Type:** Annotation Interface

```java
@Retention(RUNTIME)
@Target(TYPE)
public @interface NoAutosubscription
```

## Description

Cancels auto-subscription to all available listeners for the user's strategy. When applied, a user should add listeners manually via [`Api`](Api.html).