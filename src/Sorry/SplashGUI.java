/**
 * @author Sierra Cotnoir and Max Marder
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
public class SplashGUI extends JPanel {

    // Variable Declaration
    private JButton start;
    private boolean pushed;


    public SplashGUI(){
        /** Create layout */
        this.setLayout(new GridLayout(1,0));
        this.setBackground(Color.green);
        start = new JButton("Click to start!");
        start.addActionListener(new ButtonListener());
        this.add(start);
        start.setActionCommand("pushed");

    }

    /**
     * ButtonListener works when screen is pushed
     * to change to next screen
     */
    private class ButtonListener implements  ActionListener {
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            if (action.equals("pushed")){
                pushed = true;
                mainFrame.setScreenState(MainFrame.state.mainMenu);
            }
        }
    }

    public boolean isButtonPushed(){
        return pushed;
    }
}
