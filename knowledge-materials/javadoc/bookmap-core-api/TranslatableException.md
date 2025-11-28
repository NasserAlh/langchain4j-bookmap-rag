---
source_file: TranslatableException.html
package: velox.gui.utils.localization.translatable.exceptions
classes: TranslatableException
methods: TranslatableException, TranslatableException, TranslatableException, toLocalizedString, getTranslatableMessage
---

# TranslatableException

**Package:** velox.gui.utils.localization.translatable.exceptions

**Type:** Class

**Inheritance:** java.lang.Object → java.lang.Throwable → java.lang.Exception → TranslatableException

**All Implemented Interfaces:** Serializable, TranslatableComponent

## Description

The exception with a message that could be localized for different locales. The same as `TranslatableRuntimeException` but extends `Exception` (i.e. checked exception).

**See Also:**
- Serialized Form

## Constructors

### TranslatableException

```java
public TranslatableException()
```

### TranslatableException

```java
public TranslatableException(TranslatableComponent message)
```

### TranslatableException

```java
public TranslatableException(TranslatableComponent message, Throwable cause)
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

### getTranslatableMessage

```java
public TranslatableComponent getTranslatableMessage()
```

## Inherited Methods

**From class java.lang.Throwable:**
- `addSuppressed`, `fillInStackTrace`, `getCause`, `getLocalizedMessage`, `getMessage`, `getStackTrace`, `getSuppressed`, `initCause`, `printStackTrace`, `setStackTrace`, `toString`

**From class java.lang.Object:**
- `clone`, `equals`, `finalize`, `getClass`, `hashCode`, `notify`, `notifyAll`, `wait`

**From interface TranslatableComponent:**
- `toDefaultString`, `toLocalizedString`