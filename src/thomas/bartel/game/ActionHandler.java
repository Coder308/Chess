package thomas.bartel.game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import thomas.bartel.chessPieces.*;

/**
 * A class that handles every action that happens on the chess board
 * 
 * @author Thomas Bartel
 *
 */
public class ActionHandler {
    /**
     * Is the chess board that is used
     */
    private ChessBoard chessBoard;
    /**
     * Is an array with every label on the chess board
     */
    private JLabel[] labels;
    /**
     * Is the first position on the board that was clicked on
     */
    private Position firstChosenPosition = null;
    /**
     * Is the second position on the board that was clicked on
     */
    private Position secondChosenPosition = null;

    /**
     * The constructor for an action handler
     */
    public ActionHandler() {
        this.chessBoard = new ChessBoard();
        this.labels = this.chessBoard.getLabels();

        for (int i = 0; i < labels.length; i++) {
            int index = i;
            labels[i].addMouseListener(new MouseAdapter() {

                @Override
                public void mouseReleased(MouseEvent e) {
                    clickOnLabel(index);
                }

            });
        }
    }

    /**
     * A method that executes what happens when a user clicks on a field on the
     * chess board
     * 
     * @param index
     *            is the index of the label that the user clicked on
     */
    public void clickOnLabel(int index) {
        if (this.firstChosenPosition == null) {
            this.firstChosenPosition = Position.IndexToPosition(index);
        } else {
            this.secondChosenPosition = Position.IndexToPosition(index);
            this.chessBoard.moveChessPiece(this.firstChosenPosition, this.secondChosenPosition);
            this.firstChosenPosition = null;
            this.secondChosenPosition = null;
        }

    }

}
