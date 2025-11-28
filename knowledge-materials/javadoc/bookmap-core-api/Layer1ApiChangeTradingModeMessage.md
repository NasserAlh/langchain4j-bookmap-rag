---
source_file: Layer1ApiChangeTradingModeMessage.html
package: velox.api.layer1.messages
classes: Layer1ApiChangeTradingModeMessage
methods: Layer1ApiChangeTradingModeMessage.Mode, mode, enable, Layer1ApiChangeTradingModeMessage, toString
---

# Layer1ApiChangeTradingModeMessage

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.messages.Layer1ApiTradingMessageWithCallback → Layer1ApiChangeTradingModeMessage

## Description

Request to change the trading mode in the Trading Control Panel.

This message is only processed if it was sent from a trading strategy (see `Layer1TradingStrategy`).

## Nested Classes

### Layer1ApiChangeTradingModeMessage.Mode

```java
static enum Layer1ApiChangeTradingModeMessage.Mode
```

The mode to be enabled or disabled in the Trade Control Panel

**Inherited Nested Classes:**
- `Layer1ApiTradingMessageWithCallback.Response`
- `Layer1ApiTradingMessageWithCallback.TradingMessageResponseListener`

## Fields

### mode

```java
public final Layer1ApiChangeTradingModeMessage.Mode mode
```

### enable

```java
public final boolean enable
```

**Inherited Fields:**
- `alias`
- `listener`

## Constructors

### Layer1ApiChangeTradingModeMessage

```java
public Layer1ApiChangeTradingModeMessage(String alias, boolean enable, Layer1ApiChangeTradingModeMessage.Mode mode, Layer1ApiTradingMessageWithCallback.TradingMessageResponseListener listener)
```

**Parameters:**
- `enable` - Defines if the mode to be enabled or disabled
- `mode` - See `Layer1ApiChangeTradingModeMessage.Mode`

## Methods

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`

**Inherited Methods from java.lang.Object:**
- `clone`, `equals`, `finalize`, `getClass`, `hashCode`, `notify`, `notifyAll`, `wait`, `wait`, `wait`