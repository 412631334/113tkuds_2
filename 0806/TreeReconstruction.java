
import java.util.*;

public class TreeReconstruction {

    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    // 1. 根據前序和中序重建二元樹
    private int preIndex = 0;
    private Map<Integer, Integer> inorderMap;

    public Node buildTreePreIn(int[] preorder, int[] inorder) {
        preIndex = 0;
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTreePreInHelper(preorder, 0, inorder.length - 1);
    }

    private Node buildTreePreInHelper(int[] preorder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }
        int rootVal = preorder[preIndex++];
        Node root = new Node(rootVal);
        int inIndex = inorderMap.get(rootVal);

        root.left = buildTreePreInHelper(preorder, inStart, inIndex - 1);
        root.right = buildTreePreInHelper(preorder, inIndex + 1, inEnd);
        return root;
    }

    // 2. 根據後序和中序重建二元樹
    private int postIndex;

    public Node buildTreePostIn(int[] postorder, int[] inorder) {
        postIndex = postorder.length - 1;
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTreePostInHelper(postorder, 0, inorder.length - 1);
    }

    private Node buildTreePostInHelper(int[] postorder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }
        int rootVal = postorder[postIndex--];
        Node root = new Node(rootVal);
        int inIndex = inorderMap.get(rootVal);

        root.right = buildTreePostInHelper(postorder, inIndex + 1, inEnd);
        root.left = buildTreePostInHelper(postorder, inStart, inIndex - 1);
        return root;
    }

    // 3. 根據層序重建完全二元樹
    public Node buildTreeLevelOrder(Integer[] levelorder) {
        if (levelorder.length == 0) {
            return null;
        }
        Node root = new Node(levelorder[0]);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty() && i < levelorder.length) {
            Node curr = queue.poll();
            if (levelorder[i] != null) {
                curr.left = new Node(levelorder[i]);
                queue.offer(curr.left);
            }
            i++;
            if (i < levelorder.length && levelorder[i] != null) {
                curr.right = new Node(levelorder[i]);
                queue.offer(curr.right);
            }
            i++;
        }
        return root;
    }

    // 驗證重建的樹(以中序走訪列印)
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
        TreeReconstruction tr = new TreeReconstruction();

        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        Node root1 = tr.buildTreePreIn(preorder, inorder);
        System.out.print("前序+中序重建中序列印: ");
        tr.inorderPrint(root1);
        System.out.println();

        int[] postorder = {9, 15, 7, 20, 3};
        Node root2 = tr.buildTreePostIn(postorder, inorder);
        System.out.print("後序+中序重建中序列印: ");
        tr.inorderPrint(root2);
        System.out.println();

        Integer[] levelorder = {1, 2, 3, 4, 5, 6, 7};
        Node root3 = tr.buildTreeLevelOrder(levelorder);
        System.out.print("層序重建中序列印: ");
        tr.inorderPrint(root3);
        System.out.println();
    }
}
