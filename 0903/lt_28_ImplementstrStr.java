
public class lt_28_ImplementstrStr {

    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i <= n - m; i++) {
            if (haystack.substring(i, i + m).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    // ====== Driver ======
    public static void main(String[] args) {
        lt_28_ImplementstrStr solution = new lt_28_ImplementstrStr();
        String haystack = "hello";
        String needle = "ll";
        int index = solution.strStr(haystack, needle);
        System.out.println("Index: " + index);
    }
}
