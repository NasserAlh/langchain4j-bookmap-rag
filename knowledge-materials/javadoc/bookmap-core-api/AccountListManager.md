---
source_file: AccountListManager.html
package: velox.api.layer1.common.helper
classes: AccountListManager
methods: AccountListManager, getAccounts, getPrimaryAccountByProvider, getProviderOtherAccountsByAccountId, getProvidersWithMultiAccounts, getAccountById, getAccountsByAlias, getAliasesByAccountId, getAccountByOrderId, isPrimaryAccountOrNull, isPrimaryAccount, isPrimaryAccountOrderOrNull, isPrimaryAccountOrder, onUserMessage, generateMessagesFromAccountsList, onOrderUpdated
---

# AccountListManager

**Package:** velox.api.layer1.common.helper

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.common.helper.AccountListManager

## Description

Helper class to manage account list, map instrument aliases to account and map orders to accounts.

To use this class, you need to call `onUserMessage(Object)` with any user message you receive in `Layer1ApiAdminListener.onUserMessage(Object)` (needed message will be picked by this class itself)

To be able to map orders to accounts, you need to call `onOrderUpdated(OrderInfoUpdate)` with any order updates you receive in `Layer1ApiTradingListener.onOrderUpdated(OrderInfoUpdate)`.

This class is thread-safe.

## Constructors

### AccountListManager

```java
public AccountListManager()
```

## Methods

### getAccounts

```java
Set<AccountInfo> getAccounts()
```

Returns all available accounts.

**Returns:** Account info set

### getPrimaryAccountByProvider

```java
AccountInfo getPrimaryAccountByProvider(String providerProgrammaticName)
```

Returns primary account info for the provider.

**Parameters:**
- `providerProgrammaticName` - Provider programmatic name for the account. See `AccountInfo.providerProgrammaticName`

**Returns:** Primary account info for the provider or null if not found

**See Also:**
- `AccountInfo.providerProgrammaticName`
- `ProviderTypeHelper`

### getProviderOtherAccountsByAccountId

```java
List<AccountInfo> getProviderOtherAccountsByAccountId(String accountId, boolean includePrimary)
```

Returns all accounts for the provider by the id of one of the provider accounts (e.g. if the provider has multiple accounts, you can get all of them by the id of one of them).

**Parameters:**
- `accountId` - Account id of one of the provider accounts
- `includePrimary` - Whether to include primary account to the result

**Returns:** List of all accounts for the provider by the id of one of the provider accounts

### getProvidersWithMultiAccounts

```java
Set<String> getProvidersWithMultiAccounts()
```

Returns all providers with multiple accounts (providers are represented by their programmatic name).

**Returns:** Set of providers programmatic name for providers with multiple accounts

**See Also:**
- `AccountInfo.providerProgrammaticName`
- `ProviderTypeHelper`

### getAccountById

```java
AccountInfo getAccountById(String accountId)
```

Returns account info by account id.

**Parameters:**
- `accountId` - Account id

**Returns:** Account info or null if not found

### getAccountsByAlias

```java
Set<AccountInfo> getAccountsByAlias(String alias)
```

Returns all account infos for instrument alias.

**Parameters:**
- `alias` - Instrument alias

**Returns:** Account info set

### getAliasesByAccountId

```java
Set<String> getAliasesByAccountId(String accountId)
```

Returns the list of all currently subscribed instrument aliases for account id.

**Parameters:**
- `accountId` - Account id

**Returns:** Set of instrument aliases for given account id

### getAccountByOrderId

```java
AccountInfo getAccountByOrderId(String orderId)
```

Returns account info by order id.

**Parameters:**
- `orderId` - Order id

**Returns:** Account info or null if not found

### isPrimaryAccountOrNull

```java
Boolean isPrimaryAccountOrNull(String accountId)
```

Checks if the account is primary by account id (null is considered primary). If the account is not found returns null.

**Parameters:**
- `accountId` - Account id

**Returns:** True if the account is primary, false otherwise or null if the account is not found

### isPrimaryAccount

```java
boolean isPrimaryAccount(String accountId)
```

Checks if the account is primary by account id (null is considered primary). If the account is not found returns true.

**Parameters:**
- `accountId` - Account id

**Returns:** True if the account is primary, false otherwise

### isPrimaryAccountOrderOrNull

```java
Boolean isPrimaryAccountOrderOrNull(String orderId)
```

Checks if the account is primary by order id. If the account or order is not found returns null.

**Parameters:**
- `orderId` - Order id

**Returns:** True if the account is primary, false otherwise or null if the account or order is not found

### isPrimaryAccountOrder

```java
boolean isPrimaryAccountOrder(String orderId)
```

Checks if the account is primary by order id. If the account or order is not found returns true.

**Parameters:**
- `orderId` - Order id

**Returns:** True if the account is primary, false otherwise

### onUserMessage

```java
boolean onUserMessage(Object message)
```

Takes any user messages while accepts and processes only `TradingAccountsInfoMessage` to update the account list. Should be called from `Layer1ApiAdminListener.onUserMessage(Object)` like this:

```java
AccountListManager accountListManager = new AccountListManager();
// ...
@Override
void onUserMessage(Object data) {
    accountListManager.onUserMessage(data);

    // your code to handle any other messages here ...
}
```

You can check if the account list has been updated by checking the return value.

**Parameters:**
- `message` - Any user message received by `Layer1ApiAdminListener.onUserMessage(Object)`

**Returns:** True if the account list has any updates (new account added/removed or account has new alias added/removed, or primary account was changed), false otherwise

### generateMessagesFromAccountsList

```java
List<TradingAccountsInfoMessage> generateMessagesFromAccountsList()
```

Generates messages from the accounts list as if they were received from the provider (with isAdd=true for each).

**Returns:** List of messages for each account

### onOrderUpdated

```java
void onOrderUpdated(OrderInfoUpdate orderInfoUpdate)
```

Takes order update messages to map orders to the accounts. Should be called from `Layer1ApiTradingListener.onOrderUpdated(OrderInfoUpdate)` like this:

```java
AccountListManager accountListManager = new AccountListManager();
// ...
@Override
void onOrderUpdated(OrderInfoUpdate orderInfoUpdate) {
    accountListManager.onOrderUpdated(data);

    // your code to handle orderInfoUpdate the way you want ...
}
```

Note: if you don't need to know account info for any order, no need to call this method.

**Parameters:**
- `orderInfoUpdate` - Order info update message with account id (if specified)