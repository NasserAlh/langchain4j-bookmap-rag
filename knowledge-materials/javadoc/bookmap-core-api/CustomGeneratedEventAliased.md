---
source_file: CustomGeneratedEventAliased.html
package: velox.api.layer1.layers.strategies.interfaces
classes: CustomGeneratedEventAliased
methods: event, alias, CustomGeneratedEventAliased, toString
---

# CustomGeneratedEventAliased

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Class

**Inheritance:** java.lang.Object → CustomGeneratedEventAliased

**All Implemented Interfaces:** Serializable

## Description

If you want to intercept this message, use instanceof instead of '==' operator.

**See Also:**
- Serialized Form

## Fields

### event

```java
public final CustomGeneratedEvent event
```

### alias

```java
public final String alias
```

## Constructors

### CustomGeneratedEventAliased

```java
public CustomGeneratedEventAliased(CustomGeneratedEvent event, String alias)
```

**Parameters:**
- `event` - Event that was generated
- `alias` - Either alias of instrument this event is related to, or null if this is global event

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