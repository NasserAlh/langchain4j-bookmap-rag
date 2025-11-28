---
source_file: Parameter.html
package: velox.api.layer1.simplified
classes: Parameter
methods: name, step, minimum, maximum, reloadOnChange
---

# Parameter

**Package:** velox.api.layer1.simplified

**Type:** Annotation Interface

```java
@Target(FIELD)
@Retention(RUNTIME)
public @interface Parameter
```

## Description

A field annotated with the Parameter annotation can be configured from Bookmap's GUI ("Configure api plugins" dialog).

**Field types accepted:**
- Byte, Short, Integer, Long, Float, Double (numeric types);
- String;
- Boolean;
- Color;

Any Parameter field must be initialized in a user's class (custom module).

**Numeric types:**
A spinner gets created for any numeric field. A user should specify spinner values:
- step;
- minimum value;
- maximum value.

Otherwise, default values will be used:
- step = 1;
- minimum value = 0;
- maximum value = maximal field type value (e.g. Integer.MAX_VALUE if the field is of Integer type).

**Non-numeric field types (String, Boolean, Color):**
Step, minimum and maximum values for any other field type should not be specified. Even if specified, they will be ignored.

GUI elements generated for non-numeric field types:
- String - text field;
- Boolean - checkbox;
- Color - color interface.

## Elements

### name

```java
String name()
```

**Required:** Yes

name displayed in UI

---

### step

```java
double step()
```

**Required:** No

**Default:** `1.0`

step value for a numeric field type only

---

### minimum

```java
double minimum()
```

**Required:** No

**Default:** `0.0/0.0`

minimum value for a numeric field type only

---

### maximum

```java
double maximum()
```

**Required:** No

**Default:** `0.0/0.0`

maximum value for a numeric field type only

---

### reloadOnChange

```java
boolean reloadOnChange()
```

**Required:** No

**Default:** `true`

if true, the instrument gets reloaded after the parameter has been changed