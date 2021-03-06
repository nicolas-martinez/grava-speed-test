#Groovy vs Java speed test comparison.#

The test uses simple list iteration and filtering. While it's certainly limited, this test is inspired by a real world scenario encountered while converting an existing batch process from `Java` to `Groovy`. The existing batch process written in `Java` runs periodically reading the data from different data sources and performing some data transformation. What has been discovered is a significant performance degradation with `unexpectedly high gap of 5 to 10+ times` after converting `Java` code to `Groovy`. The code presented here is a simplified example that shows one of the problems discovered with a simple loop and filtering using collection closures.

If you have a suggestion on how to improve `Groovy` performance please send me a pull request and I'll gladly put it on a branch for the side by side comparison. Our team is considering moving to `Groovy` because of some advanced features it offers but it's hard to justify such a move because of such a high gap in performance we have encountered so far.

##Running the test using Maven##
```
git clone https://github.com/nicolas-martinez/grava-speed-test.git
cd grava-speed-test
mvn clean test
```

Here are the original results of the test when I run it on my `MacBookPro11,3`:
```
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running tst.speedtest.GroovyFilterTest
testFilter: 500000 elapsed: 342
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.637 sec
Running tst.speedtest.JavaFilterTest
testFilterUsingInterface: 500000 elapsed: 29
testFilter: 500000 elapsed: 27
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.048 sec
```

Below is my hardware profile as reported by `system_profiler SPHardwareDataType`:
```
Hardware:

    Hardware Overview:

      Model Name: MacBook Pro
      Model Identifier: MacBookPro11,3
      Processor Name: Intel Core i7
      Processor Speed: 2.5 GHz
      Number of Processors: 1
      Total Number of Cores: 4
      L2 Cache (per Core): 256 KB
      L3 Cache: 6 MB
      Memory: 16 GB
      Boot ROM Version: MBP112.0138.B11
      SMC Version (system): 2.19f12
```
The `Java` version used is:
```
java version "1.7.0_72"
Java(TM) SE Runtime Environment (build 1.7.0_72-b14)
Java HotSpot(TM) 64-Bit Server VM (build 24.72-b04, mixed mode)
```
The `Groovy` version is `2.3.7` used as defined in [pom.xml](https://github.com/nicolas-martinez/grava-speed-test/blob/master/pom.xml).

After implementing the suggestions from [stackoverflow](http://stackoverflow.com/questions/29460746/looking-for-ideas-on-how-to-improve-groovy-performance-in-the-specific-test) to use the specific types, removing extra `collect()` call from the original `groovy` code and introducing a warm up cycle from I'm still seeing that the `groovy` code is about `~5 times slower` than its `java` equivalent. 

Additional tests can be run using `./speed-test.sh` (after the compilation performed by `Maven` in the target directory) to take `JUnit` out of equation. 

```
/speed-test.sh
Java test
Java testUsingInterface: 500000 elapsed: 44
Java testUsingInterface: 500000 elapsed: 43
Java testUsingInterface: 500000 elapsed: 28
Java testUsingInterface: 500000 elapsed: 11
Java testUsingInterface: 500000 elapsed: 31
Java testUsingInterface: 500000 elapsed: 10
Java testUsingInterface: 500000 elapsed: 9
Java testUsingInterface: 500000 elapsed: 11
Java testUsingInterface: 500000 elapsed: 19
Java testUsingInterface: 500000 elapsed: 19
JavaTest: for testSize=1000000 and repeat=10 total elapsed: 226

Groovy Test
GroovyTest: 500000 elapsed: 199
GroovyTest: 500000 elapsed: 76
GroovyTest: 500000 elapsed: 91
GroovyTest: 500000 elapsed: 80
GroovyTest: 500000 elapsed: 58
GroovyTest: 500000 elapsed: 83
GroovyTest: 500000 elapsed: 91
GroovyTest: 500000 elapsed: 58
GroovyTest: 500000 elapsed: 58
GroovyTest: 500000 elapsed: 67
GroovyTest: for testSize=1000000 and repeat=10 total elapsed: 1073
```
