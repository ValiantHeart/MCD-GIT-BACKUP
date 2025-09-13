ECHO ON

echo Compile Test impl1
javac impl1/ArraySet.java
javac -cp .;impl1;junit-cs211.jar ArraySetTests.java
java  -cp .;impl1;junit-cs211.jar ArraySetTests

echo Compile Test impl2
javac impl2/ArraySet.java
javac -cp .;impl2;junit-cs211.jar ArraySetTests.java
java  -cp .;impl2;junit-cs211.jar ArraySetTests
