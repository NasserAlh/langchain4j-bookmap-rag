---
source_file: WidgetRules.html
package: velox.api.layer1.simplified
classes: WidgetRules
methods: WidgetRules, WidgetRules, WidgetRules, WidgetRules, WidgetRules, WidgetRules, WidgetRules, getForcedMin, getForcedMax, getLifeSpan, setLifeSpan, getWidgetDisplayInfo, isSymmetrical, toString, apply
---

# WidgetRules

**Package:** velox.api.layer1.simplified

**Type:** Class

**Inheritance:** java.lang.Object → WidgetRules

## Constructors

### WidgetRules

```java
public WidgetRules()
```

### WidgetRules

```java
public WidgetRules(double min, double max, long lifeSpan)
```

### WidgetRules

```java
public WidgetRules(long lifeSpan)
```

### WidgetRules

```java
public WidgetRules(double min, double max)
```

### WidgetRules

```java
public WidgetRules(double min, double max, long lifeSpan, velox.api.layer1.messages.indicators.WidgetDisplayInfo widgetDisplayInfo)
```

### WidgetRules

```java
public WidgetRules(long lifeSpan, velox.api.layer1.messages.indicators.WidgetDisplayInfo widgetDisplayInfo)
```

### WidgetRules

```java
public WidgetRules(double min, double max, velox.api.layer1.messages.indicators.WidgetDisplayInfo widgetDisplayInfo)
```

## Methods

### getForcedMin

```java
public double getForcedMin()
```

### getForcedMax

```java
public double getForcedMax()
```

### getLifeSpan

```java
public long getLifeSpan()
```

### setLifeSpan

```java
public void setLifeSpan(long lifeSpan)
```

### getWidgetDisplayInfo

```java
public velox.api.layer1.messages.indicators.WidgetDisplayInfo getWidgetDisplayInfo()
```

### isSymmetrical

```java
public boolean isSymmetrical()
```

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`

### apply

```java
public org.apache.commons.lang3.tuple.Pair<Double, Double> apply(double min, double max)
```

## Inherited Methods

**Methods inherited from class java.lang.Object:**
- `clone`
- `equals`
- `finalize`
- `getClass`
- `hashCode`
- `notify`
- `notifyAll`
- `wait`
- `wait(long)`
- `wait(long, int)`