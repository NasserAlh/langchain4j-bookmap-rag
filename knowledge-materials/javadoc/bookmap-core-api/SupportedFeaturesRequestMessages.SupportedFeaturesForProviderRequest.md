---
source_file: SupportedFeaturesRequestMessages.SupportedFeaturesForProviderRequest.html
package: velox.api.layer1.utils
classes: SupportedFeaturesRequestMessages.SupportedFeaturesForProviderRequest
methods: provider, SupportedFeaturesForProviderRequest
---

# SupportedFeaturesRequestMessages.SupportedFeaturesForProviderRequest

**Package:** velox.api.layer1.utils

**Type:** Class

**Enclosing class:** [`SupportedFeaturesRequestMessages`](SupportedFeaturesRequestMessages.html)

**Inheritance:** `java.lang.Object` → `SupportedFeaturesRequestMessages.SupportedFeaturesForProviderRequest`

## Description

Request for supported features for a specific provider.
For crosstrading, you are required to provide a symbolMappingFunction
and set trading to true.
See [`Layer1ApiProviderSupportedFeatures`](../data/Layer1ApiProviderSupportedFeatures.html) for more details.
You need to check the provider of this message to identify whether request is intended for your
strategy.

## Fields

### provider

```java
public final Layer1ApiProvider provider
```

## Constructors

### SupportedFeaturesForProviderRequest

```java
public SupportedFeaturesForProviderRequest(Layer1ApiProvider provider)
```