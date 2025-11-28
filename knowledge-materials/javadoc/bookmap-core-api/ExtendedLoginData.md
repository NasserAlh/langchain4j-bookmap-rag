---
source_file: ExtendedLoginData.html
package: velox.api.layer1.data
classes: ExtendedLoginData
methods: extendedData, ExtendedLoginData
---

# ExtendedLoginData

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** `java.lang.Object` → `velox.api.layer1.data.ExtendedLoginData`

**All Implemented Interfaces:** `Serializable`, `LoginData`

## Description

Implementation of `LoginData`. Contains a Map `<String, CredentialsSerializationField>` of extended login data. Your L0 provider will obtain it on `Layer1ApiAdminProvider.login(LoginData)` call if it is annotated with `Layer0CredentialsFieldsManager`.

**See Also:**
- `Layer0CredentialsFieldsManager`
- Serialized Form

## Fields

### extendedData

```java
public final Map<String, CredentialsSerializationField> extendedData
```

## Constructors

### ExtendedLoginData

```java
public ExtendedLoginData(Map<String, CredentialsSerializationField> extendedData)
```

## Methods Inherited

**From class `java.lang.Object`:**
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