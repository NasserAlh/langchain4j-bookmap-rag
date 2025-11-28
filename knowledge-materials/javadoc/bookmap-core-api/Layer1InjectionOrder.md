---
source_file: Layer1InjectionOrder.html
package: velox.api.layer1.annotations
classes: Layer1InjectionOrder
methods: value
---

# Layer1InjectionOrder

**Package:** velox.api.layer1.annotations

**Type:** Annotation Interface

```java
@Retention(RUNTIME)
public @interface Layer1InjectionOrder
```

## Description

Specifies the order of injection of strategies in the layers chain.

The order of injection is determined by the value of the annotation.

The lower the value, the lower the strategy is injected in the chain compared to other strategies (closer to the data provider).

If two strategies have the same order number, the order of injection is determined by the alphabetical order of the entry point class names.

The default order value is 0.

**Note:** This annotation can be used only with classes marked with `Layer1Injectable` and `Layer1UpstreamDataEditor`.

Example of injection order:

1. * Data Provider *
2. ...
3. com.example.X1, order value = -1000
4. com.example.X2, order value = -42
5. ...
6. com.example.A3, order value = 0
7. com.example.B3, order value = 0
8. com.example.C3, order value = 0
9. com.example.D3, order value = 0
10. ...
11. com.example.X4, order value = 1
12. com.example.X5, order value = 2
13. ...
14. com.example.A6, order value = 100
15. com.example.B6, order value = 100
16. com.example.C6, order value = 100
17. ...
18. com.example.X7, order value = 99999
19. ...

## Elements

### value

```java
int value()
```

**Returns:** The order value