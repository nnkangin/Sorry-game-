/**
 * @author Sierra Cotnoir
 */

package Sorry;

import java.io.Serializable;
import java.util.Random;
import java.util.ArrayList;

public class Deck implements Serializable{

    // Variable Declaration
    private static final long serialVersionUID = 1L;
    /**
     * Number of cards in deck {@value #CARDS_IN_DECK}
     */
    final static int CARDS_IN_DECK = 45;

    /** The collection of Cards */
    private ArrayList<Card> deck;

    /**
     * Constructs a 45-card deck of sorry cards. Cards start in
     * sorted order.  shuffle() method used to randomize order.
     */
    public Deck(){
        freshDeck();
    }
    /**
     * Creates an empty deck of cards with specified size
     */
    public Deck(int cards){
        deck = new ArrayList<Card>(cards);
    }

    /**
     * Give the option of adding a single card to a deck.
     * Used in junction with the deck size constructor
     * in order to create hands instead of full decks
     * @param card the card being added to the deck
     */
    public void addCard(Card card)
    {
        deck.add(card);
    }

    /**
     * Create a new collection of 45 cards in sorted order
     */
    public void freshDeck(){
        deck = new ArrayList<Card>();

        //add the ones
        for (int r = 1; r <= 5; r++)
            deck.add(new Card(1));
        //add the other values
        for (int r = 1; r <=4; r++){
            deck.add(new Card(2));
            deck.add(new Card(3));
            deck.add(new Card(4));
            deck.add(new Card(5));
            deck.add(new Card(7));
            deck.add(new Card(8));
            deck.add(new Card(10));
            deck.add(new Card(11));
            deck.add(new Card(12));
            deck.add(new Card(13));
        }

    }

    /**
     * Remove and return the top Card on the Deck
     * @return A reference to a Card that was top on the Deck
     */
    public Card dealCard()
    {
        Card c = deck.remove(0);  //  remove it (returns removed object)
        return c;
    }

    public Card checkCard()
    {
        Card c = deck.get(0);
        return c;
    }
    /**
     * Return current number of Cards in Deck
     * @return number of Cards in Deck
     */

    public int cardsRemaining()
    {
        return deck.size();
    }
    /**
     * Randomize the order of Cards in Deck
     */

    public void shuffle()
    {
        int randNum;
        Card temp;
        Random r = new Random();
        for (int i = 0; i < deck.size(); i++)
        {
            randNum = r.nextInt(deck.size());
            temp = deck.get(i);
            deck.set(i,deck.get(randNum));
            deck.set(randNum,temp);
        }
    }
    /**
     * Determine if Deck is empty
     * @return true if there are no more cards, false otherwise
     */

    public boolean isEmpty()
    {
        return (deck.size() == 0);
    }

    public boolean flip(){
        if(!this.isEmpty()){
            boolean status = true;
            this.dealCard();
            return status;
        }
        else{
            boolean status = false;
            return status;
        }
    }
}
