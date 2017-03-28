package thomas.bartel.game;

import java.awt.*;
import javax.swing.*;
import thomas.bartel.chessPieces.*;

/**
 * A chess board for a game of chess
 * 
 * @author Thomas Bartel
 *
 */
public class ChessBoard {
    /**
     * Is a panel with a gridlayout that matches a real chess board
     */
    private JPanel board = new JPanel(new GridLayout(8, 8));
    /**
     * Is a multidimensional array that contains every chess pieces that is
     * currently in the game
     */
    private ChessPiece[][] chessPiecesOnBoard;
    /**
     * Is a mutlidimensional array that contains every label that is on the
     * panel
     */
    private JLabel[] labels = new JLabel[64];
    /**
     * Is the frame of the chess game
     */
    private JFrame frame = new JFrame();

    /**
     * The constructor of a chess board
     */
    public ChessBoard() {
        this.initChessPieces();
        this.initLables();
        this.initBoard();
        this.frame.setSize(1000, 1000);
        this.frame.add(this.board);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

    /**
     * Initializes the multidimensional array that contains every chess piece in
     * the game. The chess piece are arranged in the same way as it would be in
     * a real chess match
     */
    private void initChessPieces() {
        this.chessPiecesOnBoard = new ChessPiece[8][8];

        for (int i = 0; i < 8; i++) {
            this.chessPiecesOnBoard[1][i] = new PawnBlack(Position.at(i, 1));
            this.chessPiecesOnBoard[6][i] = new PawnWhite(Position.at(i, 6));
        }
        this.initFirstBlackRow();
        this.initFirstWhiteRow();
    }

    /**
     * Initializes the chess board matching the looks of a real chess board at
     * the start of a game
     */
    private void initBoard() {
        for (int i = 0; i < this.chessPiecesOnBoard.length; i++) {
            for (int a = 0; a < this.chessPiecesOnBoard[0].length; a++) {
                int index = Position.PositionToIndex(Position.at(a, i));

                if (index < 16 || index > 47) {
                    this.labels[index].setIcon(new ImageIcon(this.chessPiecesOnBoard[i][a].getRepresenterImage()));
                }

                this.labels[index].setOpaque(true);

                if (i % 2 != 0) {
                    if (a % 2 != 0) {
                        this.labels[index].setBackground(Color.WHITE);
                    } else {
                        this.labels[index].setBackground(Color.BLACK);
                    }
                } else {
                    if (a % 2 != 0) {
                        this.labels[index].setBackground(Color.BLACK);
                    } else {
                        this.labels[index].setBackground(Color.WHITE);
                    }
                }

                board.add(this.labels[index]);
            }
        }

    }

    /**
     * Initializes the first row of chess pieces on the black players side
     */
    private void initFirstBlackRow() {
        this.chessPiecesOnBoard[0][0] = new RookBlack(Position.at(0, 0));
        this.chessPiecesOnBoard[0][1] = new KnightBlack(Position.at(1, 0));
        this.chessPiecesOnBoard[0][2] = new BishopBlack(Position.at(2, 0));
        this.chessPiecesOnBoard[0][3] = new QueenBlack(Position.at(3, 0));
        this.chessPiecesOnBoard[0][4] = new KingBlack(Position.at(4, 0));
        this.chessPiecesOnBoard[0][5] = new BishopBlack(Position.at(5, 0));
        this.chessPiecesOnBoard[0][6] = new KnightBlack(Position.at(6, 0));
        this.chessPiecesOnBoard[0][7] = new RookBlack(Position.at(7, 0));
    }

    /**
     * Initializes the first row of chess pieces on the white players side
     */
    private void initFirstWhiteRow() {
        this.chessPiecesOnBoard[7][0] = new RookWhite(Position.at(0, 7));
        this.chessPiecesOnBoard[7][1] = new KnightWhite(Position.at(1, 7));
        this.chessPiecesOnBoard[7][2] = new BishopWhite(Position.at(2, 7));
        this.chessPiecesOnBoard[7][3] = new QueenWhite(Position.at(3, 7));
        this.chessPiecesOnBoard[7][4] = new KingWhite(Position.at(4, 7));
        this.chessPiecesOnBoard[7][5] = new BishopWhite(Position.at(5, 7));
        this.chessPiecesOnBoard[7][6] = new KnightWhite(Position.at(6, 7));
        this.chessPiecesOnBoard[7][7] = new RookWhite(Position.at(7, 7));
    }

    /**
     * Fills the multidimensional array of labels with 64 labels, matching the
     * amount of squares on a chess board
     */
    private void initLables() {
        for (int i = 0; i < this.labels.length; i++) {
            labels[i] = new JLabel();
        }
    }

    /**
     * A getter-method for the array of labels on the chess board
     * 
     * @return an array of the labels that are currently on the panel
     */
    public JLabel[] getLabels() {
        return this.labels;
    }

    /**
     * Moves a chess piece at a certain position to another position
     * 
     * @param positionAt
     *            is the position that the chess piece is currently at
     * @param positionMove
     *            is the position that the chess piece should move to
     */
    public void moveChessPiece(Position positionAt, Position positionMove) {
        if (this.containsChessPiece(positionAt)) {
            ChessPiece piece = this.chessPiecesOnBoard[positionAt.y][positionAt.x];

            if (piece.getValidMovePositions().contains(positionMove)) {
                this.chessPiecesOnBoard[positionAt.y][positionAt.x] = null;
                this.chessPiecesOnBoard[positionMove.y][positionMove.x] = piece;
                piece.setPosition(Position.at(positionMove.x, positionMove.y));
                this.labels[Position.PositionToIndex(positionMove)].setIcon(new ImageIcon(piece.getRepresenterImage()));
                this.labels[Position.PositionToIndex(positionAt)].setIcon(null);
            }

        }

    }

    /**
     * A helper method to determine if there exists a chess piece at the given
     * position
     * 
     * @param positionAt
     *            is the position where the method searches for a chess piece
     * @return true if there is a chess piece at the given position
     */
    private boolean containsChessPiece(Position positionAt) {
        if (this.chessPiecesOnBoard[positionAt.y][positionAt.x] != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A helper method to determine if the given position is on the chess board
     * 
     * @param position
     *            is the position that the method checks
     * @return true if the given position is in the boundaries of the chess
     *         board
     */
    private boolean containsPosition(Position position) {
        if (position.y < this.chessPiecesOnBoard.length && position.x < this.chessPiecesOnBoard[0].length) {
            if (position.y > 0 && position.x > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * A helper method to move a pawn piece. It is used because there are some
     * special rules that don't apply for any of the other chess pieces
     * 
     * @param positionAt
     *            is the position that the pawn is currently at
     * @param positionMove
     *            is the position that the pawn is supposed to be moved to
     */
    private void movePawnPiece(Position positionAt, Position positionMove) {

    }

}
