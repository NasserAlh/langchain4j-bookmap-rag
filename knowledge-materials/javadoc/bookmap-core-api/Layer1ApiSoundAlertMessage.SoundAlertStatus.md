---
source_file: Layer1ApiSoundAlertMessage.SoundAlertStatus.html
package: velox.api.layer1.messages
classes: Layer1ApiSoundAlertMessage.SoundAlertStatus
methods: PENDING, AWAITINGREPEAT, PLAYING, DONE, POPUPCLOSED, values, valueOf
---

# Layer1ApiSoundAlertMessage.SoundAlertStatus

**Package:** velox.api.layer1.messages

**Type:** Enum

**Enclosing class:** [`Layer1ApiSoundAlertMessage`](Layer1ApiSoundAlertMessage.html)

**Inheritance:** `java.lang.Object` → `java.lang.Enum<Layer1ApiSoundAlertMessage.SoundAlertStatus>` → `Layer1ApiSoundAlertMessage.SoundAlertStatus`

**All Implemented Interfaces:** `Serializable`, `Comparable<Layer1ApiSoundAlertMessage.SoundAlertStatus>`, `Constable`

## Enum Constants

### PENDING
```java
public static final Layer1ApiSoundAlertMessage.SoundAlertStatus PENDING
```
In queue, waiting its turn

### AWAITING_REPEAT
```java
public static final Layer1ApiSoundAlertMessage.SoundAlertStatus AWAITING_REPEAT
```
Repeated alert is waiting for delay to finish

### PLAYING
```java
public static final Layer1ApiSoundAlertMessage.SoundAlertStatus PLAYING
```
Started playing

### DONE
```java
public static final Layer1ApiSoundAlertMessage.SoundAlertStatus DONE
```
Done playing

### POPUP_CLOSED
```java
public static final Layer1ApiSoundAlertMessage.SoundAlertStatus POPUP_CLOSED
```
UI popup has been closed

## Methods

### values
```java
public static Layer1ApiSoundAlertMessage.SoundAlertStatus[] values()
```
Returns an array containing the constants of this enum class, in the order they are declared.

**Returns:** An array containing the constants of this enum class, in the order they are declared

### valueOf
```java
public static Layer1ApiSoundAlertMessage.SoundAlertStatus valueOf(String name)
```
Returns the enum constant of this class with the specified name. The string must match *exactly* an identifier used to declare an enum constant in this class. (Extraneous whitespace characters are not permitted.)

**Parameters:**
- `name` - The name of the enum constant to be returned

**Returns:** The enum constant with the specified name

**Throws:**
- `IllegalArgumentException` - If this enum class has no constant with the specified name
- `NullPointerException` - If the argument is null