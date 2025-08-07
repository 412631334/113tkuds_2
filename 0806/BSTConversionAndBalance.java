
public class BSTConversionAndBalance {

    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    Node root;

    // 1. 將 BST 轉換為排序的雙向鏈結串列
    Node head = null;
    Node prev = null;

    public Node bstToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        bstToDoublyListHelper(root);
        // 使首尾相連成環狀
        head.left = prev;
        prev.right = head;
        return head;
    }

    private void bstToDoublyListHelper(Node node) {
        if (node == null) {
            return;
        }
        bstToDoublyListHelper(node.left);
        if (prev == null) {
            head = node;
        } else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;
        bstToDoublyListHelper(node.right);
    }

    // 2. 將排序陣列轉換為平衡 BST
    public Node sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    private Node sortedArrayToBSTHelper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        Node node = new Node(nums[mid]);
        node.left = sortedArrayToBSTHelper(nums, left, mid - 1);
        node.right = sortedArrayToBSTHelper(nums, mid + 1, right);
        return node;
    }

    // 3. 檢查 BST 是否平衡 並計算平衡因子(左子樹高 - 右子樹高)
    public boolean isBalanced(Node root) {
        return checkHeight(root) != -1;
    }

    private int checkHeight(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) {
            return -1;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // 4. 將 BST 中每個節點值改為所有大於等於該節點值的總和
    int sum = 0;

    public void bstToGreaterSumTree(Node root) {
        if (root == null) {
            return;
        }
        bstToGreaterSumTree(root.right);
        sum += root.val;
        root.val = sum;
        bstToGreaterSumTree(root.left);
    }

    // 中序走訪列印
    public void inorderPrint(Node root) {
        if (root == null) {
            return;
        }
        inorderPrint(root.left);
        System.out.print(root.val + " ");
        inorderPrint(root.right);
    }

    // 測試
    public static void main(String[] args) {
        BSTConversionAndBalance bst = new BSTConversionAndBalance();

        int[] vals = {4, 1, 6, 0, 2, 5, 7};
        for (int v : vals) {
            bst.root = bst.insert(bst.root, v);
        }

        System.out.print("原 BST 中序: ");
        bst.inorderPrint(bst.root);
        System.out.println();

        Node head = bst.bstToDoublyList(bst.root);
        System.out.print("雙向鏈結串列從頭開始: ");
        Node cur = head;
        for (int i = 0; i < vals.length; i++) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        System.out.println();

        int[] sortedArr = {0, 1, 2, 3, 4, 5, 6, 7};
        Node balancedRoot = bst.sortedArrayToBST(sortedArr);
        System.out.print("平衡 BST 中序: ");
        bst.inorderPrint(balancedRoot);
        System.out.println();

        System.out.println("是否平衡: " + bst.isBalanced(balancedRoot));

        bst.sum = 0;
        bst.bstToGreaterSumTree(balancedRoot);
        System.out.print("Greater Sum Tree 中序: ");
        bst.inorderPrint(balancedRoot);
        System.out.println();
    }

    // 補 insert 方法
    private Node insert(Node node, int val) {
        if (node == null) {
            return new Node(val);
        }
        if (val < node.val) {
            node.left = insert(node.left, val); 
        }else {
            node.right = insert(node.right, val);
        }
        return node;
    }
}
