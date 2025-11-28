---
source_file: Layer1GlobalStorage.html
package: velox.api.layer1.common
classes: Layer1GlobalStorage
methods: Layer1GlobalStorage, setStorageCallbacks, set, get
---

# Layer1GlobalStorage

**Package:** velox.api.layer1.common

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.common.Layer1GlobalStorage

## Description

Global storage. Only use it if you don't have other more appropriate ways to store the data. Layer1 modules should use `SettingsAccess` instead.

## Constructors

### Layer1GlobalStorage

```java
public Layer1GlobalStorage()
```

## Methods

### setStorageCallbacks

```java
public static void setStorageCallbacks(Function<String, String> valuesGetter, BiConsumer<String, String> valuesSetter)
```

Initialize storage backend. Invoked by Bookmap. Plugins should not call this method.

### set

```java
public static void set(String key, String value)
```

Store value globally.

**Parameters:**
- `key` - Key to use. Must be unique and identifiable (e.g. prefix it with your module name).
- `value` - Value to store

### get

```java
public static String get(String key)
```

Load value from global storage

**Parameters:**
- `key` - Key to use. Must be unique and identifiable (e.g. prefix it with your module name).

**Returns:** Previously stored value or null if not found