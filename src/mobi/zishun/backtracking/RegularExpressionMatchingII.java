package mobi.zishun.backtracking;

/*
 * 正则表达式匹配 （数据算法之美-39-回溯算法）
 * '*'：匹配任意多个（大于等于 0 个）任意字符
 * '?'：匹配零个或者一个任意字符。
 */
public class RegularExpressionMatchingII {
    private boolean matched;

    //text：需要匹配的文本; pattern: 正则表达式模式串
    public boolean regularExpressionMatch(char[] text, char[] pattern) {
        matched = false;
        rMatch(0, 0, text, pattern);
        return matched;
    }

    private void rMatch(int i, int j, char[] text, char[] pattern) {
        // 如果已经匹配了，就不要继续递归了
        if (matched) {
            return;
        }
        // 正则表达式到结尾了
        if (j == pattern.length) {
            // 文本串也到结尾了
            if (i == text.length) {
                matched = true;
            }
            // 此次匹配失败，return
            return;
        }
        if (pattern[j] == '*') { // *匹配任意个字符
            // 递归所有可能的情况（从匹配0个到匹配到最后一个）
            for (int k = 0; k < text.length - i; k++) {
                rMatch(i + k, j + 1, text, pattern);
            }
        } else if (pattern[j] == '?') { // *匹配零个或者一个任意字符。
            // 0个字符的情况
            rMatch(i, j + 1, text, pattern);
            rMatch(i + 1, j + 1, text, pattern);
        } else if (i < text.length && text[i] == pattern[j]) { // 纯字符串匹配
            rMatch(i + 1, j + 1, text, pattern);
        }
    }

    public static void main(String[] args) {
        RegularExpressionMatchingII m = new RegularExpressionMatchingII();
        char[] pattern = {'a', '*', 'b', '?', 'c'};
        char[] text1 = {'a', 'a', 'a', 'b', 'b', 'c', 'c'};
        char[] text2 = {'a', 'b', 'c', 'd'};
        char[] text3 = {'a', 'b', 'c'};
        System.out.println(m.regularExpressionMatch(text1, pattern));
        System.out.println(m.regularExpressionMatch(text2, pattern));
        System.out.println(m.regularExpressionMatch(text3, pattern));
    }

}
