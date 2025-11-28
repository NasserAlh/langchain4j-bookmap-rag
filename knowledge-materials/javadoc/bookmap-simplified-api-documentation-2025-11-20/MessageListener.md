---
source_file: MessageListener.html
package: velox.api.layer1.simplified
classes: MessageListener
methods: onUserMessage
---

# MessageListener

**Package:** velox.api.layer1.simplified

**Type:** Interface

## Methods

### onUserMessage

```java
void onUserMessage(Object data)
```

Receives all messages on their path from providers to listeners. Normally, just deal with `@Layer1ApiUserInterModuleMessage` instances and ignore the rest.