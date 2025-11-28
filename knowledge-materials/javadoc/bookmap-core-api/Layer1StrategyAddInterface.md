---
source_file: Layer1StrategyAddInterface.html
package: velox.api.layer1.layers.strategies.interfaces
classes: Layer1StrategyAddInterface
methods: onStrategyAdd
---

# Layer1StrategyAddInterface

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Interface

## Methods

### onStrategyAdd

```java
void onStrategyAdd()
```

Called immediately after constructor if strategy was added (as opposed to being loaded from settings); that's usually a result of user manually adding the strategy and is a good time to display initial notifications or perform initial set up process. E.g. if you develop automatic trading strategy it can reset settings to "safe" to avoid user being affected before he has a chance to change anything.

Not called on strategy update via plugin manager.