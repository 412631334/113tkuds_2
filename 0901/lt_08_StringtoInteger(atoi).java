// lt_08_StringtoInteger_atoi.java

public class lt_08_StringtoInteger_atoi {

    public int myAtoi(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int i = 0, n = s.length(), sign = 1;
        long result = 0;

        // 去除前導空白
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 處理符號
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = s.charAt(i) == '-' ? -1 : 1;
            i++;
        }

        // 讀取數字
        while (i < n && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (s.charAt(i) - '0');
            // 判斷溢位
            if (sign * result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign * result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            i++;
        }

        return (int) (sign * result);
    }
}
