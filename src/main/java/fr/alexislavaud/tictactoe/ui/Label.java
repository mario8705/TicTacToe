package fr.alexislavaud.tictactoe.ui;

import fr.alexislavaud.tictactoe.Color;
import fr.alexislavaud.tictactoe.Vector2f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBEasyFont;

import java.nio.ByteBuffer;

/**
 * Created by Alexis Lavaud on 22/12/2016.
 */
public final class Label extends UiComponent {
    private String text;
    private float scale;
    private Color color;
    private String cachedText;
    private ByteBuffer fontCache;
    private int textQuads;
    private Vector2f fontSize;

    public Label() {
        this("", 1.0f);
    }

    public Label(String text) {
        this(text, 1.0f);
    }

    public Label(String text, float scale) {
        this.text = text;
        this.scale = scale;
        this.color = new Color(1.0f, 1.0f, 1.0f);
        this.fontSize = new Vector2f();
        recalculateFontSize();
        fitSizeToFontSize();
    }

    public void fitSizeToFontSize() {
        size.set(fontSize.getX() * scale, fontSize.getY() * scale);
    }

    @Override
    public void update(float tpf) {

    }

    @Override
    public void render() {
        updateFontCache();

        GL11.glColor4f(color.r, color.g, color.b, color.a);

        GL11.glPushMatrix();
        GL11.glScalef(scale, scale, 0.0f);

        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glVertexPointer(2, GL11.GL_FLOAT, 16, fontCache);
        GL11.glDrawArrays(GL11.GL_QUADS, 0, textQuads * 4);
        GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);

        GL11.glPopMatrix();

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    private void updateFontCache() {
        if (cachedText == null || !cachedText.equals(text)) {
            fontCache = BufferUtils.createByteBuffer(text.length() * 512);
            textQuads = STBEasyFont.stb_easy_font_print(0.0f, 0.0f, text, null, fontCache);

            recalculateFontSize();

            cachedText = text;
        }
    }

    private void recalculateFontSize() {
        fontSize.setX(STBEasyFont.stb_easy_font_width(text));
        fontSize.setY(STBEasyFont.stb_easy_font_height(text));
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Vector2f getFontSize() {
        return fontSize;
    }
}
