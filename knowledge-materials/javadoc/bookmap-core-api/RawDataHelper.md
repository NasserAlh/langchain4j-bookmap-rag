---
source_file: RawDataHelper.html
package: velox.api.layer1.providers.helper
classes: RawDataHelper
methods: RawDataHelper, sendRawData, isRawDataRecordingEnabled
---

# RawDataHelper

**Package:** velox.api.layer1.providers.helper

**Type:** Class

**Inheritance:** java.lang.Object → RawDataHelper

## Description

You can use this helper class to record raw data. Main purpose of this feature is to simplify debugging. Raw data can be anything, typically it is whatever you get from your data source. It's stored inside feed file so if something is not processed properly you will be able to see what was the input that caused it.

To enable raw data recording in Bookmap set l1_api_record_raw_data:1 in config (inside "general" section). To enable replay set l1_api_play_raw_data:1 - this will enable display of the raw data in the log file, both in replay and live (in live it will only work if recording is enabled too). Note that due to restrictions imposed by some exchanges we are forced to have it disabled by default - contact support to have it enabled (this can be done for licenses that don't permit access to such exchanges)

## Constructors

### RawDataHelper

```java
public RawDataHelper()
```

## Methods

### sendRawData

```java
public static void sendRawData(String data, List<Layer1ApiAdminListener> adminListeners)
```

Record raw data.

**Parameters:**
- `data` - Raw data itself. Any string - it will be saved to the feed file and/or printed to the log file.
- `adminListeners` - Listeners used to send raw data.

**Throws:**
- `IllegalStateException` - If `isRawDataRecordingEnabled()` returns false

### isRawDataRecordingEnabled

```java
public static boolean isRawDataRecordingEnabled()
```

Check if raw data recording enabled. Only call `sendRawData(String, List)` if this method returns true. Allows to save some computations (e.g. costly data conversion into string representation) in case when raw data is not needed. Returned value does not change unless Bookmap was restarted.

**Returns:** True if raw data recording enabled