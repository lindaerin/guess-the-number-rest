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
## Instructions
* Configure applciation.properties file to match database information

```
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/"YOUR_DB_FILE"?serverTimezone=America/Chicago&useSSL=false
spring.datasource.username="user"
spring.datasource.password="pass" 
``` 
