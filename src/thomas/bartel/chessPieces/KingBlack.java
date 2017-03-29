package thomas.bartel.chessPieces;

import java.awt.image.BufferedImage;
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
     * Is the number that indicates the side of the chess piece
     */
    private int sideIndicator;

    /**
     * The constructor for a black king chess piece
     * 
     * @param position
     *            is the current position of the black king on the board
     */
    public KingBlack(Position position) {
        this.position = position;
        this.sideIndicator = 1;

        try {
            this.representerImage = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("chessImages/KingBlack.png"));
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

    @Override
    public int getSideIndicator() {
        return this.sideIndicator;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (other instanceof KingBlack) {
            return true;
        } else {
            return false;
        }
    }


}
