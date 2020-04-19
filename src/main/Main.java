package main;

import tree.BinarySearchTree;

public class Main{
    protected void start() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.add(7);
        tree.add(4);
        tree.add(2);
        tree.add(2);
        tree.add(6);
        tree.add(8);
        tree.add(12);

        tree.postorder();

        tree.display();
    }
}
