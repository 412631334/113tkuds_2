
import java.util.*;

public class M09_AVLValidate {

    static class TreeNode {

        int val;
        TreeNode left, right;

        TreeNode(int v) {
            val = v;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        TreeNode root = buildTree(arr);

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
        } else if (!isAVL(root).isValid) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
    }

    // 建樹（層序，與 M07、M08 相同）
    static TreeNode buildTree(int[] arr) {
        if (arr.length == 0 || arr[0] == -1) {
            return null;
        }
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            TreeNode node = q.poll();
            if (i < arr.length && arr[i] != -1) {
                node.left = new TreeNode(arr[i]);
                q.offer(node.left);
            }
            i++;
            if (i < arr.length && arr[i] != -1) {
                node.right = new TreeNode(arr[i]);
                q.offer(node.right);
            }
            i++;
        }
        return root;
    }

    // 檢查 BST (min < val < max)
    static boolean isBST(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return isBST(node.left, min, node.val)
                && isBST(node.right, node.val, max);
    }

    // 用來回傳「高度 + 是否為 AVL」
    static class AVLInfo {

        int height;
        boolean isValid;

        AVLInfo(int h, boolean v) {
            height = h;
            isValid = v;
        }
    }

    // 檢查 AVL
    static AVLInfo isAVL(TreeNode node) {
        if (node == null) {
            return new AVLInfo(0, true);
        }

        AVLInfo left = isAVL(node.left);
        AVLInfo right = isAVL(node.right);

        boolean valid = left.isValid && right.isValid
                && Math.abs(left.height - right.height) <= 1;
        int height = Math.max(left.height, right.height) + 1;

        return new AVLInfo(height, valid);
    }

}

/*
 * Time Complexity: O(n)
 * 說明：
 * - isBST 與 isAVL 都是一次 DFS，遍歷每個節點一次。
 * - 每個節點處理時間 O(1)，所以總時間 O(n)。
 */
