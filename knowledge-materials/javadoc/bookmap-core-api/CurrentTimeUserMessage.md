---
source_file: CurrentTimeUserMessage.html
package: velox.api.layer1.messages
classes: CurrentTimeUserMessage
methods: time, isEventsSkipped, isBeforeEventsSkipped, afterEventsSkippedTime, CurrentTimeUserMessage, CurrentTimeUserMessage, CurrentTimeUserMessage, equals, hashCode, toString
---

# CurrentTimeUserMessage

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → CurrentTimeUserMessage

**All Implemented Interfaces:** Layer1ApiIgnorableDownwardMessage, Layer1ApiIgnorableUpwardMessage

## Fields

### time

```java
public final long time
```

Current providers time

### isEventsSkipped

```java
public final boolean isEventsSkipped
```

If true, indicates some events might have been skipped  
For example, will happen after system sleep mode ended

### isBeforeEventsSkipped

```java
public final boolean isBeforeEventsSkipped
```

If true, indicates that we are going to skip `CurrentTimeUserMessage`'s.  
Next message will have flag `isEventsSkipped` set to true.

### afterEventsSkippedTime

```java
public final long afterEventsSkippedTime
```

The time of the next event which will have flag `isEventsSkipped` set to true.  
Works only with the flag `isBeforeEventsSkipped` set to true, otherwise the value is `Long.MIN_VALUE`.

## Constructors

### CurrentTimeUserMessage

```java
public CurrentTimeUserMessage(long time)
```

### CurrentTimeUserMessage

```java
public CurrentTimeUserMessage(long time, boolean isEventsSkipped)
```

### CurrentTimeUserMessage

```java
public CurrentTimeUserMessage(long time, boolean isEventsSkipped, boolean isBeforeEventsSkipped, long afterEventsSkippedTime)
```

## Methods

### equals

```java
public boolean equals(Object o)
```

**Overrides:** `equals` in class `Object`

### hashCode

```java
public int hashCode()
```

**Overrides:** `hashCode` in class `Object`

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`