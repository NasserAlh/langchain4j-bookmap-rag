---
source_file: Layer1SyntheticInstrumentProvider.html
package: velox.api.layer1.utils
classes: Layer1SyntheticInstrumentProvider
methods: isSyntheticInstrument
---

# Layer1SyntheticInstrumentProvider

**Package:** velox.api.layer1.utils

**Type:** Interface

## Description

The strategy should implement this interface if it creates synthetic instruments.

## Methods

### isSyntheticInstrument

```java
boolean isSyntheticInstrument(String alias)
```

Check if instrument with given alias is created by the strategy.

**Parameters:**
- `alias` - Alias of instrument

**Returns:** True if instrument is created by this provider