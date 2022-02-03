package mobi.zishun.string;

/*
 * BF算法（暴风(Brute Force)算法）
 */
public class BFMatching {
    public int bf(String str, String target) {
        if (target.length() > str.length()) {
            return -1;
        }
        char[] strArr = str.toCharArray();
        char[] targetArr = target.toCharArray();

        int i = 0;
        int j = 0;
        while (i < strArr.length && j < targetArr.length) {
            if (strArr[i] == targetArr[j]) {
                i++;
                j++;
            } else {
                i++;
                i -= j;
                j = 0;
            }
        }
        if (j >= targetArr.length - 1) {
            return i - j;
        }
        return -1;
    }

    public static void main(String[] args) {
        BFMatching method = new BFMatching();
        System.out.println(method.bf("abcdefg", "def"));
    }
}
