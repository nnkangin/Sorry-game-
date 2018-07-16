/**
 * @author Sierra Cotnoir
 */

package Sorry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import static Sorry.Main.mainFrame;

public class DeckGUI extends JPanel {

    private JButton deal;
    public Deck deck;

    public DeckGUI(Deck inDeck){
        /** Create layout */
        deck = inDeck;
        deck.shuffle();
        this.setLayout(new GridLayout(1,0));
        this.setBackground(Color.green);
        deal = new JButton();
        deal.addActionListener(new ButtonListener());
        this.add(deal);

    }

    /**
     * ButtonListener works when card is pushed to
     * pull up the next one
     */
    private class ButtonListener implements  ActionListener {
        public void actionPerformed(ActionEvent e) {
            boolean status = deck.flip();
            if (deck.isEmpty()){
                deck.freshDeck();
                deck.shuffle();
            }
            JButton source = (JButton) (e.getSource());
            source.setIcon(cardImage());


        }

    }

    /**
     * Add card images to cards
     */
    public ImageIcon cardImage(){
        Card c1 = (deck.checkCard());
        ImageIcon i = new ImageIcon("src/Sorry/card13.PNG");
        for(int x = 1; x <= 13; x++ ){
            if(c1.getValue()== x)
                i = new ImageIcon("src/Sorry/card" + x + ".PNG");

        }
        return i;
    }
}
