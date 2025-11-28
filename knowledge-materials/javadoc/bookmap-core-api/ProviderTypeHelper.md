---
source_file: ProviderTypeHelper.html
package: velox.api.layer1.common.helper
classes: ProviderTypeHelper
methods: ProviderTypeHelper, getProviderShortName, getProviderUserName
---

# ProviderTypeHelper

**Package:** velox.api.layer1.common.helper

**Type:** Class

**Inheritance:** java.lang.Object → ProviderTypeHelper

## Constructors

### ProviderTypeHelper

```java
public ProviderTypeHelper()
```

## Methods

### getProviderShortName

```java
public static String getProviderShortName(String providerProgrammaticName)
```

Returns short name of the provider by provider id.
e.g. "EXT:velox.api.layer0.adapters.binance.BinanceProvider" will result in "BN"

**Parameters:**
- `providerProgrammaticName` - Provider id

**Returns:** Short name of the provider

### getProviderUserName

```java
public static String getProviderUserName(String providerProgrammaticName)
```

Returns user name of the provider by provider id.
e.g. "EXT:velox.api.layer0.adapters.binance.BinanceProvider" will result in "Binance"

**Parameters:**
- `providerProgrammaticName` - Provider id

**Returns:** User name of the provider