# Project Title

Mancala

## Description

The Mancala Game is a classic two-player strategy board game where the objective is to capture more stones than your opponent.

This Java based Mancala Game implementation allows you to enjoy this traditional game on your computer. It provides a terminal-based interface so you can interact with the game and view results of the winner.

The game features the following:

Player setup: Enter the names of Player 1 and Player 2.
Initial stone distribution: pits are initialized with 4 stones each
Turn-based gameplay: Take turns to and choose a pit on your side of a board to start.

Capture stones: If the last stone you drop lands in your own empty pit, you capture your own stone and any stones in the pit directly opposite on your opponent's side.

Win conditions: The game ends when one side of the board is emtpy of stones, and then the corresponding side player wins. 

## Getting Started

### Dependencies

Java JDK with CIS2430 Containter

### Executing program

* How to build and run the program
gradle build
gradle echo

* include the expected output

Enter name for Player One: test
Enter name for Player Two: test
        Pits     12      11      10      9       8       7
test: 0          4       4       4       4       4       4
                 4       4       4       4       4       4       test: 0
        Pits     1       2       3       4       5       6

test: choose a pit to play (1-6): 


## Limitations

No limitations

## Author Information

Lucas Sudol
lsudol@uoguelph.ca

## Development History

Keep a log of what things you accomplish when.  You can use git's tagging feature to tag the versions or you can reference commits.
* 0.1 
    * Git repository (October 22)

* 0.1 
    * Function Stubs (October 22)

* 1.0
    * Completed project (November 26th)

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [simple-readme] (https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)



