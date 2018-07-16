/**
 * @author Max Marder
 */

// Imports
package Sorry;

import java.io.Serializable;
import java.util.ArrayList;
import java.awt.*;

public class Pawn implements Serializable{

    // Create direction enum
    public enum direction {
        up, down, left, right;
    }

    public enum position {
        redHome, blueHome, greenHome, yellowHome, board, blueSafe, redSafe, yellowSafe, greenSafe;
    }

    // Member Variables
    private static final long serialVersionUID = 1L;
    private String id = null;
    private Color color;
    String filePath;
    private ArrayList<BoardSquare> possibleMoves = new ArrayList<>();
    private Deck deck;
    private direction direction;
    private position position;
    public boolean swap;


    // Constructor
    public Pawn(String id, Color color, String filePath, Deck deck) {
        this.id = id;
        this.color = color;
        this.filePath = filePath;
        this.deck = deck;
        this.swap = false;
    }

    public ArrayList<BoardSquare> getPosMoves(BoardSquare boardState[][], int x, int y) {
        // clear possibleMoves from previous getPosMoves
        possibleMoves.clear();
        // TODO remove hard coded values
        Card card = deck.checkCard();
        //System.out.println(card);
        direction = getDirection(x,y);
        //System.out.println(direction);
        position = getPosition(x,y);
        //System.out.println((position));
        int remainder = 0;
        int remainder2 = 0;

        if(card.getValue() == 11){
            swap = true;
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    if (boardState[i][j].getPawn() != null && (i == 0 || i == 16 - 1 || j == 0 || j == 16 - 1) && boardState[i][j].getPawn().getcolor() != this.color) {
                        possibleMoves.add(boardState[i][j]);
                    }
                }
            }
        }
        else{
            swap = false;
        }

        if(card.getValue() == 13){
            if(position != position.board) {
                for (int i = 0; i < 16; i++) {
                    for (int j = 0; j < 16; j++) {
                        if (boardState[i][j].getPawn() != null && (i == 0 || i == 16 - 1 || j == 0 || j == 16 - 1) && boardState[i][j].getPawn().getcolor() != this.color) {
                            possibleMoves.add(boardState[i][j]);
                        }
                    }
                }
            }
        }
        else {
            if (direction == direction.left) {
                if (getPosition(x, y) == position.board) {
                    // Move 1 backwards on 10
                    if (card.getValue() == 10) {
                        if (y + 1 > 15) {
                            possibleMoves.add(boardState[x - 1][y]);
                        } else {
                            possibleMoves.add(boardState[x + remainder][y + 1 - remainder]);
                        }
                    }

                    // Move 4 backwards on 4
                    if (card.getValue() == 4) {
                        if (y + 4 > 15) {
                            remainder = y + card.getValue() - 15;
                        }
                        possibleMoves.add(boardState[x - remainder][y + card.getValue() - remainder]);
                    }

                    // Make sure pawn doesn't go past edge
                    if (y - card.getValue() < 0) {
                        remainder = y - card.getValue();
                    }

                    if (card.getValue() != 4) {
                        possibleMoves.add(boardState[x + remainder][y - card.getValue() - remainder]);
                        if(card.getValue() == 7){
                            for(int i = 1; i < 7; i++){
                                int newCard = card.getValue()-i;
                                if (y - newCard < 0) {
                                    remainder = y - newCard;
                                }
                                else {
                                    remainder = 0;
                                }
                                possibleMoves.add(boardState[x + remainder][y-newCard-remainder]);
                            }
                        }
                    }
                } else {
                    moveFromHome(card, x, y, boardState);
                }
                for (int i = 0; i < possibleMoves.size(); i++) {
                    if (possibleMoves.get(i).getPawn() != null && possibleMoves.get(i).getPawn().getcolor() == boardState[x][y].getPawn().getcolor()) {
                        possibleMoves.remove(i);

                    }
                }

            }

            if (direction == direction.right) {
                if (getPosition(x, y) == position.board) {

                    if (card.getValue() == 10) {
                        if (y - 1 < 0) {
                            possibleMoves.add(boardState[x + 1][y]);
                        } else {
                            possibleMoves.add(boardState[x + remainder][y - 1 - remainder]);
                        }
                    }

                    if (card.getValue() == 4) {
                        if (y - 4 < 0) {
                            remainder = y - card.getValue();
                        }
                        possibleMoves.add(boardState[x - remainder][y - card.getValue() - remainder]);
                    }

                    if (y + card.getValue() > 15) {
                        remainder = y + card.getValue() - 15;
                    }
                    if (card.getValue() != 4) {
                        possibleMoves.add(boardState[x + remainder][y + card.getValue() - remainder]);
                        if(card.getValue() == 7){
                            for(int i = 1; i < 7; i++){
                                int newCard = card.getValue()-i;
                                if (y + newCard > 15) {
                                    remainder = y + newCard - 15;
                                }
                                else {
                                    remainder = 0;
                                }
                                possibleMoves.add(boardState[x + remainder][y + newCard - remainder]);                            }
                        }
                    }
                    if(safeZoneCheck(x,y) == true){
                        remainder = 0;
                        remainder2 = 0;
                        if (y + card.getValue() > 15) {
                            for (int i = 0; i < possibleMoves.size(); i++) {
                                possibleMoves.remove(i);
                            }
                            remainder = y + card.getValue() - 15;
                            if (x + remainder > 2){
                                remainder2 = remainder - 2;
                            }
                            if(15-remainder2 >= 10) {
                                possibleMoves.add(boardState[2][15 - remainder2]);
                            }
                        }
                    }
                } else {
                    moveFromHome(card, x, y, boardState);
                }
                for (int i = 0; i < possibleMoves.size(); i++) {
                    if (possibleMoves.get(i).getPawn() != null && possibleMoves.get(i).getPawn().getcolor() == boardState[x][y].getPawn().getcolor()) {
                        possibleMoves.remove(i);

                    }
                }

            }
            if (direction == direction.down) {
                if (getPosition(x, y) == position.board) {

                    if (card.getValue() == 10) {
                        if (x - 1 < 0) {
                            possibleMoves.add(boardState[x][y - 1]);
                        } else {
                            possibleMoves.add(boardState[x - 1 - remainder][y - remainder]);
                        }

                    }

                    if (card.getValue() == 4) {
                        if (x - 4 < 0) {
                            remainder = x - card.getValue();
                        }
                        possibleMoves.add(boardState[x - card.getValue() - remainder][y + remainder]);
                    }

                    if (x + card.getValue() > 15) {
                        remainder = x + card.getValue() - 15;
                    }
                    if (card.getValue() != 4) {
                        possibleMoves.add((boardState[x + card.getValue() - remainder][y - remainder]));
                        if(card.getValue() == 7) {
                            for (int i = 1; i < 7; i++) {
                                int newCard = card.getValue() - i;
                                if (x + newCard > 15) {
                                    remainder = x + newCard - 15;
                                } else {
                                    remainder = 0;
                                }
                                possibleMoves.add((boardState[x + newCard - remainder][y - remainder]));
                            }
                        }
                    }
                } else {
                    moveFromHome(card, x, y, boardState);
                }
                for (int i = 0; i < possibleMoves.size(); i++) {
                    if (possibleMoves.get(i).getPawn() != null && possibleMoves.get(i).getPawn().getcolor() == boardState[x][y].getPawn().getcolor()) {
                        possibleMoves.remove(i);

                    }
                }

            }

            if (direction == direction.up) {
                if (getPosition(x, y) == position.board) {

                    if (card.getValue() == 10) {
                        if (x + 1 > 15) {
                            possibleMoves.add(boardState[x][y + 1]);
                        } else {
                            possibleMoves.add((boardState[x + 1 - remainder][y - remainder]));
                        }

                    }

                    if (card.getValue() == 4) {
                        if (x + 4 > 15) {
                            remainder = x + card.getValue() - 15;
                        }
                        possibleMoves.add((boardState[x + card.getValue() - remainder][y + remainder]));
                    }

                    if (x - card.getValue() < 0) {
                        remainder = x - card.getValue();
                    }
                    if (card.getValue() != 4) {
                        possibleMoves.add(boardState[x - card.getValue() - remainder][y - remainder]);
                        if(card.getValue() == 7) {
                            for (int i = 1; i < 7; i++) {
                                int newCard = card.getValue() - i;
                                if (x - newCard < 0) {
                                    remainder = x - newCard;
                                }
                                else {
                                    remainder = 0;
                                }
                                possibleMoves.add(boardState[x - newCard - remainder][y - remainder]);                            }
                        }
                    }
                } else {
                    moveFromHome(card, x, y, boardState);
                }
                for (int i = 0; i < possibleMoves.size(); i++) {
                    if (possibleMoves.get(i).getPawn() != null && possibleMoves.get(i).getPawn().getcolor() == boardState[x][y].getPawn().getcolor()) {
                        possibleMoves.remove(i);
                    }
                }
            }
        }


//        for (int i = 0; i < 16; i++) {
//            for (int j = 0; j < 16; j++) {
//                if (boardState[i][j].getPawn() == null && (i == 0 || i == 16 - 1 || j == 0 || j == 16 - 1)) {
//                    possibleMoves.add(boardState[i][j]);
//                }
//            }
//        }
        return possibleMoves;
    }

    // Id Setter
    public void setId(String id) {
        this.id = id;
    }

    // Color Setter
    public void setcolor(Color color) {
        this.color = color;
    }

    //Id getter
    public String getId() {
        return id;
    }

    //Color Getter
    public Color getcolor() {
        return this.color;
    }

    //Path getter
    public String getPath() {
        return this.filePath;
    }

    public direction getDirection(int x, int y){
        if (getPosition(x,y) == position.board){
            if( y >= 0 && y < 15 && x == 0 ){
                return direction.right;
            }
            else if( y == 15 && x >=0  && x < 15 ){
                return direction.down;
            }
            else if( y > 0 && y <= 15 && x == 15 ){
                return direction.left;
            }
            else{
                return direction.up;
            }
        }

        else if(getPosition(x,y) == position.blueSafe){
            return direction.left;
        }
        else if(getPosition(x,y) == position.redSafe){
            return  direction.down;
        }
        else if(getPosition(x,y) == position.yellowSafe){
            return direction.up;
        }
        else if(getPosition(x,y) == position.greenSafe){
            return direction.right;
        }

        else if (getPosition(x,y) == position.redHome){
            return direction.up;
        }
        else if(getPosition(x,y) == position.blueHome){
            return direction.right;
        }
        else if (getPosition(x,y) == position.yellowHome){
            return direction.down;
        }
        else{
            return direction.left;
        }

    }

    public position getPosition(int x, int y){
        if(x == 1 && y == 3 || x == 1 && y == 4 || x == 1 && y == 5 || x == 2 && y == 4){
            return position.redHome;
        }
        else if(y == 14 && x == 3 || y == 14 && x == 4 || y == 14 && x == 5 || y == 13 && x == 4){
            return position.blueHome;
        }
        else if(x == 14 && y == 12 || x == 14 && y == 11 || x == 14 && y == 10 || x == 13 && y == 11){
            return  position.yellowHome;
        }
        else if(y == 1 && x == 12 || y == 1 && x == 11 || y == 1 && x == 10 || y == 2 && x == 11){
            return position.greenHome;
        }
        else if(x == 2 && y >= 10 && y <= 14){
            return position.blueSafe;
        }
        else if(y == 2 && x >= 1 && x <= 5){
            return position.redSafe;
        }
        else if(y == 13 && x >= 10 && x <= 14){
            return position.yellowSafe;
        }
        else if(x == 13 && y >= 1 && y <= 5){
            return position.greenSafe;
        }
        else{
            return position.board;
        }
    }

    public void moveFromHome(Card card, int x, int y, BoardSquare boardState[] []){

        if (card.getValue() <= 2) {
            if (getPosition(x, y) == position.redHome) {
                possibleMoves.add(boardState[0][4]);
            }
            if (getPosition(x, y) == position.blueHome) {
                possibleMoves.add(boardState[4][15]);
            }
            if (getPosition(x, y) == position.yellowHome) {
                possibleMoves.add(boardState[15][11]);
            }
            if (getPosition(x, y) == position.greenHome) {
                possibleMoves.add(boardState[11][0]);
            }
        }

    }

    public boolean safeZoneCheck(int x, int y){
        if(this.color == Color.BLUE){
            if(this.direction == direction.right || this.direction == direction.down && x < 3){
                return true;
            }
        }
        else if(this.color == Color.GREEN){
            if(this.direction == direction.left || this.direction == direction.up && x > 12){
                return true;
            }
        }
        else if(this.color == Color.RED) {
            if (this.direction == direction.up || this.direction == direction.right && y < 3) {
                return true;
            }
        }
        else if(this.color == Color.YELLOW){
            if(this.direction == direction.down || this.direction == direction.left && y > 12){
                return true;
            }
        }
        return false;
    }

    public void directionSwap(int x, int y){
        if (getDirection(x,y) == direction.up){
            direction = direction.down;
        }
        if (getDirection(x,y) == direction.down){
            direction = direction.up;
        }
        if (getDirection(x,y) == direction.left){
            direction = direction.right;
        }
        if (getDirection(x,y) == direction.right){
            direction = direction.left;
        }
    }

}
