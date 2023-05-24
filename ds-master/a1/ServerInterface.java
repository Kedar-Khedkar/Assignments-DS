import java.rmi.*;

// This code is defining a Java RMI (Remote Method Invocation) interface called `ServerInterface`. It
// extends the `Remote` interface, which is required for RMI. The interface declares four methods for
// performing basic arithmetic operations: `Addition`, `Subtraction`, `Division`, and `Multiplication`.
// Each method takes two `double` parameters and throws a `RemoteException` if there is a problem with
// the remote method invocation. This interface will be implemented by a remote server and used by a
// client to perform arithmetic operations remotely.
interface ServerInterface extends Remote{
	public double Addition(double num1, double num2) throws RemoteException;
	public double Subtraction(double num1, double num2) throws RemoteException;
	public double Division(double num1, double num2) throws RemoteException;
	public double Multiplication(double num1, double num2) throws RemoteException;
}
