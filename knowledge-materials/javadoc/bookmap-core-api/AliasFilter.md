---
source_file: AliasFilter.html
package: velox.api.layer1.messages.indicators
classes: AliasFilter
methods: isDisplayedForAlias
---

# AliasFilter

**Package:** velox.api.layer1.messages.indicators

**Type:** Interface

## Description

Indicator can use this to be displayed only for certain aliases.

## Methods

### isDisplayedForAlias

```java
default boolean isDisplayedForAlias(String alias)
```

**Parameters:**
- `alias` - 

**Returns:** true if indicator should be displayed for this alias, false otherwise