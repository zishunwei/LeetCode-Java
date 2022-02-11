package mobi.zishun.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 */
public class PermutationsII {
    private final List<List<Integer>> res = new LinkedList<>();

    private List<Integer> temp;

    private boolean[] visited;

    public List<List<Integer>> permuteUnique(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return res;
        }
        temp = new ArrayList<>(n);
        visited = new boolean[n];

        Arrays.sort(nums);

        permuteDFS(nums, n, 0);
        return res;
    }

    private void permuteDFS(int[] nums, int n, int depth) {
        if (depth == n) {
            res.add(new ArrayList<>(temp));
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // 剪枝操作
                if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1]) {
                    continue;
                }
                temp.add(nums[i]);
                visited[i] = true;
                permuteDFS(nums, n, depth + 1);
                visited[i] = false;
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        PermutationsII m = new PermutationsII();
        int[] nums = {1, 2, 1};
        List<List<Integer>> res = m.permuteUnique(nums);
        System.out.println(res);
    }

}
