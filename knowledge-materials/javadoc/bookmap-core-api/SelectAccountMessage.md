---
source_file: SelectAccountMessage.html
package: velox.api.layer1.messages
classes: SelectAccountMessage
methods: accountsInfo, SelectAccountMessage, set, get
---

# SelectAccountMessage

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → SelectAccountMessage

## Description

This message requests account selection. Should be sent via `Layer1ApiAdminListener.onUserMessage(Object)`

## Fields

### accountsInfo

```java
public final AccountInfo[] accountsInfo
```

Array of accounts to select from

## Constructors

### SelectAccountMessage

```java
public SelectAccountMessage(AccountInfo[] accountsInfo)
```

Constructs new account selection request

**Parameters:**
- `accountsInfo` - Accounts to select from

## Methods

### set

```java
public void set(int selectedIndex)
```

Sets selected account index after selection is performed. Should be called once. This unblocks all pending `get()` calls and prevents blocking further ones.

**Parameters:**
- `selectedIndex` - Index in `accountsInfo` array

### get

```java
public int get() throws InterruptedException
```

Waits till selection is done and returns index of account that was selected. Can be called from multiple places.

**Returns:** Index of selected account in array passed to `SelectAccountMessage(AccountInfo[])` and available as `accountsInfo`

**Throws:**
- `InterruptedException` - If the current thread is interrupted while waiting