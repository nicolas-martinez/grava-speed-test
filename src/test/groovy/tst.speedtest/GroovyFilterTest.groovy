package tst.speedtest;

import org.junit.Test;

import java.util.List;

public class GroovyFilterTest {

    public static final int TEST_SIZE = 1000000;

    @Test
    public void testFilter() {

        long start = System.currentTimeMillis();
        GroovyFilter test = new GroovyFilter();
        List list = test.filter(TEST_SIZE);
        long elapsed = System.currentTimeMillis() - start;
        println("testFilter: "+ list.size() +" elapsed: "+ elapsed);
    }

}