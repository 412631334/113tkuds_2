
public class TreeMirrorAndSymmetry {

    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    // 判斷是否為對稱樹
    public static boolean isSymmetric(Node root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(Node a, Node b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return (a.val == b.val) && isMirror(a.left, b.right) && isMirror(a.right, b.left);
    }

    // 建立鏡像樹
    public static Node mirrorTree(Node root) {
        if (root == null) {
            return null;
        }
        Node left = mirrorTree(root.left);
        Node right = mirrorTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    // 判斷兩棵樹是否為鏡像
    public static boolean areMirror(Node a, Node b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return (a.val == b.val) && areMirror(a.left, b.right) && areMirror(a.right, b.left);
    }

    // 判斷一棵樹是否為另一棵樹的子樹
    public static boolean isSubtree(Node root, Node subRoot) {
        if (subRoot == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (isSameTree(root, subRoot)) {
            return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private static boolean isSameTree(Node a, Node b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.val != b.val) {
            return false;
        }
        return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }

    // 測試
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right.left = new Node(4);
        root.right.right = new Node(3);

        System.out.println("是否對稱樹: " + isSymmetric(root));

        Node mirror = mirrorTree(root);
        System.out.println("鏡像樹根節點值: " + mirror.val);

        Node treeA = new Node(1);
        treeA.left = new Node(2);
        treeA.right = new Node(3);

        Node treeB = new Node(1);
        treeB.left = new Node(3);
        treeB.right = new Node(2);

        System.out.println("兩樹是否鏡像: " + areMirror(treeA, treeB));

        Node subRoot = new Node(2);
        subRoot.left = new Node(3);
        System.out.println("是否為子樹: " + isSubtree(treeA, subRoot));
    }
}
