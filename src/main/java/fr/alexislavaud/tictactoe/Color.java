package fr.alexislavaud.tictactoe;

/**
 * Created by Alexis Lavaud on 22/12/2016.
 */
public final class Color
{
    private float red, green, blue, alpha;

    public Color()
    {
        this(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public Color(float red, float green, float blue)
    {
        this(red, green, blue, 1.0f);
    }

    public Color(float red, float green, float blue, float alpha)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public float getRed()
    {
        return red;
    }

    public void setRed(float red)
    {
        this.red = red;
    }

    public float getGreen()
    {
        return green;
    }

    public void setGreen(float green)
    {
        this.green = green;
    }

    public float getBlue()
    {
        return blue;
    }

    public void setBlue(float blue)
    {
        this.blue = blue;
    }

    public float getAlpha()
    {
        return alpha;
    }

    public void setAlpha(float alpha)
    {
        this.alpha = alpha;
    }
}
