package thomas.bartel.chessPieces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

/**
 * A white pawn chess piece
 * 
 * @author Thomas Bartel
 *
 */
public class PawnWhite implements ChessPiece {
    /**
     * Is the current position of the pawn on the board
     */
    private Position position;
    /**
     * Is the image that represents the pawn on the board
     */
    private BufferedImage representerImage;
    /**
     * Is a set of positions that the white pawn can go to
     */
    private Set<Position> validMovePositions = new HashSet<Position>();

    /**
     * The constructor of a white pawn
     * 
     * @param position
     *            is the current position of the pawn on the board
     */
    public PawnWhite(Position position) {
        this.position = position;

        try {
            this.representerImage = ImageIO.read(new File("Images/PawnWhite.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.validMovePositions.add(Position.at(this.position.x, this.position.y - 1));
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
