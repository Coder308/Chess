package thomas.bartel.chessPieces;

import java.awt.image.BufferedImage;
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
     * Is the number that indicates the side of the chess piece
     */
    private int sideIndicator;

    /**
     * The constructor of a white pawn
     * 
     * @param position
     *            is the current position of the pawn on the board
     */
    public PawnWhite(Position position) {
        this.position = position;
        this.sideIndicator = 0;

        try { 
            this.representerImage = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("chessImages/PawnWhite.png"));
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

        validMovePositions.add(this.position.plus(Position.at(0, -1)));

        return validMovePositions;
    }

    @Override
    public int getSideIndicator() {
        return this.sideIndicator;
    }

    /**
     * A method that find every position that the white pawn can got to if the
     * position that the pawn is supposed to go to contains an enemy chess piece
     * 
     * @return a set of positions that contains every position that the white
     *         pawn can go to if the supposed position contains an enemy chess
     *         piece
     */
    public Set<Position> getEnemyValidMovePositions() {
        Set<Position> validMovePositions = new HashSet<Position>();

        validMovePositions.add(this.position.plus(Position.at(-1, -1)));
        validMovePositions.add(this.position.plus(Position.at(1, -1)));

        return validMovePositions;
    }

    /**
     * A method that finds every valid position for the white pawn to go to
     * before it moved once
     * 
     * @return a set of valid positions for the white pawn before it mades its
     *         first move
     */
    public Set<Position> getFirstValidMovePositions() {
        Set<Position> validMovePositions = new HashSet<Position>();

        validMovePositions.add(this.position.plus(Position.at(0, -1)));
        validMovePositions.add(this.position.plus(Position.at(0, -2)));

        return validMovePositions;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (other instanceof PawnWhite) {
            return true;
        } else {
            return false;
        }
    }


}
