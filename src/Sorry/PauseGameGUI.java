package Sorry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import static Sorry.Main.mainFrame;

public class PauseGameGUI extends JPanel {
    // Variable Declaration
    // Variable Declarationprivate JButton newGame;
    private JButton mainMenu;
    private JButton resumeGame;
    private JButton helpMenu;
    private JButton save;
    private JButton exit;
    private boolean pushed;


    public PauseGameGUI() {
        /** Create layout */
        this.setLayout(new GridLayout(5, 0));
        this.setBackground(Color.red);

        /** Set up resume game button */
        resumeGame = new JButton("Resume Game");
        resumeGame.addActionListener(new PauseGameGUI.ButtonListener());
        this.add(resumeGame);
        resumeGame.setActionCommand("resumeGameClicked");

        /** Set up save button */
        save = new JButton("Save Game");
        save.addActionListener(new PauseGameGUI.ButtonListener());
        this.add(save);
        save.setActionCommand("saveGameClicked");

        /** set up help button */
        helpMenu = new JButton("Help Menu");
        helpMenu.addActionListener(new PauseGameGUI.ButtonListener());
        this.add(helpMenu);
        helpMenu.setActionCommand("helpGameClicked");


        /** Set up main menu button */
        mainMenu = new JButton("Main Menu");
        mainMenu.addActionListener(new PauseGameGUI.ButtonListener());
        this.add(mainMenu);
        mainMenu.setActionCommand("mainMenuClicked");

        /** Set up exit button */
        exit = new JButton("Quit");
        exit.addActionListener(new PauseGameGUI.ButtonListener());
        this.add(exit);
        exit.setActionCommand("exitClicked");

    }

    public void saveGame(Game game) {
        try {
            FileOutputStream gameStat = new FileOutputStream("saveGame.dat");
            ObjectOutputStream objectOut = new ObjectOutputStream(gameStat);
            objectOut.writeObject(game);
            objectOut.close();
            gameStat.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    /**
     * ButtonListener works when screen is pushed
     * to change to next screen
     */
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            /** resume game event handler */
            if (action.equals("resumeGameClicked")) {
                pushed = true;
                mainFrame.setScreenState(MainFrame.state.mainGame);
            }
            /** Save menu event handler */
            if (action.equals("saveGameClicked")) {
                pushed = true;
                saveGame(mainFrame.getSorryGame());
                System.out.println("GAME SAVED!!");
            }
            /** Main menu event handler */
            if (action.equals("mainMenuClicked")) {
                pushed = true;
                mainFrame.setScreenState(MainFrame.state.mainMenu);
            }
            /** Exit event handler */
            if (action.equals("exitClicked")) {
                pushed = true;
                System.exit(0);
            }if(action.equals("helpGameClicked")){
                pushed=true;

                JFrame j = new JFrame("Help Menu");
                JOptionPane.showMessageDialog(j, "Each player chooses four pawns of one color and places them in his or her Start. One player is selected to play first.\n" +
                        "\n" +
                        "Each player in turn draws one card from the deck and follows its instructions. To begin the game, all of a player's four pawns are restricted to Start; a player can " +
                        "\n" +
                        "only move them out onto the rest of the board if he or she draws a 1 or 2 card. A 1 or a 2 places a pawn on the space directly outside of start (a 2 does not entitle the pawn to move a second space).\n" +
                        "\n" +
                        "A pawn can jump over any other pawn during its move. However, two pawns cannot occupy the same square; a pawn that lands on a square occupied by another player's pawn \"bumps\" that pawn back to its own Start. " +
                        "\n" +
                        "Players can not bump their own pawns back to Start; if the only way to complete a move would result in a player bumping his or her own pawn, the player's pawns remain in place and the player loses his or her turn.\n" +
                        "\n" +
                        "If a pawn lands at the start of a slide (except those of its own color), either by direct movement or as the result of a switch from an 11 card or a Sorry card, it immediately \"slides\" to the last square of the slide. " +
                        "\n" +
                        "All pawns on all spaces of the slide (including those belonging to the sliding player) are sent back to their respective Starts.[4]\n" +
                        "\n" +
                        "The last five squares before each player's Home are \"Safety Zones\", and are specially colored corresponding to the colors of the Homes they lead to. Access is limited to pawns of the same color. " +
                        "\n" +
                        "Pawns inside the Safety Zones are immune to being bumped by opponent's pawns or being switched with opponents' pawns via 11 or Sorry! cards. " +
                        "\n" +
                        "However, if a pawn is forced via a 10 or 4 card to move backwards out of the Safety Zone, it is no longer considered \"safe\" and may be bumped by or switched with opponents' pawns as usual until it re-enters the Safety Zone.\n",
                        "Game Instructions",JOptionPane.INFORMATION_MESSAGE);

            }

        }
    }

    public boolean isButtonPushed() {
        return pushed;
    }
}