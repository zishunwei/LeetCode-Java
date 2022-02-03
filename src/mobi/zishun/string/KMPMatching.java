package mobi.zishun.string;

/*
 * KMP 算法是根据三位作者（D.E.Knuth，J.H.Morris 和 V.R.Pratt）的名字来命名的
 * 算法的全称是 Knuth Morris Pratt 算法，简称为 KMP 算法。
 */
public class KMPMatching {
    public int kmp(String str, String pattern) {
        return kmpAlgorithm(str.toCharArray(), str.length(), pattern.toCharArray(), pattern.length());
    }

    // a, b分别是主串和模式串；n, m分别是主串和模式串的长度。
    public int kmpAlgorithm(char[] a, int n, char[] b, int m) {
        int[] next = getNexts(b, m);
        int j = 0;
        for (int i = 0; i < n; ++i) {
            while (j > 0 && a[i] != b[j]) { // 一直找到a[i]和b[j]
                j = next[j - 1] + 1;
            }
            if (a[i] == b[j]) {
                ++j;
            }
            if (j == m) { // 找到匹配模式串的了
                return i - m + 1;
            }
        }
        return -1;
    }

    // b表示模式串，m表示模式串的长度
    private int[] getNexts(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < m; ++i) {
            while (k != -1 && b[k + 1] != b[i]) {
                k = next[k];
            }
            if (b[k + 1] == b[i]) {
                ++k;
            }
            next[i] = k;
        }
        return next;
    }

    // 我们可以用非常笨的方法，比如要计算下面这个模式串 b 的 next[4]，
    // 我们就把 b[0, 4]的所有后缀子串，从长到短找出来，依次看看，是否能跟模式串的前缀子串匹配。
    private int[] getNextsSimply(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        String str = String.valueOf(b);
        for (int i = 1; i < m; i++) {
            String sub = str.substring(0, i + 1);
            next[i] = checkNextStr(sub, i + 1);
        }
        return next;
    }

    private int checkNextStr(String str, int m) {
        int resIndex = -1;
        for (int i = 0; i < m - 1; i++) {
            if (str.substring(0, i + 1).equals(str.substring(m - i - 1, m))) {
                resIndex = i;
            }
        }
        return resIndex;
    }

    public static void main(String[] args) {
        KMPMatching method = new KMPMatching();
        System.out.println(method.kmp("ababaeabacababacd", "ababacd"));
    }

}