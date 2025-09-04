
public class lt_29_DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int sign = (dividend > 0) ^ (divisor > 0) ? -1 : 1;
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        int res = 0;
        while (a >= b) {
            long temp = b, multiple = 1;
            while (a >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            a -= temp;
            res += multiple;
        }
        return sign * res;
    }

    // ====== Driver ======
    public static void main(String[] args) {
        lt_29_DivideTwoIntegers solution = new lt_29_DivideTwoIntegers();
        int dividend = 10, divisor = 3;
        int res = solution.divide(dividend, divisor);
        System.out.println("Result: " + res);
    }
}
