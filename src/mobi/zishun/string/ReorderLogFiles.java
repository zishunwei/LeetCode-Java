package mobi.zishun.string;

import java.util.Arrays;

/*
 * 937. 重新排列日志文件
给你一个日志数组 logs。每条日志都是以空格分隔的字串，其第一个字为字母与数字混合的 标识符 。
有两种不同类型的日志：
字母日志：除标识符之外，所有字均由小写字母组成
数字日志：除标识符之外，所有字均由数字组成
*
请按下述规则将日志重新排序：
所有 字母日志 都排在 数字日志 之前。
字母日志 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序。
数字日志 应该保留原来的相对顺序。
返回日志的最终顺序。
*
示例：
输入：logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
输出：["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
* 提示：
1 <= logs.length <= 100
3 <= logs[i].length <= 100
logs[i] 中，字与字之间都用 单个 空格分隔
题目数据保证 logs[i] 都有一个标识符，并且在标识符之后至少存在一个字
* https://leetcode-cn.com/problems/reorder-data-in-log-files/
 */
public class ReorderLogFiles {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            // 从第一个空格分开，arr[0]是标识符，arr[1]是日志内容
            String[] log1Arr = log1.split(" ", 2);
            String[] log2Arr = log2.split(" ", 2);
            boolean isWord1 = Character.isLowerCase(log1Arr[1].charAt(0));
            boolean isWord2 = Character.isLowerCase(log2Arr[1].charAt(0));

            if (isWord1 != isWord2) { // 一个是字母日志，一个是数字日志
                return isWord1 ? -1 : 1; // 返回-1将log1排在前面（log1为字母日志时）
            } else if (isWord1) { // 都是字母日志
                if (log1Arr[1].equals(log2Arr[1])) { // 在内容相同时，按标识符排序
                    return log1Arr[0].compareTo(log2Arr[0]);
                } else { // 在内容不同时，忽略标识符后，按内容字母顺序排序
                    return log1Arr[1].compareTo(log2Arr[1]);
                }
            } else { // 都是数字日志
                return 0;
            }
        });
        return logs;
    }

    public static void main(String[] args) {
        ReorderLogFiles m = new ReorderLogFiles();
        String[] logs = {"dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"};
        System.out.println(Arrays.toString(m.reorderLogFiles(logs))); // [let1 art can, let3 art zero, let2 own kit dig, dig1 8 1 5 1, dig2 3 6]

        System.out.println(Character.isDigit('8')); // true
        System.out.println(Character.isLowerCase('8')); // false
        System.out.println(Character.isDigit('a')); // false
        System.out.println(Character.isLowerCase('a')); // true
    }
}
