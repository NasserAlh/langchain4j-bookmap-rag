---
source_file: Layer1ApiUserMessageModifyScreenSpacePainter.html
package: velox.api.layer1.messages.indicators
classes: Layer1ApiUserMessageModifyScreenSpacePainter
methods: Layer1ApiUserMessageModifyScreenSpacePainter.Builder, ownerClassName, ownerUserName, fullName, userName, isAdd, screenSpacePainterFactory, aliasFilter, applyNameModifier, predictFullName, toString, builder
---

# Layer1ApiUserMessageModifyScreenSpacePainter

**Package:** velox.api.layer1.messages.indicators

**Type:** Class

**Inheritance:** java.lang.Object → Layer1ApiUserMessageModifyScreenSpacePainter

**All Implemented Interfaces:** Layer1ApiStrategiesEchoMessagesLayer.StrategyEchoMessageFromLayer

## Description

Describes adding or removing a screen-space painter.

Note that you need to remember `fullName`. This is a name that will be used to address your indicator by any external parts. This name will be unique through all indicators unless you initialize indicators with same owner class and same user name. This field will be initialized in message constructor and can be accessed after.

## Nested Classes

### Layer1ApiUserMessageModifyScreenSpacePainter.Builder

Builder to build `Layer1ApiUserMessageModifyScreenSpacePainter`.

## Fields

### ownerClassName

```java
public final String ownerClassName
```

Name of owner strategy class.

### ownerUserName

```java
public final String ownerUserName
```

User friendly name of owner strategy class (if provided via `Layer1StrategyName`).

### fullName

```java
public String fullName
```

This is a unique indicator name, that will be used to address this indicator from any external part.

Do not change this field.

Use `applyNameModifier(String)` if you need to generate different full names for same user names.

### userName

```java
public final String userName
```

Name that will be visible to user i.e. in bottom panel context menu.

### isAdd

```java
public final boolean isAdd
```

### screenSpacePainterFactory

```java
public final ScreenSpacePainterFactory screenSpacePainterFactory
```

### aliasFilter

```java
public final AliasFilter aliasFilter
```

## Methods

### applyNameModifier

```java
void applyNameModifier(String modifier)
```

Use if you need to distinguish painters with same user names. For example, could be when creating painters with same names for different aliases. Modifier should be applied before sending this message.

### predictFullName

```java
static String predictFullName(Class<?> myClass, String myName)
```

There is **no guarantee** that value returned from this method will be the same as indicator's full name.

**Do not use this method to predict strategy name.**

### toString

```java
String toString()
```

**Overrides:** `toString` in class `Object`

### builder

```java
static Layer1ApiUserMessageModifyScreenSpacePainter.Builder builder(Class<?> strategyClass, String userName)
```

Creates builder to build `Layer1ApiUserMessageModifyScreenSpacePainter`.

**Returns:** Created builder.