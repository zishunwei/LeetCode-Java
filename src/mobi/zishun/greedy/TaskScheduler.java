package mobi.zishun.greedy;

import java.util.Arrays;

/*
 * 621. 任务调度器
给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。
* 其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。
* 在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
你需要计算完成所有任务所需要的 最短时间 。

示例 1：
输入：tasks = ["A","A","A","B","B","B"], n = 2
输出：8
解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
提示：
1 <= task.length <= 10^4
tasks[i] 是大写英文字母
n 的取值范围为 [0, 100]
* https://leetcode-cn.com/problems/task-scheduler/
 */
public class TaskScheduler {
    // 贪心
    // 先安排出现次数最多的任务，让这个任务两次执行的时间间隔正好为n。再在这个时间间隔内填充其他的任务。
    public int leastInterval(char[] tasks, int n) {
        // 计数
        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A']++;
        }
        // 降序排序
        Arrays.sort(count);
        // maxTimes为出现次数最多的那个任务出现的次数
        // 以此作为桶的个数
        int maxTimes = count[25];
        // maxCount为一共有多少个任务和出现最多的那个任务出现次数一样，用于统计最后一个桶的大小
        int maxCount = 1;
        // 每个桶的容量为n+1（本身执行时间+间隔，间隔可以填充其它任务）
        for (int i = 25; i > 0; i--) {
            // 有和maxTimes次数一样的任务
            if (count[i] == count[i - 1]) {
                maxCount++;
            } else {
                break;
            }
        }
        // 时间为桶的总面积（包括间隔）
        // 此时的结果是正常或偏小的，因为只考虑了有间隔/冷却时间的情况
        int res = (maxTimes - 1) * (n + 1) + maxCount;
        // 如果任务种类很多，在安排时无需冷却时间，只需要在一个任务的两次出现间填充其他任务，然后从左到右从上到下依次执行即可，
        // 由于每一个任务占用一个时间单位，我们又正正好好地使用了tasks中的所有任务，
        // 而且我们只使用tasks中的任务来占用方格（没用冷却时间）。
        // 因此这种情况下，所需要的时间即为tasks的长度。
        return Math.max(res, tasks.length);
    }

}
