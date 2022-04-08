package mobi.zishun.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 207. 课程表
你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
在选修某些课程之前需要一些先修课程。
* 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
*
示例 1：
输入：numCourses = 2, prerequisites = [[1,0]]
输出：true
解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
示例 2：
输入：numCourses = 5, prerequisites = [[1,4],[2,4],[3,1],[3,2]]
输出：true
提示：
1 <= numCourses <= 105
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
prerequisites[i] 中的所有课程对 互不相同
* https://leetcode-cn.com/problems/course-schedule/
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 建图 - 邻接表存储
        List<List<Integer>> adjList = new ArrayList<>(numCourses);
        // 入度表 indegrees。
        int[] indegrees = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            // 统计课程安排图中每个节点的入度 （统计其先修课程的课的数量）
            indegrees[prerequisite[0]]++;
            // 建图 - 索引：先修课程；值：对应后续课程列表
            adjList.get(prerequisite[1]).add(prerequisite[0]);
        }
        // bfs
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            // 将没有先修课程的课入队
            if (indegrees[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            // 无先修课程或先修课程已满足的课
            int pre = queue.poll();
            // 学完一门课
            numCourses--;
            // 遍历此先修课程对应的后续课程（当前课程）
            for (int cur : adjList.get(pre)){
                // 当前课程的先修课程-1
                indegrees[cur]--;
                // 当前课程的先修课程全部满足的情况
                if (indegrees[cur] == 0){
                    queue.offer(cur);
                }
            }
        }
        // 输出结果 - 全部课程有没有都学完
        return numCourses == 0;
    }

}
