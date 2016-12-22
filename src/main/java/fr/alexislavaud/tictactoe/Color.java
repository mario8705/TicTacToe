package fr.alexislavaud.tictactoe;

/**
 * Created by Alexis Lavaud on 22/12/2016.
 */
public final class Color {
    public float r, g, b, a;

    public Color() {
        this(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public Color(float r, float g, float b) {
        this(r, g, b, 1.0f);
    }

    public Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
}
