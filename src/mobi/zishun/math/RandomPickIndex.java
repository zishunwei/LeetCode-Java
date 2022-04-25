package mobi.zishun.math;

/*
 * 398. 随机数索引
给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。
* 您可以假设给定的数字一定存在于数组中。
注意：
数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。

示例:
int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);
// pick(3) 应该返回索引 2,3 或者 4。每个索引的返回概率应该相等。
solution.pick(3);
// pick(1) 应该返回 0。因为只有nums[0]等于1。
solution.pick(1);
* https://leetcode-cn.com/problems/random-pick-index/
 */
public class RandomPickIndex {
    // 蓄水池抽样法 - 节约内存 - 支持流式数据
    public RandomPickIndex(int[] nums) {
        this.nums = nums;
    }

    int[] nums;

    public int pick(int target) {
        int res = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                count++; // 第 count 次遇到 target
                // 假设数组里有3个target
                // 第一次遇见选中概率为1/1，第二次遇见选中概率为1/2，第三次遇见选中概率为1/3
                // 不断刷新/迭代，最终选中每个的（遍历结束后留下的）概率都为1/3
                // 如果第一个保留下来的概率：1 * 1/2 * 2/3 = 1/3 （第一次选中 * 第二次不选中 * 第三次不选中）
                if ((int) (Math.random() * count) == 0) {
                    res = i;
                }
            }
        }
        return res;
    }


    // 常规方法 - 哈希表
//    Map<Integer, List<Integer>> hashMap = new HashMap<>();
//
//    public RandomPickIndex(int[] nums) {
//        for (int i = 0; i < nums.length; i++) {
//            List<Integer> indexs = hashMap.getOrDefault(nums[i], new ArrayList<>());
//            indexs.add(i);
//            hashMap.put(nums[i], indexs);
//        }
//    }
//
//    public int pick(int target) {
//        List<Integer> indexs = hashMap.get(target);
//        int randomIndex = (int) (indexs.size() * Math.random());
//        return indexs.get(randomIndex);
//    }

}
