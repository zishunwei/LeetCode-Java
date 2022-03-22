package mobi.zishun.javabase;

import java.util.HashMap;

/*
 * Java HashMap冲突实例
 * https://blog.csdn.net/winseII/article/details/84123311
 */
public class WorstHashMapTest {

    private static final int testSize = 10000;
    private static final int specialId = 1;
    private static final String specialName = "1";

    /************************************ test1 **************************************/

    class BadModel {
        private final int id;
        private final String name;

        public BadModel(int id, String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * @see java.util.HashMap
         */
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof BadModel) {
                return id == ((BadModel) obj).id && name.equals(((BadModel) obj).name);
            }

            return false;
        }

        @Override
        public int hashCode() {
            return id;
        }
    }

    void testBadModel1() {
        HashMap<BadModel, Object> map = new HashMap<BadModel, Object>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < testSize; i++) {
            BadModel model = new BadModel(specialId, "Name" + i);
            map.put(model, "test" + i);
        }

        long end = System.currentTimeMillis();
        System.out.println("BadModel the same id  : " + (end - start));
    }

    void testBadModel2() {
        HashMap<BadModel, Object> map = new HashMap<BadModel, Object>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < testSize; i++) {
            BadModel model = new BadModel(specialId, specialName);
            map.put(model, "test" + i);
        }
        long end = System.currentTimeMillis();

        System.out.println("BadModel the same id & name : " + (end - start));
    }

    /************************************ test2 **************************************/

    class GoodModel {
        private int id;
        private String name;

        public GoodModel(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof GoodModel) {
                return id == ((GoodModel) obj).id && name.equals(((GoodModel) obj).name);
            }

            return false;
        }

        @Override
        public int hashCode() {
            return id + name.hashCode();
        }
    }

    void testGoodModel() {
        HashMap<GoodModel, Object> map = new HashMap<GoodModel, Object>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < testSize; i++) {
            GoodModel model = new GoodModel(specialId, "Name" + i);
            map.put(model, "test" + i);
        }

        long end = System.currentTimeMillis();
        System.out.println("GoodModel the same id  : " + (end - start));
    }

    public static void main(String[] args) {
        WorstHashMapTest test = new WorstHashMapTest();
        test.testBadModel1();
        test.testBadModel2();
        test.testGoodModel();
    }

}