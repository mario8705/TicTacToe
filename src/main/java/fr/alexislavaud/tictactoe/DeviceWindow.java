package fr.alexislavaud.tictactoe;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

/**
 * Created by Alexis Lavaud on 22/12/2016.
 */
public final class DeviceWindow
{
    private long windowHandle;
    private int width, height;
    private int fbWidth, fbHeight;

    /**
     * Attempt to create an OpenGL rendering context
     * @param width Window width
     * @param height Window height
     * @param appTitle Window caption
     */
    public DeviceWindow(int width, int height, String appTitle)
    {
        this.width = width;
        this.height = height;

        if (!GLFW.glfwInit())
        {
            throw new UnrecoverableError("glfwInit() returned false");
        }

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);
        this.windowHandle = GLFW.glfwCreateWindow(width, height, appTitle, MemoryUtil.NULL, MemoryUtil.NULL);

        if (windowHandle == MemoryUtil.NULL)
        {
            GLFW.glfwTerminate();

            throw new UnrecoverableError("glfwCreateWindow() failed");
        }

        GLFW.glfwMakeContextCurrent(windowHandle);
        GLFW.glfwSwapInterval(1); // Enable v-sync

        {
            final int[] fbWidthArray = new int[1];
            final int[] fbHeightArray = new int[1];
            GLFW.glfwGetFramebufferSize(this.windowHandle, fbWidthArray, fbHeightArray);

            this.fbWidth = fbWidthArray[0];
            this.fbHeight = fbHeightArray[0];
        }

        GL.createCapabilities();
    }

    /**
     * Handles OS-related events (keyboard, mouse,  ...)
     */
    public void pollEvents()
    {
        GLFW.glfwPollEvents();
    }

    /**
     * Send the frame to the monitor
     */
    public void presentFrame()
    {
        GLFW.glfwSwapBuffers(windowHandle);
    }

    /**
     * Destroy the window and terminate glfw
     */
    public void destroy()
    {
        if (windowHandle != MemoryUtil.NULL)
        {
            GLFW.glfwDestroyWindow(windowHandle);
            GLFW.glfwTerminate();

            this.windowHandle = MemoryUtil.NULL;
        }
    }

    /**
     * Checks if the window has been closed
     * @return true if the user has closed the window
     */
    public boolean isCloseRequested()
    {
        return GLFW.glfwWindowShouldClose(windowHandle);
    }

    /**
     * Returns the window's width
     * @return The window's width
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * Returns the windows's height
     * @return The windows's height
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * Returns the width of the framebuffer
     * @return The width of the framebuffer
     */
    public int getFramebufferWidth() {
        return fbWidth;
    }

    /**
     * Returns the height of the framebuffer
     * @return The height of the framebuffer
     */
    public int getFramebufferHeight() {
        return fbHeight;
    }

    /**
     * Returns the GLFW window handle
     * @return The window handle
     */
    public long getWindowHandle()
    {
        return windowHandle;
    }
}
