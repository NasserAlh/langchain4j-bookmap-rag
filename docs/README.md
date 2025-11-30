# Documentation Index

Complete guides for the LangChain4j Bookmap Chatbot application.

## 🚀 Getting Started

**Start here if you're new to the project:**

1. [QUICKSTART.md](../QUICKSTART.md) - 5-minute setup guide
   - Prerequisites
   - API key setup
   - Starting backend & frontend
   - Basic troubleshooting

## 📋 Core Documentation

### Error Handling & Debugging
- **[ERROR_OBSERVABILITY.md](ERROR_OBSERVABILITY.md)** - Complete error logging system
  - Error response structure
  - Error codes and their meanings
  - Log file locations and formats
  - Debugging workflow with examples
  - Extending the error system

- **[DEBUGGING_CHECKLIST.md](DEBUGGING_CHECKLIST.md)** - Step-by-step debugging guide
  - What to do when you see an error
  - Common issues and fixes
  - Log analysis techniques
  - Production debugging tips
  - Troubleshooting template

### Configuration & Deployment
- **[API_KEY_MANAGEMENT.md](API_KEY_MANAGEMENT.md)** - API keys setup and security
  - How to set API keys (5 methods)
  - Getting API keys from providers
  - Security best practices
  - Multiple LLM provider setup
  - Production deployment (AWS, Docker, Kubernetes)
  - Troubleshooting API key issues

## 📚 Reference Documents

### System Design & Implementation
- **[IMPLEMENTATION_SUMMARY.md](IMPLEMENTATION_SUMMARY.md)** - P0 error system overview
  - What was implemented
  - Key features explained
  - File changes reference
  - How to use the system
  - Error flow example
  - Next steps (P1, P2, P3 phases)

### Original Documentation
- **[deepseek-api.md](deepseek-api.md)** - DeepSeek API reference
- **[GUI_PLAN.md](GUI_PLAN.md)** - Frontend design plan
- **[USER_MANUAL.md](USER_MANUAL.md)** - User guide

## 🎯 Quick Navigation

**For different scenarios:**

| Scenario | Read This |
|----------|-----------|
| First time setup | [QUICKSTART.md](../QUICKSTART.md) |
| API key issues | [API_KEY_MANAGEMENT.md](API_KEY_MANAGEMENT.md) |
| Debugging an error | [DEBUGGING_CHECKLIST.md](DEBUGGING_CHECKLIST.md) |
| Understanding errors | [ERROR_OBSERVABILITY.md](ERROR_OBSERVABILITY.md) |
| Learning what was built | [IMPLEMENTATION_SUMMARY.md](IMPLEMENTATION_SUMMARY.md) |
| Deploying to production | [API_KEY_MANAGEMENT.md](API_KEY_MANAGEMENT.md#production-deployment) |

## 📁 File Structure

```
langchain4j-bookmap-rag/
├── QUICKSTART.md                    # 5-minute setup
├── .env.example                     # Template for API keys
├── docs/
│   ├── README.md                    # This file
│   ├── ERROR_OBSERVABILITY.md       # Error handling system
│   ├── API_KEY_MANAGEMENT.md        # API key setup
│   ├── DEBUGGING_CHECKLIST.md       # Debugging guide
│   ├── IMPLEMENTATION_SUMMARY.md    # What was implemented
│   ├── deepseek-api.md              # DeepSeek reference
│   ├── GUI_PLAN.md                  # Frontend design
│   └── USER_MANUAL.md               # User guide
├── logs/                            # Application logs (auto-generated)
│   ├── chatbot.log                  # All logs
│   └── chatbot-errors.log           # Errors only
└── src/main/resources/
    └── logback.xml                  # Logging configuration
```

## 🔧 Key Technologies

- **Backend:** Java 21, Maven, LangChain4j, Javalin, Logback
- **Frontend:** Svelte 5, TypeScript, Vite, Tailwind CSS
- **Logging:** Logback (SLF4J)
- **APIs:** DeepSeek, OpenAI

## 🎓 Learning Paths

### For Developers
1. Read [QUICKSTART.md](../QUICKSTART.md) - Get it running
2. Read [IMPLEMENTATION_SUMMARY.md](IMPLEMENTATION_SUMMARY.md) - Understand what was built
3. Read [ERROR_OBSERVABILITY.md](ERROR_OBSERVABILITY.md#backend-code-reference) - Learn error handling patterns
4. Read [DEBUGGING_CHECKLIST.md](DEBUGGING_CHECKLIST.md#-developer-workflow) - Learn development workflow

### For Ops/DevOps
1. Read [QUICKSTART.md](../QUICKSTART.md) - Basic setup
2. Read [API_KEY_MANAGEMENT.md](API_KEY_MANAGEMENT.md#production-deployment) - Production deployment
3. Read [ERROR_OBSERVABILITY.md](ERROR_OBSERVABILITY.md#log-files) - Understanding logs
4. Read [DEBUGGING_CHECKLIST.md](DEBUGGING_CHECKLIST.md#-production-debugging) - Production debugging

### For Support/QA
1. Read [QUICKSTART.md](../QUICKSTART.md) - How to use
2. Read [DEBUGGING_CHECKLIST.md](DEBUGGING_CHECKLIST.md#-when-you-see-an-error) - When errors occur
3. Read [USER_MANUAL.md](USER_MANUAL.md) - How to use features

## 📞 Common Questions

**Q: Where do I put my API keys?**
A: See [API_KEY_MANAGEMENT.md](API_KEY_MANAGEMENT.md) - Copy `.env.example` to `.env` and add keys

**Q: How do I debug an error?**
A: See [DEBUGGING_CHECKLIST.md](DEBUGGING_CHECKLIST.md) - Search logs by error ID

**Q: What does this error code mean?**
A: See [ERROR_OBSERVABILITY.md](ERROR_OBSERVABILITY.md#error-codes) - Error codes table

**Q: How do I deploy to production?**
A: See [API_KEY_MANAGEMENT.md](API_KEY_MANAGEMENT.md#production-deployment) - Production setup guides

**Q: Where are the logs?**
A: See [ERROR_OBSERVABILITY.md](ERROR_OBSERVABILITY.md#log-files) - Log file locations

## 🔗 External Resources

- [DeepSeek API Documentation](https://platform.deepseek.com/)
- [OpenAI API Documentation](https://platform.openai.com/docs/)
- [Logback Documentation](http://logback.qos.ch/)
- [LangChain4j Documentation](https://docs.langchain4j.dev/)
- [Svelte Documentation](https://svelte.dev/docs)

## 📝 Version Information

**P0 Implementation Complete**
- Error correlation IDs ✅
- Categorized error codes ✅
- Persistent file logging ✅
- Rich error UI ✅
- API key management ✅

**Future Phases**
- P1: Global notification system
- P2: Request context middleware
- P3: Debug mode UI

## 📧 Support

Found an issue? Here's how to get help:

1. Check [DEBUGGING_CHECKLIST.md](DEBUGGING_CHECKLIST.md) first
2. Search logs with error ID: `grep "errorId" logs/chatbot-errors.log`
3. Check [API_KEY_MANAGEMENT.md](API_KEY_MANAGEMENT.md#troubleshooting)
4. Create a GitHub issue with the error ID and relevant log excerpt

---

**Last Updated:** November 2024
**Documentation Version:** 1.0
