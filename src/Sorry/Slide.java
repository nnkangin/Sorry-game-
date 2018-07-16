/**
 * @author Griffin Cohen
 */

// Imports
package Sorry;

import java.awt.*;
import java.util.ArrayList;

public class Slide {
    //Bool to determine if pawn is at the start of slide
    protected Color color;
    private String id;
    private ArrayList<BoardSquare> slideState = new ArrayList<>();

    public Slide(String id, Color color){
        this.id=id;
        this.setColor(color);


    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public void addSquare(BoardSquare square){
        slideState.add(square);
    }

    public void printPawns(){
        for(int i =0; i < slideState.size();i++){
            if(slideState.get(i).isFilled()){
                System.out.printf("There is a pawn at position: %d, %d", slideState.get(i).x, slideState.get(i).y);
            }
        }
    }

//slide.add (reference index to add)
}
