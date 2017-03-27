package thomas.bartel.chessPieces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

/**
 * A white bishop chess piece
 * 
 * @author Thomas Bartel
 *
 */
public class BishopWhite implements ChessPiece {
    /**
     * Is the current position of the bishop on the board
     */
    private Position position;
    /**
     * Is the image that represents the bishop on the board
     */
    private BufferedImage representerImage;
    /**
     * Is a set of positions that the white bishop can go to
     */
    private Set<Position> validMovePositions = new HashSet<Position>();

    /**
     * The constructor for a white bishop chess piece
     * 
     * @param position
     *            is the current position of the white bishop on the board
     */
    public BishopWhite(Position position) {
        this.position = position;

        try {
            this.representerImage = ImageIO.read(new File("Images/BishopWhite.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.initValidMovePositions();
    }

    /**
     * Fills the set with every valid position for the white bishop to go to
     */
    private void initValidMovePositions() {
        for (int i = 0; i < 8; i++) {
            this.validMovePositions.add(this.position.plus(Position.at(i, i)));
            this.validMovePositions.add(this.position.plus(Position.at(-i, i)));
            this.validMovePositions.add(this.position.plus(Position.at(i, -i)));
            this.validMovePositions.add(this.position.plus(Position.at(-i, -i)));
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
        return this.validMovePositions;
    }

}
