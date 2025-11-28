---
source_file: MaximumSupportedFeatures.html
package: velox.api.layer1.annotations
classes: MaximumSupportedFeatures
---

# MaximumSupportedFeatures

**Package:** velox.api.layer1.annotations

**Type:** Annotation Interface

```java
@Retention(RUNTIME)
@Target(METHOD)
public @interface MaximumSupportedFeatures
```

## Description

Similar to `Layer1ApiAdminProvider.getSupportedFeatures()`, but is used to declare provider capabilities before provider is actually instantiated. Fields should normally contain the broadest possible functionality (e.g. if trading is only supported with some logins it should be set to true here).

This annotation only makes sense for providers and should be used together with `Layer1ApiAdminProvider.getSupportedFeatures()`. Mark static method that will return an instance of `Layer1ApiProviderSupportedFeatures` with this annotation.

Some functionality (such as list of known instruments) might be impossible to know at this point, in which case just return something that makes sense (e.g. do not set known instruments).