
import java.util.*;

public class lt_30_SubstringWithConcatenationOfAllWords {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s.isEmpty() || words.length == 0) {
            return res;
        }

        int wordLen = words[0].length(), wordCount = words.length;
        Map<String, Integer> wordMap = new HashMap<>();
        for (String w : words) {
            wordMap.put(w, wordMap.getOrDefault(w, 0) + 1);
        }

        for (int i = 0; i < wordLen; i++) {
            int left = i, count = 0;
            Map<String, Integer> seen = new HashMap<>();
            for (int j = i; j <= s.length() - wordLen; j += wordLen) {
                String sub = s.substring(j, j + wordLen);
                if (wordMap.containsKey(sub)) {
                    seen.put(sub, seen.getOrDefault(sub, 0) + 1);
                    count++;
                    while (seen.get(sub) > wordMap.get(sub)) {
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }
                    if (count == wordCount) {
                        res.add(left);
                    }
                } else {
                    seen.clear();
                    count = 0;
                    left = j + wordLen;
                }
            }
        }
        return res;
    }

    // ====== Driver ======
    public static void main(String[] args) {
        lt_30_SubstringWithConcatenationOfAllWords solution = new lt_30_SubstringWithConcatenationOfAllWords();
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        List<Integer> res = solution.findSubstring(s, words);
        System.out.println(res); // Output: [0, 9]
    }
}
