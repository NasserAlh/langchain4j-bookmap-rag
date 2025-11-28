---
source_file: Layer1ApiUserMessageNotification.html
package: velox.api.layer1.messages
classes: Layer1ApiUserMessageNotification
methods: RESERVEDIDHISTORICALDATA, alias, text, id, isAdd, minWidthPx, onMessageClosedCallback, Layer1ApiUserMessageNotification, getNextId, toString
---

# Layer1ApiUserMessageNotification

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → Layer1ApiUserMessageNotification

**All Implemented Interfaces:** velox.api.layer1.messages.EchoMessage

## Description

Can be used to print some text over chart with optional close icon.  
If message with `id` was already present, displayed message will be updated.  
Messages need to be removed with this message (note that you need to do this in response for close icon pressed as well).

## Fields

### RESERVED_ID_HISTORICAL_DATA

```java
public static final long RESERVED_ID_HISTORICAL_DATA
```

### alias

```java
public final String alias
```

### text

```java
public final String text
```

### id

```java
public final long id
```

### isAdd

```java
public final boolean isAdd
```

### minWidthPx

```java
public final int minWidthPx
```

### onMessageClosedCallback

```java
public final Runnable onMessageClosedCallback
```

## Constructors

### Layer1ApiUserMessageNotification

```java
public Layer1ApiUserMessageNotification(String alias, long id, String text, boolean isAdd, int minWidthPx, Runnable onMessageClosedCallback)
```

**Parameters:**
- `alias` - Target alias of message, or null if notification is for all aliases
- `id` - Unique id of message (use `getNextId()` to generate it first time)
- `text` - 
- `isAdd` - If true, message is displayed (if message with this id is already displayed - it will be updated). Otherwise message with this id will be removed
- `minWidthPx` - Minimum width required to paint this string
- `onMessageClosedCallback` - If not null, message will have cross icon, and callback will be called when it's pressed

## Methods

### getNextId

```java
public static long getNextId()
```

**Returns:** Unique id

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`