package processing;

import processing.core.PApplet;
import processing.tree.BinarySearchTree;

public class ProcessingTest extends PApplet {
    public static void main(String... args) {
        String[] processingArgs = {"ProcessingTest", "Button", "TextBox"};
        ProcessingTest processingTest = new ProcessingTest();
        PApplet.runSketch(processingArgs, processingTest);
    }

    BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();

    Button enterButton = new Button(this);
    Button resetButton = new Button(this);
    Button quitButton = new Button(this);
    TextBox textBox = new TextBox(this);

    public void settings() {
        size(1000,800);
    }

    public void setup() {
        background(210);

        textBox.setId("InputGetterId");
        textBox.setWidth(245);
        textBox.setHeight(20);
        textBox.setX((width / 2) - (textBox.getWidth() / 2));
        textBox.setY(height - textBox.getHeight());
        textBox.setPadding(0, 0, 40, 0);
        textBox.setBackgroundColor(255, 255, 255);

        enterButton.setText("Insert");
        enterButton.setId("enterId");
        enterButton.setWidth(75);
        enterButton.setHeight(20);
        enterButton.setX((width / 2) - (textBox.getWidth() / 2));
        enterButton.setY(height - enterButton.getHeight());
        enterButton.setPadding(0, 0, 10, 0);
        enterButton.setBackgroundColor(255, 255, 255);

        resetButton.setText("Reset");
        resetButton.setId("resetId");
        resetButton.setWidth(75);
        resetButton.setHeight(20);
        resetButton.setX((width / 2) - (textBox.getWidth() / 2) + enterButton.getWidth());
        resetButton.setY(height - enterButton.getHeight());
        resetButton.setPadding(0, 10, 10, 0);
        resetButton.setBackgroundColor(255, 255, 255);

        quitButton.setText("Quit");
        quitButton.setId("quitId");
        quitButton.setWidth(75);
        quitButton.setHeight(20);
        quitButton.setX((width / 2) - (textBox.getWidth() / 2) + enterButton.getWidth() + resetButton.getWidth());
        quitButton.setY(height - enterButton.getHeight());
        quitButton.setPadding(0, 20, 10, 0);
        quitButton.setBackgroundColor(255, 255, 255);

        textBox.render();
        enterButton.render();
        resetButton.render();
        quitButton.render();
    }

    public void draw() {
    }

    void InintLayout() {

    }

    public void mousePressed() {
        textBox.pressed(mouseX, mouseY);

    }

    public void keyPressed() {
        textBox.keyPressed(key, keyCode);
    }

    public void mouseMoved() {
        cursor(ARROW);

        enterButton.cursorTouched();
        resetButton.cursorTouched();
        quitButton.cursorTouched();
        textBox.cursorTouched();
    }
}
