1. `idlj -fall Calc.idl`
2. `sudo apt get install openjdk-8-jdk-headless`
3. `sudo apt-get install maven`
4. `mvn dependency:copy-dependencies`
5. `nano ~/.bashrc`
6. `export CLASSPATH=/path/to/target/dependency/*`
7. `source ~/.bashrc`
8. `javac CalcApp/*.java CalcApp/CalcPackage/*.java *.java`
9. `orbd -ORBInitialPort 1050 -ORBInitialHost localhost &`
10. `java -cp .:target/dependency/* CalcServer -ORBInitialPort 1050 -ORBInitialHost localhost`
11. `java -cp .:target/dependency/* CalcClient -ORBInitialPort 1050 -ORBInitialHost localhost`
