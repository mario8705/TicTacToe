package fr.alexislavaud.tictactoe.ui;

/**
 * Created by Alexis Lavaud on 22/12/2016.
 */
public final class TicTacToeScreen extends Screen {
    private TicTacToeBoard board;

    @Override
    public void update(float tpf) {
        super.update(tpf);

        board.getSize().set(rootContainer.getSize().getX(), rootContainer.getSize().getY());
    }

    @Override
    public void init() {
        this.board = new TicTacToeBoard();

        rootContainer.addChild(board);
    }

    @Override
    public void destroy() {
    }
}
