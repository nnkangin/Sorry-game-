/**
 * @author Max Marder
 */

// Imports
package Sorry;

import javax.swing.*;
import java.awt.*;

public class SafeZoneSquare extends BoardSquare {

    // Member Variables
    protected Color color;

    // Constructors
    public SafeZoneSquare(int x, int y, Pawn p,Color color) {
        this.x = x;
        this.y = y;
        this.p = p;
        this.setColor(color);

        setLayout(new BorderLayout());

        if (p != null)
            addPawn(p);
    }




    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
