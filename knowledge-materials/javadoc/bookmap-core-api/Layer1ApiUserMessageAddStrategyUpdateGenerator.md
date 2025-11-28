---
source_file: Layer1ApiUserMessageAddStrategyUpdateGenerator.html
package: velox.api.layer1.messages
classes: Layer1ApiUserMessageAddStrategyUpdateGenerator
methods: isAdd, shouldReceiveHistory, shouldReceiveBackfilledData, generator, strategyClass, generatorName, fullName, info, isAggregatable, Layer1ApiUserMessageAddStrategyUpdateGenerator, Layer1ApiUserMessageAddStrategyUpdateGenerator
---

# Layer1ApiUserMessageAddStrategyUpdateGenerator

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → Layer1ApiUserMessageAddStrategyUpdateGenerator

**All Implemented Interfaces:** velox.api.layer1.messages.Layer1ApiIgnorableUpwardMessage

## Description

Used to add/remove custom update generators

**Important**: use `sendUserMessage` when sending from `Layer1Attachable`, `Layer1SimpleAttachable` or `Layer1Injectable` but use `onUserMessage` from `Layer1UpstreamDataEditor`

When generator is added with this message, custom events will be generated for past history - this can take some time.

**See Also:**
- `Layer1ApiUserMessageReadCatchupDataFinished`

## Fields

### isAdd

```java
public final boolean isAdd
```

Generator will be added if true
Removed if false

### shouldReceiveHistory

```java
public final boolean shouldReceiveHistory
```

Indicates whether this generator requests historical data.
If true, loading this generator in replay mode will stop playing until all data from beginning is played to this generator so it could generate complete history

### shouldReceiveBackfilledData

```java
public final boolean shouldReceiveBackfilledData
```

Indicates whether this generator requests the entire available history or only since instrument was subscribed.

If true it will receive data that is backfilled after initial instrument subscription is established, but only if data is already in the data structures by the moment generator is running. You might have to re-register generator in response to Layer1ApiHistoricalDataLoadedMessage.

This flag works in conjunction with `shouldReceiveHistory` and it will be ignored if `shouldReceiveHistory` is false

### generator

```java
public final StrategyUpdateGenerator generator
```

### strategyClass

```java
public final Class<?> strategyClass
```

### generatorName

```java
public final String generatorName
```

### fullName

```java
public final String fullName
```

Name of data structure that will hold updates from this generator
This is corresponding to name of data structure holding this information and should be used to retrieve information in future

### info

```java
public final GeneratedEventInfo[] info
```

### isAggregatable

```java
public final boolean isAggregatable
```

Description of classes and aggregation rules of all events that this generator can generated and want to store in data structure

## Constructors

### Layer1ApiUserMessageAddStrategyUpdateGenerator

```java
public Layer1ApiUserMessageAddStrategyUpdateGenerator(Class<?> strategyClass, String generatorName, boolean isAdd, boolean shouldReceiveHistory, boolean shouldReceiveBackfilledData, StrategyUpdateGenerator generator, GeneratedEventInfo[] info)
```

**Parameters:**
- `strategyClass` - Class of strategy that is creating generator
- `generatorName` - Inner name of generator (should be unique within strategy)
- `isAdd` - True if generator is to be added, false if generator is to be removed
- `shouldReceiveHistory` - Indicates whether this generator requests historical data. See `shouldReceiveHistory`
- `shouldReceiveBackfilledData` - Receives data that is backfilled after initial instrument. See `shouldReceiveBackfilledData`
- `generator` - Generator to add/remove
- `info` - Description of classes and aggregation rules of all events that this generator can generated and want to store in data structure
  Condition should be met: all events are either aggregatable (with provided aggregator class), or non aggregatable
  If you need both type, register 2 separate generators
  All listed events are stored in same tree, so time of request for one or for all of them at once will be roughly the same
  NOTE: any event class can not be used by multiple generators

### Layer1ApiUserMessageAddStrategyUpdateGenerator

```java
public Layer1ApiUserMessageAddStrategyUpdateGenerator(Class<?> strategyClass, String generatorName, boolean isAdd, boolean shouldReceiveHistory, StrategyUpdateGenerator generator, GeneratedEventInfo[] info)
```