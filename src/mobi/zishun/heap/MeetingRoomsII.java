package mobi.zishun.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * 253. 会议室 II
给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [start_i, end_i] ，
* 返回 所需会议室的最小数量 。
示例 1：
输入：intervals = [[0,30],[5,10],[15,20]]
输出：2
示例 2：
输入：intervals = [[7,10],[2,4]]
输出：1
提示：
1 <= intervals.length <= 10^4
0 <= start_i < end_i <= 10^6
* https://leetcode-cn.com/problems/meeting-rooms-ii/
 */
public class MeetingRoomsII {
    // 想想我们将如何以一种非常简单的方式处理这个问题。我们将为当天早些时候的会议分配房间，而不是晚些时候的会议，对吗？
    // 如果你已经知道我们必须按会议开始时间对会议进行排序，那么接下来要考虑的是我们如何进行分配？
    // 在这里，任何会议都有两种可能的情况。要么没有可用的会议室，必须分配一个新的会议室，要么会议室已释放，会议可以在那里举行。
    // 需要注意的一点是，在为当前会议分配房间时，我们并不真正关心哪个房间被释放。只要有空房间，我们的工作就完成了。
    // 我们已经知道到目前为止我们已经分配的房间，我们也知道由于这些房间的会议结束时间，这些房间什么时候可以免费使用。我们只需在所有分配的房间中最早腾出的房间进行检查即可。
    // 按照前面的提示，我们可以使用min heap来存储各个房间中会议的结束时间。
    // 因此，每次我们想要检查是否有任何房间是空闲的，只需检查min堆的最顶层元素，因为这将是最早从当前占用的所有其他房间中释放出来的房间。
    // 如果我们从min heap堆顶部提取的房间不是空闲的，那么就没有其他房间是空闲的。因此，我们可以节省时间，只需分配一个新房间。
    public int minMeetingRooms(int[][] intervals) {
        // 按照 开始时间 对会议进行排序。
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        // 初始化一个新的 最小堆，将第一个会议的结束时间加入到堆中。我们只需要记录会议的结束时间，告诉我们什么时候房间会空。
        PriorityQueue<Integer> endTimePQ = new PriorityQueue<>();
        endTimePQ.offer(intervals[0][1]);
        // 对每个会议，检查堆的最小元素（即堆顶部的房间）是否空闲。
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= endTimePQ.peek()) { // 房间空闲，后一个会议的开始时间晚于当前最早结束会议房间的时间
                // 则从堆顶拿出该元素，将其改为我们处理的会议的结束时间，加回到堆中
                endTimePQ.poll();
                endTimePQ.offer(intervals[i][1]);
            } else { // 房间不空闲
                // 开新房间，并加入到堆中。
                endTimePQ.offer(intervals[i][1]);
            }
        }
        return endTimePQ.size();
    }

}
