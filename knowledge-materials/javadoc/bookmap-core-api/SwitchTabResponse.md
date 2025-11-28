---
source_file: SwitchTabResponse.html
package: velox.api.layer1.messages.tab
classes: SwitchTabResponse
methods: match
---

# SwitchTabResponse

**Package:** velox.api.layer1.messages.tab

**Type:** Interface

**Sealed Permits:** SwitchTabResponse.Success, SwitchTabResponse.NoSuchTab

**All Known Implementing Classes:** SwitchTabResponse.NoSuchTab, SwitchTabResponse.Success

## Nested Classes

| Modifier and Type | Interface | Description |
|-------------------|-----------|-------------|
| `static final class` | `SwitchTabResponse.NoSuchTab` |  |
| `static final class` | `SwitchTabResponse.Success` |  |
| `static interface` | `SwitchTabResponse.Visitor<T, E extends Exception>` |  |

## Methods

### match

```java
<T, E extends Exception> T match(SwitchTabResponse.Visitor<T, E> visitor) throws E
```

**Throws:**
- `E`