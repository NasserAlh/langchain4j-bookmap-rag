---
source_file: Layer1ApiAdminListener.html
package: velox.api.layer1
classes: Layer1ApiAdminListener
methods: onLoginFailed, onLoginSuccessful, onConnectionLost, onConnectionRestored, onSystemTextMessage, onUserMessage
---

# Layer1ApiAdminListener

**Package:** velox.api.layer1

**Type:** Interface

**All Known Subinterfaces:** Layer1ApiAdapter, Layer1ApiAdminAdapter, Layer1ApiListener

**All Known Implementing Classes:** Layer1ApiDepthFreezer, Layer1ApiInjectorRelay, Layer1ApiRelay, Layer1ApiStrategiesEchoMessagesLayer, Layer1ApiUpstreamRelay

## Description

Listens to general events, such as login results, disconnections, warnings, etc.

## Methods

### onLoginFailed

```java
void onLoginFailed(LoginFailedReason reason, @Nullable String message)
```

Failed to login with specified credentials

**Parameters:**
- `reason` - Reason code
- `message` - Text message associated with login fail (may be null)

### onLoginSuccessful

```java
void onLoginSuccessful()
```

Successful login.

### onConnectionLost

```java
void onConnectionLost(DisconnectionReason reason, @Nullable String message)
```

Connection to server lost. Provider can call this method multiple times in sequence if the reason was changed (i.e. from `DisconnectionReason.NO_INTERNET` to `DisconnectionReason.FATAL`)

**Parameters:**
- `reason` - Reason code
- `message` - Text message associated with login disconnection (may be null)

### onConnectionRestored

```java
void onConnectionRestored()
```

Connection to server restored. A provider should call it only if it previously invoked `onConnectionLost(DisconnectionReason, String)`

### onSystemTextMessage

```java
void onSystemTextMessage(String message, SystemTextMessageType messageType)
```

Passes the message into Bookmap and shows it as a popup. It is suitable for 1-time notifications, but for the general case take a look at the notifications API - `Layer1ApiSoundAlertMessage`

**Parameters:**
- `message` - Message itself
- `messageType` - Message type

**See Also:**
- `Layer1ApiSoundAlertMessage`

### onUserMessage

```java
void onUserMessage(Object data)
```

Send an upstream event. Allows incorporating arbitrary functionality into protocol

**Parameters:**
- `data` - Message content