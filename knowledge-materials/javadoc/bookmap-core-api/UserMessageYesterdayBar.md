---
source_file: UserMessageYesterdayBar.html
package: velox.api.layer1.messages
classes: UserMessageYesterdayBar
methods: UserMessageYesterdayBar, UserMessageYesterdayBar, getContract, getOpenPriceInTicks, getHighPriceInTicks, getLowPriceInTicks, getClosePriceInTicks, toString
---

# UserMessageYesterdayBar

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → UserMessageYesterdayBar

**All Implemented Interfaces:** velox.api.layer1.messages.Layer1ApiIgnorableUpwardMessage

**Deprecated:** This class is deprecated and will likely be removed from API later.

## Description

Deprecated: not used in current versions of bookmap, will likely be removed from API later.

Encapsulates yesterday bar information - contract name and OLHC prices.

## Constructors

### UserMessageYesterdayBar

```java
public UserMessageYesterdayBar(String aContract, int anOpenPrice, int aHighPrice, int aLowPrice, int aClosePrice)
```

**Deprecated**

Creates new instance of UserMessageYesterdayBar object.

**Parameters:**
- `aContract` - Contract name for yesterday bar.
- `anOpenPrice` - Yesterday open price in ticks.
- `aHighPrice` - Yesterday high price in ticks.
- `aLowPrice` - Yesterday low price in ticks.
- `aClosePrice` - Yesterday close price in ticks.

### UserMessageYesterdayBar

```java
public UserMessageYesterdayBar(String aContract, double anOpenPrice, double aHighPrice, double aLowPrice, double aClosePrice)
```

**Deprecated**

Creates new instance of UserMessageYesterdayBar object.

**Parameters:**
- `aContract` - Contract name for yesterday bar.
- `anOpenPrice` - Yesterday open price in ticks.
- `aHighPrice` - Yesterday high price in ticks.
- `aLowPrice` - Yesterday low price in ticks.
- `aClosePrice` - Yesterday close price in ticks.

## Methods

### getContract

```java
public String getContract()
```

**Deprecated**

Gets yesterday bar contract name.

**Returns:** Contract name for yesterday bar.

### getOpenPriceInTicks

```java
public int getOpenPriceInTicks()
```

**Deprecated**

Gets yesterday bar open price in ticks.

**Returns:** Yesterday open price in ticks.

### getHighPriceInTicks

```java
public int getHighPriceInTicks()
```

**Deprecated**

Gets yesterday bar high price in ticks.

**Returns:** Yesterday high price in ticks.

### getLowPriceInTicks

```java
public int getLowPriceInTicks()
```

**Deprecated**

Gets yesterday bar low price in ticks.

**Returns:** Yesterday low price in ticks.

### getClosePriceInTicks

```java
public int getClosePriceInTicks()
```

**Deprecated**

Gets yesterday bar close price in ticks.

**Returns:** Yesterday close price in ticks.

### toString

```java
public String toString()
```

**Deprecated**

**Overrides:** `toString` in class `Object`