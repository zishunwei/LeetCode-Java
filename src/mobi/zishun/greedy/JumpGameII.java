package mobi.zishun.greedy;

/*
 * 45. 跳跃游戏 II
给你一个非负整数数组nums ，你最初位于数组的第一个位置。
数组中的每个元素代表你在该位置可以跳跃的最大长度。
你的目标是使用最少的跳跃次数到达数组的最后一个位置。
假设你总是可以到达数组的最后一个位置。
链接：https://leetcode-cn.com/problems/jump-game-ii
 */
public class JumpGameII {

    // 正向查找可到达的最大位置
    // 时间复杂度：O(n)
    public int jump(int[] nums) {

        return 0;
    }

    // 反向查找出发位置
    // 时间复杂度：O(n2)
    public int jumpV2(int[] nums) {
        int res = 0;
        int pos = nums.length - 1;
        while (pos > 0) {
            for (int i = 0; i < pos; i++) {
                if (i + nums[i] >= pos) {
                    pos = i;
                    res++;
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        JumpGameII jumpGameII = new JumpGameII();
        int[] nums = {2, 3, 0, 1, 4};
        System.out.println(jumpGameII.jump(nums));
    }

}
