package mobi.zishun.math;

/*
172. 阶乘后的零
给定一个整数 n ，返回 n! 结果中尾随零的数量。
 */
public class TrailingZeroes {
    // 计算因子5：阶乘分解后有5的因数的尾数才会带0 - O(n)
    public int trailingZeroes(int n) {
        if (n < 5) {
            return 0;
        }
        return n / 5 + trailingZeroes(n / 5);
//        int ans = 0;
//        while (n != 0) {
//            n /= 5;
//            ans += n;
//        }
//        return ans;
    }

    // 计算阶层，超出时间限制
    public int trailingZeroesV2(int n) {
        int res = factorial(n);
        int count = 0;
        while (res % 10 == 0) {
            count++;
            res /= 10;
        }
        return count;
    }

    private static int factorial(int n) {
        if (n == 0) {
            return 0;
        }
        int res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    private static int factorialByRecusion(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return n * factorialByRecusion(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(factorial(4));
    }

}