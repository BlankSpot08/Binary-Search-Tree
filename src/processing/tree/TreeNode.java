package processing.tree;

import processing.core.PApplet;

class TreeNode<T> extends PApplet {
    public TreeNode(T data, float x, float y) {
        this.left = null;
        this.right = null;
        this.data = data;
        this.x = x;
        this.y = y;

        noFill();
        text(String.valueOf(data), x, y);
        ellipse(x + 15, y - 5, 30, 30);
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