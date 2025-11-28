---
source_file: Layer1ApiAlertGuiMessage.html
package: velox.api.layer1.messages
classes: Layer1ApiAlertGuiMessage
methods: Example of an addon that registers custom GUI panels, id, source, strategyName, guiPanelsProvider, isAdd, toString, builder
---

# Layer1ApiAlertGuiMessage

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → Layer1ApiAlertGuiMessage

**All Implemented Interfaces:** Layer1ApiStrategiesEchoMessagesLayer.StrategyEchoMessageFromLayer

## Description

Use this message to send GUI panels for configuration of `Layer1ApiSoundAlertMessage`'s, created by your addon (specified via `source`).

Internally Bookmap caches these messages, and if you want to remove your GUI panels - send another `Layer1ApiAlertGuiMessage` with the same **`id`** and **`isAdd` = false**

**Note:** for now multiple GUI's for alerts from the same addon **are not allowed**. If your addon sends multiple `Layer1ApiAlertGuiMessage`'s with **`isAdd` = true** - an exception will be thrown, and the addon will be unloaded. If you want to show a another GUI - you can un-register the previous one and then register another one. However, in most cases the provided functionality of `guiPanelsProvider` should be enough, as your `Function` can return different `StrategyPanel`[]

For an overview of the notification system, take a look at `Layer1ApiSoundAlertMessage` javadoc

### Example of an addon that registers custom GUI panels

To keep the example simple, lets create an addon, whose sole purpose is to add a (very basic) GUI panel to the Bookmap.

As always, we start with defining an entrypoint for our addon:

```java
@Layer1Attachable
@Layer1StrategyName("Simple alert GUI demo")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION2)
public class SimpleAlertGuiDemo implements Layer1ApiFinishable {

    private final Layer1ApiProvider provider;

    public SimpleAlertGuiDemo(Layer1ApiProvider provider) {
        this.provider = provider;
    }
}
```

Next, lets define the panels that we want to show. Here we will implement a rather basic example. However, we are only showing the concept, and you can make your GUIs as complex as you like.

Here we will create an inner class that extends `StrategyPanel`

```java
private static class SimpleGuiPanel extends StrategyPanel {

    public SimpleGuiPanel() {
        super("Simple alert GUI demo");
        add(new JLabel("Hello from Simple alert GUI demo!"));
    }
}
```

Moving on to the key part - sending our GUI to Bookmap. For that we should use `Layer1ApiAlertGuiMessage`:

```java
public SimpleAlertGuiDemo(Layer1ApiProvider provider) {
    this.provider = provider;

    guiMessage = Layer1ApiAlertGuiMessage
        .builder()
        .setSource(SimpleAlertGuiDemo.class)
        .setGuiPanelsProvider(declarationMessage -> new StrategyPanel[]{new SimpleGuiPanel()})
        .build();
    provider.sendUserMessage(guiMessage);
}
```

And this should do the job. New GUI panels should appear in *File -> Alerts -> Configure alerts -> Add alert...*.

It is also important to know that it is possible to remove your custom GUI panels from Bookmap:

```java
@Override
public void finish() {
    if (guiMessage != null) {
        Layer1ApiAlertGuiMessage removeGuiMessage = new Builder(guiMessage)
            .setIsAdd(false)
            .build();
        provider.sendUserMessage(removeGuiMessage);
    }
}
```

*Full example source code can be found in **DemoStrategies** project on Github - check out **`velox.api.layer1.simpledemo.alerts.simplegui.SimpleAlertGuiDemo`***

**See Also:**
- `Layer1ApiSoundAlertMessage`
- DemoStrategies

## Nested Classes

- `Layer1ApiAlertGuiMessage.Builder`

## Fields

### id

```java
public final String id
```

Id can be used to reference this message

### source

```java
public final Class<?> source
```

Class that created this message. The class must be annotated with `Layer1StrategyName`

### strategyName

```java
public final String strategyName
```

Name of the strategy that created this message, extracted from `Layer1StrategyName.value()`, displayed on the UI

### guiPanelsProvider

```java
public final Function<Layer1ApiSoundAlertDeclarationMessage, StrategyPanel[]> guiPanelsProvider
```

Function that returns GUI panels based on `Layer1ApiSoundAlertDeclarationMessage`  
If your GUI panels are opened with the intent to declare a new alert - the passed argument is **null**. Otherwise a user wants to modify an existing `Layer1ApiSoundAlertDeclarationMessage` - in that case the function obtains this declaration as an argument. You can use this declaration to pre-populate fields in your GUI.

### isAdd

```java
public final boolean isAdd
```

True if this message adds GUI panels. Otherwise, the panels from a message with the same `id` will be removed

## Methods

### toString

```java
public String toString()
```

**Overrides:** `toString` in class `Object`

### builder

```java
public static Layer1ApiAlertGuiMessage.Builder builder()
```

Creates builder to build `Layer1ApiAlertGuiMessage`.

**Returns:** created builder