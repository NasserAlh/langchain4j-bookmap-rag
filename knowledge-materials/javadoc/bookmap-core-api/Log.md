---
source_file: Log.html
package: velox.api.layer1.common
classes: Log
methods: Log, setListener, getListener, setLogLevel, getLogLevel, trade, tradeFmt, error, errorFmt, warn, warnFmt, info, infoFmt, debug, debugFmt, trace, traceFmt, perflog, perflogReset, warnFree
total_methods: 24
---

# Log

**Package:** velox.api.layer1.common

**Type:** Class

**Inheritance:** java.lang.Object → Log

## Description

Allows you to write messages to bookmap log, all methods with suffix "Fmt" should follow slf4j arguments passing rules.

NOTE: this part of API is likely to change.

## Nested Classes

- `Log.LogLevel` (enum)
- `Log.LogListener` (interface) 
- `Log.SimpleConsoleLogger` (class)

## Constructors

### Log

```java
public Log()
```

## Methods

### setListener

```java
public static void setListener(Log.LogListener listener)
```

Sets the log listener that handles all log messages replacing the previous log listener. Normally for internal use only: you should not change it, unless you really know what you are doing.

**Parameters:**
- `listener` - The listener that should handle log messages

### getListener

```java
public static Log.LogListener getListener()
```

Returns the current log listener that handles all log messages. Normally for internal use only: you should not use it, unless you really know what you are doing.

**Returns:** Current log listener that handles all log messages

### setLogLevel

```java
public static void setLogLevel(Log.LogLevel logLevel)
```

### getLogLevel

```java
public static Log.LogLevel getLogLevel()
```

### trade

```java
public static void trade(String message, Exception ex)
```

```java
public static void trade(String category, String message, Exception ex)
```

```java
public static void trade(String message)
```

```java
public static void trade(String category, String message)
```

### tradeFmt

```java
public static void tradeFmt(String pattern, Object... args)
```

Follow slf4j arguments passing rules.

### error

```java
public static void error(String message, Throwable ex)
```

```java
public static void error(String category, String message, Exception ex)
```

```java
public static void error(String message)
```

```java
public static void error(String category, String message)
```

### errorFmt

```java
public static void errorFmt(String pattern, Object... args)
```

Follow slf4j arguments passing rules.

### warn

```java
public static void warn(String message, Exception ex)
```

```java
public static void warn(String category, String message, Throwable ex)
```

```java
public static void warn(String message)
```

```java
public static void warn(String category, String message)
```

### warnFmt

```java
public static void warnFmt(String pattern, Object... args)
```

Follow slf4j arguments passing rules.

### info

```java
public static void info(String message, Exception ex)
```

```java
public static void info(String category, String message, Exception ex)
```

```java
public static void info(String message)
```

```java
public static void info(String category, String message)
```

### infoFmt

```java
public static void infoFmt(String pattern, Object... args)
```

Follow slf4j arguments passing rules.

### debug

```java
public static void debug(String message, Exception ex)
```

```java
public static void debug(String category, String message, Exception ex)
```

```java
public static void debug(String message)
```

```java
public static void debug(String category, String message)
```

### debugFmt

```java
public static void debugFmt(String pattern, Object... args)
```

Follow slf4j arguments passing rules.

### trace

```java
public static void trace(String message, Exception ex)
```

```java
public static void trace(String category, String message, Exception ex)
```

```java
public static void trace(String category, String message)
```

```java
public static void trace(String message)
```

### traceFmt

```java
public static void traceFmt(String pattern, Object... args)
```

Follow slf4j arguments passing rules.

### perflog

```java
public static void perflog()
```

```java
public static void perflog(String message)
```

```java
public static void perflog(String message, long minDelayToLogNanos)
```

### perflogReset

```java
public static long perflogReset()
```

### warnFree

```java
public static void warnFree(Object... messages)
```

### infoFree

```java
public static void infoFree(Object... messages)
```

### debugFree

```java
public static void debugFree(Object... messages)
```

### traceFree

```java
public static void traceFree(Object... messages)
```

### tradeFree

```java
public static void tradeFree(Object... messages)
```