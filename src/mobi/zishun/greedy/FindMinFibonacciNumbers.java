package mobi.zishun.greedy;

import java.util.ArrayList;
import java.util.List;

public class FindMinFibonacciNumbers {
    public int findMinFibonacciNumbers(int k) {
        List<Integer> fibNums = new ArrayList<>();
        int lastTwo = 1;
        int lastOne = 1;
        fibNums.add(lastTwo);
        while (lastOne <= k) {
            fibNums.add(lastOne);
            int temp = lastTwo;
            lastTwo = lastOne;
            lastOne += temp;
        }

        // 从后往前查找
//        int i = fibNums.size() - 1;
//        int diff = k - fibNums.get(i);
//        int res = 1;
//        while (diff > 0) {
//            i--;
//            if (diff >= fibNums.get(i)) {
//                diff -= fibNums.get(i);
//                res++;
//            }
//        }
//        return res;

        // 二分查找
        int fibSize = fibNums.size() - 1;
        int diff = k - fibNums.get(fibSize);
        int res = 1;
        while (diff > 0) {
            int i = binarySearch(fibNums, fibSize, diff);
            diff -= fibNums.get(i);
            fibSize = i - 1;
            res++;
        }
        return res;
    }

    private int binarySearch(List<Integer> nums, int high, int num) {
        int low = 0;
        while (low <= high) {
            int medium = (high + low) / 2;
            int prev = nums.get(medium);
            int next = nums.get(medium + 1);
            if (num >= prev && num < next) {
                return medium;
            } else if (num == next) {
                return medium + 1;
            } else if (num > next) {
                low = medium;
            } else {
                high = medium;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FindMinFibonacciNumbers method = new FindMinFibonacciNumbers();
        System.out.println(method.findMinFibonacciNumbers(143));
    }


}
