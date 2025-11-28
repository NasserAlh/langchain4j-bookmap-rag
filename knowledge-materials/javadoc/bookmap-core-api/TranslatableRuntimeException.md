---
source_file: TranslatableRuntimeException.html
package: velox.gui.utils.localization.translatable.exceptions
classes: TranslatableRuntimeException
methods: TranslatableRuntimeException, TranslatableRuntimeException, toLocalizedString
---

# TranslatableRuntimeException

**Package:** velox.gui.utils.localization.translatable.exceptions

**Type:** Class

**Inheritance:**
- java.lang.Object
  - java.lang.Throwable
    - java.lang.Exception
      - java.lang.RuntimeException
        - TranslatableRuntimeException

**All Implemented Interfaces:** Serializable, TranslatableComponent

## Description

The exception with a message that could be localized for different locales. The same as `TranslatableException` but extends `RuntimeException` (i.e. unchecked exception).

**See Also:**
- Serialized Form

## Constructors

### TranslatableRuntimeException

```java
public TranslatableRuntimeException()
```

### TranslatableRuntimeException

```java
public TranslatableRuntimeException(TranslatableComponent message)
```

## Methods

### toLocalizedString

```java
public String toLocalizedString(com.ibm.icu.util.ULocale locale)
```

**Specified by:** `toLocalizedString` in interface `TranslatableComponent`

**Parameters:**
- `locale` - 

**Returns:** localized string in specific locale

---

**Methods inherited from class java.lang.Throwable:**
- `addSuppressed`, `fillInStackTrace`, `getCause`, `getLocalizedMessage`, `getMessage`, `getStackTrace`, `getSuppressed`, `initCause`, `printStackTrace`, `printStackTrace`, `printStackTrace`, `setStackTrace`, `toString`

**Methods inherited from class java.lang.Object:**
- `clone`, `equals`, `finalize`, `getClass`, `hashCode`, `notify`, `notifyAll`, `wait`, `wait`, `wait`

**Methods inherited from interface velox.gui.utils.localization.translatable.TranslatableComponent:**
- `toDefaultString`, `toLocalizedString`