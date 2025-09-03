// lt_13_RomanToInteger.java

import java.util.HashMap;
import java.util.Map;

public class lt_13_RomanToInteger {

    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int total = 0;
        int prev = 0;
        for (char c : s.toCharArray()) {
            int curr = map.get(c);
            if (curr > prev) {
                total += curr - 2 * prev; // 減去前一次加過的
            } else {
                total += curr;
            }
            prev = curr;
        }
        return total;
    }
}
