package ui;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import mancala.MancalaGame;
import mancala.Player;
import mancala.KalahRules;
import mancala.AyoRules;
import mancala.PitNotFoundException;
import mancala.InvalidMoveException;
import mancala.GameNotOverException;
import mancala.Saver;
import java.io.IOException;  

import java.awt.Color; 







public class SwingUI extends JFrame {

    private int tall = 2;
    private int wide = 6;
     private JButton submitButton;
     private JTextField usernameField;
     private JLabel statusLabel;
     private JFileChooser fileChooser = new JFileChooser();

     private JPanel gameContainer;
     private JLabel messageLabel;

    private JLabel leftMessage = new JLabel();
    private JMenuBar menuBar;
    private PositionAwareButton[][] buttons;
    private JTextField player1Store = new JTextField();
    private JTextField player2Store = new JTextField();
    private MancalaGame game = new MancalaGame();
    private Saver save = new Saver();



    private int gameType = -1; // 0 for Kalah, 1 for Ayo
    private Player player1 = new Player("Player1");
    private Player player2 = new Player("Player2");



     public SwingUI(String title) {
        super();
        fileChooser.setCurrentDirectory(new File("."));
        basicSetUp(title);
        setupGameContainer();
        add(gameContainer, BorderLayout.CENTER);
        add(makeButtonPanel(), BorderLayout.EAST);
        player1Store.setEditable(false);
        player2Store.setEditable(false);
        updateStoreView();
        gameContainer.add(player2Store);
        gameContainer.add(makeMancalaGrid());
        gameContainer.add(player1Store);


        makeMenu();
        setJMenuBar(menuBar);
        pack();
     }
     private void basicSetUp(String title){
         this.setTitle(title);
         gameContainer = new JPanel();
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLayout(new BorderLayout());
}

private JPanel startupMessage() {
     JPanel temp = new JPanel();
     leftMessage.setText("Time to play Mancala Games!");
     temp.add(leftMessage);
     return temp;
}
 public void setupGameContainer(){
       gameContainer.add(startupMessage());
}

private JPanel makeButtonPanel() {
     JPanel buttonPanel = new JPanel();
     buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
     buttonPanel.add(makeKalahButton());
     buttonPanel.add(makeAyoButton());
     buttonPanel.add(makeNewGameButton());
     return buttonPanel;
}

/*
Side button Panel
*/

private JButton makeNewGameButton() {
    JButton button = new JButton("New Game");
    button.addActionListener(e -> newGame());
    return button;
}
private JButton makeKalahButton() {
     JButton button = new JButton("Kalah Game"); // Customize the button label
     button.addActionListener(e -> setKalahRule());
     return button;
}

private JButton makeAyoButton() {
     JButton button = new JButton("Ayo Game"); // Customize the button label
     button.addActionListener(e -> setAyoRule());
     return button;
}

protected void setKalahRule() {
    game.setBoard(new KalahRules());
    gameType = 0;
    String message = "Kalah Rules Selected!";
    leftMessage.setText("Kalah Rules Game:");
    newGame();
    JOptionPane.showMessageDialog(null,message);
}
protected void setAyoRule() {
    game.setBoard(new AyoRules());
    gameType = 1;
    String message = "Ayo Rules Selected!";
    leftMessage.setText("Ayo Rules Game:");
    newGame();
    JOptionPane.showMessageDialog(null,message);
}


protected void newGame() {
    if(game.getBoard() == null) {
        noGameTypeError();
    } else if(player1.getName() == "Player1" || player2.getName() == "Player2") {
        noPlayerError();
    }
    else {
        if (gameType == 0) {
            player1.addKalahGame();
            player2.addKalahGame();
        } else {
            player1.addAyoGame();
            player2.addAyoGame();
        }
        game.setPlayers(player1,player2);
        game.startNewGame();
        game.setCurrentPlayer(player1);
        updateView(); // Update the view to reflect the new game state
    }
}

protected void noGameTypeError(){
         String message = "Please select a game type before starting!";
         JOptionPane.showMessageDialog(null,message);
}
protected void noPlayerError(){
         String message = "Please initialize players in Player Submenu";
         JOptionPane.showMessageDialog(null,message);
}











/*
Grid
*/
protected void updateView() {
     for (int y = 0; y < tall; y++) {
        for (int x = 0; x < wide; x++) {
            try{
                if(y == 0) {
                    buttons[y][x].setText(Integer.toString(game.getNumStones(12 - x)));
                } else {
                    buttons[y][x].setText(Integer.toString(game.getNumStones(x+1)));
                }
            } catch (PitNotFoundException e) {
                buttons[y][x].setText("0");
            }
        } 
    }
    if(game.getCurrentPlayer() == player1) {
        player1Store.setForeground(Color.BLUE);
        player2Store.setForeground(Color.BLACK);
    } else {
        player2Store.setForeground(Color.BLUE);
        player1Store.setForeground(Color.BLACK);
    }
    updateStoreView();
}

protected void updateStoreView() {
    player1Store.setText(player1.getName() + "'s Store: " + player1.getStoreCount());
    player2Store.setText(player2.getName() + "'s Store: " + player2.getStoreCount());
}




private JPanel makeMancalaGrid() {
        JPanel panel = new JPanel();

        buttons = new PositionAwareButton[tall][wide];
        panel.setLayout(new GridLayout(tall, wide));
         for (int y = 0; y < tall; y++) {
            for (int x = 0; x < wide; x++) {
                buttons[y][x] = new PositionAwareButton();
                if(y == 0) {
                     buttons[y][x].setAcross(12 - x);
                } else {
                    buttons[y][x].setAcross(x+1);
                }
                buttons[y][x].addActionListener(e -> {
                 move(e);
                 checkGameOver(); 
             });

                panel.add(buttons[y][x]);
            }
         }
         return panel;
}

private void move(ActionEvent e) {
    PositionAwareButton clicked = (PositionAwareButton) (e.getSource());
    if(player1.getName() != "Player1" && player2.getName() != "Player2" && gameType >=0) {
        try{
            game.move(clicked.getAcross());
            updateView();
        } catch(InvalidMoveException t) {
            JOptionPane.showMessageDialog(null,"Incorrect box!, " + game.getCurrentPlayer().getName() + "'s turn!");
        }
    }
}
private void checkGameOver() {
    JOptionPane gameOver = new JOptionPane();
    int choice;
    if(game.isGameOver()) {
        StringBuilder returnString = new StringBuilder();
        try {
         if(gameType == 0) {
                game.getWinner().addKalahWin();
         } else {
              game.getWinner().addAyoWin();
            }
            returnString.append("Winner: ");
            returnString.append(game.getWinner().getName());
            returnString.append("! Would you like to start a new game?");
        } catch (GameNotOverException t) {
        }
        choice = gameOver.showConfirmDialog(null, returnString.toString(),"Warning", JOptionPane.YES_NO_OPTION);

        if(choice == JOptionPane.NO_OPTION) {
             for (int y = 0; y < tall; y++) {
                for (int x = 0; x < wide; x++) {
                    buttons[y][x].setText("0");
                }
            } 
        } else {
            newGame();
        }
    }
}



 private void makeMenu() {
    menuBar = new JMenuBar();
    JMenu playerMenu = new JMenu("Player"); // Customize the menu label
    JMenuItem item = new JMenuItem("Player Stats"); // Customize the menu item
    item.addActionListener(e -> playerStats());
    JMenuItem item1 = new JMenuItem("Load Player 1"); // Customize the menu item
    item1.addActionListener(e -> loadPlayer1());
    JMenuItem item2 = new JMenuItem("Load Player 2"); // Customize the menu item
    item2.addActionListener(e -> loadPlayer2());


    JMenuItem item3 = new JMenuItem("Initialize Player 1"); // Customize the menu item
    item3.addActionListener(e -> initPlayer1());
    JMenuItem item4 = new JMenuItem("Initialize Player 2");
    item4.addActionListener(e -> initPlayer2());


    playerMenu.add(item);
    playerMenu.add(item1);
    playerMenu.add(item2);
    playerMenu.add(item3);
    playerMenu.add(item4);
    menuBar.add(playerMenu);

    JMenu saveMenu = new JMenu("Save");
    JMenuItem item5 = new JMenuItem("Save Player 1");
    item5.addActionListener(e -> savePlayer1());
    JMenuItem item6 = new JMenuItem("Save Player 2");
    item6.addActionListener(e -> savePlayer2());
    JMenuItem item7 = new JMenuItem("Save Game");
    item7.addActionListener(e -> saveGame());
    saveMenu.add(item5);
    saveMenu.add(item6);
    saveMenu.add(item7);
    menuBar.add(saveMenu);

    JMenu loadGame = new JMenu("Game");
    JMenuItem item8 = new JMenuItem("Load Game");
    item8.addActionListener(e -> loadGame());
    loadGame.add(item8);
    menuBar.add(loadGame);
}

private void initPlayer1() {
    String name = JOptionPane.showInputDialog("Enter a name for Player1: ");
    player1 = new Player(name);
    updateStoreView();
}
private void initPlayer2() {
    String name = JOptionPane.showInputDialog("Enter a name for Player2: ");
    player2 = new Player(name);
    updateStoreView();
}

private void loadPlayer1() {
    int returnVal = fileChooser.showOpenDialog(SwingUI.this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        String filename = fileChooser.getSelectedFile().getName();
        try {
            player1 = (Player) save.loadObject(filename);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Failure to open File!");
        }
    } else {
        JOptionPane.showMessageDialog(null,"Failure to open File!");
    }
    updateStoreView();
}

private void loadPlayer2() {
    int returnVal = fileChooser.showOpenDialog(SwingUI.this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        String filename = fileChooser.getSelectedFile().getName();
        try {
            player2 = (Player) save.loadObject(filename);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Failure to open File!");
        }
    } else {
        JOptionPane.showMessageDialog(null,"Failure to open File!");
    }
    updateStoreView();
}




private void savePlayer1() {
    int returnVal = fileChooser.showSaveDialog(SwingUI.this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        String filename = fileChooser.getSelectedFile().getName();
        try {
            save.saveObject(player1,filename);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Failure to save File!");
        }
    } else {
        JOptionPane.showMessageDialog(null,"Failure to save File!");
    }

}

private void savePlayer2() {
    int returnVal = fileChooser.showSaveDialog(SwingUI.this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        String filename = fileChooser.getSelectedFile().getName();
        try {
            save.saveObject(player2,filename);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Failure to save File!");
        }
    } else {
        JOptionPane.showMessageDialog(null,"Failure to save File!");
    }

}


private void saveGame() {
    int returnVal = fileChooser.showSaveDialog(SwingUI.this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        String filename = fileChooser.getSelectedFile().getName();
        try {
            save.saveObject(game,filename);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Failure to save File!");
        }
    } else {
        JOptionPane.showMessageDialog(null,"Failure to save File!");
    }


    
    JOptionPane exit = new JOptionPane();
    if(exit.showConfirmDialog(null, "Exit as well?","Warning",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        System.exit(0);
    } 
}

private void loadGame() {
    int returnVal = fileChooser.showOpenDialog(SwingUI.this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        String filename = fileChooser.getSelectedFile().getName();
        try {
            game = (MancalaGame) save.loadObject(filename);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Failure to open File!");
        }
    } else {
        JOptionPane.showMessageDialog(null,"Failure to open File!");
    }

    player1 = game.getPlayer1();
    player2 = game.getPlayer2();
    updateView();
}

protected void playerStats() {
    StringBuilder returnString = new StringBuilder();
    returnString.append("Player1: " + player1.getName() + "\n");
    returnString.append("Kalah Games: " + player1.getKalahGames() + "\n");
    returnString.append("Kalah Wins: " + player1.getKalahWins() + "\n");
    returnString.append("Ayo Games: " + player1.getAyoGames() + "\n");
    returnString.append("Ayo Wins: " + player1.getAyoWins() + "\n\n");

    returnString.append("Player2: " + player2.getName() + "\n");
    returnString.append("Kalah Games: " + player2.getKalahGames() + "\n");
    returnString.append("Kalah Wins: " + player2.getKalahWins() + "\n");
    returnString.append("Ayo Games: " + player2.getAyoGames() + "\n");
    returnString.append("Ayo Wins: " + player2.getAyoWins());
    JOptionPane.showMessageDialog(null,returnString.toString());
}

}