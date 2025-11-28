---
source_file: OrderDuration.html
package: velox.api.layer1.data
classes: OrderDuration
methods: DAY, DYP, GTC, GCP, GTD, GDP, GTT, FOK, ATO, ATC, IOC, GTCPO, code, values, valueOf, toLocalizedString, valueOf, valueOfLoose
---

# OrderDuration

**Package:** velox.api.layer1.data

**Type:** Enum

**Inheritance:** java.lang.Object → java.lang.Enum<OrderDuration> → OrderDuration

**All Implemented Interfaces:** Serializable, Comparable<OrderDuration>, Constable

## Description

Order durations

## Enum Constants

### DAY

```java
public static final OrderDuration DAY
```

Day order. Order is working through the current trading day only.

### DYP

```java
public static final OrderDuration DYP
```

Similar to `DAY`, but includes extended trading session

### GTC

```java
public static final OrderDuration GTC
```

Good Til Canceled. Order is working until canceled or until the contract is no longer available for trading.

### GCP

```java
public static final OrderDuration GCP
```

Similar to `GTC`, but includes extended trading session

### GTD

```java
public static final OrderDuration GTD
```

Good Til Date. Order is working until the end of the nearest trading day for the contract on or before the date specified in the order.

### GDP

```java
public static final OrderDuration GDP
```

Similar to `GTD`, but includes extended trading session

### GTT

```java
public static final OrderDuration GTT
```

Good Til Time. Order is working until the specified time.

### FOK

```java
public static final OrderDuration FOK
```

Fill Or Kill. Immediately fill this order completely or cancel.

### ATO

```java
public static final OrderDuration ATO
```

At The Open. Buy or sell at the very beginning of the trading day.

### ATC

```java
public static final OrderDuration ATC
```

At The Close. Buy or sell at the close of the market, or as near to the closing price as possible.

### IOC

```java
public static final OrderDuration IOC
```

Immediate-Or-Cancel. An IOC requires all or part of the order to be executed immediately; otherwise, the order (or any unfilled parts of the order) will be canceled.

### GTC_PO

```java
public static final OrderDuration GTC_PO
```

The post-only flag indicates that the order should only make liquidity. If any part of the order results in taking liquidity, the order will be rejected and no part of it will execute.

Relevant for GDAX

## Fields

### code

```java
public final int code
```

Code used in recorded files

## Methods

### values

```java
public static OrderDuration[] values()
```

Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** an array containing the constants of this enum class, in the order they are declared

### valueOf

```java
public static OrderDuration valueOf(String name)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - the name of the enum constant to be returned

**Returns:** the enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - if this enum class has no constant with the specified name
- `NullPointerException` - if the argument is null

### toLocalizedString

```java
public String toLocalizedString()
```

### valueOf

```java
public static OrderDuration valueOf(int code)
```

Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `code` - the name of the enum constant to be returned

**Returns:** the enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - if this enum class has no constant with the specified name
- `NullPointerException` - if the argument is null

### valueOfLoose

```java
public static OrderDuration valueOfLoose(String name)
```

Parse order duration from string that might not exactly match the enum.

**Parameters:**
- `name` - string to parse

**Returns:** parsed order duration