---
source_file: MultiAccountAware.html
package: velox.api.layer1.data
classes: MultiAccountAware
methods: getTradingAccountId
---

# MultiAccountAware

**Package:** velox.api.layer1.data

**Type:** Interface

**All Known Implementing Classes:** BalanceInfo, OrderInfo, OrderInfoUpdate, SimpleOrderSendParameters, SingleOrderSendParameters, StatusInfo

## Description

Indicates that the data can be associated with a specific trading account, and provides the account details to identify which account the data is associated with.

## Methods

### getTradingAccountId

```java
String getTradingAccountId()
```