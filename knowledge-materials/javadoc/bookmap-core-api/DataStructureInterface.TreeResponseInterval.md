---
source_file: DataStructureInterface.TreeResponseInterval.html
package: velox.api.layer1.messages.indicators
classes: DataStructureInterface.TreeResponseInterval
methods: events, TreeResponseInterval
---

# DataStructureInterface.TreeResponseInterval

**Package:** velox.api.layer1.messages.indicators

**Type:** Class (static)

**Enclosing Interface:** `DataStructureInterface`

**Inheritance:** `java.lang.Object` → `DataStructureInterface.TreeResponseInterval`

## Description

Tree structure response for given interval

## Fields

### events

```java
public Map<String, Object> events
```

For standard events key is a string value of `DataStructureInterface.StandardEvents`. Value is a tree response of a corresponding type (for standard events see `DataStructureInterface.StandardEvents`)

For custom generated events with aggregation, key is string value of event non aggregated (value) class `GeneratedEventInfo.valueClass`, and value will be an object of `GeneratedEventInfo.aggregationClass`

## Constructors

### TreeResponseInterval

```java
public TreeResponseInterval()
```