package Sorry;

import java.io.Serializable;

public class Card implements Serializable{

    // Variable Declaration
    private static final long serialVersionUID = 1L;
    //initialize card values
    static int SORRY = 13;
    private int value;//holds card value

    /**
     * Constructor that sets the card value
     * @param value the card value
     */
    Card (int value){
        this.value = value;
    }

    /**
     * Method that returns value as an integer
     */
    public int getValue(){
        return value;
    }

    /**
     * Method that converts values to strings and
     * returns the card as a string displaying value
     */
    public String toString(){
        String Value = "Sorry";
        if (value == SORRY)
            Value = "Sorry";
        if (value == 1)
            Value = "1";
        if (value == 2)
            Value = "2";
        if (value == 3)
            Value = "3";
        if (value == 4)
            Value = "4";
        if (value == 5)
            Value = "5";
        if (value == 7)
            Value = "7";
        if (value == 8)
            Value = "8";
        if (value == 9)
            Value = "9";
        if (value == 10)
            Value = "10";
        if (value == 11)
            Value = "11";
        if (value == 12)
            Value = "12";

        String CARD = (Value);
        return CARD;
    }
}
