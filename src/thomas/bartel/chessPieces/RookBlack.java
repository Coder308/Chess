package thomas.bartel.chessPieces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

/**
 * A black rook chess piece
 * 
 * @author Thomas Bartel
 *
 */
public class RookBlack implements ChessPiece {
    /**
     * Is the current position of the rook on the board
     */
    private Position position;
    /**
     * Is the image that represents the rook on the board
     */
    private BufferedImage representerImage;
    /**
     * Is a set of positions that the black rook can go to
     */
    private Set<Position> validMovePositions = new HashSet<Position>();

    /**
     * The constructor for a black rook chess piece
     * 
     * @param position
     *            is the current position of the black rook on the board
     */
    public RookBlack(Position position) {
        this.position = position;

        try {
            this.representerImage = ImageIO.read(new File("Images/RookBlack.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.initValidMovePositions();
    }

    /**
     * Fills the set with every valid position for the black rook to go to
     */
    private void initValidMovePositions() {
        for (int i = 0; i < 8; i++) {
            this.validMovePositions.add(this.position.plus(Position.at(this.position.x, i)));
            this.validMovePositions.add(this.position.plus(Position.at(this.position.x, -i)));
            this.validMovePositions.add(this.position.plus(Position.at(i, this.position.y)));
            this.validMovePositions.add(this.position.plus(Position.at(-i, this.position.y)));
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
