
public class lt_25_ReverseNodesinkGroup {

    public static class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, end = dummy;

        while (true) {
            int count = 0;
            while (end != null && count < k) {
                end = end.next;
                count++;
            }
            if (end == null) {
                break;
            }

            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;

            pre.next = reverse(start);
            start.next = next;

            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null, cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }
}
