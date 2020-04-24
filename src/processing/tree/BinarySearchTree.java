package processing.tree;

import processing.core.PApplet;

public class BinarySearchTree<T extends Comparable<T>> {
    public BinarySearchTree(PApplet pApplet) {
        this.root = null;
        this.pApplet = pApplet;
    }

    private TreeNode<T> root;
    private final PApplet pApplet;

    public void add(T data) {
        root = add(root, data, (pApplet.width / 2f) - 50, 100);

        display();
    }

    private TreeNode<T> add(TreeNode<T> node, T data, float x, float y) {
        if (root == null) {
            return new TreeNode<>(data, x, y, pApplet);
        }

        if (!search(data)) {
            if (data.compareTo(node.getData()) < 0) {
                if (node.getLeft() == null) {
                    node.setLeft(new TreeNode<>(data, pApplet), node, root);

                    if (node.getLeft().isDescendantOf(root.getRight())) {
                        node.moveLeftSiblings(node.getParent(), node.getLeft());
                    }
                }

                else {
                    node.setLeft(add(node.getLeft(), data, x, y), node, root);
                }
            }

            else if (data.compareTo(node.getData()) > 0) {
                if (node.getRight() == null) {
                    node.setRight(new TreeNode<>(data, pApplet), node);
                }

                else {
                    node.setRight(add(node.getRight(), data, x, y), node);
                }
            }
        }

        return node;
    }

    public void display() {
        pApplet.setup();

        levelOrder();
    }

    public void levelOrder() {
        if (root != null) {
            int level = getLevel(root);

            System.out.println();
            for (int i = 1; i <= level; i++) {
                levelOrder(root, i);
            }
        }
    }

    private void levelOrder(TreeNode<T> node, int level) {
        if (node == null) {
            return;
        }

//        System.out.println("LEVEL: " + level);

        if (level == 1) {
//            node.setX(node.isLeftChildren() ?  ((getLevel(root) - level) * 26) * -1 : (getLevel(root) - level) * 26);
            node.render();
//            System.out.println("NODE: " + node.getData());
//            System.out.println("NODE LEVEL: " + node.getLevel());
//            System.out.println("NODE WIDTH: " + node.getLevelWidth());
//            System.out.println("NODE HEIGHT: " + node.getLevelHeight());
//            System.out.println("NODE PARENT: " + (node.getParent() != null ? node.getParent().getData() : "null"));
//            System.out.println("NODE SIBLING: " + (node.getSibling() != null ? node.getSibling().getData() : null));
//            System.out.println("NODE SIBLING POSITION: " + node.isLeftChildren());
//            System.out.println("NODE LEFT: " + (node.getLeft() != null ? node.getLeft().getData() : "null"));
//            System.out.println("NODE RIGHT: " + (node.getRight() != null ? node.getRight().getData() : "null"));
//            System.out.println();
        }

        else if (level > 1) {
            levelOrder(node.getLeft(), level - 1);
            levelOrder(node.getRight(), level - 1);
        }
    }

    private int getLevel(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }

        int leftLevel = getLevel(node.getLeft());
        int rightLevel = getLevel(node.getRight());

        return leftLevel > rightLevel ? leftLevel + 1 : rightLevel + 1;
    }

    public void createArrow(float x1, float y1, float x2, float y2) {
        pApplet.strokeWeight((float) 0.50);
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

    public boolean search(T value) {
        return search(root, value);
    }

    private boolean search(TreeNode<T> node, T data) {
        if (node == null) {
            return false;
        }

        if (data.compareTo(node.getData()) < 0) {
            return search(node.getLeft(), data);
        }

        else if (data.compareTo(node.getData()) > 0) {
            return search(node.getRight(), data);
        }

        else return node.getData() == data;
    }
}