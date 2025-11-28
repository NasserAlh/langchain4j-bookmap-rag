---
source_file: SettingsAccess.html
package: velox.api.layer1.messages.indicators
classes: SettingsAccess
methods: getSettings, setSettings
---

# SettingsAccess

**Package:** velox.api.layer1.messages.indicators

**Type:** Interface

## Description

Interface that can be used to save or get settings from config.

## Methods

### getSettings

```java
Object getSettings(String alias, String fullName, Class<?> settingsClass)
```

**Parameters:**
- `alias` - Instrument alias settings are for or null, if settings are global
- `fullName` - Strategy full name
- `settingsClass` - Class of your settings object

**Returns:** Settings object. If there was no compatible saved object for this request, new object will be created with default constructor

**Changing returned object will not change settings. You need to call `setSettings(String, String, Object, Class)` for this**

### setSettings

```java
void setSettings(String alias, String fullName, Object settings, Class<?> settingsClass)
```

**Parameters:**
- `alias` - Instrument alias settings are for, null if settings are global
- `fullName` - Strategy full name
- `settings` - Settings object you want to save
- `settingsClass` - Class of your settings object