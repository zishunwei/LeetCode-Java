package mobi.zishun.stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/*
 * 739. 每日温度
给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
* 其中 answer[i] 是指在第 i 天之后，才会有更高的温度。如果气温在这之后都不会升高，请在该位置用 0 来代替。
示例 1:
输入: temperatures = [73,74,75,71,69,72,76,73]
输出: [1,1,4,2,1,1,0,0]
示例 2:
输入: temperatures = [30,40,50,60]
输出: [1,1,1,0]
提示：
1 <= temperatures.length <= 10^5
30 <= temperatures[i] <= 100
* https://leetcode-cn.com/problems/daily-temperatures/
 */
public class DailyTemperatures {
    // 单调栈 - O(n)
    // 可以维护一个存储下标的单调栈，从栈底到栈顶的下标对应的温度列表中的温度依次递减。
    // 如果一个下标在单调栈里，则表示尚未找到下一次温度更高的下标。
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        // 栈保存索引，从栈底到栈顶的下标对应的温度列表中的温度依次递减
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            // 判断当前温度大于栈顶温度
            // 则可以确定栈顶索引的结果，因为当前一定是离栈顶索引最近且满足条件的
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int preIndex = stack.pop();
                res[preIndex] = i - preIndex;
            }
            // 保证栈顶索引指向的值最小
            stack.push(i);
        }
        return res;
    }

    // 暴力 - 计数 - 反向遍历 - O(mn)
    public int[] dailyTemperaturesV2(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        int[] count = new int[71]; // 索引：温度; value：索引
        Arrays.fill(count, n);
        // 从后往前遍历
        for (int i = n - 1; i >= 0; i--) {
            int warmerIndex = n;
            // 遍历比当前温度更大的温度，找对应最近的索引
            for (int temp = temperatures[i] - 30 + 1; temp <= 70; temp++) {
                if (count[temp] < warmerIndex) {
                    warmerIndex = count[temp];
                }
            }
            // 找到了warmerIndex
            if (warmerIndex < n) {
                res[i] = warmerIndex - i;
            }
            // 从后往前遍历保证同一温度最新值（索引）最靠前
            count[temperatures[i] - 30] = i;
        }
        return res;
    }

    public static void main(String[] args) {
        DailyTemperatures m = new DailyTemperatures();
        int[] temperatures = {34, 80, 80, 34, 34, 80, 80, 80, 80, 34};
        System.out.println(Arrays.toString(m.dailyTemperatures(temperatures)));
    }

}
