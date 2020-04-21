package processing.tree;


import processing.core.PApplet;

public class BinarySearchTree<T extends Comparable<T>> {
    public BinarySearchTree(PApplet pApplet) {
        this.root = null;
        this.pApplet = pApplet;
    }

    TreeNode<T> root;
    PApplet pApplet;

    public void add(T data) {
        root = add(root, data, pApplet.width / 2, 100);
    }

    private TreeNode<T> add(TreeNode<T> node, T data, float x, float y) {
        if (root == null) {
            return new TreeNode<T> (data, x, y, pApplet);
        }

        if (node == null) {
            return new TreeNode<T> (data, x, y, pApplet);
        }

        if (data.compareTo(node.getData()) < 0) {
            createArrow(x + 20, y + 15, node.getX() - 15, node.getY() + 23);
            node.setLeft(add(node.getLeft(), data, node.getX() - 50, node.getY() + 40));
        }

        else if (data.compareTo(node.getData()) > 0) {
            createArrow(x + 20, y + 15, node.getX() + 56, node.getY() + 23);
            node.setRight(add(node.getRight(), data, node.getX() + 50, node.getY() + 40));
        }

        return node;
    }

//    public void delete(T data) {
//        root = delete(root, data);
//    }
//
//    private TreeNode<T> delete(TreeNode<T> node, T data) {
//        if (node == null) {
//            return node;
//        }
//
//        if (data.compareTo(node.getData()) < 0) {
//            node.setLeft(delete(node.getLeft(), data));
//        }
//
//        else if (data.compareTo(node.getData()) > 0) {
//            node.setRight(delete(node.getRight(), data));
//        }
//
//        else {
//            if (node.getLeft() == null && node.getRight() == null) {
//                return null;
//            }
//
//            else if (node.getLeft() != null && node.getRight() != null) {
//                node.setData(node.getLeft().getData().compareTo(node.getRight().getData()) < 0 ? node.getLeft().getData() : node.getRight().getData());
//
//                node.setRight(delete(node.getRight(), node.getData()));
//            }
//
//            else if (node.getLeft() == null) {
//                return node.getRight();
//            }
//
//            else if (node.getRight() == null) {
//                return node.getLeft();
//            }
//        }
//
//        return node;
//    }

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
}