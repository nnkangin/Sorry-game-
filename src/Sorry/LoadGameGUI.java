/**
 * @author Max Marder
 */

// Imports
package Sorry;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import static Sorry.Main.mainFrame;


public class LoadGameGUI extends JPanel {

    // Variable Declaration

    // declare sub-panels for each color
    private JPanel savedGamesPanel, redTeam, greenTeam, yellowTeam, blueTeam;
    // declare stats panels
    private JPanel redStats;
    private JPanel greenStats;
    private JPanel yellowStats;
    private JPanel blueStats;
    // declare details panels
    private JPanel redDetails = new JPanel(new GridLayout(3, 3));
    private JPanel greenDetails = new JPanel(new GridLayout(3, 3));
    private JPanel yellowDetails = new JPanel(new GridLayout(3, 3));
    private JPanel blueDetails = new JPanel(new GridLayout(3, 3));
    // declare combo panel
    private JPanel gameComboPanel = new JPanel();
    // declare combo boxes
    private JComboBox<String> gameComboBox;
    // declare Scroll Panes for each color
    private JScrollPane gameScroll;
    // declare select button
    private Button gameSelect;
    private boolean select = false;
    // declare start and back buttons
    private Button startButton, backButton;
    // declare player statistics
    private ArrayList<Game> savedGamesList;
    private ArrayList<Player> redPlayers, greenPlayers, yellowPlayers, bluePlayers;
    private ArrayList<String> redNamesList = new ArrayList<String>();
    private ArrayList<String> greenNamesList = new ArrayList<String>();
    private ArrayList<String> yellowNamesList = new ArrayList<String>();
    private ArrayList<String> blueNamesList = new ArrayList<String>();
    private ArrayList<String> gameNamesList = new ArrayList<String>();
    private String[] redNamesArray = {}, greenNamesArray = {}, yellowNamesArray = {}, blueNamesArray = {};
    private String[] gameNamesArray = {};
    // declare players
    private Player redPlayer, greenPlayer, yellowPlayer, blueplayer, tempPlayer;
    // declare player names
    private String redName, greenName, yellowName, blueName;

    private Game selectedGame;

    // Constructor
    public LoadGameGUI() {
        /** Create layout */
        this.setLayout(new GridLayout(4, 3));
        this.setBorder(BorderFactory.createTitledBorder(null, "LOAD GAME", TitledBorder.TOP, TitledBorder.CENTER, new Font("Lucida Calligraphy", Font.PLAIN, 20), Color.ORANGE));

        // Configure Saved Game Panel
        // Saved Games Panel
        savedGamesPanel = new JPanel();
        savedGamesPanel.setBorder(BorderFactory.createTitledBorder(null, "SELECT GAME", TitledBorder.TOP, TitledBorder.CENTER, new Font("arial", Font.BOLD, 18), Color.BLACK));
        savedGamesPanel.setLayout(new FlowLayout());

        // TODO create arraylist of saved games and convert to array here
        //         TODO Make this work
        // Fetch details of saved games
        savedGamesList = fetchGames();
        Iterator<Game> savedGameIterator = savedGamesList.iterator();
        while (savedGameIterator.hasNext())
            gameNamesList.add(Long.toString(savedGameIterator.next().getID()));
        gameNamesArray = gameNamesList.toArray(gameNamesArray);

        gameComboBox = new JComboBox<String>(gameNamesArray);
        gameScroll = new JScrollPane(gameComboBox);
        // configure select button
        gameSelect = new Button("Select");
        gameComboPanel.setLayout(new FlowLayout());
        // add components to saved games panel
        savedGamesPanel.add(gameScroll);
        savedGamesPanel.add(gameSelect);

        // TODO uncomment action listeners
        // define select buttons
        gameSelect.addActionListener(new ButtonListener());
        gameSelect.setActionCommand("Select");
        // add combo panels to saved game panel
        savedGamesPanel.add(gameComboPanel, BorderLayout.NORTH);

        this.add(savedGamesPanel, BorderLayout.NORTH);
        // add buffer panel
        this.add(new JPanel(), BorderLayout.NORTH);

        // Configure Team Panels
        // Red Team Panel
        redTeam = new JPanel();
        redTeam.setBorder(BorderFactory.createTitledBorder(null, "RED TEAM", TitledBorder.TOP, TitledBorder.CENTER, new Font("arial", Font.BOLD, 18), Color.RED));
        redTeam.setLayout(new BorderLayout());
        // Green Team Panel
        greenTeam = new JPanel();
        greenTeam.setBorder(BorderFactory.createTitledBorder(null, "GREEN TEAM", TitledBorder.TOP, TitledBorder.CENTER, new Font("arial", Font.BOLD, 18), Color.GREEN));
        greenTeam.setLayout(new BorderLayout());
        // Yellow Team Panel
        yellowTeam = new JPanel();
        yellowTeam.setBorder(BorderFactory.createTitledBorder(null, "YELLOW TEAM", TitledBorder.TOP, TitledBorder.CENTER, new Font("arial", Font.BOLD, 18), Color.YELLOW));
        yellowTeam.setLayout(new BorderLayout());
        // Blue Team Panel
        blueTeam = new JPanel();
        blueTeam.setBorder(BorderFactory.createTitledBorder(null, "BLUE TEAM", TitledBorder.TOP, TitledBorder.CENTER, new Font("arial", Font.BOLD, 18), Color.BLUE));
        blueTeam.setLayout(new BorderLayout());
        // Define stats panels
        redStats = new JPanel(new GridLayout(3, 3));
        greenStats = new JPanel(new GridLayout(3, 3));
        yellowStats = new JPanel(new GridLayout(3, 3));
        blueStats = new JPanel(new GridLayout(3, 3));
        // Define name lists
        redNamesList = new ArrayList<String>();
        greenNamesList = new ArrayList<String>();
        yellowNamesList = new ArrayList<String>();
        blueNamesList = new ArrayList<String>();

        // add labels to stats panels
        redStats.add(new JLabel("Name   :"));
        redStats.add(new JLabel("Played :"));
        redStats.add(new JLabel("Won    :"));
        greenStats.add(new JLabel("Name   :"));
        greenStats.add(new JLabel("Played :"));
        greenStats.add(new JLabel("Won    :"));
        yellowStats.add(new JLabel("Name   :"));
        yellowStats.add(new JLabel("Played :"));
        yellowStats.add(new JLabel("Won    :"));
        blueStats.add(new JLabel("Name   :"));
        blueStats.add(new JLabel("Played :"));
        blueStats.add(new JLabel("Won    :"));

        // add stats panels to team panels
        redTeam.add(redStats, BorderLayout.WEST);
        greenTeam.add(greenStats, BorderLayout.WEST);
        yellowTeam.add(yellowStats, BorderLayout.WEST);
        blueTeam.add(blueStats, BorderLayout.WEST);

        // add team panels to GUI
        this.add(redTeam);
        this.add(greenTeam);
        this.add(yellowTeam);
        this.add(blueTeam);

        // Define back and start buttons
        backButton = new Button("BACK");
        backButton.addActionListener(new ButtonListener());
        backButton.setActionCommand("backButtonClicked");

        startButton = new Button("START");
        startButton.addActionListener(new ButtonListener());
        startButton.setActionCommand("startGameClicked");

        // TODO add buttons to start and return to previous menu
        this.add(backButton, BorderLayout.SOUTH);
        this.add(startButton, BorderLayout.SOUTH);
    }
    //Gets array of saved games from dat file
    public ArrayList<Game> fetchGames() {
        Game tempGame;
        ArrayList<Game> gamesList = new ArrayList<Game>();
        try {
            FileInputStream file = new FileInputStream("saveGame.dat");
            ObjectInputStream inObject = new ObjectInputStream(file);
            try {
//                while (inObject.) {
                    tempGame = (Game) inObject.readObject();
                    gamesList.add(tempGame);
                    inObject.close();
                    file.close();

//                }

            } catch (EOFException e) {
                inObject.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            gamesList.clear();
            return gamesList;
        }
        return gamesList;

    }
    public void setSelectedGame(Game selectedGame) {
        this.selectedGame = selectedGame;
    }

    public Game getSelectedGame() {
        return selectedGame;
    }
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            /** Back button event handler */
            if (action.equals("Select")) {
                select = true;
                String gameId = (String) gameComboBox.getSelectedItem();
                int gameIndex = Integer.parseInt(gameId);
                gameIndex--;
                ArrayList<Game> gamesList = fetchGames();
                Game selectedGame = gamesList.get(gameIndex);
                setSelectedGame(selectedGame);
                System.out.println(selectedGame.getRedPlayer().getName());
                updatePlayerDetails(redDetails,redTeam,selectedGame.getRedPlayer());
                updatePlayerDetails(greenDetails,greenTeam,selectedGame.getGreenPlayer());
                updatePlayerDetails(yellowDetails,yellowTeam,selectedGame.getYellowPlayer());
                updatePlayerDetails(blueDetails,blueTeam,selectedGame.getBluePlayer());

            }
            if (action.equals("backButtonClicked")) {
                mainFrame.setScreenState(MainFrame.state.mainMenu);
            }
            // TODO set main frame sorry game to loaded sorry game
            /** Start game event handler */
            if (action.equals("startGameClicked")&& select ==true) {
                mainFrame.setScreenState(MainFrame.state.mainGame);
                    // create new game object with loaded players
                    mainFrame.setSorryGame(getSelectedGame());
                    // update player details in sorry board
                    mainFrame.getSorryGame().getSorryBoard().updatePlayers();
                    // update player details in game's control panel
                    mainFrame.getSorryGameGUI().getControlPanelGui().updatePlayerDetails();
                     // update current turn in control panel
                    mainFrame.getSorryGameGUI().getControlPanelGui().updateInfoPanel();
                    // switch to main game pane
                    mainFrame.setScreenState(MainFrame.state.mainGame);

            }
        }private void updatePlayerDetails(JPanel inDetails, JPanel inTeam, Player inPlayer){
            inDetails.removeAll();
            inDetails.add(new JLabel(" " + inPlayer.getName()));
            inDetails.add(new JLabel(" " + inPlayer.getGamesPlayed()));
            inDetails.add(new JLabel(" " + inPlayer.getGamesWon()));
            inTeam.revalidate();
            inTeam.repaint();
            inTeam.add(inDetails);
        }
    }
}
