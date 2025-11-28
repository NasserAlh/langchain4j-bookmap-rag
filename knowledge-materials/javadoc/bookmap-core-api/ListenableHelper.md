---
source_file: ListenableHelper.html
package: velox.api.layer1.common
classes: ListenableHelper
methods: ListenableHelper, addListeners, addListeners, removeListeners
---

# ListenableHelper

**Package:** velox.api.layer1.common

**Type:** Class

**Inheritance:** java.lang.Object → ListenableHelper

## Constructors

### ListenableHelper

```java
public ListenableHelper()
```

## Methods

### addListeners

```java
public static void addListeners(Object listenable, Object listener)
```

Subscribe as `Layer1ApiAdminListener`, `Layer1ApiDataListener`, `Layer1ApiInstrumentListener`, `Layer1ApiTradingListener` if applicable.

**Parameters:**
- `listenable` - 
- `listener` - 

### addListeners

```java
public static void addListeners(LayerApiListenable listenable, Object listener)
```

Subscribe as `Layer1ApiAdminListener`, `Layer1ApiDataListener`, `Layer1ApiInstrumentListener`, `Layer1ApiTradingListener` if applicable.

**Parameters:**
- `listenable` - 
- `listener` - 

### removeListeners

```java
public static void removeListeners(LayerApiListenable listenable, Object listener)
```

Unsubscribe as `Layer1ApiAdminListener`, `Layer1ApiDataListener`, `Layer1ApiInstrumentListener`, `Layer1ApiTradingListener` if applicable.

**Parameters:**
- `listenable` - 
- `listener` -