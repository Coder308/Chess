package thomas.bartel.chessPieces;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

/**
 * A black knight chess piece
 * 
 * @author Thomas Bartel
 *
 */
public class KnightBlack implements ChessPiece {

    /**
     * Is the current position of the knight on the board
     */
    private Position position;
    /**
     * Is the image that represents the knight on the board
     */
    private BufferedImage representerImage;
    /**
     * Is the number that indicates the side of the chess piece
     */
    private int sideIndicator;

    /**
     * The constructor for a black knight chess piece
     * 
     * @param position
     *            is the current position of the knight on the board
     */
    public KnightBlack(Position position) {
        this.position = position;
        this.sideIndicator = 1;

        try {
            this.representerImage = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("chessImages/KnightBlack.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public BufferedImage getRepresenterImage() {
        return this.representerImage;
    }

    @Override
    public Set<Position> getValidMovePositions() {
        Set<Position> validMovePositions = new HashSet<Position>();

        validMovePositions.add(this.position.plus(Position.at(-2, 1)));
        validMovePositions.add(this.position.plus(Position.at(-2, -1)));
        validMovePositions.add(this.position.plus(Position.at(-1, -2)));
        validMovePositions.add(this.position.plus(Position.at(-1, 2)));
        validMovePositions.add(this.position.plus(Position.at(2, -1)));
        validMovePositions.add(this.position.plus(Position.at(2, 1)));
        validMovePositions.add(this.position.plus(Position.at(1, -2)));
        validMovePositions.add(this.position.plus(Position.at(1, 2)));

        return validMovePositions;
    }

    @Override
    public int getSideIndicator() {
        return this.sideIndicator;
    }
}
