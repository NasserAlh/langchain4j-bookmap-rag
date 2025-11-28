---
source_file: RunModeHelper.html
package: velox.api.layer1.common
classes: RunModeHelper
methods: RunModeHelper, setRunModeOnce, isSet, isLive, isRealTrading
---

# RunModeHelper

**Package:** velox.api.layer1.common

**Type:** Class

**Inheritance:** java.lang.Object → RunModeHelper

## Description

This class can be used to retrieve Bookmap Run Mode

## Constructors

### RunModeHelper

```java
public RunModeHelper()
```

## Methods

### setRunModeOnce

```java
public static void setRunModeOnce(boolean isLive, boolean isRealTrading)
```

Called by Bookmap code once to set Run Mode.

### isSet

```java
public static boolean isSet()
```

**Returns:** true if the Run Mode is selected

### isLive

```java
public static Boolean isLive()
```

This flag indicates that Bookmap gets the data from the provider, it still can be delayed or replayed by the provider. Otherwise, the data is replayed by Bookmap.

**Returns:** true if the data live (by provider)

### isRealTrading

```java
public static Boolean isRealTrading()
```

This flag indicates that all the trading events will be passed to provider, it still can be simulated on provider side. Otherwise, the trading is simulated by Bookmap.

**Returns:** true if the trading is real (by provider)