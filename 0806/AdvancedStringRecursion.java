
import java.util.HashSet;

public class AdvancedStringRecursion {

    // 遞迴產生字串所有排列組合
    public static void permutations(String prefix, String str) {
        if (str.length() == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < str.length(); i++) {
                permutations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1));
            }
        }
    }

    // 遞迴字串匹配（簡單的包含判斷）
    public static boolean isMatch(String text, String pattern) {
        if (pattern.length() == 0) {
            return text.length() == 0;
        }
        if (pattern.charAt(0) == '*') {
            for (int i = 0; i <= text.length(); i++) {
                if (isMatch(text.substring(i), pattern.substring(1))) {
                    return true;
                }
            }
            return false;
        } else {
            if (text.length() == 0) {
                return false;
            }
            if (text.charAt(0) != pattern.charAt(0)) {
                return false;
            }
            return isMatch(text.substring(1), pattern.substring(1));
        }
    }

    // 遞迴移除字串中的重複字符
    public static String removeDuplicates(String str) {
        return removeDupHelper(str, new HashSet<>());
    }

    private static String removeDupHelper(String str, HashSet<Character> set) {
        if (str.length() == 0) {
            return "";
        }
        char first = str.charAt(0);
        if (set.contains(first)) {
            return removeDupHelper(str.substring(1), set);
        } else {
            set.add(first);
            return first + removeDupHelper(str.substring(1), set);
        }
    }

    // 遞迴計算所有子字串組合 (示意用，會列印全部)
    public static void allSubstrings(String str) {
        allSubstringsHelper(str, 0, "");
    }

    private static void allSubstringsHelper(String str, int index, String current) {
        if (index == str.length()) {
            if (current.length() > 0) {
                System.out.println(current);
            }
            return;
        }
        // 選擇加當前字元或不加
        allSubstringsHelper(str, index + 1, current + str.charAt(index));
        allSubstringsHelper(str, index + 1, current);
    }

    // 測試
    public static void main(String[] args) {
        System.out.println("字串排列組合：");
        permutations("", "abc");

        System.out.println("\n字串匹配（簡易版）：");
        System.out.println("abc vs abc: " + isMatch("abc", "abc"));
        System.out.println("abc vs *bc: " + isMatch("abc", "*bc"));
        System.out.println("abc vs a*": " + isMatch("abc", "a *
        "));

        System.out.println("\n移除重複字符：");
        System.out.println(removeDuplicates("aabbccabc"));

        System.out.println("\n所有子字串組合：");
        allSubstrings("ab");
    }
}
