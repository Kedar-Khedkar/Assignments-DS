import java.rmi.*;  
import java.rmi.registry.*;  
public class AddServer
{  
	public static void main(String args[])
	{  
		try
		{  
			Addition stub = new AddImplementor();  
			Naming.rebind("rmi://localhost:5252/sum-server",stub);  
		}
		catch(Exception e)
		{
			System.out.println(e);
		}  
	}  
}  
