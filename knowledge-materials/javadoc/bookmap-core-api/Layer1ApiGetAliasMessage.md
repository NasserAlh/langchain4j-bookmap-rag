---
source_file: Layer1ApiGetAliasMessage.html
package: velox.api.layer1.messages
classes: Layer1ApiGetAliasMessage
methods: symbol, exchange, type, Layer1ApiGetAliasMessage, Layer1ApiGetAliasMessage
---

# Layer1ApiGetAliasMessage

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → Layer1ApiGetAliasMessage

## Description

Request for alias generation based on symbol, exchange, type.

A provider should respond with what is the unique alias for an instrument with this symbol/exchange/type.

If a provider doesn't recognize this instrument, it can respond with `null`, or return a *unique* value that could be an alias for the instrument if it would exist: e.g. if your alias is always in \<symbol\>.\<exchange\> format, then you can generate it in the same way. In this case, the provider should also be able to handle subsequent requests related to this symbol, like order executions during cross-trading - i.e. gracefully reject an attempt to send orders for such non-existent instrument.

Return String alias or `null`

## Fields

### symbol

```java
public final String symbol
```

### exchange

```java
public final String exchange
```

### type

```java
public final String type
```

## Constructors

### Layer1ApiGetAliasMessage

```java
public Layer1ApiGetAliasMessage(String symbol, String exchange, String type)
```

### Layer1ApiGetAliasMessage

```java
public Layer1ApiGetAliasMessage(InstrumentCoreInfo instrumentInfo)
```