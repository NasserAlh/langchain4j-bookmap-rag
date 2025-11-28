---
source_file: UserMessageLayersChainCreatedTargeted.html
package: velox.api.layer1.messages
classes: UserMessageLayersChainCreatedTargeted
methods: targetClass, isNew, UserMessageLayersChainCreatedTargeted, UserMessageLayersChainCreatedTargeted, isTargeted
---

# UserMessageLayersChainCreatedTargeted

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → UserMessageLayersChainCreatedTargeted

**All Implemented Interfaces:** Layer1ApiIgnorableDownwardMessage

## Description

Strategy will receive this message with it's target class specified when it is added and can start sending messages.  
(message.getClass() == (strategy class).class) should return true if it is target of the message.  
Note that there can be many messages of this type that are not related to your class.

**See Also:**
- `Layer1Attachable` for full strategy life cycle description

## Fields

### targetClass

```java
public final Class<?> targetClass
```

### isNew

```java
public boolean isNew
```

Will be true if this is strategy was just added in layer chain, false if it was re-added (e.g. in case of chart settings reset/inherit).

## Constructors

### UserMessageLayersChainCreatedTargeted

```java
public UserMessageLayersChainCreatedTargeted(Class<?> targetClass, boolean isNew)
```

### UserMessageLayersChainCreatedTargeted

```java
public UserMessageLayersChainCreatedTargeted(boolean isNew)
```

No class will be targeted with this.

## Methods

### isTargeted

```java
public boolean isTargeted()
```