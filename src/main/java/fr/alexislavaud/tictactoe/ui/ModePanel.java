package fr.alexislavaud.tictactoe.ui;

import fr.alexislavaud.tictactoe.Vector2f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBEasyFont;

import java.nio.ByteBuffer;

/**
 * Created by Alexis Lavaud on 22/12/2016.
 */
public final class ModePanel extends UiComponent {
    private float scale;
    private boolean hovered;
    private boolean clicked;
    private ByteBuffer fontCache;
    private int numQuads;
    private Vector2f fontSize;
    private Runnable onClickCallback;

    public ModePanel(String text) {
        this.fontCache = BufferUtils.createByteBuffer(text.length() * 512);
        this.numQuads = STBEasyFont.stb_easy_font_print(0.0f, 0.0f, text, null, fontCache);
        this.fontSize = new Vector2f(STBEasyFont.stb_easy_font_width(text), STBEasyFont.stb_easy_font_height(text));
    }

    @Override
    public void update(float tpf) {
        if (!clicked) {
            if (hovered) {
                this.scale = Math.min(scale + tpf * 0.5f, 0.05f);
            } else {
                this.scale = Math.max(scale - tpf * 0.5f, 0.0f);
            }
        }
    }

    @Override
    public void render() {
        super.render();

        GL11.glPushMatrix();
        GL11.glTranslatef(size.getX() / 2.0f, size.getY() / 2.0f, 0.0f);
        GL11.glScalef(1.0f + scale, 1.0f + scale, 0.0f);

        final float halfSizeX = size.getX() / 2.0f;
        final float halfSizeY = size.getY() / 2.0f;

        GL11.glRectf(-halfSizeX, -halfSizeY, halfSizeX, halfSizeY);

        GL11.glScalef(4.0f, 4.0f, 0.0f);
        GL11.glTranslatef(-fontSize.getX() / 2.0f, -fontSize.getY() / 2.0f, 0.0f);

        GL11.glColor4f(0.0f, 0.0f, 0.0f, 1.0f);

        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glVertexPointer(2, GL11.GL_FLOAT, 16, fontCache);
        GL11.glDrawArrays(GL11.GL_QUADS, 0, numQuads * 4);
        GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);

        GL11.glPopMatrix();

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public void onMouseButtonDown(float x, float y, int button) {
        this.clicked = true;
        this.scale = -0.05f;
    }

    @Override
    public void onMouseButtonUp(float x, float y, int button) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_1) {
            this.clicked = false;

            if (hovered && onClickCallback != null) {
                onClickCallback.run();
            }
        }
    }

    @Override
    public void onMouseIn() {
        this.hovered = true;
    }

    @Override
    public void onMouseOut() {
        this.hovered = false;
    }

    public void setOnClickCallback(Runnable onClickCallback) {
        this.onClickCallback = onClickCallback;
    }
}
