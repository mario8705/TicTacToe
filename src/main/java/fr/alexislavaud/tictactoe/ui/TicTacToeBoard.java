package fr.alexislavaud.tictactoe.ui;

import org.lwjgl.opengl.GL11;

/**
 * Created by Alexis Lavaud on 22/12/2016.
 */
public final class TicTacToeBoard extends UiComponent {
    private char[][] boardPieces;

    public TicTacToeBoard() {
        this.boardPieces = new char[3][3];
    }

    private void renderGrid(float lineSize) {
        GL11.glColor3f(0.0f, 1.0f, 1.0f);

        final float halfLineSize = lineSize / 2.0f;
        final float sizeXover3 = getSize().getX() / 3.0f;
        final float sizeYover3 = getSize().getY() / 3.0f;

        /* Draw vertical lines */
        GL11.glRectf(0.0f, 0.0f, lineSize, getSize().getY());
        GL11.glRectf(sizeXover3 - halfLineSize, 0.0f, sizeXover3 + halfLineSize, getSize().getY());
        GL11.glRectf(sizeXover3 * 2.0f - halfLineSize, 0.0f, sizeXover3 * 2.0f + halfLineSize, getSize().getY());
        GL11.glRectf(getSize().getX() - lineSize, 0.0f, getSize().getX(), getSize().getY());

        /* Draw horizontal lines */
        GL11.glRectf(0.0f, 0.0f, getSize().getX(), lineSize);
        GL11.glRectf(0.0f, sizeYover3 - halfLineSize, getSize().getX(), sizeYover3 + halfLineSize);
        GL11.glRectf(0.0f, sizeYover3 * 2.0f - halfLineSize, getSize().getX(), sizeYover3 * 2.0f + halfLineSize);
        GL11.glRectf(0.0f, getSize().getY() - lineSize, getSize().getX(), getSize().getY());

        GL11.glColor3f(1.0f, 1.0f, 1.0f);
    }

    private void drawPieces() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {

            }
        }
    }

    @Override
    public void render() {
        renderGrid(10.0f);
        drawPieces();
    }

    public boolean hasWon(char piece) {
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (boardPieces[i][0] == piece && boardPieces[i][1] == piece && boardPieces[i][2] == piece) {
                return true;
            }
        }

        // Check rows
        for (int i = 0; i < 3; i++) {
            if (boardPieces[0][i] == piece && boardPieces[1][i] == piece && boardPieces[2][i] == piece) {
                return true;
            }
        }

        // Check diagonals

        return false;
    }
}
