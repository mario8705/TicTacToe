package fr.alexislavaud.tictactoe.ui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBImage;

import java.nio.ByteBuffer;

/**
 * Created by Alexis Lavaud on 22/12/2016.
 */
public final class Image extends UiComponent {
    private int textureHandle;
    private final int width, height;

    private Image(int width, int height, int textureHandle) {
        this.width = width;
        this.height = height;
        this.textureHandle = textureHandle;
    }

    public void destroy() {
        if (GL11.glIsTexture(textureHandle)) {
            GL11.glDeleteTextures(textureHandle);
            this.textureHandle = 0;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTextureHandle() {
        return textureHandle;
    }

    public static Image loadImage(String filename) {
        int[] width = { 0 }, height = { 0 }, bpp = { 0 };

        ByteBuffer pixels = STBImage.stbi_load(filename, width, height, bpp, 0);

        if (pixels == null) {
            return null;
        }

        int internalFormat = (bpp[0] == 32) ? GL11.GL_RGBA : GL11.GL_RGB;
        int format;

        switch (bpp[0]) {
            case 32:
                format = GL11.GL_RGBA;
                break;

            case 24:
                format = GL11.GL_RGB;
                break;

            case 16:
                format = GL11.GL_RGB16;
                break;

            default:
                STBImage.stbi_image_free(pixels);
                return null;
        }

        int previousBoundTexture = GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D);

        int textureHandle = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureHandle);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, internalFormat, width[0], height[0], 0, format, GL11.GL_UNSIGNED_BYTE, pixels);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, previousBoundTexture);

        return new Image(width[0], height[0], textureHandle);
    }
}
