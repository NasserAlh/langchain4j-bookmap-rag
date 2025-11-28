---
source_file: Layer1LocalizationBundle.html
package: velox.api.layer1.annotations
classes: Layer1LocalizationBundle
methods: value
---

# Layer1LocalizationBundle

**Package:** velox.api.layer1.annotations

**Type:** Annotation Interface

**Annotations:** 
- `@Target(TYPE)`
- `@Retention(RUNTIME)`

## Description

The general name of the bundle containing the translation.  
This bundle will be used to resolve *translationKeys* in others' annotations.  
If the *translationKey* is not found in the given *bundle* then it will be displayed as is.

In the current version it is applied only to `Layer1ActionMetadata.name()` and `Layer1ActionMetadata.groups()` values.  
It is allowed to use *translationKeys* and *plain text* values at the same time in those annotations.

Example of usage with `Layer1ActionMetadata`:

```java
@Layer1Injectable
@Layer1StrategyName("Action Strategy 1")
@Layer1LocalizationBundle("resources.StrategyTestBundle")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION2)
public class ActionStrategy1 extends Layer1ApiInjectorRelay implements Layer1ApiFinishable, Layer1ActionMapper {
...
    @Layer1ActionMetadata(id = "action_strategy1.say_hi", name = "Test.HelloWorld", groups = {"Alert Custom", "Test.Group1", "Test.GroupNotExist})
    public class HelloWorldAction implements Layer1ExternalAction {

        @Override
        public boolean performAction(String actionId, KeyEvent e) {
            provider.sendUserMessage(
                    Layer1ApiSoundAlertMessage.builder()
                            .setTextInfo("Hi everyone! :)")
                            .setSource(ActionStrategy1.class)
                            .setMetadata(this.getClass().getName())
                            .setShowPopup(true)
                            .build()
            );
            return true;
        }

        @Override
        public void onShortcutChanged(String actionId, Set<String> shortcuts) {}
    }
...
}
```

Content of the file *resources/StrategyTestBundle_en.properties* which will be used with *English* locale:

```
Test.HelloWorld=Hello world
Test.Group1=Action
```

With all of this in the keyboard shortcuts user will see the next path: `"Add-ons" -> "Action Strategy1" -> "Alert Custom" -> "Action" -> "Test.GroupNotExist" -> "Hello world"`

You can also use this annotation to declare the bundle to the specified annotation only:

```java
@Layer1Injectable
@Layer1StrategyName("Action Strategy 1")
@Layer1LocalizationBundle("resources.StrategyTestBundle")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION2)
public class ActionStrategy1 extends Layer1ApiInjectorRelay implements Layer1ApiFinishable, Layer1ActionMapper {
...
    @Layer1LocalizationBundle("resources.StrategyTestBundleForAnnotationOnly")
    @Layer1ActionMetadata(id = "action_strategy1.say_hi", name = "Test.HelloWorld", groups = {"Alert Custom", "Test.Group1", "Test.GroupNotExist})
    public class HelloWorldAction implements Layer1ExternalAction {
      ...
    }
...
}
```

In this case, per-annotation bundle will be applied in the priority to the per-class one, so per-class bundle will be ignored for this annotation.

## Required Elements

### value

```java
String value()
```

The general name of the bundle containing the translation.  
This bundle will be used to resolve *translationKeys* in others' annotations.  
If the *translationKey* is not found in the given *bundle* then it will be displayed as is.