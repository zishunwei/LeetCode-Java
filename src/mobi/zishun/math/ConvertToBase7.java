package mobi.zishun.math;

/*
 * 504. 七进制数
给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
输入: num = 100
输出: "202"
 */
public class ConvertToBase7 {
    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        boolean isMinus = num < 0;
        if (isMinus) {
            num = -num;
        }
        StringBuilder res = new StringBuilder();
        while (num > 0) {
            res.append(num % 7);
            num /= 7;
        }
        if (isMinus){
            res.append('-');
        }
        return res.reverse().toString();
    }

}
