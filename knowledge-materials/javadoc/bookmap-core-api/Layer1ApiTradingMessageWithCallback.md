---
source_file: Layer1ApiTradingMessageWithCallback.html
package: velox.api.layer1.messages
classes: Layer1ApiTradingMessageWithCallback
methods: Response, TradingMessageResponseListener, listener, alias, Layer1ApiTradingMessageWithCallback
---

# Layer1ApiTradingMessageWithCallback

**Package:** velox.api.layer1.messages

**Type:** Class

**Modifiers:** `public abstract`

**Inheritance:** `java.lang.Object` → `Layer1ApiTradingMessageWithCallback`

**Direct Known Subclasses:** `Layer1ApiChangeTradingModeMessage`, `Layer1ApiSetOrderSizeMessage`

## Nested Classes

### Response

```java
static enum Layer1ApiTradingMessageWithCallback.Response
```

The response, which can be received via `Layer1ApiTradingMessageWithCallback.TradingMessageResponseListener`

### TradingMessageResponseListener

```java
static interface Layer1ApiTradingMessageWithCallback.TradingMessageResponseListener
```

Is used to receive a `Layer1ApiTradingMessageWithCallback.Response` after `Layer1ApiTradingMessageWithCallback` was processed

## Fields

### listener

```java
public final Layer1ApiTradingMessageWithCallback.TradingMessageResponseListener listener
```

### alias

```java
public final String alias
```

## Constructors

### Layer1ApiTradingMessageWithCallback

```java
public Layer1ApiTradingMessageWithCallback(Layer1ApiTradingMessageWithCallback.TradingMessageResponseListener listener, String alias)
```

**Parameters:**
- `listener` - See `Layer1ApiTradingMessageWithCallback.TradingMessageResponseListener`. The listener cannot be null
- `alias` - The changes will be applied only to an instrument, that is currently open