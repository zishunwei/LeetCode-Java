package mobi.zishun.dynamicprogramming;

/*
 * 数据算法之美 - 40 | 初识动态规划：如何巧妙解决“双十一”购物时的凑单问题？
 * 淘宝的“双十一”购物节有各种促销活动，比如“满 200 元减 50 元”。
 * 假设你女朋友的购物车中有 n 个（n>100）想买的商品，
 * 她希望从里面选几个，在凑够满减条件的前提下，让选出来的商品价格总和最大程度地接近满减条件（200 元）
 */
public class Double11Advance {
    // items商品价格，n商品个数, w表示满减条件，比如200
    // return 选择商品的总价格
    public int double11advance(int[] items, int w) {
        int n = items.length;
        boolean[] states = new boolean[3 * w + 1]; //超过3倍就没有薅羊毛的价值了
        states[0] = true;
        if (items[0] <= 3 * w) {
            states[items[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 3 * w - items[i]; j >= 0; j--) {
                if (states[j]) {
                    states[j + items[i]] = true;
                }
            }
        }

        for (int j = w; j <= 3 * w; j++) {
            if (states[j]) {
                return j;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Double11Advance m = new Double11Advance();
        int[] items = {27, 41, 59, 33, 34, 34, 57};
        System.out.println(m.double11advance(items, 200));
        int[] items2 = {1, 3, 5, 9, 11};
        System.out.println(m.double11advance(items2, 7));
    }
}
