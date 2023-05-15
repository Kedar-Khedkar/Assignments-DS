## STEP1:

The project starts with defining an IDL file which contains the required interfaces, methods, parameters, events,
arguments, exceptions etc. within a module. The IDL file is saved as “Calc.idl”

## STEP2:

Map the IDL file into its equivalent JAVA file using the following commands:<br>
`idlj –fclient Calc.idl`
`idlj –fserver Calc.idl`

## STEP3

Compile the server using the following command:<br>
`javac CalcServer.java`

Compile the client using the following command:<br>
`javac CalcClient.java`

## STEP4

Compile the codes in CalcApp using the following command:<br>
`javac CalcApp/*.java`

## STEP5

Start the Name Service using the following command:<br>
`start orbd –ORBInitialPort 1050 –ORBInitialHost localhost`

## STEP6

Start the server using the following command:<br>
`java CalcServer –ORBInitialPort 1050 –ORBInitialHost localhost`

## STEP7

Start the Client using the following command:<br>
`java CalcClient –ORBInitialPort 1050 –ORBInitialHost localhost`
