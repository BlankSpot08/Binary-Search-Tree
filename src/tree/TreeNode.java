package tree;

public class TreeNode<T> {
    public TreeNode(T data) {
        this.data = data;
        left = right = null;
    }

    private TreeNode<T> left;
    private TreeNode<T> right;
    private T data;

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
