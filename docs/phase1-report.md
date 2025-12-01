# Phase 1 Implementation Report: JavaFX 25 GUI Setup

**Date:** December 1, 2025
**Phase:** 1 - Project Setup & Basic UI
**Status:** Completed with known issues

---

## Executive Summary

Phase 1 of the JavaFX GUI implementation has been completed. The basic UI framework is functional with AtlantaFX theming, but several technical challenges were encountered related to JDK 25 and JavaFX 25 tooling compatibility. This report documents the issues faced, workarounds implemented, and lessons learned.

---

## 1. Problems Encountered

### 1.1 javafx-maven-plugin Incompatibility (BLOCKER - Worked Around)

**Issue:** The standard `javafx-maven-plugin` (version 0.0.8) cannot handle JavaFX 25 jars compiled with JDK 25 (class file version 69).

**Error Message:**
```
Unsupported major.minor version 67.0
```

**Root Cause:** The plugin uses the system JDK (21) to analyze JavaFX jar metadata, but JavaFX 25 jars are compiled for JDK 25 (class file version 69). The plugin itself doesn't support the toolchain mechanism properly.

**Attempted Solutions:**
1. Upgraded to javafx-maven-plugin 0.0.9 - **Does not exist**
2. Used exec-maven-plugin with `<toolchain>jdk</toolchain>` - **Did not work** (still used system JDK)
3. Used `${java.home}/bin/java` - **Resolved to system JDK 21, not toolchain JDK 25**

**Final Workaround:** Created `run-gui.bat` script that explicitly sets `JAVA_HOME` to JDK 25 path before running.

**Impact:** Cannot use `mvn javafx:run` command. Must use batch script or direct java command.

---

### 1.2 exec-maven-plugin Toolchain Support (BLOCKER - Worked Around)

**Issue:** The `exec:exec` goal doesn't properly inherit the JDK from Maven Toolchains.

**Error Message:**
```
java.lang.UnsupportedClassVersionError: com/example/chatbot/Launcher has been compiled
by a more recent version of the Java Runtime (class file version 69.0),
this version of the Java Runtime only recognizes class file versions up to 65.0
```

**Root Cause:** While `exec:java` goal inherits the classpath properly, `exec:exec` uses the system Java even when `<toolchain>jdk</toolchain>` is specified.

**Workaround:** Use `run-gui.bat` which sets `JAVA_HOME` explicitly.

---

### 1.3 JavaFX Module Path Conflicts (RESOLVED)

**Issue:** When using `--module-path` with all dependencies, module conflicts occur.

**Error Message:**
```
java.lang.module.FindException: Two versions of module org.apache.commons.logging
found in target\lib (jcl-over-slf4j-2.0.16.jar and commons-logging-1.3.4.jar)
```

**Root Cause:** The project has conflicting transitive dependencies that both export the same module name.

**Resolution:** Switched to non-modular (classpath) approach instead of module-path. This requires:
1. A `Launcher.java` class that doesn't extend `Application`
2. Running with `-cp` instead of `--module-path`

---

### 1.4 JavaFX Classpath Mode Warnings (KNOWN - Deferred)

**Issue:** Running JavaFX from classpath produces warnings about unsupported configuration.

**Warning Messages:**
```
WARNING: Unsupported JavaFX configuration: classes were loaded from 'unnamed module @...'
WARNING: A restricted method in java.lang.System has been called
WARNING: java.lang.System::load has been called by com.sun.glass.utils.NativeLibLoader
in an unnamed module
WARNING: Use --enable-native-access=ALL-UNNAMED to avoid a warning for callers in this module
WARNING: Restricted methods will be blocked in a future release unless native access is enabled
```

**Status:** Partially mitigated with `--enable-native-access=ALL-UNNAMED` flag. The "Unsupported JavaFX configuration" warning persists but doesn't affect functionality.

**Impact:** Cosmetic only. Application runs correctly despite warnings.

**Future Risk:** The warning states "Restricted methods will be blocked in a future release." This may require migration to proper module-path configuration in future JDK versions.

---

### 1.5 Duplicate Plugin Declaration (RESOLVED)

**Issue:** Initially had duplicate `exec-maven-plugin` declarations in pom.xml.

**Warning:**
```
'build.plugins.plugin.(groupId:artifactId)' must be unique but found duplicate
declaration of plugin org.codehaus.mojo:exec-maven-plugin
```

**Resolution:** Consolidated into single plugin declaration with single execution configuration.

---

## 2. Known Issues & Technical Debt

### 2.1 Deferred Issues (Not Blocking)

| Issue | Severity | Description | Mitigation |
|-------|----------|-------------|------------|
| Module path conflicts | Medium | Cannot run in proper modular mode | Using classpath mode |
| JavaFX warnings | Low | "Unsupported configuration" warnings | Application works; cosmetic issue |
| No `mvn javafx:run` | Medium | Standard Maven command doesn't work | Use `run-gui.bat` |
| CSS not auto-loading | Low | Must manually add stylesheet to scene | Added in `App.java` |

### 2.2 Architecture Decisions with Trade-offs

1. **Non-modular Mode:** Chose classpath over module-path for simplicity. Trade-off: potential future compatibility issues.

2. **Launcher Class Pattern:** Required extra class to bootstrap JavaFX from classpath. Trade-off: slight complexity increase.

3. **Batch Script for Running:** Less portable than pure Maven. Trade-off: Windows-specific solution.

---

## 3. Bugs Still Lingering

### 3.1 Theme Toggle State Sync
**Description:** The theme toggle button text ("Dark"/"Light") doesn't visually update the button style immediately - only the application theme changes.

**Status:** Minor UX issue, deferred to Phase 4 (Settings & Controls).

### 3.2 Chat History Not Persisted
**Description:** Chat history list shows placeholder items, not actual history.

**Status:** Expected - will be implemented in Phase 5.

### 3.3 Send Button Enter Key Behavior
**Description:** Enter key in TextArea may insert newline before consuming the event in some edge cases.

**Status:** Minor, needs testing in Phase 2.

---

## 4. Lessons Learned

### 4.1 Tooling Lags Behind JDK/JavaFX Releases

**Lesson:** Maven plugins and tooling often lag behind the latest JDK and library releases by months or years.

**Recommendation:** When using cutting-edge versions (like JDK 25 released September 2025), plan for manual workarounds and script-based solutions.

### 4.2 Maven Toolchains Have Limitations

**Lesson:** Maven Toolchains work well for compilation (`maven-compiler-plugin`) but poorly for execution (`exec-maven-plugin`, `javafx-maven-plugin`).

**Recommendation:** For execution, use explicit batch/shell scripts that set `JAVA_HOME` rather than relying on toolchain inheritance.

### 4.3 JavaFX Module Path is Complex

**Lesson:** JavaFX 9+ module system adds complexity, especially when:
- Dependencies conflict at module level
- Running from classpath (non-modular)
- Mixing modular and non-modular code

**Recommendation:** For simpler projects, classpath mode with Launcher pattern is acceptable. For production apps, invest time in proper module-info.java configuration.

### 4.4 AtlantaFX Works Seamlessly

**Lesson:** AtlantaFX 2.1.0 integrates smoothly with JavaFX 25 with no compatibility issues.

**Recommendation:** Continue using AtlantaFX for theming; it's well-maintained and JDK-agnostic.

### 4.5 Incremental Validation is Critical

**Lesson:** Testing each component (compile, run, theme, FXML binding) incrementally saved significant debugging time.

**Recommendation:** Always validate each step before moving to the next, especially when dealing with version mismatches.

---

## 5. Configuration Summary

### Working Configuration

| Component | Version/Setting |
|-----------|-----------------|
| JDK | 25.0.1+8 (Eclipse Adoptium) |
| JavaFX | 25.0.1 |
| AtlantaFX | 2.1.0 |
| Maven Compiler Plugin | 3.13.0 |
| Maven Toolchains Plugin | 3.2.0 |
| exec-maven-plugin | 3.5.0 |

### Run Command (Working)
```batch
"C:\Program Files\Eclipse Adoptium\jdk-25.0.1+8\bin\java" ^
  --enable-native-access=ALL-UNNAMED ^
  -cp "target/classes;target/lib/*" ^
  com.example.chatbot.Launcher
```

### Run Command (NOT Working)
```bash
mvn javafx:run  # Plugin incompatible with JDK 25
mvn exec:java   # Uses system JDK 21, not toolchain JDK 25
```

---

## 6. Recommendations for Phase 2

1. **Keep Current Architecture:** The classpath + Launcher pattern works; don't over-engineer.

2. **Add module-info.java Later:** Consider adding modular support only if deployment requires it.

3. **Test on Linux/macOS:** Current batch script is Windows-only; create `run-gui.sh` for cross-platform support.

4. **Monitor javafx-maven-plugin:** Check for updates that add JDK 25 support.

5. **Document the "Why":** Update CLAUDE.md with the JavaFX run instructions for future reference.

---

## 7. Files Modified/Created in Phase 1

### New Files
- `src/main/java/com/example/chatbot/App.java`
- `src/main/java/com/example/chatbot/Launcher.java`
- `src/main/java/com/example/chatbot/view/MainController.java`
- `src/main/resources/fxml/main.fxml`
- `src/main/resources/css/app.css`
- `run-gui.bat`
- `docs/javafx-gui-plan.md` (updated)
- `docs/phase1-report.md` (this file)

### Modified Files
- `pom.xml` - Added JavaFX 25, AtlantaFX 2.1.0, toolchain config
- `~/.m2/toolchains.xml` - Added JDK 25 entry
- `README.md` - Updated prerequisites

---

## 8. Conclusion

Phase 1 is complete with a functional GUI skeleton. The main technical challenge was the mismatch between Maven plugin ecosystem and JDK 25, requiring workarounds for execution. The application runs correctly with the batch script approach, and the foundation is solid for Phase 2 (Chat Functionality) implementation.

**Next Steps:** Proceed to Phase 2 - Connect `DeepSeekChatbot` to the GUI ViewModel for functional chat.
