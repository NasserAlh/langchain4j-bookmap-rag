---
source_file: TemporaryFile.html
package: velox.api.layer1.common.temporaryfiles
classes: TemporaryFile
methods: TemporaryFile, TemporaryFile, getPath, create, dispose, dispose
---

# TemporaryFile

**Package:** velox.api.layer1.common.temporaryfiles

**Type:** Class

**Inheritance:** java.lang.Object → TemporaryFile

## Description

Manages files and folders inside temporary folder ensuring automatic deletion when unlocked or during startup. Only attempts to delete unlocked files/folders.

## Constructors

### TemporaryFile

```java
public TemporaryFile(Path pathToFile, boolean isDirectory)
```

Create a temporary file abstraction. Doesn't actually perform any filesystem operations yet.

**Parameters:**
- `pathToFile` - Relative path to the new file/directory within bookmap temporary folder
- `isDirectory` - If true temporary file will be a directory, otherwise it's a plain file

### TemporaryFile

```java
protected TemporaryFile(TemporaryFileDirectory temporaryFolder, Path filePathRelativeToFolder, boolean isDirectory)
```

## Methods

### getPath

```java
public Path getPath()
```

Provides full normalized path to managed file

### create

```java
public void create()
```

Creates lock file and, if `isDirectory` set to true also creates the empty folder.

### dispose

```java
public void dispose()
```

Unlock the file/folder and remove it (recursively, if it's a folder). Throws an exception if something goes wrong.

### dispose

```java
public void dispose(boolean removeFile, boolean throwException)
```

Unlock the file/folder and can remove it (recursively, if it's a folder). Can throw an exception if something goes wrong.

**Parameters:**
- `removeFile` - If true - remove the locked file/folder
- `throwException` - If true - exception is thrown on failure. Otherwise it's logged but not thrown.