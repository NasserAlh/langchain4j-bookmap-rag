---
source_file: OrderRepresentationHelper.html
package: velox.api.layer1.common.helper
classes: OrderRepresentationHelper
methods: OrderRepresentationHelper.PriceFormatter, OrderRepresentationHelper, textRepresentation
---

# OrderRepresentationHelper

**Package:** velox.api.layer1.common.helper

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.common.helper.OrderRepresentationHelper

## Nested Classes

### OrderRepresentationHelper.PriceFormatter

**Type:** Interface

## Constructors

### OrderRepresentationHelper

```java
public OrderRepresentationHelper()
```

## Methods

### textRepresentation

```java
public static String textRepresentation(SimpleOrderSendParameters order, OrderRepresentationHelper.PriceFormatter formatter, double sizeMultiplier)
```

Converts order to string representation (can include html tags).

**Parameters:**
- `order` - Order to be represented as string
- `formatter` - Function converting price to string
- `sizeMultiplier` - Size multiplier for order size to be displayed

**Returns:** String representation of the order

---

**Methods inherited from class java.lang.Object:**
- `clone()`
- `equals(Object)`
- `finalize()`
- `getClass()`
- `hashCode()`
- `notify()`
- `notifyAll()`
- `toString()`
- `wait()`
- `wait(long)`
- `wait(long, int)`