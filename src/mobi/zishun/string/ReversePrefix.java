package mobi.zishun.string;

/*
 * 2000. 反转单词前缀
 */
public class ReversePrefix {
    public String reversePrefix(String word, char ch) {
        int index = word.indexOf(ch);
        if (index >= 0) {
            char[] prefix = word.substring(0, index + 1).toCharArray();
            int prefixLength = prefix.length;
            StringBuilder result = new StringBuilder();
            for (int i = prefixLength - 1; i >= 0; i--) {
                result.append(prefix[i]);
            }
            result.append(word.substring(index + 1));
            return result.toString();
        }
        return word;
    }

    public static void main(String[] args) {
        ReversePrefix method = new ReversePrefix();
        System.out.println(method.reversePrefix("abcdefg", 'c'));
    }

}
