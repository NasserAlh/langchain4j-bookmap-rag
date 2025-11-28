---
source_file: LoginFailedReason.html
package: velox.api.layer1.data
classes: LoginFailedReason
methods: WRONGCREDENTIALS, REENTERCREDENTIALS, NOINTERNETCONNECTION, NOLOCALCONNECTION, SIMULTANEOUSCONNECTIONKICKABLE, TRADINGPASSWORDEXPIRED, NEWTRADINGPASSWORDFAILED, UNKNOWN, FATAL, values, valueOf
---

# LoginFailedReason

**Package:** velox.api.layer1.data

**Type:** Enum

## Inheritance

- java.lang.Object
- java.lang.Enum<LoginFailedReason>
- velox.api.layer1.data.LoginFailedReason

**All Implemented Interfaces:** Serializable, Comparable<LoginFailedReason>, Constable

## Description

Reason why login failed

## Enum Constants

### WRONG_CREDENTIALS

```java
public static final LoginFailedReason WRONG_CREDENTIALS
```

Server didn't accept credentials

### REENTER_CREDENTIALS

```java
public static final LoginFailedReason REENTER_CREDENTIALS
```

Enter credentials again (the same ones). Happens because user credentials are not stored inside provider.

### NO_INTERNET_CONNECTION

```java
public static final LoginFailedReason NO_INTERNET_CONNECTION
```

Can not login because there is no connection to server(s) through the internet.

### NO_LOCAL_CONNECTION

```java
public static final LoginFailedReason NO_LOCAL_CONNECTION
```

Can not login because there is no connection to server located on the same machine.

### SIMULTANEOUS_CONNECTION_KICKABLE

```java
public static final LoginFailedReason SIMULTANEOUS_CONNECTION_KICKABLE
```

Simultaneous session that can be kicked

### TRADING_PASSWORD_EXPIRED

```java
@Deprecated
public static final LoginFailedReason TRADING_PASSWORD_EXPIRED
```

**Deprecated**

### NEW_TRADING_PASSWORD_FAILED

```java
@Deprecated
public static final LoginFailedReason NEW_TRADING_PASSWORD_FAILED
```

**Deprecated**

### UNKNOWN

```java
public static final LoginFailedReason UNKNOWN
```

Unknown problem. Try to login again - probably it will work.

### FATAL

```java
public static final LoginFailedReason FATAL
```

Fatal problem. No point in trying again until issue is resolved

## Methods

### values

```java
public static LoginFailedReason[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** an array containing the constants of this enum class, in the order they are declared

### valueOf

```java
public static LoginFailedReason valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - the name of the enum constant to be returned

**Returns:** the enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - if this enum class has no constant with the specified name
- `NullPointerException` - if the argument is null