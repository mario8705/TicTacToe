package fr.alexislavaud.tictactoe;

import org.w3c.dom.css.Rect;

/**
 * Created by Alexis Lavaud on 22/12/2016.
 */
public final class Rectangle {
    private float x;
    private float y;
    private float width;
    private float height;

    public Rectangle() {
        this(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public Rectangle(float x, float y, float width, float height) {
       set(x, y, width, height);
    }

    public void set(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isInside(Vector2f v) {
        if (v.getX() > x && v.getX() <= x + width && v.getY() > y && v.getY() <= y + height) {
            return true;
        }

        return false;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
