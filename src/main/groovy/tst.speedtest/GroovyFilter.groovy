package tst.speedtest

import groovy.transform.CompileStatic

@CompileStatic
class GroovyFilter {

    private List filter(int length) {

        long start = System.currentTimeMillis();

        List items = (0..length)
        List even = items.findAll { int item -> item > 0 && item % 2 == 0 }

        long elapsed = System.currentTimeMillis() - start;
        println("GroovyTest: "+ even.size() +" elapsed: "+ elapsed);

        return even;
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        GroovyFilter test = new GroovyFilter();
        int testSize = args[0] as int
        int repeat = args[1] as int
        (1..repeat).each {
            test.filter(testSize);
        }

        long elapsed = System.currentTimeMillis() - start;
        println("GroovyTest: for testSize=${testSize} and repeat=${repeat} total elapsed: "+ elapsed);
    }
}
