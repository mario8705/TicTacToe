package fr.alexislavaud.tictactoe.ui;

/**
 * Created by Alexis Lavaud on 22/12/2016.
 */
public abstract class Screen {
    protected Container rootContainer;

    public Screen() {
        this.rootContainer = new Container();
    }

    /**
     * Update the gui logic
     * @param tpf Time per frame
     */
    public void update(float tpf) {
        rootContainer.update(tpf);
    }

    /**
     * Render the gui
     */
    public void render() {
        rootContainer.render();
    }

    public void onMouseMove(float x, float y) {
        rootContainer.onMouseMove(x, y);
    }

    public void onMouseButtonDown(float x, float y, int button) {
        rootContainer.onMouseButtonDown(x, y, button);
    }

    public void onMouseButtonUp(float x, float y, int button) {
        rootContainer.onMouseButtonUp(x, y, button);
    }

    public abstract void init();
    public abstract void destroy();

    public Container getRootContainer() {
        return rootContainer;
    }
}
