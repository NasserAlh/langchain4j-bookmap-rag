---
source_file: WidgetDisplayInfo.html
package: velox.api.layer1.messages.indicators
classes: WidgetDisplayInfo
methods: type, centerValue, WidgetDisplayInfo
---

# WidgetDisplayInfo

**Package:** velox.api.layer1.messages.indicators

**Type:** Class

**Inheritance:** java.lang.Object → WidgetDisplayInfo

## Description

Defines the way widget is displaying values.

By default widgets fills counter clockwise (if circle) or up (if bar). Another way is for widget to be symmetric, with specified value as center.

## Nested Classes

- `WidgetDisplayInfo.Type` (enum)

## Fields

### type

```java
public final WidgetDisplayInfo.Type type
```

### centerValue

```java
public final double centerValue
```

## Constructors

### WidgetDisplayInfo

```java
public WidgetDisplayInfo(WidgetDisplayInfo.Type type, double centerValue)
```

**Parameters:**
- `type` - 
- `centerValue` - Value of center point if type is SYMMETRIC, any value otherwise