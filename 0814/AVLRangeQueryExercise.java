// 檔名：AVLRangeQueryExercise.java

import java.util.*;

class AVLRangeQueryExercise {

    static class Node {

        int key;
        Node left, right;

        Node(int k) {
            key = k;
        }
    }

    List<Integer> rangeQuery(Node root, int min, int max) {
        List<Integer> res = new ArrayList<>();
        inorder(root, min, max, res);
        return res;
    }

    void inorder(Node node, int min, int max, List<Integer> res) {
        if (node == null) {
            return;
        }
        if (node.key > min) {
            inorder(node.left, min, max, res);
        }
        if (node.key >= min && node.key <= max) {
            res.add(node.key);
        }
        if (node.key < max) {
            inorder(node.right, min, max, res);
        }
    }

    public static void main(String[] args) {
        AVLRangeQueryExercise q = new AVLRangeQueryExercise();
        Node root = new Node(20);
        root.left = new Node(10);
        root.right = new Node(30);
        System.out.println(q.rangeQuery(root, 15, 30)); // [20,30]
    }
}
