package mobi.zishun.math;

/*
 * 190. 颠倒二进制位
颠倒给定的 32 位无符号整数的二进制位。
请注意，在某些语言（如 Java）中，没有无符号整数类型。
在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
* 在 Java 中，编译器使用二进制补码记法来表示有符号整数。
* 因此，在 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
https://leetcode-cn.com/problems/reverse-bits/
 */
public class ReverseBits {
    // API - 分治
    public int reverseBits(int n) {
        return Integer.reverse(n);
        //        // HD, Figure 7-1
        //        i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
        //        i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
        //        i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;
        //        i = (i << 24) | ((i & 0xff00) << 8) |
        //            ((i >>> 8) & 0xff00) | (i >>> 24);
        //        return i;
    }

    // 逐位颠倒
    public int reverseBitsV2(int n){
        int res = 0;
        for (int i = 0; i < 32 && n != 0; ++i) {
            res |= (n & 1) << (31 - i);
            n >>>= 1;
        }
        return res;
    }

}
