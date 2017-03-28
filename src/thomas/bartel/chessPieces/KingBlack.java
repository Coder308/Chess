package thomas.bartel.chessPieces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

/**
 * A black king chess piece
 * 
 * @author Thomas Bartel
 *
 */
public class KingBlack implements ChessPiece {
    /**
     * Is the current position of the king on the board
     */
    private Position position;
    /**
     * Is the image that represents the king on the board
     */
    private BufferedImage representerImage;

    /**
     * The constructor for a black king chess piece
     * 
     * @param position
     *            is the current position of the black king on the board
     */
    public KingBlack(Position position) {
        this.position = position;

        try {
            this.representerImage = ImageIO.read(new File("Images/KingBlack.png"));
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

        for (int i = 0; i <= 1; i++) {
            for (int a = 0; a <= 1; a++) {
                validMovePositions.add(this.position.plus(Position.at(i, a)));
                validMovePositions.add(this.position.plus(Position.at(-i, -a)));
                validMovePositions.add(this.position.plus(Position.at(-i, a)));
                validMovePositions.add(this.position.plus(Position.at(i, -a)));
            }
        }

        return validMovePositions;
    }

}
