---
source_file: TimeAdapter.html
package: velox.api.layer1.simplified
classes: TimeAdapter
methods: onTimestamp
extends: TimeListener
---

# TimeAdapter

**Package:** velox.api.layer1.simplified

**Type:** Interface

**All Superinterfaces:** TimeListener

## Description

An adapter for `TimeListener` with empty default method implementations.

## Methods

### onTimestamp

```java
default void onTimestamp(long t)
```

Time of the next event(s)

**Parameters:**
- `t` - Time in nanoseconds