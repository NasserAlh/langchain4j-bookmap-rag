---
source_file: Layer1ApiMboDataListener.html
package: velox.api.layer1
classes: Layer1ApiMboDataListener
methods: onMboSend, onMboReplace, onMboCancel
---

# Layer1ApiMboDataListener

**Package:** velox.api.layer1

**Type:** Interface

**All Known Subinterfaces:**
- `Layer1ApiAdapter`
- `Layer1ApiListener`
- `Layer1ApiMboDataAdapter`
- `OnlineValueCalculatorAdapter`
- `StrategyUpdateGenerator`

**All Known Implementing Classes:**
- `Layer1ApiDepthFreezer`
- `Layer1ApiInjectorRelay`
- `Layer1ApiRelay`
- `Layer1ApiStrategiesEchoMessagesLayer`
- `Layer1ApiUpstreamRelay`

## Description

Listener for MBO events

## Methods

### onMboSend

```java
void onMboSend(String alias, String orderId, boolean isBid, int price, int size)
```

### onMboReplace

```java
void onMboReplace(String alias, String orderId, int price, int size)
```

### onMboCancel

```java
void onMboCancel(String alias, String orderId)
```