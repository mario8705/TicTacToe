package fr.alexislavaud.tictactoe.ui;

import fr.alexislavaud.tictactoe.main.TicTacToe;

/**
 * Created by Alexis Lavaud on 22/12/2016.
 */
public final class TicTacToeScreen extends Screen
{
    private boolean playingAgainstComputer;
    private boolean opponentTurn;
    private Button mainMenuButton;
    private Label statusLabel;
    private TicTacToeBoard board;

    public TicTacToeScreen(boolean playingAgainstComputer)
    {
        this.playingAgainstComputer = playingAgainstComputer;
        this.opponentTurn = false;
    }

    @Override
    public void update(float tpf)
    {
        super.update(tpf);

        mainMenuButton.getPosition().set(10.0f, 10.0f);

        statusLabel.getPosition().setX(300.0f);
        statusLabel.getPosition().setY((mainMenuButton.getSize().getY() + 10.0f - statusLabel.getFontSize().getY()) / 2.0f);

        updateStatusLabel();

        board.getPosition().set(10.0f, 10.0f * 2.0f + mainMenuButton.getSize().getY());
        board.getSize().setX(rootContainer.getSize().getX() - board.getPosition().getX() - 10.0f);
        board.getSize().setY(rootContainer.getSize().getY() - board.getPosition().getY() - 10.0f);
    }

    private void updateStatusLabel()
    {
        if (playingAgainstComputer)
        {
            if (opponentTurn)
            {
                statusLabel.setText("Computer's turn");
            }
            else
            {
                statusLabel.setText("Your turn");
            }
        }
        else
        {
            if (opponentTurn)
            {
                statusLabel.setText("Player 2's turn");
            }
            else
            {
                statusLabel.setText("Player 1's turn");
            }
        }
    }

    @Override
    public void init()
    {
        this.mainMenuButton = new Button("Main Menu");
        this.statusLabel = new Label();
        this.board = new TicTacToeBoard();

        mainMenuButton.setButtonCallback(() -> TicTacToe.getInstance().setNextScreen(new MainMenuScreen()));
        statusLabel.setScale(3.0f);
        board.setBoardCellChangeCallback(this::onBoardCellChange);

        rootContainer.addChilds(mainMenuButton, statusLabel, board);
    }

    @Override
    public void destroy()
    {
    }

    private void onBoardCellChange(int cellX, int cellY)
    {
        if (opponentTurn && playingAgainstComputer || !board.isCellEmpty(cellX, cellY))
        {
            return;
        }

        if (opponentTurn)
        {
            board.setBoardCellAt(cellX, cellY, 'X');
            opponentTurn = false;

            if (board.hasWon('X'))
            {
                System.out.println("Congratulations X");
                board.clearBoard();
            }
        }
        else
        {
            board.setBoardCellAt(cellX, cellY, 'O');
            opponentTurn = true;

            if (board.hasWon('O'))
            {
                System.out.println("Congratulations O");
                board.clearBoard();
            }
        }
    }
}
