---
source_file: Bar.html
package: velox.api.layer1.simplified
classes: Bar
methods: Bar, Bar, Bar, addTrade, startNext, getOpen, setOpen, getClose, setClose, getHigh, setHigh, getLow, setLow, getVolumeBuy, setVolumeBuy, getVolumeSell, setVolumeSell, getVolumeTotal, getVwapBuy, getVwapSell
total_methods: 21
---

# Bar

**Package:** velox.api.layer1.simplified

**Type:** Class

**Inheritance:** java.lang.Object → Bar

## Description

Single OHLC bar

## Constructors

### Bar

```java
public Bar()
```

### Bar

```java
public Bar(double openPrice)
```

### Bar

```java
public Bar(Bar other)
```

## Methods

### addTrade

```java
public void addTrade(boolean isBuy, long volume, double price)
```

Update bar based on the new trade

### startNext

```java
public void startNext()
```

Set open/close prices to previous close price, clear other fields

### getOpen

```java
public double getOpen()
```

### setOpen

```java
public void setOpen(double open)
```

### getClose

```java
public double getClose()
```

### setClose

```java
public void setClose(double close)
```

### getHigh

```java
public double getHigh()
```

### setHigh

```java
public void setHigh(double high)
```

### getLow

```java
public double getLow()
```

### setLow

```java
public void setLow(double low)
```

### getVolumeBuy

```java
public long getVolumeBuy()
```

### setVolumeBuy

```java
public void setVolumeBuy(long volumeBuy)
```

### getVolumeSell

```java
public long getVolumeSell()
```

### setVolumeSell

```java
public void setVolumeSell(long volumeSell)
```

### getVolumeTotal

```java
public long getVolumeTotal()
```

### getVwapBuy

```java
public double getVwapBuy()
```

### getVwapSell

```java
public double getVwapSell()
```

### getVwap

```java
public double getVwap()
```