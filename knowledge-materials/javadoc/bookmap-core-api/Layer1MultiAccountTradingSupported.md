---
source_file: Layer1MultiAccountTradingSupported.html
package: velox.api.layer1.annotations
classes: Layer1MultiAccountTradingSupported
---

# Layer1MultiAccountTradingSupported

**Package:** velox.api.layer1.annotations

**Type:** Annotation Interface

```java
@Target(TYPE)
@Retention(RUNTIME)
public @interface Layer1MultiAccountTradingSupported
```

## Description

Indicates that the marked strategy supports data from multiple trading accounts to be received.

With this annotation, such data is received with:
- `Layer1ApiTradingListener.onStatus(velox.api.layer1.data.StatusInfo)`
- `Layer1ApiTradingListener.onBalance(velox.api.layer1.data.BalanceInfo)`
- `Layer1ApiTradingListener.onOrderUpdated(velox.api.layer1.data.OrderInfoUpdate)`
- `Layer1ApiTradingListener.onOrderExecuted(velox.api.layer1.data.ExecutionInfo)`

methods, both from primary and non-primary accounts (see `AccountInfo.isPrimary`). Otherwise, if strategy is not annotated with this annotation, only data from a primary account is received or the data has no account specified (i.e. account id is null; provider has no multi-account support).

**See Also:**
- `Layer1ApiTradingListener`
- `TradingAccountsInfoMessage`