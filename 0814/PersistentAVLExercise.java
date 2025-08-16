// 檔名：PersistentAVLExercise.java

import java.util.*;

class PersistentAVLExercise {

    static class Node {

        final int key, height;
        final Node left, right;

        Node(int k, Node l, Node r) {
            key = k;
            left = l;
            right = r;
            height = 1 + Math.max(height(l), height(r));
        }
    }

    static int height(Node n) {
        return n == null ? 0 : n.height;
    }

    static int balance(Node n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    static Node rotateRight(Node y) {
        Node x = y.left, T2 = x.right;
        return new Node(x.key, x.left, new Node(y.key, T2, y.right));
    }

    static Node rotateLeft(Node x) {
        Node y = x.right, T2 = y.left;
        return new Node(y.key, new Node(x.key, x.left, T2), y.right);
    }

    static Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key, null, null);
        }
        if (key < node.key) {
            node = new Node(node.key, insert(node.left, key), node.right); 
        }else if (key > node.key) {
            node = new Node(node.key, node.left, insert(node.right, key)); 
        }else {
            return node;
        }

        int bal = balance(node);
        if (bal > 1 && key < node.left.key) {
            return rotateRight(node);
        }
        if (bal < -1 && key > node.right.key) {
            return rotateLeft(node);
        }
        if (bal > 1 && key > node.left.key) {
            return rotateRight(new Node(node.key, rotateLeft(node.left), node.right));
        }
        if (bal < -1 && key < node.right.key) {
            return rotateLeft(new Node(node.key, node.left, rotateRight(node.right)));
        }
        return node;
    }

    public static void main(String[] args) {
        List<Node> versions = new ArrayList<>();
        Node root = null;
        root = insert(root, 10);
        versions.add(root);
        root = insert(root, 20);
        versions.add(root);
        root = insert(root, 5);
        versions.add(root);
        System.out.println("Version 1 root = " + versions.get(0).key); // 10
        System.out.println("Version 2 root = " + versions.get(1).key); // 10
        System.out.println("Version 3 root = " + versions.get(2).key); // 10
    }
}
