---
source_file: Layer1ApiIgnorableDownwardMessage.html
package: velox.api.layer1.messages
classes: Layer1ApiIgnorableDownwardMessage
---

# Layer1ApiIgnorableDownwardMessage

**Package:** velox.api.layer1.messages

**Type:** Interface

**All Known Subinterfaces:**
- `UserProviderTargetedMessage`

**All Known Implementing Classes:**
- `CurrentTimeUserMessage`
- `UserMessageLayersChainCreatedTargeted`

## Description

Used to mark classes that are not required to be intercepted before reaching lower (provider) end of the chain.

**See Also:**
- `Layer1ApiIgnorableUpwardMessage`