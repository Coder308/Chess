package thomas.bartel.chessPieces;

import java.util.LinkedList;

/**
 * A position with a x coordinate and a y coordinate
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
        return Position.at(this.x + position.x, this.y + position.y);
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

    /**
     * A static method that finds every position that lays in a line between the
     * two given positions. This set also contains the two given positions
     * 
     * @param positionAt
     *            is the position that you start from
     * @param positionMove
     *            is the position that you want to go to
     * @return a set of every position that in a line between the two given
     *         positions. It also contains both given positionsS
     */
    public static LinkedList<Position> positionsInLine(Position positionAt, Position positionMove) {
        LinkedList<Position> positionsInLine = new LinkedList<Position>();
        positionsInLine.add(positionAt);
        Position delta = null;

        if (positionAt.x == positionMove.x) {
            for (int i = 0; i < 8; i++) {
                if (positionAt.plus(Position.at(0, i)).equals(positionMove)) {
                    delta = Position.at(0, 1);
                    break;
                } else if (positionAt.plus(Position.at(0, -i)).equals(positionMove)) {
                    delta = Position.at(0, -1);
                    break;
                }
            }

        } else if (positionAt.y == positionMove.y) {
            for (int i = 0; i < 8; i++) {
                if (positionAt.plus(Position.at(i, 0)).equals(positionMove)) {
                    delta = Position.at(1, 0);
                    break;
                } else if (positionAt.plus(Position.at(-i, 0)).equals(positionMove)) {
                    delta = Position.at(-1, 0);
                    break;
                }
            }

        } else if (positionAt.x < positionMove.x) {
            for (int i = 0; i < 8; i++) {
                if (positionAt.plus(Position.at(i, i)).equals(positionMove)) {
                    delta = Position.at(1, 1);
                    break;
                } else if (positionAt.plus(Position.at(i, -i)).equals(positionMove)) {
                    delta = Position.at(1, -1);
                    break;
                }
            }

        } else if (positionAt.x > positionMove.x) {
            for (int i = 0; i < 8; i++) {
                if (positionAt.plus(Position.at(-i, i)).equals(positionMove)) {
                    delta = Position.at(-1, 1);
                    break;
                } else if (positionAt.plus(Position.at(-i, -i)).equals(positionMove)) {
                    delta = Position.at(-1, -1);
                    break;
                }
            }
        }

        Position savePosition = delta;

        while (!positionsInLine.contains(positionMove)) {
            positionsInLine.add(positionAt.plus(delta));

            delta = delta.plus(savePosition);
        }

        return positionsInLine;
    }

}