---
source_file: GeneratedUpdateConsumer.html
package: velox.api.layer1.messages.indicators
classes: GeneratedUpdateConsumer
methods: setGeneratedEventsConsumer, getGeneratedEventsConsumer
---

# GeneratedUpdateConsumer

**Package:** velox.api.layer1.messages.indicators

**Type:** Interface

**All Known Subinterfaces:** StrategyUpdateGenerator

## Methods

### setGeneratedEventsConsumer

```java
void setGeneratedEventsConsumer(Consumer<CustomGeneratedEventAliased> consumer)
```

**Parameters:**
- `consumer` - That should be used to store generated event to be accessed later by strategy. The event must have exactly the same time that is passed to the setTime method at the moment the given event is sent to the consumer (i.e. the event should not be in the future or in the past, relative to the time passed to the setTime)

### getGeneratedEventsConsumer

```java
Consumer<CustomGeneratedEventAliased> getGeneratedEventsConsumer()
```

**Returns:** Consumer that should be used to store generated event to be accessed later by strategy