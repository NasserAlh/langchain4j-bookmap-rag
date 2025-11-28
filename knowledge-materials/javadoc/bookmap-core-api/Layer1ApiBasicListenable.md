---
source_file: Layer1ApiBasicListenable.html
package: velox.api.layer1
classes: Layer1ApiBasicListenable
methods: instrumentListeners, dataListeners, mboDataListeners, tradingListeners, adminListeners, theOnlyInstrumentListener, theOnlyDataListener, theOnlyMboDataListener, theOnlyTradingListener, theOnlyAdminListener, Layer1ApiBasicListenable, onNoSubscribers, addListener, removeListener, addListener, removeListener, addListener, removeListener, addListener, removeListener
total_methods: 27
---

# Layer1ApiBasicListenable

**Package:** velox.api.layer1

**Type:** Class

**Inheritance:** java.lang.Object → Layer1ApiBasicListenable

**All Implemented Interfaces:** Layer1ApiAdminListenable, Layer1ApiDataListenable, Layer1ApiInstrumentListenable, Layer1ApiMboDataListenable, Layer1ApiTradingListenable, LayerApiListenable

**Direct Known Subclasses:** Layer1ApiUpstreamRelay

## Fields

### instrumentListeners

```java
protected final List<Layer1ApiInstrumentListener> instrumentListeners
```

List of listers for instrument events. Direct modification not recommended, use `addListener(Layer1ApiInstrumentListener)`/`removeListener(Layer1ApiInstrumentListener)` instead

### dataListeners

```java
protected final List<Layer1ApiDataListener> dataListeners
```

List of listers for data events. Direct modification not recommended, use `addListener(Layer1ApiDataListener)`/`removeListener(Layer1ApiDataListener)` instead

### mboDataListeners

```java
protected final List<Layer1ApiMboDataListener> mboDataListeners
```

List of listers for mbo events. Direct modification not recommended, use `addListener(Layer1ApiMboDataListener)`/`removeListener(Layer1ApiMboDataListener)` instead

### tradingListeners

```java
protected final List<Layer1ApiTradingListener> tradingListeners
```

List of listers for trading events. Direct modification not recommended, use `addListener(Layer1ApiTradingListener)`/`removeListener(Layer1ApiTradingListener)` instead

### adminListeners

```java
protected final List<Layer1ApiAdminListener> adminListeners
```

List of listers for admin events. Direct modification not recommended, use `addListener(Layer1ApiAdminListener)`/`removeListener(Layer1ApiAdminListener)` instead

### theOnlyInstrumentListener

```java
protected Layer1ApiInstrumentListener theOnlyInstrumentListener
```

Set if `instrumentListeners` only contains single element. Allows for faster event routing.

### theOnlyDataListener

```java
protected Layer1ApiDataListener theOnlyDataListener
```

Set if `dataListeners` only contains single element. Allows for faster event routing.

### theOnlyMboDataListener

```java
protected Layer1ApiMboDataListener theOnlyMboDataListener
```

Set if `mboDataListeners` only contains single element. Allows for faster event routing.

### theOnlyTradingListener

```java
protected Layer1ApiTradingListener theOnlyTradingListener
```

Set if `tradingListeners` only contains single element. Allows for faster event routing.

### theOnlyAdminListener

```java
protected Layer1ApiAdminListener theOnlyAdminListener
```

Set if `adminListeners` only contains single element. Allows for faster event routing.

## Constructors

### Layer1ApiBasicListenable

```java
public Layer1ApiBasicListenable()
```

## Methods

### onNoSubscribers

```java
protected void onNoSubscribers()
```

Called when no subscribers left. Can be overridden to perform cleanup, etc.

### addListener

```java
public void addListener(Layer1ApiInstrumentListener listener)
```

Add instrument events listener

**Parameters:**
- `listener` - listener to add

### removeListener

```java
public void removeListener(Layer1ApiInstrumentListener listener)
```

Remove instrument events listener

**Parameters:**
- `listener` - listener to remove

### addListener

```java
public void addListener(Layer1ApiDataListener listener)
```

Add data listener

**Parameters:**
- `listener` - listener to add

### removeListener

```java
public void removeListener(Layer1ApiDataListener listener)
```

Remove data listener

**Parameters:**
- `listener` - listener to remove

### addListener

```java
public void addListener(Layer1ApiMboDataListener listener)
```

Add mbo data listener

**Parameters:**
- `listener` - listener to add

### removeListener

```java
public void removeListener(Layer1ApiMboDataListener listener)
```

Remove mbo data listener

**Parameters:**
- `listener` - listener to remove

### addListener

```java
public void addListener(Layer1ApiTradingListener listener)
```

Add trading listener

**Parameters:**
- `listener` - listener to add

### removeListener

```java
public void removeListener(Layer1ApiTradingListener listener)
```

Remove trading listener

**Parameters:**
- `listener` - listener to remove

### addListener

```java
public void addListener(Layer1ApiAdminListener listener)
```

Add listener for general events

**Parameters:**
- `listener` - listener to add

### removeListener

```java
public void removeListener(Layer1ApiAdminListener listener)
```

Remove listener for general events

**Parameters:**
- `listener` - listener to remove

### getUniqueListenersCount

```java
public int getUniqueListenersCount()
```

Function for getting number of unique listeners. SLOW! Make it faster if necessary. PROBABLY UNRELIABLE! Sometime may not return the exact result (synchronization issues).

**Returns:** number of unique listeners

### getAdminListenerBroadcaster

```java
public Layer1ApiAdminListener getAdminListenerBroadcaster()
```

### getInstrumentListenerBroadcaster

```java
public Layer1ApiInstrumentListener getInstrumentListenerBroadcaster()
```

### getTradingListenerBroadcaster

```java
public Layer1ApiTradingListener getTradingListenerBroadcaster()
```

### getDataListenerBroadcaster

```java
public Layer1ApiDataListener getDataListenerBroadcaster()
```