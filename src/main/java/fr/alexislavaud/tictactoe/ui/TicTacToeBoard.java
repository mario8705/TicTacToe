package fr.alexislavaud.tictactoe.ui;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;

/**
 * Created by Alexis Lavaud on 22/12/2016.
 */
public final class TicTacToeBoard extends UiComponent
{
    private char[][] boardCells;

    public TicTacToeBoard()
    {
        this.boardCells = new char[3][3];
        emptyBoard();

        // TODO DEBUG
        boardCells[0][0] = 'O';
        boardCells[1][0] = 'O';
        boardCells[1][2] = 'O';
        boardCells[1][1] = 'X';
        boardCells[0][1] = 'X';
        boardCells[0][1] = 'X';
    }

    private void renderGrid(float lineSize)
    {
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

    private void drawCells()
    {
        for (int x = 0; x < 3; x++)
        {
            for (int y = 0; y < 3; y++)
            {
                char cell = boardCells[x][y];

                final float sizeXover6 = getSize().getX() / 6.0f;
                final float sizeYover6 = getSize().getY() / 6.0f;

                final float xpos = x * 2.0f * sizeXover6 + sizeXover6;
                final float ypos = y * 2.0f * sizeYover6 + sizeYover6;

                if (cell == 'O')
                {
                    GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
                    GL11.glVertex2f(xpos, ypos);

                    final double pi2 = Math.PI * 2.0f;

                    for (int i = 0; i <= 16; i++)
                    {
                        double radians = i * pi2 / 16;

                        GL11.glVertex2d(xpos + Math.sin(radians) * 40.0f, ypos + Math.cos(radians) * 40.0f);
                        GL11.glVertex2d(xpos + Math.sin(radians) * 50.0f, ypos + Math.cos(radians) * 50.0f);
                    }

                    GL11.glEnd();
                }
                else if (cell == 'X')
                {
                    GL11.glBegin(GL11.GL_QUADS);
                    GL11.glVertex2f(xpos - 25.0f, ypos - 25.0f);
                    GL11.glVertex2f(xpos - 15.0f, ypos - 25.0f);
                    GL11.glVertex2f(xpos + 25.0f, ypos + 25.0f);
                    GL11.glVertex2f(xpos + 15.0f, ypos + 25.0f);

                    GL11.glVertex2f(xpos + 25.0f, ypos - 25.0f);
                    GL11.glVertex2f(xpos + 15.0f, ypos - 25.0f);
                    GL11.glVertex2f(xpos - 25.0f, ypos + 25.0f);
                    GL11.glVertex2f(xpos - 15.0f, ypos + 25.0f);
                    GL11.glEnd();
                }
            }
        }
    }

    @Override
    public void render()
    {
        renderGrid(10.0f);
        drawCells();
    }

    public boolean hasWon(char piece)
    {
        // Check columns
        for (int i = 0; i < 3; i++)
        {
            if (boardCells[i][0] == piece && boardCells[i][1] == piece && boardCells[i][2] == piece)
            {
                return true;
            }
        }

        // Check rows
        for (int i = 0; i < 3; i++)
        {
            if (boardCells[0][i] == piece && boardCells[1][i] == piece && boardCells[2][i] == piece)
            {
                return true;
            }
        }

        // Check diagonals
        if (boardCells[0][0] == piece && boardCells[1][1] == piece && boardCells[2][2] == piece)
        {
            return true;
        }

        if (boardCells[2][0] == piece && boardCells[1][1] == piece && boardCells[0][2] == piece)
        {
            return true;
        }

        return false;
    }

    public int emptyCellsCount()
    {
        int count = 0;

        for (int x = 0; x < 3; x++)
        {
            for (int y = 0; y < 3; y++)
            {
                if (boardCells[x][y] == ' ')
                {
                    count++;
                }
            }
        }

        return count;
    }

    private void emptyBoard()
    {
        for (int i = 0; i < 3; i++)
        {
            Arrays.fill(boardCells[i], ' ');
        }
    }

    @Override
    public void onMouseButtonUp(float x, float y, int button)
    {
        int cellX = (int) (x / size.getX() * 3.0f);
        int cellY = (int) (y / size.getY() * 3.0f);

        if (cellX < 0 || cellX > 2 || cellX < 0 || cellY > 2)
            return;

        if (button == GLFW.GLFW_MOUSE_BUTTON_1)
        {
            boardCells[cellX][cellY] = 'O';

            if (hasWon('O'))
            {
                System.out.println("Fuck off");
            }
        }

        // TODO to continue
    }
}
