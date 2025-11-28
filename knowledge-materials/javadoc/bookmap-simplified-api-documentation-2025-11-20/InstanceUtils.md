---
source_file: InstanceUtils.html
package: velox.api.layer1.simplified
classes: InstanceUtils
methods: InstanceUtils, setValuesFromSettings, readSettingsFromAnnotations, readDefaultColorSettings, getColorPanel, addCustomGui, isTypeAllowed, isTypeAllowed
---

# InstanceUtils

**Package:** velox.api.layer1.simplified

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.simplified.InstanceUtils

## Constructors

### InstanceUtils

```java
public InstanceUtils()
```

## Methods

### setValuesFromSettings

```java
public static void setValuesFromSettings(UserSettings settings, Object instance, Class<? extends Annotation> annotationClass)
```

### readSettingsFromAnnotations

```java
public static Map<String, UserSettings.ParameterField> readSettingsFromAnnotations(Object instance, Class<? extends Annotation> annotationClass)
```

### readDefaultColorSettings

```java
public static Map<String, UserSettings.ParameterField> readDefaultColorSettings(Object instance, Class<? extends Annotation> annotationClass)
```

### getColorPanel

```java
public static velox.gui.StrategyPanel getColorPanel(CustomModule instance, SimplifiedL1ApiLoader<? extends CustomModule> apiLoader, String alias)
```

### addCustomGui

```java
public static velox.gui.StrategyPanel addCustomGui(Object instance, UserSettings settings, String panelName, String alias, SimplifiedL1ApiLoader<?> apiLoader)
```

### isTypeAllowed

```java
public static boolean isTypeAllowed(Class<?> simpleStrategyClass, Field field)
```

### isTypeAllowed

```java
public static void isTypeAllowed(Class<?> simpleStrategyClass, Class<? extends Annotation> annotationClass)
```

## Inherited Methods

**From java.lang.Object:**
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