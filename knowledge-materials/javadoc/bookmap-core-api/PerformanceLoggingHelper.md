---
source_file: PerformanceLoggingHelper.html
package: velox.api.layer1.utils
classes: PerformanceLoggingHelper
methods: PerformanceLoggingHelper, logPerformance, setLogPerformanceRunnable
---

# PerformanceLoggingHelper

**Package:** velox.api.layer1.utils

**Type:** Class

**Inheritance:** java.lang.Object → PerformanceLoggingHelper

## Description

Helper class used to request a performance log record. Can be used

## Constructors

### PerformanceLoggingHelper

```java
public PerformanceLoggingHelper()
```

## Methods

### logPerformance

```java
public static void logPerformance()
```

Call this if something is lagging for unknown reason and you want to know if that's because CPU is fully utilized or because of something else, like network. Will make bookmap measure cpu usage and print into the log file. Note: measurement will start shortly after you request it, but it will take some time to complete, meaning log record will represent CPU usage after the call, not before it.

### setLogPerformanceRunnable

```java
public static void setLogPerformanceRunnable(Runnable logPerformanceRunnable)
```

**Internal** method used to set handler for performance logging request. API modules should not use it.

**Parameters:**
- `logPerformanceRunnable` - Runnable that will handle performance logging

---

**Methods inherited from class java.lang.Object:** clone, equals, finalize, getClass, hashCode, notify, notifyAll, toString, wait, wait, wait