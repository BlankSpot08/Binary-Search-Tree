package processing.tree;


import processing.core.PApplet;

public class BinarySearchTree<T extends Comparable<T>> extends PApplet {
    public BinarySearchTree() {
        this.root = null;
    }

    TreeNode<T> root;

    public void add(T data) {
        root = add(root, data, width / 2, 25);
    }

    private TreeNode<T> add(TreeNode<T> node, T data, float x, float y) {
        if (root == null) {
            return new TreeNode<T> (data, x, y);
        }

        if (node == null) {
            return new TreeNode<T> (data, x, y);
        }

        if (data.compareTo(node.getData()) < 0) {
            line(x, y, node.getX() - 50, node.getY() + 40);
            node.setLeft(add(node.getLeft(), data, node.getX() - 50, node.getY() + 40));
        }

        else if (data.compareTo(node.getData()) > 0) {
            line(x, y, node.getX() + 50, node.getY() + 40);
            node.setRight(add(node.getRight(), data, node.getX() + 50, node.getY() + 40));
        }

        return node;
    }

    public void delete(T data) {
        root = delete(root, data);
    }

    private TreeNode<T> delete(TreeNode<T> node, T data) {
        if (node == null) {
            return node;
        }

        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(delete(node.getLeft(), data));
        }

        else if (data.compareTo(node.getData()) > 0) {
            node.setRight(delete(node.getRight(), data));
        }

        else {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }

            else if (node.getLeft() != null && node.getRight() != null) {

            }

            else if (node.getLeft() == null) {
                return node.getRight();
            }

            else if (node.getRight() == null) {
                return node.getLeft();
            }
        }

        return node;
    }
}