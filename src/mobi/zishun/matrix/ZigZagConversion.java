package mobi.zishun.matrix;

/*
 * 6. Z 字形变换
将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行Z 字形排列。
比如输入字符串为 "PAYPALISHIRING"行数为 3 时，排列如下：
P   A   H   N
A P L S I I G
Y   I   R
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
请你实现这个将字符串进行指定行数变换的函数：
链接：https://leetcode-cn.com/problems/zigzag-conversion
 */
public class ZigZagConversion {
    // 字符串 s 是以 ZZ 字形为顺序存储的字符串，目标是按行打印。
    // 按Z字形顺序遍历字符串 s 时，每个字符c对应的行索引是从0增加到numRows-1，再从numRows-1减少到0
    // 因此，解决方案为：模拟这个行索引的变化，在遍历 s 中把每个字符填到正确的行 res[i]
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder[] rowsArr = new StringBuilder[numRows];
        char[] chars = s.toCharArray();
        for (int i = 0; i < numRows; i++) {
            rowsArr[i] = new StringBuilder();
        }
        // 按Z字形顺序遍历字符串 s 时，每个字符c对应的行索引是从0增加到numRows-1，再从numRows-1减少到0
        // 使用flag改变方向
        int row = 0;
        int flag = 1;
        for (char c : chars) {
            rowsArr[row].append(c);
            row += flag;
            if (row == 0) {
                flag = 1;
            } else if (row == numRows - 1) {
                flag = -1;
            }
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder oneRow : rowsArr) {
            result.append(oneRow);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        ZigZagConversion m = new ZigZagConversion();
        System.out.println(m.convert("PAYPALISHIRING", 3));
    }

}
