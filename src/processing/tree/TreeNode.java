package processing.tree;

import processing.core.PApplet;

class TreeNode<T> {
    public TreeNode(T data, float x, float y, PApplet pApplet) {
        this.left = null;
        this.right = null;
        this.data = data;
        this.x = x;
        this.y = y;

        pApplet.noFill();
        pApplet.ellipse(x + 20, y - 5, 40, 40);

        pApplet.textAlign(pApplet.CENTER);
        pApplet.text(String.valueOf(data), (x + 20), y);
    }

    private float x;
    private float y;
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T data;

    protected void setX(float x) {
        this.x = x;
    }

    protected float getX() {
        return this.x;
    }

    protected void setY(float y) {
        this.y = y;
    }

    protected float getY() {
        return this.y;
    }

    protected void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    protected TreeNode<T> getLeft() {
        return this.left;
    }

    protected void setRight(TreeNode<T> right) {
        this.right = right;
    }

    protected TreeNode<T> getRight() {
        return this.right;
    }

    protected void setData(T data) {
        this.data = data;
    }

    protected T getData() {
        return this.data;
    }
}