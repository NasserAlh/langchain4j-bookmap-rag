---
source_file: Layer1ApiAdminProvider.html
package: velox.api.layer1
classes: Layer1ApiAdminProvider
methods: getSupportedFeatures, login, getCurrentTime, getSource, sendUserMessage, close
extends: ** java.lang.AutoCloseable, Layer1ApiAdminListenable
---

# Layer1ApiAdminProvider

**Package:** velox.api.layer1

**Type:** Interface

**All Superinterfaces:** java.lang.AutoCloseable, Layer1ApiAdminListenable

**All Known Subinterfaces:** Layer1ApiProvider

**All Known Implementing Classes:** velox.api.layer0.live.ExternalLiveBaseProvider, velox.api.layer0.replay.ExternalReaderBaseProvider, velox.api.layer1.layers.Layer1ApiDepthFreezer, velox.api.layer1.layers.Layer1ApiInjectorRelay, velox.api.layer1.layers.Layer1ApiRelay, velox.api.layer1.layers.Layer1ApiStrategiesEchoMessagesLayer

## Description

Class implementing this should provide general Layer1 Api functions.

## Methods

### getSupportedFeatures

```java
Layer1ApiProviderSupportedFeatures getSupportedFeatures()
```

Get information about features that are supported by a provider. Note that some fields might be set differently depending on when request is made. E.g. adapter might not know if trading will be there until login actually happens.

If you are writing a provider - take a look at `MaximumSupportedFeatures` as a way to declare full list of capabilities before being instantiated.

**Returns:** Object describing supported features

**See Also:**
- `MaximumSupportedFeatures`

### login

```java
void login(LoginData loginData)
```

Initiate login to the platform.

**Parameters:**
- `loginData` - Credentials, different platforms use different subclasses

### getCurrentTime

```java
long getCurrentTime()
```

Returns current Unix epoch time. This method allows time distortions if consumer is too slow - in this case time of the next event may be returned.

**Returns:** Current Unix epoch time in nanoseconds

### getSource

```java
String getSource()
```

Returns string representing data source.

**Returns:** String representing data source

### sendUserMessage

```java
@Nullable Object sendUserMessage(Object data)
```

Send a downstream event. Allows incorporating arbitrary functionality into protocol.

**Parameters:**
- `data` - Message content

**Returns:** Response object or null

### close

```java
void close()
```

Shut down and dispose. For details on the provider lifecycle, check out the javadoc of `ExternalLiveBaseProvider` and `ExternalReaderBaseProvider` for Live and Replay external modules respectively.

**Specified by:** `close` in interface `java.lang.AutoCloseable`

## Inherited Methods from Layer1ApiAdminListenable

```java
void addListener(Layer1ApiAdminListener listener)
void removeListener(Layer1ApiAdminListener listener)
```