/**
 * @author Max Marder
 */

// IMPORTS
package Sorry;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * The board square class is a JPanel tile that makes up the game board
 * the board square has parameters for location, safety, color, selection, and pawns
 */
public class BoardSquare extends JPanel implements Serializable {

    // Variable Declaration
    private static final long serialVersionUID = 1L;
    int x, y;
    protected JLabel content;
    protected Pawn p;
    protected boolean isSelect;
    protected boolean isPosDest;
    private boolean isSafe;
    private boolean isStart;
    private boolean isEnd;
    private Color color;

    // Constructors
    public BoardSquare() {
         x= 0;
        y= 0;
        p = null;
    }
    public BoardSquare(int x, int y, Pawn p) {
        this.x = x;
        this.y = y;
        this.p = p;

        setLayout(new BorderLayout());

        if (p != null)
            addPawn(p);
    }
    public boolean isFilled(){
        if (this.p != null){
            return true;
        }
        return false;
    }

    // Piece Getter
    public Pawn getPawn() {
        return this.p;
    }

    // Set Icon for Piece in BoardSquare
    public void addPawn(Pawn p) {
        ImageIcon icon = new javax.swing.ImageIcon(this.getClass().getResource(p.getPath()));
        content = new JLabel(icon);
        this.add(content);
        this.p = p;
    }
    // Remove Pawn in BoardSquare
    public void removePawn() {
        p = null;
        this.remove(content);
    }

    // Mark square with red border when selected
    public void select(){
        this.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        this.isSelect = true;
    }
    // Remove selection border
    public void deSelect(){
        this.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(Board.frameWidth / 1000.0f)));
        this.isSelect = false;
    }
    public boolean isSelect(){
        return this.isSelect;
    }
    public boolean isPosDest(){
        return this.isPosDest;
    }
    // Highlight a square to indicate that it is a possible getPosMoves
    public void addPossibleDestination()
    {
        this.setBorder(BorderFactory.createLineBorder(Color.BLUE, 4));
        this.isPosDest = true;
        this.revalidate();
        this.repaint();
    }
    // Unhighlight previously highlighted squares
    public void removePossibleDestination()
    {
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.isPosDest = false;
    }

    public boolean isSafe() {
        return isSafe;
    }

    public void setSafe(boolean safe) {
        isSafe = safe;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
