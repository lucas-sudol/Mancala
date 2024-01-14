# Test Case Charts
## int captureStones ( int stoppingPoint )
Category        Specific Test Case      Expected Result
empty pit       empty pit 1             4
empty pit       empty pit 7             4
test if opposite pit empties: pit 1,12  0,4

## int distributeStones ( int startingPoint )
Category        Specific Test Case      Expected Result
store          removePit11, start 2     9
store          removePit6, start  7     9
stoneMovement  Start 1                  InitialStones, 4

## int getNumStones( int pitNum)
Category        Specific Test Case      Expected Result
getStones       pit 1                   4
getStones       pit 12                  4
getStones       pit 1, after adding     5

## boolean isSideEmpty ( int pitNum)
Category        Specific Test Case      Expected Result
Player 1        removeSideStones        true
Player 1        removeSomeStones        flase
Player 2        removeSideStones        true
Player 2        removeSomeStones        flase

## void resetBoard ()
Category        Specific Test Case      Expected Result
pits            Pits: 1,4,7             4,4,4
stores          stores 1,2              0,0

## void registerPlayers ( Player one , Player two)
Category        Specific Test Case      Expected Result
player1         addPlayers              player1
player2         addPlayers              player2
