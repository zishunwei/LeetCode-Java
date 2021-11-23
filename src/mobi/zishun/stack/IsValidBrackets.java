package mobi.zishun.stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class IsValidBrackets {
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Deque<Character> stack = new LinkedList<>();
        HashMap<Character, Character> pairs = new LinkedHashMap<Character, Character>() {{
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }};
        for (int i = 0; i < s.length(); i++) {
            char character = s.charAt(i);
            if (pairs.containsKey(character)){
                stack.push(character);
            } else {
                if (stack.isEmpty() || !pairs.get(stack.pop()).equals(character)){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

}
