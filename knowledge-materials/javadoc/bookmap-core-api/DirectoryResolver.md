---
source_file: DirectoryResolver.html
package: velox.api.layer1.common
classes: DirectoryResolver
methods: DirectoryResolver, getBookmapRootDirectory, getBookmapDirectoryByName, getConfigDirectory, getLogsDirectory, getFeedsDirectory, getErrorReportsDirectory, getScreenshotsDirectory, getDiagnosticLogsDirectory, getSoundsDirectory, getLayer0ApiModulesDirectory, getLayer1ApiModulesDirectory, getLocalDataCacheDirectory, getRemoteDataCacheDirectory, getDataLibraryDirectory, getTemporaryDirectory, getNativeErrorLogsDirectory, setPermissions
---

# DirectoryResolver

**Package:** velox.api.layer1.common

**Type:** Class

**Inheritance:** java.lang.Object → DirectoryResolver

## Description

This class contains access methods to Bookmap folder structure

## Constructors

### DirectoryResolver

```java
public DirectoryResolver()
```

## Methods

### getBookmapRootDirectory

```java
public static Path getBookmapRootDirectory()
```

Allows to access Bookmap root directory
On windows: C:\Bookmap by default, may be changed during installation
On mac: ~/Library/Application Support/Bookmap
On linux: ~/.bookmap

**Returns:** Bookmap root directory

### getBookmapDirectoryByName

```java
public static Path getBookmapDirectoryByName(String folderName)
```

Allows to get subfloder in Bookmap directory.
Can be used to create custom folders or files.
Example:
```
{
    Path myFolder = DirectoryResolver.getBookmapDirectoryByName("MyFolder");
    Files.createDirectories(myFolder);
    myFolder.resolve("myFile").toFile().createNewFile();
}
```

**Parameters:**
- `folderName` - Name of subfolder under Bookmap root directory

**Returns:** Path representing subfolder with name folderName under Bookmap root directory

### getConfigDirectory

```java
public static Path getConfigDirectory()
```

Config directory is where all configuration files for Bookmap are stored.
Also this is working directory for Bookmap java process.

**Returns:** Path representing Config directory under Bookmap root directory

### getLogsDirectory

```java
public static Path getLogsDirectory()
```

### getFeedsDirectory

```java
public static Path getFeedsDirectory()
```

### getErrorReportsDirectory

```java
public static Path getErrorReportsDirectory()
```

### getScreenshotsDirectory

```java
public static Path getScreenshotsDirectory()
```

### getDiagnosticLogsDirectory

```java
public static Path getDiagnosticLogsDirectory()
```

### getSoundsDirectory

```java
public static Path getSoundsDirectory()
```

### getLayer0ApiModulesDirectory

```java
public static Path getLayer0ApiModulesDirectory()
```

### getLayer1ApiModulesDirectory

```java
public static Path getLayer1ApiModulesDirectory()
```

### getLocalDataCacheDirectory

```java
public static Path getLocalDataCacheDirectory()
```

### getRemoteDataCacheDirectory

```java
public static Path getRemoteDataCacheDirectory()
```

### getDataLibraryDirectory

```java
public static Path getDataLibraryDirectory()
```

### getTemporaryDirectory

```java
public static Path getTemporaryDirectory()
```

### getNativeErrorLogsDirectory

```java
public static Path getNativeErrorLogsDirectory()
```

Directory where JVM crash logs are stored.
By default, JVM stores crash logs to the process working directory.
It's is equal to the Config directory on Windows and Linux.
On Mac it could be different, but on crash we execute script to move crash logs to Config directory.
So it's always equal to the Config directory within the current implementation.

**Returns:** Path representing directory where JVM crash logs are configured.

### setPermissions

```java
public static void setPermissions(Path path, PosixFilePermission... permissions)
```

Set permissions for a file or directory.

**Parameters:**
- `path` - represents a path in a filesystem to directory or file