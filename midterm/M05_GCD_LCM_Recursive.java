
import java.util.*;

public class M05_GCD_LCM_Recursive {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        long g = gcd(a, b);
        long l = (a / g) * b; // 先除再乘，避免溢位

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }

    // 遞迴歐幾里得演算法
    static long gcd(long x, long y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }
}

/*
 * Time Complexity: O(log(min(a, b)))
 * 說明：遞迴 gcd 每次取模，數值大小顯著下降。
 * 根據歐幾里得演算法分析，步驟數量與 log(min(a,b)) 成正比。
 */
