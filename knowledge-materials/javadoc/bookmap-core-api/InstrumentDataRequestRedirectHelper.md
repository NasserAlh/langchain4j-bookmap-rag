---
source_file: InstrumentDataRequestRedirectHelper.html
package: velox.api.layer1.providers.helper
classes: InstrumentDataRequestRedirectHelper
methods: InstrumentDataRequestRedirectHelper, redirectUserMessage
---

# InstrumentDataRequestRedirectHelper

**Package:** velox.api.layer1.providers.helper

**Type:** Class

**Inheritance:** java.lang.Object → InstrumentDataRequestRedirectHelper

## Description

When creating synthetic instruments using `Layer1UpstreamDataEditor` bookmap will still try to query those instruments for certain things (like "which provider does it belong to?", "which order types are supported by that instrument").

This class provides helper methods to take care of that for you.

## Constructors

### InstrumentDataRequestRedirectHelper

```java
public InstrumentDataRequestRedirectHelper()
```

## Methods

### redirectUserMessage

```java
public static Object redirectUserMessage(Object data, Function<Object, Object> forwardFunction, Function<String, String> aliasMapper)
```

Should be used inside `Layer1ApiAdminProvider.sendUserMessage(Object)`, helps you to redirect requests.

**Parameters:**
- `data` - The original data object that you have received as part of request
- `forwardFunction` - Where to forward requests (usually to the lower layers, often will be something like `super::sendUserMessage`)
- `aliasMapper` - Your function that handles alias translation. If provided alias isn't created by your strategy - return null, otherwise provide some other alias to take that information from (e.g. if you are creating synthetic instruments that will often be one of underlying instruments)

**Returns:** Value that should be returned by `Layer1ApiAdminProvider.sendUserMessage(Object)`