# DeepSeek Chatbot Frontend

A modern, responsive web interface for the DeepSeek chatbot built with Svelte 5 and Tailwind CSS.

## Features

- **Real-time Streaming**: Stream responses from the DeepSeek API with Server-Sent Events (SSE)
- **Dark Mode**: Full dark theme support with automatic system preference detection
- **RAG Support**: Integrate Retrieval-Augmented Generation for knowledge-based responses
- **Configurable Settings**: Adjust model selection, temperature, streaming mode, and token usage display
- **Responsive Design**: Works seamlessly on desktop and tablet devices
- **Token Usage Tracking**: Monitor input/output tokens and estimated costs
- **Connection Status**: Real-time backend health monitoring

## Tech Stack

- **Framework**: [Svelte 5](https://svelte.dev/) - Reactive JavaScript
- **Build Tool**: [Vite](https://vitejs.dev/) - Next-generation frontend build tool
- **Styling**: [Tailwind CSS](https://tailwindcss.com/) - Utility-first CSS framework
- **Icons**: [lucide-svelte](https://lucide.dev/guide/packages/lucide-svelte) - Beautiful icon library
- **SSE**: [sveltekit-sse](https://github.com/khulnasoft-lab/sveltekit-sse) - Server-Sent Events for streaming
- **Language**: TypeScript

## Getting Started

### Prerequisites

- Node.js 18+ 
- npm or yarn

### Installation

```bash
# Install dependencies
npm install

# Start development server
npm run dev

# Build for production
npm run build

# Preview production build
npm run preview
```

The development server runs at `http://localhost:5173`

## Available Scripts

- `npm run dev` - Start development server with hot module replacement
- `npm run build` - Build optimized production bundle
- `npm run preview` - Preview production build locally
- `npm run check` - Run TypeScript and Svelte type checking

## Project Structure

```
src/
├── lib/
│   ├── components/        # Reusable Svelte components
│   │   ├── ChatWindow.svelte    # Message display area
│   │   ├── ChatMessage.svelte   # Individual message component
│   │   ├── ChatInput.svelte     # Message input with auto-resize
│   │   ├── Sidebar.svelte       # Settings and controls panel
│   │   └── index.ts             # Component exports
│   ├── stores/            # Svelte stores (state management)
│   │   ├── chat.svelte.ts      # Chat state and messages
│   │   └── theme.svelte.ts     # Dark/light theme state
│   ├── api.ts             # API client for backend communication
│   ├── types.ts           # TypeScript type definitions
│   └── assets/            # Static assets (favicon, etc)
├── routes/
│   ├── +layout.svelte     # Root layout with theme initialization
│   └── +page.svelte       # Main chat page
└── app.css               # Global styles with dark mode support

static/                   # Static files (public assets)
```

## Features in Detail

### Dark Mode

The theme system automatically:
- Detects system color scheme preference (`prefers-color-scheme`)
- Persists user preference to localStorage
- Applies theme to all components with smooth transitions
- Provides toggle button in the settings sidebar

### Chat Features

- **Streaming**: Responses stream token-by-token for real-time feedback
- **Non-streaming**: Wait for complete response if preferred
- **Token Usage**: Track token consumption and estimated API costs
- **Chat History**: View full conversation history with timestamps
- **Clear History**: Reset conversation and start fresh

### Settings Panel

- **Model Selection**: Choose between DeepSeek Chat and Reasoner models
- **Temperature Control**: Adjust response creativity (0.0 = precise, 1.5 = creative)
- **Streaming Toggle**: Enable/disable real-time response streaming
- **Token Usage Display**: Show detailed token and cost information
- **RAG Mode**: Enable Retrieval-Augmented Generation for context-aware responses
- **Document Ingestion**: Index documents for RAG processing

## Backend Integration

The frontend communicates with a Java backend API running on `http://localhost:8080`.

Key endpoints:
- `GET /health` - Backend health check
- `POST /chat` - Send message and get response
- `POST /chat/stream` - Stream response with SSE
- `GET /settings` - Get current settings
- `POST /settings` - Update settings
- `POST /rag/toggle` - Enable/disable RAG mode
- `POST /rag/ingest` - Index documents for RAG
- `POST /chat/clear` - Clear chat history

## Environment Variables

The frontend doesn't require environment variables. Configuration is done through:
1. Backend API settings endpoint
2. Browser localStorage for theme preference

## Development

### Code Style

- TypeScript for type safety
- Svelte 5 reactive variables with `$state` and `$derived`
- Tailwind CSS for styling (no CSS-in-JS)
- Lucide SVG icons

### Adding New Features

1. Create components in `src/lib/components/`
2. Add types to `src/lib/types.ts` if needed
3. Use stores from `src/lib/stores/` for state management
4. Update API calls in `src/lib/api.ts` for backend integration
5. Add dark mode styles using `dark:` Tailwind prefix

### Type Checking

Run type checking with:
```bash
npm run check
```

## Building for Production

```bash
npm run build
```

This creates an optimized build in `.svelte-kit/output/` with:
- Code splitting and lazy loading
- CSS minification
- JavaScript bundling and minification
- Asset optimization

## Deployment

The built app is a standard SvelteKit application. Choose an adapter based on your hosting:

- **Vercel/Netlify**: Use `@sveltejs/adapter-auto` (default)
- **Docker**: Add `@sveltejs/adapter-node`
- **Static hosting**: Add `@sveltejs/adapter-static`

See [SvelteKit Adapters Documentation](https://svelte.dev/docs/kit/adapters)

## Troubleshooting

### Port Already in Use

If port 5173 is busy, Vite will use the next available port. Check console output for the actual URL.

### Dark Mode Not Working

Clear localStorage and reload:
```javascript
localStorage.clear()
location.reload()
```

### Build Errors

Ensure all dependencies are installed:
```bash
npm install
npm run check
```

## License

This project is part of the LangChain4j learning platform.
