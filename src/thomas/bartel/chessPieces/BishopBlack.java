package thomas.bartel.chessPieces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

/**
 * A black bishop chess piece
 * 
 * @author Thomas Bartel
 *
 */
public class BishopBlack implements ChessPiece {
    /**
     * Is the current position of the bishop on the board
     */
    private Position position;
    /**
     * Is the image that represents the bishop on the board
     */
    private BufferedImage representerImage;

    /**
     * The constructor for a black bishop chess piece
     * 
     * @param position
     *            is the current position of the black bishop on the board
     */
    public BishopBlack(Position position) {
        this.position = position;

        try {
            this.representerImage = ImageIO.read(new File("Images/BishopBlack.png"));
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

        for (int i = 0; i < 8; i++) {
            validMovePositions.add(this.position.plus(Position.at(i, i)));
            validMovePositions.add(this.position.plus(Position.at(-i, i)));
            validMovePositions.add(this.position.plus(Position.at(i, -i)));
            validMovePositions.add(this.position.plus(Position.at(-i, -i)));
        }

        return validMovePositions;
    }

}
