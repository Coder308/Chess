package thomas.bartel.chessPieces;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

/**
 * A black pawn chess piece
 * 
 * @author Thomas Bartel
 *
 */
public class PawnBlack implements ChessPiece {
    /**
     * Is the current position of the pawn on the board
     */
    private Position position;
    /**
     * Is the image that represents the pawn on the board
     */
    private BufferedImage representerImage;

    /**
     * The constructor of a black pawn
     * 
     * @param position
     *            is the current position of the pawn on the board
     */
    public PawnBlack(Position position) {
        this.position = position;

        try {
            this.representerImage = ImageIO.read(new File("Images/PawnBlack.png"));
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

        validMovePositions.add(this.position.plus(Position.at(0, 1)));

        return validMovePositions;
    }

}
