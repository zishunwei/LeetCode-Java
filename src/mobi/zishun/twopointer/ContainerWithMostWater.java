package mobi.zishun.twopointer;

/*
 * 11. 盛最多水的容器
 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。
 在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。
 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 链接：https://leetcode-cn.com/problems/container-with-most-water
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int res = 0;
        while (i < j) {
            if (height[i] <= height[j]) {
                int maxArea = height[i] * (j - i);
                if (maxArea > res) {
                    res = maxArea;
                }
                i++;
            } else {
                int maxArea = height[j] * (j - i);
                if (maxArea > res) {
                    res = maxArea;
                }
                j--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ContainerWithMostWater method = new ContainerWithMostWater();
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(method.maxArea(height));
    }

}
