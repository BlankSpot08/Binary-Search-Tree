package processing;

import processing.core.PApplet;
import processing.core.PVector;

public class TextBox extends PApplet {
    public TextBox(PApplet pApplet) {
        this.pApplet = pApplet;

        text = "";
        id = "textbox";
        pos = new PVector(0, 0);
        backgroundColor = color(255);
        width = 10;
        height = 10;
        borderColor = color(255, 255, 255);
        backgroundSelected = color(150);
        textLength = 0;
    }

    private PApplet pApplet;
    private PVector pos;
    private String text;
    private String id;
    private float width;
    private float height;
    private int backgroundColor;
    private int textLength;
    private int backgroundSelected;
    private int borderColor;

    private boolean selected;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setBackgroundColor(int r, int g, int b) {
        backgroundColor = color(r, g, b);
    }

    public void setBorderColor(int r, int g, int b) {
        borderColor = color(r, g, b);
    }

    public void setX(float x) {
        this.pos.x = x;
    }

    public float getX() {
        return pos.x;
    }

    public void setY(float y) {
        this.pos.y = y;
    }

    public float getY() {
        return pos.y;
    }

    public void setPadding(float top, float left, float bottom, float right) {
        pos.x += left;
        pos.x -= right;
        pos.y += top;
        pos.y -= bottom;
    }

    public void render() {
        if (selected || hovering()) {
            pApplet.fill(backgroundSelected);
        }

        else {
            pApplet.fill(backgroundColor);
        }

        pApplet.rect(pos.x, pos.y, width, height);

        pApplet.fill(0);
        pApplet.text(text, pos.x + width / 2, pos.y + height / 2);
    }

    public boolean keyPressed(char key, int keycode) {
        if (selected) {
            if (keycode == (int) BACKSPACE) {
                backspace();
            }

            else if (keycode == 32) {
                addText(' ');
            }

            else if (keycode == (int) ENTER) {
                return true;
            }

            else {
                boolean isKeyCapitalLetter = (key >= 'A' && key <= 'Z');
                boolean isKeySmallLetter = (key >= 'a' && key <= 'z');
                boolean isKeyNumber = (key >= '0' && key <= '9');

                if (isKeyCapitalLetter || isKeySmallLetter || isKeyNumber) {
                    addText(key);
                }
            }

            render();
        }

        return false;
    }

    private void addText(char text) {
        if (pApplet.textWidth(this.text + text) < width) {
            this.text += text;
            textLength++;
        }
    }

    private void backspace() {
        if (textLength - 1 >= 0) {
            text = text.substring(0, textLength - 1);
            textLength--;
        }
    }

    private boolean overBox(float x, float y) {
        return x >= pos.x && x <= (pos.x + this.width)
                && y >= pos.y && y <= (pos.y + this.height);
    }

    public void pressed(float x, float y) {
        selected = overBox(x, y);
        render();
    }

    public void cursorTouched() {
        if (hovering()) {
            mouseOver();
        }

        render();
    }

    private boolean hovering() {
        return pApplet.mouseX >= pos.x && pApplet.mouseX <= (pos.x + this.width)
                && pApplet.mouseY >= pos.y && pApplet.mouseY <= (pos.y + this.height);
    }

    private void mouseOver() {
        pApplet.cursor(HAND);
    }
}
