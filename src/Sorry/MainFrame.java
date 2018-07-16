/**
 * @author Max Marder
 */

// Imports
package Sorry;

import javax.swing.*;
import java.awt.*;

/**
 * The main Frame for all GUI Panels
 * Main Frame moniters state of the program and handles
 * changing from one menu to another
 */
public class MainFrame extends JFrame {

    // Create state enum
    public enum state {
        splashScreen, mainMenu, newGame, loadGame, options, mainGame, infoPanel, pauseMenu;
    }

    // Variable Declaration
    public static final int frameWidth = 800;
    public static final int frameHeight = 600;
    public static state screenState;
    private Container content;
    private static Game sorryGame;
    private static GameGUI sorryGameGUI;
    private static SplashGUI splash;
    private static MainMenuGUI mainMenu;
    private static NewGameGUI newGame;
    private static LoadGameGUI loadGame;
    private static PauseGameGUI pauseMenu;

    // Constructor
    public MainFrame() {


        // Initialize default state
        this.screenState = state.splashScreen;
        sorryGame = new Game(null, null, null, null);
        sorryGameGUI = new GameGUI(sorryGame);
        splash = new SplashGUI();
        newGame = new NewGameGUI();
        loadGame = new LoadGameGUI();
        mainMenu = new MainMenuGUI();
        pauseMenu = new PauseGameGUI();
        content = getContentPane();
        setSize(frameWidth, frameHeight);
        setTitle("Sorry!");
        content.setBackground(Color.black);
        content.setLayout(new BorderLayout());

        // Update frame according to state
        this.updateFrame();

        // Set default close operation to exit
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void updateFrame() {
        content.setVisible(false);
        content.removeAll();
        content.repaint();
        content.invalidate();
        switch (this.screenState) {
            case splashScreen:
                this.content.add(splash);
                break;
            case mainMenu:
                this.content.add(mainMenu);
                break;
            case newGame:
                this.content.add(newGame);
                break;
            case mainGame:
                this.content.add(sorryGameGUI.getContent());
                break;
            case pauseMenu:
                this.content.add(pauseMenu);
                break;
            case loadGame:
                this.content.add(loadGame);
                break;

        }
        content.setVisible(true);
    }

    public static Game getSorryGame() {
        return sorryGame;
    }
    public static GameGUI getSorryGameGUI() {
        return sorryGameGUI;
    }

    public void setScreenState(state inState){
        this.screenState = inState;
        updateFrame();
    }
    public static void setSorryGame(Game sorryGame) {
        MainFrame.sorryGame = sorryGame;
    }
}