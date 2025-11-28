---
source_file: Layer1ApiRequestCurrentTimeEvents.html
package: velox.api.layer1.messages
classes: Layer1ApiRequestCurrentTimeEvents
methods: BOOKMAPINTERVALNS, BOOKMAPUSERMESSAGE, MININTERVALNS, startTimeNs, intervalNs, isAdd, Layer1ApiRequestCurrentTimeEvents, getIntervalNs, setAdd, toString
---

# Layer1ApiRequestCurrentTimeEvents

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → Layer1ApiRequestCurrentTimeEvents

## Description

Providers time will be sent using CurrentTimeUserMessage at least as often as specified by interval. There is one exception: if system was in sleep mode or system time was changed while bookmap was working, some events might be skipped, and event with `CurrentTimeUserMessage.isEventsSkipped` = true flag will be sent.

## Fields

### BOOKMAP_INTERVAL_NS

```java
public static final long BOOKMAP_INTERVAL_NS
```

### BOOKMAP_USER_MESSAGE

```java
public static final Layer1ApiRequestCurrentTimeEvents BOOKMAP_USER_MESSAGE
```

This message is always used by core for `CurrentTimeUserMessage`.

### MIN_INTERVAL_NS

```java
public static final long MIN_INTERVAL_NS
```

### startTimeNs

```java
public final long startTimeNs
```

### intervalNs

```java
public final long intervalNs
```

### isAdd

```java
public boolean isAdd
```

## Constructors

### Layer1ApiRequestCurrentTimeEvents

```java
public Layer1ApiRequestCurrentTimeEvents(boolean isAdd, long startTimeNs, long intervalNs)
```

**Parameters:**
- `isAdd` - True if message wants to start receiving current time messages, false if to stop
- `startTimeNs` - Time of first message sent in UTC nanoseconds
- `intervalNs` - In nanoseconds, at least 1 time message will be sent in every interval. 0 if you only want one message to be sent (can't be less than `MIN_INTERVAL_NS`)

## Methods

### getIntervalNs

```java
public long getIntervalNs()
```

### setAdd

```java
public void setAdd(boolean isAdd)
```

**Parameters:**
- `isAdd` - True if message wants to start receiving current time messages, false if to stop

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`