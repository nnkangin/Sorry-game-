/**
 * @author Max Marder
 */

// Imports
package Sorry;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Player implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private int gamesPlayed;
    private int gamesWon;
    private boolean turn;
    private Color color;
    private int turncount;

    // Constructor
    public Player(String name) {
        this.name = name.trim();
        gamesPlayed = 0;
        gamesWon = 0;
        turn = false;
        setColor(null);
        turncount=0;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }
    // Name Getter
    public String getName() {
        return name;
    }

    // Returns the number of games played
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    // Returns the number of games won
    public int getGamesWon() {
        return gamesWon;
    }

    // Calculates the win percentage of the player
    public int winPercent() {
        return (gamesWon * 100) / gamesPlayed;
    }

    // Increments the number of games played
    public void updateGamesPlayed() {
        gamesPlayed++;
    }

    // Increments the number of games won
    public void updateGamesWon() {
        gamesWon++;
    }

    //Function to fetch the list of the players from file
    public static ArrayList<Player> fetchPlayers() {
        Player tempPlayer;
        ObjectInputStream input = null;
        ArrayList<Player> players = new ArrayList<Player>();
        try {
            File inFile = new File(System.getProperty("user.dir") + File.separator + "sorryGame.dat");
            input = new ObjectInputStream(new FileInputStream(inFile));
            try {
                while (true) {
                    tempPlayer = (Player) input.readObject();
                    players.add(tempPlayer);
                }
            } catch (EOFException e) {
                input.close();
            }
        } catch (FileNotFoundException e) {
            players.clear();
            return players;
        } catch (IOException e) {
            e.printStackTrace();
            try {
                input.close();
            } catch (IOException e1) {
            }
            JOptionPane.showMessageDialog(null, "Unable to read the required Game files!!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Game Data File Corrupted!! Click Ok to Continue Building New File");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return players;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getTurncount() {
        return turncount;
    }

    public void setTurncount(int turncount) {
        this.turncount = turncount;
    }

    // Function to update the statistics of a player
//    public void Update_Player()
//    {
//        ObjectInputStream input = null;
//        ObjectOutputStream output = null;
//        Player tempPlayer;
//        File inFile = null;
//        File outFile = null;
//        try {
//            inFile = new File(System.getProperty("user.dir") + File.separator + "sorryGame.dat");
//            outFile = new File(System.getProperty("user.dir") + File.separator + "tempFile.dat");
//        } catch (SecurityException e) {
//            JOptionPane.showMessageDialog(null, "Read-Write Permission Denied !! Program Cannot Start");
//            System.exit(0);
//        }
//        boolean playerNonexistent;
//        try {
//            if (outFile.exists() == false)
//                outFile.createNewFile();
//            if (inFile.exists() == false) {
//                output = new ObjectOutputStream(new java.io.FileOutputStream(outFile, true));
//                output.writeObject(this);
//            } else {
//                input = new ObjectInputStream(new FileInputStream(inFile));
//                output = new ObjectOutputStream(new FileOutputStream(outFile));
//                playerNonexistent = true;
//                try {
//                    while (true) {
//                        tempPlayer = (Player) input.readObject();
//                        if (tempPlayer.getName().equals(this.getName())) {
//                            output.writeObject(this);
//                            playerNonexistent = false;
//                        } else
//                            output.writeObject(tempPlayer);
//                    }
//                } catch (EOFException e) {
//                    input.close();
//                }
//                if (playerNonexistent)
//                    output.writeObject(this);
//            }
//            inFile.delete();
//            output.close();
//            File newf = new File(System.getProperty("user.dir") + File.separator + "sorryGame.dat");
//            if (outFile.renameTo(newf) == false)
//                System.out.println("File Renaming Unsuccessful");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Unable to read/write the required Game files !! Press ok to continue");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Game Data File Corrupted !! Click Ok to Continue Building New File");
//        } catch (Exception e) {
//
//        }
//    }
}
