---
source_file: ConfigureSubscriptionMessage.html
package: velox.api.layer1.messages
classes: ConfigureSubscriptionMessage
methods: instruments, ConfigureSubscriptionMessage
---

# ConfigureSubscriptionMessage

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.messages.ConfigureSubscriptionMessage

## Description

You can control instruments shown in bookmap subscription dialog using this message. Currently listed instruments will be added to subscription dialog dropdown if not already present there. More functionality might be added later.

This message can be sent using `Layer1ApiAdminListener.onUserMessage(Object)` **after** your adapter receives `Layer1ApiAdminProvider.login(velox.api.layer1.data.LoginData)` call (sending it immediately from this method is fine).

## Fields

### instruments

```java
public final List<InstrumentCoreInfo> instruments
```

Instruments to add to subscription dialog

## Constructors

### ConfigureSubscriptionMessage

```java
public ConfigureSubscriptionMessage()
```