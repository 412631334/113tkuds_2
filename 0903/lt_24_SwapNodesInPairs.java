
public class lt_24_SwapNodesInPairs {

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

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            ListNode first = cur.next;
            ListNode second = cur.next.next;
            first.next = second.next;
            second.next = first;
            cur.next = second;
            cur = first;
        }
        return dummy.next;
    }

    // ====== Driver ======
    public static void main(String[] args) {
        lt_24_SwapNodesInPairs solution = new lt_24_SwapNodesInPairs();

        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode swapped = solution.swapPairs(head);

        while (swapped != null) {
            System.out.print(swapped.val + " ");
            swapped = swapped.next;
        }
    }
}
