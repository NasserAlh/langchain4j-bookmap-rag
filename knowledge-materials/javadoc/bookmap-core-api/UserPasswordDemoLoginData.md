---
source_file: UserPasswordDemoLoginData.html
package: velox.api.layer1.data
classes: UserPasswordDemoLoginData
methods: isDemo, kick, UserPasswordDemoLoginData, UserPasswordDemoLoginData
---

# UserPasswordDemoLoginData

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.data.UserPasswordLoginData → UserPasswordDemoLoginData

**All Implemented Interfaces:** Serializable, LoginData

**See Also:** Serialized Form

## Fields

### isDemo

```java
public final boolean isDemo
```

True if it's a demo account. Necessary for some platforms where connection point depends on this.

### kick

```java
public final boolean kick
```

Kick another connection if platform has connections limit that is exhausted.

**Fields inherited from class velox.api.layer1.data.UserPasswordLoginData:**
- `newPassword`
- `password` 
- `user`

## Constructors

### UserPasswordDemoLoginData

```java
public UserPasswordDemoLoginData(String user, String password, boolean isDemo, boolean kick)
```

### UserPasswordDemoLoginData

```java
public UserPasswordDemoLoginData(String user, String password, boolean isDemo)
```

**Methods inherited from class java.lang.Object:**
- `clone()`
- `equals(Object)`
- `finalize()`
- `getClass()`
- `hashCode()`
- `notify()`
- `notifyAll()`
- `toString()`
- `wait()`
- `wait(long)`
- `wait(long, int)`