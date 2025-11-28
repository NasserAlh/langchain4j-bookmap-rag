---
source_file: Layer1ApiUserMessageModifyIndicator.Builder.html
package: velox.api.layer1.messages.indicators
classes: Layer1ApiUserMessageModifyIndicator.Builder
methods: Builder, setIsAdd, setIndicatorColorScheme, setColorInterface, setIndicatorLineStyle, setDefaultTooltipBackgrondColor, setDefaultTooltipTextColor, setIndicatorDisplayLogic, setIndicatorMinMarginPriceOut, setIndicatorMaxMarginPriceOut, setMinLimitsRange, setBoundsInfo, setGraphType, setIsSupportWidget, setIsShowColorSettings, setIsEnableSettingsFromConfigPopup, setOnlineCalculatable, setAliasFiler, setWidgetDisplayInfo, setIsLineEnabledByDefault
total_methods: 29
---

# Layer1ApiUserMessageModifyIndicator.Builder

**Package:** velox.api.layer1.messages.indicators

**Type:** Class (static final)

**Enclosing class:** `Layer1ApiUserMessageModifyIndicator`

**Inheritance:** java.lang.Object → Layer1ApiUserMessageModifyIndicator.Builder

## Description

Builder to build `Layer1ApiUserMessageModifyIndicator`.

## Constructors

### Builder

```java
public Builder(Layer1ApiUserMessageModifyIndicator message)
```

## Methods

### setIsAdd

```java
public Layer1ApiUserMessageModifyIndicator.Builder setIsAdd(boolean isAdd)
```

**Parameters:**
- `isAdd` - true if message is adding indicator, false if it's removing indicator

**Returns:** this builder

### setIndicatorColorScheme

```java
public Layer1ApiUserMessageModifyIndicator.Builder setIndicatorColorScheme(IndicatorColorScheme indicatorColorScheme)
```

**Parameters:**
- `indicatorColorScheme` - if not null, indicator colors will be assign according to given color scheme

**Returns:** this builder

### setColorInterface

```java
public Layer1ApiUserMessageModifyIndicator.Builder setColorInterface(Layer1IndicatorColorInterface colorInterface)
```

**Parameters:**
- `colorInterface` - this will be used to set / get colors described by `indicatorColorScheme`

### setIndicatorLineStyle

```java
public Layer1ApiUserMessageModifyIndicator.Builder setIndicatorLineStyle(IndicatorLineStyle indicatorLineStyle)
```

**Parameters:**
- `indicatorLineStyle` - indicator line style, if null default line style will be used

**Returns:** this builder

### setDefaultTooltipBackgrondColor

```java
public Layer1ApiUserMessageModifyIndicator.Builder setDefaultTooltipBackgrondColor(Color defaultTooltipBackgrondColor)
```

**Parameters:**
- `defaultTooltipBackgrondColor` - if user has no settings for this indicator color, this color will be used as default for drawing text background in graph tooltip

**Returns:** this builder

### setDefaultTooltipTextColor

```java
public Layer1ApiUserMessageModifyIndicator.Builder setDefaultTooltipTextColor(Color defaultTooltipTextColor)
```

**Parameters:**
- `defaultTooltipTextColor` - if user has no settings for this indicator color, this color will be used as default for drawing text in graph tooltip (background will be of `setDefaultTooltipBackgrondColor(Color)`)

**Returns:** this builder

### setIndicatorDisplayLogic

```java
public Layer1ApiUserMessageModifyIndicator.Builder setIndicatorDisplayLogic(IndicatorDisplayLogic indicatorDisplayLogic)
```

**Parameters:**
- `indicatorDisplayLogic` - can be null, default logic will be applied. If provided logic violates provided `Layer1ApiUserMessageModifyIndicator.indicatorMinMarginPriceOut`, `Layer1ApiUserMessageModifyIndicator.indicatorMaxMarginPriceOut` or `Layer1ApiUserMessageModifyIndicator.minLimitsRange`, limits will be modified to fit into provided arguments

**Returns:** this builder

### setIndicatorMinMarginPriceOut

```java
public Layer1ApiUserMessageModifyIndicator.Builder setIndicatorMinMarginPriceOut(Double indicatorMinMarginPriceOut)
```

**Parameters:**
- `indicatorMinMarginPriceOut` - if not null, indicator's limits will be recalculated when there is less then that amount of indicator range empty space by either border

**Returns:** this builder

### setIndicatorMaxMarginPriceOut

```java
public Layer1ApiUserMessageModifyIndicator.Builder setIndicatorMaxMarginPriceOut(Double indicatorMaxMarginPriceOut)
```

**Parameters:**
- `indicatorMaxMarginPriceOut` - if not null, indicator's limits will be recalculated when there is more then that amount of indicator range empty space by either border

**Returns:** this builder

### setMinLimitsRange

```java
public Layer1ApiUserMessageModifyIndicator.Builder setMinLimitsRange(Double minLimitsRange)
```

**Parameters:**
- `minLimitsRange` - if not null, indicator's limits range (max - min value) can't be less then minLimitsRange

**Returns:** this builder

### setBoundsInfo

```java
public Layer1ApiUserMessageModifyIndicator.Builder setBoundsInfo(BoundsInfo boundsInfo)
```

**Parameters:**
- `boundsInfo` - minimum and maximum values of widget, or null if they should be determined on fly can be null to indicate no predetermined bounds

**Returns:** this builder

### setGraphType

```java
public Layer1ApiUserMessageModifyIndicator.Builder setGraphType(Layer1ApiUserMessageModifyIndicator.GraphType graphType)
```

**Parameters:**
- `graphType` - position of a graph (or NONE). Null will default to NONE

**Returns:** this builder

### setIsSupportWidget

```java
public Layer1ApiUserMessageModifyIndicator.Builder setIsSupportWidget(boolean isSupportWidget)
```

**Parameters:**
- `isSupportWidget` - true if widget is supported, false if widget is not supported

**Returns:** this builder

### setIsShowColorSettings

```java
public Layer1ApiUserMessageModifyIndicator.Builder setIsShowColorSettings(boolean isShowColorSettings)
```

**Parameters:**
- `isShowColorSettings` - If true, indicator popup menu (if one exists) will have 'Setting' entry that will open indicator settings dialog
If indicator is of `Layer1ApiUserMessageModifyIndicator.GraphType.BOTTOM`, settings button will be enabled based on this property

**Returns:** this builder

### setIsEnableSettingsFromConfigPopup

```java
public Layer1ApiUserMessageModifyIndicator.Builder setIsEnableSettingsFromConfigPopup(boolean isEnableSettingsFromConfigPopup)
```

**Parameters:**
- `isEnableSettingsFromConfigPopup` - if true, config popup settings icon will open corresponding strategies dialog tab

**Returns:** this builder

### setOnlineCalculatable

```java
public Layer1ApiUserMessageModifyIndicator.Builder setOnlineCalculatable(OnlineCalculatable onlineCalculatable)
```

**Parameters:**
- `onlineCalculatable` - if not null, indicator will be calculated for chart online

**Returns:** this builder

### setAliasFiler

```java
public Layer1ApiUserMessageModifyIndicator.Builder setAliasFiler(AliasFilter aliasFilter)
```

**Parameters:**
- `aliasFilter` - if null, indicator will be displayed for all aliases, otherwise only for ones defined by filter

**Returns:** this builder

### setWidgetDisplayInfo

```java
public Layer1ApiUserMessageModifyIndicator.Builder setWidgetDisplayInfo(WidgetDisplayInfo widgetDisplayInfo)
```

**Parameters:**
- `widgetDisplayInfo` - information about how widget should display values

**Returns:** this builder

### setIsLineEnabledByDefault

```java
public Layer1ApiUserMessageModifyIndicator.Builder setIsLineEnabledByDefault(boolean isLineEnabledByDefault)
```

**Parameters:**
- `isLineEnabledByDefault` - true if line should be shown by default when strategy is first added, false otherwise

**Returns:** this builder

### setIsWidgetEnabledByDefault

```java
public Layer1ApiUserMessageModifyIndicator.Builder setIsWidgetEnabledByDefault(boolean isWidgetEnabledByDefault)
```

**Parameters:**
- `isWidgetEnabledByDefault` - true if widget should be shown by default when strategy is first added, false otherwise

**Returns:** this builder

### setIndicatorContextMenuInformation

```java
public Layer1ApiUserMessageModifyIndicator.Builder setIndicatorContextMenuInformation(IndicatorContextMenuInformation indicatorContextMenuInformation)
```

**Parameters:**
- `indicatorContextMenuInformation` - information about custom context menus entries

**Returns:** this builder

### setHorizontalValueLinesInfo

```java
public Layer1ApiUserMessageModifyIndicator.Builder setHorizontalValueLinesInfo(HorizontalValueLinesInfo horizontalValueLinesInfo)
```

**Parameters:**
- `horizontalValueLinesInfo` - information about horizontal lines

**Returns:** this builder

### build

```java
public Layer1ApiUserMessageModifyIndicator build()
```

### extendFullName

```java
public Layer1ApiUserMessageModifyIndicator.Builder extendFullName(String suffix)
```

Allows extending full name to register same user name for multiple aliases

**Parameters:**
- `suffix` - suffix to add to full name

### setGraphLayerRenderPriority

```java
public Layer1ApiUserMessageModifyIndicator.Builder setGraphLayerRenderPriority(int graphLayerRenderPriority)
```

Determines order of layers rendered on chart for indicator line. Higher value will be rendered later
Can be arbitrary value or one of predefined `Layer1ApiUserMessageModifyIndicator.LayerRenderPriority` values
Default is `Layer1ApiUserMessageModifyIndicator.LayerRenderPriority.ABOVE_BBO`

**Parameters:**
- `graphLayerRenderPriority` - graph layer render priority

### setGraphLayerRenderPriority

```java
public Layer1ApiUserMessageModifyIndicator.Builder setGraphLayerRenderPriority(Layer1ApiUserMessageModifyIndicator.LayerRenderPriority graphLayerRenderPriority)
```

Determines order of layers rendered on chart for indicator line. Higher value will be rendered later
Can be arbitrary value or one of predefined `Layer1ApiUserMessageModifyIndicator.LayerRenderPriority` values
Default is `Layer1ApiUserMessageModifyIndicator.LayerRenderPriority.ABOVE_BBO`

**Parameters:**
- `graphLayerRenderPriority` - graph layer render priority

### setIconLayerRanderPriotity

```java
public Layer1ApiUserMessageModifyIndicator.Builder setIconLayerRanderPriotity(int iconLayerRanderPriotity)
```

Determines order of layers rendered on chart for indicator icons. Higher value will be rendered later
Can be arbitrary value or one of predefined `Layer1ApiUserMessageModifyIndicator.LayerRenderPriority` values
Default is `Layer1ApiUserMessageModifyIndicator.LayerRenderPriority.TOP`

**Parameters:**
- `iconLayerRanderPriotity` - icon layer render priority

### setIconLayerRanderPriotity

```java
public Layer1ApiUserMessageModifyIndicator.Builder setIconLayerRanderPriotity(Layer1ApiUserMessageModifyIndicator.LayerRenderPriority iconLayerRanderPriotity)
```

Determines order of layers rendered on chart for indicator icons. Higher value will be rendered later
Can be arbitrary value or one of predefined `Layer1ApiUserMessageModifyIndicator.LayerRenderPriority` values
Default is `Layer1ApiUserMessageModifyIndicator.LayerRenderPriority.TOP`

**Parameters:**
- `iconLayerRanderPriotity` - icon layer render priority