package tst.speedtest;

import org.junit.Test;

import java.util.List;

public class JavaFilterTest {

    public static final int TEST_SIZE = 1000000;
    public static final int TEST_REPEAT = 10;

    @Test
    public void testFilter() {

        long start = System.currentTimeMillis();
        JavaFilter test = new JavaFilter();
        for(int i = 1; i <= TEST_REPEAT; i++) {
            List<Integer> list = test.filter(TEST_SIZE);
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("testFilter: for testSize=" + TEST_SIZE + " and repeat=" + TEST_REPEAT + " total elapsed: " + elapsed);
    }

    @Test
    public void testFilterUsingInterface() {

        long start = System.currentTimeMillis();
        JavaFilter test = new JavaFilter();
        for(int i = 1; i <= TEST_REPEAT; i++) {
            List<Integer> list = test.testUsingInterface(TEST_SIZE);
        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("testFilterUsingInterface: for testSize=" + TEST_SIZE + " and repeat=" + TEST_REPEAT + " total elapsed: " + elapsed);
    }
}