#Groovy vs Java speed test comparison.#

The test uses simple list iteration and filtering. While it's certainly limited, this test is inspired by a real world scenario encountered while converting an existing batch process from `Java` to `Groovy`. The existing batch process written in `Java` runs periodically reading the data from different data sources and performing some data transformation. What has been discovered is a significant performance degradation with `unexpectedly high gap of 10+ times` after converting `Java` code to `Groovy`. The code presented here is a simplified example that shows one of the problems discovered with a simple loop and filtering using collection closures.

If you have a suggestion on how to improve `Groovy` performance please send me a pull request and I'll gladly put it on a branch for the side by side comparison. Our team is considering moving to `Groovy` because of some advanced features it offers but it's hard to justify such a move because of such a high gap in performance we have encountered so far.

##Running the test using Maven##
```
git clone https://github.com/nicolas-martinez/grava-speed-test.git
cd grava-speed-test
mvn clean test
```
Additional tests can be run using `./speed-test.sh` (after the compilation performed by `Maven` in the target directory) to take `JUnit` out of equation.

Here are the results of the test when I run it on my `MacBookPro11,3`:
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
And here is the `Java` version:
```
Picked up JAVA_TOOL_OPTIONS: -Xms1024m -Xmx2048m -XX:PermSize=256m -XX:MaxPermSize=1024m
java version "1.7.0_72"
Java(TM) SE Runtime Environment (build 1.7.0_72-b14)
Java HotSpot(TM) 64-Bit Server VM (build 24.72-b04, mixed mode)
```
The `Groovy` version is `2.3.7` as defined in [pom.xml](https://github.com/nicolas-martinez/grava-speed-test/blob/master/pom.xml).
