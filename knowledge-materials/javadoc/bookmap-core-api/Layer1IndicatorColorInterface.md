---
source_file: Layer1IndicatorColorInterface.html
package: velox.api.layer1.layers.strategies.interfaces
classes: Layer1IndicatorColorInterface
methods: setColor, getColor, addColorChangeListener
---

# Layer1IndicatorColorInterface

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Interface

## Methods

### setColor

```java
void setColor(String alias, String name, Color color)
```

Can be called if color is changed from external places such as dialogs or context menus

**Parameters:**
- `alias` - 
- `name` - 
- `color` - 

### getColor

```java
Color getColor(String alias, String name)
```

### addColorChangeListener

```java
void addColorChangeListener(ColorsChangedListener listener)
```

Add listener that will be notified every time colors are changed  
You should implement this method if you have color that can be changed from both inside the strategy and outside (like from color dialog)  
Note that there is no need to remove the listener - weak links are used