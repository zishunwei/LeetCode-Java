package mobi.zishun.greedy;

import java.util.Arrays;

/*
 * 1405. 最长快乐字符串
如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
 1. s 是一个尽可能长的快乐字符串。
 2. s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
 3. s 中只含有 'a'、'b' 、'c' 三种字母。
如果不存在这样的字符串 s ，请返回一个空字符串 ""。
* https://leetcode-cn.com/problems/longest-happy-string/
 */
public class LongestDiverseString {

    //贪心策略：
    //尽可能优先使用当前数量最多的字母，因为最后同一种字母剩余的越多，越容易出现字母连续相同的情况。如果构建完成最长的快乐字符串后还存在剩余未选择的字母，则剩余的字母一定为同一种字母且该字母的总数量最多。
    //依次从当前数量最多的字母开始尝试，如果发现加入当前字母会导致出现三个连续相同字母，则跳过当前字母，直到我们找到可以添加的字母为止。实际上每次只会在数量最多和次多的字母中选择一个。
    //如果尝试所有的字母都无法添加，则直接退出，此时构成的字符串即为最长的快乐字符串。
    public String longestDiverseString(int a, int b, int c) {
        Pair[] arr = {new Pair(a, 'a'), new Pair(b, 'b'), new Pair(c, 'c')};
        StringBuilder res = new StringBuilder();

        while (true) {
            Arrays.sort(arr, (p1, p2) -> (p2.count - p1.count));
            // flag-判断是否添加完（所有pair.freq为0 or 只剩一种字母并且连续添加两次）
            boolean hasNext = false;
            for (Pair pair : arr) {
                if (pair.count < 1) {
                    break;
                }
                int n = res.length();
                // 同一字母连续添加两次
                if (n >= 2 && res.charAt(n - 2) == pair.ch && res.charAt(n - 1) == pair.ch) {
                    continue;
                }
                hasNext = true;
                res.append(pair.ch);
                pair.count--;
                // 一次for循环找一个值
                break;
            }
            if (!hasNext) {
                break;
            }
        }
        return res.toString();
    }

    private static class Pair {
        private int count;
        private final char ch;

        public Pair(int count, char ch) {
            this.count = count;
            this.ch = ch;
        }
    }

    public static void main(String[] args) {
        LongestDiverseString method = new LongestDiverseString();
        System.out.println(method.longestDiverseString(7, 1, 0));
    }

}
