package processing;

import processing.core.PVector;
import processing.core.PApplet;

public class Button extends PApplet {
    public Button(PApplet pApplet)  {
        this.text = text;
        this.width = 10;
        this.height = 10;
        this.backgroundColor = color(255, 255, 255);
        this.backgroundSelected = color(150);
        this.borderColor = color(0, 0, 0);
        this.pos = new PVector(0, 0);
        this.pApplet = pApplet;
    }

    private PApplet pApplet;
    private PVector pos;
    private String text;
    private String id;
    private float width;
    private float height;
    private int backgroundColor;
    private int borderColor;
    private int backgroundSelected;

    boolean pressed;
    boolean clicked;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getWidth() {
        return this.width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getHeight() {
        return this.height;
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void setX(float x) {
        this.pos.x = x;
    }

    public float getX() {
        return this.pos.x;
    }

    public void setY(float y) {
        this.pos.y = y;
    }

    public float getY() {
        return this.pos.y;
    }

    public void setBackgroundColor(int r, int g, int b) {
        backgroundColor = color(r, g, b);
    }

    public void setBorderColor(int r, int g, int b) {
        borderColor = color(r, g, b);
    }

    public void render() {
        pApplet.stroke(borderColor);

        if (hovering()) {
            pApplet.fill(backgroundSelected);
        }

        else {
            pApplet.fill(backgroundColor);
        }

        pApplet.rect(pos.x, pos.y, width, height);

        pApplet.textAlign(CENTER, CENTER);
        pApplet.fill(0);
        pApplet.text(text, pos.x + (width / 2), pos.y + (height / 2));

    }

    public void setPadding(float top, float left, float bottom, float right) {
        pos.x += left;
        pos.x -= right;
        pos.y += top;
        pos.y -= bottom;
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

    public void setOnClickedAction() {

    };
}
