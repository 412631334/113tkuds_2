
import java.util.*;

public class M10_RBPropertiesCheck {

    static class Node {

        int val;
        char color; // 'B' 或 'R'

        Node(int v, char c) {
            val = v;
            color = c;
        }
    }

    static Node[] tree;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        tree = new Node[n];
        for (int i = 0; i < n; i++) {
            String valStr = sc.next();
            String colorStr = sc.next();
            int val = Integer.parseInt(valStr);
            char color = (val == -1) ? 'B' : colorStr.charAt(0);
            tree[i] = new Node(val, color);
        }

        // 1. 根節點黑色
        if (tree.length > 0 && tree[0].color != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        // 2 & 3. DFS 檢查紅紅相鄰 & 黑高一致
        Result res = checkRB(0);
        if (!res.valid) {
            System.out.println(res.msg);
        } else {
            System.out.println("RB Valid");
        }
    }

    // 回傳結果物件
    static class Result {

        boolean valid;
        String msg;
        int blackHeight;

        Result(boolean v, String m, int h) {
            valid = v;
            msg = m;
            blackHeight = h;
        }
    }

    static Result checkRB(int index) {
        if (index >= tree.length || tree[index].val == -1) {
            // NIL 節點視為黑，高度 1
            return new Result(true, "", 1);
        }

        Node node = tree[index];
        Result left = checkRB(2 * index + 1);
        if (!left.valid) {
            return left;
        }

        Result right = checkRB(2 * index + 2);
        if (!right.valid) {
            return right;
        }

        // 紅紅相鄰檢查
        if (node.color == 'R') {
            if ((2 * index + 1 < tree.length && tree[2 * index + 1].color == 'R')
                    || (2 * index + 2 < tree.length && tree[2 * index + 2].color == 'R')) {
                return new Result(false, "RedRedViolation at index " + index, 0);
            }
        }

        // 黑高度檢查
        if (left.blackHeight != right.blackHeight) {
            return new Result(false, "BlackHeightMismatch", 0);
        }

        int bh = left.blackHeight + (node.color == 'B' ? 1 : 0);
        return new Result(true, "", bh);
    }
}
