---
source_file: Layer1ApiUserMessageModifyStrategiesMainMenu.html
package: velox.api.layer1.messages
classes: Layer1ApiUserMessageModifyStrategiesMainMenu
methods: Layer1ApiUserMessageModifyStrategiesMainMenu.MenuDescription, strategyName, isAdd, menuDescriptionSet, Layer1ApiUserMessageModifyStrategiesMainMenu
---

# Layer1ApiUserMessageModifyStrategiesMainMenu

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → Layer1ApiUserMessageModifyStrategiesMainMenu

**All Implemented Interfaces:** Layer1ApiStrategiesEchoMessagesLayer.StrategyEchoMessageFromLayer

**Deprecated:** for a few reasons: it's not safe to use without user code wrapping and seems not used by any add-on developers, so it's not supported any more

## Description

Describes either adding or removing group of actions for some strategy in main menu

## Nested Classes

### Layer1ApiUserMessageModifyStrategiesMainMenu.MenuDescription

**Deprecated**

Describes single menu item that is created by this message

## Fields

### strategyName

```java
public final String strategyName
```

**Deprecated**

### isAdd

```java
public final boolean isAdd
```

**Deprecated**

### menuDescriptionSet

```java
public final Set<Layer1ApiUserMessageModifyStrategiesMainMenu.MenuDescription> menuDescriptionSet
```

**Deprecated**

Should have required action listeners attached.

## Constructors

### Layer1ApiUserMessageModifyStrategiesMainMenu

```java
public Layer1ApiUserMessageModifyStrategiesMainMenu(String strategyName, boolean isAdd, Set<Layer1ApiUserMessageModifyStrategiesMainMenu.MenuDescription> menuDescriptionSet)
```

**Deprecated**

**Parameters:**
- `strategyName` - Name of strategy that owns following methods. Will be used to group data in strategies menu
- `isAdd` - true if described actions should be added to strategy menu, false if all actions for this strategy should be removed
- `menuDescriptionSet` - Describes all menu items that should be added to menu.

## Methods

**Methods inherited from class java.lang.Object:**
- `clone()`
- `equals(Object)`
- `finalize()`
- `getClass()`
- `hashCode()`
- `notify()`
- `notifyAll()`
- `toString()`
- `wait()`
- `wait(long)`
- `wait(long, int)`