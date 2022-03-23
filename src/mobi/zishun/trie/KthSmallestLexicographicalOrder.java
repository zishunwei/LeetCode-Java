package mobi.zishun.trie;

/*
 * 440. 字典序的第K小数字
给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
输入: n = 13, k = 2
输出: 10
解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 */
public class KthSmallestLexicographicalOrder {
    public int findKthNumber(int n, int k) {
        int res = 1;
        k--;
        while (k > 0) {
            int count = getCount(res, n);
            if (count <= k) {
                k -= count;
                res++;
            } else {
                res *= 10;
                k--;
            }
        }
        return res;
    }

    // 该函数实现了统计范围 [1, n] 内以 prefix 为前缀的数的个数。
    private int getCount(int prefix, int n) {
        int count = 0;
        long start = prefix;
        long end = start;
        while (start <= n) {
            count += Math.min(end, n) - start + 1;
            start *= 10;
            end = end * 10 + 9;
        }
        return count;
    }
}