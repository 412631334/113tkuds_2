
public class RecursionVsIteration {

    // 計算二項式係數 (遞迴)
    public static int binomialCoeffRec(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }
        return binomialCoeffRec(n - 1, k - 1) + binomialCoeffRec(n - 1, k);
    }

    // 計算二項式係數 (迭代)
    public static int binomialCoeffIter(int n, int k) {
        int[][] C = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i) {
                    C[i][j] = 1;
                } else {
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                }
            }
        }
        return C[n][k];
    }

    // 尋找陣列所有元素乘積 (遞迴)
    public static int productRec(int[] arr, int index) {
        if (index == arr.length) {
            return 1;
        }
        return arr[index] * productRec(arr, index + 1);
    }

    // 尋找陣列所有元素乘積 (迭代)
    public static int productIter(int[] arr) {
        int prod = 1;
        for (int v : arr) {
            prod *= v;
        }
        return prod;
    }

    // 計算字串中元音字母數量 (遞迴)
    public static int countVowelsRec(String s, int index) {
        if (index == s.length()) {
            return 0;
        }
        char c = Character.toLowerCase(s.charAt(index));
        int count = (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') ? 1 : 0;
        return count + countVowelsRec(s, index + 1);
    }

    // 計算字串中元音字母數量 (迭代)
    public static int countVowelsIter(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) {
                count++;
            }
        }
        return count;
    }

    // 檢查括號是否配對正確 (遞迴)
    public static boolean checkParenthesesRec(String s) {
        return checkHelper(s, 0, 0);
    }

    private static boolean checkHelper(String s, int index, int count) {
        if (count < 0) {
            return false;  // 出現多餘右括號

        }
        if (index == s.length()) {
            return count == 0;
        }
        char c = s.charAt(index);
        if (c == '(') {
            return checkHelper(s, index + 1, count + 1);
        } else if (c == ')') {
            return checkHelper(s, index + 1, count - 1);
        } else {
            return checkHelper(s, index + 1, count);
        }
    }

    // 檢查括號是否配對正確 (迭代)
    public static boolean checkParenthesesIter(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    // 測試
    public static void main(String[] args) {
        System.out.println("二項式係數 C(5,2) 遞迴: " + binomialCoeffRec(5, 2));
        System.out.println("二項式係數 C(5,2) 迭代: " + binomialCoeffIter(5, 2));

        int[] arr = {1, 2, 3, 4};
        System.out.println("陣列乘積 遞迴: " + productRec(arr, 0));
        System.out.println("陣列乘積 迭代: " + productIter(arr));

        String str = "Hello World";
        System.out.println("元音數 遞迴: " + countVowelsRec(str, 0));
        System.out.println("元音數 迭代: " + countVowelsIter(str));

        String test1 = "(())()";
        String test2 = "(()))(";
        System.out.println("括號配對 遞迴 (\"(())()\" ) : " + checkParenthesesRec(test1));
        System.out.println("括號配對 迭代 (\"(()))(\" ) : " + checkParenthesesIter(test2));
    }
}
