// lt_21_MergeTwoLists.java

class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

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
            if (!p.trim().isEmpty()) {
                cur.next = new ListNode(Integer.parseInt(p.trim()));
                cur = cur.next;
            }
        }
        return dummy.next;
    }

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
}

public class lt_21_MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0), cur = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if (list1 != null) {
            cur.next = list1;
        }
        if (list2 != null) {
            cur.next = list2;
        }
        return dummy.next;
    }
}
