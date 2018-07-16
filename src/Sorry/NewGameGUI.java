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
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Iterator;

import static Sorry.Main.main;
import static Sorry.Main.mainFrame;


public class NewGameGUI extends JPanel {

    // Variable Declaration

    // declare sub-panels for each color
    private JPanel redTeam, greenTeam, yellowTeam, blueTeam;
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
    // declare combo panels
    private JPanel redComboPanel = new JPanel();
    private JPanel greenComboPanel = new JPanel();
    private JPanel yellowComboPanel = new JPanel();
    private JPanel blueComboPanel = new JPanel();
    // declare combo boxes
    private JComboBox<String> redComboBox, greenComboBox, yellowComboBox, blueComboBox;
    // declare Scroll Panes for each color
    private JScrollPane redScroll, greenScroll, yellowScroll, blueScroll;
    // declare select buttons
    private Button redSelect, greenSelect, yellowSelect, blueSelect;
    // declare new player buttons
    private Button redNewPlayer, greenNewPlayer, yellowNewPlayer, blueNewPlayer;
    // declare start and back buttons
    private Button startButton, backButton;
    // declare player statistics
    private ArrayList<Player> redPlayers, greenPlayers, yellowPlayers, bluePlayers;
    private ArrayList<String> redNamesList = new ArrayList<String>();
    private ArrayList<String> greenNamesList = new ArrayList<String>();
    private ArrayList<String> yellowNamesList = new ArrayList<String>();
    private ArrayList<String> blueNamesList = new ArrayList<String>();
    private String[] redNamesArray = {}, greenNamesArray = {}, yellowNamesArray = {}, blueNamesArray = {};
    // declare players
    private Player redPlayer, greenPlayer, yellowPlayer, blueplayer, tempPlayer;
    // declare player names
    private String redName, greenName, yellowName, blueName;

    private boolean selected = false;
    private boolean redSelected =false;
    private boolean greenSelected =false;
    private boolean yellowSelected = false;
    private boolean blueSelected = false;
    private  boolean newRed = false;
    private  boolean newGreen = false;
    private  boolean newYellow = false;
    private  boolean newBlue = false;
    // Constructor
    public NewGameGUI() {
        /** Create layout */
        this.setLayout(new GridLayout(3, 3));
        this.setBorder(BorderFactory.createTitledBorder(null, "SELECT YOUR TEAM", TitledBorder.TOP, TitledBorder.CENTER, new Font("Lucida Calligraphy", Font.PLAIN, 20), Color.ORANGE));
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

//         TODO Make this work
        // Fetch player lists
        redPlayers = fetchPlayers();
        greenPlayers = fetchPlayers();
        yellowPlayers = fetchPlayers();
        bluePlayers = fetchPlayers();

        // fetch player name lists
        redNamesList = fetchPlayerNames();
        redNamesArray = redNamesList.toArray(redNamesArray);
        greenNamesList = fetchPlayerNames();
        greenNamesArray = greenNamesList.toArray(greenNamesArray);
        yellowNamesList = fetchPlayerNames();
        yellowNamesArray = yellowNamesList.toArray(yellowNamesArray);
        blueNamesList = fetchPlayerNames();
        blueNamesArray = blueNamesList.toArray(blueNamesArray);

        // Combo boxes for each team
        redComboBox = new JComboBox<String>(redNamesArray);
        greenComboBox = new JComboBox<String>(greenNamesArray);
        yellowComboBox = new JComboBox<String>(yellowNamesArray);
        blueComboBox = new JComboBox<String>(blueNamesArray);
        // Scroll Panes for each class
        redScroll = new JScrollPane(redComboBox);
        greenScroll = new JScrollPane(greenComboBox);
        yellowScroll = new JScrollPane(yellowComboBox);
        blueScroll = new JScrollPane(blueComboBox);
        // Set flow layout to combo panels
        redComboPanel.setLayout(new FlowLayout());
        greenComboPanel.setLayout(new FlowLayout());
        yellowComboPanel.setLayout(new FlowLayout());
        blueComboPanel.setLayout(new FlowLayout());

        // TODO uncomment action listeners
        // define select buttons
        redSelect = new Button("Select");
        redSelect.addActionListener(new SelectHandler(redComboBox, redDetails, redTeam));
        redSelect.setActionCommand("redSelect");
        greenSelect = new Button("Select");
        greenSelect.addActionListener(new SelectHandler(greenComboBox, greenDetails, greenTeam));
        greenSelect.setActionCommand("greenSelect");
        yellowSelect = new Button("Select");
        yellowSelect.addActionListener(new SelectHandler(yellowComboBox, yellowDetails, yellowTeam));
        yellowSelect.setActionCommand("yellowSelect");
        blueSelect = new Button("Select");
        blueSelect.addActionListener(new SelectHandler(blueComboBox, blueDetails, blueTeam));
        blueSelect.setActionCommand("blueSelect");

        // define new buttons
        redNewPlayer = new Button("New Player");
        redNewPlayer.addActionListener(new NewHandler(redDetails, redTeam));
        redNewPlayer.setActionCommand("newRedPlayer");
        greenNewPlayer = new Button("New Player");
        greenNewPlayer.addActionListener(new NewHandler(greenDetails, greenTeam));
        greenNewPlayer.setActionCommand("newGreenPlayer");
        yellowNewPlayer = new Button("New Player");
        yellowNewPlayer.addActionListener(new NewHandler(yellowDetails, yellowTeam));
        yellowNewPlayer.setActionCommand("newYellowPlayer");
        blueNewPlayer = new Button("New Player");
        blueNewPlayer.addActionListener(new NewHandler(blueDetails, blueTeam));
        blueNewPlayer.setActionCommand("newBluePlayer");

        // add components to each combo panel
        redComboPanel.add(redScroll);
        redComboPanel.add(redSelect);
        redComboPanel.add(redNewPlayer);
        greenComboPanel.add(greenScroll);
        greenComboPanel.add(greenSelect);
        greenComboPanel.add(greenNewPlayer);
        yellowComboPanel.add(yellowScroll);
        yellowComboPanel.add(yellowSelect);
        yellowComboPanel.add(yellowNewPlayer);
        blueComboPanel.add(blueScroll);
        blueComboPanel.add(blueSelect);
        blueComboPanel.add(blueNewPlayer);

        // add combo panels to team panels
        redTeam.add(redComboPanel, BorderLayout.NORTH);
        greenTeam.add(greenComboPanel, BorderLayout.NORTH);
        yellowTeam.add(yellowComboPanel, BorderLayout.NORTH);
        blueTeam.add(blueComboPanel, BorderLayout.NORTH);

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

    // retrieve array list of players from databse
    public ArrayList<Player> fetchPlayers() {
        DBconnect dBConnect = new DBconnect();
        Player tempPlayer = new Player("");
        ArrayList<Player> players = new ArrayList<Player>();
        ArrayList<String> nameList = new ArrayList<String>();
        nameList = dBConnect.getNameData();
        for (int i = 0; i < dBConnect.getNumPlayers(); i++) {
            tempPlayer.setName(nameList.get(i));
            tempPlayer.setGamesPlayed(dBConnect.getGamesPlayedValue(nameList.get(i)));
            tempPlayer.setGamesWon(dBConnect.getGamesWonValue(nameList.get(i)));
            players.add(tempPlayer);
            // TEST
//            System.out.printf("name %s, games played: %d, games won %d", nameList.get(i), dBConnect.getGamesPlayedValue(nameList.get(i)), dBConnect.getGamesWonValue(nameList.get(i)));
//            System.out.println("\n");
        }
//        // TEST namelist
//        System.out.println(nameList);
        return players;
    }

    // retrieve array list of players from databse
    public ArrayList<String> fetchPlayerNames() {
        DBconnect dBConnect = new DBconnect();
        ArrayList<String> nameList = new ArrayList<String>();
        nameList = dBConnect.getNameData();
        return nameList;
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            /** Back button event handler */
            if (action.equals("backButtonClicked")) {
                mainFrame.setScreenState(MainFrame.state.mainMenu);
            }
            /** Start game event handler */
            if ((redSelected == true || newRed ==true)  && (greenSelected ==true || newGreen ==true)
                && (yellowSelected == true || newYellow ==true) && (blueSelected ==true || newBlue ==true) && action.equals("startGameClicked")) {
                // define players for each team using details form team panel

                // create new game object with loaded players
                mainFrame.setSorryGame(new Game(redPlayer, greenPlayer, yellowPlayer, blueplayer));
                // update player details in sorry board
                //mainFrame.getSorryGame().getSorryBoard().updatePlayers();
                // update player details in game's control panel
                mainFrame.getSorryGameGUI().getControlPanelGui().updatePlayerDetails();
                // update current turn in control panel
                mainFrame.getSorryGameGUI().getControlPanelGui().updateInfoPanel();
                // switch to main game pane
                mainFrame.setScreenState(MainFrame.state.mainGame);
            }
        }
    }

    class SelectHandler implements ActionListener {
        private JComboBox<String> comboBox;
        private JPanel detailsPanel;
        private JPanel teamPanel;

        SelectHandler(JComboBox<String> inComboBox, JPanel inDetailsPanel, JPanel inTeamPanel) {
            comboBox = inComboBox;
            detailsPanel = inDetailsPanel;
            teamPanel = inTeamPanel;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {
            DBconnect dBConnect = new DBconnect();
            String playerName = (String) comboBox.getSelectedItem();
            String action = arg0.getActionCommand();
            detailsPanel.removeAll();
            detailsPanel.add(new JLabel(" " + playerName));
            detailsPanel.add(new JLabel(" " + dBConnect.getGamesPlayedValue(playerName)));
            detailsPanel.add(new JLabel(" " + dBConnect.getGamesWonValue(playerName)));
            teamPanel.revalidate();
            teamPanel.repaint();
            teamPanel.add(detailsPanel);

            if (action == "redSelect") {
                redSelected = true;
                redPlayer = new Player(playerName);
                redPlayer.setGamesPlayed(dBConnect.getGamesPlayedValue(playerName));
                redPlayer.setColor(Color.RED);
            }else if(action == "greenSelect"){
                greenPlayer = new Player(playerName);
                greenPlayer.setGamesPlayed(dBConnect.getGamesPlayedValue(playerName));
                greenSelected =true;
            }else if (action == "yellowSelect"){
                yellowPlayer = new Player(playerName);
                yellowPlayer.setGamesPlayed(dBConnect.getGamesPlayedValue(playerName));
                yellowSelected = true;
            }else{
                blueplayer = new Player(playerName);
                blueplayer.setGamesPlayed(dBConnect.getGamesPlayedValue(playerName));
                blueSelected =true;
            }

        }

    }


    class NewHandler implements ActionListener {
        private JPanel detailsPanel;
        private JPanel teamPanel;

        NewHandler(JPanel indetailsPanel, JPanel inteamPanel) {
            detailsPanel = indetailsPanel;
            teamPanel = inteamPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
//            // TODO Auto-generated method stub
            try {
                DBconnect dBConnect = new DBconnect();
                JFrame j = new JFrame("New Player Setup");
                String playerName = JOptionPane.showInputDialog(j, "Enter your name: ");
                if (playerName.length() != 0) {
                    Player tem = new Player(playerName);
                    dBConnect.writeData(playerName);
                } else {
                    JOptionPane.showMessageDialog(j, "Please Type in a Name!");
                    return;
                }
                String action = e.getActionCommand();

                detailsPanel.removeAll();
                detailsPanel.add(new JLabel(" " + playerName));
                detailsPanel.add(new JLabel(" " + dBConnect.getGamesPlayedValue(playerName)));
                detailsPanel.add(new JLabel(" " + dBConnect.getGamesWonValue(playerName)));
                teamPanel.revalidate();
                teamPanel.repaint();
                teamPanel.add(detailsPanel);
                if (action == "newRedPlayer") {
                    redPlayer = new Player(playerName);
                    redPlayer.setGamesPlayed(dBConnect.getGamesPlayedValue(playerName));
                    redPlayer.setColor(Color.RED);
                    newRed = true;
                }else if(action == "newGreenPlayer"){
                    greenPlayer = new Player(playerName);
                    greenPlayer.setGamesPlayed(dBConnect.getGamesPlayedValue(playerName));
                    newGreen =true;
                }else if (action == "newYellowPlayer"){
                    yellowPlayer = new Player(playerName);
                    yellowPlayer.setGamesPlayed(dBConnect.getGamesPlayedValue(playerName));
                    newYellow = true;
                }else{
                    blueplayer = new Player(playerName);
                    blueplayer.setGamesPlayed(dBConnect.getGamesPlayedValue(playerName));
                    newBlue =true;
                }
                selected = true;
            } catch (Exception ex) {

            }
        }
    }
}