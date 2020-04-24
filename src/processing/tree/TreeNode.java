package processing.tree;

import processing.core.PApplet;
import processing.core.PVector;

public class TreeNode<T extends Comparable<T>> {
    public TreeNode(T data, PApplet pApplet) {
        this.pApplet = pApplet;
        this.data = data;

        this.pos = new PVector(pApplet.width / 2f - levelWidth / 2f, 100);
        this.left = null;
        this.right = null;
        this.parent = null;
        this.sibling = null;
    }

    public TreeNode(T data, float x, float y, PApplet pApplet) {
        this.data = data;

        this.pos = new PVector(x, y);

        this.pApplet = pApplet;

        isLeftChildren = false;
        isRightChildren = false;
    }

    private final PApplet pApplet;
    private final float width = 32;
    private final float height = 32;
    private final PVector pos;

    private int levelHeight = 100;
    private int levelWidth = 1;
    private int level = 1;

    private boolean isRightChildren;
    private boolean isLeftChildren;

    private TreeNode<T> left;
    private TreeNode<T> right;
    private TreeNode<T> parent;
    private TreeNode<T> sibling;
    private T data;

    public void setX(float x) {
        this.pos.x = x;
    }

    public float getX() {
        return this.pos.x;
    }

    public void setY(float y) {
        if (this.parent != null) {
            this.pos.y = this.parent.pos.y + y;
        }
    }

    public float getY() {
        return this.pos.y;
    }

    public boolean isRightChildren() {
        return isRightChildren;
    }

    public void setRightChildren(boolean rightChildren) {
        isRightChildren = rightChildren;
    }

    public boolean isLeftChildren() {
        return isLeftChildren;
    }

    public void setLeftChildren(boolean leftChildren) {
        isLeftChildren = leftChildren;
    }

    public void setLeft(TreeNode<T> left, TreeNode<T> node, TreeNode<T> root) {
        if (this.left == null) {
            this.left = left;

            this.left.parent = node;
            this.left.level = this.left.parent.level + 1;

            this.left.sibling = node.right;

            this.left.pos.x = node.pos.x - 42;
            this.left.pos.y = node.pos.y + 50;

            this.left.setLeftChildren(true);
        }
    }

    public boolean isAncestorOf(TreeNode<T> node) {
        return isAncestorOf(node, this);
    }

    private boolean isAncestorOf(TreeNode<T> node, TreeNode<T> ancestor) {
        if (node.parent != null) {
            if (node == ancestor) {
                return true;
            }

            return isAncestorOf(node.parent, this);
        }

        return false;
    }

    public boolean isDescendantOf(TreeNode<T> ancestor) {
        return isDescendantOf(this, ancestor);
    }

    private boolean isDescendantOf(TreeNode<T> descendant, TreeNode<T> ancestor) {
        if (descendant.parent != null) {
            if (descendant == ancestor) {
                return true;
            }

            return isDescendantOf(descendant.parent, ancestor);
        }

        return false;
    }

    public void moveLeftRightSiblings(TreeNode<T> node, TreeNode<T> main) {
        moveLeftRight(node, main);
    }

    private void moveLeftRight(TreeNode<T> node, TreeNode<T> main) {
        System.out.println("CURRENT DATA: " + node.data);
        traverseLeftRight(node, node, main);
        System.out.println();

        if (node.parent != null) {
            moveLeftRight(node.parent, main);
        }
    }

    private void traverseLeftRight(TreeNode<T> node, TreeNode<T> currentNode, TreeNode<T> main) {
        if (node != null) {
            if (node.right != null && !node.right.isAncestorOf(main)) {
                traverseLeftRight(node.right, currentNode, main);
            }

            if (node != currentNode) {
                node.pos.x = node.pos.x + 22;
            }

            if (node.left != null && !node.left.isAncestorOf(main) && node.left.data.compareTo(main.data) > 0) {
                traverseLeftRight(node.left, currentNode, main  );
            }
        }
    }

    public void moveRightRightSiblings(TreeNode<T> node, TreeNode<T> main) {
        moveRightRight(node, main);
    }

    private void moveRightRight(TreeNode<T> node, TreeNode<T> main) {
        System.out.println("CURRENT NODE: " + node.data);
        traverseRightRight(node, node, main);
        System.out.println();

        if (node.parent != null) {
            moveRightRight(node.parent, main);
        }
    }

    private void traverseRightRight(TreeNode<T> node, TreeNode<T> currentNode, TreeNode<T> main) {
        if (node != null) {
            if (node.right != null && !node.right.isAncestorOf(main)) {
                traverseRightRight(node.right, currentNode, main);
            }

            if (node != currentNode) {
                node.pos.x = node.pos.x + 22;
            }

            if (node.left != null && !node.left.isAncestorOf(main) && node.left.data.compareTo(main.data) > 0) {
                traverseRightRight(node.left, currentNode, main);
            }
        }
    }

    public void moveRightLeftSiblings(TreeNode<T> node, TreeNode<T> main) {
        moveRightLeft(node, main);
    }

    private void moveRightLeft(TreeNode<T> node, TreeNode<T> main) {
        System.out.println("CURRENT NODE: " + node.data);

        traverseRightLeft(node, node, main);

        System.out.println();
        if (node.parent != null) {
            moveRightLeft(node.parent, main);
        }
    }

    private void traverseRightLeft(TreeNode<T> node, TreeNode<T> currentNode, TreeNode<T> main) {
        if (node != null) {
            if (node.getLeft() != null && !node.getLeft().isAncestorOf(main)) {
                traverseRightLeft(node.getLeft(), currentNode, main);
            }

            if (node != currentNode) {
                node.setX(node.getX() - 22);
            }

            if (node.getRight() != null && !node.getRight().isAncestorOf(main) && !node.right.parent.isAncestorOf(main)) {
                traverseRightLeft(node.getRight(), currentNode, main);
            }
        }
    }

    public TreeNode<T> getLeft() {
        return this.left;
    }

    public void setRight(TreeNode<T> right, TreeNode<T> node) {
        if (this.right == null) {
            this.right = right;

            this.right.parent = node;
            this.right.level = this.right.parent.level + 1;

            this.right.sibling = node.left;

            this.right.pos.x = node.pos.x + 42;
            this.right.pos.y = node.pos.y + 50;

            this.right.setRightChildren(true);
        }
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

    public int getLevelHeight() {
        return levelHeight + ((getLevel() - 1) * 62);
    }

    public void setLevelHeight(int levelHeight) {
        this.levelHeight = levelHeight;
    }

    public int getLevelWidth() {
        return ((((int) Math.pow(2, getLevel() - 1) - 1) * 2) + 1) * 42;
    }

    public void setLevelWidth(int levelWidth) {
        this.levelWidth = levelWidth;
    }

    public void setLevel(int level) {
        if (this.parent != null) {
            this.level = parent.level + 1;
        }
    }

    public int getLevel() {
        return level;
    }

    public void render() {
        createArrow();
        pApplet.noFill();
        pApplet.ellipse(pos.x, pos.y, width, height);

        pApplet.textAlign(pApplet.CENTER);
        pApplet.textSize(12);
        pApplet.text(String.valueOf(data), pos.x, pos.y + 5);
        pApplet.textSize(14);
    }

    public void createArrow() {
        if (parent != null) {
            if (isRightChildren) {
                createArrow(getParent().getX(), getParent().getY() + 15, pos.x - 15 , pos.y - 15);
            }

            else if (isLeftChildren) {
                createArrow(getParent().getX(), getParent().getY() + 15, pos.x + 15, pos.y - 15);
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