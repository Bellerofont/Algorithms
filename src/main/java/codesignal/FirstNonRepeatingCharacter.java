package codesignal;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonRepeatingCharacter {

    char firstNotRepeatingCharacter(String s) {
        Map<Character, Integer> cm = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            cm.merge(s.charAt(i), 1, Integer::sum);
        }
        System.out.println(cm.keySet());
        if (cm.containsValue(1)) {
            for (char c : cm.keySet()) {
                if (cm.get(c) == 1) return c;
            }
        }
        return '_';
    }
}
