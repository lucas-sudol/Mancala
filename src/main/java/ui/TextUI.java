package ui;
import mancala.MancalaGame;
import mancala.Player;
import java.util.Scanner;
import mancala.InvalidMoveException;
import mancala.GameNotOverException;
import mancala.Saver;
import java.io.IOException; 
import mancala.UserProfile;
import mancala.AyoRules;
import mancala.KalahRules;
import mancala.PitNotFoundException;
import mancala.*;

public class TextUI{
    public static void main(String[] args) throws IOException, PitNotFoundException, InvalidMoveException{
        SwingUI gameUI = new SwingUI("Game");
        gameUI.setVisible(true);
        /*
        Scanner userIn = new Scanner(System.in);
        MancalaGame game = new MancalaGame();
        Player player1;
        Player player2;

        //Saver saveGame = new Saver();

        game.setBoard(new KalahRules());

        System.out.print("Enter name for Player One: ");
        String input = userIn.nextLine().trim();
        player1 = new Player(input);
        System.out.print("Enter name for Player Two: ");
        input = userIn.nextLine().trim();
        player2 = new Player(input);
        
        game.setPlayers(player1,player2);

        game.startNewGame();

        game.setCurrentPlayer(player1);



        int turn = 0;
        boolean continueCheck = true;



        do{
            System.out.println(game);
            if(game.getCurrentPlayer() == player1) {
                System.out.println(game.getCurrentPlayer().getName() + ": choose a pit to play (1-6): ");
                input = userIn.nextLine().trim();
                try {
                    game.move(Integer.parseInt(input));
                } catch (InvalidMoveException t) {
                    System.out.println("Invalid Move! Please try again!");
                    turn--;
                }
            } else {
                System.out.println(game.getCurrentPlayer().getName() + ": choose a pit to play (7-12): ");
                input = userIn.nextLine().trim();
                try {
                    game.move(Integer.parseInt(input));
                } catch (InvalidMoveException t) {
                    System.out.println("Invalid Move! Please try again!");
                    turn--;
                }
            }
            turn++;

            //System.out.println("Exit and save game? 1 for yes, 0 for no");
            //input = userIn.nextLine().trim();

            //if(Integer.parseInt(input) == 1) {
                //continueCheck = false; 
                //saveGame.saveObject(game, "./assets/mancala.ser");
            //}

        }while(!game.isGameOver() && continueCheck);
        System.out.println(game);
        try {
            System.out.println("The winner is: " + game.getWinner().getName());
        } catch (GameNotOverException e) {
            System.out.println("Error getting winner");
        }
        */
    }
}