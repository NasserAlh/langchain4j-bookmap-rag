---
source_file: Layer1StrategyDateLicensed.html
package: velox.api.layer1.annotations
classes: Layer1StrategyDateLicensed
methods: value
---

# Layer1StrategyDateLicensed

**Package:** velox.api.layer1.annotations

**Type:** Annotation Interface

```java
@Retention(RUNTIME)
public @interface Layer1StrategyDateLicensed
```

## Description

Use this annotation if your strategy should only be available if it user has corresponding license field set to a date ahead of current date.

**NOTE:** right now you need contact Bookmap support to discuss how to control this field. In the future there will be a way to control it directly.

## Required Elements

### value

```java
String value()
```

Your strategy ID that you received from Bookmap support