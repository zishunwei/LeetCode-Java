package mobi.zishun.others;
/*
121. 买卖股票的最佳时机
 */

public class MaxProfit {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int res = 0;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            }
            if (res < price - minPrice) {
                res = price - minPrice;
            }
        }
        return res;
    }

}
