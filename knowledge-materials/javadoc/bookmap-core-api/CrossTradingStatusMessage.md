---
source_file: CrossTradingStatusMessage.html
package: velox.api.layer1.messages
classes: CrossTradingStatusMessage
methods: hostAlias, targetAlias, status, targetInstrumentInfo, CrossTradingStatusMessage, CrossTradingStatusMessage, CrossTradingStatusMessage, clone, toString
---

# CrossTradingStatusMessage

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.messages.CrossTradingStatusMessage

**All Implemented Interfaces:** Layer1ApiStrategiesEchoMessagesLayer.StrategyEchoMessageFromGui, Layer1ApiIgnorableUpwardMessage

## Description

Received by add-ons and providers when cross-trading starts/stops through `Layer1ApiAdminListener.onUserMessage(Object)` for add-ons and through `Layer1ApiAdminProvider.sendUserMessage(Object)` for providers.

Note that if the target instrument is not subscribed, isDepthFull in InstrumentInfo of target instrument will be false regardless of it's value in the actual InstrumentInfo.

## Fields

### hostAlias

```java
public final String hostAlias
```

The instrument from which cross-trading happens (from which user send orders)

### targetAlias

```java
public final String targetAlias
```

The instrument on which cross-trading happens (on which orders are executed)

**Note:** providers should receive this alias without provider type (i.e. if full target alias is "M6BH3.CME@RITHMIC" the provider should receive "M6BH3.CME")

### status

```java
public final CrossTradingStatus status
```

### targetInstrumentInfo

```java
public final InstrumentInfo targetInstrumentInfo
```

## Constructors

### CrossTradingStatusMessage

```java
public CrossTradingStatusMessage(String hostAlias, String targetAlias, CrossTradingStatus status, InstrumentInfo targetInstrumentInfo)
```

**Parameters:**
- `hostAlias` - The instrument from which cross-trading happens (from which user send orders)
- `targetAlias` - The instrument on which cross-trading happens (on which orders are executed)
- `status` - Current cross-trading status
- `targetInstrumentInfo` - The Info of the target instrument

**See Also:**
- `CrossTradingStatus`

### CrossTradingStatusMessage

```java
public CrossTradingStatusMessage(String hostAlias, String targetAlias, CrossTradingStatus status)
```

### CrossTradingStatusMessage

```java
public CrossTradingStatusMessage(CrossTradingStatusMessage event)
```

## Methods

### clone

```java
public Object clone()
```

**Overrides:** `clone` in class `Object`

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`