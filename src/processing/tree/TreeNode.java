package processing.tree;

import processing.core.PApplet;
import processing.core.PVector;

class TreeNode<T> {
    public TreeNode(T data, float x, float y, PApplet pApplet) {
        this.left = null;
        this.right = null;
        this.parent = null;
        this.sibling = null;
        this.data = data;

        this.pos = new PVector(x, y);

        this.pApplet = pApplet;
    }

    public TreeNode(T data, TreeNode<T> parent, float x, float y, PApplet pApplet) {
        this.left = null;
        this.right = null;
        this.sibling = null;
        this.parent = parent;

        if (this.parent.getLeft() == this) {
            this.sibling = parent.right;
        }

        else if (this.parent.getRight() == this) {
            this.sibling = parent.left;
        }

        this.sibling = this.parent.left == this ? parent.right : parent.left;

        this.data = data;

        this.pos = new PVector(x, y);

        this.pApplet = pApplet;
    }

    private final PApplet pApplet;
    private final float width = 40;
    private final float height = 40;
    private PVector pos;

    public TreeNode<T> left;
    public TreeNode<T> right;
    public TreeNode<T> parent;
    public TreeNode<T> sibling;
    public T data;

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

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getLeft() {
        return this.left;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public TreeNode<T> getRight() {
        return this.right;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public TreeNode<T> getSibling() {
        return sibling;
    }

    public void setSibling(TreeNode<T> sibling) {
        this.sibling = sibling;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void render() {
        pApplet.noFill();
        pApplet.ellipse(pos.x + 20, pos.y - 5, width, height);

        pApplet.textAlign(pApplet.CENTER);
        pApplet.text(String.valueOf(data), pos.x + 20, pos.y);
    }

    public void createArrow() {
        if (this.parent != null) {
            if (this.getParent().getRight() == this) {
                createArrow(this.getParent().getX() + 20, this.getParent().getY() + 15, pos.x + 4, pos.y - 17);
            }

            else if (this.getParent().getLeft() == this) {
                createArrow(this.getParent().getX() + 20, this.getParent().getY() + 15, pos.x + width - 3, pos.y - 17);
            }
        }
    }

    public void createArrow(float x1, float y1, float x2, float y2) {
        pApplet.strokeWeight(0.50f);
        pApplet.line(x1, y1, x2, y2);

        pApplet.pushMatrix();
        pApplet.translate(x2, y2);

        float rotate = PApplet.atan2(x1-x2, y2-y1);
        pApplet.rotate(rotate);

        pApplet.line(0, 0, -10, -10);
        pApplet.line(0, 0, 10, -10);

        pApplet.popMatrix();
        pApplet.strokeWeight(1);
    }
}