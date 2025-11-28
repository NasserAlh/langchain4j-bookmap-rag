---
source_file: guides/GettingStarted.md
type: guide
topic: getting-started, minimum-requirements, annotations
package: velox.api.layer1.simplified
related_classes: CustomModule, CustomModuleAdapter
annotations: Layer1SimpleAttachable, Layer1ApiVersion, Layer1StrategyName, Layer1TradingStrategy
---

# Getting Started with Bookmap Add-On Development

This guide covers the minimum requirements and essential concepts for creating Bookmap add-ons using the Simplified API.

## Important Note

When creating a Bookmap add-on, it's important to understand that it operates as part of the larger Bookmap platform. This means that typical standalone Java entry points, such as `public static void main(String[] args)`, are not used or allowed. Instead, the add-on lifecycle is managed by Bookmap itself.

## Minimum Requirements

### Required Annotations

To create a Bookmap add-on, your class must include:

| Annotation | Purpose |
|------------|---------|
| `@Layer1SimpleAttachable` | Makes the add-on attachable to instruments |
| `@Layer1ApiVersion` | Specifies API version compatibility |
| `@Layer1StrategyName` | Display name shown in Bookmap UI |
| `@Layer1TradingStrategy` | Required for add-ons that place orders |

### Required Implementation

Implement one of:
- `CustomModule` interface - requires implementing `initialize()` and `stop()` methods
- `CustomModuleAdapter` adapter - provides empty default implementations (recommended for simple add-ons)

## Basic Examples

### Minimal Add-On

The simplest possible Bookmap add-on:

```java
@Layer1SimpleAttachable
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
public class HelloBookmapApiMinimum implements CustomModuleAdapter {
}
```

### Add-On with Logging

A basic add-on that logs initialization and shutdown:

```java
@Layer1SimpleAttachable
@Layer1StrategyName("Hello Bookmap API with logs")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)
public class HelloBookmapApiWithLogs implements CustomModule {

    @Override
    public void initialize(String alias, InstrumentInfo info, Api api, InitialState initialState) {
        Log.info("Hello");
    }

    @Override
    public void stop() {
        Log.info("Bye");
    }
}
```

## Lifecycle Methods

### initialize()

Called before any other method. Parameters:
- `alias` - Host instrument (the one where checkbox was checked)
- `info` - Instrument info (may contain extra data in subclasses)
- `api` - Object for interacting with Bookmap
- `initialState` - Additional information such as last trade price

### stop()

Called before unloading the module. Use this to:
- Release any allocated resources
- Stop any background threads
- Clean up GUI components

## See Also

- [Data Listeners](DataListeners.md) - Receiving market data
- [Api Interface](ApiInterface.md) - Interacting with Bookmap
- [Javadoc: CustomModule](../javadoc/bookmap-simplified-api-documentation-2025-11-20/CustomModule.md)
- [Javadoc: CustomModuleAdapter](../javadoc/bookmap-simplified-api-documentation-2025-11-20/CustomModuleAdapter.md)
