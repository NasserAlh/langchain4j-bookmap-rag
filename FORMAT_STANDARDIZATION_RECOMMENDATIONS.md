# Format Standardization Recommendations for RAG Ingestion

This document provides analysis and recommendations for standardizing `Comprehensive_Guide_Bookmap_Addons.md` to align with the javadoc markdown format used in the `javadoc/` folder.

---

## Current Format Comparison

### Javadoc Format (Target Format for RAG)

The javadoc files use a **structured, atomic format** optimized for retrieval:

```markdown
---
source_file: ClassName.html
package: velox.api.layer1.simplified
classes: ClassName
methods: method1, method2, method3
---

# ClassName

**Package:** velox.api.layer1.simplified

**Type:** Interface/Class

**All Known Subinterfaces/Subclasses:** ...

## Description

Brief description.

## Methods

### methodName

```java
void methodName(Type param)
```

Description of method.

**Parameters:**
- `param` - Description
```

### Comprehensive Guide Format (Current Issues)

The `Comprehensive_Guide_Bookmap_Addons.md` is a **monolithic narrative document** (~1600 lines) with:

| Issue | Impact on RAG |
|-------|---------------|
| No YAML frontmatter | Cannot index metadata for filtering |
| Mixed content types (interfaces, classes, examples interleaved) | Poor retrieval precision |
| Inconsistent heading hierarchy | Chunking produces inconsistent results |
| Embedded examples within interface/class docs | Retrieved chunks contain irrelevant code |
| No explicit package declarations | Missing context for code understanding |
| Informal section separators (`---`) | Ambiguous document structure |

---

## Recommendations

### 1. Split into Atomic Files

Break the comprehensive guide into individual files, one per topic:

| Current Section | Suggested File |
|-----------------|----------------|
| Minimum Requirements | `guides/getting-started/MinimumRequirements.md` |
| TimeListener Add-Ons | `guides/data-listeners/TimeListener.md` |
| MBP Data/Order Book | `guides/order-book/MBPData.md` |
| MBO Data | `guides/order-book/MBOData.md` |
| VWAP Calculation | `examples/VWAPCalculator.md` |
| Historical Data | `guides/historical-data/HistoricalDataHandling.md` |
| Api Interface | `guides/trading/ApiInterface.md` |
| Order Placement | `guides/trading/OrderPlacement.md` |
| Example: NasserDom | `examples/NasserDom.md` |
| Example: OrderBlockStrategy | `examples/OrderBlockStrategy.md` |

---

### 2. Add YAML Frontmatter to Each File

Every file should have metadata for indexing:

```yaml
---
source_file: guides/getting-started/MinimumRequirements.md
type: guide
topic: addon-basics
related_classes: CustomModule, CustomModuleAdapter
annotations: Layer1SimpleAttachable, Layer1ApiVersion, Layer1StrategyName, Layer1TradingStrategy
---
```

**Frontmatter Schema:**

| Field | Description | Example Values |
|-------|-------------|----------------|
| `source_file` | Relative path to file | `guides/MinimumRequirements.md` |
| `type` | Document type | `guide`, `example`, `reference` |
| `package` | Java package (if applicable) | `velox.api.layer1.simplified` |
| `topic` | Topic tags for filtering | `addon-basics`, `order-book`, `trading` |
| `related_classes` | Related API classes | `CustomModule, DepthDataListener` |
| `implements` | Interfaces implemented (for examples) | `CustomModule, TradeDataListener` |
| `annotations` | Required annotations | `Layer1SimpleAttachable` |
| `methods` | Methods documented | `onDepth, onTrade` |

---

### 3. Standardize Heading Structure

Use consistent heading hierarchy across all files:

```markdown
# Title (Document Name)

**Type:** Guide | Example | Reference

**Related Interfaces:** CustomModule, DepthDataListener

**Prerequisites:** List any required reading

## Overview

Brief description (1-2 paragraphs max).

## Key Concepts

### Concept 1

...

## Code Examples

### Example: Name

```java
// code
```

**Explanation:**
- Point 1
- Point 2

## See Also

- [Related Document](path)
```

---

### 4. Separate Examples from Reference Documentation

**Current (problematic for RAG):**

```markdown
### Interface: DepthDataListener
[interface docs + 200 lines of example code mixed together]
```

**Recommended - Reference File** (`reference/DepthDataListener.md`):

```markdown
---
source_file: reference/DepthDataListener.md
package: velox.api.layer1.simplified
type: interface
methods: onDepth
---

# DepthDataListener

**Package:** velox.api.layer1.simplified

**Type:** Interface

## Description

Get incremental depth updates.

## Methods

### onDepth

```java
void onDepth(boolean isBid, int price, int size)
```

**Parameters:**
- `isBid` - True if update describes changes to bid side
- `price` - Price level (as level number)
- `size` - New size (0 if removed)

## Related Examples

- [NasserDom Example](../examples/NasserDom.md)
```

**Recommended - Example File** (`examples/NasserDom.md`):

```markdown
---
source_file: examples/NasserDom.md
type: example
implements: CustomModule, DepthDataListener, TradeDataListener
topic: depth-of-market, volume-profile
---

# NasserDom Example

**Type:** Complete Add-on Example

**Implements:** CustomModule, DepthDataListener, TradeDataListener

## Purpose

Displays a Depth of Market GUI with volume profile.

## Full Code

```java
// complete code here
```

## Key Implementation Details

### Order Book Management
...

### GUI Updates
...
```

---

### 5. Use Consistent Code Block Annotations

Add language hints and context comments:

```java
// Required annotations for Bookmap add-on
@Layer1SimpleAttachable           // Makes add-on attachable to instruments
@Layer1StrategyName("Market Data Listener")  // Display name in Bookmap UI
@Layer1ApiVersion(Layer1ApiVersionValue.VERSION1)  // API version compatibility
@Layer1TradingStrategy            // Required for add-ons that place orders
public class MarketDataListener implements CustomModuleAdapter {
    // Implementation
}
```

---

### 6. Add Cross-Reference Links

At the bottom of each file, include related documentation:

```markdown
## Related Documentation

- **Javadoc:** [DepthDataListener](../javadoc/bookmap-simplified-api/DepthDataListener.md)
- **Example:** [Complete DOM Implementation](../examples/NasserDom.md)
- **Guide:** [Order Book Management](./OrderBookMBP.md)
```

---

### 7. Suggested Directory Structure

```
knowledge-materials/
├── javadoc/
│   ├── bookmap-core-api/              # Existing - keep as-is (351 files)
│   └── bookmap-simplified-api/         # Existing - keep as-is (61 files)
│
├── guides/
│   ├── getting-started/
│   │   ├── MinimumRequirements.md      # Add-on basics, required annotations
│   │   └── AddonLifecycle.md           # initialize, stop, lifecycle management
│   │
│   ├── data-listeners/
│   │   ├── TimeListener.md             # Timestamp handling
│   │   ├── DepthDataListener.md        # MBP depth updates
│   │   ├── TradeDataListener.md        # Trade event handling
│   │   ├── BarDataListener.md          # OHLC bar data
│   │   └── HistoricalModeListener.md   # Historical vs real-time mode
│   │
│   ├── order-book/
│   │   ├── MBPData.md                  # Market By Price order book
│   │   ├── MBOData.md                  # Market By Order (CME) data
│   │   └── OrderBookManagement.md      # Best practices for book maintenance
│   │
│   ├── trading/
│   │   ├── ApiInterface.md             # Api interface methods
│   │   ├── OrderPlacement.md           # SimpleOrderSendParametersBuilder
│   │   └── PositionManagement.md       # Position and balance listeners
│   │
│   ├── indicators/
│   │   ├── RegisteringIndicators.md    # Indicator registration
│   │   └── CustomSettings.md           # CustomSettingsPanelProvider
│   │
│   └── historical-data/
│       ├── HistoricalDataHandling.md   # Pre-subscription, backfill, replay
│       └── DataTypes.md                # Historical data types explained
│
├── examples/
│   ├── HelloBookmap.md                 # Minimal add-on example
│   ├── NasserDom.md                    # Full DOM with volume profile
│   ├── OrderBlockStrategy.md           # Trading strategy with indicators
│   ├── VWAPCalculator.md               # VWAP calculation example
│   ├── OrderPlacer.md                  # Order placement helper class
│   └── MarketDataListener.md           # Various listener implementations
│
└── reference/
    ├── Annotations.md                  # All required/optional annotations
    ├── Interfaces.md                   # Quick reference for all interfaces
    └── CommonPatterns.md               # Design patterns in Bookmap add-ons
```

---

### 8. Chunk Size Optimization for RAG

| Metric | Current | Recommended |
|--------|---------|-------------|
| File count | 1 large file | 15-20 focused files |
| Lines per file | ~1600 | 100-300 |
| Tokens per file | ~8000+ | 500-1500 |
| Topics per file | Many mixed | Single focused topic |

**Benefits of smaller, focused files:**

- **Retrieval precision** - Queries return relevant chunks only
- **Context efficiency** - LLM sees focused, relevant content
- **Deduplication** - Avoids redundant information in prompts
- **Maintainability** - Easier to update individual topics

---

## Implementation Priority

### Phase 1: Quick Wins (High Impact, Low Effort)

1. Add YAML frontmatter to the existing comprehensive guide
2. Create a table of contents with anchor links
3. Standardize heading levels

### Phase 2: Restructuring (High Impact, Medium Effort)

1. Extract all code examples into separate files
2. Split guide into topic-based files
3. Add cross-references between files

### Phase 3: Enhancement (Medium Impact, Higher Effort)

1. Create reference files for interfaces not covered in javadoc
2. Add more practical examples
3. Create an index/search page

---

## Template Files

### Guide Template

```markdown
---
source_file: guides/category/TopicName.md
type: guide
topic: topic-tag
related_classes: Class1, Class2
prerequisites: [OtherGuide.md]
---

# Topic Name

**Type:** Guide

**Related Classes:** Class1, Class2

## Overview

One to two paragraphs explaining what this guide covers.

## Prerequisites

- [Prerequisite Guide](path/to/guide.md)

## Key Concepts

### Concept 1

Explanation...

### Concept 2

Explanation...

## Implementation

### Step 1: Description

```java
// Code example
```

### Step 2: Description

```java
// Code example
```

## Common Pitfalls

- Pitfall 1 and how to avoid it
- Pitfall 2 and how to avoid it

## Related Documentation

- [Related Guide](path)
- [Javadoc Reference](path)
- [Example Implementation](path)
```

### Example Template

```markdown
---
source_file: examples/ExampleName.md
type: example
implements: Interface1, Interface2
topic: topic-tag
annotations: Annotation1, Annotation2
---

# Example Name

**Type:** Complete Example

**Implements:** Interface1, Interface2

**Required Annotations:** Annotation1, Annotation2

## Purpose

Brief description of what this example demonstrates.

## Prerequisites

- Understanding of [Topic](path/to/guide.md)

## Complete Code

```java
package example;

// Full implementation here
```

## Code Walkthrough

### Initialization

Explanation of the initialize method...

### Data Handling

Explanation of data listener methods...

### Cleanup

Explanation of the stop method...

## Key Takeaways

- Point 1
- Point 2
- Point 3

## Related Documentation

- [Guide: Topic](path)
- [Javadoc: Interface](path)
```

---

## Next Steps

1. Review and approve the recommended structure
2. Create the directory structure
3. Begin splitting the comprehensive guide
4. Add YAML frontmatter to all files
5. Verify RAG retrieval quality with test queries
