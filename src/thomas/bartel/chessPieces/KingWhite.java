package thomas.bartel.chessPieces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import javax.imageio.ImageIO;

/**
 * A white king chess piece
 * 
 * @author Thomas Bartel
 *
 */
public class KingWhite implements ChessPiece {
    /**
     * Is the current position of the king on the board
     */
    private Position position;
    /**
     * Is the image that represents the king on the board
     */
    private BufferedImage representerImage;

    /**
     * The constructor for a white king chess piece
     * 
     * @param position
     *            is the current position of the white king on the board
     */
    public KingWhite(Position position) {
        this.position = position;

        try {
            this.representerImage = ImageIO.read(new File("Images/KingWhite.png"));
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
        return null;
    }

}
