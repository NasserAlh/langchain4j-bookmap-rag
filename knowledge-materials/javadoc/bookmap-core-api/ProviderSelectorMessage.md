---
source_file: ProviderSelectorMessage.html
package: velox.api.layer1.providers.data
classes: ProviderSelectorMessage
methods: sourceProviderType, ProviderSelectorMessage, createProviderSelectorMessage, getProviderProgrammaticName
---

# ProviderSelectorMessage

**Package:** velox.api.layer1.providers.data

**Type:** Class

**Inheritance:** java.lang.Object → ProviderSelectorMessage

## Description

The message specifies that the next action relates to a specific provider (where it can't be derived from other data).

Examples of such events: `Layer1ApiAdminListener.onLoginSuccessful()` or `Layer1ApiInstrumentListener.onInstrumentAdded(String, InstrumentInfo)`

Here is how an addon can listen for `Layer1ApiAdminListener.onConnectionLost(DisconnectionReason, String)` - note how the `sourceProvider` is updated in `onUserMessage(Object data)` and later read in `onConnectionLost(DisconnectionReason reason, String message)`

```java
String sourceProvider;

@Override
public void onUserMessage(Object data) {
    if (data instanceof ProviderSelectorMessage) {
        ProviderSelectorMessage message = (ProviderSelectorMessage) data;
        sourceProvider = message.getProviderProgrammaticName();
    }
    super.onUserMessage(data);
}

@Override
public void onConnectionLost(DisconnectionReason reason, String message) {
    super.onConnectionLost(reason, message);
    // You can do something more useful then simply logging the provider name
    Log.info("Connection lost for provider: " + sourceProvider);
}
```

## Fields

### sourceProviderType

```java
public final velox.api.layer1.data.ProviderType sourceProviderType
```

This field is not intended for public API usage. Use `getProviderProgrammaticName()` instead.

## Constructors

### ProviderSelectorMessage

```java
public ProviderSelectorMessage(velox.api.layer1.data.ProviderType sourceProviderType)
```

DO NOT use this directly, use `ProviderType.getProviderSelectorMessage()` instead - this way you'll avoid creating a new object every time.

**Parameters:**
- `sourceProviderType` - 

## Methods

### createProviderSelectorMessage

```java
public static final ProviderSelectorMessage createProviderSelectorMessage(velox.api.layer1.data.ProviderType providerType)
```

Returns provider selector messages of specific type. Objects are reused - same message is returned for same type.

### getProviderProgrammaticName

```java
public String getProviderProgrammaticName()
```

**Returns:** Unique string representation of a provider that caused an event, or `null` if the underlying provider is `null`