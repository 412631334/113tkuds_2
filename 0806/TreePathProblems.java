
import java.util.*;

public class TreePathProblems {

    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    Node root;

    // 1. 找出所有從根到葉的路徑
    public List<List<Integer>> allRootToLeafPaths(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        dfsPaths(root, new ArrayList<>(), res);
        return res;
    }

    private void dfsPaths(Node node, List<Integer> path, List<List<Integer>> res) {
        if (node == null) {
            return;
        }
        path.add(node.val);
        if (node.left == null && node.right == null) {
            res.add(new ArrayList<>(path));
        } else {
            dfsPaths(node.left, path, res);
            dfsPaths(node.right, path, res);
        }
        path.remove(path.size() - 1);
    }

    // 2. 判斷是否存在根到葉路徑和為目標值
    public boolean hasPathSum(Node root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // 3. 找出和最大的根到葉路徑
    public int maxRootToLeafSum(Node root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        return root.val + Math.max(maxRootToLeafSum(root.left), maxRootToLeafSum(root.right));
    }

    // 4. 計算樹的直徑（任意兩節點最大路徑和）
    int maxDiameter = Integer.MIN_VALUE;

    public int diameterOfTree(Node root) {
        maxDiameter = Integer.MIN_VALUE;
        dfsDiameter(root);
        return maxDiameter;
    }

    private int dfsDiameter(Node node) {
        if (node == null) {
            return 0;
        }
        int left = dfsDiameter(node.left);
        int right = dfsDiameter(node.right);

        // 計算當前節點最大路徑和(左 + 右 + 節點值)
        int currPathSum = left + right + node.val;
        maxDiameter = Math.max(maxDiameter, currPathSum);

        // 回傳單邊最大路徑和(節點值 + max(左,右))
        return node.val + Math.max(left, right);
    }

    // 測試
    public static void main(String[] args) {
        TreePathProblems tree = new TreePathProblems();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        System.out.println("所有根到葉路徑: " + tree.allRootToLeafPaths(tree.root));
        System.out.println("存在根到葉路徑和為 7: " + tree.hasPathSum(tree.root, 7));
        System.out.println("最大根到葉路徑和: " + tree.maxRootToLeafSum(tree.root));
        System.out.println("樹的直徑(最大路徑和): " + tree.diameterOfTree(tree.root));
    }
}
