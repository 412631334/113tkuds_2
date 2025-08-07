
public class BSTValidationAndRepair {

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

    // 驗證是否為有效 BST
    public boolean isValidBST(Node root) {
        return isValidBSTHelper(root, null, null);
    }

    private boolean isValidBSTHelper(Node node, Integer low, Integer high) {
        if (node == null) {
            return true;
        }
        if (low != null && node.val <= low) {
            return false;
        }
        if (high != null && node.val >= high) {
            return false;
        }
        return isValidBSTHelper(node.left, low, node.val)
                && isValidBSTHelper(node.right, node.val, high);
    }

    // 找出不符合 BST 規則的節點（回傳節點數量）
    // 簡化版本：計算不在有效範圍的節點數
    public int countInvalidNodes(Node root) {
        return countInvalidNodesHelper(root, null, null);
    }

    private int countInvalidNodesHelper(Node node, Integer low, Integer high) {
        if (node == null) {
            return 0;
        }
        int count = 0;
        if ((low != null && node.val <= low) || (high != null && node.val >= high)) {
            count++;
        }
        count += countInvalidNodesHelper(node.left, low, node.val);
        count += countInvalidNodesHelper(node.right, node.val, high);
        return count;
    }

    // 修復兩節點錯位 BST（此題較複雜，這裡只提供偵測並回傳錯誤節點，修復需額外實作）
    // 計算需要移除多少節點才能變成有效 BST (簡化：回傳不符合節點數量)
    public int nodesToRemoveForValidBST() {
        return countInvalidNodes(root);
    }

    // 測試
    public static void main(String[] args) {
        BSTValidationAndRepair bst = new BSTValidationAndRepair();
        int[] vals = {10, 5, 15, 6, 20};
        for (int v : vals) {
            bst.insert(v);
        }

        System.out.println("是否有效BST: " + bst.isValidBST(bst.root));
        System.out.println("不符合BST規則節點數: " + bst.countInvalidNodes(bst.root));
        System.out.println("需移除節點數(簡化): " + bst.nodesToRemoveForValidBST());
    }
}
