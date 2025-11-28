---
source_file: SoundSynthHelper.html
package: velox.api.layer1.layers.utils
classes: SoundSynthHelper
methods: SoundSynthHelper, initialize, waitUntilInitialized, synthesize, say
---

# SoundSynthHelper

**Package:** velox.api.layer1.layers.utils

**Type:** Class

**Inheritance:** java.lang.Object → SoundSynthHelper

## Description

Wrapper around TTS library. Use this if you need to generate sound from text.

## Constructors

### SoundSynthHelper

```java
public SoundSynthHelper()
```

## Methods

### initialize

```java
public static void initialize()
```

Perform initialization. Called once during startup. **Add-ons should not call it.**

### waitUntilInitialized

```java
public static void waitUntilInitialized() throws InterruptedException
```

Wait for initialization to complete. **Not relevant for add-ons, by the time add-on is loaded it's already complete.**

**Throws:**
- `InterruptedException`

### synthesize

```java
public static byte[] synthesize(String text)
```

Prepare sound from text

**Parameters:**
- `text` - Will be converted into sound

**Returns:** Bytes that can be fed to `AudioSystem.getAudioFileFormat(java.io.InputStream)` (after wrapping in `ByteArrayInputStream`)

### say

```java
public static void say(String text)
```

Synthesize and say immediately. Mostly useful for debug. For addons it's better to use `Layer1ApiSoundAlertMessage` and `synthesize(String)` instead (this will prevent overlapping, for example)

**Parameters:**
- `text` - Will be converted into sound