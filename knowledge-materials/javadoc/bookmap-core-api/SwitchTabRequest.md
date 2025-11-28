---
source_file: SwitchTabRequest.html
package: velox.api.layer1.messages.tab
classes: SwitchTabRequest
methods: of, alias, equals, hashCode, toString
---

# SwitchTabRequest

**Package:** velox.api.layer1.messages.tab

**Type:** Class

**Inheritance:** java.lang.Object → SwitchTabRequest

## Description

This message is used to create a request to switch to a tab in the application. Send created message via `Layer1ApiAdminProvider.sendUserMessage(Object)` and receive a `SwitchTabResponse` as a response to get the result of tab switching.

## Methods

### of

```java
public static SwitchTabRequest of(String alias)
```

### alias

```java
public String alias()
```

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

## Inherited Methods

**From class java.lang.Object:**
- `clone()`
- `finalize()`
- `getClass()`
- `notify()`
- `notifyAll()`
- `wait()`
- `wait(long)`
- `wait(long, int)`