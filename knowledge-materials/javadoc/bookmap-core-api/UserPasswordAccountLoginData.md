---
source_file: UserPasswordAccountLoginData.html
package: velox.api.layer1.data
classes: UserPasswordAccountLoginData
methods: account, kick, UserPasswordAccountLoginData, UserPasswordAccountLoginData, UserPasswordAccountLoginData
---

# UserPasswordAccountLoginData

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → velox.api.layer1.data.UserPasswordLoginData → UserPasswordAccountLoginData

**All Implemented Interfaces:** Serializable, LoginData

**Deprecated**

**See Also:** Serialized Form

---

## Fields

### account

```java
public final int account
```

**Deprecated**

Account number

### kick

```java
public final boolean kick
```

**Deprecated**

Kick another connection if platform has connections limit that is exhausted.

---

## Constructors

### UserPasswordAccountLoginData

```java
public UserPasswordAccountLoginData(String user, String password, int account, boolean kick)
```

**Deprecated**

### UserPasswordAccountLoginData

```java
public UserPasswordAccountLoginData(String user, String password, String newPassword, int account, boolean kick)
```

**Deprecated**

### UserPasswordAccountLoginData

```java
public UserPasswordAccountLoginData(String user, String password, int account)
```

**Deprecated**

---

## Inherited Fields

**From class velox.api.layer1.data.UserPasswordLoginData:**
- `newPassword`
- `password` 
- `user`