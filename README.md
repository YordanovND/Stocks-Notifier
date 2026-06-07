# Stocks Notifier

A lightweight Spring Boot application that retrieves stock prices from Yahoo Finance and sends daily notifications via Telegram.

## Features

* Retrieves stock quotes from Yahoo Finance
* Sends notifications through Telegram Bot API
* Configurable stock symbol
* Configurable notification schedule
* No database required
* Can be executed locally or through GitHub Actions

## Tech Stack

* Java 21
* Spring Boot
* Jackson
* Yahoo Finance API
* Telegram Bot API
* GitHub Actions

## Configuration

### Yahoo Finance

```yaml
http:
  yahoo:
    baseUrl: https://query1.finance.yahoo.com
    followSymbol: PSFE
```

### Telegram

The application expects the following environment variables:

```text
TELEGRAM_BOT_TOKEN
TELEGRAM_CHAT_ID
```

Example:

```yaml
telegram:
  bot-token: ${TELEGRAM_BOT_TOKEN}
  chat-id: ${TELEGRAM_CHAT_ID}
```

## Telegram Bot Setup

1. Open Telegram
2. Search for `@BotFather`
3. Create a new bot using:

```text
/newbot
```

4. Save the generated bot token
5. Open a chat with your bot
6. Send:

```text
/start
```

7. Retrieve your chat id using:

```text
https://api.telegram.org/bot<TOKEN>/getUpdates
```

## Running Locally

```bash
./gradlew bootRun
```

Windows:

```powershell
gradlew.bat bootRun
```

## GitHub Actions

The project includes a GitHub Actions workflow that can run automatically every day and send a Telegram notification without requiring a dedicated server.

Required GitHub Secrets:

```text
TELEGRAM_BOT_TOKEN
TELEGRAM_CHAT_ID
```

## Example Notification

```text
📈 PSFE Daily Update

PSFE current price is -> $6.90.
Last update time is: 05-06-2026 23:00
```

## License

This project is provided for educational and personal use.
