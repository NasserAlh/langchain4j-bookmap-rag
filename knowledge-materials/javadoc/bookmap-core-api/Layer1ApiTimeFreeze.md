---
source_file: Layer1ApiTimeFreeze.html
package: velox.api.layer1.messages
classes: Layer1ApiTimeFreeze
methods: maxFreezeTimeNs, owner, Layer1ApiTimeFreeze, beginExpirationCountdown, resume, isResumed, toString
---

# Layer1ApiTimeFreeze

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → Layer1ApiTimeFreeze

## Description

Request to freeze time temporarily. Helpful to send snapshot or other updates that belong to same exact timestamp logically. Can be sent from L0 live module.

## Fields

### maxFreezeTimeNs

```java
public final long maxFreezeTimeNs
```

Fallback to prevent eternal freeze

### owner

```java
public final Class<?> owner
```

Source of the freeze. Helpful to understand who is responsible for not lifting freeze in time

## Constructors

### Layer1ApiTimeFreeze

```java
public Layer1ApiTimeFreeze(long maxFreezeTimeNs, Class<?> owner)
```

Create new freeze request. Should be sent via `Layer1ApiAdminListener.onUserMessage(Object)` and then lifted via `resume()`. If not lifted in time, will be lifted automatically after `maxFreezeTimeNs`

**Parameters:**
- `maxFreezeTimeNs` - see `maxFreezeTimeNs`
- `owner` - see `owner`

## Methods

### beginExpirationCountdown

```java
public void beginExpirationCountdown()
```

Start fallback expiration countdown. Normally should only be called by bookmap core.

### resume

```java
public void resume()
```

Resume event flow. Just call this method, no need to send the message again.

### isResumed

```java
public boolean isResumed()
```

Returns true if resumed manually or automatically after `maxFreezeTimeNs`.

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`