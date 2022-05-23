package mobi.zishun.binarysearch;

import java.util.Arrays;

/*
 * 436. 寻找右区间
给你一个区间数组 intervals ，其中 intervals[i] = [starti, endi] ，且每个 starti 都 不同 。
区间 i 的 右侧区间 可以记作区间 j ，并满足 startj >= endi ，且 startj 最小化 。
返回一个由每个区间 i 的 右侧区间 的最小起始位置组成的数组。
* 如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。

示例 1：
输入：intervals = [[1,2]]
输出：[-1]
解释：集合中只有一个区间，所以输出-1。
示例 2：
输入：intervals = [[3,4],[2,3],[1,2]]
输出：[-1,0,1]
解释：对于 [3,4] ，没有满足条件的“右侧”区间。
对于 [2,3] ，区间[3,4]具有最小的“右”起点;
对于 [1,2] ，区间[2,3]具有最小的“右”起点。
* 示例 3：
输入：intervals = [[1,1],[3,4]]
输出：[0,-1]
解释：[1,1]的右区间可以是自己本身。
提示：
1 <= intervals.length <= 2 * 10^4
intervals[i].length == 2
-10^6 <= starti <= endi <= 10^6
每个间隔的起点都 不相同
* https://leetcode.cn/problems/find-right-interval/
 */
public class FindRightInterval {
    // 排序 + 双指针（查找排序数组的第一个元素） - O(nlog(n))
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[][] sorted = new int[n][2];
        for (int i = 0; i < n; i++) {
            sorted[i][0] = intervals[i][0];
            sorted[i][1] = i;
        }
        Arrays.sort(sorted, (a, b) -> a[0] - b[0]);

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            // 二分查找
            // 在 sorted 中找到第一个满足左端点 start 大于等于 intervals[i][1] 的成员 sorted[j]，
            // sorted[j][1] 即是 its[i] 的最右区间。
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int mid = (left + right) >> 1;
                // sorted的start大于等于intervals的end，收缩right
                if (sorted[mid][0] >= intervals[i][1]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            // 判断是否满足条件
            if (sorted[right][0] >= intervals[i][1]) {
                res[i] = sorted[right][1];
            } else {
                res[i] = -1;
            }
        }
        return res;
    }


    // 排序 + 暴力 - O(n2)
    public int[] findRightIntervalV2(int[][] intervals) {
        int n = intervals.length;
        int[][] sorted = new int[n][3];
        for (int i = 0; i < n; i++) {
            sorted[i][0] = intervals[i][0];
            sorted[i][1] = intervals[i][1];
            sorted[i][2] = i;
        }
        Arrays.sort(sorted, (a, b) -> a[0] - b[0]);
        int[] res = new int[n];
        loop1:
        for (int i = 0; i < n; i++) {
            int curEnd = sorted[i][1];
            int curIndex = sorted[i][2];
            for (int j = i; j < n; j++) {
                if (sorted[j][0] >= curEnd) {
                    res[curIndex] = sorted[j][2];
                    continue loop1;
                }
            }
            res[curIndex] = -1;
        }
        return res;
    }


}
