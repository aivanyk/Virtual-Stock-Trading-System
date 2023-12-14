pushd src
javac -d . -sourcepath . *.java View/*.java Controller/*.java Model/*.java Util/*.java
java -cp ".;..\library\mysql-connector-j-8.2.0.jar" com.stock_test.Main
popd