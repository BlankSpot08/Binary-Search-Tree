package processing.main;

import processing.core.PApplet;
import processing.graphics.Button;
import processing.graphics.TextBox;
import processing.tree.BinarySearchTree;

public class ProcessingTest extends PApplet {
    public static void main(String... args) {
        String[] processingArgs = { "ProcessingTest" };
        ProcessingTest processingTest = new ProcessingTest();
        PApplet.runSketch(processingArgs, processingTest);
    }

    PApplet pApplet = this;
    BinarySearchTree<Integer> tree = new BinarySearchTree<>(pApplet);

    Button enterButton = new Button(pApplet) {

        @Override
        public void setOnAction() {
            if (textBox.getText().length() != 0) {
                tree.add(Integer.valueOf(textBox.getText()));

                textBox.setText("");
                textBox.render();

                tree.display();
            }
        }

    };

    Button resetButton = new Button(pApplet) {

        @Override
        public void setOnAction() {
            BinarySearchTree<Integer> newTree = new BinarySearchTree<>(pApplet);

            tree = newTree;
            textBox.setText("");

            setup();
        }

    };

    Button quitButton = new Button(pApplet) {

        @Override
        public void setOnAction() {
            exit();
        }

    };

    TextBox textBox = new TextBox(pApplet) {

        @Override
        public void setOnPressedKeyAction(char key, int keyCode) {

            if (textBox.keyPressed(key, keyCode)) {
                tree.add(Integer.valueOf(textBox.getText()));

                textBox.setText("");
                textBox.render();

                tree.display();
            }

        }
    };

    public void settings() {
        size(1000,800);
    }

    public void setup() {
        background(210);
        textSize(14);

        textBox.setWidth(245);
        textBox.setHeight(20);
        textBox.setX((width / 2) - (textBox.getWidth() / 2));
        textBox.setY(height - textBox.getHeight());
        textBox.setPadding(0, 0, 40, 0);
        textBox.setBackgroundColor(255, 255, 255);
        textBox.setDisableLetters(true);

        enterButton.setText("Insert");
        enterButton.setWidth(75);
        enterButton.setHeight(20);
        enterButton.setX((width / 2) - (textBox.getWidth() / 2));
        enterButton.setY(height - enterButton.getHeight());
        enterButton.setPadding(0, 0, 10, 0);
        enterButton.setBackgroundColor(255, 255, 255);

        resetButton.setText("Reset");
        resetButton.setWidth(75);
        resetButton.setHeight(20);
        resetButton.setX((width / 2) - (textBox.getWidth() / 2) + enterButton.getWidth());
        resetButton.setY(height - enterButton.getHeight());
        resetButton.setPadding(0, 10, 10, 0);
        resetButton.setBackgroundColor(255, 255, 255);

        quitButton.setText("Quit");
        quitButton.setWidth(75);
        quitButton.setHeight(20);
        quitButton.setX((width / 2) - (textBox.getWidth() / 2) + enterButton.getWidth() + resetButton.getWidth());
        quitButton.setY(height - enterButton.getHeight());
        quitButton.setPadding(0, 20, 10, 0);
        quitButton.setBackgroundColor(255, 255, 255);

        render();
    }

    public void draw() {
//        tree.add(2);
//        tree.add(3);
//        tree.add(1);
//        tree.add(6);
//        tree.add(10);
//        tree.add(5);
//        tree.add(7);
//        tree.add(4);
    }

    public void mousePressed() {
        textBox.pressed(mouseX, mouseY);

        enterButton.pressed(mouseX, mouseY);

        quitButton.pressed(mouseX, mouseY);

        resetButton.pressed(mouseX, mouseY);
    }

    public void keyPressed() {
        textBox.setOnPressedKeyAction(key, keyCode);
    }

    public void mouseMoved() {
        cursor(ARROW);

        enterButton.cursorTouched();
        resetButton.cursorTouched();
        quitButton.cursorTouched();
        textBox.cursorTouched();

    }

    public void createTitle() {
        String title = "Binary Search Tree";
        textSize(40);
        text(title, width / 2 - title.length() , 20);
        textSize(14);
    }

    public void render() {
        textBox.render();
        enterButton.render();
        resetButton.render();
        quitButton.render();
        createTitle();
    }
}
