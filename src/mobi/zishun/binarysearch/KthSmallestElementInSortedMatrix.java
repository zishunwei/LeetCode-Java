package mobi.zishun.binarysearch;

import java.util.PriorityQueue;

/*
 * 378. 有序矩阵中第 K 小的元素
给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
你必须找到一个内存复杂度优于 O(n2) 的解决方案。

示例 1：
输入：matrix = [[1,4,7],[2,5,8],[3,6,9]], k = 8
输出：8
提示：
n == matrix.length
n == matrix[i].length
1 <= n <= 300
-10^9 <= matrix[i][j] <= 10^9
题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列
1 <= k <= n2
进阶：
你能否用一个恒定的内存(即 O(1) 内存复杂度)来解决这个问题?
你能在 O(n) 的时间复杂度下解决这个问题吗?这个方法对于面试来说可能太超前了，但是你会发现阅读这篇文章（ this paper ）很有趣。
* https://leetcode.cn/problems/kth-smallest-element-in-a-sorted-matrix/
 */
public class KthSmallestElementInSortedMatrix {

    // 双指针 - O(nlog(r−l))，二分查找进行次数为 O(log(r−l))，每次操作时间复杂度为 O(n)。
    // 1. 找出二维矩阵中最小的数 left，最大的数 right，那么第 k 小的数必定在 left ~ right 之间
    // 2. mid=(left+right)/2；在二维矩阵中寻找小于等于 mid 的元素个数 count
    // 3. 若这个 count 小于 k，表明第 k 小的数在右半部分且不包含 mid，即 left=mid+1, right=right，又保证了第 k 小的数在 left ~ right 之间
    // 4. 若这个 count 大于 k，表明第 k 小的数在左半部分且可能包含 mid，即 left=left, right=mid，又保证了第 k 小的数在 left~right 之间
    // 5. 因为每次循环中都保证了第 k 小的数在 left ~right 之间，当 left==right 时，第 k 小的数即被找出，等于 right
    // * 注意：这里的 left mid right 是数值，不是索引位置。
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int medium = (right - left) / 2 + left;
            // check二维矩阵中 <= mid 的元素总个数是否大于等于k
            if (check(matrix, medium, k, n)) {
                right = medium;
            } else {
                left = medium + 1;
            }
        }
        return left;
    }

    // check二维矩阵中 <= mid 的元素总个数是否大于等于k
    private boolean check(int[][] matrix, int medium, int k, int n) {
        // 以列为单位找，找到每一列最后一个<=mid的数即知道每一列有多少个数<=mid
        int i = n - 1;
        int j = 0;
        int count = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= medium) {
                // 第j列有i+1个元素<=mid
                count += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return count >= k;
    }

    // 优先队列 - O(klong(n)) - 归并 k 次，每次堆中插入和弹出的操作时间复杂度均为 logn。
    public int kthSmallestV2(int[][] matrix, int k) {
        // 小顶堆
        // 队内的为有三个值的数组，1.数值；2.i坐标; 3.j坐标。
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // 将第一列入队
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        // 弹出过程，第k个弹出的元素即为结果
        for (int i = 0; i < k - 1; i++) {
            int[] cur = pq.poll();
            int curi = cur[1];
            int curj = cur[2];
            // 将当前元素右侧元素入队（同一行更大一点的元素）
            // 注意不要越界
            if (curj < n - 1) {
                pq.offer(new int[]{matrix[curi][curj + 1], curi, curj + 1});
            }
        }
        return pq.peek()[0];
    }

}