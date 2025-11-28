---
source_file: NanoClock.html
package: velox.api.layer1.common
classes: NanoClock
methods: NanoClock, currentTimeNanos
---

# NanoClock

**Package:** velox.api.layer1.common

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.common.NanoClock

## Description

Provides high precision timestamps. It's around `System.currentTimeMillis() * 1000_000`, but it increases smoothly between neighboring milliseconds.

## Constructors

### NanoClock

```java
public NanoClock()
```

## Methods

### currentTimeNanos

```java
public static long currentTimeNanos()
```

Get current time in nanoseconds. Expected to be fast, less than ~50 nanoseconds per call if called sequentially (~33ns/call on Ryzen 7 2700X)

**Returns:** Current time in nanoseconds

---

**Methods inherited from class java.lang.Object:** `clone`, `equals`, `finalize`, `getClass`, `hashCode`, `notify`, `notifyAll`, `toString`, `wait`, `wait`, `wait`