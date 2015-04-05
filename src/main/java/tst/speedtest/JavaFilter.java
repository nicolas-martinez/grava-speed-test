package tst.speedtest;

import java.util.ArrayList;
import java.util.List;

public class JavaFilter {

    List<Long> filter(int length) {

        List<Long> items = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            items.add(Long.valueOf(i + 1));
        }

        List<Long> even = new ArrayList<Long>();
        for(Long item : items){
            if (item > 0 && item % 2 == 0) {
                even.add(item);
            }
        }

        return even;
    }

    List<Long> testUsingInterface(int length) {

        List<Long> items = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            items.add(Long.valueOf(i + 1));
        }

        List<Long> even = new ArrayList<Long>();

        Closure closure = new Closure() {
            @Override
            public boolean evaluate(Long item) {
                return item > 0 && item % 2 == 0;
            }
        };

        for(Long item : items){
            if (closure.evaluate(item)) {
                even.add(item);
            }
        }

        return even;
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        JavaFilter test = new JavaFilter();
        //List<Long> list = test.filter(Integer.valueOf(args[0]));
        List<Long> list = test.testUsingInterface(Integer.valueOf(args[0]));
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("JavaTest: "+ list.size() + " elapsed: "+ elapsed);
    }

    public static interface Closure {

        public boolean evaluate(Long value);
    }
}