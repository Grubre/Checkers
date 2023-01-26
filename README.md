# Checkers
A multiplayer implementation of checkers with multiple variants and possibility to play against
to play against a bot.
## Implementation
The application is split into three components:

### server

The server is capable of holding multiple concurrent lobbies.
Each lobby manages connection between client and decides whether the moves they make are legal.
It also handles checking whether the game has ended, and if it is, decides who won.

### client

Client is the graphical interface written in JavaFX that allows the player to interact with the game.

### shared lib

Shared lib is where most of the program logic lives. Here we have all the code that is mutual between
server and client. That includes classes used to communicate, as well as everything concerning game logic.

