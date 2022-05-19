package mobi.zishun.binarysearch;

/*
 * 668. 乘法表中第k小的数 - （378. 有序矩阵中第 K 小的元素）进阶版
几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第k小的数字吗？
给定高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第k 小的数字。

输入: m = 2, n = 3, k = 6
输出: 6
解释:
乘法表:
1	2	3
2	4	6
第6小的数字是 6 (1, 2, 2, 3, 4, 6).
注意：
m 和 n 的范围在 [1, 30000] 之间。
k 的范围在 [1, m * n] 之间。
* https://leetcode.cn/problems/kth-smallest-number-in-multiplication-table/
 */
public class KthSmallestElementInMultiplicationTable {
    // 二分查找 -
    public int findKthNumber(int m, int n, int k) {
        int left = 1;
        int right = m * n;
        while (left < right) {
            int medium = ((right - left) >> 1) + left;
            // 乘法表里小于等于medium的个数 >= k
            if (check(medium, m, n, k)) {
                right = medium;
            } else {
                left = medium + 1;
            }
        }
        return left;
    }

    private boolean check(int medium, int m, int n, int k) {
        int j = 1;
        int i = m;
        int count = 0;
        while (i > 0 && j <= n) {
            if (i * j <= medium) {
                count += i;
                j++;
            } else {
                i--;
            }
        }
        return count >= k;
    }

    public static void main(String[] args) {
        KthSmallestElementInMultiplicationTable m = new KthSmallestElementInMultiplicationTable();
        System.out.print(m.findKthNumber(2, 3, 6));
    }

}
