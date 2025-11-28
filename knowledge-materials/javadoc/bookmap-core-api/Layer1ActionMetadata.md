---
source_file: Layer1ActionMetadata.html
package: velox.api.layer1.actions.annotations
classes: Layer1ActionMetadata
methods: groups, id, name
---

# Layer1ActionMetadata

**Package:** velox.api.layer1.actions.annotations

**Type:** Annotation Interface

```java
@Target(TYPE)
@Retention(RUNTIME)
@Repeatable(Layer1ActionMetadataList.class)
public @interface Layer1ActionMetadata
```

## Description

Mark your [`Layer1ExternalAction`](../Layer1ExternalAction.html) implementations with this annotation.

## Elements

### groups

```java
String[] groups()
```

Describes group hierarchy where the action will be located in the **Keyboard Shortcuts** window.

The root group for all external actions is **Addons/strategy_name**.

**Default:** `{}`

### id

```java
String id()
```

Action unique identifier. It's a mandatory field.

### name

```java
String name()
```

The action name you see in **Keyboard Shortcuts**. If it's not present the `id()` field will be used instead.

**Default:** `""`