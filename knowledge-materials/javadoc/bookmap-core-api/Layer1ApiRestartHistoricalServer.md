---
source_file: Layer1ApiRestartHistoricalServer.html
package: velox.api.layer1.messages.historicalserver
classes: Layer1ApiRestartHistoricalServer
methods: Layer1ApiRestartHistoricalServer, Layer1ApiRestartHistoricalServer, getWaitBeforeRestartMs
---

# Layer1ApiRestartHistoricalServer

**Package:** velox.api.layer1.messages.historicalserver

**Type:** Class

**Inheritance:** java.lang.Object → Layer1ApiRestartHistoricalServer

## Description

This message can be used if you need to restart server due to some error, like failing to obtain instrument list from provider due to network error.

Note that you can specify time to wait before restart happens (none by default)

## Constructors

### Layer1ApiRestartHistoricalServer

```java
public Layer1ApiRestartHistoricalServer()
```

### Layer1ApiRestartHistoricalServer

```java
public Layer1ApiRestartHistoricalServer(long waitBeforeRestartMs)
```

## Methods

### getWaitBeforeRestartMs

```java
public long getWaitBeforeRestartMs()
```