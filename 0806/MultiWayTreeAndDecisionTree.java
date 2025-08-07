
import java.util.*;

public class MultiWayTreeAndDecisionTree {

    static class MultiWayNode {

        int val;
        List<MultiWayNode> children;

        MultiWayNode(int v) {
            val = v;
            children = new ArrayList<>();
        }
    }

    MultiWayNode root;

    // 建立多路樹
    public void buildSampleTree() {
        root = new MultiWayNode(1);
        MultiWayNode c2 = new MultiWayNode(2);
        MultiWayNode c3 = new MultiWayNode(3);
        MultiWayNode c4 = new MultiWayNode(4);
        MultiWayNode c5 = new MultiWayNode(5);
        root.children.add(c2);
        root.children.add(c3);
        c2.children.add(c4);
        c2.children.add(c5);
    }

    // 深度優先走訪
    public void dfs(MultiWayNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        for (MultiWayNode child : node.children) {
            dfs(child);
        }
    }

    // 廣度優先走訪
    public void bfs() {
        if (root == null) {
            return;
        }
        Queue<MultiWayNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            MultiWayNode curr = queue.poll();
            System.out.print(curr.val + " ");
            for (MultiWayNode child : curr.children) {
                queue.offer(child);
            }
        }
    }

    // 簡單猜數字決策樹模擬
    public void guessNumberGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("想一個 1~10 的數字，我來猜：");
        int low = 1, high = 10;
        while (low <= high) {
            int mid = (low + high) / 2;
            System.out.println("是不是 " + mid + "? (y/n)");
            String ans = sc.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.println("我猜對了！");
                break;
            } else {
                System.out.println("你的數字比較大嗎？(y/n)");
                String bigger = sc.nextLine();
                if (bigger.equalsIgnoreCase("y")) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        sc.close();
    }

    // 計算多路樹高度
    public int height(MultiWayNode node) {
        if (node == null) {
            return 0;
        }
        int maxChildHeight = 0;
        for (MultiWayNode child : node.children) {
            maxChildHeight = Math.max(maxChildHeight, height(child));
        }
        return maxChildHeight + 1;
    }

    // 計算每個節點度數 (子節點數量)
    public void printNodeDegrees(MultiWayNode node) {
        if (node == null) {
            return;
        }
        System.out.println("節點 " + node.val + " 的度數: " + node.children.size());
        for (MultiWayNode child : node.children) {
            printNodeDegrees(child);
        }
    }

    public static void main(String[] args) {
        MultiWayTreeAndDecisionTree tree = new MultiWayTreeAndDecisionTree();
        tree.buildSampleTree();
        System.out.print("深度優先走訪: ");
        tree.dfs(tree.root);
        System.out.println();

        System.out.print("廣度優先走訪: ");
        tree.bfs();
        System.out.println();

        System.out.println("多路樹高度: " + tree.height(tree.root));

        tree.printNodeDegrees(tree.root);

        // 猜數字遊戲
        tree.guessNumberGame();
    }
}
