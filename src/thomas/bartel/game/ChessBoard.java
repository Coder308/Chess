package thomas.bartel.game;

import java.awt.*;
import java.util.LinkedList;

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
     * Is a counter for the amount of rounds played;
     */
    private int roundCounter;
    /**
     * Is the original color of the label that is chosen right now
     */
    private Color backgroundColor;
    /**
     * Is a panel that the board panel will be placed on
     */
    private JPanel window = new JPanel(new BorderLayout());
    /**
     * Is a label that shows whose turn it is
     */
    private JLabel turnIndicator;

    /**
     * The constructor of a chess board
     */
    public ChessBoard() {
        this.initChessPieces();
        this.initLables();
        this.initBoard();
        this.initWindow();
        this.frame.setSize(1000, 1000);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setTitle("A simple game of chess");
        this.frame.setVisible(true);
        this.roundCounter = 0;
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
     * Initializes the windowpanel where the turnindicatorlabel and the
     * boardpanel are located
     */
    private void initWindow() {
        this.turnIndicator = new JLabel("The white side begins!");
        this.window.add(this.turnIndicator, BorderLayout.NORTH);
        this.window.add(this.board, BorderLayout.CENTER);
        this.frame.add(this.window);

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
        ChessPiece[][] copyBoard = this.copyBoard(this.chessPiecesOnBoard);

        if (this.containsChessPiece(positionAt)) {
            ChessPiece piece = this.chessPiecesOnBoard[positionAt.y][positionAt.x];

            if (piece.getSideIndicator() % 2 == this.roundCounter % 2) {

                if (piece instanceof PawnWhite) {
                    this.movePawnWhitePiece(positionAt, positionMove, (PawnWhite) piece);
                } else if (piece instanceof PawnBlack) {
                    this.movePawnBlackPiece(positionAt, positionMove, (PawnBlack) piece);
                } else if (piece instanceof KnightWhite || piece instanceof KnightBlack) {
                    this.moveKnightPieces(positionAt, positionMove, piece);
                } else {
                    this.moveOtherPieces(positionAt, positionMove, piece);
                }
            }
        }

        if (!this.equalsBoard(this.chessPiecesOnBoard, copyBoard)) {
            this.roundCounter++;
        }

        if (this.roundCounter % 2 != 0) {
            this.turnIndicator.setText("It's the black sides turn!");
        } else {
            this.turnIndicator.setText("It's the white sides turn!");
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
     * A helper method to move a white pawn piece. It is used because there are
     * some special rules that don't apply for any of the other chess pieces
     * 
     * @param positionAt
     *            is the position that the white pawn is currently at
     * @param positionMove
     *            is the position that the white pawn is supposed to be moved to
     * @param pawn
     *            is the white pawn chess piece
     */
    private void movePawnWhitePiece(Position positionAt, Position positionMove, PawnWhite pawn) {
        positionMove = this.correctPositionMove(positionAt, positionMove);

        if (this.containsChessPiece(positionMove)) {

            if (pawn.getEnemyValidMovePositions().contains(positionMove) && pawn
                    .getSideIndicator() != this.chessPiecesOnBoard[positionMove.y][positionMove.x].getSideIndicator()) {

                this.chessPiecesOnBoard[positionAt.y][positionAt.x] = null;
                this.chessPiecesOnBoard[positionMove.y][positionMove.x] = pawn;
                pawn.setPosition(Position.at(positionMove.x, positionMove.y));
                this.labels[Position.PositionToIndex(positionMove)].setIcon(new ImageIcon(pawn.getRepresenterImage()));
                this.labels[Position.PositionToIndex(positionAt)].setIcon(null);

            }

        } else if (positionAt.y == 6) {

            if (pawn.getFirstValidMovePositions().contains(positionMove)) {

                this.chessPiecesOnBoard[positionAt.y][positionAt.x] = null;
                this.chessPiecesOnBoard[positionMove.y][positionMove.x] = pawn;
                pawn.setPosition(Position.at(positionMove.x, positionMove.y));
                this.labels[Position.PositionToIndex(positionMove)].setIcon(new ImageIcon(pawn.getRepresenterImage()));
                this.labels[Position.PositionToIndex(positionAt)].setIcon(null);

            }

        } else {

            if (pawn.getValidMovePositions().contains(positionMove)) {

                this.chessPiecesOnBoard[positionAt.y][positionAt.x] = null;
                this.chessPiecesOnBoard[positionMove.y][positionMove.x] = pawn;
                pawn.setPosition(Position.at(positionMove.x, positionMove.y));
                this.labels[Position.PositionToIndex(positionMove)].setIcon(new ImageIcon(pawn.getRepresenterImage()));
                this.labels[Position.PositionToIndex(positionAt)].setIcon(null);
            }

        }

        if (pawn.getPosition().y == 0) {
            this.chessPiecesOnBoard[pawn.getPosition().y][pawn.getPosition().x] = new QueenWhite(pawn.getPosition());
            this.labels[Position.PositionToIndex(pawn.getPosition())].setIcon(new ImageIcon(
                    this.chessPiecesOnBoard[pawn.getPosition().y][pawn.getPosition().x].getRepresenterImage()));
        }
    }

    /**
     * A helper method to move a black pawn piece. It is used because there are
     * some special rules that don't apply for any of the other chess pieces
     * 
     * @param positionAt
     *            is the position that the black pawn is currently at
     * @param positionMove
     *            is the position that the black pawn is supposed to be moved to
     * @param pawn
     *            is the black pawn chess piece
     */
    private void movePawnBlackPiece(Position positionAt, Position positionMove, PawnBlack pawn) {
        positionMove = this.correctPositionMove(positionAt, positionMove);

        if (this.containsChessPiece(positionMove)) {

            if (pawn.getEnemyValidMovePositions().contains(positionMove) && pawn
                    .getSideIndicator() != this.chessPiecesOnBoard[positionMove.y][positionMove.x].getSideIndicator()) {

                this.chessPiecesOnBoard[positionAt.y][positionAt.x] = null;
                this.chessPiecesOnBoard[positionMove.y][positionMove.x] = pawn;
                pawn.setPosition(Position.at(positionMove.x, positionMove.y));
                this.labels[Position.PositionToIndex(positionMove)].setIcon(new ImageIcon(pawn.getRepresenterImage()));
                this.labels[Position.PositionToIndex(positionAt)].setIcon(null);

            }

        } else if (positionAt.y == 1) {

            if (pawn.getFirstValidMovePositions().contains(positionMove)) {

                this.chessPiecesOnBoard[positionAt.y][positionAt.x] = null;
                this.chessPiecesOnBoard[positionMove.y][positionMove.x] = pawn;
                pawn.setPosition(Position.at(positionMove.x, positionMove.y));
                this.labels[Position.PositionToIndex(positionMove)].setIcon(new ImageIcon(pawn.getRepresenterImage()));
                this.labels[Position.PositionToIndex(positionAt)].setIcon(null);

            }

        } else {

            if (pawn.getValidMovePositions().contains(positionMove)) {

                this.chessPiecesOnBoard[positionAt.y][positionAt.x] = null;
                this.chessPiecesOnBoard[positionMove.y][positionMove.x] = pawn;
                pawn.setPosition(Position.at(positionMove.x, positionMove.y));
                this.labels[Position.PositionToIndex(positionMove)].setIcon(new ImageIcon(pawn.getRepresenterImage()));
                this.labels[Position.PositionToIndex(positionAt)].setIcon(null);
            }

        }

        if (pawn.getPosition().y == 7) {
            this.chessPiecesOnBoard[pawn.getPosition().y][pawn.getPosition().x] = new QueenBlack(pawn.getPosition());
            this.labels[Position.PositionToIndex(pawn.getPosition())].setIcon(new ImageIcon(
                    this.chessPiecesOnBoard[pawn.getPosition().y][pawn.getPosition().x].getRepresenterImage()));
        }
    }

    /**
     * A helper method to move any chess piece that isn't a pawn
     * 
     * @param positionAt
     *            is the position that the chess piece is currently at
     * @param positionMove
     *            is the position that the chess piece is supposed to be moved
     *            to
     * @param piece
     *            is the chess piece
     */
    private void moveOtherPieces(Position positionAt, Position positionMove, ChessPiece piece) {
        positionMove = this.correctPositionMove(positionAt, positionMove);

        if (piece.getValidMovePositions().contains(positionMove) && this.containsChessPiece(positionMove)) {

            if (piece.getSideIndicator() != this.chessPiecesOnBoard[positionMove.y][positionMove.x]
                    .getSideIndicator()) {

                this.chessPiecesOnBoard[positionAt.y][positionAt.x] = null;
                this.chessPiecesOnBoard[positionMove.y][positionMove.x] = piece;
                piece.setPosition(Position.at(positionMove.x, positionMove.y));
                this.labels[Position.PositionToIndex(positionMove)].setIcon(new ImageIcon(piece.getRepresenterImage()));
                this.labels[Position.PositionToIndex(positionAt)].setIcon(null);
            }

        } else if (piece.getValidMovePositions().contains(positionMove)) {

            this.chessPiecesOnBoard[positionAt.y][positionAt.x] = null;
            this.chessPiecesOnBoard[positionMove.y][positionMove.x] = piece;
            piece.setPosition(Position.at(positionMove.x, positionMove.y));
            this.labels[Position.PositionToIndex(positionMove)].setIcon(new ImageIcon(piece.getRepresenterImage()));
            this.labels[Position.PositionToIndex(positionAt)].setIcon(null);

        }
    }

    /**
     * A helper method that moves a chess piece that is either a black or a
     * white knight. This method is used because knight chess pieces aren't
     * affected by the rule of other chess pieces who can't jump over other
     * chess pieces.
     * 
     * @param positionAt
     *            is the current position of the knight chess piece
     * @param positionMove
     *            is the position that the knight chess piece is supposed to be
     *            moved to
     * @param piece
     *            is either a KnightWhite chess piece or a KnightBlack chess
     *            piece
     */
    private void moveKnightPieces(Position positionAt, Position positionMove, ChessPiece piece) {
        if (piece.getValidMovePositions().contains(positionMove) && this.containsChessPiece(positionMove)) {

            if (piece.getSideIndicator() != this.chessPiecesOnBoard[positionMove.y][positionMove.x]
                    .getSideIndicator()) {

                this.chessPiecesOnBoard[positionAt.y][positionAt.x] = null;
                this.chessPiecesOnBoard[positionMove.y][positionMove.x] = piece;
                piece.setPosition(Position.at(positionMove.x, positionMove.y));
                this.labels[Position.PositionToIndex(positionMove)].setIcon(new ImageIcon(piece.getRepresenterImage()));
                this.labels[Position.PositionToIndex(positionAt)].setIcon(null);
            }

        } else if (piece.getValidMovePositions().contains(positionMove)) {

            this.chessPiecesOnBoard[positionAt.y][positionAt.x] = null;
            this.chessPiecesOnBoard[positionMove.y][positionMove.x] = piece;
            piece.setPosition(Position.at(positionMove.x, positionMove.y));
            this.labels[Position.PositionToIndex(positionMove)].setIcon(new ImageIcon(piece.getRepresenterImage()));
            this.labels[Position.PositionToIndex(positionAt)].setIcon(null);

        }
    }

    /**
     * A helper method that finds out if there exists a chess piece in the line
     * of movement of the moving chess piece. It returns this position which
     * causes the game to not make a move
     * 
     * @param positionAt
     *            is the position that the chess piece is currently at
     * @param positionMove
     *            is the position that the chess piece is supposed to be moved
     *            to
     * @return the position of a chess piece that is in the line of movement of
     *         the chess piece
     */
    private Position correctPositionMove(Position positionAt, Position positionMove) {
        LinkedList<Position> positionsInLine = Position.positionsInLine(positionAt, positionMove);

        for (Position position : positionsInLine) {
            if (!position.equals(positionAt) && this.containsChessPiece(position)) {
                return position;
            }
        }

        return positionMove;
    }

    /**
     * A helper-method that copies a 2-dimensional array
     * 
     * @param board
     *            is the 2-dimensional board that needs to be copied
     * @return a 2-dimensional array that is the exact copy of the given array
     */
    private ChessPiece[][] copyBoard(ChessPiece[][] board) {
        ChessPiece[][] copyBoard = new ChessPiece[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int a = 0; a < board[0].length; a++) {
                copyBoard[i][a] = board[i][a];
            }
        }

        return copyBoard;
    }

    /**
     * A helper method to determine if the board stays the same after a move was
     * made
     * 
     * @param board
     *            is the board after the move
     * @param copyBoard
     *            is the board before the move
     * @return true if the boards are equal
     */
    private boolean equalsBoard(ChessPiece[][] board, ChessPiece[][] copyBoard) {

        for (int i = 0; i < board.length; i++) {
            for (int a = 0; a < board[0].length; a++) {
                if (board[i][a] != null && copyBoard[i][a] == null) {
                    return false;
                } else if (board[i][a] == null && copyBoard[i][a] != null) {
                    return false;
                } else if (board[i][a] != null && copyBoard[i][a] != null) {
                    if (!board[i][a].equals(copyBoard[i][a])) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * A method that highlights the label that is chosen by the player
     * 
     * @param index
     *            is the index of the label that needs to be highlighted
     */
    public void highlight(int index) {
        this.backgroundColor = this.labels[index].getBackground();
        this.labels[index].setBackground(Color.YELLOW);
    }

    /**
     * A method that sets the color of the board back to its original state
     * 
     * @param index
     *            is the index of the label that needs to be changeds to its old
     *            color
     */
    public void normalize(int index) {
        this.labels[index].setBackground(this.backgroundColor);
        this.backgroundColor = null;
    }

    /**
     * A method that determines if there is still a white king on the board
     * 
     * @return true if there exist a white king chess piece on the board
     */
    public boolean containsWhiteKing() {
        for (ChessPiece[] columns : this.chessPiecesOnBoard) {
            for (ChessPiece piece : columns) {
                if (piece instanceof KingWhite) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * A method that determines if there is still a black king on the board
     * 
     * @return true if there exists a black king chess piece on the board
     */
    public boolean containsBlackKing() {
        for (ChessPiece[] columns : this.chessPiecesOnBoard) {
            for (ChessPiece piece : columns) {
                if (piece instanceof KingBlack) {
                    return true;
                }
            }
        }

        return false;
    }

}
