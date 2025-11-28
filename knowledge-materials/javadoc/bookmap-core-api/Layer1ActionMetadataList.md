---
source_file: Layer1ActionMetadataList.html
package: velox.api.layer1.actions.annotations
classes: Layer1ActionMetadataList
methods: value
---

# Layer1ActionMetadataList

**Package:** velox.api.layer1.actions.annotations

**Type:** Annotation Interface

```java
@Target(TYPE)
@Retention(RUNTIME)
public @interface Layer1ActionMetadataList
```

## Description

Allows declaring multiple [`Layer1ActionMetadata`](Layer1ActionMetadata.html) annotations over action class.

## Required Elements

### value

```java
Layer1ActionMetadata[] value()
```