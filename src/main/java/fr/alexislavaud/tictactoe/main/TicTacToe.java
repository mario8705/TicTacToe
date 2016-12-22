package fr.alexislavaud.tictactoe.main;

import fr.alexislavaud.tictactoe.DeviceWindow;
import fr.alexislavaud.tictactoe.Vector2f;
import fr.alexislavaud.tictactoe.ui.MainMenuScreen;
import fr.alexislavaud.tictactoe.ui.Screen;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import javax.swing.*;

/**
 * Created by Alexis Lavaud on 22/12/2016.
 */
public final class TicTacToe {
    private static TicTacToe instance = null;
    private volatile boolean isRunning;
    private DeviceWindow deviceWindow;
    private Vector2f mousePosition;
    private Screen currentScreen;
    private Screen nextScreen;

    /**
     * Game's private constructor
     */
    private TicTacToe() {
        this.mousePosition = new Vector2f();
        System.out.println("Using LWJGL v" + Version.getVersion());
    }

    /**
     * Initialize the game
     */
    private void init() {
        this.deviceWindow = new DeviceWindow(800, 600, "TicTacToe");
        setNextScreen(new MainMenuScreen());

        // Register callbacks
        GLFW.glfwSetCursorPosCallback(deviceWindow.getWindowHandle(), this::cursorPosCallback);
        GLFW.glfwSetMouseButtonCallback(deviceWindow.getWindowHandle(), this::mouseButtonCallback);
    }

    private void cursorPosCallback(long window, double xpos, double ypos) {
        if (currentScreen != null) {
            currentScreen.onMouseMove((float) xpos, (float) ypos);
        }

        mousePosition.set((float) xpos, (float) ypos);
    }

    private void mouseButtonCallback(long windowID, int button, int action, int mods) {
        if (currentScreen != null) {
            switch (action) {
                case GLFW.GLFW_PRESS:
                    currentScreen.onMouseButtonDown(mousePosition.getX(), mousePosition.getY(), button);
                    break;

                case GLFW.GLFW_RELEASE:
                    currentScreen.onMouseButtonUp(mousePosition.getX(), mousePosition.getY(), button);
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * Update the game's logic
     * @param tpf Time per frame : the duration of the last frame in seconds
     */
    private void update(float tpf) {
        if (nextScreen != currentScreen) {
            if (currentScreen != null) {
                currentScreen.destroy();
            }

            this.currentScreen = nextScreen;

            if (currentScreen != null) {
                currentScreen.init();
            }
        }

        if (currentScreen != null) {
            currentScreen.getRootContainer().setSize(new Vector2f(deviceWindow.getWidth(), deviceWindow.getHeight()));
            currentScreen.update(tpf);
        }
    }

    /**
     * Render the game's graphics
     */
    private void render() {
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        GL11.glViewport(0, 0, deviceWindow.getWidth(), deviceWindow.getHeight());

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0f, deviceWindow.getWidth(), deviceWindow.getHeight(), 0.0f, 1.0f, -1.0f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

        if (currentScreen != null) {
            currentScreen.render();
        }
    }

    /**
     * Starts the game
     */
    private void start() {
        this.isRunning = true;

        init();

        long lastFrameTime = System.nanoTime();

        while (isRunning) {
            long currentTime = System.nanoTime();
            float tpf = (currentTime - lastFrameTime) / 1000000000.0f;
            lastFrameTime = currentTime;

            deviceWindow.pollEvents();

            if (deviceWindow.isCloseRequested()) {
                isRunning = false;
            }

            update(tpf);
            render();

            deviceWindow.presentFrame();
        }

        deviceWindow.destroy();
    }

    public void setNextScreen(Screen nextScreen) {
        this.nextScreen = nextScreen;
    }

    public void shutdown() {
        this.isRunning = false;
    }

    public static TicTacToe getInstance() {
        return instance;
    }

    /**
     * Program's entry point
     * @param args Program's command line arguments (unused)
     */
    public static void main(String[] args) {
        try {
            instance = new TicTacToe();
            instance.start();
        } catch (Throwable throwable) {
            try {
                // Attempt to set the system's look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Throwable e) {
                ;
            }

            throwable.printStackTrace();

            JOptionPane.showMessageDialog(null, String.format("The game ran into problems: %s", throwable.getLocalizedMessage()), "Unrecoverable error", JOptionPane.ERROR_MESSAGE);
            System.exit(1); // Make sure that all thread are terminated and no non-daemon thread are still alive and thus making the vm busy
        }
    }
}
