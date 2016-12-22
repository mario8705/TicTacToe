package fr.alexislavaud.tictactoe.ui;

import fr.alexislavaud.tictactoe.Vector2f;

/**
 * Created by Alexis Lavaud on 22/12/2016.
 */
public abstract class UiComponent {
    protected UiComponent parent;
    protected Vector2f position;
    protected Vector2f size;

    public UiComponent() {
        this.parent = null;
        this.position = new Vector2f();
        this.size = new Vector2f();
    }

    public void centerX(float width) {
        position.setX((width - size.getX()) / 2.0f);
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Vector2f getSize() {
        return size;
    }

    public void setSize(Vector2f size) {
        this.size = size;
    }

    public void update(float tpf) {}
    public void render() {}

    public void onMouseMove(float x, float y) {}
    public void onMouseButtonDown(float x, float y, int button) {}
    public void onMouseButtonUp(float x, float y, int button) {}
    public void onMouseIn() {}
    public void onMouseOut() {}
}
