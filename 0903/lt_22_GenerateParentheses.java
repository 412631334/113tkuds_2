// lt_22_GenerateParenthesis.java

import java.util.*;

public class lt_22_GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, String cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            result.add(cur);
            return;
        }
        if (open < max) {
            backtrack(result, cur + "(", open + 1, close, max);
        }
        if (close < open) {
            backtrack(result, cur + ")", open, close + 1, max);
        }
    }
}
