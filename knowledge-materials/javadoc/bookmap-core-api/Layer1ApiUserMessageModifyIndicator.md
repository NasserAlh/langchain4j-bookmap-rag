---
source_file: Layer1ApiUserMessageModifyIndicator.html
package: velox.api.layer1.messages.indicators
classes: Layer1ApiUserMessageModifyIndicator
methods: ownerClassName, ownerUserName, fullName, userName, isAdd, indicatorColorScheme, colorInterface, indicatorLineStyle, defaultTooltipBackgrondColor, defaultTooltipTextColor, indicatorDisplayLogic, indicatorMinMarginPriceOut, indicatorMaxMarginPriceOut, minLimitsRange, boundsInfo, graphType, isSupportWidget, isShowColorSettings, isEnableSettingsFromConfigPopup, onlineCalculatable
total_methods: 38
---

# Layer1ApiUserMessageModifyIndicator

**Package:** velox.api.layer1.messages.indicators

**Type:** Class

**Inheritance:** java.lang.Object → Layer1ApiUserMessageModifyIndicator

**All Implemented Interfaces:** Layer1ApiStrategiesEchoMessagesLayer.StrategyEchoMessageFromLayer

## Description

Describes adding or removing indicator.

Note that you need to remember `fullName`. This is a name that will be used to address your indicator by any external parts (for example online indicator calculations). This name will be unique through all indicators unless you initialize indicators with same owner class and same user name. This field will be initialized in message constructor and can be accessed after.

## Nested Classes

- **Layer1ApiUserMessageModifyIndicator.Builder** - Builder to build `Layer1ApiUserMessageModifyIndicator`
- **Layer1ApiUserMessageModifyIndicator.GraphType** - Enum
- **Layer1ApiUserMessageModifyIndicator.LayerRenderPriority** - Enum

## Fields

### ownerClassName

```java
public final String ownerClassName
```

Name of owner strategy class.

---

### ownerUserName

```java
public final String ownerUserName
```

User friendly name of owner strategy class (if provided via `Layer1StrategyName`).

---

### fullName

```java
public String fullName
```

This is a unique indicator name, that will be used to address this indicator from any external part.

Do not change this field.

Use `applyNameModifier(String)` if you need to generate different full names for same user names.

---

### userName

```java
public final String userName
```

Name that will be visible to user i.e. in bottom panel context menu.

---

### isAdd

```java
public final boolean isAdd
```

---

### indicatorColorScheme

```java
public final IndicatorColorScheme indicatorColorScheme
```

Default color scheme has only one main color, named as strategy and used for every value.

---

### colorInterface

```java
public final Layer1IndicatorColorInterface colorInterface
```

This will be used to set/get colors described by `indicatorColorScheme`.

If null, colors will be attempted to retrieve via internal color storage by names provided by `indicatorColorScheme`.

---

### indicatorLineStyle

```java
public final IndicatorLineStyle indicatorLineStyle
```

Line style used by indicator.

---

### defaultTooltipBackgrondColor

```java
public final Color defaultTooltipBackgrondColor
```

---

### defaultTooltipTextColor

```java
public final Color defaultTooltipTextColor
```

---

### indicatorDisplayLogic

```java
public final IndicatorDisplayLogic indicatorDisplayLogic
```

---

### indicatorMinMarginPriceOut

```java
public final Double indicatorMinMarginPriceOut
```

---

### indicatorMaxMarginPriceOut

```java
public final Double indicatorMaxMarginPriceOut
```

---

### minLimitsRange

```java
public final Double minLimitsRange
```

---

### boundsInfo

```java
public final BoundsInfo boundsInfo
```

---

### graphType

```java
public final Layer1ApiUserMessageModifyIndicator.GraphType graphType
```

Strategies have few options of where graph is displayed, see `Layer1ApiUserMessageModifyIndicator.GraphType`.

Default: `Layer1ApiUserMessageModifyIndicator.GraphType.NONE`

---

### isSupportWidget

```java
public final boolean isSupportWidget
```

Some strategies may choose to NOT display themselves as widget.

Default: true (widget supported).

---

### isShowColorSettings

```java
public final boolean isShowColorSettings
```

---

### isEnableSettingsFromConfigPopup

```java
public final boolean isEnableSettingsFromConfigPopup
```

---

### onlineCalculatable

```java
public final OnlineCalculatable onlineCalculatable
```

---

### aliasFilter

```java
public final AliasFilter aliasFilter
```

---

### widgetDisplayInfo

```java
public WidgetDisplayInfo widgetDisplayInfo
```

---

### isLineEnabledByDefault

```java
public boolean isLineEnabledByDefault
```

---

### isWidgetEnabledByDefault

```java
public boolean isWidgetEnabledByDefault
```

---

### indicatorContextMenuInformation

```java
public IndicatorContextMenuInformation indicatorContextMenuInformation
```

---

### horizontalValueLinesInfo

```java
public HorizontalValueLinesInfo horizontalValueLinesInfo
```

---

### graphLayerRenderPriority

```java
public int graphLayerRenderPriority
```

---

### iconLayerRenderPriority

```java
public int iconLayerRenderPriority
```

## Constructors

### Layer1ApiUserMessageModifyIndicator

```java
public Layer1ApiUserMessageModifyIndicator(Class<?> strategyClass, String userName, boolean isAdd, IndicatorColorScheme indicatorColorScheme, Layer1IndicatorColorInterface colorInterface, IndicatorLineStyle indicatorLineStyle, Color defaultTooltipBackgrondColor, Color defaultTooltipTextColor, IndicatorDisplayLogic indicatorDisplayLogic, Double indicatorMinMarginPriceOut, Double indicatorMaxMarginPriceOut, Double minLimitsRange, BoundsInfo boundsInfo, Layer1ApiUserMessageModifyIndicator.GraphType graphType, Boolean isSupportWidget, Boolean isShowColorSettings, Boolean isEnableSettingsFromConfigPopup, OnlineCalculatable onlineCalculatable, AliasFilter aliasFilter)
```

Note that you need to remember `fullName`. This is a name that will be used to address your indicator by any external parts (for example online indicator calculations). This name will be unique through all indicators unless you initialize indicators with same owner class and same user name. This field will be initialized in message constructor and can be accessed after.

**Parameters:**
- `strategyClass` - Class of strategy, creating this indicator
- `userName` - Name that will be visible to user i.e. in bottom panel context menu
- `isAdd` - True if message is adding indicator, false if it's removing indicator
- `indicatorColorScheme` - If not null, indicator colors will be assigned according to given color scheme
- `colorInterface` - This will be used to set/get colors described by `indicatorColorScheme`
- `indicatorLineStyle` - Indicator line style, if null default line style will be used
- `defaultTooltipTextColor` - If user has no settings for this indicator color, this color will be used as default for drawing text in graph tooltip (background will be of defaultGraphColor)
- `indicatorDisplayLogic` - Can be null, default logic will be applied. If provided logic violates provided `indicatorMinMarginPriceOut`, `indicatorMaxMarginPriceOut` or `minLimitsRange`, limits will be modified to fit into provided arguments
- `indicatorMinMarginPriceOut` - If not null, indicator's limits will be recalculated when there is less than that amount of indicator range empty space by either border
- `indicatorMaxMarginPriceOut` - If not null, indicator's limits will be recalculated when there is more than that amount of indicator range empty space by either border
- `minLimitsRange` - If not null, indicator's limits range (max - min value) can't be less than minLimitsRange
- `boundsInfo` - Minimum and maximum values of widget. Null to indicate no predetermined bounds (will be determined on fly)
- `graphType` - Position of a graph (or NONE). Null will default to NONE
- `isSupportWidget` - True if widget is supported, false if widget is not supported
- `isShowColorSettings` - (Temporary) if false, no color settings will be available via popup menu
- `isEnableSettingsFromConfigPopup` - If true, config popup settings icon will open corresponding strategies dialog tab
- `onlineCalculatable` - If not null, indicator will be calculated for chart online
- `aliasFilter` - If null, indicator will be displayed for all aliases, otherwise only for ones defined by filter

---

### Layer1ApiUserMessageModifyIndicator

```java
public Layer1ApiUserMessageModifyIndicator(Layer1ApiUserMessageModifyIndicator message)
```

---

### Layer1ApiUserMessageModifyIndicator

```java
public Layer1ApiUserMessageModifyIndicator(Layer1ApiUserMessageModifyIndicator message, boolean isAdd)
```

Copy of message with overridden isAdd.

---

### Layer1ApiUserMessageModifyIndicator

```java
public Layer1ApiUserMessageModifyIndicator(Class<?> strategyClass, String userName, boolean isAdd)
```

Note that you need to remember `fullName`. This is a name that will be used to address your indicator by any external parts (for example online indicator calculations). This name will be unique through all indicators unless you initialize indicators with same owner class and same user name. This field will be initialized in message constructor and can be accessed after.

**Parameters:**
- `strategyClass` - Class of strategy, creating this indicator
- `userName` - Name that will be visible to user e.g. in bottom panel context menu
- `isAdd` - True if message is adding indicator, false if it's removing indicator

## Methods

### applyNameModifier

```java
public void applyNameModifier(String modifier)
```

Use if you need to distinguish strategies with same user names. For example, could be when creating strategies with same names for different aliases. Modifier should be applied before sending this message.

---

### predictFullName

```java
public static String predictFullName(Class<?> myClass, String myName)
```

There is **no guarantee** that value returned from this method will be the same as indicator's full name.

**Do not use this method to predict strategy name.**

---

### builder

```java
public static Layer1ApiUserMessageModifyIndicator.Builder builder(Class<?> strategyClass, String userName)
```

Creates builder to build `Layer1ApiUserMessageModifyIndicator`.

**Parameters:**
- `strategyClass` - Class of strategy, creating this indicator
- `userName` - Name that will be visible to user e.g. in bottom panel context menu

**Returns:** Created builder

---

### builder

```java
public static Layer1ApiUserMessageModifyIndicator.Builder builder(Class<?> strategyClass, String localizedName, String unlocalizedName)
```

Creates builder to build `Layer1ApiUserMessageModifyIndicator`.

**Parameters:**
- `strategyClass` - Class of strategy, creating this indicator
- `localizedName` - Name that will be visible to user e.g. in bottom panel context menu
- `unlocalizedName` - Name that will be used to identify the indicator in the code (english name of the indicator can be used)

**Returns:** Created builder

**See Also:**
- `fullName`
- `predictFullName(Class, String)` for fullName

---

### getOwnerUserName

```java
public static String getOwnerUserName(Class<?> ownerClass)
```

---

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`