package tree;

public class BinarySearchTree<T extends Comparable<T>> {
    public BinarySearchTree() {
        root = null;
    }

    private TreeNode<T> root;
    private int level;

    public void add(T data) {
        root = add(root, data);
    }

    private TreeNode<T> add(TreeNode<T> node, T data) {
        if (node == null) {
            return new TreeNode<>(data);
        }

        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(add(node.getLeft(), data));
        }

        else if (data.compareTo(node.getData()) > 0) {
            node.setRight(add(node.getRight(), data));
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
                node.setData(node.getLeft().getData().compareTo(node.getRight().getData()) < 0 ? node.getLeft().getData() : node.getRight().getData());

                node.setRight(delete(node.getRight(), node.getData()));
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

    public int count() {
        return count(root);
    }

    private int count(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }

        int i = 1;
        i += count(root.getRight());
        i += count(root.getLeft());

        return i;
    }

    public void levelOrder() {
        if (root != null) {
            int level = getLevel(root);

            for (int i = 1; i <= level; i++) {
                levelOrder(root, i);
            }
        }
    }

    private void levelOrder(TreeNode<T> node, int level) {
        if (node == null) {
            return;
        }

        if (level == 1) {
            System.out.print(node.getData() + " ");
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

    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(TreeNode<T> node) {
        if (node != null) {
            inorder(node.getLeft());
            System.out.print(node.getData() + " ");
            inorder(node.getRight());
        }
    }

    public void preorder() {
        if (root != null) {
            preorder(root);
            System.out.println();
        }
    }

    private void preorder(TreeNode<T> node) {
        if (node != null) {
            System.out.print(node.getData() + " ");
            preorder(node.getLeft());
            preorder(node.getRight());
        }
    }

    public void postorder() {
        postorder(root);
        System.out.println();
    }

    private void postorder(TreeNode<T> node) {
        if (node != null) {
            postorder(node.getLeft());
            postorder(node.getRight());
            System.out.print(node.getData() + " ");
        }
    }

    public boolean isEmpty() {
        return root == null;
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
