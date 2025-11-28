---
source_file: OrderBook.html
package: velox.api.layer1.layers.utils
classes: OrderBook
methods: NONE, bidMap, askMap, OrderBook, OrderBook, onUpdate, onUpdate, updateMap, getBestBidPriceOrNone, getBestAskPriceOrNone, getMidPriceOrNan, getFirstKeyOrNone, getFirstValueOrNone, levels, getWorstBidPriceOrNone, getWorstAskPriceOrNone, getLastKeyOrNone, levels, levels, getSizeFor
total_methods: 26
---

# OrderBook

**Package:** velox.api.layer1.layers.utils

**Type:** Class

**Inheritance:** java.lang.Object → OrderBook

## Fields

### NONE

```java
public static final int NONE
```

### bidMap

```java
protected TreeMap<Integer, Long> bidMap
```

### askMap

```java
protected TreeMap<Integer, Long> askMap
```

## Constructors

### OrderBook

```java
public OrderBook()
```

### OrderBook

```java
public OrderBook(OrderBook orderBook)
```

## Methods

### onUpdate

```java
public void onUpdate(boolean isBid, int price, long size)
```

### onUpdate

```java
public void onUpdate(DepthAggregationEvent depthAggregationEvent)
```

### updateMap

```java
protected void updateMap(TreeMap<Integer, Long> updatedMap, int price, long size)
```

### getBestBidPriceOrNone

```java
public int getBestBidPriceOrNone()
```

### getBestAskPriceOrNone

```java
public int getBestAskPriceOrNone()
```

### getMidPriceOrNan

```java
public double getMidPriceOrNan()
```

### getFirstKeyOrNone

```java
public int getFirstKeyOrNone(TreeMap<Integer, Long> map)
```

### getFirstValueOrNone

```java
public long getFirstValueOrNone(TreeMap<Integer, Long> map)
```

### levels

```java
public Integer[] levels(boolean isBid)
```

### getWorstBidPriceOrNone

```java
public int getWorstBidPriceOrNone()
```

### getWorstAskPriceOrNone

```java
public int getWorstAskPriceOrNone()
```

### getLastKeyOrNone

```java
public int getLastKeyOrNone(TreeMap<Integer, Long> map)
```

### levels

```java
public Map<Integer, Long> levels(boolean isBid, int from, int to)
```

### levels

```java
public Map<Integer, Long> levels(boolean isBid, int from, boolean fromInclusive, int to, boolean toInclusive)
```

### getSizeFor

```java
public long getSizeFor(boolean isBid, int price)
```

### getSizeFor

```java
public long getSizeFor(boolean isBid, int price, long defaultSize)
```

### clear

```java
public void clear()
```

### getBidMap

```java
public TreeMap<Integer, Long> getBidMap()
```

Beware: changing this map will break order book

### getAskMap

```java
public TreeMap<Integer, Long> getAskMap()
```

Beware: changing this map will break order book

### isEmpty

```java
public boolean isEmpty()
```

Check if order book is empty

**Returns:** true if both sides of the book are empty, false otherwise

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`