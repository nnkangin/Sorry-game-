/**
 * @author Max Marder
 */

package Sorry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static Sorry.Main.mainFrame;


/**
 * The splash screen GUI is the first screen the user sees
 * it displays the game logo and credit information
 */
public class MainMenuGUI extends JPanel {

    // Variable Declaration
    private JButton newGame;
    private JButton loadGame;
    private JButton options;
    private JButton exit;
    private boolean pushed;


    public MainMenuGUI(){
        /** Create layout */
        this.setLayout(new GridLayout(4,0));
        this.setBackground(Color.green);

        /** Set up new game button */
        newGame = new JButton("NEW GAME");
        newGame.addActionListener(new ButtonListener());
        this.add(newGame);
        newGame.setActionCommand("newGameClicked");

        /** Set up load game button */
        loadGame = new JButton("LOAD GAME");
        loadGame.addActionListener(new ButtonListener());
        this.add(loadGame);
        loadGame.setActionCommand("loadGameClicked");

        /** Set up options button */
        options = new JButton("OPTIONS");
        options.addActionListener(new ButtonListener());
        this.add(options);
        options.setActionCommand("optionsClicked");

        /** Set up exit button */
        exit = new JButton("QUIT");
        exit.addActionListener(new ButtonListener());
        this.add(exit);
        exit.setActionCommand("exitClicked");

    }

    /**
     * ButtonListener works when screen is pushed
     * to change to next screen
     */
    private class ButtonListener implements  ActionListener {
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            /** New game event handler */
            if (action.equals("newGameClicked")){
                pushed = true;
                mainFrame.setScreenState(MainFrame.state.newGame);
            }
            /** Load game event handler */
            if (action.equals("loadGameClicked")) {
                pushed = true;
                mainFrame.setScreenState(MainFrame.state.loadGame);
            }
            /** Options menu event handler */
            if (action.equals("optionsClicked")) {
                pushed = true;
                mainFrame.setScreenState(MainFrame.state.options);
            }
            /** Exit event handler */
            if (action.equals("exitClicked")) {
                pushed = true;
                System.exit(0);
            }
        }
    }

    public boolean isButtonPushed(){
        return pushed;
    }
}

