package fr.alexislavaud.tictactoe.ui;

import fr.alexislavaud.tictactoe.Vector2f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexis Lavaud on 22/12/2016.
 */
public class Container extends UiComponent {
    private List<UiComponent> childs;
    private UiComponent focusedComponent;
    private Vector2f previousMousePosition;
    private boolean[] mouseButtons;

    public Container() {
        this.childs = new ArrayList<>();
        this.focusedComponent = null;
        this.previousMousePosition = new Vector2f();
        this.mouseButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    }

    @Override
    public void update(float tpf) {
        for (int i = 0; i < childs.size(); i++) {
            UiComponent component = childs.get(i);

            component.update(tpf);
        }
    }

    @Override
    public void render() {
        for (int i = 0; i < childs.size(); i++) {
            UiComponent component = childs.get(i);

            GL11.glPushMatrix();
            GL11.glTranslatef(component.position.getX(), component.position.getY(), 0.0f);

            component.render();
            GL11.glPopMatrix();

            GL11.glColor4f(1.0f, 0.0f, 0.0f, 1.0f);
            GL11.glBegin(GL11.GL_LINE_LOOP);
            GL11.glVertex2f(component.position.getX(), component.position.getY());
            GL11.glVertex2f(component.position.getX() + component.size.getX(), component.position.getY());
            GL11.glVertex2f(component.position.getX() + component.size.getX(), component.position.getY() + component.size.getY());
            GL11.glVertex2f(component.position.getX(), component.position.getY() + component.size.getY());
            GL11.glEnd();
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    public void addChild(UiComponent child) {
        childs.add(child);
        child.parent = this;
    }

    public void addChilds(UiComponent... components) {
        for (UiComponent component : components) {
            addChild(component);
        }
    }

    public void removeChild(UiComponent child) {
        if (childs.remove(child) && child.parent == this) {
            child.parent = null;
        }
    }

    public UiComponent getComponentAt(float x, float y) {
        for (int i = childs.size() - 1; i >= 0; i--) {
            UiComponent child = childs.get(i);

            if (isMouseInComponent(x, y, child)) {
                return child;
            }
        }

        return null;
    }

    @Override
    public void onMouseMove(float x, float y) {
        for (int i = childs.size() - 1; i >= 0; i--) {
            UiComponent component = childs.get(i);

            if (isMouseInComponent(x, y, component))
            {
                component.onMouseMove(x - component.position.getX(), y - component.position.getY());
                break;
            }
        }

        UiComponent previousHighlightedComponent = getComponentAt(previousMousePosition.getX(), previousMousePosition.getY());
        UiComponent currentHighlightedComponent = getComponentAt(x, y);

        if (previousHighlightedComponent != null && previousHighlightedComponent != currentHighlightedComponent) {
            previousHighlightedComponent.onMouseOut();
        }

        if (currentHighlightedComponent != null && currentHighlightedComponent != previousHighlightedComponent) {
            currentHighlightedComponent.onMouseIn();
        }

        if (focusedComponent != null && isAnyMouseButtonDown()) {
            focusedComponent.onMouseMove(x - focusedComponent.position.getX(), y - focusedComponent.position.getY());
        }

        previousMousePosition.set(x, y);
    }

    private boolean isAnyMouseButtonDown() {
        boolean ret = false;

        for (boolean mouseButton : mouseButtons) {
            ret = ret || mouseButton;
        }

        return ret;
    }

    @Override
    public void onMouseButtonDown(float x, float y, int button) {
        UiComponent highlightedComponent = getComponentAt(x, y);

        mouseButtons[button] = true;

        if (highlightedComponent != null) {
            highlightedComponent.onMouseButtonDown(x - highlightedComponent.position.getY(), y - highlightedComponent.position.getY(), button);
            focusedComponent = highlightedComponent;
        }
        else {
            focusedComponent = null;
        }
    }

    @Override
    public void onMouseButtonUp(float x, float y, int button) {
        mouseButtons[button] = false;

        if (focusedComponent != null) {
            focusedComponent.onMouseButtonUp(x - focusedComponent.position.getX(), y - focusedComponent.position.getY(), button);
        }
    }

    private boolean isMouseInComponent(float x, float y, UiComponent component) {
        Vector2f position = component.position;
        Vector2f size = component.getSize();

        if (x > position.getX() && x <= position.getX() + size.getX() &&
                y > position.getY() && y <= position.getY() + size.getY()) {

            return true;
        }

        return false;
    }

    @Override
    public void onMouseIn() {

    }

    @Override
    public void onMouseOut() {

    }
}
