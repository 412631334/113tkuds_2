
import java.util.*;

public class BinaryTreeBasicOperations {

    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    Node root;

    // 建立測試樹（手動）
    public void buildSampleTree() {
        root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.left = new Node(3);
        root.left.right = new Node(7);
        root.right.right = new Node(18);
    }

    // 節點值總和
    public int sum(Node node) {
        if (node == null) {
            return 0;
        }
        return node.val + sum(node.left) + sum(node.right);
    }

    // 節點平均值
    public double average(Node node) {
        int[] res = countAndSum(node);
        return (double) res[1] / res[0];
    }

    // 回傳[節點數, 總和]
    private int[] countAndSum(Node node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] left = countAndSum(node.left);
        int[] right = countAndSum(node.right);
        return new int[]{
            left[0] + right[0] + 1,
            left[1] + right[1] + node.val
        };
    }

    // 最大節點值
    public int maxVal(Node node) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        return Math.max(node.val, Math.max(maxVal(node.left), maxVal(node.right)));
    }

    // 最小節點值
    public int minVal(Node node) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }
        return Math.min(node.val, Math.min(minVal(node.left), minVal(node.right)));
    }

    // 計算樹的寬度（每層節點數最大值）
    public int maxWidth(Node root) {
        if (root == null) {
            return 0;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int maxWidth = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            maxWidth = Math.max(maxWidth, size);
            for (int i = 0; i < size; i++) {
                Node curr = q.poll();
                if (curr.left != null) {
                    q.offer(curr.left);
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                }
            }
        }
        return maxWidth;
    }

    // 判斷是否為完全二元樹
    public boolean isComplete(Node root) {
        if (root == null) {
            return true;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        boolean mustBeLeaf = false;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (curr.left != null) {
                if (mustBeLeaf) {
                    return false;
                }
                q.offer(curr.left);
            } else {
                mustBeLeaf = true;
            }

            if (curr.right != null) {
                if (mustBeLeaf) {
                    return false;
                }
                q.offer(curr.right);
            } else {
                mustBeLeaf = true;
            }
        }
        return true;
    }

    // 測試
    public static void main(String[] args) {
        BinaryTreeBasicOperations tree = new BinaryTreeBasicOperations();
        tree.buildSampleTree();

        System.out.println("節點總和: " + tree.sum(tree.root));
        System.out.println("節點平均值: " + tree.average(tree.root));
        System.out.println("最大節點值: " + tree.maxVal(tree.root));
        System.out.println("最小節點值: " + tree.minVal(tree.root));
        System.out.println("樹的最大寬度: " + tree.maxWidth(tree.root));
        System.out.println("是否為完全二元樹: " + tree.isComplete(tree.root));
    }
}
