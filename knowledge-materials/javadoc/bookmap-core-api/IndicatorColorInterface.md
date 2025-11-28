---
source_file: IndicatorColorInterface.html
package: velox.api.layer1.messages.indicators
classes: IndicatorColorInterface
methods: set, getOrDefault, addColorChangeListener
---

# IndicatorColorInterface

**Package:** velox.api.layer1.messages.indicators

**Type:** Interface

## Description

Interface to get/set colors for this strategy. Names are the same as used when declaring the color.

## Methods

### set

```java
void set(String name, Color color)
```

### getOrDefault

```java
Color getOrDefault(String name, Color defaultValue)
```

### addColorChangeListener

```java
void addColorChangeListener(ColorsChangedListener listener)
```

Add listener that will be notified every time colors are changed. Note that there is no need to remove listener - weak links are used.