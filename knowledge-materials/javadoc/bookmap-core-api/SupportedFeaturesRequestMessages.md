---
source_file: SupportedFeaturesRequestMessages.html
package: velox.api.layer1.utils
classes: SupportedFeaturesRequestMessages
methods: SupportedFeaturesRequestMessages.SupportedFeaturesForProviderRequest, SupportedFeaturesRequestMessages
---

# SupportedFeaturesRequestMessages

**Package:** velox.api.layer1.utils

**Type:** Class

**Inheritance:** java.lang.Object → SupportedFeaturesRequestMessages

## Description

Message requests for supported features. Strategy that want to allow crosstrading from their instruments should catch this messages and return supported features.

## Nested Classes

### SupportedFeaturesRequestMessages.SupportedFeaturesForProviderRequest

Request for supported features for a specific provider.

## Constructors

### SupportedFeaturesRequestMessages

```java
public SupportedFeaturesRequestMessages()
```

## Methods Inherited from java.lang.Object

```java
clone(), equals(Object), finalize(), getClass(), hashCode(), notify(), notifyAll(), toString(), wait(), wait(long), wait(long, int)
```