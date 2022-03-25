package mobi.zishun.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * 56. 合并区间
以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
* 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
示例 1：
输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
输出：[[1,6],[8,10],[15,18]]
解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
示例 2：
输入：intervals = [[1,4],[4,5]]
输出：[[1,5]]
解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
提示：
1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
* https://leetcode-cn.com/problems/merge-intervals/
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        // 排序 - 只需要按低点排序即可
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
//      Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        int n = intervals.length;
        List<int[]> resultList = new ArrayList<int[]>();
        int low = intervals[0][0];
        int high = intervals[0][1];
        for (int i = 1; i < n; i++) {
            int[] cur = intervals[i];
            if (cur[0] <= high) {
                high = Math.max(cur[1], high);
            } else {
                resultList.add(new int[]{low, high});
                low = cur[0];
                high = cur[1];
            }
        }
        resultList.add(new int[]{low, high});
        return resultList.toArray(new int[resultList.size()][]);
    }

}
