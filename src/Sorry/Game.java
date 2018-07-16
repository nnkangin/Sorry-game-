/**
 /**
 * @author Max Marder
 */

// Imports
package Sorry;

import java.awt.*;
import java.util.ArrayList;
import java.io.Serializable;

/** The Game class contains a list players, the board, and all game parameters */
public class Game implements Serializable{

    // Variable Declaration
    private static final long serialVersionUID = 1L;
    private static Board sorryBoard;
    private static Deck sorryDeck;
    private static Player redPlayer, greenPlayer, yellowPlayer, bluePlayer;
    private static ArrayList<Player> playerList = new ArrayList<>();

    // Constructor
    public Game(Player inRedPlayer, Player inGreenPlayer, Player inYellowPlayer, Player inBluePlayer){
        setRedPlayer(inRedPlayer);
        playerList.add(inRedPlayer);
        setGreenPlayer(inGreenPlayer);
        playerList.add(inGreenPlayer);
        setYellowPlayer(inYellowPlayer);
        playerList.add(inYellowPlayer);
        setBluePlayer(inBluePlayer);
        playerList.add(inBluePlayer);

        sorryDeck = new Deck();
        setSorryBoard(new Board(sorryDeck));
        /*redPlayer = new Player(" ");
        redPlayer.setColor(Color.red);
        greenPlayer = new Player(" ");
        greenPlayer.setColor(Color.green);
        yellowPlayer = new Player(" ");
        yellowPlayer.setColor(Color.yellow);
        bluePlayer = new Player(" ");
        bluePlayer.setColor(Color.blue);*/
    }

    // Getters
    public static Board getSorryBoard() {
        return sorryBoard;
    }
    public static Deck getSorryDeck() {
        return sorryDeck;
    }
    public static ArrayList<Player> getPlayerList() {
        return playerList;
    }
    public static Player getRedPlayer() {
        return redPlayer;
    }
    public static Player getGreenPlayer() {
        return greenPlayer;
    }
    public static Player getYellowPlayer() {
        return yellowPlayer;
    }
    public static Player getBluePlayer() { return bluePlayer; }
    public static long getID(){ return 1L;}

    // Setters
    public static void setSorryDeck(Deck sorryDeck) {
        Game.sorryDeck = sorryDeck;
    }
    public  void setSorryBoard(Board sorryBoard) {
        Game.sorryBoard = sorryBoard;
    }
    public static void setPlayerList(ArrayList<Player> playerList) {
        Game.playerList = playerList;
    }
    public static void setRedPlayer(Player redPlayer) {
        Game.redPlayer = redPlayer;
    }
    public static void setGreenPlayer(Player greenPlayer) {
        Game.greenPlayer = greenPlayer;
    }
    public static void setYellowPlayer(Player yellowPlayer) {
        Game.yellowPlayer = yellowPlayer;
    }
    public static void setBluePlayer(Player bluePlayer) {
        Game.bluePlayer = bluePlayer;
    }


}
