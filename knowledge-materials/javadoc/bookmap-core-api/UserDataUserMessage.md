---
source_file: UserDataUserMessage.html
package: velox.api.layer1.reading
classes: UserDataUserMessage
methods: tag, alias, data, UserDataUserMessage
---

# UserDataUserMessage

**Package:** velox.api.layer1.reading

**Type:** Class

**Inheritance:** java.lang.Object → UserDataUserMessage

**All Implemented Interfaces:** velox.api.layer1.messages.Layer1ApiIgnorableUpwardMessage

## Description

This class is used for sending arbitrary user data and storing it into feeds/historical data.

Messages with alias != null will be stored as instrument specific events and will be available both in replay and historical data.

Messages with alias == null will be stored as global events and will be available only in replay. **Currently such messages are not stored in historical data.**

You can use custom generators to get data from these messages stored in historical data/trees. See an example in UserDataUserMessageDemo from the **[DemoStrategies](https://github.com/BookmapAPI/DemoStrategies)** repo.

## Fields

### tag

```java
public final String tag
```

### alias

```java
public final String alias
```

### data

```java
public final byte[] data
```

## Constructors

### UserDataUserMessage

```java
public UserDataUserMessage(String tag, String alias, byte[] data)
```