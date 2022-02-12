package mobi.zishun.backtracking;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 * 17. 电话号码的字母组合
给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 */
public class LetterCombinations {
    private final Map<Character, char[]> map = new HashMap<>();

    private final List<String> results = new LinkedList<>();

    private final StringBuilder temp = new StringBuilder();

//    private boolean[] visted;

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return results;
        }

        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});

        int n = digits.length();
//        visted = new boolean[n];

        char[] digitsArr = digits.toCharArray();
        dfs(digitsArr, n, 0);
        return results;
    }

    private void dfs(char[] digitsArr, int n, int index) {
        if (index == n) {
            results.add(temp.toString());
            return;
        }

//        for (int i = 0; i < n; i++) {
//            // 剪枝
//            if (!visted[i] && (i == 0 || visted[i - 1])) {
        char[] curCharsArr = map.get(digitsArr[index]);
//                visted[i] = true;
        for (char c : curCharsArr) {
            temp.append(c);
            dfs(digitsArr, n, index + 1);
            temp.deleteCharAt(temp.length() - 1);
        }
//                visted[i] = false;
//            }
//        }
    }

    public static void main(String[] args) {
//        char[][] chars = new char[3][4];
//        System.out.println(chars[0][0] == 0); //true
        LetterCombinations m = new LetterCombinations();
        List<String> res = m.letterCombinations("23");
        System.out.println(res);
    }


}
