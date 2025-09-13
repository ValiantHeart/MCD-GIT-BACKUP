echo off

echo Running tests; output will be in results.txt
for /l %%i in (1,1,13) do (
    echo --------------------------
    echo Implementation %%i
    echo javac -cp .;junit-cs211.jar;imp%%i *.java
    javac -cp .;junit-cs211.jar;imp%%i *.java
    java -cp  .;junit-cs211.jar;imp%%i Project5Tests 
) > results.txt
echo Results in results.txt
