package mobi.zishun.array;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 252. 会议室
给定一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，
* 请你判断一个人是否能够参加这里面的全部会议。

示例 1：
输入：intervals = [[0,30],[5,10],[15,20]]
输出：false
示例 2：
输入：intervals = [[7,10],[2,4]]
输出：true
提示：
0 <= intervals.length <= 10^4
intervals[i].length == 2
0 <= starti < endi <= 10^6
* https://leetcode-cn.com/problems/meeting-rooms/
 */
public class MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        int n = intervals.length;
        if (n == 0) {
            return true;
        }
        // 按开始时间排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < n-1; i++) {
            // 当前会议的结束时间 > 下一个会议的开始时间
            // 即返回false
            if (intervals[i][1] > intervals[i+1][0]){
                return false;
            }
        }
        return true;
    }

}
