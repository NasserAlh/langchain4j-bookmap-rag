---
source_file: UserMessageRewindBase.html
package: velox.api.layer1.messages
classes: UserMessageRewindBase
methods: aliasToOrderBooksMap, aliasToOrderBooksMboMap, UserMessageRewindBase
---

# UserMessageRewindBase

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → UserMessageRewindBase

## Description

When rewind happens this message will be sent.

## Fields

### aliasToOrderBooksMap

```java
public HashMap<String, OrderBook> aliasToOrderBooksMap
```

State after rewind for all known instrument's order books (MBP)

### aliasToOrderBooksMboMap

```java
public HashMap<String, OrderBookMbo> aliasToOrderBooksMboMap
```

State after rewind for all known instrument's order books (MBO)

## Constructors

### UserMessageRewindBase

```java
public UserMessageRewindBase()
```