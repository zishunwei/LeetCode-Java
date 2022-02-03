package mobi.zishun.javabase;

public class NumberSystemTest {
    public void lettersTest() {
        int a = 'a';
        int z = 'z';
        System.out.println(a);
        System.out.println(z);
        System.out.println(z - a);
    }

    // 将字符串转换成二进制字符串，以空格相隔
    private String strToBinstr(String str) {
        char[] strChar = str.toCharArray();
        StringBuilder result = new StringBuilder();
        for (char c : strChar) {
            result.append(Integer.toBinaryString(c));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        NumberSystemTest numberSystemTest = new NumberSystemTest();
        numberSystemTest.lettersTest();

        System.out.println(numberSystemTest.strToBinstr("a"));
        System.out.println(numberSystemTest.strToBinstr("abc"));
    }
}
