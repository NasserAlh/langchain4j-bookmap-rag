---
source_file: UpdateCustomIndicatorMessage.html
package: velox.api.layer1.messages
classes: UpdateCustomIndicatorMessage
methods: getIndicatorName, getIndicatorValue
---

# UpdateCustomIndicatorMessage

**Package:** velox.api.layer1.messages

**Type:** Interface

## Description

Message should implement this interface if it wants to update an existing custom indicator.

## Methods

### getIndicatorName

```java
String getIndicatorName()
```

**Returns:** Name of indicator that should be updated

### getIndicatorValue

```java
double getIndicatorValue()
```

**Returns:** New value for this indicator