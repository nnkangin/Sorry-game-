/**
 /**
 * @author Max Marder
 */

// Imports
package Sorry;

import javax.swing.*;
import java.awt.*;

/**
 * The GameGUI contains the split pane
 * containing the main game and control panel GUI
 */
public class GameGUI extends JSplitPane{

    // Variable Declaration
    public static Game sorryGame;
    public static BoardGUI sorryBoardGui;
    public static ControlPanel controlPanelGui;
    public static DeckGUI deckGui;
    public static Deck sorryDeck;
    private JSplitPane splitPanel;

    // Constructor
    public GameGUI(Game inSorryGame){
        sorryGame = inSorryGame;
        sorryDeck = sorryGame.getSorryDeck();
        deckGui = new DeckGUI(sorryGame.getSorryDeck());
        // TODO add deck gui to control panel
        controlPanelGui = new ControlPanel(sorryGame);
        sorryBoardGui = new BoardGUI(sorryGame.getSorryBoard());
        splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sorryBoardGui, controlPanelGui);
    }

    public JSplitPane getContent(){
        return this.splitPanel;
    }

    public static ControlPanel getControlPanelGui() {
        return controlPanelGui;
    }
}