---
source_file: ExecutorsHelper.PrioritizedRunnable.html
package: velox.api.layer1.common.helper
classes: ExecutorsHelper.PrioritizedRunnable
methods: PrioritizedRunnable, run, compareTo
---

# ExecutorsHelper.PrioritizedRunnable

**Package:** velox.api.layer1.common.helper

**Type:** Class (static)

**Enclosing class:** [`ExecutorsHelper`](ExecutorsHelper.html)

**Inheritance:** `java.lang.Object` → `ExecutorsHelper.PrioritizedRunnable`

**All Implemented Interfaces:** `Comparable<ExecutorsHelper.PrioritizedRunnable>`, `Runnable`

## Description

Runnable with an integer priority. Lower number means faster execution.

## Constructors

### PrioritizedRunnable

```java
public PrioritizedRunnable(Runnable runnable, int priority)
```

## Methods

### run

```java
public void run()
```

**Specified by:** `run` in interface `Runnable`

### compareTo

```java
public int compareTo(ExecutorsHelper.PrioritizedRunnable o)
```

**Specified by:** `compareTo` in interface `Comparable<ExecutorsHelper.PrioritizedRunnable>`