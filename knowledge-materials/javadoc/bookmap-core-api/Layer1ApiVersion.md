---
source_file: Layer1ApiVersion.html
package: velox.api.layer1.annotations
classes: Layer1ApiVersion
methods: value
---

# Layer1ApiVersion

**Package:** velox.api.layer1.annotations

**Type:** Annotation Interface

```java
@Retention(RUNTIME)
@Target(TYPE)
public @interface Layer1ApiVersion
```

## Description

Accepts integer value specifying API version that was used to write this module. Only the entry point should be marked with this. When breaking changes are made to the API bookmap will try to use compatibility mechanism in order to keep code written for older versions running.

**Note, that if no annotation is specified `Layer1ApiVersionValue.VERSION0` will be assumed by default, which is probably not version you want to use if you are writing a new module.**

## Required Elements

### value

```java
Layer1ApiVersionValue value()
```