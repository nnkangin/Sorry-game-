/**
 * @author Max Marder
 */

// Imports
package Sorry;

// Imports

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

import static Sorry.Main.mainFrame;

// Sorry Board Class
public class Board implements MouseListener, Serializable {

    // Variable Declaration
    private static final long serialVersionUID = 1L;
    private static final int boardLength = 16;
    private static final int boardHeight = 16;
    public static final int frameWidth = 800;
    public static final int frameHeight = 600;
    public Deck cards;
    private BoardSquare tempSquare, selectedSquare, prevSelectedSquare;
    public static BoardSquare[][] boardState;
    private Slide greenSlide1 = new Slide("greenSlide1", Color.green);
    private Slide greenSlide2 = new Slide("greenSlide2", Color.green);
    private Slide redSlide1 = new Slide("redSlide1", Color.red);
    private Slide redSlide2 = new Slide("redSlide2", Color.red);
    private Slide yellowSlide1 = new Slide("yellowSlide1", Color.yellow);
    private Slide yellowSlide2 = new Slide("yellowSlide2", Color.yellow);
    private Slide blueSlide1 = new Slide("blueSlide1", Color.blue);
    private Slide blueSlide2 = new Slide("blueSlide2", Color.blue);
    private ArrayList<BoardSquare> destList = new ArrayList<>();
    private static Pawn r0, r1, r2, r3, // Red Pawns
                        g0, g1, g2, g3, // Green Pawns
                        y0, y1, y2, y3, // Yellow Pawns
                        b0, b1, b2, b3; // Blue Pawns

    // Start Zone Positions
    int[][] redStartZonePositions = {{1,4},{1,3},{1,5},{2,4}};
    int[][] greenStartZonePositions = {{11,1},{11,2},{10,1},{12,1}};
    int[][] yellowStartZonePositions = {{14,11},{14,10},{14,12},{13,11}};
    int[][] blueStartZonePositions = {{4,14},{5,14},{3,14},{4,13}};

    // Safe Zone Positions
    private int safeZoneLength = 5;
    private int redSafeZoneX = 1;
    private int redSafeZoneY = 2;
    private int greenSafeZoneX = 13;
    private int greenSafeZoneY = 1;
    private int yellowSafeZoneX = 14;
    private int yellowSafeZoneY = 13;
    private int blueSafeZoneX = 2;
    private int blueSafeZoneY = 14;

    // End Zone Positions
    int[][] redEndZonePositions = {{1+safeZoneLength,2},{1+safeZoneLength,1},{1+safeZoneLength,3},{2+safeZoneLength,2}};
    int[][] greenEndZonePositions = {{14-safeZoneLength,13},{14-safeZoneLength,12},{14-safeZoneLength,14},{13-safeZoneLength,13}};
    int[][] yellowEndZonePositions ={{13,1+safeZoneLength},{13,2+safeZoneLength},{12,1+safeZoneLength},{14,1+safeZoneLength}};
    int[][] blueEndZonePositions = {{2,14-safeZoneLength},{3,14-safeZoneLength},{1,14-safeZoneLength},{2,13-safeZoneLength}};

    // Adding players
    private Player redPlayer, greenPlayer, yellowPlayer, bluePlayer;


    /**
     * Constructs a 16 x 16 Sorry board.
     */
    public Board(Deck deck) {

        /** Define default boardState.*/
        cards = deck;
        redPlayer = new Player("RED");
        redPlayer.setColor(Color.red);
        greenPlayer = new Player("Blue");
        greenPlayer.setColor(Color.green);
        yellowPlayer = new Player("Green");
        yellowPlayer.setColor(Color.yellow);
        bluePlayer = new Player("Blue");
        bluePlayer.setColor(Color.blue);
        mainFrame.getSorryGame().getRedPlayer();
        initBoard();
        initStartZones();
        initSafeZones();
        initEndZones();
        initSlides();
        initPawns();
    }

    private void initBoard() {
        // Define starting configuration of boardState game logic
        boardState = new BoardSquare[boardLength][boardHeight];
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardHeight; j++) {
                // define board square with position (x, y) and null pawn
                // then add mouse listener to boardSquare
                tempSquare = new BoardSquare(i, j, null);
                tempSquare.addMouseListener(this);

                // recolor squares at periphery of the board and add border
                if (i == 0 || i == boardLength - 1 || j == 0 || j == boardHeight - 1) {
                    tempSquare.setBackground(new Color(113, 198, 113));
                    tempSquare.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(frameWidth / 1000.0f)));
                    boardState[i][j] = tempSquare;
                } else {
                    tempSquare.setBackground(Color.BLACK);
                    boardState[i][j] = tempSquare;
                }
            }
        }
    }

    private void initStartZones(){
        makeStartZone(redStartZonePositions, Color.RED);
        makeStartZone(greenStartZonePositions, Color.GREEN);
        makeStartZone(yellowStartZonePositions, Color.GREEN);
        makeStartZone(blueStartZonePositions, Color.BLUE);
    }

    private void makeStartZone(int[][] startZonePositions, Color c){
        for (int i = 0; i < startZonePositions.length; i++){
            boardState[startZonePositions[i][0]][startZonePositions[i][1]].setColor(c);
            boardState[startZonePositions[i][0]][startZonePositions[i][1]].setSafe(true);
            boardState[startZonePositions[i][0]][startZonePositions[i][1]].setStart(true);
            boardState[startZonePositions[i][0]][startZonePositions[i][1]].setBackground(c);
            boardState[startZonePositions[i][0]][startZonePositions[i][1]].setBorder(BorderFactory.createStrokeBorder(new BasicStroke(frameWidth / 1000.0f)));
        }
    }

    private void initSafeZones() {
        //Adding red safe Zones to board______________________________________________________________
        for (int i = 0; i < safeZoneLength; i++) {
            boardState[redSafeZoneX +i][redSafeZoneY].setColor(Color.RED);
            boardState[redSafeZoneX +i][redSafeZoneY].setSafe(true);
            boardState[redSafeZoneX +i][redSafeZoneY].setBackground(boardState[redSafeZoneX][redSafeZoneY].getColor());
            boardState[redSafeZoneX +i][redSafeZoneY].setBorder(BorderFactory.createStrokeBorder(new BasicStroke(frameWidth / 1000.0f)));
        }
        //Adding green safe Zone to board____________________________________________________________
        for (int i = 0; i < safeZoneLength; i++) {
            boardState[greenSafeZoneX][greenSafeZoneY +i].setColor(Color.GREEN);
            boardState[greenSafeZoneX][greenSafeZoneY +i].setSafe(true);
            boardState[greenSafeZoneX][greenSafeZoneY +i].setBackground(boardState[greenSafeZoneX][greenSafeZoneY].getColor());
            boardState[greenSafeZoneX][greenSafeZoneY +i].setBorder(BorderFactory.createStrokeBorder(new BasicStroke(frameWidth / 1000.0f)));
        }
        //Adding yellow safe Zone to board____________________________________________________________
        for (int i = 0; i < safeZoneLength; i++) {
            boardState[yellowSafeZoneX -i][yellowSafeZoneY].setColor(Color.YELLOW);
            boardState[yellowSafeZoneX -i][yellowSafeZoneY].setSafe(true);
            boardState[yellowSafeZoneX -i][yellowSafeZoneY].setBackground(boardState[yellowSafeZoneX][yellowSafeZoneY].getColor());
            boardState[yellowSafeZoneX -i][yellowSafeZoneY].setBorder(BorderFactory.createStrokeBorder(new BasicStroke(frameWidth / 1000.0f)));
        }
        //Adding blue safe Zone to board____________________________________________________________
        for (int i = 0; i < safeZoneLength; i++) {
            boardState[blueSafeZoneX][blueSafeZoneY -i].setColor(Color.BLUE);
            boardState[blueSafeZoneX][blueSafeZoneY -i].setSafe(true);
            boardState[blueSafeZoneX][blueSafeZoneY -i].setBackground(boardState[blueSafeZoneX][blueSafeZoneY].getColor());
            boardState[blueSafeZoneX][blueSafeZoneY -i].setBorder(BorderFactory.createStrokeBorder(new BasicStroke(frameWidth / 1000.0f)));
        }
    }

    private void initEndZones(){
        makeEndZone(redEndZonePositions, Color.RED);
        makeEndZone(greenEndZonePositions, Color.GREEN);
        makeEndZone(yellowEndZonePositions, Color.GREEN);
        makeEndZone(blueEndZonePositions, Color.BLUE);
    }

    private void makeEndZone(int[][] endZonePositions, Color c){
        for (int i = 0; i < endZonePositions.length; i++){
            boardState[endZonePositions[i][0]][endZonePositions[i][1]].setColor(c);
            boardState[endZonePositions[i][0]][endZonePositions[i][1]].setSafe(true);
            boardState[endZonePositions[i][0]][endZonePositions[i][1]].setEnd(true);
            boardState[endZonePositions[i][0]][endZonePositions[i][1]].setBackground(c);
            boardState[endZonePositions[i][0]][endZonePositions[i][1]].setBorder(BorderFactory.createStrokeBorder(new BasicStroke(frameWidth / 1000.0f)));
        }
    }

    private void initSlides(){
        //Adds 1st red slide squares
        for (int i = 11; i < 15; i++) {
            boardState[i][0].setBackground(Color.GREEN);
            greenSlide1.addSquare(boardState[i][0]);
        }
        //Adds 2nd set of green slide spaces
        greenSlide1.printPawns();
        for (int i = 2; i < 7; i++) {
            boardState[i][0].setBackground(Color.GREEN);
            greenSlide2.addSquare(boardState[i][0]);
        }
        //Adds 1st set of red slide spaces
        for (int i = 9; i < 14; i++) {
            boardState[0][i].setBackground(Color.RED);
            redSlide1.addSquare(boardState[0][i]);
        }
        //Adds 2nd set of red slide spaces
        for (int i = 1; i < 5; i++) {
            boardState[0][i].setBackground(Color.RED);
            redSlide2.addSquare(boardState[0][i]);
        }
        //Adds 1st set of yellow slide spaces
        for (int i = 2; i < 7; i++) {
            boardState[15][i].setBackground(Color.YELLOW);
            yellowSlide1.addSquare(boardState[15][i]);
        }
        //Adds 2nd set of yellow slide spaces
        for (int i = 11; i < 15; i++) {
            boardState[15][i].setBackground(Color.YELLOW);
            yellowSlide2.addSquare(boardState[15][i]);
        }
        //Adds 1st set of blue slide spaces
        for (int i = 9; i < 14; i++) {
            boardState[i][15].setBackground(Color.BLUE);
            blueSlide1.addSquare(boardState[i][15]);
        }
        //Adds 2nd set of blue slide spaces
        for (int i = 1; i < 5; i++) {
            boardState[i][15].setBackground(Color.BLUE);
            blueSlide2.addSquare(boardState[i][15]);
        }
    }

    private void initPawns(){
        // Define Pawns
        r0 = new Pawn("r0", Color.RED, "redPawn.png", cards);
        r1 = new Pawn("r1", Color.RED, "redPawn.png", cards);
        r2 = new Pawn("r2", Color.RED, "redPawn.png", cards);
        r3 = new Pawn("r3", Color.RED, "redPawn.png", cards);
        Pawn[] redPawns = new Pawn[] {r0,r1,r2,r3};
        placePawns(redStartZonePositions, redPawns);

        g0 = new Pawn("g0", Color.GREEN, "greenPawn.png", cards);
        g1 = new Pawn("g1", Color.GREEN, "greenPawn.png", cards);
        g2 = new Pawn("g2", Color.GREEN, "greenPawn.png", cards);
        g3 = new Pawn("g3", Color.GREEN, "greenPawn.png", cards);
        Pawn[] greenPawns = new Pawn[] {g0,g1,g2,g3};
        placePawns(greenStartZonePositions, greenPawns);

        y0 = new Pawn("g0", Color.YELLOW, "yellowPawn.png", cards);
        y1 = new Pawn("g1", Color.YELLOW, "yellowPawn.png", cards);
        y2 = new Pawn("g2", Color.YELLOW, "yellowPawn.png", cards);
        y3 = new Pawn("g3", Color.YELLOW, "yellowPawn.png", cards);
        Pawn[] yellowPawns = new Pawn[] {y0,y1,y2,y3};
        placePawns(yellowStartZonePositions, yellowPawns);

        b0 = new Pawn("g0", Color.BLUE, "bluePawn.png", cards);
        b1 = new Pawn("g1", Color.BLUE, "bluePawn.png", cards);
        b2 = new Pawn("g2", Color.BLUE, "bluePawn.png", cards);
        b3 = new Pawn("g3", Color.BLUE, "bluePawn.png", cards);
        Pawn[] bluePawns = new Pawn[] {b0,b1,b2,b3};
        placePawns(blueStartZonePositions, bluePawns);
    }

    // updates player details
    public void updatePlayers(){
        redPlayer = mainFrame.getSorryGame().getRedPlayer();
        redPlayer.setColor(Color.RED);

        greenPlayer = mainFrame.getSorryGame().getGreenPlayer();
        greenPlayer.setColor(Color.GREEN);

        yellowPlayer = mainFrame.getSorryGame().getYellowPlayer();
        yellowPlayer.setColor(Color.YELLOW);

        bluePlayer = mainFrame.getSorryGame().getBluePlayer();
        bluePlayer.setColor(Color.BLUE);

    }

    private void placePawns(int[][] startZonePositions, Pawn[] pawnArray){
        for (int i = 0; i < startZonePositions.length; i++){
            boardState[startZonePositions[i][0]][startZonePositions[i][1]].addPawn(pawnArray[i]);
        }
    }

    private  void sendHome(Pawn pawn){
        if(pawn.getcolor() == Color.BLUE){
            for(int i = 0; i < blueStartZonePositions.length; i++){
                int x = (blueStartZonePositions[i][0]);
                int y = (blueStartZonePositions [i][1]);
                if (boardState[x][y].getPawn() == null){
                    boardState[x][y].addPawn(pawn);
                    break;
                }
            }
        }
        if(pawn.getcolor() == Color.RED){
            for(int i = 0; i < redStartZonePositions.length; i++){
                int x = (redStartZonePositions[i][0]);
                int y = (redStartZonePositions [i][1]);
                if (boardState[x][y].getPawn() == null){
                    boardState[x][y].addPawn(pawn);
                    break;
                }
            }
        }
        if(pawn.getcolor() == Color.YELLOW){
            for(int i = 0; i < yellowStartZonePositions.length; i++){
                int x = (yellowStartZonePositions[i][0]);
                int y = (yellowStartZonePositions [i][1]);
                if (boardState[x][y].getPawn() == null){
                    boardState[x][y].addPawn(pawn);
                    break;
                }
            }
        }
        if(pawn.getcolor() == Color.GREEN){
            for(int i = 0; i < greenStartZonePositions.length; i++){
                int x = (greenStartZonePositions[i][0]);
                int y = (greenStartZonePositions [i][1]);
                if (boardState[x][y].getPawn() == null){
                    boardState[x][y].addPawn(pawn);
                    break;
                }
            }
        }
    }

    /**
     * Highlights squares that are possible getPosMoves destinations
     */
    private void showDestinations(ArrayList<BoardSquare> posDestlist) {
        ListIterator<BoardSquare> it = posDestlist.listIterator();
        while (it.hasNext())
            it.next().addPossibleDestination();
    }

    /**
     * Remove Highlight from previously selected squares
     */
    private void hideDestinations(ArrayList<BoardSquare> destlist) {
        ListIterator<BoardSquare> it = destlist.listIterator();
        while (it.hasNext())
            it.next().removePossibleDestination();
    }
    public  Player getTurn(Player p1,Player p2, Player p3, Player p4) {

            Player currentPlayer = null;
        try {
            if (p1.getTurncount() == p2.getTurncount() && p3.getTurncount() == p2.getTurncount()
                    && p3.getTurncount() == p4.getTurncount()) {
                currentPlayer = p1;
            }
            if (p1.getTurncount() > p2.getTurncount()) {
                currentPlayer = p2;
            }
            if (p2.getTurncount() > p3.getTurncount()) {
                currentPlayer = p3;
            }
            if (p3.getTurncount() > p4.getTurncount()) {
                currentPlayer = p4;
            }
            return currentPlayer;
        } catch (Exception e){

        }
        return currentPlayer;
    }

    /**
     * Overwrite mouse click method to select a board square
     * and display possible desinations for movement
     */
    @Override
    public void mouseClicked(MouseEvent arg0) {
        // Assign selectedSquare when clicked
        //System.out.println(getTurn( redPlayer,greenPlayer,yellowPlayer,bluePlayer).getName() + " its your turn");
        selectedSquare = (BoardSquare) arg0.getSource();
        if (prevSelectedSquare == null) {
            // If pawn is selected by user, highlight and show possible move destinations
            if (selectedSquare.getPawn() != null && !selectedSquare.isSelect()
                    && selectedSquare.getPawn().getcolor()== getTurn(mainFrame.getSorryGame().getRedPlayer(), mainFrame.getSorryGame().getYellowPlayer(),mainFrame.getSorryGame().getGreenPlayer(), mainFrame.getSorryGame().getBluePlayer()).getColor()  ) {
                selectedSquare.select();
                prevSelectedSquare = selectedSquare;
                destList.clear();
                destList = selectedSquare.getPawn().getPosMoves(boardState, selectedSquare.x, selectedSquare.y);
                showDestinations(destList);
            }
            // If pawn is already selected, unselect it
            else if (selectedSquare.getPawn() != null && selectedSquare.isSelect()) {
                System.out.println(getTurn(redPlayer, greenPlayer, yellowPlayer, bluePlayer).getColor());
                selectedSquare.deSelect();
                hideDestinations(destList);
                destList.clear();
                prevSelectedSquare = null;
                //S

            }
        }
             else {
            // If a highlighted possible destination is clicked
            // add Pawn to destination and remove pawn from previously selected tempSquare
            if (selectedSquare.isPosDest()) {
                // Update boardState
                boolean swap = false;
                if(selectedSquare.getPawn() != null && prevSelectedSquare.getPawn().swap == false){
                    sendHome(selectedSquare.getPawn());
                    selectedSquare.removePawn();
                }
                else if(selectedSquare.getPawn() != null && prevSelectedSquare.getPawn().swap == true){
                    Pawn tempPawn1 = prevSelectedSquare.getPawn();
                    Pawn tempPawn2 = selectedSquare.getPawn();
                    selectedSquare.removePawn();
                    prevSelectedSquare.removePawn();
                    prevSelectedSquare.addPawn(tempPawn2);
                    selectedSquare.addPawn(tempPawn1);
                    swap = true;

                }
                if(swap == false){
                    selectedSquare.addPawn(prevSelectedSquare.getPawn());
                }
                if (prevSelectedSquare.getPawn() != null && swap == false) {
                    prevSelectedSquare.removePawn();
                }
            }
            if (prevSelectedSquare != null) {
                prevSelectedSquare.deSelect();
                prevSelectedSquare = null;
            }


            hideDestinations(destList);
            destList.clear();

        }
        //testing turn count method
        //getTurn(redPlayer,greenPlayer, yellowPlayer,bluePlayer).setTurncount(getTurn(redPlayer,greenPlayer, yellowPlayer,bluePlayer).getTurncount() +1);
        //System.out.println(getTurn( redPlayer,greenPlayer, yellowPlayer,bluePlayer).getColor());

    }


    /**
     * Other Irrelevant abstract function. Only the Click Event is captured.
     */
    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }
}