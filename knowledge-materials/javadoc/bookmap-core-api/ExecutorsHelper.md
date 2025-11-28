---
source_file: ExecutorsHelper.html
package: velox.api.layer1.common.helper
classes: ExecutorsHelper
methods: ExecutorsHelper.PrioritizedRunnable, ExecutorsHelper, newOnDemandSingleThreadExecutor, newOnDemandSingleThreadPriorityExecutor, newSingleThreadExecutor, newSingleThreadExecutor, newThreadPoolExecutor, newThreadPoolExecutor, newFixedThreadPoolExecutor, newThreadPoolPriorityExecutor, newThreadPoolExecutor, newThreadPoolExecutor, newScheduledThreadPool, newOnDemandPriorityThreadPoolExecutor
---

# ExecutorsHelper

**Package:** velox.api.layer1.common.helper

**Type:** Class

**Inheritance:** java.lang.Object → ExecutorsHelper

## Nested Classes

### ExecutorsHelper.PrioritizedRunnable

Runnable with an integer priority.

## Constructors

### ExecutorsHelper

```java
public ExecutorsHelper()
```

## Methods

### newOnDemandSingleThreadExecutor

```java
public static ThreadPoolExecutor newOnDemandSingleThreadExecutor(String name)
```

Similar to `Executors.newSingleThreadExecutor()`, but limits threads lifetime and sets a name.

### newOnDemandSingleThreadPriorityExecutor

```java
public static ThreadPoolExecutor newOnDemandSingleThreadPriorityExecutor(String name)
```

Respects priority of runnables if those are instances of `ExecutorsHelper.PrioritizedRunnable`, limits threads lifetime, sets a name. Otherwise similar to `Executors.newSingleThreadExecutor()`

### newSingleThreadExecutor

```java
public static ThreadPoolExecutor newSingleThreadExecutor(String name)
```

Similar to `Executors.newSingleThreadExecutor()`, but sets a name and isn't wrapped in FinalizableDelegatedExecutorService.

### newSingleThreadExecutor

```java
public static ThreadPoolExecutor newSingleThreadExecutor(String name, Consumer<Boolean> queueEmptyCallback)
```

Similar to `Executors.newSingleThreadExecutor()`, but sets a name and isn't wrapped in FinalizableDelegatedExecutorService.

### newThreadPoolExecutor

```java
public static ThreadPoolExecutor newThreadPoolExecutor(int corePoolSize, int maximumPoolSize, String name, Consumer<Boolean> queueEmptyCallback)
```

### newThreadPoolExecutor

```java
public static ThreadPoolExecutor newThreadPoolExecutor(int corePoolSize, int maximumPoolSize, String name)
```

### newFixedThreadPoolExecutor

```java
public static ThreadPoolExecutor newFixedThreadPoolExecutor(int poolSize, String name)
```

### newThreadPoolPriorityExecutor

```java
public static ThreadPoolExecutor newThreadPoolPriorityExecutor(int corePoolSize, int maximumPoolSize, String name)
```

### newThreadPoolExecutor

```java
public static ThreadPoolExecutor newThreadPoolExecutor(int corePoolSize, int maximumPoolSize, String name, BlockingQueue<Runnable> queue)
```

### newThreadPoolExecutor

```java
public static ThreadPoolExecutor newThreadPoolExecutor(int corePoolSize, int maximumPoolSize, String name, BlockingQueue<Runnable> queue, RejectedExecutionHandler handler)
```

### newScheduledThreadPool

```java
public static ScheduledExecutorService newScheduledThreadPool(int corePoolSize, String name)
```

### newOnDemandPriorityThreadPoolExecutor

```java
public static velox.api.layer1.common.helper.PriorityThreadPoolExecutor newOnDemandPriorityThreadPoolExecutor(String name)
```