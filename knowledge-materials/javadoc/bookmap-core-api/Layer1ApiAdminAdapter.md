---
source_file: Layer1ApiAdminAdapter.html
package: velox.api.layer1
classes: Layer1ApiAdminAdapter
methods: onLoginFailed, onLoginSuccessful, onConnectionLost, onConnectionRestored, onSystemTextMessage, onUserMessage
extends: ** Layer1ApiAdminListener
---

# Layer1ApiAdminAdapter

**Package:** velox.api.layer1

**Type:** Interface

**All Superinterfaces:** Layer1ApiAdminListener

**All Known Subinterfaces:** Layer1ApiAdapter

## Description

Provides default empty implementations.

## Methods

### onLoginFailed

```java
default void onLoginFailed(LoginFailedReason reason, String message)
```

Failed to login with specified credentials

**Parameters:**
- `reason` - Reason code
- `message` - Text message associated with login fail (may be null)

### onLoginSuccessful

```java
default void onLoginSuccessful()
```

Successful login.

### onConnectionLost

```java
default void onConnectionLost(DisconnectionReason reason, String message)
```

Connection to server lost. Provider can call this method multiple times in sequence if the reason was changed (i.e. from `DisconnectionReason.NO_INTERNET` to `DisconnectionReason.FATAL`)

**Parameters:**
- `reason` - Reason code
- `message` - Text message associated with login disconnection (may be null)

### onConnectionRestored

```java
default void onConnectionRestored()
```

Connection to server restored. A provider should call it only if it previously invoked `Layer1ApiAdminListener.onConnectionLost(DisconnectionReason, String)`

### onSystemTextMessage

```java
default void onSystemTextMessage(String message, SystemTextMessageType messageType)
```

Passes the message into Bookmap and shows it as a popup. It is suitable for 1-time notifications, but for the general case take a look at the notifications API - `Layer1ApiSoundAlertMessage`

**Parameters:**
- `message` - Message itself
- `messageType` - Message type

**See Also:**
- `Layer1ApiSoundAlertMessage`

### onUserMessage

```java
default void onUserMessage(Object data)
```

Send an upstream event. Allows incorporating arbitrary functionality into protocol

**Parameters:**
- `data` - Message content