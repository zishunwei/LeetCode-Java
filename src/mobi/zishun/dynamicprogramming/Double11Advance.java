package mobi.zishun.dynamicprogramming;

import java.util.LinkedList;
import java.util.List;

/*
 * 数据算法之美 - 40 | 初识动态规划：如何巧妙解决“双十一”购物时的凑单问题？
 * 淘宝的“双十一”购物节有各种促销活动，比如“满 200 元减 50 元”。
 * 假设你女朋友的购物车中有 n 个（n>100）想买的商品，
 * 她希望从里面选几个，在凑够满减条件的前提下，让选出来的商品价格总和最大程度地接近满减条件（200 元）
 */
public class Double11Advance {
    // items商品价格，n商品个数, w表示满减条件，比如200
    // return 选择了哪些价格的商品
    public List<Integer> double11advance(int[] items, int w) {
        int n = items.length;
        boolean[][] states = new boolean[n][3 * w + 1]; //超过3倍就没有薅羊毛的价值了
        states[0][0] = true;
        if (items[0] <= 3 * w) {
            states[0][items[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 3 * w; j++) {
                if (states[i - 1][j]) {
                    states[i][j] = true;
                }
            }
            for (int j = 0; j <= 3 * w - items[i]; j++) {
                if (states[i - 1][j]) {
                    states[i][j + items[i]] = true;
                }
            }
        }

        int j;
        for (j = w; j <= 3 * w; j++) {
            if (states[n - 1][j]) {
                break; // 输出结果大于等于w的最小值, 记录当前j
            }
        }
        List<Integer> res = new LinkedList<>();
        if (j > 3 * w) {
            return res; // 没有可行解
        }
        for (int i = n - 1; i >= 1; i--) {
            // 状态 (i, j) 只有可能从 (i-1, j) 或者 (i-1, j-value[i]) 两个状态推导过来。
            // 所以，我们就检查这两个状态是否是可达的，也就是 states[i-1][j]或者 states[i-1][j-value[i]]是否是 true。
            // 如果 states[i-1][j]可达，就说明我们没有选择购买第 i 个商品，如果 states[i-1][j-value[i]]可达，那就说明我们选择了购买第 i 个商品。
            if (j - items[i] >= 0 && states[i - 1][j - items[i]]) {
                res.add(items[i]);
                j -= items[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Double11Advance m = new Double11Advance();
        int[] items = {27, 41, 59, 33, 34, 34, 57};
        System.out.println(m.double11advance(items, 200));
        int[] items2 = {1, 3, 5, 9, 11};
        System.out.println(m.double11advance(items2, 7));
    }
}
