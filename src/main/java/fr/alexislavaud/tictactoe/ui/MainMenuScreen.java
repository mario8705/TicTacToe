package fr.alexislavaud.tictactoe.ui;

import fr.alexislavaud.tictactoe.main.TicTacToe;

/**
 * Created by Alexis Lavaud on 22/12/2016.
 */
public final class MainMenuScreen extends Screen
{
    private Image backgroundImage;
    private Label gameTitle;
    private Button playButton;
    private Button quitButton;
    private Label versionLabel;

    @Override
    public void update(float tpf)
    {
        super.update(tpf);

        gameTitle.centerX(rootContainer.getSize().getX());
        gameTitle.getPosition().setY(10.0f);

        playButton.centerX(rootContainer.getSize().getX());
        playButton.getPosition().setY(rootContainer.getSize().getY() / 2.0f - playButton.getSize().getY());

        quitButton.centerX(rootContainer.getSize().getX());
        quitButton.getPosition().setY(rootContainer.getSize().getY() / 2.0f + 10.0f);

        versionLabel.getPosition().set(0.0f, rootContainer.getSize().getY() - versionLabel.getFontSize().getY());
    }

    @Override
    public void init()
    {
        this.gameTitle = new Label("TicTacToe", 4.0f);
        this.playButton = new Button("Play!");
        this.quitButton = new Button("Quit");
        this.versionLabel = new Label("TicTacToe v1.0");

        playButton.setButtonCallback(() -> TicTacToe.getInstance().setNextScreen(new SelectModeScreen()));
        quitButton.setButtonCallback(() -> TicTacToe.getInstance().shutdown());

        rootContainer.addChilds(gameTitle, playButton, quitButton, versionLabel);
    }

    @Override
    public void destroy()
    {
    }
}
