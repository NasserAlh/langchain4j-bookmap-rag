---
source_file: GeneratedEventInfo.html
package: velox.api.layer1.messages
classes: GeneratedEventInfo
methods: valueClass, aggregationClass, aggregator, GeneratedEventInfo, GeneratedEventInfo
---

# GeneratedEventInfo

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → GeneratedEventInfo

## Description

NOTE: any event class can not be used by multiple generators

## Fields

### valueClass

```java
public Class<?> valueClass
```

### aggregationClass

```java
public Class<?> aggregationClass
```

### aggregator

```java
public CustomEventAggregatble aggregator
```

## Constructors

### GeneratedEventInfo

```java
public GeneratedEventInfo(Class<?> valueClass)
```

**Parameters:**
- `valueClass` - Update class without any aggregations

### GeneratedEventInfo

```java
public GeneratedEventInfo(Class<?> valueClass, Class<?> aggregationClass, CustomEventAggregatble aggregator)
```

**Parameters:**
- `valueClass` - Class of value
- `aggregationClass` - Class of aggregations. Should be null if no aggregations is required
- `aggregator` - Rules of aggregations. Should operate on classes valueClass and aggregationClass. Should be null if no aggregations is required