# JavaFX GUI Plan for LangChain4j DeepSeek Chatbot

## Overview

This document outlines the implementation plan for building a modern desktop GUI for the LangChain4j DeepSeek Chatbot using **JavaFX 25** (latest LTS release, September 2025).

---

## 1. Technology Stack

### Core Framework
| Component | Version | Notes |
|-----------|---------|-------|
| **JDK** | **25** | LTS release, recommended for JavaFX 25 |
| **JavaFX** | 25.0.1 | Latest LTS (requires JDK 23+, JDK 25 recommended) |
| **LangChain4j** | 1.0.0-beta1 | Existing chatbot framework |
| **AtlantaFX** | 2.0.1+ | Modern CSS theme library (GitHub Primer-inspired) |

### Why JDK 25?
- **Both LTS releases** - JavaFX 25 and JDK 25 are both Long-Term Support
- **Recommended pairing** - JavaFX 25 release notes explicitly recommend JDK 25
- **Preview features** - Title bar controls work optimally with matching JDK
- **No workarounds** - All compatibility flags removed

> **Note:** JDK 21 remains as the system default for Bookmap addon development (separate projects).

### JavaFX 25 New Features to Leverage

#### Controls in Title Bar (Preview) - [JDK-8313424](https://bugs.openjdk.org/browse/JDK-8313424)
Place JavaFX controls directly in the window title bar for a modern, integrated look.

#### RichTextArea with CSS Styling - [JDK-8355774](https://bugs.openjdk.org/browse/JDK-8355774)
Enhanced text editing with CSS-styled highlights - perfect for chat input with formatting.

#### CSS Media Feature Queries - [JDK-8345348](https://bugs.openjdk.org/browse/JDK-8345348)
Responsive styling based on platform preferences (dark mode, reduced motion, etc.).

#### TextFlow TabStopPolicy - [JDK-8314482](https://bugs.openjdk.org/browse/JDK-8314482)
Better text layout control for code blocks in AI responses.

#### Public Text Layout API - [JDK-8341670](https://bugs.openjdk.org/browse/JDK-8341670)
Access to text layout info for custom rendering of chat messages.

#### Multi-line Prompt Text for TextArea - [JDK-8335547](https://bugs.openjdk.org/browse/JDK-8335547)
Better placeholder text support for the chat input area.

#### Improved Accessibility (macOS)
- NSAccessibilitySlider, Stepper, ProgressIndicator, Group, Image, TabGroup protocols
- Better screen reader support for all UI elements

### Runtime Changes
```bash
# No longer needed in JavaFX 25 (fixed in JDK-8334137):
# --sun-misc-unsafe-memory-access=allow

# Linux requirement: GTK 3.20 or later
```

---

## 2. Architecture: MVVM Pattern

Using **Model-View-ViewModel (MVVM)** for:
- Better testability (ViewModel can be tested without UI)
- Clean separation of concerns
- Reactive data binding with JavaFX properties

### Package Structure
```
src/main/java/com/example/chatbot/
├── DeepSeekChatbot.java          # Existing - becomes part of Model
├── model/
│   ├── ChatMessage.java          # Message data object
│   ├── ChatSession.java          # Conversation state
│   └── Settings.java             # Application settings
├── viewmodel/
│   ├── ChatViewModel.java        # Main chat logic
│   ├── SettingsViewModel.java    # Settings panel logic
│   └── RagViewModel.java         # RAG status and controls
├── view/
│   ├── MainView.java             # Primary application window
│   ├── ChatView.java             # Chat interface component
│   ├── SettingsView.java         # Settings panel
│   └── components/
│       ├── MessageBubble.java    # Custom chat bubble control
│       ├── StreamingText.java    # Animated text for streaming
│       └── StatusBar.java        # Token usage display
└── App.java                      # JavaFX Application entry point
```

### FXML Structure
```
src/main/resources/
├── fxml/
│   ├── main.fxml                 # Main window layout
│   ├── chat.fxml                 # Chat area layout
│   └── settings.fxml             # Settings dialog
├── css/
│   ├── app.css                   # Application-specific styles
│   └── chat-bubbles.css          # Message bubble styling
└── images/
    └── icons/                    # SVG icons
```

---

## 3. UI Design

### Main Window Layout (with Title Bar Controls - JavaFX 25 Preview)
```
┌─────────────────────────────────────────────────────────────┐
│  [Logo] DeepSeek Chatbot    [Model ▼] [⚙]      [─][□][×]   │  ← Controls in title bar!
├─────────────────────────────────────────────────────────────┤
│  ┌─ Sidebar ──────┐  ┌─ Chat Area ────────────────────────┐ │
│  │                │  │                                    │ │
│  │  [+ New Chat]  │  │  ┌──────────────────────────────┐  │ │
│  │                │  │  │ User: How do I use the API?  │  │ │
│  │  Chat History  │  │  └──────────────────────────────┘  │ │
│  │  ────────────  │  │                                    │ │
│  │  > Session 1   │  │  ┌──────────────────────────────┐  │ │
│  │    Session 2   │  │  │ DeepSeek: To use the API...  │  │ │
│  │    Session 3   │  │  │ [streaming animation]        │  │ │
│  │                │  │  └──────────────────────────────┘  │ │
│  │                │  │                                    │ │
│  │  ────────────  │  ├────────────────────────────────────┤ │
│  │  [📊 RAG: ON]  │  │  ┌────────────────────────┐ [Send] │ │
│  │  [🌙 Theme]    │  │  │ Type your message...   │        │ │ ← Multi-line prompt
│  │                │  │  │ (supports multiple     │        │ │
│  └────────────────┘  │  │  lines of placeholder) │        │ │
│                      │  └────────────────────────┘        │ │
│                      ├────────────────────────────────────┤ │
│                      │  [Stream ✓] [RAG ○] │ 150↓ 89↑ $0.01│ │
│                      └────────────────────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
```

### Theme: AtlantaFX with Primer Dark/Light
```java
// Apply modern theme
Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
```

### CSS Media Queries (JavaFX 25 Feature)
```css
/* Responsive to system dark mode preference */
@media (prefers-color-scheme: dark) {
    .root {
        -fx-base: #1a1a1a;
        -fx-background: #0d1117;
    }
}

/* Respect reduced motion preference */
@media (prefers-reduced-motion: reduce) {
    .streaming-cursor {
        -fx-opacity: 1; /* No blinking animation */
    }
}
```

### Color Palette
| Element | Light Mode | Dark Mode |
|---------|------------|-----------|
| Background | `#ffffff` | `#0d1117` |
| User Bubble | `#ddf4ff` | `#1f6feb` |
| AI Bubble | `#f6f8fa` | `#21262d` |
| Accent | `#0969da` | `#58a6ff` |
| Text | `#24292f` | `#c9d1d9` |

---

## 4. Implementation Phases

### Phase 1: Project Setup & Basic UI
1. Update `pom.xml` with JavaFX 25 dependencies
2. Upgrade JDK from 21 to 23+
3. Create `App.java` entry point
4. Implement basic FXML layout (main window)
5. Apply AtlantaFX theme
6. Create simple chat display (non-functional)

**Maven Dependencies:**
```xml
<properties>
    <maven.compiler.source>25</maven.compiler.source>
    <maven.compiler.target>25</maven.compiler.target>
    <javafx.version>25.0.1</javafx.version>
    <atlantafx.version>2.0.1</atlantafx.version>
</properties>

<dependencies>
    <!-- JavaFX 25 -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>${javafx.version}</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>${javafx.version}</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-graphics</artifactId>
        <version>${javafx.version}</version>
    </dependency>

    <!-- JavaFX 25 Incubator modules for RichTextArea -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-incubator-richtext</artifactId>
        <version>${javafx.version}</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-incubator-input</artifactId>
        <version>${javafx.version}</version>
    </dependency>

    <!-- Modern Theme -->
    <dependency>
        <groupId>io.github.mkpaz</groupId>
        <artifactId>atlantafx-base</artifactId>
        <version>${atlantafx.version}</version>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-maven-plugin</artifactId>
            <version>0.0.8</version>
            <configuration>
                <mainClass>com.example.chatbot.App</mainClass>
                <!-- Preview features for title bar controls -->
                <options>
                    <option>--enable-preview</option>
                </options>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### Phase 2: Chat Functionality
1. Create `ChatMessage` model with JavaFX properties
2. Implement `ChatViewModel` with observable collections
3. Build `MessageBubble` custom control
4. Connect existing `DeepSeekChatbot` to ViewModel
5. Implement message sending (non-streaming first)

**Key Binding Pattern:**
```java
public class ChatViewModel {
    private final ObservableList<ChatMessage> messages = FXCollections.observableArrayList();
    private final StringProperty currentInput = new SimpleStringProperty("");
    private final BooleanProperty isLoading = new SimpleBooleanProperty(false);

    public void sendMessage() {
        if (currentInput.get().isBlank()) return;

        isLoading.set(true);
        ChatMessage userMsg = new ChatMessage(currentInput.get(), MessageType.USER);
        messages.add(userMsg);
        currentInput.set("");

        // Async call to DeepSeekChatbot
        CompletableFuture.supplyAsync(() -> chatbot.chat(userMsg.getText()))
            .thenAcceptAsync(response -> {
                messages.add(new ChatMessage(response, MessageType.AI));
                isLoading.set(false);
            }, Platform::runLater);
    }
}
```

### Phase 3: Streaming Support
1. Create `StreamingText` component for animated token display
2. Implement streaming callback integration
3. Add typing indicator animation
4. Handle streaming interruption gracefully

**Streaming Integration:**
```java
streamingChatModel.chat(messages, new StreamingChatResponseHandler() {
    @Override
    public void onPartialResponse(String token) {
        Platform.runLater(() -> currentMessage.appendText(token));
    }

    @Override
    public void onCompleteResponse(ChatResponse response) {
        Platform.runLater(() -> {
            currentMessage.setComplete(true);
            displayUsage(response);
        });
    }
});
```

### Phase 4: Settings & Controls
1. Build settings panel (model selection, temperature slider)
2. Implement RAG toggle with status indicator
3. Add token usage display in status bar
4. Create keyboard shortcuts (Enter to send, Ctrl+Enter for newline)
5. **Title bar controls** (JavaFX 25 Preview) - model selector in title bar

**Title Bar Controls (JavaFX 25 Preview Feature):**
```java
// Enable preview features for title bar controls
public class App extends Application {
    @Override
    public void start(Stage stage) {
        // Create title bar controls
        ComboBox<String> modelSelector = new ComboBox<>();
        modelSelector.getItems().addAll("deepseek-chat", "deepseek-reasoner");

        Button settingsBtn = new Button("⚙");

        // Add to stage's title bar (Preview API)
        stage.getTitleBar().getLeftItems().add(modelSelector);
        stage.getTitleBar().getRightItems().add(settingsBtn);

        // ... rest of setup
    }
}
```

**Settings Bindings:**
```java
// Temperature slider
temperatureSlider.valueProperty().bindBidirectional(viewModel.temperatureProperty());

// Model dropdown
modelComboBox.valueProperty().bindBidirectional(viewModel.modelProperty());

// RAG toggle
ragToggle.selectedProperty().bindBidirectional(viewModel.ragEnabledProperty());
```

### Phase 5: Polish & Advanced Features
1. Chat history persistence (sidebar)
2. Dark/Light theme toggle with CSS media queries
3. Markdown rendering in AI responses
4. Code syntax highlighting with RichTextArea
5. Copy message button
6. Export conversation feature
7. System tray integration (optional)

---

## 5. Custom Components

### MessageBubble Control
```java
public class MessageBubble extends VBox {
    private final Label content;
    private final Label timestamp;
    private final MessageType type;

    public MessageBubble(ChatMessage message) {
        this.type = message.getType();
        this.content = new Label(message.getText());
        this.timestamp = new Label(formatTime(message.getTimestamp()));

        getStyleClass().add("message-bubble");
        getStyleClass().add(type == MessageType.USER ? "user-bubble" : "ai-bubble");

        content.setWrapText(true);
        content.setMaxWidth(400);

        getChildren().addAll(content, timestamp);
    }
}
```

**CSS for Message Bubbles:**
```css
.message-bubble {
    -fx-padding: 12px 16px;
    -fx-background-radius: 16px;
    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 4, 0, 0, 2);
}

.user-bubble {
    -fx-background-color: -color-accent-subtle;
    -fx-alignment: CENTER-RIGHT;
}

.ai-bubble {
    -fx-background-color: -color-bg-subtle;
    -fx-alignment: CENTER-LEFT;
}

.ai-bubble .content {
    -fx-font-family: "JetBrains Mono", monospace;
}
```

### StreamingText Component
```java
public class StreamingText extends TextFlow {
    private final Timeline cursorBlink;
    private final Text cursor = new Text("▌");

    public StreamingText() {
        cursorBlink = new Timeline(
            new KeyFrame(Duration.ZERO, e -> cursor.setOpacity(1)),
            new KeyFrame(Duration.millis(500), e -> cursor.setOpacity(0))
        );
        cursorBlink.setCycleCount(Timeline.INDEFINITE);
        getChildren().add(cursor);
        cursorBlink.play();
    }

    public void appendText(String token) {
        getChildren().remove(cursor);
        getChildren().add(new Text(token));
        getChildren().add(cursor);
    }

    public void setComplete(boolean complete) {
        if (complete) {
            cursorBlink.stop();
            getChildren().remove(cursor);
        }
    }
}
```

### RichTextArea for Chat Input (JavaFX 25)
```java
import jfx.incubator.scene.control.richtext.RichTextArea;

public class ChatInputArea extends RichTextArea {
    public ChatInputArea() {
        // Multi-line prompt text (JavaFX 25 feature)
        setPromptText("Type your message here...\n(Shift+Enter for new line, Enter to send)");

        // CSS styling for highlights
        getStyleClass().add("chat-input");
    }
}
```

---

## 6. Build & Run Commands

```bash
# Compile with JavaFX 25
mvn clean compile

# Run the GUI application (with preview features)
mvn javafx:run

# Run with explicit preview flag
mvn javafx:run -Djavafx.run.options="--enable-preview"

# Package as executable JAR (with dependencies)
mvn package shade:shade

# Run packaged JAR
java --module-path /path/to/javafx-sdk/lib \
     --add-modules javafx.controls,javafx.fxml,jfx.incubator.richtext \
     --enable-preview \
     -jar target/deepseek-chatbot-1.0-SNAPSHOT.jar
```

---

## 7. Testing Strategy

### Unit Tests (ViewModel)
```java
@Test
void sendMessage_addsUserAndAiMessages() {
    ChatViewModel vm = new ChatViewModel(mockChatbot);
    vm.currentInputProperty().set("Hello");

    vm.sendMessage();

    assertEquals(2, vm.getMessages().size());
    assertEquals(MessageType.USER, vm.getMessages().get(0).getType());
    assertEquals(MessageType.AI, vm.getMessages().get(1).getType());
}
```

### Integration Tests (TestFX)
```java
@ExtendWith(ApplicationExtension.class)
class ChatViewTest {
    @Start
    void start(Stage stage) {
        // Initialize test scene
    }

    @Test
    void clickSend_displaysMessage(FxRobot robot) {
        robot.clickOn("#inputField").write("Test message");
        robot.clickOn("#sendButton");

        verifyThat("#chatHistory", hasChild(".user-bubble"));
    }
}
```

---

## 8. Accessibility (Enhanced in JavaFX 25)

JavaFX 25 adds comprehensive macOS accessibility protocols:

| Protocol | Control | JDK Issue |
|----------|---------|-----------|
| NSAccessibilitySlider | Temperature slider | JDK-8313556 |
| NSAccessibilityStepper | Spinner controls | JDK-8313558 |
| NSAccessibilityProgressIndicator | Loading indicators | JDK-8350316 |
| NSAccessibilityGroup | Layout containers | JDK-8351773 |
| NSAccessibilityImage | Icons | JDK-8356983 |
| TabGroup | Settings tabs | JDK-8359257 |

**Best Practices:**
- All interactive elements have focus indicators
- Keyboard navigation fully supported
- Screen reader compatible labels
- High contrast theme option
- Respect `prefers-reduced-motion` via CSS media queries

---

## 9. Notable Bug Fixes in JavaFX 25

These fixes improve the chatbot GUI experience:

| Issue | Impact on Chatbot |
|-------|-------------------|
| [JDK-8357157](https://bugs.openjdk.org/browse/JDK-8357157) | AnimationTimer no longer freezes app (streaming cursor) |
| [JDK-8089080](https://bugs.openjdk.org/browse/JDK-8089080) | TextArea caret doesn't disappear on backspace |
| [JDK-8281384](https://bugs.openjdk.org/browse/JDK-8281384) | No random chars when pasting from clipboard |
| [JDK-8351368](https://bugs.openjdk.org/browse/JDK-8351368) | RichTextArea paste from Word works |
| [JDK-8351878](https://bugs.openjdk.org/browse/JDK-8351878) | RichTextArea copy/paste fixed |
| [JDK-8088343](https://bugs.openjdk.org/browse/JDK-8088343) | Task.cancel() race condition fixed |
| [JDK-8146479](https://bugs.openjdk.org/browse/JDK-8146479) | Scene not black after restore from minimized |

---

## 10. File Structure After Implementation

```
src/
├── main/
│   ├── java/com/example/chatbot/
│   │   ├── App.java                      # NEW: JavaFX entry point
│   │   ├── DeepSeekChatbot.java          # EXISTING: Core chat logic
│   │   ├── model/
│   │   │   ├── ChatMessage.java          # NEW
│   │   │   ├── ChatSession.java          # NEW
│   │   │   └── Settings.java             # NEW
│   │   ├── viewmodel/
│   │   │   ├── ChatViewModel.java        # NEW
│   │   │   └── SettingsViewModel.java    # NEW
│   │   ├── view/
│   │   │   ├── MainView.java             # NEW
│   │   │   ├── ChatView.java             # NEW
│   │   │   └── components/
│   │   │       ├── MessageBubble.java    # NEW
│   │   │       ├── StreamingText.java    # NEW
│   │   │       └── StatusBar.java        # NEW
│   │   └── rag/                          # EXISTING: RAG components
│   └── resources/
│       ├── fxml/
│       │   ├── main.fxml                 # NEW
│       │   ├── chat.fxml                 # NEW
│       │   └── settings.fxml             # NEW
│       ├── css/
│       │   ├── app.css                   # NEW (with media queries)
│       │   └── chat-bubbles.css          # NEW
│       └── images/icons/                 # NEW: SVG icons
└── test/
    └── java/com/example/chatbot/
        ├── viewmodel/
        │   └── ChatViewModelTest.java    # NEW
        └── view/
            └── ChatViewTest.java         # NEW (TestFX)
```

---

## 11. Project Setup with JDK 25

### Upgrading Current Project to JDK 25

This project (`langchain4j-bookmap-rag`) will use JDK 25 for both the chatbot backend and JavaFX GUI.

### Step 1: Install JDK 25
```powershell
# Option 1: Using winget
winget install EclipseAdoptium.Temurin.25.JDK

# Option 2: Download from Adoptium
# https://adoptium.net/temurin/releases/?version=25

# JDK 25 typically installs to:
# C:\Program Files\Eclipse Adoptium\jdk-25.0.x-hotspot\
```

### Step 2: Configure Maven Toolchains (Recommended)

This allows the project to use JDK 25 while keeping JDK 21 as system default for Bookmap work.

Create/edit `~/.m2/toolchains.xml`:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<toolchains>
    <!-- JDK 21 for Bookmap addons (other projects) -->
    <toolchain>
        <type>jdk</type>
        <provides>
            <version>21</version>
            <vendor>adoptium</vendor>
        </provides>
        <configuration>
            <jdkHome>C:\Program Files\Eclipse Adoptium\jdk-21.0.x-hotspot</jdkHome>
        </configuration>
    </toolchain>

    <!-- JDK 25 for this chatbot project -->
    <toolchain>
        <type>jdk</type>
        <provides>
            <version>25</version>
            <vendor>adoptium</vendor>
        </provides>
        <configuration>
            <jdkHome>C:\Program Files\Eclipse Adoptium\jdk-25.0.x-hotspot</jdkHome>
        </configuration>
    </toolchain>
</toolchains>
```

### Step 3: Update pom.xml

Add toolchain plugin to use JDK 25:
```xml
<properties>
    <maven.compiler.source>25</maven.compiler.source>
    <maven.compiler.target>25</maven.compiler.target>
    <javafx.version>25.0.1</javafx.version>
</properties>

<build>
    <plugins>
        <!-- Use JDK 25 via toolchain -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-toolchains-plugin</artifactId>
            <version>3.1.0</version>
            <executions>
                <execution>
                    <goals>
                        <goal>toolchain</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <toolchains>
                    <jdk>
                        <version>25</version>
                    </jdk>
                </toolchains>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### Running the Project

```powershell
# Build and run (uses JDK 25 via toolchain, system default stays JDK 21)
cd C:\Users\nasser\Dev\langchain4j-bookmap-rag
mvn clean javafx:run
```

### Alternative: Per-Project JAVA_HOME Script

Create `run.bat` in the project root:
```batch
@echo off
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-25.0.x-hotspot
set PATH=%JAVA_HOME%\bin;%PATH%
mvn javafx:run
```

### JavaFX 25 Removed/Changed APIs
- `TransitionEvent(EventType, StyleableProperty, Duration)` constructor removed
  - Use `TransitionEvent(EventType, StyleableProperty, String, Duration)` instead

### No Longer Needed in JavaFX 25
- `--sun-misc-unsafe-memory-access=allow` flag (fixed in JDK-8334137)

---

## 12. References

### Official Documentation
- [JavaFX 25 Release Notes](https://openjfx.io/highlights/25/) (when available)
- [OpenJFX New APIs (25)](https://openjfx.io/javadoc/25/new-list.html)
- [OpenJFX Deprecated APIs (25)](https://openjfx.io/javadoc/25/deprecated-list.html)
- [Gluon JavaFX Downloads](https://gluonhq.com/products/javafx/)

### Theme & Styling
- [AtlantaFX GitHub](https://github.com/mkpaz/atlantafx)
- [JavaFX CSS Reference](https://openjfx.io/javadoc/25/javafx.graphics/javafx/scene/doc-files/cssref.html)

### Architecture Patterns
- [MVVM Pattern in Java](https://java-design-patterns.com/patterns/model-view-viewmodel/)
- [JavaFX MVC/MVVM Patterns](https://www.pragmaticcoding.ca/javafx/Frameworks/)
- [Cognitive MVVM Framework](https://github.com/carldea/cognitive)

### Testing
- [TestFX](https://github.com/TestFX/TestFX)

### JavaFX 25 Feature Requests
- [JDK-8313424](https://bugs.openjdk.org/browse/JDK-8313424) - Controls in title bar (Preview)
- [JDK-8345348](https://bugs.openjdk.org/browse/JDK-8345348) - CSS media feature queries
- [JDK-8355774](https://bugs.openjdk.org/browse/JDK-8355774) - RichTextArea CSS highlights
- [JDK-8335547](https://bugs.openjdk.org/browse/JDK-8335547) - Multi-line prompt text

---

## Summary

This plan modernizes the existing console-based DeepSeek chatbot into a polished desktop application using:

- **JavaFX 25 LTS** for the latest UI capabilities including:
  - Controls in title bar (Preview)
  - CSS media feature queries for responsive theming
  - Enhanced RichTextArea with CSS styling
  - Multi-line prompt text support
  - Improved accessibility on macOS
- **MVVM architecture** for maintainability and testability
- **AtlantaFX** for professional, modern styling
- **Streaming support** with animated text display
- **RAG integration** with visual status indicators

The implementation is divided into 5 phases, allowing incremental delivery while maintaining a working application throughout development.
