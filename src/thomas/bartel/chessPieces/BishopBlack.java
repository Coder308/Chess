package thomas.bartel.chessPieces;

import java.awt.image.BufferedImage;
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
     * Is the number that indicates the side of the chess piece
     */
    private int sideIndicator;

    /**
     * The constructor for a black bishop chess piece
     * 
     * @param position
     *            is the current position of the black bishop on the board
     */
    public BishopBlack(Position position) {
        this.position = position;
        this.sideIndicator = 1;

        try {
            this.representerImage = ImageIO
                    .read(this.getClass().getClassLoader().getResourceAsStream("chessImages/BishopBlack.png"));
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

        for (int i = 0; i < 8; i++) {
            validMovePositions.add(this.position.plus(Position.at(i, i)));
            validMovePositions.add(this.position.plus(Position.at(-i, i)));
            validMovePositions.add(this.position.plus(Position.at(i, -i)));
            validMovePositions.add(this.position.plus(Position.at(-i, -i)));
        }

        return validMovePositions;
    }

    @Override
    public int getSideIndicator() {
        return this.sideIndicator;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (other instanceof BishopBlack) {
            return true;
        } else {
            return false;
        }
    }

}
