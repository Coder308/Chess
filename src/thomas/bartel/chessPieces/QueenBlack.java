package thomas.bartel.chessPieces;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

/**
 * A black queen chess piece
 * 
 * @author Thomas Bartel
 *
 */
public class QueenBlack implements ChessPiece {
    /**
     * Is the current position of the queen on the board
     */
    private Position position;
    /**
     * Is the image that represents the queen on the board
     */
    private BufferedImage representerImage;

    /**
     * The constructor for a black queen chess piece
     * 
     * @param position
     *            is the current position of the black queen on the board
     */
    public QueenBlack(Position position) {
        this.position = position;

        try {
            this.representerImage = ImageIO.read(new File("Images/QueenBlack.png"));
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
            validMovePositions.add(this.position.plus(Position.at(0, i)));
            validMovePositions.add(this.position.plus(Position.at(0, -i)));
            validMovePositions.add(this.position.plus(Position.at(i, 0)));
            validMovePositions.add(this.position.plus(Position.at(-i, 0)));
        }

        return validMovePositions;
    }

}
