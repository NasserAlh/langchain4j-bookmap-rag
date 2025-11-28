---
source_file: OnlineCalculatable.ValueBundle.html
package: velox.api.layer1.layers.strategies.interfaces
classes: OnlineCalculatable.ValueBundle
methods: value, additionalObjects, ValueBundle, toString
---

# OnlineCalculatable.ValueBundle

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Class

**Enclosing Interface:** [`OnlineCalculatable`](OnlineCalculatable.html)

**Inheritance:** `java.lang.Object` → `OnlineCalculatable.ValueBundle`

## Fields

### value

```java
public Object value
```

### additionalObjects

```java
public List<Object> additionalObjects
```

## Constructors

### ValueBundle

```java
public ValueBundle(Object value, List<Object> additionalObjects)
```

**Parameters:**
- `value` - Representation of this pixel state (currently accepts `Double`, `OnlineCalculatable.Marker`, `List<OnlineCalculatable.Marker>` or `OnlineCalculatable.ValueBundle`)
- `additionalObjects` - That will be processed by renderer. Currently existing objects: `VerticalLine` draws a vertical line on chart

## Methods

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`

**Methods inherited from class `java.lang.Object`:**
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