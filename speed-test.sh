testsize=1000000
repeat=10
groovy_version=2.3.7
groovy_all=~/.m2/repository/org/codehaus/groovy/groovy-all/$groovy_version/groovy-all-$groovy_version.jar
classpath=target/classes

echo "Java test"
time java -classpath $classpath tst.speedtest.JavaFilter $testsize $repeat

echo "Groovy Test"
time java -classpath $groovy_all:$classpath tst.speedtest.GroovyFilter $testsize $repeat