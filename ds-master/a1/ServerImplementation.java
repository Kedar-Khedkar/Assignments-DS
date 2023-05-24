import java.rmi.*;
import java.rmi.server.*;

/**
 * The ServerImplementation class implements the ServerInterface and provides methods for basic
 * arithmetic operations.
 */
public class ServerImplementation extends UnicastRemoteObject implements ServerInterface{
	
	public ServerImplementation() throws RemoteException{
	
	}
	
	public double Addition(double num1, double num2) throws RemoteException{
		return num1 + num2;
	}
	
	public double Subtraction(double num1, double num2) throws RemoteException{
		return num1 - num2;
	}
	
	public double Multiplication(double num1, double num2) throws RemoteException{
		return num1 * num2;
	}
	
	public double Division(double num1, double num2) throws RemoteException{
		return num1 / num2;
	}
} 
