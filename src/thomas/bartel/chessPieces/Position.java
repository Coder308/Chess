package thomas.bartel.chessPieces;

/**
 * A position with a x coordinate and a y coordinates
 * 
 * @author Thomas Bartel
 *
 */
public class Position {
    /**
     * Is the x coordinate of the position
     */
    public final int x;
    /**
     * Is the y coordinate of the positions
     */
    public final int y;

    /**
     * The constructor of a position
     * 
     * @param x
     *            is the x coordinate of the position
     * @param y
     *            is the y coordinate of the position
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * A static method that returns a position with the given coordinates
     * 
     * @param x
     *            is the x coordinate of the position
     * @param y
     *            is the y coordinate of the position
     * @return a new position with the given coordinates
     */
    public static Position at(int x, int y) {
        return new Position(x, y);
    }

    /**
     * A method that adds a position to this position
     * 
     * @param position
     *            is the position that you want to add
     * @return a new position whose coordinates are the sum of its own
     *         coordinates and the coordinates of the given position
     */
    public Position plus(Position position) {
        return Position.at(this.x + x, this.y + y);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Position) {
            Position position = (Position) other;

            if (this.x == position.x && this.y == position.y) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.x + this.y;
    }

    /**
     * Turns a given position into the equivalent index of the label on the
     * chess board
     * 
     * @param position
     *            is the position on the chess board
     * @return the index of the label on the chess board at the given position
     */
    public static int PositionToIndex(Position position) {
        return 8 * position.y + position.x;
    }

    /**
     * Turns a given index into the equivalent position of the label on the
     * chess board
     * 
     * @param index
     *            is the index of a label on the chess board
     * @return the position of the label with the given index on the chess board
     */
    public static Position IndexToPosition(int index) {
        int y = 0;
        if (index < 64 && index > 55) {
            y = 7;
        } else if (index < 56 && index > 47) {
            y = 6;
        } else if (index < 48 && index > 39) {
            y = 5;
        } else if (index < 40 && index > 31) {
            y = 4;
        } else if (index < 32 && index > 23) {
            y = 3;
        } else if (index < 24 && index > 15) {
            y = 2;
        } else if (index < 16 && index > 7) {
            y = 1;
        } else {
            y = 0;
        }

        return Position.at(index - 8 * y, y);
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

}
