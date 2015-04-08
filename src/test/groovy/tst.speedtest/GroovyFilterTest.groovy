package tst.speedtest;

import org.junit.Test;

import java.util.List;

public class GroovyFilterTest {

    public static final int TEST_SIZE = 1000000;
    public static final int TEST_REPEAT = 10;

    @Test
    public void testFilter() {

        long start = System.currentTimeMillis();
        GroovyFilter test = new GroovyFilter();
        (1..TEST_REPEAT).each {
            List list = test.filter(TEST_SIZE);
        }
        long elapsed = System.currentTimeMillis() - start;
        println("testFilter: for testSize=${TEST_SIZE} and repeat=${TEST_REPEAT} total elapsed: "+ elapsed);
    }

}