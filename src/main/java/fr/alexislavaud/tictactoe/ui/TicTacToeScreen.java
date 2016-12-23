package fr.alexislavaud.tictactoe.ui;

import fr.alexislavaud.tictactoe.main.TicTacToe;

/**
 * Created by Alexis Lavaud on 22/12/2016.
 */
public final class TicTacToeScreen extends Screen
{
    private boolean playingAgainstComputer;
    private Button mainMenuButton;
    private Label statusLabel;
    private TicTacToeBoard board;

    @Override
    public void update(float tpf)
    {
        super.update(tpf);

        mainMenuButton.getPosition().set(10.0f, 10.0f);

        statusLabel.getPosition().setX(300.0f);
        statusLabel.getPosition().setY((mainMenuButton.getSize().getY() + 10.0f - statusLabel.getFontSize().getY()) / 2.0f);
        statusLabel.setText("Player2 turn");

        board.getPosition().set(10.0f, 10.0f * 2.0f + mainMenuButton.getSize().getY());
        board.getSize().setX(rootContainer.getSize().getX() - board.getPosition().getX() - 10.0f);
        board.getSize().setY(rootContainer.getSize().getY() - board.getPosition().getY() - 10.0f);
    }

    @Override
    public void init()
    {
        this.mainMenuButton = new Button("Main Menu");
        this.statusLabel = new Label();
        this.board = new TicTacToeBoard();

        mainMenuButton.setButtonCallback(() -> TicTacToe.getInstance().setNextScreen(new MainMenuScreen()));
        statusLabel.setScale(3.0f);

        rootContainer.addChilds(mainMenuButton, statusLabel, board);
    }

    @Override
    public void destroy()
    {
    }
}
