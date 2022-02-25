package mobi.zishun.math;

/*
 * 537. 复数乘法
复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
实部 是一个整数，取值范围是 [-100, 100]
虚部 也是一个整数，取值范围是 [-100, 100]
i2 == -1
给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。
* 示例 1：
输入：num1 = "1+1i", num2 = "1+1i"
输出："0+2i"
解释：(1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
链接：https://leetcode-cn.com/problems/complex-number-multiplication
 */
public class ComplexNumberMultiply {
    public String complexNumberMultiply(String num1, String num2) {
        String[] num1arr = num1.split("\\+");
        String[] num2arr = num2.split("\\+");
        int a = Integer.parseInt(num1arr[0]);
        int c = Integer.parseInt(num2arr[0]);
        int b = Integer.parseInt(num1arr[1].substring(0, num1arr[1].length() - 1));
        int d = Integer.parseInt(num2arr[1].substring(0, num2arr[1].length() - 1));
        // z1=a+bi，z2=c+di;
        // z1*z2 = (a+bi)(c+di) = ac+adi+bci+bdi2
        int resReal = a * c - b * d;
        int resUnreal = a * d + b * c;
        return resReal + "+" + resUnreal + "i";
    }

    // 正则表达式写法
    public String complexNumberMultiplyV2(String num1, String num2) {
        String[] complex1 = num1.split("\\+|i");
        String[] complex2 = num2.split("\\+|i");
        int real1 = Integer.parseInt(complex1[0]);
        int imag1 = Integer.parseInt(complex1[1]);
        int real2 = Integer.parseInt(complex2[0]);
        int imag2 = Integer.parseInt(complex2[1]);
        return String.format("%d+%di", real1 * real2 - imag1 * imag2, real1 * imag2 + imag1 * real2);
    }

}
