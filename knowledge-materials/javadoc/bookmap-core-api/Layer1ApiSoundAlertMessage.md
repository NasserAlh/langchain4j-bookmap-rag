---
source_file: Layer1ApiSoundAlertMessage.html
package: velox.api.layer1.messages
classes: Layer1ApiSoundAlertMessage
methods: Notification system examples, Notification system step-by-step guide
---

# Layer1ApiSoundAlertMessage

**Package:** velox.api.layer1.messages

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.messages.Layer1ApiSoundAlertMessage

**All Implemented Interfaces:** Layer1ApiStrategiesEchoMessagesLayer.StrategyEchoMessageFromLayer

## Description

This message triggers Bookmap to show a notification/play sound alert.

### Notification system examples

If you want to jump right into examples, take a look at **[DemoStrategies](https://github.com/BookmapAPI/DemoStrategies)** project. Here is a brief explanation of what you can find there:

- **`velox.api.layer1.simpledemo.alerts.tradeprice.SimplePriceAlertDemo`** - a basic demo that shows how you can create and send a single type of alerts. The process of developing this addon is described step-by-step below, in the section *Example of an addon leveraging notification system.*
- **`velox.api.layer1.simpledemo.alerts.simplegui.SimpleAlertGuiDemo`** - a "Hello-World" style demo showing how to incorporate your own GUI for managing notifications. Implemented step-by-step in [`Layer1ApiAlertGuiMessage`](Layer1ApiAlertGuiMessage.html) javadoc.
- **`velox.api.layer1.simpledemo.alerts.tradeprice.CustomPriceAlertDemo`** - a more elaborate example of the notification system usage. Shows how an addon can create alerts dynamically, using its own GUI.
- **`velox.api.layer1.simpledemo.alerts.manual.Layer1ApiAlertDemo`** - a "synthetic" example, allowing you to see as many features of the notification system as possible. Alerts are sent *manually* from the strategies dialog.

### Notification system step-by-step guide

The notification system has multiple moving parts and might seem intimidating at first glance. Below is a step-by-step guide to a simple addon development which sends alerts based on some market event.

First, let's define a framework for interaction with the notification system.

Just like in many other parts of Bookmap, an addon communicates with the Bookmap via special messages objects. That is, your addon should listen for messages via [`Layer1ApiAdminListener.onUserMessage(Object)`](velox/api/layer1/Layer1ApiAdminListener.html#onUserMessage(java.lang.Object)) and send them with [`Layer1ApiAdminProvider.sendUserMessage(Object)`](velox/api/layer1/Layer1ApiAdminProvider.html#sendUserMessage(java.lang.Object))

#### Notification system workflow

The workflow looks like this:

- Register a UI for controlling addon's notifications with [`Layer1ApiAlertGuiMessage`](Layer1ApiAlertGuiMessage.html) (optional step, omitted in the example below)
- Declare a group of sound alerts by sending [`Layer1ApiSoundAlertDeclarationMessage`](Layer1ApiSoundAlertDeclarationMessage.html), store this message to later link it with actual notifications
- Setup listening for declarations messages with a flag isAdd = false
- Send an initial [`Layer1ApiAlertSettingsMessage`](Layer1ApiAlertSettingsMessage.html) for a new declaration, setup listening for alert settings coming from Bookmap
- When a trigger, defined internally by the addon occurs - send an actual alert with [`Layer1ApiSoundAlertMessage`](Layer1ApiSoundAlertMessage.html)
- (optional) Cancel currently active alert with [`Layer1ApiSoundAlertCancelMessage`](Layer1ApiSoundAlertCancelMessage.html) - this will close popup created by the alert, stop sound notification playback, and cancel all planned future executions if the alert is repeated ([`repeatCount`](#repeatCount) `> 1`)

#### Example of an addon leveraging notification system

Now, lets see how it all works together in a more realistic example

Lets say we want to develop an addon which tracks the trades, and if there is a trade with price larger than 10 - we notify a user.

First, we should create an entrypoint class, so or addon can be loaded by the Bookmap (this is the same process as for any other addon).

```java
@Layer1Attachable
@Layer1StrategyName("Simple price alert demo")
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION2)
public class SimplePriceAlertDemo implements
    Layer1ApiAdminAdapter,
    Layer1ApiDataAdapter,
    Layer1ApiInstrumentAdapter,
    Layer1ApiFinishable {

    private Layer1ApiProvider provider;

    public SimplePriceAlertDemo(Layer1ApiProvider provider) {
        this.provider = provider;

        ListenableHelper.addListeners(provider, this);
    }

    @Override
    public void finish() {
    }
}
```

Now, our addon can be loaded, and it also gets the underlying [`Layer1ApiProvider`](velox/api/layer1/Layer1ApiProvider.html) - the key entity in Bookmap API, which gives access to various types of events (note the subscription with `ListenableHelper`). Later we will also use the provider **to send messages to BM**. In addition, we need to implement [`Layer1ApiFinishable`](velox/api/layer1/Layer1ApiFinishable.html), but we don't need to add any implementation for now

Also, our class implements [`Layer1ApiAdminAdapter`](velox/api/layer1/Layer1ApiAdminAdapter.html) - which gives us ability to **listen for messages coming from BM** by implementing [`Layer1ApiAdminAdapter.onUserMessage(Object)`](velox/api/layer1/Layer1ApiAdminAdapter.html#onUserMessage(java.lang.Object))

In addition, we want to listen for trades, and for that we implement [`Layer1ApiDataAdapter`](velox/api/layer1/Layer1ApiDataAdapter.html), as its method [`Layer1ApiDataAdapter.onTrade(String, double, int, TradeInfo)`](velox/api/layer1/Layer1ApiDataAdapter.html#onTrade(java.lang.String,double,int,velox.api.layer1.data.TradeInfo)) does exactly what we want. Likewise, to show the correct trade price and size - we need to know the pips and size increment of the current instrument. We get this data from [`Layer1ApiInstrumentAdapter.onInstrumentAdded(String, InstrumentInfo)`](velox/api/layer1/Layer1ApiInstrumentAdapter.html#onInstrumentAdded(java.lang.String,velox.api.layer1.data.InstrumentInfo))

Thus, we store the information of the instruments by adding the `aliasToInstrumentInfo` map, and update it using methods of [`Layer1ApiInstrumentAdapter`](velox/api/layer1/Layer1ApiInstrumentAdapter.html):

```java
    private final Map<String, InstrumentInfo> aliasToInstrumentInfo = new ConcurrentHashMap<>();

    @Override
    public void onInstrumentAdded(String alias, InstrumentInfo instrumentInfo) {
        aliasToInstrumentInfo.put(alias, instrumentInfo);
    }

    @Override
    public void onInstrumentRemoved(String alias) {
        aliasToInstrumentInfo.remove(alias);
    }
```

Next step - lets listen for the trades, and start simple - write a message to logs when the trade with price > 10 occurs:

```java
@Override
public void onTrade(String alias, double price, int size, TradeInfo tradeInfo) {
    InstrumentInfo instrumentInfo = aliasToInstrumentInfo.get(alias);
    double realPrice = price * instrumentInfo.pips;
    double realSize = size * (1 / instrumentInfo.sizeMultiplier);
    if (realSize != 0 && realPrice > 10) {
        // Here, instead of writing to logs, we later want to send an alert
        Log.info(String.format("Trade of price > 10 occurred, actual price={%.2f}, size={%.2f}", realPrice, realSize));
    }
}
```

You might ask: *Why can't we just use the `price` and `size` - the arguments of `onTrade` method? Why should we bother multiplying it by the pips and dividing by sizeMultiplier?* And the answer is - Bookmap passes the price and size not as the "real" values, but as the *number of increments*. To make it clearer, lets say that for an instrument with `pips = 0.5` there was a trade with `realPrice = 50.0`. Now, we want to calculate the number of price increments to express the real price:  
`incrementsNumber = realPrice / pips = 50.0 / 0.5 = 100`.  
Thus, `100` is the value you will get in `price` argument, when the real trade price is `50.0`.

But, here we want to perform the reversed operation - get the real price from the number of price increments using pips. For this we will use the following formula:  
**`realPrice = priceIncrementsNumber * pips`**

The same goes for the trade size - it is also represented as a number of increments, but instead of the increment size we have a `sizeMultiplier` - which is a reversed value to size increment. As an example, say that there was a trade of `realSize = 50.0` with `sizeMultiplier = 10`. To calculate the number of size increments we will use the formula:  
`sizeIncrementsNumber = realSize * sizeMultiplier = 50.0 * 10 = 500`  
However, in this case we are interested in getting the real size from the number of size increments:  
**`realSize = sizeIncrementsNumber * (1 / sizeMultiplier)`**

Now, instead of plain logging, we want to use the notification system. Lets go through its workflow:

#### Register a UI for controlling addon's notifications with [`Layer1ApiAlertGuiMessage`](Layer1ApiAlertGuiMessage.html)

To keep this example simple, we won't implement GUI for it. However, you can take a look at [`Layer1ApiAlertGuiMessage`](Layer1ApiAlertGuiMessage.html) javadoc

#### Declare a group of sound alerts by sending [`Layer1ApiSoundAlertDeclarationMessage`](Layer1ApiSoundAlertDeclarationMessage.html), store this message to later link it with actual declarations

Lets think about our alert - how we want our user to see it. For this simple example, our alert will show popup, won't have sound notification and will be a single-shot (non-repeated) alert. It will be related to all available instruments aliases. A description for the trigger of this alert might be simply - "Trade price > 10"

With this description in mind lets create a declaration message and send it when our addon is loaded by Bookmap. This moment is indicated by [`UserMessageLayersChainCreatedTargeted`](UserMessageLayersChainCreatedTargeted.html) message. Thus, lets create an `initAlerts()` method, which will be called each time the addon is loaded. The method will send an alert declaration, and store it for later use as a class field:

```java
private Layer1ApiSoundAlertDeclarationMessage declarationMessage;

private final Object declarationLock = new Object();

@Override
public void onUserMessage(Object data) {
    if (data instanceof UserMessageLayersChainCreatedTargeted) {
        UserMessageLayersChainCreatedTargeted message = (UserMessageLayersChainCreatedTargeted) data;
        if (message.targetClass == SimplePriceAlertDemo.class) {
            synchronized (declarationLock) {
                initAlerts();
            }
        }
    }
}

private void initAlerts() {
    declarationMessage = Layer1ApiSoundAlertDeclarationMessage.builder()
        .setTriggerDescription("Trade price > 10")
        .setSource(SimplePriceAlertDemo.class)
        .setPopupAllowed(true)
        .setAliasMatcher(alias -> true)
        .build();
    provider.sendUserMessage(declarationMessage);
}
```

*Note:* We also added a synchronization lock - `declarationLock`. Although for now the synchronization doesn't make any sense as we access the `declarationMessage` from one thread, later it will be accessed from different threads. Thus, we want to prevent a race condition from happening.

If we did everything right - in Bookmap a new record should appear in the "Configure alerts" table - available via *File -> Alerts -> Configure alerts*. We **WON'T** see the notifications just yet, we are still setting them up.

Next step in the notifications workflow is strongly connected to this one:

#### Listen for declarations messages with a flag isAdd = false

The idea is - when your addon sees this type of declaration message - it **should stop** sending notifications of that type. Your addon gets this types of events when a user clicks "Remove alert" button in *Configure alerts* table mentioned above. Thus, lets setup listening for this type of messages:

```java
@Override
public void onUserMessage(Object data) {
    if (data instanceof UserMessageLayersChainCreatedTargeted) {
        UserMessageLayersChainCreatedTargeted message = (UserMessageLayersChainCreatedTargeted) data;
        if (message.targetClass == SimplePriceAlertDemo.class) {
            synchronized (declarationLock) {
                initAlerts();
            }
        }
    } else if (data instanceof Layer1ApiSoundAlertDeclarationMessage) {
        Layer1ApiSoundAlertDeclarationMessage obtainedDeclarationMessage = (Layer1ApiSoundAlertDeclarationMessage) data;
        if (obtainedDeclarationMessage.source == SimplePriceAlertDemo.class
            && !obtainedDeclarationMessage.isAdd) {
            synchronized (declarationLock) {
                declarationMessage = null;
            }
        }
    }
}
```

*A couple of notes:*

- Here we have only one declaration message, while in the real application you might have multiple - in that case you can store them in a Map using id as a key, and when a message with the same id arrives - remove the value from the Map. However, the main idea of this guide is to introduce concepts one-by-one, and use as little non-necessary functionality as possible, so we won't be bothering with the multiple declarations just yet.
- As you might have noticed, we are not using the value of the declaration message - and it will be fixed soon.

For now, lets go to the next step in the notifications workflow:

#### Send an initial [`Layer1ApiAlertSettingsMessage`](Layer1ApiAlertSettingsMessage.html) for a new declaration, and listen for alert settings coming from Bookmap

The settings message is connected to a declaration message, and it can be used **to notify your addon about settings changes**, or, vice versa - **your addon can notify Bookmap about the settings changes**. Now, before we send the actual notifications, let's define the settings that will be used in the alert message, and send them to Bookmap. Also, we want to set up listening for the settings messages, which will look just like the listening for the declarations messages in the previous step. Below is the result of code changes:

```java
private Layer1ApiAlertSettingsMessage settingsMessage;

private void initAlerts() {
    declarationMessage = Layer1ApiSoundAlertDeclarationMessage.builder()
        .setTriggerDescription("Trade price > 10")
        .setSource(SimplePriceAlertDemo.class)
        .setPopupAllowed(true)
        .setAliasMatcher(alias -> true)
        .build();
    provider.sendUserMessage(declarationMessage);

    settingsMessage = Layer1ApiAlertSettingsMessage
        .builder()
        .setDeclarationId(declarationMessage.id)
        .setPopup(true)
        .setSource(SimplePriceAlertDemo.class)
        .build();
    provider.sendUserMessage(settingsMessage);
}

@Override
public void onUserMessage(Object data) {
    if (data instanceof UserMessageLayersChainCreatedTargeted) {
        UserMessageLayersChainCreatedTargeted message = (UserMessageLayersChainCreatedTargeted) data;
        if (message.targetClass == SimplePriceAlertDemo.class) {
            synchronized (declarationLock) {
                initAlerts();
            }
        }
    } else if (data instanceof Layer1ApiSoundAlertDeclarationMessage) {
        Layer1ApiSoundAlertDeclarationMessage obtainedDeclarationMessage = (Layer1ApiSoundAlertDeclarationMessage) data;
        if (obtainedDeclarationMessage.source == SimplePriceAlertDemo.class
            && !obtainedDeclarationMessage.isAdd) {
            synchronized (declarationLock) {
                declarationMessage = null;
            }
        }
    } else if (data instanceof Layer1ApiAlertSettingsMessage) {
        Layer1ApiAlertSettingsMessage obtainedSettingsMessage = (Layer1ApiAlertSettingsMessage) data;
        if (obtainedSettingsMessage.source == SimplePriceAlertDemo.class) {
            settingsMessage = (Layer1ApiAlertSettingsMessage) data;
        }
    }
}
```

*Key points here:* we are sending the settings message, linking it to the specific declaration by id, *after this declaration has been sent*. As we decided in the very beginning, our notification has a popup, but no sound alert - and it is reflected in the settings message creation.

Moving on to the next step in the workflow:

#### When a trigger, defined internally by the addon occurs - send an actual alert with [`Layer1ApiSoundAlertMessage`](Layer1ApiSoundAlertMessage.html)

Now that we have everything in place, we are ready to send the actual notification:

```java
@Override
public void onTrade(String alias, double price, int size, TradeInfo tradeInfo) {
    InstrumentInfo instrumentInfo = aliasToInstrumentInfo.get(alias);
    double realPrice = price * instrumentInfo.pips;
    double realSize = size * (1 / instrumentInfo.sizeMultiplier);
    if (realSize != 0 && realPrice > 10) {
        Log.info(String.format("Trade of price > 10 occurred, actual price={%.2f}, size={%.2f}", realPrice, realSize));
        synchronized (declarationLock) {
            if (declarationMessage != null) {
                Layer1ApiSoundAlertMessage soundAlertMessage = Layer1ApiSoundAlertMessage.builder()
                    .setAlias(alias)
                    .setTextInfo(String.format("Trade actual price={%.2f}, size={%.2f}", realPrice, realSize))
                    .setAdditionalInfo("Trade of price > 10")
                    .setShowPopup(settingsMessage.popup)
                    .setAlertDeclarationId(declarationMessage.id)
                    .setSource(SimplePriceAlertDemo.class)
                    .build();
                provider.sendUserMessage(soundAlertMessage);
            }
        }
    }
}
```

Here we specify all the data we want to show on the alert. Note that this message is linked with the [`Layer1ApiSoundAlertDeclarationMessage`](Layer1ApiSoundAlertDeclarationMessage.html) by the [`alertDeclarationId`](#alertDeclarationId) field, and the settings (popup/sound notifications status) are taken from the stored [`Layer1ApiAlertSettingsMessage`](Layer1ApiAlertSettingsMessage.html). This ensures data integrity between Bookmap and your addon.

Note that although [`Layer1ApiSoundAlertMessage`](Layer1ApiSoundAlertMessage.html) has many fields which might look intimidating, only