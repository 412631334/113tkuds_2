
import java.util.*;

public class BSTKthElement {

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

    // 中序走訪找第 k 小元素
    public int kthSmallest(int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return (k <= list.size()) ? list.get(k - 1) : -1;
    }

    private void inorder(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }

    // 找第 k 大元素 (利用中序走訪反向)
    public int kthLargest(int k) {
        List<Integer> list = new ArrayList<>();
        reverseInorder(root, list);
        return (k <= list.size()) ? list.get(k - 1) : -1;
    }

    private void reverseInorder(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }
        reverseInorder(node.right, list);
        list.add(node.val);
        reverseInorder(node.left, list);
    }

    // 找第 k 小到第 j 小之間元素
    public List<Integer> rangeKtoJ(int k, int j) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        if (k < 1) {
            k = 1;
        }
        if (j > list.size()) {
            j = list.size();
        }
        if (k > j) {
            return new ArrayList<>();
        }
        return list.subList(k - 1, j);
    }

    // 動態插入刪除待擴充（此範例未實作）
    // 測試
    public static void main(String[] args) {
        BSTKthElement bst = new BSTKthElement();
        int[] vals = {20, 8, 22, 4, 12, 10, 14};
        for (int v : vals) {
            bst.insert(v);
        }

        System.out.println("第 3 小元素: " + bst.kthSmallest(3));
        System.out.println("第 2 大元素: " + bst.kthLargest(2));
        System.out.println("第 2 到第 5 小元素: " + bst.rangeKtoJ(2, 5));
    }
}
