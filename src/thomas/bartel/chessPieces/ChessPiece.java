package thomas.bartel.chessPieces;

import java.awt.image.BufferedImage;
import java.util.Set;

/**
 * A chess piece on a chess board
 * 
 * @author Thomas Bartel
 *
 */
public interface ChessPiece {

    /**
     * A getter-method for the current position of the chess piece on the board
     * 
     * @return the current position of the chess piece on the board
     */
    Position getPosition();

    /**
     * A setter-method for the current position of the chess piece on the board
     * 
     * @param position
     *            is the new current position of the chess piece on the board
     */
    void setPosition(Position position);

    /**
     * A getter-method for the image that represents the chess piece on the
     * board
     * 
     * @return the image that represents the chess piece on the board
     */
    BufferedImage getRepresenterImage();

    /**
     * A method to find every valid position for the chess piece to move to
     * 
     * @return a set of positions who are a valid place on the board for the
     *         chess piece to move to
     */
    Set<Position> getValidMovePositions();

    /**
     * A method to get the side indicator of a chess piece
     * 
     * @return 0 if the chess piece is white. 1 if the chess piece is black
     */
    int getSideIndicator();

}
