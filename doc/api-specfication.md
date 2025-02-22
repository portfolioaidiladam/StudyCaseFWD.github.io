## Tic-tac-toe Game API Documentation

## Overview
This document provides detailed information about the Tic-tac-toe game REST API endpoints, request/response formats, 
and example usage.

## Base URL

```json
http://localhost:8080/tictactoe
```

## API Endpoints

## Start New Game
Creates a new game with specified board size.

```json
Endpoint: /api/game/start
```
Method: POST

## Parameters:
size (query parameter, integer, required)

Description: Size of the game board (NxN)

Constraints: 3 ≤ size ≤ 10

## Response:

```json
{
  "board": [
    ["EMPTY", "EMPTY", "EMPTY"],
    ["EMPTY", "EMPTY", "EMPTY"],
    ["EMPTY", "EMPTY", "EMPTY"]
  ],
  "size": 3,
  "currentPlayer": "X",
  "gameState": "IN_PROGRESS",
  "message": "Game started successfully"
}
```

## Status Codes:
200 OK: Game created successfully

400 Bad Request: Invalid board size

## Example Request:

```json
bash

curl -X POST "http://localhost:8080/tictactoe/api/game/start?size=3"
```

## Make Move
Makes a move at the specified position.

```json
Endpoint: /api/game/move
```
Method: POST

## Parameters:
row (query parameter, integer, required)

Description: Row index (0-based)


col (query parameter, integer, required)

Description: Column index (0-based)

## Response:

```json
{
  "board": [
    ["X", "EMPTY", "EMPTY"],
    ["EMPTY", "EMPTY", "EMPTY"],
    ["EMPTY", "EMPTY", "EMPTY"]
  ],
  "size": 3,
  "currentPlayer": "O",
  "gameState": "IN_PROGRESS",
  "message": "Move successful"
}
```

## Status Codes:
200 OK: Move successful

400 Bad Request: Invalid move

500 Internal Server Error: Server error

## Example Request:

```json
bash

curl -X POST "http://localhost:8080/tictactoe/api/game/move?row=0&col=0"
```

## Get Game State
Retrieves current game state.

```json
Endpoint: /api/game/state
```
Method: GET

## Response:

```json
{
  "board": [
    ["X", "O", "X"],
    ["O", "X", "O"],
    ["X", "EMPTY", "EMPTY"]
  ],
  "size": 3,
  "currentPlayer": "O",
  "gameState": "IN_PROGRESS",
  "message": "Game state retrieved successfully"
}
```

## Status Codes:
200 OK: State retrieved successfully

500 Internal Server Error: Server error

## Example Request:

```json
bash

curl "http://localhost:8080/tictactoe/api/game/state"
```

## Game States
The game can be in one of these states:

IN_PROGRESS: Game is ongoing

WIN: A player has won

DRAW: Game ended in a draw

## Players
X: First player

O: Second player

EMPTY: Empty cell

