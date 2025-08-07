
import java.util.*;

public class LevelOrderTraversalVariations {

    static class Node {

        int val;
        Node left, right;

        Node(int v) {
            val = v;
        }
    }

    Node root;

    public void buildSampleTree() {
        root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
    }

    // 將每層節點分別放在不同 List
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                currLevel.add(curr.val);
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            res.add(currLevel);
        }
        return res;
    }

    // 之字形層序走訪
    public List<List<Integer>> zigzagLevelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> currLevel = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                if (leftToRight) {
                    currLevel.addLast(curr.val); 
                }else {
                    currLevel.addFirst(curr.val);
                }
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            res.add(currLevel);
            leftToRight = !leftToRight;
        }
        return res;
    }

    // 只列印每層最後一個節點
    public List<Integer> lastNodeOfEachLevel(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int lastVal = 0;
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                lastVal = curr.val;
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            res.add(lastVal);
        }
        return res;
    }

    // 垂直層序走訪（根據水平位置分組）
    public List<List<Integer>> verticalOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Node> queue = new LinkedList<>();
        Queue<Integer> colQueue = new LinkedList<>();
        queue.offer(root);
        colQueue.offer(0);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            int col = colQueue.poll();

            map.putIfAbsent(col, new ArrayList<>());
            map.get(col).add(curr.val);

            if (curr.left != null) {
                queue.offer(curr.left);
                colQueue.offer(col - 1);
            }
            if (curr.right != null) {
                queue.offer(curr.right);
                colQueue.offer(col + 1);
            }
        }

        res.addAll(map.values());
        return res;
    }

    // 測試
    public static void main(String[] args) {
        LevelOrderTraversalVariations tree = new LevelOrderTraversalVariations();
        tree.buildSampleTree();

        System.out.println("每層節點列表: " + tree.levelOrder(tree.root));
        System.out.println("之字形層序走訪: " + tree.zigzagLevelOrder(tree.root));
        System.out.println("每層最後一個節點: " + tree.lastNodeOfEachLevel(tree.root));
        System.out.println("垂直層序走訪: " + tree.verticalOrder(tree.root));
    }
}
