---
source_file: ScreenSpaceCanvas.PreparedImage.html
package: velox.api.layer1.layers.strategies.interfaces
classes: ScreenSpaceCanvas.PreparedImage
methods: PreparedImage, getReadOnlyImage, getReadOnlyOpenglRepresentation
---

# ScreenSpaceCanvas.PreparedImage

**Package:** velox.api.layer1.layers.strategies.interfaces

**Type:** Class

**Enclosing Interface:** `ScreenSpaceCanvas`

**Inheritance:** `java.lang.Object` → `ScreenSpaceCanvas.PreparedImage`

## Description

Wrapper for an image. Reusing a `ScreenSpaceCanvas.PreparedImage` instance is encouraged - this improves rendering performance. Keep in mind that constructing `ScreenSpaceCanvas.PreparedImage` launches certain (relatively expansive) background preparation tasks (so avoid creating instances that you won't really need).

## Constructors

### PreparedImage

```java
public PreparedImage(BufferedImage image)
```

## Methods

### getReadOnlyImage

```java
public BufferedImage getReadOnlyImage()
```

Returns corresponding icon, do not modify it.

**Returns:** The corresponding BufferedImage (read-only)

### getReadOnlyOpenglRepresentation

```java
public int[] getReadOnlyOpenglRepresentation()
```

**Returns:** The OpenGL representation of the image as an integer array (read-only)