// 檔名：AVLBasicExercise.java

class AVLBasicExercise {

    static class Node {

        int key, height;
        Node left, right;

        Node(int k) {
            key = k;
            height = 1;
        }
    }

    Node root;

    int height(Node n) {
        return n == null ? 0 : n.height;
    }

    int getBalance(Node n) {
        return n == null ? 0 : height(n.left) - height(n.right);
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

    Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }
        if (key < node.key) {
            node.left = insert(node.left, key); 
        }else if (key > node.key) {
            node.right = insert(node.right, key); 
        }else {
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        // LL
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }
        // RR
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }
        // LR
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    boolean search(Node node, int key) {
        if (node == null) {
            return false;
        }
        if (node.key == key) {
            return true;
        }
        return key < node.key ? search(node.left, key) : search(node.right, key);
    }

    boolean isValidAVL(Node node) {
        if (node == null) {
            return true;
        }
        int balance = getBalance(node);
        if (balance < -1 || balance > 1) {
            return false;
        }
        return isValidAVL(node.left) && isValidAVL(node.right);
    }

    public static void main(String[] args) {
        AVLBasicExercise avl = new AVLBasicExercise();
        avl.root = avl.insert(avl.root, 10);
        avl.root = avl.insert(avl.root, 20);
        avl.root = avl.insert(avl.root, 30);
        System.out.println(avl.search(avl.root, 20)); // true
        System.out.println(avl.isValidAVL(avl.root)); // true
    }
}
