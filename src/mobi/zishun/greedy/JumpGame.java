package mobi.zishun.greedy;

/*
 * 55. 跳跃游戏
给定一个非负整数数组nums ，你最初位于数组的 第一个下标 。
数组中的每个元素代表你在该位置可以跳跃的最大长度。
判断你是否能够到达最后一个下标。
链接：https://leetcode-cn.com/problems/jump-game
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int maxJumpLength = 0;
        for (int i = 0; i < n; i++) {
            if (i <= maxJumpLength) {
                maxJumpLength = Math.max(maxJumpLength, i + nums[i]);
                if (maxJumpLength >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        JumpGame jumpGame = new JumpGame();
        int[] nums1 = {2, 3, 1, 1, 4};
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println(jumpGame.canJump(nums1));
        System.out.println(jumpGame.canJump(nums2));
    }

}
