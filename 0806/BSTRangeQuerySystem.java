
import java.util.*;

public class BSTRangeQuerySystem {

    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    Node root;

    public void insert(int val) {
        root = insertRec(root, val);
    }

    private Node insertRec(Node node, int val) {
        if (node == null) {
            return new Node(val);
        }
        if (val < node.val) {
            node.left = insertRec(node.left, val); 
        }else {
            node.right = insertRec(node.right, val);
        }
        return node;
    }

    // 範圍查詢回傳 List<Integer>
    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryRec(root, min, max, result);
        return result;
    }

    private void rangeQueryRec(Node node, int min, int max, List<Integer> result) {
        if (node == null) {
            return;
        }
        if (node.val > min) {
            rangeQueryRec(node.left, min, max, result);
        }
        if (node.val >= min && node.val <= max) {
            result.add(node.val);
        }
        if (node.val < max) {
            rangeQueryRec(node.right, min, max, result);
        }
    }

    // 範圍計數
    public int rangeCount(int min, int max) {
        return rangeCountRec(root, min, max);
    }

    private int rangeCountRec(Node node, int min, int max) {
        if (node == null) {
            return 0;
        }
        if (node.val < min) {
            return rangeCountRec(node.right, min, max); 
        }else if (node.val > max) {
            return rangeCountRec(node.left, min, max); 
        }else {
            return 1 + rangeCountRec(node.left, min, max) + rangeCountRec(node.right, min, max);
        }
    }

    // 範圍總和
    public int rangeSum(int min, int max) {
        return rangeSumRec(root, min, max);
    }

    private int rangeSumRec(Node node, int min, int max) {
        if (node == null) {
            return 0;
        }
        if (node.val < min) {
            return rangeSumRec(node.right, min, max); 
        }else if (node.val > max) {
            return rangeSumRec(node.left, min, max); 
        }else {
            return node.val + rangeSumRec(node.left, min, max) + rangeSumRec(node.right, min, max);
        }
    }

    // 找出最接近目標值的節點值
    public int closestValue(int target) {
        return closestValueRec(root, target, root.val);
    }

    private int closestValueRec(Node node, int target, int closest) {
        if (node == null) {
            return closest;
        }
        if (Math.abs(node.val - target) < Math.abs(closest - target)) {
            closest = node.val;
        }
        if (target < node.val) {
            return closestValueRec(node.left, target, closest); 
        }else {
            return closestValueRec(node.right, target, closest);
        }
    }

    // 測試
    public static void main(String[] args) {
        BSTRangeQuerySystem bst = new BSTRangeQuerySystem();
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        for (int v : values) {
            bst.insert(v);
        }

        System.out.println("範圍查詢 [10,30]: " + bst.rangeQuery(10, 30));
        System.out.println("範圍計數 [10,30]: " + bst.rangeCount(10, 30));
        System.out.println("範圍總和 [10,30]: " + bst.rangeSum(10, 30));
        System.out.println("最接近 12 的值: " + bst.closestValue(12));
    }
}
