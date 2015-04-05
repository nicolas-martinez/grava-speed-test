package tst.speedtest

import groovy.transform.CompileStatic

@CompileStatic
class GroovyFilter {

    private List filter(int length) {

        List items = (0..length).collect()
        List even = items.findAll { item -> item > 0 && item.longValue() % 2 == 0 }

        return even;
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        GroovyFilter test = new GroovyFilter();
        List list = test.filter(args[0] as int);
        long elapsed = System.currentTimeMillis() - start;
        print("GroovyTest: "+ list.size() +" elapsed: "+ elapsed);
    }
}
