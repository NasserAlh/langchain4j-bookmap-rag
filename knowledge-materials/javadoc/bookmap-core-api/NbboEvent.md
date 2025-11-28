---
source_file: NbboEvent.html
package: velox.api.layer1.messages
classes: NbboEvent
methods: alias, isBid, price, exchange, NbboEvent, NbboEvent
---

# NbboEvent

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.messages.NbboEvent

**All Implemented Interfaces:** velox.api.layer1.messages.Layer1ApiIgnorableUpwardMessage

## Fields

### alias

```java
public final String alias
```

### isBid

```java
public final boolean isBid
```

### price

```java
public final double price
```

### exchange

```java
public final String exchange
```

## Constructors

### NbboEvent

```java
public NbboEvent(String alias, boolean isBid, double price, String exchange)
```

### NbboEvent

```java
public NbboEvent(String alias, NbboEvent event)
```

## Methods Inherited

**From class java.lang.Object:**
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