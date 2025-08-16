// 檔名：AVLLeaderboardSystem.java

class AVLLeaderboardSystem {

    static class Node {

        int score, height, size;
        Node left, right;

        Node(int s) {
            score = s;
            height = 1;
            size = 1;
        }
    }

    Node root;

    int height(Node n) {
        return n == null ? 0 : n.height;
    }

    int size(Node n) {
        return n == null ? 0 : n.size;
    }

    void update(Node n) {
        if (n != null) {
            n.height = 1 + Math.max(height(n.left), height(n.right));
            n.size = 1 + size(n.left) + size(n.right);
        }
    }

    Node rightRotate(Node y) {
        Node x = y.left, T2 = x.right;
        x.right = y;
        y.left = T2;
        update(y);
        update(x);
        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right, T2 = y.left;
        y.left = x;
        x.right = T2;
        update(x);
        update(y);
        return y;
    }

    int getBalance(Node n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    Node insert(Node node, int score) {
        if (node == null) {
            return new Node(score);
        }
        if (score < node.score) {
            node.left = insert(node.left, score); 
        }else {
            node.right = insert(node.right, score);
        }
        update(node);
        int balance = getBalance(node);
        if (balance > 1 && score < node.left.score) {
            return rightRotate(node);
        }
        if (balance < -1 && score > node.right.score) {
            return leftRotate(node);
        }
        if (balance > 1 && score > node.left.score) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && score < node.right.score) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    int rank(Node node, int score) {
        if (node == null) {
            return 0;
        }
        if (score < node.score) {
            return rank(node.left, score); 
        }else if (score > node.score) {
            return size(node.left) + 1 + rank(node.right, score); 
        }else {
            return size(node.left) + 1;
        }
    }

    int select(Node node, int k) {
        if (node == null) {
            return -1;
        }
        int leftSize = size(node.left);
        if (k == leftSize + 1) {
            return node.score;
        }
        if (k <= leftSize) {
            return select(node.left, k); 
        }else {
            return select(node.right, k - leftSize - 1);
        }
    }

    public static void main(String[] args) {
        AVLLeaderboardSystem sys = new AVLLeaderboardSystem();
        sys.root = sys.insert(sys.root, 100);
        sys.root = sys.insert(sys.root, 200);
        sys.root = sys.insert(sys.root, 150);
        System.out.println("Rank of 150 = " + sys.rank(sys.root, 150)); // 2
        System.out.println("Top 1 = " + sys.select(sys.root, 1)); // 100
    }
}
