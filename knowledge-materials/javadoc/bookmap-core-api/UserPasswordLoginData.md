---
source_file: UserPasswordLoginData.html
package: velox.api.layer1.data
classes: UserPasswordLoginData
methods: user, password, newPassword, UserPasswordLoginData, UserPasswordLoginData
---

# UserPasswordLoginData

**Package:** velox.api.layer1.data

**Type:** Class

**Inheritance:** java.lang.Object → UserPasswordLoginData

**All Implemented Interfaces:** Serializable, LoginData

**Direct Known Subclasses:** UserPasswordAccountLoginData, UserPasswordDemoLoginData

**See Also:** Serialized Form

## Fields

### user

```java
public final String user
```

### password

```java
public final String password
```

### newPassword

```java
public final String newPassword
```

If supported by platform password will be changed to this value. Null if no change is requested.

## Constructors

### UserPasswordLoginData

```java
public UserPasswordLoginData(String user, String password)
```

### UserPasswordLoginData

```java
public UserPasswordLoginData(String user, String password, String newPassword)
```