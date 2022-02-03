package mobi.zishun.javabase;

public class NumberSystemTest {
    public void lettersTest() {
        int a = 'a';
        int z = 'z';
        System.out.println(a);
        System.out.println(z);
        System.out.println(z - a);
    }

    public static void main(String[] args) {
        NumberSystemTest numberSystemTest = new NumberSystemTest();
        numberSystemTest.lettersTest();
    }
}
