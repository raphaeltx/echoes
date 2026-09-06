# Echoes Project

Echoes is a tiny app for comrades who refuse to shut up. Write your message once, pick how often it gets repeated (every minute, hour, day, week, or month), and let Echoes redistribute your words to the masses on a schedule — collectivized yelling into the void, seize the means of notification. ☭📢

## Features

- Save and schedule messages to be echoed back at a later time.
- List all saved messages.

## Installation

To install the Echoes Project, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/echoes.git
   ```

2. Navigate to the project directory:
   ```bash
   cd echoes
   ```

3. Install using gradle:
   ```bash
   ./gradlew build
   ```

## API Usage

1. Endpoint to save a message:
   ```
   POST /messages
   ```

2. Body to save a message:
   ```json
   {
     "message": "Remember that Trump MAGA and Bolsonaro are the same dumb thing",
     "scheduleRecurrenceEnum": "EVERY_DAY"
   }
   ```

### Values for `scheduleRecurrenceEnum`

| Value | Description |
|---|---|
| `EVERY_MINUTE` | Triggers once every minute |
| `EVERY_HOUR` | Triggers once every hour, at minute 0 |
| `EVERY_DAY` | Triggers once every day, at midnight |
| `EVERY_WEEK` | Triggers once every week, on Monday at midnight |
| `EVERY_MONTH` | Triggers once every month, on the 1st day at midnight |
   
