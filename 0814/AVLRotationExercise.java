// 檔名：AVLRotationExercise.java

class AVLRotationExercise {

    static class Node {

        int key, height;
        Node left, right;

        Node(int k) {
            key = k;
            height = 1;
        }
    }

    int height(Node n) {
        return n == null ? 0 : n.height;
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    Node leftRightRotate(Node node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    Node rightLeftRotate(Node node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    public static void main(String[] args) {
        AVLRotationExercise r = new AVLRotationExercise();
        Node root = new Node(30);
        root.left = new Node(20);
        root.left.left = new Node(10);
        root = r.rightRotate(root);
        System.out.println("After right rotation root = " + root.key); // 20
    }
}
