---
source_file: LocalizedBundleProvider.html
package: velox.api.layer1.localization
classes: LocalizedBundleProvider
methods: getBundle, getCurrentULocale
---

# LocalizedBundleProvider

**Package:** velox.api.layer1.localization

**Type:** Interface

## Methods

### getBundle

```java
LocalizedBundle getBundle(String bundleName)
```

Translation files has next structure:

```
LocaleElements.properties
       |
    -----------------------------
   |                            |
Translation_fr.properties  Translation_en.properties
   |
 ------------------------------------
|                                   |
Translation_fr_CA.properties   Translation_fr_FR.properties
```

If data in translation file is absent for specific locale than it will be fetched from the `parent` translation file, and it will keep going to the base file until the data is found or an `MissingResourceException` is thrown.

For more info about structure of locale files please refer to `UResourceBundle` javadoc

**Parameters:**
- `bundleName` - Base file name where the translation is stored, without the extension and locale prefixes (i.e. file with translation named like this: "translation_us.properties", than base name is "translation").
  
  If the localization file is not in the root directory of the resources, you must specify the path to it in the bundleName, for example, your file is located in *"resources/locale/Translation_us.properties"* and taking into account the fact that the "resources" are the root directory then the bundleName should be specified as follows: bundleName = locale.Translation
  
  **NOTE: bundleName should be unique between different addons**

**Returns:** `LocalizedBundle` with current Bookmap locale

### getCurrentULocale

```java
com.ibm.icu.util.ULocale getCurrentULocale()
```

**Returns:** Current Bookmap locale