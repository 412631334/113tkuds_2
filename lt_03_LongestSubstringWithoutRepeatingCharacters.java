// lt_03_LongestSubstringWithoutRepeatingCharacters.java

import java.util.*;

public class lt_03_LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int left = 0, right = 0, maxLen = 0;

        while (right < s.length()) {
            char c = s.charAt(right);

            // 如果已經有重複，移動左指標直到不重複
            while (window.contains(c)) {
                window.remove(s.charAt(left));
                left++;
            }

            // 加入當前字元
            window.add(c);
            maxLen = Math.max(maxLen, right - left + 1);

            right++;
        }

        return maxLen;
    }
}
