---
source_file: Layer1ApiUserMessageReadCatchupDataFinished.html
package: velox.api.layer1.messages
classes: Layer1ApiUserMessageReadCatchupDataFinished
methods: generatorFullName, reason, Layer1ApiUserMessageReadCatchupDataFinished
---

# Layer1ApiUserMessageReadCatchupDataFinished

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → Layer1ApiUserMessageReadCatchupDataFinished

**All Implemented Interfaces:** velox.api.layer1.messages.Layer1ApiIgnorableUpwardMessage

## Description

This message is generated after a reading of catch up data is finished. The possible use case is to notify the addon that a particular generator has finished historical data calculation.

## Nested Classes

- `enum Layer1ApiUserMessageReadCatchupDataFinished.Reason`

## Fields

### generatorFullName

```java
public final String generatorFullName
```

### reason

```java
public final Layer1ApiUserMessageReadCatchupDataFinished.Reason reason
```

## Constructors

### Layer1ApiUserMessageReadCatchupDataFinished

```java
public Layer1ApiUserMessageReadCatchupDataFinished(String generatorFullName, Layer1ApiUserMessageReadCatchupDataFinished.Reason reason)
```

## Methods Inherited

**From class java.lang.Object:**
- `clone()`
- `equals(Object)`
- `finalize()`
- `getClass()`
- `hashCode()`
- `notify()`
- `notifyAll()`
- `toString()`
- `wait()`
- `wait(long)`
- `wait(long, int)`