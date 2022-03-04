package mobi.zishun.array;

/*
 * 1725. 可以形成最大正方形的矩形数目
 */
public class CountGoodRectangles {
    public int countGoodRectangles(int[][] rectangles) {
        int res = 0;
        int maxLength = 0;
        for (int[] rectangle : rectangles) {
            int minLength = Math.min(rectangle[0], rectangle[1]);
            if (minLength == maxLength){
                res++;
            } else if (minLength > maxLength) {
                res = 1;
                maxLength = minLength;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] rectangles = {{5, 8}, {3, 9}, {5, 12}, {16, 5}};
        CountGoodRectangles method =new CountGoodRectangles();
        System.out.println(method.countGoodRectangles(rectangles));
    }

}
