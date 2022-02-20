package mobi.zishun.array;

/*
 * 717. 1比特与2比特字符
有两种特殊字符：
第一种字符可以用一个比特0来表示
第二种字符可以用两个比特(10或11)来表示、
给定一个以 0 结尾的二进制数组bits，如果最后一个字符必须是一位字符，则返回 true 。
示例1:
输入: bits = [1, 0, 0]
输出: true
解释: 唯一的编码方式是一个两比特字符和一个一比特字符。
所以最后一个字符是一比特字符。
链接：https://leetcode-cn.com/problems/1-bit-and-2-bit-characters
。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsOneBitCharacter {
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        int i = 0;
        while (i < n - 1) {
            if (bits[i] == 1) {
                i += 2;
            } else if (bits[i] == 0) {
                i++;
            }
        }
        return i == n - 1;
    }

}
