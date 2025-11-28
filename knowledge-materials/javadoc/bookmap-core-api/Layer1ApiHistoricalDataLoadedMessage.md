---
source_file: Layer1ApiHistoricalDataLoadedMessage.html
package: velox.api.layer1.messages
classes: Layer1ApiHistoricalDataLoadedMessage
methods: alias, Layer1ApiHistoricalDataLoadedMessage
---

# Layer1ApiHistoricalDataLoadedMessage

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → Layer1ApiHistoricalDataLoadedMessage

**All Implemented Interfaces:** EchoMessage, Layer1ApiIgnorableUpwardMessage

## Description

Historical data for this alias have been loaded.
If any data (like sum of trades at time) was cached for this instrument, it may not be valid now.

## Fields

### alias

```java
public final String alias
```

## Constructors

### Layer1ApiHistoricalDataLoadedMessage

```java
public Layer1ApiHistoricalDataLoadedMessage(String alias)
```