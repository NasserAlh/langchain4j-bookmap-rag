# Changelog

All notable changes to the DeepSeek Chatbot Frontend are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- Dark theme support with automatic system preference detection
- Theme toggle button in settings sidebar
- Dark mode styles for all UI components
- LocalStorage persistence for theme preference (light/dark)
- Custom dark mode scrollbar styling
- `theme.svelte.ts` store for theme state management
- TypeScript configuration (`tsconfig.json`)

### Changed
- Updated `+layout.svelte` to initialize theme on mount
- Enhanced `Sidebar.svelte` with Moon/Sun toggle icon
- Added dark mode Tailwind classes to all components:
  - `ChatWindow.svelte`
  - `ChatMessage.svelte`
  - `ChatInput.svelte`
  - `+page.svelte` (main layout)

## [0.0.1] - 2025-11-30

### Added
- Initial Svelte 5 frontend implementation
- Chat window with message display and auto-scroll
- Chat input with auto-resizing textarea and Send button
- Settings sidebar with:
  - Model selection (DeepSeek Chat/Reasoner)
  - Temperature slider (0.0-1.5)
  - Streaming toggle
  - Token usage display toggle
  - RAG mode management
  - Document ingestion button
  - Clear chat history button
- Connection status indicator (connected/error/connecting/reconnecting)
- Message display with:
  - User/Bot avatar differentiation
  - Message timestamp
  - Token usage and cost estimation
  - Error indicator with message
- Real-time streaming support via Server-Sent Events
- Backend API integration for:
  - Health checking
  - Chat completions (streaming and non-streaming)
  - Settings management
  - RAG toggle and document ingestion
  - Chat history clearing
- Responsive layout with sidebar and main chat area
- Icon library integration (lucide-svelte)
- Tailwind CSS styling with custom components
- TypeScript support throughout codebase

### Project Setup
- Vite build configuration
- SvelteKit framework setup
- Tailwind CSS v4 integration
- TypeScript configuration
- Package scripts:
  - `dev` - Development server
  - `build` - Production build
  - `preview` - Preview production build locally
  - `check` - TypeScript and Svelte type checking

## Known Issues

- Theme initialization might have a brief flash on first load (system preference detection)
- Dark mode toggle updates state immediately but may need page refresh in some browsers

## Future Enhancements

- [ ] User authentication and profiles
- [ ] Multiple conversation threads
- [ ] Message editing and deletion
- [ ] Code syntax highlighting in responses
- [ ] Export conversations to PDF/Markdown
- [ ] Custom theme colors (beyond light/dark)
- [ ] Keyboard shortcuts for common actions
- [ ] Mobile optimization improvements
- [ ] Accessibility improvements (ARIA labels, keyboard navigation)
- [ ] Localization/i18n support
