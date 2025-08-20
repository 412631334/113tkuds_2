
import java.util.*;

public class M04_TieredTaxSimple {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        long total = 0; // 紀錄稅額總和
        for (int i = 0; i < n; i++) {
            long income = sc.nextLong();
            long tax = calcTax(income);
            total += tax;
            System.out.println("Tax: " + tax);
        }

        long avg = total / n; // 取整數
        System.out.println("Average: " + avg);
    }

    // 計算稅額
    static long calcTax(long income) {
        long tax = 0;
        if (income > 1000000) {
            tax += (income - 1000000) * 30 / 100;
            income = 1000000;
        }
        if (income > 500000) {
            tax += (income - 500000) * 20 / 100;
            income = 500000;
        }
        if (income > 120000) {
            tax += (income - 120000) * 12 / 100;
            income = 120000;
        }
        if (income > 0) {
            tax += income * 5 / 100;
        }
        return tax;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：每筆收入在 calcTax 中最多進行常數次（4 段）運算 O(1)，
 * 共 n 筆 → 總時間為 O(n)。
 */
