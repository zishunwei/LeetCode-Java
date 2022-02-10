package mobi.zishun.recursion;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 */
public class Permutations {
    private final List<List<Integer>> results = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        permuteRecursion(nums, nums.length, nums.length);
        return results;
    }

    // n为数组长度
    // k表示要处理的子数组的数据个数
    public void permuteRecursion(int[] nums, int n, int k) {
        if (k == 1) {
            results.add(arrayToList(nums));
        }
        // 1234 先把 1 换到最后，递归，相当于把最后一位是1 的处理好了。
        // 然后要把1换回来，又变成1234 ，再把2换到最后一位，再递归，再换回来。以此类推。
        for (int i = 0; i < k; i++) {
            swap(nums, i, k - 1);
            permuteRecursion(nums, n, k - 1);
            swap(nums, i, k - 1);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private List<Integer> arrayToList(int[] nums) {
        return Arrays.stream(nums).boxed().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Permutations m = new Permutations();
        int[] nums = {1, 2, 3};
        System.out.println(m.permute(nums));
    }
}