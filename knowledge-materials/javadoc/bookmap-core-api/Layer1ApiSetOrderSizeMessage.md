---
source_file: Layer1ApiSetOrderSizeMessage.html
package: velox.api.layer1.messages
classes: Layer1ApiSetOrderSizeMessage
methods: ORDERSIZEMINVALUE, ORDERSIZEMAXVALUE, orderSize, Layer1ApiSetOrderSizeMessage, toString
---

# Layer1ApiSetOrderSizeMessage

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.messages.Layer1ApiTradingMessageWithCallback → Layer1ApiSetOrderSizeMessage

## Description

Requests to change the order size in the Trading Control Panel.

This message is only processed if it was sent from a trading strategy (see `Layer1TradingStrategy`).

Works only when the Trading Control Panel is enabled.

## Fields

### ORDER_SIZE_MIN_VALUE

```java
public static final int ORDER_SIZE_MIN_VALUE
```

### ORDER_SIZE_MAX_VALUE

```java
public static final int ORDER_SIZE_MAX_VALUE
```

### orderSize

```java
public final int orderSize
```

## Constructors

### Layer1ApiSetOrderSizeMessage

```java
public Layer1ApiSetOrderSizeMessage(String alias, int orderSize, Layer1ApiTradingMessageWithCallback.TradingMessageResponseListener listener)
```

**Parameters:**
- `orderSize` - To be applied in the Trading Control Panel. The value should be between `ORDER_SIZE_MIN_VALUE` and `ORDER_SIZE_MAX_VALUE`
  **Note that the `InstrumentInfo.sizeMultiplier` of the instrument will be applied to the value!**

## Methods

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`