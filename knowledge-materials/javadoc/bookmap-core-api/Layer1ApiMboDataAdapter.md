---
source_file: Layer1ApiMboDataAdapter.html
package: velox.api.layer1
classes: Layer1ApiMboDataAdapter
methods: onMboSend, onMboReplace, onMboCancel
extends: ** Layer1ApiMboDataListener
---

# Layer1ApiMboDataAdapter

**Package:** velox.api.layer1

**Type:** Interface

**All Superinterfaces:** Layer1ApiMboDataListener

**All Known Subinterfaces:** Layer1ApiAdapter, OnlineValueCalculatorAdapter, StrategyUpdateGenerator

## Description

Provides default empty implementations

## Methods

### onMboSend

```java
default void onMboSend(String alias, String orderId, boolean isBid, int price, int size)
```

**Specified by:** `onMboSend` in interface `Layer1ApiMboDataListener`

---

### onMboReplace

```java
default void onMboReplace(String alias, String orderId, int price, int size)
```

**Specified by:** `onMboReplace` in interface `Layer1ApiMboDataListener`

---

### onMboCancel

```java
default void onMboCancel(String alias, String orderId)
```

**Specified by:** `onMboCancel` in interface `Layer1ApiMboDataListener`