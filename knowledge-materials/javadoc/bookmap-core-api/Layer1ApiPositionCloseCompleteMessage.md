---
source_file: Layer1ApiPositionCloseCompleteMessage.html
package: velox.api.layer1.messages
classes: Layer1ApiPositionCloseCompleteMessage
methods: alias, Layer1ApiPositionCloseCompleteMessage, toString
---

# Layer1ApiPositionCloseCompleteMessage

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.messages.Layer1ApiPositionCloseCompleteMessage

## Description

Notify bookmap that previous order sent with `SimpleOrderSendParameters.closingPositionHint` was processed successfully.

## Fields

### alias

```java
public final String alias
```

## Constructors

### Layer1ApiPositionCloseCompleteMessage

```java
public Layer1ApiPositionCloseCompleteMessage(String alias)
```

## Methods

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`

---

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