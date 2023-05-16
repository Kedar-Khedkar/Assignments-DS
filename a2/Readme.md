## STEP1:

IDL (Interface Definition Language) is the language that describes the interface for the server.
Once the client knows that interface, effective communication takes place between the client and the server.
IDL file contains the required interfaces, methods, parameters, events,
arguments, exceptions etc. within a module. The IDL file is saved as “Calc.idl”.

## STEP2:

Map the IDL file into its equivalent JAVA files using the following command:<br>
`idlj –fall Calc.idl`

If getting error like this in Ubuntu: 'command idlj not found', then use the command ```sudo apt get install openjdk-8-jdk-headless```. 
<br>

### The above commands lead to the formation of the following files within the package:

a) `_CalcStub.java` : This file is the Client Stub.<br><br>
b) `Calc.java` : This file contains the JAVA version of the IDL interface. <br><br>
c) `CalcHelper.java` : This Class provides the narrow() to cast the CORBA object references to the proper data types. <br><br>
d) `CalcHolder.java` : This class is used to handle and provide functionalities to in/out/in-out statements.<br><br>
e) `CalcOperations.java` : This Class contains all the methods defined in the IDL interface in its JAVA version.<br><br>
f) `CalcPOA.java` : This file is the Server Skeleton.<br><br>

### These files are stored in CalcPackage Folder<br><br>

g) `DivisionByZero.java` : This Class defines an exception called DivisionByZero, which inherits from the CORBA UserException class. The purpose of this exception is to be thrown when a division by zero occurs in a program that uses the Calc interface defined in the Calc.idl file.<br><br>
h) `DivisionByZeroHelper.java` : This Class provides helper methods [write(), read(), insert() and extract()] for working with instances of the DivisionByZero exception.<br><br>
i) `DivisionByZeroHolder.java` : This Class provides a holder for instances of the DivisionByZero exception. The DivisionByZeroHolder class implements the Streamable interface, which is used by CORBA for marshaling and unmarshaling data between distributed objects.<br><br>

## STEP3
For Installing CORBA and all other required dependencies in Ubuntu.<br>
Enter this in terminal `sudo apt-get install maven`<br>
Then install the dependencies using  `mvn dependency:copy-dependencies`.

## STEP4
Compile the server, client CalcPackage and CalcApp using the following command:<br>
`javac CalcApp/*.java CalcApp/CalcPackage/*.java CalcClient.java CalcServer.java`

## STEP5

Start the Name Service using the following command:<br>

<b>For Windows</b> <br>
`start orbd –ORBInitialPort 1050 –ORBInitialHost localhost`<br>
<b>For Ubuntu</b><br>
`orbd -ORBInitialPort 1050 -ORBInitialHost localhost &`<br>

## STEP6

Start the server using the following command:<br>
<b>For Windows</b> <br>
`java CalcServer –ORBInitialPort 1050 –ORBInitialHost localhost`<br>
<b>For Ubuntu</b><br>
`java -cp .:target/dependency/* CalcServer -ORBInitialPort 1050 -ORBInitialHost localhost`<br>

## STEP7

Start the Client using the following command:<br>

Run this command on new Terminal<br>
<b>For Windows</b> <br>
`java CalcClient –ORBInitialPort 1050 –ORBInitialHost localhost`<br>
<b>For Ubuntu</b><br>
`java -cp .:target/dependency/* CalcClient -ORBInitialPort 1050 -ORBInitialHost localhost`<br>
