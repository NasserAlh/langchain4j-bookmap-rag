---
source_file: HyperlinkHelper.html
package: velox.api.layer1.utils
classes: HyperlinkHelper
methods: HyperlinkHelper, openInBrowser, openInBrowser, addHyperlinkListener, removeHyperlinkListener
---

# HyperlinkHelper

**Package:** velox.api.layer1.utils

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.utils.HyperlinkHelper

## Constructors

### HyperlinkHelper

```java
public HyperlinkHelper()
```

## Methods

### openInBrowser

```java
public static void openInBrowser(String url)
```

Opens the provided link in the browser and sends event statistics that this link was opened. Where possible, prioritize the use of the `openInBrowser(String url, JComponent source)` method to provide better context.

**Parameters:**
- `url` - URL to be opened

### openInBrowser

```java
public static void openInBrowser(String url, JComponent component)
```

See `openInBrowser(String url)`

**Parameters:**
- `component` - The interaction with which triggered the opening of the link

### addHyperlinkListener

```java
public static void addHyperlinkListener(JEditorPane editorPane)
```

Adds `HyperlinkListener` that opens links in the browser when clicking on them. Make sure that you use links with protocol, otherwise an exception will be thrown when trying to follow the link.

**Parameters:**
- `editorPane` - Editor pane to which the listener will be added

### removeHyperlinkListener

```java
public static void removeHyperlinkListener(JEditorPane editorPane)
```

Removes the hyperlink listener if you added it before using `addHyperlinkListener(JEditorPane editorPane)`

**Parameters:**
- `editorPane` - Editor pane from which the listener will be removed