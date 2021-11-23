package mobi.zishun.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
1. 两数之和
给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现,不能在结果中出现两次。

你可以按任意顺序返回答案。
 */
public class TwoSum {
    private static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        for (int i = 0; i<nums.length; i++){
            if (!hashMap.containsKey(nums[i])){
                hashMap.put(target - nums[i], i);
            } else {
                return new int[]{hashMap.get(nums[i]), i};
            }
        }
        return new int[0];
    }

    public static void main(String[] args){
        int[] nums = {2,7,3,11};
        System.out.println(Arrays.toString(twoSum(nums, 5)));
    }
}