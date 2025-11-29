# DeepSeek API Documentation

## Table of Contents
- [Models & Pricing](#models--pricing)
- [Your First API Call](#your-first-api-call)
- [Temperature Parameter](#temperature-parameter)

---

## Models & Pricing

### Overview
Prices are listed in units of **per 1M tokens**. A token is the smallest unit of text that the model recognizes and can be a word, number, or punctuation mark. Billing is based on the total number of input and output tokens processed by the model.

### Model Details

| Feature | deepseek-chat | deepseek-reasoner |
|---------|---------------|-------------------|
| **Model Version** | DeepSeek-V3.2-Exp (Non-thinking Mode) | DeepSeek-V3.2-Exp (Thinking Mode) |
| **Context Length** | 128K | 128K |
| **Max Output** | Default: 4K<br>Maximum: 8K | Default: 32K<br>Maximum: 64K |
| **Json Output** | ✓ | ✓ |
| **Function Calling** | ✓ | ✗ (¹) |
| **Chat Prefix Completion (Beta)** | ✓ | ✓ |
| **FIM Completion (Beta)** | ✓ | ✗ |

**(¹)** If a request to the `deepseek-reasoner` model includes the `tools` parameter, the request will actually be processed using the `deepseek-chat` model.

### Pricing

| Token Type | Price per 1M Tokens |
|------------|---------------------|
| **Input Tokens (Cache Hit)** | $0.028 |
| **Input Tokens (Cache Miss)** | $0.28 |
| **Output Tokens** | $0.42 |

### Deduction Rules

**Calculation:** `expense = number of tokens × price`

Fees are directly deducted from your topped-up balance or granted balance, with preference for using the granted balance first when both are available.

**Note:** Product prices may vary and DeepSeek reserves the right to adjust them. It's recommended to top up based on actual usage and regularly check for the most recent pricing information.

---

## Your First API Call

### API Compatibility

The DeepSeek API uses an API format compatible with OpenAI. You can use the OpenAI SDK or software compatible with the OpenAI API to access DeepSeek by modifying the configuration.

### Configuration Parameters

| Parameter | Value |
|-----------|-------|
| **base_url** | `https://api.deepseek.com` |
| **api_key** | Apply for an API key |

**Notes:**
- For OpenAI compatibility, you can also use `https://api.deepseek.com/v1` as the `base_url`. The `v1` here has NO relationship with the model's version.
- `deepseek-chat` and `deepseek-reasoner` are upgraded to DeepSeek-V3.2-Exp now:
  - `deepseek-chat` is the **non-thinking mode** of DeepSeek-V3.2-Exp
  - `deepseek-reasoner` is the **thinking mode** of DeepSeek-V3.2-Exp

### Invoking the Chat API

Once you have obtained an API key, you can access the DeepSeek API using the following examples. These are non-stream examples; you can set the `stream` parameter to `true` to get streaming responses.

#### cURL Example

```bash
curl https://api.deepseek.com/chat/completions \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer ${DEEPSEEK_API_KEY}" \
  -d '{
    "model": "deepseek-chat",
    "messages": [
      {"role": "system", "content": "You are a helpful assistant."},
      {"role": "user", "content": "Hello!"}
    ],
    "stream": false
  }'
```

#### Python Example

```python
from openai import OpenAI

client = OpenAI(
    api_key="YOUR_DEEPSEEK_API_KEY",
    base_url="https://api.deepseek.com"
)

response = client.chat.completions.create(
    model="deepseek-chat",
    messages=[
        {"role": "system", "content": "You are a helpful assistant."},
        {"role": "user", "content": "Hello!"}
    ],
    stream=False
)

print(response.choices[0].message.content)
```

#### Node.js Example

```javascript
const OpenAI = require('openai');

const client = new OpenAI({
    apiKey: 'YOUR_DEEPSEEK_API_KEY',
    baseURL: 'https://api.deepseek.com'
});

async function main() {
    const response = await client.chat.completions.create({
        model: 'deepseek-chat',
        messages: [
            {role: 'system', content: 'You are a helpful assistant.'},
            {role: 'user', content: 'Hello!'}
        ],
        stream: false
    });
    
    console.log(response.choices[0].message.content);
}

main();
```

---

## Streaming Responses

### Overview

Streaming allows you to receive the model's response incrementally as it's generated, rather than waiting for the complete response. This is particularly useful for:
- **Improving user experience** with immediate feedback
- **Building chat interfaces** with typewriter effects
- **Processing long responses** without timeouts
- **Real-time applications** requiring low latency

### Enabling Streaming

Set the `stream` parameter to `true` in your API request.

### Python Streaming Example (Standard Model)

```python
from openai import OpenAI

client = OpenAI(
    api_key="YOUR_DEEPSEEK_API_KEY",
    base_url="https://api.deepseek.com"
)

messages = [
    {"role": "system", "content": "You are a helpful assistant."},
    {"role": "user", "content": "Write a short poem about artificial intelligence."}
]

response = client.chat.completions.create(
    model="deepseek-chat",
    messages=messages,
    stream=True
)

print("Assistant: ", end="", flush=True)
for chunk in response:
    if chunk.choices[0].delta.content:
        print(chunk.choices[0].delta.content, end="", flush=True)
print("\n")
```

### Node.js Streaming Example (Standard Model)

```javascript
const OpenAI = require('openai');

const client = new OpenAI({
    apiKey: 'YOUR_DEEPSEEK_API_KEY',
    baseURL: 'https://api.deepseek.com'
});

async function streamExample() {
    const stream = await client.chat.completions.create({
        model: 'deepseek-chat',
        messages: [
            {role: 'system', content: 'You are a helpful assistant.'},
            {role: 'user', content: 'Write a short poem about artificial intelligence.'}
        ],
        stream: true
    });
    
    process.stdout.write('Assistant: ');
    for await (const chunk of stream) {
        if (chunk.choices[0]?.delta?.content) {
            process.stdout.write(chunk.choices[0].delta.content);
        }
    }
    console.log('\n');
}

streamExample();
```

### cURL Streaming Example

```bash
curl https://api.deepseek.com/chat/completions \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer ${DEEPSEEK_API_KEY}" \
  -d '{
    "model": "deepseek-chat",
    "messages": [
      {"role": "system", "content": "You are a helpful assistant."},
      {"role": "user", "content": "Hello!"}
    ],
    "stream": true
  }'
```

### Streaming with Multi-round Conversations

```python
from openai import OpenAI

client = OpenAI(
    api_key="YOUR_DEEPSEEK_API_KEY",
    base_url="https://api.deepseek.com"
)

messages = [{"role": "user", "content": "What's the capital of France?"}]

# First round
response = client.chat.completions.create(
    model="deepseek-chat",
    messages=messages,
    stream=True
)

print("Assistant: ", end="", flush=True)
assistant_message = ""
for chunk in response:
    if chunk.choices[0].delta.content:
        content = chunk.choices[0].delta.content
        print(content, end="", flush=True)
        assistant_message += content
print("\n")

# Add assistant response to conversation history
messages.append({"role": "assistant", "content": assistant_message})

# Second round
messages.append({"role": "user", "content": "What about Germany?"})

response = client.chat.completions.create(
    model="deepseek-chat",
    messages=messages,
    stream=True
)

print("Assistant: ", end="", flush=True)
assistant_message = ""
for chunk in response:
    if chunk.choices[0].delta.content:
        content = chunk.choices[0].delta.content
        print(content, end="", flush=True)
        assistant_message += content
print("\n")
```

### Streaming Best Practices

1. **Buffer management**: Accumulate chunks to reconstruct the complete message for conversation history
2. **Error handling**: Implement robust error handling for network interruptions
3. **Flush output**: Use `flush=True` in Python or appropriate flushing in other languages for real-time display
4. **Token counting**: Keep track of tokens even in streaming mode for accurate billing
5. **Timeout handling**: Set appropriate timeouts for streaming connections

### Streaming vs Non-Streaming Comparison

| Aspect | Streaming | Non-Streaming |
|--------|-----------|---------------|
| **Latency** | Low (immediate first token) | High (wait for complete response) |
| **User Experience** | Progressive (typewriter effect) | All-at-once |
| **Memory Usage** | Lower (process chunks) | Higher (complete response) |
| **Implementation** | Slightly more complex | Simpler |
| **Use Case** | Chat interfaces, real-time apps | Batch processing, simple scripts |
| **Token Counting** | Manual accumulation needed | Automatic in response |

---

## Temperature Parameter

### Default Value

The default value of `temperature` is **1.0**.

### Recommended Temperature Settings by Use Case

| Use Case | Recommended Temperature |
|----------|------------------------|
| **Coding / Math** | 0.0 |
| **Data Cleaning / Data Analysis** | 1.0 |
| **General Conversation** | 1.3 |
| **Translation** | 1.3 |
| **Creative Writing / Poetry** | 1.5 |

### Guidelines

- **Lower temperature (0.0)**: More deterministic and focused outputs, ideal for tasks requiring precision like coding and mathematics
- **Default temperature (1.0)**: Balanced outputs suitable for analytical tasks
- **Higher temperature (1.3-1.5)**: More creative and diverse outputs, suitable for conversational and creative tasks

---

## Quick Reference

### Model Selection Guide

- Use **deepseek-chat** for:
  - Standard conversational tasks
  - Function calling requirements
  - FIM (Fill-in-the-Middle) completion
  - Cost-effective solutions

- Use **deepseek-reasoner** for:
  - Tasks requiring extended reasoning
  - Complex problem-solving
  - Longer output requirements (up to 64K tokens)
  - When you need the thinking mode capabilities

### Key Features Summary

- **Context Length**: 128K tokens for both models
- **JSON Output**: Supported by both models
- **Function Calling**: Only available in deepseek-chat
- **Streaming**: Supported (set `stream: true`)
- **OpenAI Compatible**: Can use OpenAI SDKs with modified configuration

### Cost Optimization Tips

1. **Cache hits** are significantly cheaper ($0.028 vs $0.28 per 1M tokens)
2. Use appropriate temperature settings to avoid regeneration
3. Choose the right model based on your needs (deepseek-chat is more cost-effective for simpler tasks)
4. Monitor token usage regularly
5. Leverage granted balance before topped-up balance

---

## Token & Token Usage

### What are Tokens?

Tokens are the basic units used by models to represent natural language text and are also the units used for billing. They can be intuitively understood as "characters" or "words". Typically, a Chinese word, an English word, a number, or a symbol is counted as a token.

### Token Conversion Ratios

Generally, the conversion ratio between tokens in the model and the number of characters is approximately as follows:

- **1 English character ≈ 0.3 token**
- **1 Chinese character ≈ 0.6 token**

**Important Notes:**
- Due to different tokenization methods used by different models, the conversion ratios can vary
- The actual number of tokens processed each time is based on the model's return, which you can view from the usage results

### Calculate Token Usage Offline

You can run the demo tokenizer code in the `deepseek_tokenizer.zip` package to calculate the token usage for your input/output offline.

**Resource:** [deepseek_tokenizer.zip](https://api-docs.deepseek.com/deepseek_tokenizer.zip)

### Token Calculation Examples

| Text Type | Example | Approximate Tokens |
|-----------|---------|-------------------|
| English sentence | "Hello, how are you?" | ~6 tokens |
| Chinese sentence | "你好吗？" | ~2.4 tokens |
| Mixed content | "Hello 你好" | ~3.6 tokens |
| Code snippet | `def hello(): print("hi")` | ~8-10 tokens |

---

## Rate Limit

### Rate Limit Policy

DeepSeek API does **NOT** constrain user's rate limit. We will try our best to serve every request.

### High Traffic Handling

However, please note that when our servers are under high traffic pressure, your requests may take some time to receive a response from the server. During this period, your HTTP request will remain connected, and you may continuously receive contents in the following formats:

**Non-streaming requests:** Continuously return empty lines

**Streaming requests:** Continuously return SSE keep-alive comments (`: keep-alive`)

### Important Considerations

- These contents do not affect the parsing of the JSON body by the OpenAI SDK
- If you are parsing the HTTP responses yourself, please ensure to handle these empty lines or comments appropriately
- **Connection timeout:** If the request is still not completed after 30 minutes, the server will close the connection

### Best Practices for High Traffic

1. Implement proper timeout handling in your client code
2. Handle empty lines and keep-alive comments gracefully
3. Use the OpenAI SDK for automatic handling of these edge cases
4. Implement exponential backoff for retry logic
5. Monitor response times and adjust application logic accordingly

---

## Error Codes

When calling DeepSeek API, you may encounter errors. Here are the causes and solutions for each error code.

### Error Code Reference

| Code | Error Type | Cause | Solution |
|------|-----------|-------|----------|
| **400** | Invalid Format | Invalid request body format | Modify your request body according to the hints in the error message. Refer to [DeepSeek API Docs](https://api-docs.deepseek.com/) for API format details. |
| **401** | Authentication Fails | Authentication fails due to wrong API key | Check your API key. If you don't have one, [create an API key](https://platform.deepseek.com/api_keys) first. |
| **402** | Insufficient Balance | You have run out of balance | Check your account's balance and visit the [Top up page](https://platform.deepseek.com/top_up) to add funds. |
| **422** | Invalid Parameters | Your request contains invalid parameters | Modify your request parameters according to the hints in the error message. Refer to [DeepSeek API Docs](https://api-docs.deepseek.com/) for details. |
| **429** | Rate Limit Reached | You are sending requests too quickly | Pace your requests reasonably. Consider temporarily switching to alternative LLM service providers like OpenAI. |
| **500** | Server Error | Our server encounters an issue | Retry your request after a brief wait and contact support if the issue persists. |
| **503** | Server Overloaded | The server is overloaded due to high traffic | Retry your request after a brief wait. |

### Error Handling Best Practices

1. **Implement retry logic** with exponential backoff for 500 and 503 errors
2. **Validate API keys** before making requests to avoid 401 errors
3. **Check balance regularly** to prevent 402 errors
4. **Validate parameters** locally before sending to reduce 422 errors
5. **Rate limit your requests** to avoid 429 errors
6. **Log error messages** for debugging and monitoring

### Example Error Handling Code

```python
import time
from openai import OpenAI

def make_api_call_with_retry(client, messages, max_retries=3):
    for attempt in range(max_retries):
        try:
            response = client.chat.completions.create(
                model="deepseek-chat",
                messages=messages
            )
            return response
        except Exception as e:
            error_code = getattr(e, 'status_code', None)
            
            if error_code in [500, 503]:
                # Retry with exponential backoff
                wait_time = 2 ** attempt
                print(f"Server error, retrying in {wait_time}s...")
                time.sleep(wait_time)
            elif error_code == 429:
                # Rate limited, wait longer
                print("Rate limited, waiting 60s...")
                time.sleep(60)
            else:
                # Don't retry for other errors
                raise e
    
    raise Exception("Max retries exceeded")
```

---

## Reasoning Model (deepseek-reasoner)

### Overview

`deepseek-reasoner` is a reasoning model developed by DeepSeek. Before delivering the final answer, the model first generates a **Chain of Thought (CoT)** to enhance the accuracy of its responses. The API provides users with access to the CoT content generated by `deepseek-reasoner`, enabling them to view, display, and distill it.

### Prerequisites

When using `deepseek-reasoner`, please upgrade the OpenAI SDK first to support the new parameters:

```bash
pip3 install -U openai
```

### API Parameters

#### Input Parameters

- **max_tokens**: The maximum output length (including the CoT part)
  - Default: 32K
  - Maximum: 64K

#### Output Parameters

- **reasoning_content**: The content of the CoT, which is at the same level as `content` in the output structure
- **content**: The content of the final answer

### Supported Features

✓ **Json Output**  
✓ **Chat Completion**  
✓ **Chat Prefix Completion (Beta)**

### Not Supported Features

✗ **Function Calling**  
✗ **FIM (Beta)**

### Not Supported Parameters

The following parameters are not supported:
- `temperature`
- `top_p`
- `presence_penalty`
- `frequency_penalty`
- `logprobs`
- `top_logprobs`

**Important Compatibility Note:**
- Setting `temperature`, `top_p`, `presence_penalty`, or `frequency_penalty` will **not trigger an error** but will also have **no effect**
- Setting `logprobs` or `top_logprobs` **will trigger an error**

---

## Multi-round Conversation (Standard Model)

### Overview

The DeepSeek `/chat/completions` API is a **"stateless" API**, meaning the server does not record the context of the user's requests. Therefore, users must **concatenate all previous conversation history** and pass it to the chat API with each request.

### Implementation Example

The following Python code demonstrates how to concatenate context to achieve multi-turn conversations:

```python
from openai import OpenAI

client = OpenAI(
    api_key="<DeepSeek API Key>",
    base_url="https://api.deepseek.com"
)

# Round 1
messages = [{"role": "user", "content": "What's the highest mountain in the world?"}]

response = client.chat.completions.create(
    model="deepseek-chat",
    messages=messages
)

messages.append(response.choices[0].message)
print(f"Messages Round 1: {messages}")

# Round 2
messages.append({"role": "user", "content": "What is the second?"})

response = client.chat.completions.create(
    model="deepseek-chat",
    messages=messages
)

messages.append(response.choices[0].message)
print(f"Messages Round 2: {messages}")
```

### Message Flow Breakdown

**First Round:**
```json
[
  {"role": "user", "content": "What's the highest mountain in the world?"}
]
```

**Second Round:**

After the first round, you must:
1. Add the model's output from the first round to the end of the messages array
2. Add the new question to the end of the messages array

```json
[
  {"role": "user", "content": "What's the highest mountain in the world?"},
  {"role": "assistant", "content": "The highest mountain in the world is Mount Everest."},
  {"role": "user", "content": "What is the second?"}
]
```

### Best Practices

1. **Always maintain conversation history** - Store all messages in your application
2. **Append in order** - Always add messages sequentially (user → assistant → user → assistant)
3. **Include system messages** - If using a system prompt, keep it at the beginning of all requests
4. **Monitor token usage** - Long conversations can consume significant tokens
5. **Leverage context caching** - Repeated prefixes benefit from automatic caching

---

## JSON Output

### Overview

In many scenarios, users need the model to output in strict JSON format to achieve structured output, facilitating subsequent parsing. DeepSeek provides **JSON Output** to ensure the model outputs valid JSON strings.

### Requirements

To enable JSON Output, users should:

1. **Set the `response_format` parameter** to `{'type': 'json_object'}`
2. **Include the word "json"** in the system or user prompt, and provide an example of the desired JSON format to guide the model
3. **Set the `max_tokens` parameter** reasonably to prevent the JSON string from being truncated midway
4. **Handle empty content** - When using the JSON Output feature, the API may occasionally return empty content. We are actively working on optimizing this issue. You can try modifying the prompt to mitigate such problems.

### Sample Code

Here is the complete Python code demonstrating the use of JSON Output:

```python
import json
from openai import OpenAI

client = OpenAI(
    api_key="<your api key>",
    base_url="https://api.deepseek.com",
)

system_prompt = """
The user will provide some exam text. Please parse the "question" and "answer"
and output them in JSON format.

EXAMPLE INPUT:
Which is the highest mountain in the world? Mount Everest.

EXAMPLE JSON OUTPUT:
{
  "question": "Which is the highest mountain in the world?",
  "answer": "Mount Everest"
}
"""

user_prompt = "Which is the longest river in the world? The Nile River."

messages = [
    {"role": "system", "content": system_prompt},
    {"role": "user", "content": user_prompt}
]

response = client.chat.completions.create(
    model="deepseek-chat",
    messages=messages,
    response_format={
        'type': 'json_object'
    }
)

print(json.loads(response.choices[0].message.content))
```

**Output:**
```json
{
  "question": "Which is the longest river in the world?",
  "answer": "The Nile River"
}
```

### Best Practices for JSON Output

1. **Always include examples** - Show the model exactly what JSON structure you want
2. **Be specific about schema** - Define all required fields clearly in your prompt
3. **Use descriptive field names** - Make it clear what each field should contain
4. **Set appropriate max_tokens** - Ensure the complete JSON can be generated
5. **Validate output** - Always parse and validate the JSON response in your code
6. **Handle errors gracefully** - Implement retry logic for empty or malformed responses

### Common Use Cases

- **Data extraction** from unstructured text
- **Structured information retrieval**
- **Form filling** and data normalization
- **API response formatting**
- **Database record creation**
- **Configuration file generation**

---

## Function Calling

### Overview

**Function Calling** allows the model to call external tools to enhance its capabilities. The model can intelligently determine when to call functions and extract the necessary parameters from user input.

### How Function Calling Works

1. User sends a request with available tools defined
2. Model analyzes the request and determines if a function should be called
3. Model returns function name and parameters
4. User executes the function and sends results back
5. Model generates natural language response based on function results

### Sample Code

Here is an example of using Function Calling to get the current weather information:

```python
from openai import OpenAI

def send_messages(messages):
    response = client.chat.completions.create(
        model="deepseek-chat",
        messages=messages,
        tools=tools
    )
    return response.choices[0].message

client = OpenAI(
    api_key="<your api key>",
    base_url="https://api.deepseek.com",
)

tools = [
    {
        "type": "function",
        "function": {
            "name": "get_weather",
            "description": "Get weather of a location, the user should supply a location first.",
            "parameters": {
                "type": "object",
                "properties": {
                    "location": {
                        "type": "string",
                        "description": "The city and state, e.g. San Francisco, CA",
                    }
                },
                "required": ["location"]
            },
        }
    },
]

messages = [{"role": "user", "content": "How's the weather in Hangzhou, Zhejiang?"}]
message = send_messages(messages)

print(f"User>\t {messages[0]['content']}")

tool = message.tool_calls[0]
messages.append(message)
messages.append({"role": "tool", "tool_call_id": tool.id, "content": "24℃"})

message = send_messages(messages)
print(f"Model>\t {message.content}")
```

### Execution Flow

1. **User:** Asks about the current weather in Hangzhou
2. **Model:** Returns the function `get_weather({location: 'Hangzhou'})`
3. **User:** Calls the function `get_weather({location: 'Hangzhou'})` and provides the result to the model
4. **Model:** Returns in natural language, "The current temperature in Hangzhou is 24°C."

**Important Note:** The functionality of the `get_weather` function needs to be provided by the user. The model itself does not execute specific functions.

---

## Function Calling: Strict Mode (Beta)

### Overview

In **strict mode**, the model strictly adheres to the format requirements of the function's JSON schema when outputting a function call, ensuring that the model's output complies with the user's definition.

### Enabling Strict Mode

To use strict mode, you need to:

1. Use `base_url="https://api.deepseek.com/beta"` to enable Beta features
2. In the `tools` parameter, all functions need to set the `strict` property to `true`
3. The server will validate the JSON Schema of the function provided by the user. If the schema does not conform to the specifications or contains JSON schema types that are not supported by the server, an error message will be returned

### Strict Mode Tool Definition Example

```python
{
    "type": "function",
    "function": {
        "name": "get_weather",
        "strict": true,
        "description": "Get weather of a location, the user should supply a location first.",
        "parameters": {
            "type": "object",
            "properties": {
                "location": {
                    "type": "string",
                    "description": "The city and state, e.g. San Francisco, CA",
                }
            },
            "required": ["location"],
            "additionalProperties": false
        }
    }
}
```

### Supported JSON Schema Types in Strict Mode

| Type | Description |
|------|-------------|
| `object` | Nested structure with key-value pairs |
| `string` | Text values with optional constraints |
| `number` | Numeric values (floating point) |
| `integer` | Whole numbers |
| `boolean` | True/false values |
| `array` | Lists of items |
| `enum` | Predefined set of values |
| `anyOf` | Matches any one of provided schemas |

---

## JSON Schema Type Details for Strict Mode

### object

The `object` defines a nested structure containing key-value pairs, where `properties` specifies the schema for each key (or property) within the object.

**Requirements:**
- All properties of every object **must be set as required**
- The `additionalProperties` attribute of the object **must be set to false**

**Example:**
```json
{
  "type": "object",
  "properties": {
    "name": { "type": "string" },
    "age": { "type": "integer" }
  },
  "required": ["name", "age"],
  "additionalProperties": false
}
```

---

### string

**Supported parameters:**

- **pattern**: Uses regular expressions to constrain the format of the string
- **format**: Validates the string against predefined common formats

**Currently supported formats:**
- `email` - Email address
- `hostname` - Hostname
- `ipv4` - IPv4 address
- `ipv6` - IPv6 address
- `uuid` - UUID

**Unsupported parameters:**
- `minLength`
- `maxLength`

**Example:**
```json
{
  "type": "object",
  "properties": {
    "user_email": {
      "type": "string",
      "description": "The user's email address",
      "format": "email"
    },
    "zip_code": {
      "type": "string",
      "description": "Six digit postal code",
      "pattern": "^\\d{6}$"
    }
  }
}
```

---

### number/integer

**Supported parameters:**

- **const**: Specifies a constant numeric value
- **default**: Defines the default value of the number
- **minimum**: Specifies the minimum value
- **maximum**: Specifies the maximum value
- **exclusiveMinimum**: Defines a value that the number must be greater than
- **exclusiveMaximum**: Defines a value that the number must be less than
- **multipleOf**: Ensures that the number is a multiple of the specified value

**Example:**
```json
{
  "type": "object",
  "properties": {
    "score": {
      "type": "integer",
      "description": "A number from 1-5, which represents your rating, the higher, the better",
      "minimum": 1,
      "maximum": 5
    }
  },
  "required": ["score"],
  "additionalProperties": false
}
```

---

### array

**Unsupported parameters:**
- `minItems`
- `maxItems`

**Example:**
```json
{
  "type": "object",
  "properties": {
    "keywords": {
      "type": "array",
      "description": "Five keywords of the article, sorted by importance",
      "items": {
        "type": "string",
        "description": "A concise and accurate keyword or phrase."
      }
    }
  },
  "required": ["keywords"],
  "additionalProperties": false
}
```

---

### enum

The `enum` ensures that the output is one of the predefined options. For example, in the case of order status, it can only be one of a limited set of specified states.

**Example:**
```json
{
  "type": "object",
  "properties": {
    "order_status": {
      "type": "string",
      "description": "Ordering status",
      "enum": ["pending", "processing", "shipped", "cancelled"]
    }
  }
}
```

---

### anyOf

Matches any one of the provided schemas, allowing fields to accommodate multiple valid formats. For example, a user's account could be either an email address or a phone number:

**Example:**
```json
{
  "type": "object",
  "properties": {
    "account": {
      "anyOf": [
        { 
          "type": "string", 
          "format": "email", 
          "description": "Can be an email address" 
        },
        { 
          "type": "string", 
          "pattern": "^\\d{11}$", 
          "description": "Or an 11-digit phone number" 
        }
      ]
    }
  }
}
```

---

### $ref and $def

You can use `$def` to define reusable modules and then use `$ref` to reference them, reducing schema repetition and enabling modularization. Additionally, `$ref` can be used independently to define recursive structures.

**Example:**
```json
{
  "type": "object",
  "properties": {
    "report_date": {
      "type": "string",
      "description": "The date when the report was published"
    },
    "authors": {
      "type": "array",
      "description": "The authors of the report",
      "items": {
        "$ref": "#/$def/author"
      }
    }
  },
  "required": ["report_date", "authors"],
  "additionalProperties": false,
  "$def": {
    "author": {
      "type": "object",
      "properties": {
        "name": {
          "type": "string",
          "description": "author's name"
        },
        "institution": {
          "type": "string",
          "description": "author's institution"
        },
        "email": {
          "type": "string",
          "format": "email",
          "description": "author's email"
        }
      },
      "additionalProperties": false,
      "required": ["name", "institution", "email"]
    }
  }
}
```

---

## Context Caching (Enhanced Documentation)

### Overview

The DeepSeek API **Context Caching on Disk Technology** is enabled by default for all users, allowing them to benefit without needing to modify their code. This innovative feature can reduce costs by up to **90%** for requests with repetitive content.

### How Context Caching Works

Each user request triggers the construction of a hard disk cache. If subsequent requests have overlapping prefixes with previous requests, the overlapping part will only be fetched from the cache, which counts as a **"cache hit."**

**Critical Note:** Between two requests, only the repeated **prefix** part can trigger a "cache hit." Partial matches in the middle of the input will not trigger a cache hit.

### Pricing Impact

| Input Type | Price per 1M Tokens |
|------------|---------------------|
| **Cache Hit** | $0.028 |
| **Cache Miss** | $0.28 |
| **Output** | $0.42 |

Cache hits are **10x cheaper** than cache misses, resulting in potential cost savings of up to 90%.

---

## Context Caching Examples

### Example 1: Long Text Q&A

**First Request:**
```python
messages: [
    {"role": "system", "content": "You are an experienced financial report analyst..."},
    {"role": "user", "content": "<financial report content>\n\nPlease summarize the key information of this financial report."}
]
```

**Second Request:**
```python
messages: [
    {"role": "system", "content": "You are an experienced financial report analyst..."},
    {"role": "user", "content": "<financial report content>\n\nPlease analyze the profitability of this financial report."}
]
```

In this example, both requests have the same **prefix**, which is the system message + `<financial report content>` in the user message. During the second request, this prefix part will count as a "cache hit."

---

### Example 2: Multi-round Conversation

**First Request:**
```python
messages: [
    {"role": "system", "content": "You are a helpful assistant"},
    {"role": "user", "content": "What is the capital of China?"}
]
```

**Second Request:**
```python
messages: [
    {"role": "system", "content": "You are a helpful assistant"},
    {"role": "user", "content": "What is the capital of China?"},
    {"role": "assistant", "content": "The capital of China is Beijing."},
    {"role": "user", "content": "What is the capital of the United States?"}
]
```

In this example, the second request can reuse the initial system message and user message from the first request, which will count as a "cache hit."

---

### Example 3: Using Few-shot Learning

In practical applications, users can enhance the model's output performance through few-shot learning. Few-shot learning involves providing a few examples in the request to allow the model to learn a specific pattern. Since few-shot generally provides the same context prefix, **the cost of few-shot is significantly reduced** with the support of context caching.

**First Request:**
```python
messages: [
    {"role": "system", "content": "You are a history expert. The user will provide a series of questions, and your answers should be concise and start with `Answer:`"},
    {"role": "user", "content": "In what year did Qin Shi Huang unify the six states?"},
    {"role": "assistant", "content": "Answer: 221 BC"},
    {"role": "user", "content": "Who was the founder of the Han Dynasty?"},
    {"role": "assistant", "content": "Answer: Liu Bang"},
    {"role": "user", "content": "Who was the last emperor of the Tang Dynasty?"},
    {"role": "assistant", "content": "Answer: Li Zhu"},
    {"role": "user", "content": "Who was the founding emperor of the Ming Dynasty?"},
    {"role": "assistant", "content": "Answer: Zhu Yuanzhang"},
    {"role": "user", "content": "Who was the founding emperor of the Qing Dynasty?"}
]
```

**Second Request:**
```python
messages: [
    {"role": "system", "content": "You are a history expert. The user will provide a series of questions, and your answers should be concise and start with `Answer:`"},
    {"role": "user", "content": "In what year did Qin Shi Huang unify the six states?"},
    {"role": "assistant", "content": "Answer: 221 BC"},
    {"role": "user", "content": "Who was the founder of the Han Dynasty?"},
    {"role": "assistant", "content": "Answer: Liu Bang"},
    {"role": "user", "content": "Who was the last emperor of the Tang Dynasty?"},
    {"role": "assistant", "content": "Answer: Li Zhu"},
    {"role": "user", "content": "Who was the founding emperor of the Ming Dynasty?"},
    {"role": "assistant", "content": "Answer: Zhu Yuanzhang"},
    {"role": "user", "content": "When did the Shang Dynasty fall?"}
]
```

In this example, 4-shots are used. The only difference between the two requests is the last question. The second request can reuse the content of the first 4 rounds of dialogue from the first request, which will count as a "cache hit."

---

## Checking Cache Hit Status

In the response from the DeepSeek API, two fields in the `usage` section reflect the cache hit status of the request:

1. **prompt_cache_hit_tokens**: The number of tokens in the input of this request that resulted in a cache hit ($0.028 per million tokens)
2. **prompt_cache_miss_tokens**: The number of tokens in the input of this request that did not result in a cache hit ($0.28 per million tokens)

### Example Response:
```python
{
  "usage": {
    "prompt_tokens": 150000,
    "prompt_cache_hit_tokens": 140000,
    "prompt_cache_miss_tokens": 10000,
    "completion_tokens": 500,
    "total_tokens": 150500
  }
}
```

---

## Context Caching: Key Points

### Hard Disk Cache and Output Randomness

The hard disk cache only matches the prefix part of the user's input. The output is still generated through computation and inference, and it is influenced by parameters such as `temperature`, introducing randomness.

### Additional Notes

1. **Minimum cache unit**: The cache system uses 64 tokens as a storage unit; content less than 64 tokens will not be cached
2. **Best-effort system**: The cache system works on a "best-effort" basis and does not guarantee a 100% cache hit rate
3. **Automatic cleanup**: Cache construction takes seconds. Once the cache is no longer in use, it will be automatically cleared, usually within a few hours to a few days

### Latency Reduction

First token latency will be significantly reduced in requests with long, repetitive inputs. For a 128K prompt with high cache hit rate, the first token latency is cut from **13 seconds to just 500ms**.

### Security

- Each user's cache is **isolated** and logically invisible to others, ensuring data privacy and security
- Unused cache entries are automatically cleared after a period, ensuring they are not retained or repurposed
- **Zero additional storage fees** - Storage usage for the cache is free

---

## Beneficial Scenarios for Context Caching

**Ideal use cases:**

- **Q&A assistants** with long preset prompts
- **Role-play scenarios** with extensive character settings and multi-turn conversations
- **Data analysis** with recurring queries on the same documents/files
- **Code analysis and debugging** with repeated repository references
- **Few-shot learning** with consistent example sets
- **Document processing** with recurring document prefixes
- **Agent workflows** with repeated system instructions

**Average savings:** Historical data shows that users save over **50% on average** even without optimization. With proper optimization for cache characteristics, users can save up to **90%**.

---

## Why DeepSeek Leads with Disk Caching

Based on publicly available information, DeepSeek appears to be the first large language model provider globally to implement extensive disk caching in API services.

This is made possible by the **MLA (Multi-Head Latent Attention) architecture** in DeepSeek V2, which:
- Enhances model performance
- Significantly reduces the size of the context KV cache
- Enables efficient storage on low-cost disks

### API Capacity

The DeepSeek API is designed to handle:
- **Up to 1 trillion tokens per day**
- **No limits on concurrency or rate**
- Ensuring high-quality service for all users

Feel free to scale up your parallelism!

---

## Multi-round Conversation with Reasoning Model

### Conversation Flow

In each round of the conversation, the model outputs:
1. The CoT (`reasoning_content`)
2. The final answer (`content`)

In the next round of the conversation, **the CoT from previous rounds is NOT concatenated into the context**.

### Conversation Diagram

```
Turn 1          Turn 2          Turn 3
┌─────────┐     ┌─────────┐     ┌─────────┐
│Question①│     │Question①│     │Question①│
└─────────┘     └─────────┘     └─────────┘
┌ ─ ─ ─ ─┐     ┌─────────┐     ┌─────────┐
│  COT①  │     │ Answer①│     │ Answer①│
└ ─ ─ ─ ─┘     └─────────┘     └─────────┘
┌─────────┐     ┌─────────┐     ┌─────────┐
│ Answer① │     │Question②│     │Question②│
└─────────┘     └─────────┘     └─────────┘
                ┌ ─ ─ ─ ─┐     ┌─────────┐
                │  COT②  │     │ Answer②│
                └ ─ ─ ─ ─┘     └─────────┘
                ┌─────────┐     ┌─────────┐
                │ Answer②│     │Question③│
                └─────────┘     └─────────┘
                                ┌ ─ ─ ─ ─┐
                                │  COT③  │
                                └ ─ ─ ─ ─┘
                                ┌─────────┐
                                │ Answer③│
                                └─────────┘
```

**Critical Note:** If the `reasoning_content` field is included in the sequence of input messages, the API will return a **400 error**. Therefore, you should remove the `reasoning_content` field from the API response before making the next API request.

---

## API Examples for Reasoning Model

The following code demonstrates how to access the CoT and the final answer, as well as how to conduct multi-round conversations.

### Non-Streaming Example

```python
from openai import OpenAI

client = OpenAI(
    api_key="<DeepSeek API Key>",
    base_url="https://api.deepseek.com"
)

# Round 1
messages = [{"role": "user", "content": "9.11 and 9.8, which is greater?"}]

response = client.chat.completions.create(
    model="deepseek-reasoner",
    messages=messages,
    stream=False
)

# Access the reasoning content and final answer
reasoning_content = response.choices[0].message.reasoning_content
content = response.choices[0].message.content

print("Reasoning Process:")
print(reasoning_content)
print("\nFinal Answer:")
print(content)

# Round 2
messages.append({"role": "assistant", "content": content})
messages.append({
    "role": "user", 
    "content": "How many Rs are there in the word 'strawberry'?"
})

response = client.chat.completions.create(
    model="deepseek-reasoner",
    messages=messages,
    stream=False
)

reasoning_content = response.choices[0].message.reasoning_content
content = response.choices[0].message.content

print("\nReasoning Process:")
print(reasoning_content)
print("\nFinal Answer:")
print(content)
```

### Streaming Example

```python
from openai import OpenAI

client = OpenAI(
    api_key="<DeepSeek API Key>",
    base_url="https://api.deepseek.com"
)

# Round 1
messages = [{"role": "user", "content": "9.11 and 9.8, which is greater?"}]

response = client.chat.completions.create(
    model="deepseek-reasoner",
    messages=messages,
    stream=True
)

reasoning_content = ""
content = ""

for chunk in response:
    if chunk.choices[0].delta.reasoning_content:
        reasoning_content += chunk.choices[0].delta.reasoning_content
        print(chunk.choices[0].delta.reasoning_content, end="", flush=True)
    elif chunk.choices[0].delta.content:
        content += chunk.choices[0].delta.content
        print(chunk.choices[0].delta.content, end="", flush=True)

print("\n")

# Round 2
messages.append({"role": "assistant", "content": content})
messages.append({
    "role": "user",
    "content": "How many Rs are there in the word 'strawberry'?"
})

response = client.chat.completions.create(
    model="deepseek-reasoner",
    messages=messages,
    stream=True
)

reasoning_content = ""
content = ""

for chunk in response:
    if chunk.choices[0].delta.reasoning_content:
        reasoning_content += chunk.choices[0].delta.reasoning_content
        print(chunk.choices[0].delta.reasoning_content, end="", flush=True)
    elif chunk.choices[0].delta.content:
        content += chunk.choices[0].delta.content
        print(chunk.choices[0].delta.content, end="", flush=True)
```

### Key Points for Multi-round Conversations

1. **Do NOT include `reasoning_content`** in subsequent messages - only include the final `content`
2. **Store conversation history** properly by appending assistant responses with only the `content` field
3. **Handle streaming carefully** by separately accumulating `reasoning_content` and `content`
4. **The reasoning process is ephemeral** - it's only generated for the current turn and not used in future context

### Use Cases for Reasoning Model

**Ideal for:**
- Complex mathematical problems
- Logic puzzles and riddles
- Multi-step reasoning tasks
- Code debugging and optimization
- Scientific problem-solving
- Strategic planning

**Not ideal for:**
- Simple factual queries
- Creative writing (use standard model with higher temperature)
- Function calling tasks
- Scenarios requiring rapid responses without thinking overhead