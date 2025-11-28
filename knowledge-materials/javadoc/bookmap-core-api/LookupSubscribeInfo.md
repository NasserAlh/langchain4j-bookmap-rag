---
source_file: LookupSubscribeInfo.html
package: velox.api.layer1.data
classes: LookupSubscribeInfo
methods: description, enabled, LookupSubscribeInfo, LookupSubscribeInfo
---

# LookupSubscribeInfo

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.data.SubscribeInfo → LookupSubscribeInfo

## Description

Subscription info for subscription dialog lookup dropdown

## Fields

### description

```java
public final String description
```

This description will be shown at lookup dropdown items

### enabled

```java
public final boolean enabled
```

Marks that lookup item should be enabled

**Fields inherited from velox.api.layer1.data.SubscribeInfo:**
- `exchange`
- `symbol` 
- `type`

## Constructors

### LookupSubscribeInfo

```java
public LookupSubscribeInfo(String symbol, String exchange, String type, String description)
```

### LookupSubscribeInfo

```java
public LookupSubscribeInfo(String symbol, String exchange, String type, String description, boolean enabled)
```

**Methods inherited from velox.api.layer1.data.SubscribeInfo:**
- `equals(Object)`
- `hashCode()`
- `toString()`

**Methods inherited from java.lang.Object:**
- `clone()`
- `finalize()`
- `getClass()`
- `notify()`
- `notifyAll()`
- `wait()`
- `wait(long)`
- `wait(long, int)`