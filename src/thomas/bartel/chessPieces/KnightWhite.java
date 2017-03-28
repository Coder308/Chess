package thomas.bartel.chessPieces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

/**
 * A white knight chess piece
 * 
 * @author Thomas Bartel
 *
 */
public class KnightWhite implements ChessPiece {
    /**
     * Is the current position of the knight on the board
     */
    private Position position;
    /**
     * Is the image that represents the knight on the board
     */
    private BufferedImage representerImage;

    /**
     * The constructor for a white knight chess piece
     * 
     * @param position
     *            is the current position of the knight on the board
     */
    public KnightWhite(Position position) {
        this.position = position;

        try {
            this.representerImage = ImageIO.read(new File("Images/KnightWhite.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
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

}
