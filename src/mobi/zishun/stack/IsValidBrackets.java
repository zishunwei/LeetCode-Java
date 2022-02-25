package mobi.zishun.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class IsValidBrackets {
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 != 0) {
            return false;
        }
        char[] charArr = s.toCharArray();
        HashMap<Character, Character> pairs = new HashMap<>() {{
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }};
        Deque<Character> stack = new ArrayDeque<>(n / 2);
        for (char cur : charArr) {
            if (pairs.containsKey(cur)) {
                stack.addLast(cur);
            } else {
                if (stack.isEmpty() || pairs.get(stack.peekLast()) != cur) {
                    return false;
                }
                stack.removeLast();
            }
        }
        return stack.isEmpty();
    }
}
