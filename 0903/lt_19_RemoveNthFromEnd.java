// lt_19_RemoveNthFromEnd.java

// 注意：不要宣告為 public，否則會跟檔名衝突
class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    // Driver 會用到：將字串如 "[1,2,3]" 轉成鏈表
    static ListNode deserialize(String s) {
        if (s == null) {
            return null;
        }
        s = s.trim();
        if (s.equals("[]") || s.length() == 0) {
            return null;
        }
        if (s.charAt(0) == '[' && s.charAt(s.length() - 1) == ']') {
            s = s.substring(1, s.length() - 1).trim();
        }
        if (s.length() == 0) {
            return null;
        }

        String[] parts = s.split(",");
        ListNode dummy = new ListNode(0), cur = dummy;
        for (String p : parts) {
            p = p.trim();
            if (p.isEmpty()) {
                continue;
            }
            cur.next = new ListNode(Integer.parseInt(p));
            cur = cur.next;
        }
        return dummy.next;
    }

    // 方便除錯
    static String serialize(ListNode head) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (head != null) {
            sb.append(head.val);
            head = head.next;
            if (head != null) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String toString() {
        return serialize(this);
    }
}

public class lt_19_RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;

        // 先走 n 步到間距
        for (int i = 0; i < n; i++) {
            if (fast.next == null) {
                return head; // n 不合法的保險判斷

                        }fast = fast.next;
        }
        // 一起走到尾，slow 指向要刪除的前一個
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
