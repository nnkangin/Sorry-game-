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

import static Sorry.Main.mainFrame;


public class ControlPanel extends JPanel {

    // Variable Declaration
    // declare game
    private static Game sorryGame;
    // declare sub-panels for each color
    private JPanel redTeam, greenTeam, yellowTeam, blueTeam;
    // declare stats panels
    private JPanel redStats;
    private JPanel greenStats;
    private JPanel yellowStats;
    private JPanel blueStats;
    // declare information panel
    private JPanel infoPanel;
    // delare player panel
    private JPanel playerPanel;
    // declare deck panel
    private JPanel deckPanel;
    // declare button panel
    private JPanel buttonPanel;
    // declare buttons
    private JButton endTurn, pauseMenu;
    // declare player names
    private static String redName, greenName, yellowName, blueName;
    // declare deck gui elements
    public static DeckGUI deckGui;
    private static Player players;
    public Board board;




    // Constructor
    public ControlPanel(Game inSorryGame) {
        // set game variable
        sorryGame = inSorryGame;

        /** Create layout for Control Panel */
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder(null, "GAME PANEL", TitledBorder.TOP, TitledBorder.CENTER, new Font("arial", Font.PLAIN, 14), Color.ORANGE));

        // Configure player panel
        playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(5, 1));
        // Configure information panel
        infoPanel = new JPanel();
        infoPanel.setBorder(BorderFactory.createTitledBorder(null, "CURRENT TURN", TitledBorder.TOP, TitledBorder.CENTER, new Font("arial", Font.BOLD, 10), Color.BLACK));
        infoPanel.setLayout(new BorderLayout());
        try {
            infoPanel.add(new JLabel(sorryGame.getSorryBoard().getTurn(sorryGame.getRedPlayer(), sorryGame.getYellowPlayer(), sorryGame.getGreenPlayer(), sorryGame.getBluePlayer()).getName()));
        }catch(Exception e){}
        playerPanel.add(infoPanel, BorderLayout.NORTH);
        // Configure Team Panels
        // Red Team Panel
        redTeam = new JPanel();
        redTeam.setBorder(BorderFactory.createTitledBorder(null, "RED TEAM", TitledBorder.TOP, TitledBorder.CENTER, new Font("arial", Font.BOLD, 10), Color.RED));
        redTeam.setLayout(new BorderLayout());
        // Green Team Panel
        greenTeam = new JPanel();
        greenTeam.setBorder(BorderFactory.createTitledBorder(null, "GREEN TEAM", TitledBorder.TOP, TitledBorder.CENTER, new Font("arial", Font.BOLD, 10), Color.GREEN));
        greenTeam.setLayout(new BorderLayout());
        // Yellow Team Panel
        yellowTeam = new JPanel();
        yellowTeam.setBorder(BorderFactory.createTitledBorder(null, "YELLOW TEAM", TitledBorder.TOP, TitledBorder.CENTER, new Font("arial", Font.BOLD, 10), Color.YELLOW));
        yellowTeam.setLayout(new BorderLayout());
        // Blue Team Panel
        blueTeam = new JPanel();
        blueTeam.setBorder(BorderFactory.createTitledBorder(null, "BLUE TEAM", TitledBorder.TOP, TitledBorder.CENTER, new Font("arial", Font.BOLD, 10), Color.BLUE));
        blueTeam.setLayout(new BorderLayout());
        // Define stats panels
        redStats = new JPanel(new GridLayout(1, 1));
        greenStats = new JPanel(new GridLayout(1, 1));
        yellowStats = new JPanel(new GridLayout(1, 1));
        blueStats = new JPanel(new GridLayout(1, 1));
        // define names
       redName = "poop" ;
        greenName = "poop";
        yellowName = "poop";
        blueName = "poop";
        // add labels to stats panels
        redStats.add(new JLabel("Name:  " + redName));
        greenStats.add(new JLabel("Name:  " + greenName));
        yellowStats.add(new JLabel("Name:  " + yellowName));
        blueStats.add(new JLabel("Name:  " + blueName));
        // add stats panels to team panels
        redTeam.add(redStats);
        greenTeam.add(greenStats);
        yellowTeam.add(yellowStats);
        blueTeam.add(blueStats);
        // add team panels to player panel
        playerPanel.add(redTeam);
        playerPanel.add(greenTeam);
        playerPanel.add(yellowTeam);
        playerPanel.add(blueTeam);
        // add player panel to GUI
        this.add(playerPanel, BorderLayout.NORTH);

        // Configure deck panel
        deckPanel = new JPanel();
        deckPanel.setLayout(new GridLayout(1, 1));
        // define deck gui
        deckGui = new DeckGUI(sorryGame.getSorryDeck());
        // add deck panel to GUI
        this.add(deckGui, BorderLayout.CENTER);

        // Configure button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 0));
        endTurn = new JButton("END TURN");
        endTurn.addActionListener(new ButtonListener());
        endTurn.setActionCommand("endTurn");
        pauseMenu = new JButton("PAUSE");
        pauseMenu.addActionListener(new ButtonListener());
        pauseMenu.setActionCommand("pauseGame");
        // add buttons
        buttonPanel.add(endTurn);
        buttonPanel.add(pauseMenu);
        // add button panel to GUI
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    // TODO create function that updates the detail panels of the control panel with the current player names
    // TODO start the control panel with placeholder names and call the update function once the game is started form the start button in newGameGui
    public void updatePlayerDetails() {
        // update red team stats panel
        redName = sorryGame.getRedPlayer().getName();
        redStats.removeAll();
        redStats.add(new JLabel(" " + redName));
        // update green team stats panel
        greenName = sorryGame.getGreenPlayer().getName();
        greenStats.removeAll();
        greenStats.add(new JLabel(" " + greenName));
        //update yellow team stats panel
        yellowName = sorryGame.getYellowPlayer().getName();
        yellowStats.removeAll();
        yellowStats.add(new JLabel(" " + yellowName));
        //update blue team stats panel
        blueName = sorryGame.getBluePlayer().getName();
        blueStats.removeAll();
        blueStats.add(new JLabel(" " + blueName));

        // redraw control panel with new details
        this.revalidate();
        this.repaint();
    }

    // updates contents of info panel with current players turn
    public void updateInfoPanel() {
        // remove info from turn display
        infoPanel.removeAll();
        String currentTurn = sorryGame.getSorryBoard().getTurn(sorryGame.getRedPlayer(),sorryGame.getYellowPlayer(),sorryGame.getGreenPlayer(),sorryGame.getBluePlayer()).getName();
        infoPanel.add(new JLabel(" " + currentTurn));
        infoPanel.revalidate();
        infoPanel.repaint();
    }

    public void incrementPlayerTurn(){
        // change turn in game state
        sorryGame.getSorryBoard().getTurn(sorryGame.getRedPlayer(),sorryGame.getYellowPlayer(),sorryGame.getGreenPlayer(),sorryGame.getBluePlayer()).setTurncount(sorryGame.getSorryBoard().getTurn(sorryGame.getRedPlayer(),sorryGame.getYellowPlayer(),sorryGame.getGreenPlayer(),sorryGame.getBluePlayer()).getTurncount()+1);
    }

    /**
     * ButtonListener works when screen is pushed
     * to change to next screen
     */
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            /** Turn end handler */
            // TODO End current team turn when pressed
            if (action.equals("endTurn")) {
                incrementPlayerTurn();
                updateInfoPanel();
//                // TEST button functionality
//                infoPanel.removeAll();
//                //infoPanel.remove(new JLabel(sorryGame.getSorryBoard().getTurn(sorryGame.getRedPlayer(),sorryGame.getYellowPlayer(),sorryGame.getGreenPlayer(),sorryGame.getBluePlayer()).getName()));
//
//
//
//                // update infor panel with current payer turn
//                infoPanel.add(new JLabel(" " + sorryGame.getSorryBoard().getTurn(sorryGame.getRedPlayer(),sorryGame.getYellowPlayer(),sorryGame.getGreenPlayer(),sorryGame.getBluePlayer()).getName()));
//                System.out.println(sorryGame.getSorryBoard().getTurn(sorryGame.getRedPlayer(),sorryGame.getYellowPlayer(),sorryGame.getGreenPlayer(),sorryGame.getBluePlayer()).getName() +" its your turn");
            }
            /** Pause game handler */
            // TODO pause the game and prevent additional player input
            // TODO bring up pause menu to resume, save, and exit
            if (action.equals("pauseGame")) {
                mainFrame.setScreenState(MainFrame.state.pauseMenu);
            }
        }
    }
}
