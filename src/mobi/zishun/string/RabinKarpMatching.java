package mobi.zishun.string;

public class RabinKarpMatching {

    //哈希时需要用到进制计算，这里只涉及26个字母所以使用26进制
    private final int d;

    // 不过，这里有一个小细节需要注意，那就是 26^(m-1) 这部分的计算，我们可以通过查表的方法来提高效率。
    // 我们事先计算好 26^0、26^1、26^2……26^(m-1)，并且存储在一个长度为 m 的数组中，公式中的“次方”就对应数组的下标。
    private final long[] fArr;

    public RabinKarpMatching() {
        d = 26;
        fArr = new long[d];
        long f = 1;
        for (int i = 0; i < d; i++) {
            fArr[i] = f;
            f *= d;
        }
    }

    /**
     * 假设只匹配字母，所以d等于26
     *
     * @param str     主串
     * @param pattern 模式串
     * @return 匹配起始索引
     */
    public long rk(String str, String pattern) {
        int strSize = str.length();
        int patternSize = pattern.length();

        //str子串的hash值
        long strCode = str.charAt(0) - 'a';
        //pattern的hash值
        long patternCode = pattern.charAt(0) - 'a';

        //计算sCode、pCode、h
        for (int i = 1; i < patternSize; i++) {
            patternCode = d * patternCode + pattern.charAt(i) - 'a';
            //计算str第一个子串的hash
            strCode = d * strCode + str.charAt(i) - 'a';
        }

        //最大需要匹配的次数
        long frequency = strSize - patternSize + 1;

        //字符串开始匹配，对patternCode和strCode开始比较，并更新strCode的值
        for (int i = 0; i < frequency; i++) {
            if (strCode == patternCode && ensureMatching(i, str, pattern)) {
                return i;
            }
            //更新strCode的值，即计算str[i+1,i+m-1]子串的hashCode
            strCode = (strCode - fArr[patternSize - 1] * (str.charAt(i) - 'a')) * d + (str.charAt(i + patternSize) - 'a');
        }
        return -1;
    }

    /**
     * hash值一样并不能完全确保字符串一致，所以还需要进一步确认
     *
     * @param i       hash值相同时字符串比对的位置
     * @param pattern 模式串
     * @return 是否确定匹配
     */
    private static boolean ensureMatching(int i, String str, String pattern) {
        String strSub = str.substring(i, i + pattern.length());
        return strSub.equals(pattern);
    }

    public static void main(String[] args) {
        RabinKarpMatching rk = new RabinKarpMatching();
        long res = rk.rk("fedabcaaaaccccdddfff", "cccdddfff");
        System.out.println(res);
    }
}
