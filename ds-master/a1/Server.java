import java.rmi.*;

public class Server{
	public static void main(String[] args){
		
		// This code block is creating an instance of the `ServerImplementation` class and binding it to the
		// RMI registry with the name "Server". The `Naming.rebind()` method is used to bind the object to
		// the registry. If successful, the message "Server is Ready....." is printed to the console. The
		// code is wrapped in a try-catch block to handle any exceptions that may occur during the binding
		// process.
		try{
			ServerImplementation serverImpl = new ServerImplementation();
			Naming.rebind("Server",serverImpl);
			System.out.println("Server is Ready.....");
		}
		
		// The `catch` block is used to handle any exceptions that may occur during the execution of the
		// `try` block. If an exception occurs, the code inside the `catch` block will be executed. In this
		// case, the code is printing a message to the console indicating that an exception occurred at the
		// server, along with the error message associated with the exception (`e.getMessage()`). This helps
		// to identify and diagnose any issues that may arise during the execution of the program.
		catch(Exception e){
			System.out.println("Exception occured at Server! "+ e.getMessage());
		}	
	}
}
