#Technical stack
- In this exercise we require to design and implement an application using any JVM based language (Java, Kotlin, Scala).
- The application should be able to accept Rest API calls
- The application should store the data in a database of your choice
 
#Business scenario
- The application should implement Rock-Paper-Scissors game
- The game is played between a player and the application
- There could be many players with different names playing in parallel
- One player can play many rounds
- A round of the game is when a player ‘throws’ rock, paper or scissors shapes. The application, in turn, generates an arbitrary choice of a shape and announces the winner of the round: player or the application.
- Each round of the game can have possible outcomes:
  - Draw (player and the application have same shapes)
  - Win or Loss for a player (see common rock-paper-scissors rules)
- The application should be able to provide player’s statistics, e.g. rounds played, wins, losses…

#Application requirements:
The application should provide an API with the following capabilities:
- Submit a new round for a player with his/her shape choice. The API should return the round details: player’s and the application’s shapes, winner of the round
- Get player’s statistics
- Get players list
- The API should implement Basic authentication mechanism with username and password
#Things to consider:
- As a result of this exercise, we’d like to see the application working, code compiling and tests passing
- Please consider the environment setup for the application to run. That means we expect to see a guidance if any additional components need to be set up for application to run, e.g. local databases or docker/docker-compose stacks
- This exercise is to assess your knowledge and ability to apply the design and development best practices, so please consider that while completing it
- If there are some uncertainties in the exercise, feel free to make reasonable assumptions. Don’t forget to document such assumptions, so we can review them as well
- Also, feel free to add features that make the application and the game more fun
- We expect you to finish this exercise in 1-2 hours. That doesn’t mean you can’t do it faster or spend more time. It’s just a reference for you to keep in mind in order to not overly complicate it.

Once exercise is completed, please upload your code to your Github and share a link with us
