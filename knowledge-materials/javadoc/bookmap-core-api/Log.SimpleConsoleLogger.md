---
source_file: Log.SimpleConsoleLogger.html
package: velox.api.layer1.common
classes: Log.SimpleConsoleLogger
methods: SimpleConsoleLogger, log
---

# Log.SimpleConsoleLogger

**Package:** velox.api.layer1.common

**Type:** Class

**Enclosing class:** Log

**Inheritance:** java.lang.Object → Log.SimpleConsoleLogger

**All Implemented Interfaces:** Log.LogListener

## Description

Static nested class that implements LogListener interface for simple console logging.

## Constructors

### SimpleConsoleLogger

```java
public SimpleConsoleLogger()
```

## Methods

### log

```java
void log(Log.LogLevel level, String category, String message, Throwable ex)
```

**Specified by:** `log` in interface `Log.LogListener`