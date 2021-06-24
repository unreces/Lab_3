import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This inner class handles mouse events in the main grid of map cells, by
 * modifying the cells based on the mouse button state and the initial edit
 * that was performed.
 **/
class MapCellHandler implements MouseListener {
    /**
     * This value will be true if a mouse button has been pressed and we are
     * currently in the midst of a modification operation.
     **/
    private boolean modifying;

    /**
     * This value records whether we are making cells passable or
     * impassable.  Which it is depends on the original state of the cell
     * that the operation was started within.
     **/
    private boolean makePassable;

    /**
     * Initiates the modification operation.
     **/
    public void mousePressed(MouseEvent e) {
        modifying = true;

        JMapCell cell = (JMapCell) e.getSource();

        // If the current cell is passable then we are making them
        // impassable; if it's impassable then we are making them passable.

        makePassable = !cell.isPassable();

        cell.setPassable(makePassable);
    }

    /**
     * Ends the modification operation.
     **/
    public void mouseReleased(MouseEvent e) {
        modifying = false;
    }

    /**
     * If the mouse has been pressed, this continues the modification
     * operation into the new cell.
     **/
    public void mouseEntered(MouseEvent e) {
        if (modifying) {
            JMapCell cell = (JMapCell) e.getSource();
            cell.setPassable(makePassable);
        }
    }

    /**
     * Not needed for this handler.
     **/
    public void mouseExited(MouseEvent e) {
        // This one we ignore.
    }

    /**
     * Not needed for this handler.
     **/
    public void mouseClicked(MouseEvent e) {
        // And this one too.
    }
}
