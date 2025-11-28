---
source_file: ExecutionInfo.html
package: velox.api.layer1.data
classes: ExecutionInfo
methods: orderId, size, price, executionId, time, isSimulated, ExecutionInfo, ExecutionInfo, ExecutionInfo, toString, toBuilder
---

# ExecutionInfo

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.data.ExecutionInfo

**All Implemented Interfaces:** java.io.Serializable

## Description

Information about order execution

**See Also:**
- Serialized Form

## Fields

### orderId

```java
public final String orderId
```

### size

```java
public final int size
```

Size of the execution

### price

```java
public final double price
```

Price where execution happened

### executionId

```java
public final String executionId
```

Execution ID

### time

```java
public final long time
```

Time when execution happened

### isSimulated

```java
public final boolean isSimulated
```

True if execution is simulated by Bookmap.

## Constructors

### ExecutionInfo

```java
public ExecutionInfo(String orderId, int size, double price, String executionId, long time)
```

### ExecutionInfo

```java
public ExecutionInfo(String orderId, int size, double price, String executionId, long time, boolean isSimulated)
```

### ExecutionInfo

```java
public ExecutionInfo(ExecutionInfo executionInfo)
```

## Methods

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`

### toBuilder

```java
public ExecutionInfoBuilder toBuilder()
```