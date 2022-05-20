## Guess the Number REST Service

Spring Boot REST application using JDBC Template to access the database.

REST server play a number guessing game. In each game, a 4-digit number is generated where every digit is different. For each round, the user guesses a number and is told the exact and partial digit matches.

## REST Endpoints
* Begin Game ``` POST /api/begin ```
* Make a Guess ``` POST /api/guess ```
* List All Games ``` GET /api/game ```
* List Specific Game ``` GET /api/game/{gameId} ```

## Project Structure
```
mastermind
 ┣ controller
 ┃ ┣ Error.java
 ┃ ┣ MastermindController.java
 ┃ ┗ MastermindExceptionHandler.java
 ┣ dao
 ┃ ┣ MastermindGameDao.java
 ┃ ┣ MastermindGameDaoDB.java
 ┃ ┣ MastermindRoundDao.java
 ┃ ┗ MastermindRoundDaoDB.java
 ┣ model
 ┃ ┣ Game.java
 ┃ ┗ Round.java
 ┣ service
 ┃ ┣ InvalidGuessException.java
 ┃ ┗ MastermindService.java
 ┗ App.java
```

