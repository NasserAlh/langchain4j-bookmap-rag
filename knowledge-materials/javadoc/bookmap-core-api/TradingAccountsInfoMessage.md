---
source_file: TradingAccountsInfoMessage.html
package: velox.api.layer1.messages
classes: TradingAccountsInfoMessage
methods: alias, accountInfo, isAdd, TradingAccountsInfoMessage, toString
---

# TradingAccountsInfoMessage

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.messages.TradingAccountsInfoMessage

## Description

Message containing an account info for one of the accounts of the instrument. Each instrument can have multiple accounts, so this message can be sent multiple times for the same instrument.

This message should be sent by the provider (if any trading accounts are available) for each instrument alias the provider has subscription for, using `Layer1ApiAdminListener.onUserMessage(Object)`.

If provider receives `TradingOnlySubscribe` message, it should send `TradingAccountsInfoMessage` in response for the instrument if it supports multi-account trading.

This message can be received by the strategy in `Layer1ApiAdminListener.onUserMessage(Object)`.  
To handle this message you can use `AccountListManager` to track any account information.  
You can also handle this message by yourself, if you don't want to use `AccountListManager`.

**See Also:**
- `Layer1MultiAccountTradingSupported`
- `Layer1ApiTradingListener`

## Fields

### alias

```java
public final String alias
```

Alias of the instrument the account relates to.

Alias should be null (with isAdd=true) to notify about account information for the provider before any instrument is subscribed, if such information is available before the subscription to any instrument (i.e. just connected to the provider).

If a message was previously sent with `alias = null` and `isAdd = true` before any instrument subscription, a message with `alias = null` and `isAdd = false` should be sent when disconnecting from the provider to indicate the removal of account information.

Sending a message with `alias = null` and `isAdd = false` will remove the account information for the provider (for all aliases). This message can be sent to remove account information even if no prior message with `alias = null` and `isAdd = true` was sent during the connection.

On provider disconnect, the provider should remove all account information for all aliases. This can be achieved by sending messages with `alias = null` and `isAdd = false` to remove accounts for all aliases, as described above.

When unsubscribing from a single instrument, the provider should remove the account information only for that specific instrument by specifying the specific `alias` of the instrument in this message.

### accountInfo

```java
public final AccountInfo accountInfo
```

Account info for the account of the instrument.

### isAdd

```java
public final boolean isAdd
```

If true, account information for the instrument has just been added, if false - account is no longer available.

## Constructors

### TradingAccountsInfoMessage

```java
public TradingAccountsInfoMessage(String alias, AccountInfo accountInfo, boolean isAdd)
```

**Parameters:**
- `alias` - Alias of the instrument the account relates to. Can be null to notify about account information before any instrument is subscribed.
- `accountInfo` - Account info for the account of the instrument
- `isAdd` - If true, account information for the instrument has just been added, if false - account is no longer available

## Methods

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`