package fr.alexislavaud.tictactoe.ui;

import fr.alexislavaud.tictactoe.main.TicTacToe;

/**
 * Created by Alexis Lavaud on 22/12/2016.
 */
public final class SelectModeScreen extends Screen
{
    private Label selectModeLabel;
    private ModePanel aiModePanel;
    private ModePanel versusModePanel;
    private Button backButton;

    public SelectModeScreen()
    {

    }

    @Override
    public void update(float tpf)
    {
        super.update(tpf);

        selectModeLabel.centerX(rootContainer.getSize().getX());
        selectModeLabel.getPosition().setY(10.0f);

        final float panelSize = rootContainer.getSize().getX() / 2.0f - (10.0f * 3.0f);
        final float margin = 20.0f;
        final float panelStartY = 100.0f;
        final float panelStopY = rootContainer.getSize().getY() - panelStartY - margin;

        aiModePanel.getPosition().set(margin, panelStartY);
        aiModePanel.getSize().set(panelSize, panelStopY);

        versusModePanel.getPosition().set(rootContainer.getSize().getX() - panelSize - margin, panelStartY);
        versusModePanel.getSize().set(panelSize, panelStopY);

        backButton.getPosition().set(5.0f, 5.0f);
    }

    @Override
    public void init()
    {
        this.selectModeLabel = new Label("Select mode", 4.0f);
        this.aiModePanel = new ModePanel("With computer");
        this.versusModePanel = new ModePanel("With friend");
        this.backButton = new Button("Back");

        aiModePanel.setOnClickCallback(() -> TicTacToe.getInstance().setNextScreen(new TicTacToeScreen(true)));
        versusModePanel.setOnClickCallback(() -> TicTacToe.getInstance().setNextScreen(new TicTacToeScreen(false)));
        backButton.setButtonCallback(() -> TicTacToe.getInstance().setNextScreen(new MainMenuScreen()));

        rootContainer.addChilds(selectModeLabel, aiModePanel, versusModePanel, backButton);
    }

    @Override
    public void destroy()
    {
    }
}
