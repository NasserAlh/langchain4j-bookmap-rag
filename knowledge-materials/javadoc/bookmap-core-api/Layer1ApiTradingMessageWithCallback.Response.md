---
source_file: Layer1ApiTradingMessageWithCallback.Response.html
package: velox.api.layer1.messages
classes: Layer1ApiTradingMessageWithCallback.Response
methods: SUCCESS, STATEWASNOTCHANGED, ACTIONREJECTEDBYUSER, INSTRUMENTNOTOPEN, TRADINGISDISABLED, TRADINGCANNOTBEENABLED, ACTIONREJECTED, values, valueOf
---

# Layer1ApiTradingMessageWithCallback.Response

**Package:** velox.api.layer1.messages

**Type:** Enum

**Enclosing class:** `Layer1ApiTradingMessageWithCallback`

**Inheritance:**
- `java.lang.Object`
- `java.lang.Enum<Layer1ApiTradingMessageWithCallback.Response>`
- `velox.api.layer1.messages.Layer1ApiTradingMessageWithCallback.Response`

**All Implemented Interfaces:** `Serializable`, `Comparable<Layer1ApiTradingMessageWithCallback.Response>`, `Constable`

## Description

The response, which can be received via `Layer1ApiTradingMessageWithCallback.TradingMessageResponseListener`

## Enum Constants

### SUCCESS

```java
public static final Layer1ApiTradingMessageWithCallback.Response SUCCESS
```

The message was successfully processed

### STATE_WAS_NOT_CHANGED

```java
public static final Layer1ApiTradingMessageWithCallback.Response STATE_WAS_NOT_CHANGED
```

The message was processed, but the state didn't change

### ACTION_REJECTED_BY_USER

```java
public static final Layer1ApiTradingMessageWithCallback.Response ACTION_REJECTED_BY_USER
```

The user rejected the changes requested by this message

### INSTRUMENT_NOT_OPEN

```java
public static final Layer1ApiTradingMessageWithCallback.Response INSTRUMENT_NOT_OPEN
```

The instrument is not visible in the main window or in separate windows. Changes can be made to the visible instrument

### TRADING_IS_DISABLED

```java
public static final Layer1ApiTradingMessageWithCallback.Response TRADING_IS_DISABLED
```

Trading is disabled for this instrument

### TRADING_CANNOT_BE_ENABLED

```java
public static final Layer1ApiTradingMessageWithCallback.Response TRADING_CANNOT_BE_ENABLED
```

Trading cannot be enabled for this instrument

### ACTION_REJECTED

```java
public static final Layer1ApiTradingMessageWithCallback.Response ACTION_REJECTED
```

The changes were rejected for other reasons

## Methods

### values

```java
public static Layer1ApiTradingMessageWithCallback.Response[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** An array containing the constants of this enum class, in the order they are declared

### valueOf

```java
public static Layer1ApiTradingMessageWithCallback.Response valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - The name of the enum constant to be returned

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null