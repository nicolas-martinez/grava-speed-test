package tst.speedtest;

import java.util.ArrayList;
import java.util.List;

public class JavaFilter {

    List<Integer> filter(int length) {

        long start = System.currentTimeMillis();

        List<Integer> items = new ArrayList<Integer>(length);
        for (int i = 0; i < length; i++) {
            items.add(Integer.valueOf(i + 1));
        }

        List<Integer> even = new ArrayList<Integer>();
        for(Integer item : items){
            if (item > 0 && item % 2 == 0) {
                even.add(item);
            }
        }

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Java test: " + even.size() + " elapsed: " + elapsed);

        return even;
    }

    List<Integer> testUsingInterface(int length) {

        long start = System.currentTimeMillis();

        List<Integer> items = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            items.add(Integer.valueOf(i + 1));
        }

        List<Integer> even = new ArrayList<Integer>();

        Closure closure = new Closure() {
            @Override
            public boolean evaluate(Integer item) {
                return item > 0 && item % 2 == 0;
            }
        };

        for(Integer item : items){
            if (closure.evaluate(item)) {
                even.add(item);
            }
        }

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Java testUsingInterface: " + even.size() + " elapsed: " + elapsed);

        return even;
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        JavaFilter test = new JavaFilter();
        int testSize = Integer.valueOf(args[0]);
        int repeat = Integer.valueOf(args[1]);
        for(int i = 1; i <= repeat; i++) {
            //test.filter(testSize);
            test.testUsingInterface(testSize);
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("JavaTest: for testSize=" + testSize + " and repeat=" + repeat + " total elapsed: " + elapsed);
    }

    public static interface Closure {

        public boolean evaluate(Integer value);
    }
}