package Sorry;

// Imports
import javax.swing.*;
import java.awt.*;

// Sorry Board Class
public class BoardGUI extends JPanel {

    // Variable Declaration
    private Board sorryBoard;
    private static final int boardLength = 16;
    private static final int boardHeight = 16;
    public static final int frameWidth = 600;
    public static final int frameHeight = 600;

    // Constructor
    public BoardGUI(Board inSorryBoard) {
        sorryBoard = inSorryBoard;
        setSize(frameWidth, frameHeight);
        this.setLayout(new GridLayout(boardLength, boardHeight));
        this.setMinimumSize(new Dimension(600, 400));
        updateBoard();
    }

    /** Update board GUI with current board state */
    private void updateBoard() {
        this.removeAll();
        for (int i = 0; i < boardLength; i++) {
            for (int j = 0; j < boardHeight; j++) {
                this.add(sorryBoard.boardState[i][j]);
            }
        }
    }
}