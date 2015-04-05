package tst.speedtest;

import org.junit.Test;

import java.util.List;

public class JavaFilterTest {

    public static final int TEST_SIZE = 1000000;

    @Test
    public void testFilter() {

        long start = System.currentTimeMillis();
        JavaFilter test = new JavaFilter();
        List<Long> list = test.filter(TEST_SIZE);
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("testFilter: " + list.size() + " elapsed: " + elapsed);
    }

    @Test
    public void testFilterUsingInterface() {

        long start = System.currentTimeMillis();
        JavaFilter test = new JavaFilter();
        List<Long> list = test.testUsingInterface(TEST_SIZE);
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("testFilterUsingInterface: " + list.size() + " elapsed: " + elapsed);
    }
}