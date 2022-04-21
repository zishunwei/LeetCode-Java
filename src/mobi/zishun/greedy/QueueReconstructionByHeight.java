package mobi.zishun.greedy;

import java.util.Arrays;

/*
 * 406. 根据身高重建队列
假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
* 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
请你重新构造并返回输入数组 people 所表示的队列。
* 返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。

示例 1：
输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
解释：
编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
提示：
1 <= people.length <= 2000
0 <= hi <= 10^6
0 <= ki < people.length
题目数据确保队列可以被重建
* https://leetcode-cn.com/problems/queue-reconstruction-by-height/
 */
public class QueueReconstructionByHeight {
    // 贪心
    // 先按高的排序，再遍历插队
    public int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        // 按照数对的元素 1 降序排序，按照数对的元素 2 升序排序。
        // 原因是，按照元素 1 进行降序排序，对于每个元素，在其之前的元素的个数，就是大于等于他的元素的数量，
        // 而按照第二个元素正向排序，我们希望 ki 大的尽量在后面，减少插入操作的次数。
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        int[][] res = new int[n][2];
        // 记录已经塞入多少个人到结果数组中
        for (int i = 0; i < n; i++) {
            // ki即为当前人应该在的位置（因为已经排过序了）
            int preNum = people[i][1];
            if (preNum < i) {
                // 插队
                // 将res[preNum,i-1]全部向后移一位,移到res[preNum+1,i]
                System.arraycopy(res, preNum, res, preNum + 1, i - preNum);
                // 插入到res[preNum]
                res[preNum] = people[i];
            } else {
                // ki已满足，直接塞到当前队列末尾
                res[i] = people[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        QueueReconstructionByHeight m = new QueueReconstructionByHeight();
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        System.out.println(Arrays.deepToString(m.reconstructQueue(people)));
    }

}
